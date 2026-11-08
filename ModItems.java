package com.sacos;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public class ModItems {
	// Bolsa normal: 3 filas x 9 = 27 espacios (inventario guardado dentro del propio item)
	public static final Item BOLSA = register("bolsa", 3, false);
	// Ender Bag: abre tu ender chest (el mismo contenido en todo el mundo)
	public static final Item ENDER_BAG = register("ender_bag", 3, true);
	// Phora Bag: 6 filas x 9 = 54 espacios (cambia el 6 por otro numero de filas, de 1 a 6)
	public static final Item PHORA_BAG = register("phora_bag", 6, false);

	private static Item register(String name, int rows, boolean ender) {
		return Registry.register(BuiltInRegistries.ITEM, SacosMod.id(name),
				new BagItem(new Item.Properties().stacksTo(1), rows, ender));
	}

	public static void init() {
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
			entries.accept(BOLSA);
			entries.accept(ENDER_BAG);
			entries.accept(PHORA_BAG);
		});
	}
}
