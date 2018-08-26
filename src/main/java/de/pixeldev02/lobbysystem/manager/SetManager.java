package de.pixeldev02.lobbysystem.manager;

import de.pixeldev02.lobbysystem.Lobbysystem;
import de.pixeldev02.lobbysystem.utils.Data;
import de.pixeldev02.lobbysystem.utils.SkullCacher;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.UUID;

public class SetManager {

    public static ArrayList<Player> setters = new ArrayList<>();


    public static void toggle(Player player){
        if(canSet(player)){
            removePlayer(player);
        }else{
            addPlayer(player);
        }

    }

    public static boolean canSet(Player player){
        return setters.contains(player);
    }

    public static void addPlayer(Player player){
        setters.add(player);
        player.getInventory().clear();
        player.setGameMode(GameMode.CREATIVE);
        player.sendMessage(Data.prefix + "§7Locationmodus §aaktiviert");
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 0, 5F);
        openMainInventory(player);
    }

    public static void removePlayer(Player player){

        setters.remove(player);
        player.getInventory().clear();

        if (player.hasPermission("lobbysystem.joinitems")) {
            BuildManager.setJoinItems(player, BuildManager.Mode.TEAM);
        } else {
            BuildManager.setJoinItems(player, BuildManager.Mode.NORMAL);
        }

        player.setGameMode(GameMode.ADVENTURE);
        player.sendMessage(Data.prefix + "§7Locationmodus §cdeaktiviert");
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 0, 5F);
    }

    public static void onInteract(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();

        if(e.getItem() != null) {
            if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aLobbyspawns")) {
                openLocationHotbar(p);

            } else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §6Secrets")) {
                p.getInventory().clear();
                openSecretsInventory(p);

            } else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §7Zurück")) {
                p.getInventory().clear();
                openMainInventory(p);
            }

            if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lSpawn")) {
                try {
                    Lobbysystem.getInstance().getLocationManager().saveLocation(p.getLocation(), "spawn");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 1L, 2L);
                    p.sendMessage(Data.prefix + "Du hast den Spawn für den §aSpawn §7erfolgreich gesetzt.");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

            } else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lBedwars")) {
                try {
                    Lobbysystem.getInstance().getLocationManager().saveLocation(p.getLocation(), "bedwars");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 1L, 2L);
                    p.sendMessage(Data.prefix + "Du hast den Spawn für §aBedwars §7erfolgreich gesetzt.");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

            } else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lEpicKnock")) {
                try {
                    Lobbysystem.getInstance().getLocationManager().saveLocation(p.getLocation(), "epicknock");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 1L, 2L);
                    p.sendMessage(Data.prefix + "Du hast den Spawn für §aEpicKnock §7erfolgreich gesetzt.");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

            } else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lTeam")) {
                try {
                    Lobbysystem.getInstance().getLocationManager().saveLocation(p.getLocation(), "team");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 1L, 2L);
                    p.sendMessage(Data.prefix + "Du hast den Spawn für das §aTeam §7erfolgreich gesetzt.");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

            } else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lChest-Opening")) {
                try {
                    Lobbysystem.getInstance().getLocationManager().saveLocation(p.getLocation(), "chestopening");
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 1L, 2L);
                    p.sendMessage(Data.prefix + "Du hast den Spawn für das §aChest-Opening §7erfolgreich gesetzt.");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private static void openLocationHotbar(final Player p) {
        final ItemStack Spawn = new ItemManager(Material.MAGMA_CREAM).setDisplayName("§c§lSpawn").build();
        final ItemStack Bedwars = new ItemManager(Material.BED).setDisplayName("§c§lBedwars").build();
        final ItemStack EpicKnock = new ItemManager(Material.STICK).setDisplayName("§c§lEpicKnock").build();
        final ItemStack Team = new ItemManager(SkullCacher.getHead(Bukkit.getOfflinePlayer(UUID.fromString("e2214300-91f2-46c6-9a75-bf7d5d2a0321")))).setDisplayName("§c§lTeam").build();
        final ItemStack ChestOpening = new ItemManager(Material.ENDER_CHEST).setDisplayName("§c§lChest-Opening").build();
        final ItemStack back = SkullManager.getHead("http://textures.minecraft.net/texture/e02fa3b2dcb11c6639cc9b9146bea54fbc6646d855bdde1dc6435124a11215d", "§8» §7Zurück");

        p.getInventory().clear();
        p.getInventory().setItem(2, Spawn);
        p.getInventory().setItem(3, Bedwars);
        p.getInventory().setItem(4, EpicKnock);
        p.getInventory().setItem(5, Team);
        p.getInventory().setItem(6, ChestOpening);

        p.getInventory().setItem(8, back);
    }

    private static void openMainInventory(final Player p) {
        final ItemStack Spawns = new ItemManager(Material.ENDER_PEARL).setDisplayName("§8» §aLobbyspawns").build();
        final ItemStack Secrets = SkullManager.getHead("http://textures.minecraft.net/texture/f310d3fd429778f3e9721df865e566e54617d5906a893ca0e7afd77171fd90", "§8» §6Secrets");

        p.getInventory().setItem(2, Spawns);
        p.getInventory().setItem(6, Secrets);
    }

    private static void openSecretsInventory(final Player p) {
        final ItemStack Secret = SkullManager.getHead("http://textures.minecraft.net/texture/f310d3fd429778f3e9721df865e566e54617d5906a893ca0e7afd77171fd90", "§a§lSecret §8- §7<§eName§7>");
        final ItemStack back = SkullManager.getHead("http://textures.minecraft.net/texture/e02fa3b2dcb11c6639cc9b9146bea54fbc6646d855bdde1dc6435124a11215d", "§8» §7Zurück");

        p.getInventory().setItem(4, Secret);
        p.getInventory().setItem(8, back);
    }
}
