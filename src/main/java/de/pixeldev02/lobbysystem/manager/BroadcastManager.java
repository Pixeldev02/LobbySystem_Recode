package de.pixeldev02.lobbysystem.manager;

import de.pixeldev02.lobbysystem.Lobbysystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BroadcastManager {

    Integer time = 40;

    public BroadcastManager() {
        startBroadCast();
    }

    public void startBroadCast() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(Player all : Bukkit.getOnlinePlayers()) {
                    if(time >= 30 && time <= 40) {
                        ActionbarManager.sendActionbar(all, "§a■ §7■ ■ ■ §8| §7Der Status der Bewerbungsphase ist: §a§lGeöffnet");
                    }

                    if(time >= 20 && time <= 30) {
                        ActionbarManager.sendActionbar(all, "§7■ §a■ §7■ ■ §8| §7Unser Forum: §eEpicJumpForum.net/Forum         §f");
                    }

                    if(time >= 10 && time <= 20) {
                        ActionbarManager.sendActionbar(all, "§7■ ■ §a■ §7■ §8| §7Bedwars Update: §bSpawner Upgrade §7+ §bKits");
                    }

                    if(time >= 0 && time <= 10) {
                        ActionbarManager.sendActionbar(all, "§7■ ■ ■ §a■ §8| §7Wir befinden uns momentan in der §cBETA §7Phase");
                    }

                    if(time == 0) {
                        time = 40;
                    }
                }

                time --;
            }
        }, 20, 20);
    }
}
