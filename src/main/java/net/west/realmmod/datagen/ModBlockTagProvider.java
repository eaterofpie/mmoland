package net.west.realmmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.west.realmmod.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
        .add(ModBlocks.MITHRIL_COBBLE)
        .add(ModBlocks.MITHRIL_STONE)
        .add(ModBlocks.MITHRIL_BRICK)
        .add(ModBlocks.MITHRIL_ORE)
        .add(ModBlocks.ANGBAND_COBBLE)
        .add(ModBlocks.ANGBAND_STONE)
        .add(ModBlocks.ANGBAND_ORE)
        .add(ModBlocks.MORDOR_ORE)
        .add(ModBlocks.DEEPSLATE_MITHRIL_ORE);

    }
}
