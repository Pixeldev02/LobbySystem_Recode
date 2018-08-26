package de.pixeldev02.lobbysystem.gadgets;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class GadgetItemMain {

    public static HashMap<ItemStack, GadgetItem> currentItems = new HashMap<ItemStack, GadgetItem>();
    public static HashMap<Integer, GadgetItem> idsOfItems = new HashMap<Integer, GadgetItem>();
}
