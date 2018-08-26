package de.pixeldev02.lobbysystem.manager;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.player.permission.PermissionGroup;
import de.pixeldev02.lobbysystem.Lobbysystem;
import org.bukkit.entity.Player;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RedisManager {

    private Jedis jedis;

    private Lobbysystem plugin;

    private String host;

    private int port;

    public RedisManager(Lobbysystem plugin) {
        this.plugin = plugin;
        connect();
    }

    public void connect() {
        this.jedis = new Jedis("localhost", 6379);
        this.jedis.select(1);
        this.jedis.connect();
        System.out.println("Redis >> Verbunden");
    }

    //<editor-fold defaultstate="collapsed" desc="setRankData">
    public void setRankData(final Player p) {
        CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId());
        PermissionGroup permissionGroup = CloudAPI.getInstance().getPermissionGroup(cloudPlayer.getPermissionEntity().getGroups().iterator().next().getGroup());

        this.jedis.append("Name: " + p.getName(), permissionGroup.getName());

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getRankData">
    public String getRankData(final Player p) {
        return this.jedis.get("Name: " + p.getName());
    }
    //</editor-fold>
}
