package de.pixeldev02.lobbysystem.listeners;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.player.permission.PermissionGroup;
import de.pixeldev02.lobbysystem.utils.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        CloudPlayer player = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId());
        PermissionGroup permissionGroup = CloudAPI.getInstance().getPermissionGroup(player.getPermissionEntity().getGroups().iterator().next().getGroup());

        String format = permissionGroup.getPrefix() + " §8× " + permissionGroup.getSuffix() + p.getName() + " §8» §7" + e.getMessage();

        if(player.getServer().contains("SilentLobby")) {
            p.sendMessage(Data.prefix + "§cDer Chat ist in der SilentLobby deaktiviert!");
            e.getRecipients().remove(p);
            e.setCancelled(true);
        }

        e.setFormat(format);
    }
}
