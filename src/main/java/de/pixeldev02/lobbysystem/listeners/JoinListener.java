package de.pixeldev02.lobbysystem.listeners;

import de.pixeldev02.lobbysystem.Lobbysystem;
import de.pixeldev02.lobbysystem.invetorys.Playerhider;
import de.pixeldev02.lobbysystem.manager.BuildManager;
import de.pixeldev02.lobbysystem.mysql.PlayerSecretSQL;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class JoinListener implements Listener {

    private Lobbysystem plugin;

    public JoinListener(Lobbysystem plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent e) throws SQLException, ClassNotFoundException {

        final Player p = e.getPlayer();

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                try {
                    Location spawnloc = Lobbysystem.getInstance().getLocationManager().getLocation("Lobby", "spawn");
                    p.teleport(spawnloc);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        }, 2);

        p.setMaxHealth(10);
        p.setHealth(10);
        p.setFoodLevel(20);
        p.setGameMode(GameMode.ADVENTURE);
        e.setJoinMessage(null);

        Lobbysystem.getInstance().getOnlinetimeManager().createPlayer(p);
        //Lobbysystem.getInstance().getRedisManager().setRankData(p);
        Lobbysystem.getInstance().getGadgetSQL().registerPlayer(p.getUniqueId());
        //CoinsAPI.createPlayer(p);
        PlayerSecretSQL.registerPlayer(p.getUniqueId());

        Bukkit.getOnlinePlayers().forEach((player) -> {
            Lobbysystem.getInstance().getPlayerScoreboard().setPlayerBoard(player);

            if(Playerhider.hideAllPlayers.contains(player)) {
                player.hidePlayer(p);
            }

            if(Playerhider.hideExceptTeam.contains(player)) {
                if(!p.hasPermission("lobby.hidermid")) {
                    player.hidePlayer(p);
                }
            }
        });

        if (p.hasPermission("lobby.joinitems")) {
            BuildManager.setJoinItems(p, BuildManager.Mode.TEAM);
            BuildManager.setJoinItems(p, BuildManager.Mode.TEAM);
        } else {
            BuildManager.setJoinItems(p, BuildManager.Mode.NORMAL);
            BuildManager.setJoinItems(p, BuildManager.Mode.NORMAL);
        }

        if(Lobbysystem.getInstance().getGadgetSQL().getHeads(p.getUniqueId()) >= 1) {
            Integer ID = Lobbysystem.getInstance().getGadgetSQL().getHeads(p.getUniqueId());

        }
    }
}