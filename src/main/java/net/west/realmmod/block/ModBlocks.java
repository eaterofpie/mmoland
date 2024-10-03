package net.west.realmmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.west.realmmod.RealmMod;

public class ModBlocks {
    public static final Block MITHRIL_STONE = registerBlock("mithril_stone",
            new Block(AbstractBlock.Settings.create().strength(5F).requiresTool().luminance(state -> 6)));
    public static final Block MITHRIL_LOG = registerBlock("mithril_log",
           new PillarBlock( AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable().luminance(state -> 6)));
    public static final Block MITHRIL_LEAVES = registerBlock("mithril_leaves",
    new LeavesBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.AZALEA_LEAVES).nonOpaque().allowsSpawning(Blocks::canSpawnOnLeaves).suffocates(Blocks::never).blockVision(Blocks::never).burnable().pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never).luminance(state -> 6)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(RealmMod.MOD_ID,name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(RealmMod.MOD_ID,name),
                new BlockItem(block, new Item.Settings()));
    }

      public static void registerModBlock() {
          RealmMod.LOGGER.info("Registering Mod Blocks for "+ RealmMod.MOD_ID);

          ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
              entries.add(ModBlocks.MITHRIL_STONE);
              entries.add(ModBlocks.MITHRIL_LOG);
              entries.add(ModBlocks.MITHRIL_LEAVES);
          });
      }
}
