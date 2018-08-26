package de.pixeldev02.lobbysystem.utils;

import de.pixeldev02.lobbysystem.Lobbysystem;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class TeleportAnimation {

    public HashMap<Player, Integer> taskID = new HashMap<>();

    @SuppressWarnings("deprecation")
    public void teleportwithboost(Player p, String location) {
        Bukkit.getScheduler().runTaskAsynchronously(Lobbysystem.getInstance(), new Runnable() {

            @Override
            public void run() {
                try {
                    p.closeInventory();
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Lobbysystem.getInstance(), () -> {

                        p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 2L, 2L);
                        p.setVelocity(p.getLocation().getDirection().multiply(0.2).setY(1.8D));
                        p.playEffect(p.getLocation(), Effect.FLAME, 1);
                        p.playEffect(p.getLocation(), Effect.FLAME, 1);
                        p.playEffect(p.getLocation(), Effect.FLAME, 1);
                        p.playEffect(p.getLocation(), Effect.FLAME, 1);
                        p.playEffect(p.getLocation(), Effect.FLAME, 1);
                        p.playEffect(p.getLocation(), Effect.FLAME, 1);
                        p.playEffect(p.getLocation(), Effect.FLAME, 1);
                        p.playEffect(p.getLocation(), Effect.FLAME, 1);
                        p.playEffect(p.getLocation(), Effect.FLAME, 1);
                        p.setHealthScale(1);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Lobbysystem.getInstance(), () -> {
                            try {
                                p.teleport(Lobbysystem.getInstance().getLocationManager().getLocation("Lobby", location));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            p.playSound(p.getLocation(), Sound.NOTE_PLING, 2L, 2L);
                            p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 1);
                            healthGenerat(p, 1);
                        }, 5);
                    }, 1);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void healthGenerat(Player p, int time) {
        p.setHealthScale(1);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (p.getHealthScale() > 9) {
                    cancel();
                } else {
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 2L, 2L);
                    p.setHealthScale(p.getHealthScale() + 1);
                }
            }
        }.runTaskTimer(Lobbysystem.getInstance(), 0, time);
    }
}
