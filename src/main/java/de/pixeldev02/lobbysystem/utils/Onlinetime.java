package de.pixeldev02.lobbysystem.utils;

import de.pixeldev02.lobbysystem.Lobbysystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Onlinetime {

    private Lobbysystem plugin;

    public Onlinetime(Lobbysystem plugin) {
        this.plugin = plugin;
        onTimer();
    }

    private void onTimer() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    int z = getOnlineTime(p.getName()) + 1;
                    Lobbysystem.getInstance().getMySQL().onUpdate("UPDATE LobbyPlayer SET Onlinezeit = " + z + " WHERE Name = '" + p.getName() + "'");

                }
            }

        }, 20, 60*20L);
    }

    public void createPlayer(Player p) {
        ResultSet rs = Lobbysystem.getInstance().getMySQL().onQuery("SELECT * FROM LobbyPlayer WHERE UUID='" + p.getUniqueId().toString() + "'");

        try {
            if(!rs.next()) {
                Lobbysystem.getInstance().getMySQL().onUpdate("INSERT INTO LobbyPlayer(UUID,Name,Onlinezeit) VALUES('" + p.getUniqueId().toString() + "', '" + p.getName() + "','" + 0 + "')");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Integer getOnlineTime(String player) {
        int z = 0;
        ResultSet rs = Lobbysystem.getInstance().getMySQL().onQuery("SELECT * FROM LobbyPlayer WHERE Name = '" + player + "'");

        try {
            if (rs.next()) {
                z = rs.getInt("Onlinezeit");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return z;
    }

    public String getPlayerOnlineTime(Player p) {
        int zeit = getOnlineTime(p.getName());
        int stunden = (int) zeit / 60;
        int minuten = zeit - (stunden * 60);

        return String.format("%02d:%02d",stunden,minuten);
    }
}
