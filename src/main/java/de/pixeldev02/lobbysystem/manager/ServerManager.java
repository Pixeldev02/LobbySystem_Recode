package de.pixeldev02.lobbysystem.manager;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import de.pixeldev02.lobbysystem.Lobbysystem;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.LinkedList;

public class ServerManager {

    private Lobbysystem plugin;
    public static LinkedList<String> servername = new LinkedList<>();


    public ServerManager(Lobbysystem plugin) {
        this.plugin = plugin;
    }

    public String getPlayerServerName(Player p) {
        CloudPlayer player = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId());
        String servername = player.getServer();

        if(player.getServer() != null) {
            if (!player.getServer().contains("null")) {
                Integer ID = CloudAPI.getInstance().getServerInfo(player.getServer()).getServiceId().getId();
                if (servername.contains("SilentLobby")) {
                    return "Silent-" + ID;
                }
                if (servername.contains("PremiumLobby")) {
                    return "Premium-" + ID;
                }
                if (servername.contains("Lobby") || servername.contains("SilentLobby") || servername.contains("PremiumLobby")) {
                    return servername;
                }
            }
        }
        return "null";
    }

    public int getEpicMineCount() {
        int onlinecount = 0;

        for(ServerInfo epicmine : CloudAPI.getInstance().getServers("EpicMine")) {
            servername.add(epicmine.getServiceId().getServerId());
        }

        Collections.sort(servername);

        for(String st : servername) {
            ServerInfo info = null;

            for(ServerInfo list : CloudAPI.getInstance().getServers("EpicMine")) {
                if(list.getServiceId().getServerId().equalsIgnoreCase(st)) {
                    info = list;
                }
            }

            if(info != null && st.startsWith("EpicMine")) {
                return info.getOnlineCount();
            }
        }
        return 0;
    }

    public int getBedwarsCount() {
        int onlinecount = 0;

        for(ServerInfo bw8x1 : CloudAPI.getInstance().getServers("BW8x1")) {
            servername.add(bw8x1.getServiceId().getServerId());
        }

        for(ServerInfo bw8x2 : CloudAPI.getInstance().getServers("BW8x2")) {
            servername.add(bw8x2.getServiceId().getServerId());
        }

        for(ServerInfo bw4x2 : CloudAPI.getInstance().getServers("BW4x2")) {
            servername.add(bw4x2.getServiceId().getServerId());
        }

        for(ServerInfo bw4x3 : CloudAPI.getInstance().getServers("BW4x3")) {
            servername.add(bw4x3.getServiceId().getServerId());
        }

        Collections.sort(servername);

        for(String st : servername) {
            ServerInfo info = null;

            for(ServerInfo list : CloudAPI.getInstance().getServers("BW8x1")) {
                if(list.getServiceId().getServerId().equalsIgnoreCase(st)) {
                    info = list;
                }
            }

            for(ServerInfo list : CloudAPI.getInstance().getServers("BW8x2")) {
                if(list.getServiceId().getServerId().equalsIgnoreCase(st)) {
                    info = list;
                }
            }

            for(ServerInfo list : CloudAPI.getInstance().getServers("BW4x2")) {
                if(list.getServiceId().getServerId().equalsIgnoreCase(st)) {
                    info = list;
                }
            }

            for(ServerInfo list : CloudAPI.getInstance().getServers("BW4x3")) {
                if(list.getServiceId().getServerId().equalsIgnoreCase(st)) {
                    info = list;
                }
            }

            if(info != null && st.startsWith("BW")) {
                return info.getOnlineCount();
            }
        }
        return 0;
    }

    public int getEpicKnockCount() {
        int onlinecount = 0;

        for(ServerInfo epicknock : CloudAPI.getInstance().getServers("EpicKnock")) {
            servername.add(epicknock.getServiceId().getServerId());
        }

        Collections.sort(servername);

        for(String st : servername) {
            ServerInfo info = null;

            for(ServerInfo list : CloudAPI.getInstance().getServers("EpicKnock")) {
                if(list.getServiceId().getServerId().equalsIgnoreCase(st)) {
                    info = list;
                }
            }

            if(info != null && st.startsWith("EpicKnock")) {
                return info.getOnlineCount();
            }
        }
        return 0;
    }
}
