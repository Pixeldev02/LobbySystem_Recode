package de.pixeldev02.lobbysystem.commands;

import de.pixeldev02.lobbysystem.invetorys.Playerhider;
import de.pixeldev02.lobbysystem.manager.BuildManager;
import de.pixeldev02.lobbysystem.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if(!(cs instanceof Player))
            return true;

        Player p = (Player) cs;

        if(args.length == 0) {
            if(p.hasPermission("lobby.build")) {
                BuildManager.toggle(p);
            }
        }

        if(args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);

            if(target != null) {
                BuildManager.toggle(target);
                if(BuildManager.builders.contains(p)) {
                    p.sendMessage(Data.prefix + "Du hast den Buildmodus von §e " + target.getName() + " §c§ldeaktiviert");
                } else {
                    p.sendMessage(Data.prefix + "Du hast den Buildmodus von §e " + target.getName() + " §a§kaktiviert");
                }

            } else {
                p.sendMessage(Data.prefix + "§cDieser Spieler ist nicht online!");
            }
        }
        return false;
    }
}
