package com.sacos;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;

/** Inventario de una bolsa: se lee y se guarda dentro del propio item (componente CONTAINER). */
public class BagContainer extends SimpleContainer {
	private final ItemStack bag;
	private boolean loading = true;

	public BagContainer(ItemStack bag, int size) {
		super(size);
		this.bag = bag;
		NonNullList<ItemStack> items = NonNullList.withSize(size, ItemStack.EMPTY);
		bag.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyInto(items);
		for (int i = 0; i < size; i++) {
			setItem(i, items.get(i));
		}
		loading = false;
	}

	@Override
	public void setChanged() {
		super.setChanged();
		if (!loading) {
			List<ItemStack> list = new ArrayList<>();
			for (int i = 0; i < getContainerSize(); i++) {
				list.add(getItem(i));
			}
			bag.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(list));
		}
	}
}
