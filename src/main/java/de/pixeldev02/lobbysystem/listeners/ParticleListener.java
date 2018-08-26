package de.pixeldev02.lobbysystem.listeners;

import de.pixeldev02.lobbysystem.Lobbysystem;
import de.pixeldev02.lobbysystem.utils.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class ParticleListener implements Listener {

    @EventHandler
    public void onClickSilent(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();

        if(e.getItem() == null) return;

        if(!e.getAction().equals(Action.LEFT_CLICK_AIR)) return;

        if(e.getItem().getType().equals(Material.TNT)) {
            new BukkitRunnable(){
                double t = Math.PI/4;
                Location loc = p.getLocation();
                public void run(){
                    Bukkit.broadcastMessage("a");
                    t = t + 0.1*Math.PI;
                    for (double theta = 0; theta <= 2*Math.PI; theta = theta + Math.PI/32){
                        double x = t*cos(theta);
                        double y = 2*Math.exp(-0.1*t) * sin(t) + 1.5;
                        double z = t*sin(theta);
                        loc.add(x,y,z);
                        ParticleEffect.FIREWORKS_SPARK.display(0,0,0,0,1,loc);
                        loc.subtract(x,y,z);

                        theta = theta + Math.PI/64;

                        x = t*cos(theta);
                        y = 2*Math.exp(-0.1*t) * sin(t) + 1.5;
                        z = t*sin(theta);
                        loc.add(x,y,z);
                        ParticleEffect.CRIT_MAGIC.display(0,0,0,0,1,loc);
                        loc.subtract(x,y,z);
                    }
                    if (t > 20){
                        this.cancel();
                    }
                }

            }.runTaskTimer(Lobbysystem.getInstance(), 0, 1);
        }

    }
}
