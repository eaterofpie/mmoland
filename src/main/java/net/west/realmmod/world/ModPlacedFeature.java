package net.west.realmmod.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;
import net.west.realmmod.RealmMod;
import net.west.realmmod.block.ModBlocks;

import java.util.List;

public class ModPlacedFeature {
    public static final RegistryKey<PlacedFeature> MITHRIL_ORE_PLACED_KEY = registerKey("mithril_ore_placed");
    public static final RegistryKey<PlacedFeature> ANGBAND_ORE_PLACED_KEY = registerKey("angband_ore_placed");
    public static final RegistryKey<PlacedFeature> MORDOR_ORE_PLACED_KEY = registerKey("mordor_ore_placed");


    public static final RegistryKey<PlacedFeature> MITHRIL_KEY = registerKey("mithril");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, MITHRIL_ORE_PLACED_KEY, registryLookup.getOrThrow(ModConfiguredFeatures.MITHRIL_ORE_KEY),
                Modifiers.modifiersCount(9,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-24), YOffset.fixed(64))));

        register(context, ANGBAND_ORE_PLACED_KEY, registryLookup.getOrThrow(ModConfiguredFeatures.ANGBAND_ORE_KEY),
                Modifiers.modifiersCount(9,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));

        register(context, MORDOR_ORE_PLACED_KEY, registryLookup.getOrThrow(ModConfiguredFeatures.MORDOR_ORE_KEY),
                Modifiers.modifiersCount(9,


                        HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));
        register(context, MITHRIL_KEY, registryLookup.getOrThrow(ModConfiguredFeatures.MITHRIL_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(1, 0.2f, 3),
                        ModBlocks.MITHRIL_SAPLING));
    }
    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(RealmMod.MOD_ID, name));

    }

    private static void register(Registerable<PlacedFeature> context,
                                 RegistryKey<PlacedFeature> key,
                                 RegistryEntry<ConfiguredFeature<?, ?>> config,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }


    public static class Modifiers {
        public static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
            return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
        }

        public static List<PlacementModifier> modifiersCount(int count, PlacementModifier heightModifier) {
            return modifiers(CountPlacementModifier.of(count), heightModifier);
        }

        public static List<PlacementModifier> modifiersRarity(int chance, PlacementModifier heightModifier) {
            return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
        }
    }
}
