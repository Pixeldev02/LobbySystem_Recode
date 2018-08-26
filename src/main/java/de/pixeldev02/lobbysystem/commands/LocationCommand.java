package de.pixeldev02.lobbysystem.commands;

import de.pixeldev02.lobbysystem.Lobbysystem;
import de.pixeldev02.lobbysystem.manager.ItemManager;
import de.pixeldev02.lobbysystem.manager.SetManager;
import de.pixeldev02.lobbysystem.manager.SkullManager;
import de.pixeldev02.lobbysystem.manager.TitleManager;
import de.pixeldev02.lobbysystem.utils.Data;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.FileNotFoundException;

public class LocationCommand implements CommandExecutor {

    private Lobbysystem plugin;

    public LocationCommand(Lobbysystem plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if(!(cs instanceof Player))
            return true;

        Player p = (Player) cs;
        if(p.hasPermission("lobbysystem.set")) {
            if(args.length == 0) {
                SetManager.toggle(p);
            } else {
                p.sendMessage(Data.prefix + "§cBenutze /set");
            }
            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("Spawn")) {
                    try {
                        Lobbysystem.getInstance().getLocationManager().saveLocation(p.getLocation(), "spawn");
                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 1L, 2L);
                        TitleManager.sendTitle(p, 10, 20, 10, "§a§l✔", "§7§lSpawn gesetzt");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                } else if(args[0].equalsIgnoreCase("Bedwars")) {
                        try {
                            Lobbysystem.getInstance().getLocationManager().saveLocation(p.getLocation(), "bedwars");
                            p.playSound(p.getLocation(), Sound.NOTE_PLING, 1L, 2L);
                            TitleManager.sendTitle(p, 10, 20, 10, "§a§l✔", "§7§lBedwars Spawn gesetzt");
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                } else if(args[0].equalsIgnoreCase("EpicKnock")) {
                    try {
                        Lobbysystem.getInstance().getLocationManager().saveLocation(p.getLocation(), "epicknock");
                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 1L, 2L);
                        TitleManager.sendTitle(p, 10, 20, 10, "§a§l✔", "§7§lEpicKnock Spawn gesetzt");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                } else if(args[0].equalsIgnoreCase("Team")) {
                    try {
                        Lobbysystem.getInstance().getLocationManager().saveLocation(p.getLocation(), "team");
                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 1L, 2L);
                        TitleManager.sendTitle(p, 10, 20, 10, "§a§l✔", "§7§lTeam Spawn gesetzt");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if(args[0].equalsIgnoreCase("ChestOpening")) {
                    try {
                        Lobbysystem.getInstance().getLocationManager().saveLocation(p.getLocation(), "chestopening");
                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 1L, 2L);
                        TitleManager.sendTitle(p, 10, 20, 10, "§a§l✔", "§7§lChestOpening Spawn gesetzt");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    p.sendMessage("§7§m------------------------------------------");
                    p.sendMessage("§a/set Spawn §8× §7Setzt die Spawn Location");
                    p.sendMessage("§a/set Bedwars §8× §7Setzt die Bedwars Spawn Location");
                    p.sendMessage("§a/set EpicKnock §8× §7Setzt die EpicKnock Spawn Location");
                    p.sendMessage("§a/set Team §8× §7Setzt die Team Spawn Location");
                    p.sendMessage("§a/set ChestOpening §8× §7Setzt die ChestOpening Spawn Location");
                    p.sendMessage("§7§m------------------------------------------");
                }
            }
        }

        return false;
    }
}
