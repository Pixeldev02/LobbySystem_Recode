package de.pixeldev02.lobbysystem.manager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryCreator {
    private Inventory inv;

    public InventoryCreator(String displayname, int size)
    {
        this.inv = Bukkit.createInventory(null, size, displayname);
    }

    public InventoryCreator(String displayname, InventoryType inventoryType)
    {
        this.inv = Bukkit.createInventory(null, inventoryType, displayname);
    }

    public void setItem(int slot, ItemStack stack)
    {
        this.inv.setItem(slot, stack);
    }

    public void addItem(ItemStack stack)
    {
        this.inv.addItem(new ItemStack[] { stack });
    }

    public void fillInv(int shortid)
    {
        for (int i = 0; i < this.inv.getSize(); i++)
        {
            ItemStack stack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)shortid);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName("§r");
            stack.setItemMeta(meta);
            this.inv.setItem(i, stack);
        }
    }

    public Inventory getInventory()
    {
        return this.inv;
    }

    public void openInv(Player p)
    {
        p.openInventory(this.inv);
    }

    public Integer getSize() {
        return this.inv.getSize();
    }

}
