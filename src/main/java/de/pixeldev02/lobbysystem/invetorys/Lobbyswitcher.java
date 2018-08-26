package de.pixeldev02.lobbysystem.invetorys;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import de.pixeldev02.lobbysystem.manager.InventoryCreator;
import de.pixeldev02.lobbysystem.manager.SkullManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Lobbyswitcher {

    static int slot = 10;
    static int slotpremium = 28;

    public static void openSwitch(final Player p) {
        InventoryCreator inventory = new InventoryCreator("§8» §6Lobbywechsler", 5*9);
        inventory.fillInv(14);

        LinkedList<String> servername = new LinkedList<>();

        for(ServerInfo list: CloudAPI.getInstance().getServers("Lobby")) {
            servername.add(list.getServiceId().getServerId());
        }

        for(ServerInfo list: CloudAPI.getInstance().getServers("PremiumLobby")) {
            servername.add(list.getServiceId().getServerId());
        }

        Collections.sort(servername);

        inventory.setItem(11, new ItemStack(Material.AIR));
        inventory.setItem(12, new ItemStack(Material.AIR));
        inventory.setItem(13, new ItemStack(Material.AIR));
        inventory.setItem(14, new ItemStack(Material.AIR));
        inventory.setItem(15, new ItemStack(Material.AIR));
        inventory.setItem(16, new ItemStack(Material.AIR));

        inventory.setItem(28, new ItemStack(Material.AIR));
        inventory.setItem(29, new ItemStack(Material.AIR));
        inventory.setItem(30, new ItemStack(Material.AIR));
        inventory.setItem(31, new ItemStack(Material.AIR));
        inventory.setItem(32, new ItemStack(Material.AIR));
        inventory.setItem(33, new ItemStack(Material.AIR));
        inventory.setItem(34, new ItemStack(Material.AIR));
        slot = 10;
        slotpremium = 28;

        for(String st : servername) {


            ServerInfo info = null;

            for (ServerInfo list : CloudAPI.getInstance().getServers("Lobby")) {
                if(list.getServiceId().getServerId().equalsIgnoreCase(st)) {
                    info = list;
                }
            }

            for (ServerInfo list : CloudAPI.getInstance().getServers("PremiumLobby")) {
                if(list.getServiceId().getServerId().equalsIgnoreCase(st)) {
                    info = list;
                }
            }

            if(info != null && st.startsWith("Lobby")) {

                if(!st.contains("10")) {
                    setServerItem(inventory, st, slot, info.getMaxPlayers(), info.getOnlineCount());
                    slot++;
                }


            }

            if(info != null && st.startsWith("PremiumLobby")) {

                if(!st.contains("10")) {
                    setServerItem(inventory, st, slotpremium, info.getMaxPlayers(), info.getOnlineCount());
                    slotpremium++;
                }
            }
        }
        inventory.openInv(p);
        p.playSound(p.getLocation(), Sound.CHEST_OPEN, 1L, 2L);
    }

    private static void setServerItem(InventoryCreator inventory, String servername, int slot,int max, int online) {
        if(servername.startsWith("Lobby") && !servername.equalsIgnoreCase(CloudAPI.getInstance().getServerId())) {
            ItemStack item = SkullManager.getHead("http://textures.minecraft.net/texture/ec516bd9e7c5edcc3494d545d9a47ef3549587b3b7b68d5435c0ff226e9f", "§a" +servername);
            ItemMeta itemm = item.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("§8» §aOnline §8× §7" + online + "§8/§7" + max);
            lore.add("");
            itemm.setLore(lore);
            item.setItemMeta(itemm);
            inventory.setItem(slot, item);
        } else if(servername.startsWith("Lobby") && servername.equalsIgnoreCase(CloudAPI.getInstance().getServerId())) {
            ItemStack item = SkullManager.getHead("http://textures.minecraft.net/texture/34de4d95ba4a46db7e566b345f7894d1d258f893eb92c780b3da775eedf91", "§a" +servername);
            ItemMeta itemm = item.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("§8» §aOnline §8× §7" + online + "§8/§7" + max);
            lore.add("§8» §cDu befindest dich hier");
            lore.add("");
            itemm.setLore(lore);
            item.setItemMeta(itemm);
            inventory.setItem(slot, item);
        } else if(servername.startsWith("Premium") && servername.equalsIgnoreCase(CloudAPI.getInstance().getServerId())) {
            ItemStack item = SkullManager.getHead("http://textures.minecraft.net/texture/34de4d95ba4a46db7e566b345f7894d1d258f893eb92c780b3da775eedf91", "§6"+servername);
            ItemMeta itemm = item.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("§8» §aOnline §8× §7" + online + "§8/§7" + max);
            lore.add("§8» §cDu befindest dich hier");
            lore.add("");
            itemm.setLore(lore);
            item.setItemMeta(itemm);
            inventory.setItem(slot, item);
        } else  if(servername.startsWith("Premium") && !servername.equalsIgnoreCase(CloudAPI.getInstance().getServerId())) {
            ItemStack item = SkullManager.getHead("http://textures.minecraft.net/texture/7d719e785f2b267d84b9a1d40c63a347a12fc15ff1e54b164b2be53f549d535", "§6"+servername);
            ItemMeta itemm = item.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("§8» §aOnline §8× §7" + online + "§8/§7" + max);
            lore.add("");
            itemm.setLore(lore);
            item.setItemMeta(itemm);
            inventory.setItem(slot, item);
        }
    }
}
