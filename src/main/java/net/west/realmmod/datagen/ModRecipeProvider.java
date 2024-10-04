package net.west.realmmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.west.realmmod.RealmMod;
import net.west.realmmod.block.ModBlocks;
import net.west.realmmod.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        List<ItemConvertible> MITHRIL_SMELTABLES = List.of(ModItems.RAW_MITHRIL, ModBlocks.MITHRIL_ORE,
                ModBlocks.DEEPSLATE_MITHRIL_ORE);

        offerSmelting(exporter, MITHRIL_SMELTABLES, RecipeCategory.MISC, ModItems.MITHRIL, 0.25f, 200, "pink_garnet");
        offerBlasting(exporter, MITHRIL_SMELTABLES, RecipeCategory.MISC, ModItems.RAW_MITHRIL, 0.25f, 100, "pink_garnet");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.MITHRIL, RecipeCategory.DECORATIONS, ModBlocks.MITHRIL_STONE);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.ANGBAND, RecipeCategory.DECORATIONS, ModBlocks.ANGBAND_COBBLE);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MITHRIL_COBBLE)
                .pattern("RR")
                .pattern("RR")
                .input('R', ModItems.MITHRIL)
                .criterion(hasItem(ModItems.MITHRIL), conditionsFromItem(ModItems.MITHRIL))
                .offerTo(exporter, Identifier.of(RealmMod.MOD_ID, "mithril_cobble_from_raw_mithril"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MITHRIL_STONE)
                .pattern("RR")
                .pattern("RR")
                .input('R', ModBlocks.MITHRIL_COBBLE)
                .criterion(hasItem(ModBlocks.MITHRIL_COBBLE), conditionsFromItem(ModBlocks.MITHRIL_COBBLE))
                .offerTo(exporter, Identifier.of(RealmMod.MOD_ID, "mithril_stone_from_mithril_cobble"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MITHRIL_BRICK)
                .pattern("RR")
                .pattern("RR")
                .input('R', ModBlocks.MITHRIL_STONE)
                .criterion(hasItem(ModBlocks.MITHRIL_STONE), conditionsFromItem(ModBlocks.MITHRIL_STONE))
                .offerTo(exporter, Identifier.of(RealmMod.MOD_ID, "mithril_brick_from_mithril_stone"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ANGBAND_COBBLE)
                .pattern("AA")
                .pattern("AA")
                .input('A', ModItems.ANGBAND)
                .criterion(hasItem(ModItems.ANGBAND), conditionsFromItem(ModItems.ANGBAND))
                .offerTo(exporter, Identifier.of(RealmMod.MOD_ID, "angband_cobble_from_angband"));
    }
}
