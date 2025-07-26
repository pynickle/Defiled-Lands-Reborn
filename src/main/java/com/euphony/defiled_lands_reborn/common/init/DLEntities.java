package com.euphony.defiled_lands_reborn.common.init;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import com.euphony.defiled_lands_reborn.common.entity.*;
import com.euphony.defiled_lands_reborn.common.entity.boss.DestroyerBoss;
import com.euphony.defiled_lands_reborn.common.entity.boss.MournerBoss;
import com.euphony.defiled_lands_reborn.common.entity.projectile.BlastemFruitProjectile;
import com.euphony.defiled_lands_reborn.common.entity.projectile.BlazingBlastemFruitProjectile;
import com.euphony.defiled_lands_reborn.common.entity.projectile.RavagerProjectile;
import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@EventBusSubscriber(modid = DefiledLandsReborn.MOD_ID)
public class DLEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, DefiledLandsReborn.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<DefiledSlime>> DEFILED_SLIME = registerMob("defiled_slime", 0xbe6d91, 0xc873a0, () -> EntityType.Builder.of(DefiledSlime::new, MobCategory.MONSTER)
            .sized(0.52F, 0.52F).eyeHeight(0.325F).spawnDimensionsScale(4.0F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<BookWyrm>> BOOK_WYRM = registerMob("book_wyrm", 0x412c41, 0xfef9e4, () -> EntityType.Builder.of(BookWyrm::new, MobCategory.CREATURE)
            .sized(0.9f, 0.9f).eyeHeight(0.125f).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<GoldenBookWyrm>> GOLDEN_BOOK_WYRM = registerMob("golden_book_wyrm", 0xf1ca00, 0xfef9e4, () -> EntityType.Builder.of(GoldenBookWyrm::new, MobCategory.CREATURE)
            .sized(0.9f, 0.9f).eyeHeight(0.125f).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Scuttler>> SCUTTLER = registerMob("scuttler", 0x211823, 0xce0bff, () -> EntityType.Builder.of(Scuttler::new, MobCategory.MONSTER)
            .sized(1.4F, 0.9F).eyeHeight(0.65F).passengerAttachments(0.765F).clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<Host>> HOST = registerMob("host", 0x3a3a3a, 0xc873a0, () -> EntityType.Builder.of(Host::new, MobCategory.MONSTER)
            .sized(0.6F, 1.95F).eyeHeight(1.74F).passengerAttachments(2.0125F).ridingOffset(-0.7F).clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<Shambler>> SHAMBLER = registerMob("shambler", 0x171717, 0xebebeb, () -> EntityType.Builder.of(Shambler::new, MobCategory.MONSTER)
            .sized(0.6F, 2.9F).eyeHeight(2.55F).passengerAttachments(2.80625F).clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<TwistedShambler>> TWISTED_SHAMBLER = registerMob("twisted_shambler", 0x171717, 0xf62e2e, () -> EntityType.Builder.of(TwistedShambler::new, MobCategory.MONSTER)
            .sized(0.6F, 2.9F).eyeHeight(2.55F).passengerAttachments(2.80625F).clientTrackingRange(8));

    public static final DeferredHolder<EntityType<?>, EntityType<MournerBoss>> MOURNER = registerMob("the_mourner", 0x262626, 0xf9f9f9, () -> EntityType.Builder.of(MournerBoss::new, MobCategory.MONSTER)
            .sized(0.7F, 2.4F).eyeHeight(2.14F).fireImmune().clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<DestroyerBoss>> DESTROYER = registerMob("the_destroyer", 0xa9a9a9, 0xf9f9f9, () -> EntityType.Builder.of(DestroyerBoss::new, MobCategory.MONSTER)
            .sized(0.7F, 2.4F).eyeHeight(2.14F).fireImmune().clientTrackingRange(10));

    public static final DeferredHolder<EntityType<?>, EntityType<RavagerProjectile>> RAVAGER_PROJECTILE = registerMisc("ravager_projectile", () -> EntityType.Builder.<RavagerProjectile>of(RavagerProjectile::new, MobCategory.MISC)
            .sized(0.3125F, 0.3125F).clientTrackingRange(4).updateInterval(10));
    public static final DeferredHolder<EntityType<?>, EntityType<BlastemFruitProjectile>> BLASTEM_FRUIT_PROJECTILE = registerMisc("blastem_fruit_projectile", () -> EntityType.Builder.<BlastemFruitProjectile>of(BlastemFruitProjectile::new, MobCategory.MISC)
            .sized(0.3125F, 0.3125F).clientTrackingRange(4).updateInterval(10));
    public static final DeferredHolder<EntityType<?>, EntityType<BlazingBlastemFruitProjectile>> BLAZING_BLASTEM_FRUIT_PROJECTILE = registerMisc("blazing_blastem_fruit_projectile", () -> EntityType.Builder.<BlazingBlastemFruitProjectile>of(BlazingBlastemFruitProjectile::new, MobCategory.MISC)
            .sized(0.3125F, 0.3125F).clientTrackingRange(4).updateInterval(10));

    public static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> registerMisc(String name, Supplier<EntityType.Builder<E>> builder) {
        return ENTITIES.register(name, () -> builder.get().build(Utils.prefix(name).toString()));
    }

    private static <T extends Mob> DeferredHolder<EntityType<?>, EntityType<T>> registerMob(String name, int eggPrimary, int eggSecondary, Supplier<EntityType.Builder<T>> builder) {
        DeferredHolder<EntityType<?>, EntityType<T>> entityType = register(name, builder);
        DeferredItem<Item> spawnEggItem = DLItems.register( name + "_spawn_egg", p -> new DeferredSpawnEggItem(entityType, eggPrimary, eggSecondary, p));
        DLItems.SPAWN_EGGS.add(spawnEggItem);
        return entityType;
    }

    public static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String name, Supplier<EntityType.Builder<T>> builder) {
        ResourceLocation location = Utils.prefix(name);
        return ENTITIES.register(name, () -> builder.get().build(location.toString()));
    }


    @SubscribeEvent
    public static void setSpawnPlacement(RegisterSpawnPlacementsEvent event) {
        event.register(DEFILED_SLIME.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DefiledSlime::checkDefiledSlimeSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(BOOK_WYRM.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BookWyrm::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(GOLDEN_BOOK_WYRM.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GoldenBookWyrm::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(SCUTTLER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Scuttler::checkScuttlerSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(HOST.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Zombie::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(SHAMBLER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Shambler::checkShamblerSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(TWISTED_SHAMBLER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TwistedShambler::checkTwistedShamblerSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);

        event.register(MOURNER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(DESTROYER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
    }

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(DEFILED_SLIME.get(), Monster.createMonsterAttributes().build());
        event.put(BOOK_WYRM.get(), BookWyrm.createAttributes().build());
        event.put(GOLDEN_BOOK_WYRM.get(), GoldenBookWyrm.createAttributes().build());
        event.put(SCUTTLER.get(), Scuttler.createAttributes().build());
        event.put(HOST.get(), Host.createAttributes().build());
        event.put(SHAMBLER.get(), Shambler.createAttributes().build());
        event.put(TWISTED_SHAMBLER.get(), TwistedShambler.createAttributes().build());

        event.put(MOURNER.get(), MournerBoss.createAttributes().build());
        event.put(DESTROYER.get(), DestroyerBoss.createAttributes().build());
    }
}
