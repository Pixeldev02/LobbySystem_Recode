package de.pixeldev02.lobbysystem.invetorys;

import de.pixeldev02.lobbysystem.manager.InventoryCreator;
import de.pixeldev02.lobbysystem.manager.ItemManager;
import de.pixeldev02.lobbysystem.manager.SkullManager;
import de.pixeldev02.lobbysystem.utils.Inventorys;
import de.pixeldev02.lobbysystem.utils.SkullCacher;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Settings {

    public static void openSettings(final Player p) {
        InventoryCreator inv = new InventoryCreator(Inventorys.GUI.SETTINGS.getName(), Inventorys.GUI.SETTINGS.getSize());

        final ItemStack placeholder = new ItemManager(Material.STAINED_GLASS_PANE).setData((byte) 14).setDisplayName(" ").build();
        final ItemStack gadgets = new ItemManager(Material.ENDER_CHEST).setDisplayName("§8» §aGadgets §8«").build();
        final ItemStack freunde = new ItemManager(SkullCacher.getHead(Bukkit.getOfflinePlayer(p.getUniqueId()))).setDisplayName("§8» §eFreunde §8«").build();

        final ItemStack Settings = SkullManager.getHead("http://textures.minecraft.net/texture/5f4c21d17ad636387ea3c736bff6ade897317e1374cd5d9b1c15e6e8953432", "§8» §cEinstellungen §8«");
        ItemMeta SettingsMeta = Settings.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        SettingsMeta.setLore(lore);
        Settings.setItemMeta(SettingsMeta);

        for(int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, placeholder);
        }

        inv.setItem(11, Settings);
        inv.setItem(13, gadgets);
        inv.setItem(15, freunde);
        p.playSound(p.getLocation(), Sound.CHEST_OPEN, 1L, 2L);

        inv.openInv(p);
    }

    public static void openSettingsMenu(final Player p) {
        InventoryCreator inv = new InventoryCreator(Inventorys.GUI.SETTINGSMENU.getName(), Inventorys.GUI.SETTINGSMENU.getSize());

        final ItemStack placeholder = new ItemManager(Material.STAINED_GLASS_PANE).setData((byte) 14).setDisplayName(" ").build();

        final ItemStack scoreboard = new ItemManager(Material.ITEM_FRAME).setDisplayName("§8» §aScoreboard").addLoreAll(Arrays.asList("§7§m-------------------------", "", "§7Schaltet das Scoreboard §aan§7/§caus", "")).build();
        final ItemStack hotbarsound = SkullManager.getHeadLore("http://textures.minecraft.net/texture/ff7becf1718e9a6096ee5f9cb7abeb6cfd9488c44a3116407c92ec33ad7d8521", "§8» §aHotbarsound", Arrays.asList("§7§m-------------------------", "", "§7Schaltet den Sound beim switchen", "der Slots §aan§7/§caus", ""));
        final ItemStack jointeleport = new ItemManager(Material.ENDER_PEARL).setDisplayName("§8» §aJointeleport").addLoreAll(Arrays.asList("§7§m-------------------------", "", "§7Schaltet das teleportieren zum Spawn", "beim Joinen §aan§7/§caus", "")).build();
        final ItemStack chat = new ItemManager(Material.BOOK).setDisplayName("§8» §aChaterwähnung").addLoreAll(Arrays.asList("§7§m-------------------------", "", "§7Schaltet den Sound wenn dein Name", "im Chat auftaucht §aan§7/§caus", "")).build();


    }
}
