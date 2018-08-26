package de.pixeldev02.lobbysystem.manager;

import de.pixeldev02.lobbysystem.utils.Data;
import de.pixeldev02.lobbysystem.utils.SkullCacher;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class BuildManager {

    public static ArrayList<Player> builders = new ArrayList<>();

    public static void toggle(Player player){
        if(canBuild(player)){
            removePlayer(player);
        }else{
            addPlayer(player);
        }

    }

    public static boolean canBuild(Player player){
        return builders.contains(player);
    }

    public static void addPlayer(Player player){
        builders.add(player);
        player.getInventory().clear();
        player.setGameMode(GameMode.CREATIVE);
        player.sendMessage(Data.prefix + "§7Buildmodus §aaktiviert");
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 0, 5F);
    }

    public static void removePlayer(Player player){

        builders.remove(player);
        player.getInventory().clear();

        if (player.hasPermission("lobbysystem.joinitems")) {
            setJoinItems(player, Mode.TEAM);
        } else {
            setJoinItems(player, Mode.NORMAL);
        }

        player.setGameMode(GameMode.ADVENTURE);
        player.sendMessage(Data.prefix + "§7Buildmodus §cdeaktiviert");
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 0, 5F);
    }

    public enum Mode {
        NORMAL, TEAM
    }

    public static void setJoinItems(Player player, Mode mode) {

        final Inventory inv = player.getInventory();

        final ItemStack Navigator = new ItemManager(Material.COMPASS).setDisplayName("§6Navigator §8× §7Rechtsklick").build();
        final ItemStack PlayerHider = new ItemManager(Material.BLAZE_ROD).setDisplayName("§6Spieler Verstecker §8× §7Rechtsklick").build();
        final ItemStack LobbyChanger = new ItemManager(Material.NETHER_STAR).setDisplayName("§6LobbyWechsler §8× §7Rechtsklick").build();
        final ItemStack Gadget = new ItemManager(Material.CLAY_BALL).setDisplayName("§6Gadget §8× §cDeaktiviert").build();
        final ItemStack Settings = new ItemManager(SkullCacher.getHead(player)).setDisplayName("§6Einstellungen §8× §7Rechtsklick").build();

        final ItemStack Nick = new ItemManager(Material.NAME_TAG).setDisplayName("§6Nick §8× §cDeaktiviert").build();
        final ItemStack SilentLobby = new ItemManager(Material.TNT).setDisplayName("§6Silent-Lobby §8× §7Rechtsklick").build();

        switch (mode) {

            case NORMAL:

                inv.clear();

                inv.setItem(0, LobbyChanger);
                inv.setItem(1, PlayerHider);
                inv.setItem(4, Navigator);
                inv.setItem(7, Gadget);
                inv.setItem(8, Settings);
                break;

            case TEAM:

                inv.clear();

                inv.setItem(0, LobbyChanger);
                inv.setItem(1, PlayerHider);
                inv.setItem(3, Nick);
                inv.setItem(4, Navigator);
                inv.setItem(5, SilentLobby);
                inv.setItem(7, Gadget);
                inv.setItem(8, Settings);
                break;

            default:
                break;
        }
    }
}
