package de.pixeldev02.lobbysystem.gadgets;

import org.bukkit.inventory.ItemStack;

public class GadgetItem {

    int id;
    int slot;
    ItemStack display;
    GadgetItemType type;
    GadgetItemRarity rarity;

    public GadgetItem(int id, ItemStack display,  GadgetItemType type, GadgetItemRarity rarity, int slot) {
        this.display = display;
        this.type = type;
        this.rarity = rarity;
        this.id = id;
        this.slot = slot;
        GadgetItemMain.currentItems.put(display, this);
        GadgetItemMain.idsOfItems.put(id, this);
    }

    public int getId() {
        return this.id;
    }

    public int getSlot() {
        return this.slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public ItemStack getDisplay() {
        return this.display;
    }

    public GadgetItemType getType() {
        return this.type;
    }

    public GadgetItemRarity getRarity() {
        return this.rarity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDisplay(ItemStack display) {
        this.display = display;
    }

    public void setType(GadgetItemType type) {
        this.type = type;
    }

    public void setRarity(GadgetItemRarity rarity) {
        this.rarity = rarity;
    }
}
