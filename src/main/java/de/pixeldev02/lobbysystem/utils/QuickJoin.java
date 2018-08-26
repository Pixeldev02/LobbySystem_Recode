package de.pixeldev02.lobbysystem.utils;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import de.pixeldev02.lobbysystem.Lobbysystem;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;

public class QuickJoin {

    public void quickJoin(final Player player, final String serverGroup){
        Collection<ServerInfo> servers = CloudAPI.getInstance().getServers(serverGroup);
        ServerInfo serverInfo = null;
        for(ServerInfo gameServer : servers){
            if(!gameServer.isIngame() && gameServer.getOnlineCount() < gameServer.getMaxPlayers()){
                if(serverInfo != null){
                    if(gameServer.getOnlineCount() > serverInfo.getOnlineCount()){
                        serverInfo = gameServer;
                    }
                }else{
                    serverInfo = gameServer;
                }
            }
        }
        if(serverInfo != null){
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);

            String server = serverInfo.getServiceId().toString().split("#")[0];
            CloudPlayer p = CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId());
            String playerServer = p.getServer();

            if(playerServer.equalsIgnoreCase(server)) {
                player.sendMessage(Data.prefix + "§cDu befindest dich bereits auf einer SilentLobby.");

            } else {
                try {
                    out.writeUTF("Connect");
                    out.writeUTF(server);
                    player.sendMessage(Data.prefix + "Du wirst jetzt auf §a" + server + " §7verschoben.");
                } catch (IOException ex){
                    ex.printStackTrace();
                }
                player.sendPluginMessage(Lobbysystem.getInstance(), "BungeeCord", b.toByteArray());
            }
        } else {
            player.sendMessage(Data.prefix + "§cEs ist momentan keine SilentLobby online.");
        }
    }
}
