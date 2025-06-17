package com.euphony.defiled_lands_reborn.common.entity;

import com.euphony.defiled_lands_reborn.common.init.DLEntities;
import com.euphony.defiled_lands_reborn.common.init.DLItems;
import com.euphony.defiled_lands_reborn.common.init.DLSounds;
import com.euphony.defiled_lands_reborn.config.ConfigHelper;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.extensions.IHolderExtension;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public class BookWyrm extends Animal {
    private int enchLevel, digestingTime;
    private int digested, toDigest, digestTimer;

    public BookWyrm(EntityType<? extends BookWyrm> type, Level world) {
        super(type, world);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new PanicGoal(this, 1.3));
        goalSelector.addGoal(2, new BreedGoal(this, 1));
        goalSelector.addGoal(3, new TemptGoal(this, 1.2, Ingredient.of(DLItems.FOUL_CANDY, Items.ENCHANTED_BOOK), false));
        goalSelector.addGoal(4, new FollowParentGoal(this, 1.2));
        goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1));
        goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6f));
        goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createAnimalAttributes().add(Attributes.MAX_HEALTH, 12).add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (level().isClientSide && isDigesting()) {
            for (int i = 0; i < 2; ++i) {
                level().addParticle(ParticleTypes.ENCHANT, getRandomX(0.5), getRandomY(), getRandomZ(0.5), (random.nextDouble() - 0.5) * 2.0, -random.nextDouble(), (random.nextDouble() - 0.5) * 2.0);
            }
        }
        if (!level().isClientSide && toDigest > 0) {
            digestTimer--;
            if (digestTimer <= 0) {
                digested++;
                toDigest--;
                digestTimer = digestingTime;
                if (digested >= enchLevel) {
                    digested -= enchLevel;
                    makeBook();
                }
            }
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!isBaby() && (stack.is(Items.ENCHANTED_BOOK))) {
            toDigest += getBookValue(stack);
            if (digestTimer <= 0) digestTimer = digestingTime;

            stack.consume(1, player);

            playSound(SoundEvents.GENERIC_EAT.value(), 1, 1);
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    private void makeBook() {
        ItemStack stack;
        List<EnchantmentInstance> enchantmentInstances = getPossibleEnchantments();
        stack = new ItemStack(Items.ENCHANTED_BOOK);
        for (EnchantmentInstance e : enchantmentInstances) {
            stack.enchant(e.enchantment(), e.level());
        }
        playSound(DLSounds.wyrmBook.get(), 1, 1);
        spawnAtLocation((ServerLevel) level(), stack);
    }

    public List<EnchantmentInstance> getPossibleEnchantments() {
        List<EnchantmentInstance> enchantmentInstances = EnchantmentHelper.selectEnchantment(random, Items.BOOK.getDefaultInstance(), enchLevel, level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).stream().map(a -> a.exclusiveSet().get(0)).map(IHolderExtension::getDelegate));
        enchantmentInstances.removeIf(enchantmentInstance -> enchantmentInstance.enchantment().tags().noneMatch(p -> p.isFor(EnchantmentTags.IN_ENCHANTING_TABLE.registry())));
        return enchantmentInstances;
    }

    public static int getBookValue(ItemStack stack) {
        int total = 0;

        for (Object2IntMap.Entry<Holder<Enchantment>> e : EnchantmentHelper.getEnchantmentsForCrafting(stack).entrySet()) {
            total += e.getKey().value().getMinCost(e.getIntValue());
        }

        return total;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_, EntitySpawnReason p_363316_, @Nullable SpawnGroupData p_146749_) {
        wildGenes(this, random);
        return super.finalizeSpawn(p_146746_, p_146747_, p_363316_, p_146749_);
    }

    public static void wildGenes(BookWyrm wyrm, RandomSource rand) {
        wyrm.digestingTime = rand.nextInt(81) + 160;
        wyrm.enchLevel = rand.nextInt(4) + 3;
    }

    @Override
    public boolean canMate(Animal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        } else {
            return otherAnimal instanceof BookWyrm && this.canParent() && ((BookWyrm) otherAnimal).canParent();
        }
    }

    protected boolean canParent() {
        return !this.isVehicle() && !this.isPassenger() && !this.isBaby() && this.getHealth() >= this.getMaxHealth() && this.isInLove();
    }

    public static void mixGenes(BookWyrm a, BookWyrm b, BookWyrm child, RandomSource rand) {
        int maxLevel = Math.max(a.enchLevel, b.enchLevel);
        int level = a.enchLevel + b.enchLevel + rand.nextInt(maxLevel + 1);
        child.enchLevel = Math.min(level / 2, ConfigHelper.common().entity.bookWyrmMaxEnchantingLevel.get());

        int maxTime = Math.max(a.digestingTime, b.digestingTime);
        int k = a.digestingTime + b.digestingTime - rand.nextInt((int)(maxTime + 0.75));
        child.digestingTime = k / 2;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(DLItems.FOUL_CANDY);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return DLSounds.wyrmIdle.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return DLSounds.wyrmHurt.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return DLSounds.wyrmDeath.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    public boolean isDigesting() {
        return toDigest > 0;
    }

    @Override
    public void readAdditionalSaveData(ValueInput compound) {
        super.readAdditionalSaveData(compound);
        enchLevel = compound.getInt("EnchantingLevel").get();
        digestingTime = compound.getInt("DigestingTime").get();
        digested = compound.getInt("Digested").get();
        toDigest = compound.getInt("ToDigest").get();
        digestTimer = compound.getInt("DigestTimer").get();
    }

    @Override
    public void addAdditionalSaveData(ValueOutput compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("EnchantingLevel", enchLevel);
        compound.putInt("DigestingTime", digestingTime);
        compound.putInt("Digested", digested);
        compound.putInt("ToDigest", toDigest);
        compound.putInt("DigestTimer", digestTimer);
    }

    public boolean isGolden(AgeableMob mate) {
        double i = ConfigHelper.common().entity.goldenBookWyrmProbabilityForZeroGolden.get();
        if (mate instanceof GoldenBookWyrm) {
            i = ConfigHelper.common().entity.goldenBookWyrmProbabilityForOneGolden.get();
        }
        if (random.nextDouble() <= i) {
            return true;
        }
        return false;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mate) {
        if(mate instanceof BookWyrm) {
            if (isGolden(mate)) {
                GoldenBookWyrm child = DLEntities.GOLDEN_BOOK_WYRM.get().create(level, EntitySpawnReason.BREEDING);
                mixGenes(this, (BookWyrm) mate, child, random);
                return child;
            } else {
                BookWyrm child = DLEntities.BOOK_WYRM.get().create(level, EntitySpawnReason.BREEDING);
                mixGenes(this, (BookWyrm) mate, child, random);
                return child;
            }
        }
        return null;
    }

    @Override
    @Nonnull
    public Packet<ClientGamePacketListener> getAddEntityPacket(@Nonnull ServerEntity serverEntity) {
        return new ClientboundAddEntityPacket(this, serverEntity);
    }

    public int getDigestingTime() {
        return digestingTime;
    }

    public int getEnchLevel() {
        return enchLevel;
    }

    public int getDigested() {
        return digested;
    }

    public int getToDigest() {
        return toDigest;
    }
}
