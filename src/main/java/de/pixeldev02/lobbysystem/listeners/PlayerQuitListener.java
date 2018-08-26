package de.pixeldev02.lobbysystem.listeners;

import de.pixeldev02.lobbysystem.gadgets.GadgetItemRegister;
import de.pixeldev02.lobbysystem.invetorys.Playerhider;
import de.pixeldev02.lobbysystem.manager.BuildManager;
import de.pixeldev02.lobbysystem.manager.SetManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(final PlayerQuitEvent e) throws SQLException, ClassNotFoundException {
        final Player p = e.getPlayer();

        e.setQuitMessage(null);

        if(BuildManager.canBuild(p)) {
            BuildManager.removePlayer(p);
        }

        if(Playerhider.hideAllPlayers.contains(p)) {
            Playerhider.hideAllPlayers.remove(p);
        }

        if(Playerhider.hideExceptTeam.contains(p)) {
            Playerhider.hideExceptTeam.remove(p);
        }

        if(SetManager.setters.contains(p)) {
            SetManager.setters.remove(p);
        }

        if(GadgetItemRegister.headsActive.containsKey(p.getUniqueId().toString())) {
        }
    }
}
