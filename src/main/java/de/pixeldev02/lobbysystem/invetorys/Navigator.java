package de.pixeldev02.lobbysystem.invetorys;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.server.ServerState;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import de.pixeldev02.lobbysystem.Lobbysystem;
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

import java.util.*;

public class Navigator  {

    public static void openNavigatorInv(Player p) {
        InventoryCreator inventoryCreator = new InventoryCreator(Inventorys.GUI.TELEPORTER.getName(), Inventorys.GUI.TELEPORTER.getSize());
        inventoryCreator.fillInv((short) 14);

        final ItemStack Spawn = new ItemManager(Material.MAGMA_CREAM).setDisplayName("§e§lSpawn").build();
        final ItemStack Bedwars = new ItemManager(Material.BED).setAmount(Lobbysystem.getInstance().getServerManager().getBedwarsCount()).setDisplayName("§7» §e§lBedwars §7«").addLoreLine("§7§m------------------------").addLoreLine("").addLoreLine("§7» §a§lLinkslick §8× §eTeleportieren").addLoreLine("§7» §a§lRechtsklick §8× §eServerGUI").addLoreLine("").addLoreLine("§7§m------------------------").build();
        final ItemStack EpicKnock = new ItemManager(Material.STICK).setAmount(Lobbysystem.getInstance().getServerManager().getEpicKnockCount()).setDisplayName("§7» §e§lEpicKnock §7«").addLoreLine("§7§m------------------------").addLoreLine("").addLoreLine("§7» §a§lLinkslick §8× §eTeleportieren").addLoreLine("§7» §a§lRechtsklick §8× §eServerGUI").addLoreLine("").addLoreLine("§7§m------------------------").build();
        final ItemStack CoomingSoon = new ItemManager(Material.BARRIER).setDisplayName("§7» §e§lKommt bald §7«").build();
        final ItemStack Team = new ItemManager(SkullCacher.getHead(Bukkit.getOfflinePlayer(UUID.fromString("e349c0dc-ec01-4441-98a5-62e7af93482c")))).setDisplayName("§7» §c§lTeam §7«").build();
        final ItemStack ChestOpening = new ItemManager(Material.ENDER_CHEST).setDisplayName("§7» §5§lChest Opening §7«").build();

        inventoryCreator.setItem(12, EpicKnock);
        inventoryCreator.setItem(14, Bedwars);
        inventoryCreator.setItem(19, CoomingSoon);
        inventoryCreator.setItem(22, Spawn);
        inventoryCreator.setItem(25, CoomingSoon);
        inventoryCreator.setItem(30, Team);
        inventoryCreator.setItem(32, ChestOpening);
        p.playSound(p.getLocation(), Sound.CHEST_OPEN, 1L, 2L);

        inventoryCreator.openInv(p);
    }

    public static void openQuick(String group, Player p) {
        LinkedList<String> servername = new LinkedList<>();
        InventoryCreator inv;

        for (ServerInfo api : CloudAPI.getInstance().getServers(group)) {
            if (api.getServerState() != ServerState.INGAME) {
                if (api.getServerState() != ServerState.OFFLINE) {
                    servername.add(api.getServiceId().getServerId());
                }
            }
        }

        inv = new InventoryCreator(Inventorys.GUI.SERVERLIST.getName() + group, 5 * 9);
        inv.fillInv(15);

        for (int i = 0; i < 9; ++i) {
            inv.setItem(i, new ItemManager(Material.STAINED_GLASS_PANE, 1).setDisplayName("§c").setData((short) 7).build());
        }

        inv.setItem(5 * 9 - 1, SkullManager.getHead(
                "http://textures.minecraft.net/texture/e02fa3b2dcb11c6639cc9b9146bea54fbc6646d855bdde1dc6435124a11215d",
                "§8» §cZurück"));
        inv.setItem(5 * 9 - 1 - 4, new ItemManager(Material.PAPER, 1).setDisplayName("§8» §7QuickJoin").build());
        inv.setItem(4, new ItemManager(Material.NETHER_STAR, 1).setDisplayName("§8» §e" + group).build());
        inv.openInv(p);

        if (servername.size() == 0) {
            for (int i = 20; i < 25; i++) {
                ItemStack maintance = new ItemManager(Material.STAINED_GLASS_PANE, 1).setData((short) 14)
                        .setDisplayName("§cOFFLINE").build();
                inv.setItem(i, maintance);
            }
            for (int i = 29; i < 34; i++) {
                ItemStack maintance = new ItemManager(Material.STAINED_GLASS_PANE, 1).setData((short) 14)
                        .setDisplayName("§cOFFLINE").build();
                inv.setItem(i, maintance);
            }
            return;
        }

        if (CloudAPI.getInstance().getServerGroup(group).isMaintenance()) {
            for (int i = 20; i < 25; i++) {
                ItemStack maintance = new ItemManager(Material.STAINED_GLASS_PANE, 1).setData((short) 14)
                        .setDisplayName("§4Wartungen").build();
                inv.setItem(i, maintance);
            }
            for (int i = 29; i < 34; i++) {
                ItemStack maintance = new ItemManager(Material.STAINED_GLASS_PANE, 1).setData((short) 14)
                        .setDisplayName("§4Wartungen").build();
                inv.setItem(i, maintance);
            }
            return;
        }

        Collections.sort(servername);
        final Integer slot[] = {20};

        int i = 0;

        for (String st : servername) {
            if (i == 10) {
                break;
            }
            i++;

        ServerInfo info = null;
            for (ServerInfo list : CloudAPI.getInstance().getServers(group)) {
                if (list.getServerState() != ServerState.INGAME) {
                    if(list.getServerState() != ServerState.OFFLINE) {
                        if (list.getServiceId().getServerId().equalsIgnoreCase(st)) {
                            info = list;
                        }
                    }
                }
            }

            if (info != null) {
                if (slot[0] == 25) {
                    slot[0] = 29;
                } else {
                    setServerItem(inv, st, slot[0], info.isIngame(), info.getMaxPlayers(), info.getOnlineCount(), info.getMotd(), info.getServerState());
                    slot[0]++;
                }
            }
        }
    }


    private static void setServerItem(InventoryCreator inv, String servername, int slot, boolean ingame, int max, int online, String map, ServerState serverstate) {
        Bukkit.getScheduler().runTaskAsynchronously(Lobbysystem.getInstance(), new Runnable() {

            @Override
            public void run() {
                List<String> lore = new ArrayList<>();
                lore.add("§7§m------------------------");
                lore.add("");
                lore.add("§8» §aOnline §8× §7" + online + "§8/§7" + max);
                lore.add("§8» §eMap §8× §7" + map);
                lore.add("");
                lore.add("§7§m------------------------");
                ItemStack on = new ItemManager(Material.STAINED_CLAY, online).setData((short) 13)
                        .setDisplayName("§a§l" + servername).addLoreAll(lore).build();

                inv.setItem(slot, on);
            }
        });
    }
}
