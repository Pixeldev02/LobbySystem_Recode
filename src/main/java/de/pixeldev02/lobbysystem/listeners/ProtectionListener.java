package de.pixeldev02.lobbysystem.listeners;

import de.pixeldev02.lobbysystem.manager.SecretManager;
import de.pixeldev02.lobbysystem.manager.BuildManager;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class ProtectionListener implements Listener  {


   @EventHandler
    public void onDamage(final EntityDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onFood(final FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

   @EventHandler
    public void onSpawn(final CreatureSpawnEvent e) {
        if(e.getEntity() instanceof ArmorStand) {
            e.setCancelled(false);
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onPickUp(final PlayerPickupItemEvent e) {
       if(BuildManager.canBuild(e.getPlayer())) {
           e.setCancelled(false);
       } else {
           e.setCancelled(true);
       }
    }

    @EventHandler
    public void onDrop(final PlayerDropItemEvent e) {
        if(BuildManager.canBuild(e.getPlayer())) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(final BlockPlaceEvent e) {
        SecretManager.onPlace(e);
        if(BuildManager.canBuild(e.getPlayer())) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(final BlockBreakEvent e) {
        if(BuildManager.canBuild(e.getPlayer())) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void onCraft(final CraftItemEvent e) {
        e.setCancelled(true);
    }


    @EventHandler
    public void onInteract(final PlayerInteractEvent e) {
        if(BuildManager.builders.contains(e.getPlayer()) || e.getItem().getType() == Material.FISHING_ROD) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onExplode(final EntityExplodeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onThunder(ThunderChangeEvent e) {
        Boolean thunder = e.toThunderState();
        if (thunder) {
            e.setCancelled(true);
            e.getWorld().setThundering(false);
        }
    }

    @EventHandler
    public void onRain(WeatherChangeEvent e) {
        Boolean rain = e.toWeatherState();
        if (rain) {
            e.setCancelled(true);
            e.getWorld().setStorm(false);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerFishingEvent(PlayerFishEvent event) {
        Player p = event.getPlayer();
        Fish h = event.getHook();
        if(event.getCaught() != null && event.getCaught() instanceof Player) {
            ItemStack stack = event.getPlayer().getItemInHand();

            double dbl = -100.0;
            if(dbl < 0.0) {
                event.getPlayer().setItemInHand(null);

            }
            stack.setDurability((short) dbl);

            Player p2 = (Player) event.getCaught();
            p.playSound(p.getLocation(), Sound.ITEM_BREAK, 20L, 20L);
            Vector from = event.getPlayer().getLocation().toVector();
            World world = p.getWorld();
            Location loc = p.getLocation();
            Block behind = loc.getBlock();
            int direction = (int) loc.getYaw();
            if(direction < 0) {
                direction += 360;
                direction = (direction + 45) / 90;
            } else {
                direction = (direction + 45) / 90;
            }
            switch(direction) {
                case 1:
                    behind = world.getBlockAt(behind.getX() + 1, behind.getY(), behind.getZ());
                    break;
                case 2:
                    behind = world.getBlockAt(behind.getX(), behind.getY(), behind.getZ() + 1);
                    break;
                case 3:
                    behind = world.getBlockAt(behind.getX() - 1, behind.getY(), behind.getZ());
                    break;
                case 4:
                    behind = world.getBlockAt(behind.getX(), behind.getY(), behind.getZ() - 1);
                    break;
                case 0:
                    behind = world.getBlockAt(behind.getX(), behind.getY(), behind.getZ() - 1);
                    break;
                default:
                    break;
            }
            Location toloc = p.getLocation();
            if(behind != null) {
                toloc = behind.getLocation().add(0, 1, 0);
            }
            Vector to = toloc.toVector();
            Vector vector = to.subtract(from);
            vector.multiply(0.5);
            p2.setVelocity(vector);
            p2.teleport(p);

        } else if (((event.getState().equals(PlayerFishEvent.State.IN_GROUND)) || (event.getState().equals(PlayerFishEvent.State.FAILED_ATTEMPT))) && (Bukkit.getWorld(event.getPlayer().getWorld().getName()).getBlockAt(h.getLocation().getBlockX(), h.getLocation().getBlockY() - 1, h.getLocation().getBlockZ()).getType() != Material.AIR) && (Bukkit.getWorld(event.getPlayer().getWorld().getName()).getBlockAt(h.getLocation().getBlockX(), h.getLocation().getBlockY() - 1, h.getLocation().getBlockZ()).getType() != Material.STATIONARY_WATER)) {
            Location lc = p.getLocation();
            Location to1 = event.getHook().getLocation();
            lc.setY(lc.getY() + 0.8D);
            p.teleport(lc);
            double g = -0.08D;
            double t;
            double d = t = to1.distance(lc);
            double v_x = (1.0D + 0.07D * t) * (to1.getX() - lc.getX()) / t;
            double v_y = (1.0D + 0.03D * t) * (to1.getY() - lc.getY()) / t - -0.04D * t;
            double v_z = (1.0D + 0.07D * t) * (to1.getZ() - lc.getZ()) / t;
            Vector v = p.getVelocity();
            v.setX(v_x);
            v.setY(v_y);
            v.setZ(v_z);
            p.setVelocity(v);
            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
        }
    }
}
