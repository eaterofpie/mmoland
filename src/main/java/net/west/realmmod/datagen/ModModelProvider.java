package net.west.realmmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.west.realmmod.block.ModBlocks;
import net.west.realmmod.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MITHRIL_COBBLE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MITHRIL_STONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MITHRIL_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_MITHRIL_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MITHRIL_BRICK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ANGBAND_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ANGBAND_COBBLE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ANGBAND_STONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MORDOR_ORE);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.RAW_MITHRIL, Models.GENERATED);
        itemModelGenerator.register(ModItems.MITHRIL, Models.GENERATED);
        itemModelGenerator.register(ModItems.DWARF_IRON, Models.GENERATED);
        itemModelGenerator.register(ModItems.ANGBAND, Models.GENERATED);

    }
}
