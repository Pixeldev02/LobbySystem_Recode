package de.pixeldev02.lobbysystem.listeners;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class HotbarListener implements Listener {

    @EventHandler
    public void onSwitch(final PlayerItemHeldEvent e) {
        final Player p = e.getPlayer();
        p.playSound(p.getLocation(), Sound.WOOD_CLICK, 10, 10);
    }
}
