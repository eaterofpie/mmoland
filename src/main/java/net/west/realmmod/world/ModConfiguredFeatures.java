package net.west.realmmod.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer;
import net.west.realmmod.RealmMod;
import net.west.realmmod.block.ModBlocks;

import java.util.List;



public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> MITHRIL_ORE_KEY = registerKey("mithril_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ANGBAND_ORE_KEY  = registerKey("angband_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MORDOR_ORE_KEY = registerKey("mordor_ore");



    public static final RegistryKey<ConfiguredFeature<?, ?>> MITHRIL_KEY = registerKey("mithril");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneOreReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateOreReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherOreReplaceables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endOreReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);

        List<OreFeatureConfig.Target> overworldHobbitTargets = List.of(
                OreFeatureConfig.createTarget(stoneOreReplaceables, ModBlocks.MITHRIL_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateOreReplaceables, ModBlocks.DEEPSLATE_MITHRIL_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> netherAngbandTargets = List.of(
                OreFeatureConfig.createTarget(netherOreReplaceables, ModBlocks.ANGBAND_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> endMordorTargets = List.of(
                OreFeatureConfig.createTarget(endOreReplaceables, ModBlocks.MORDOR_ORE.getDefaultState()));

        register(context, MITHRIL_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldHobbitTargets, 9));
        register(context, ANGBAND_ORE_KEY, Feature.ORE, new OreFeatureConfig(netherAngbandTargets, 9));
        register(context, MORDOR_ORE_KEY, Feature.ORE, new OreFeatureConfig(endMordorTargets, 9));

        RegistryEntryLookup<PlacedFeature> registryLookup = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);




        register(context, MITHRIL_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                SimpleBlockStateProvider.of(ModBlocks.MITHRIL_LOG), // log
                new DarkOakTrunkPlacer(4, 4, 6), // baseHeight, firstRandomHeight, secondRandomHeight

                SimpleBlockStateProvider.of(ModBlocks.MITHRIL_LEAVES), // leaves
                new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 2), // radius, offset, height

                new TwoLayersFeatureSize(3, 0, 3) // limit, lowerSize, upperSize
        ).build());

}
public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
    return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(RealmMod.MOD_ID, name));
}

private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                               RegistryKey<ConfiguredFeature<?, ?>> key,
                                                                               F feature,
                                                                               FC featureConfig) {
    context.register(key, new ConfiguredFeature<>(feature, featureConfig));
}
}
