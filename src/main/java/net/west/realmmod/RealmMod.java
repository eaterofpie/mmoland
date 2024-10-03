package net.west.realmmod;

import net.fabricmc.api.ModInitializer;

import net.west.realmmod.block.ModBlocks;
import net.west.realmmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RealmMod implements ModInitializer {
	public static final String MOD_ID = "realmmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlock();

	}
}