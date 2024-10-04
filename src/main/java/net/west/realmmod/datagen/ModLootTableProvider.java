package net.west.realmmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.west.realmmod.block.ModBlocks;
import net.west.realmmod.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.MITHRIL_COBBLE);
        addDrop(ModBlocks.MITHRIL_STONE);
        addDrop(ModBlocks.MITHRIL_BRICK);
        addDrop(ModBlocks.SILVER_LEAVES);
        addDrop(ModBlocks.MITHRIL_LEAVES);
        addDrop(ModBlocks.MITHRIL_LOG);

        addDrop(ModBlocks.MITHRIL_ORE, oreDrops(ModBlocks.MITHRIL_ORE, ModItems.MITHRIL));
        addDrop(ModBlocks.ANGBAND_ORE, oreDrops(ModBlocks.ANGBAND_ORE, ModItems.MITHRIL));
        addDrop(ModBlocks.MORDOR_ORE, oreDrops(ModBlocks.MORDOR_ORE, ModItems.MITHRIL));
        addDrop(ModBlocks.DEEPSLATE_MITHRIL_ORE, multipleOreDrops(ModBlocks.DEEPSLATE_MITHRIL_ORE, ModItems.RAW_MITHRIL, 3,7));

    }
    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));

    }
}
