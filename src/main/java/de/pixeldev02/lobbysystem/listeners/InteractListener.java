package de.pixeldev02.lobbysystem.listeners;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.player.permission.PermissionGroup;
import de.pixeldev02.lobbysystem.Lobbysystem;
import de.pixeldev02.lobbysystem.manager.SecretManager;
import de.pixeldev02.lobbysystem.invetorys.Lobbyswitcher;
import de.pixeldev02.lobbysystem.invetorys.Navigator;
import de.pixeldev02.lobbysystem.invetorys.Playerhider;
import de.pixeldev02.lobbysystem.invetorys.Settings;
import de.pixeldev02.lobbysystem.manager.SetManager;
import de.pixeldev02.lobbysystem.secrets.SecretRegister;
import de.pixeldev02.lobbysystem.utils.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.sql.SQLException;

public class InteractListener implements Listener {

    @EventHandler
    public void onInteract(final PlayerInteractEvent e) throws SQLException, ClassNotFoundException {
        final Player p = e.getPlayer();

        SecretManager.onInteract(e);
        SecretRegister.onInteractSecret(e);
        SetManager.onInteract(e);

        if(e.getItem() != null) {
            if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                CloudPlayer player = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId());
                PermissionGroup permissionGroup = CloudAPI.getInstance().getPermissionGroup(player.getPermissionEntity().getGroups().iterator().next().getGroup());

                if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Navigator §8× §7Rechtsklick")) {
                    Navigator.openNavigatorInv(p);

                } else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Silent-Lobby §8× §7Rechtsklick")) {
                    if(permissionGroup.getJoinPower() < 50) {
                        p.sendMessage(Data.prefix + "Du hast nicht die nötigen Rechte dazu.");
                    } else {
                        Lobbysystem.getInstance().getQuickJoinManager().quickJoin(p, "SilentLobby");
                    }

                } else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6LobbyWechsler §8× §7Rechtsklick")) {
                    Lobbyswitcher.openSwitch(p);

                } else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Einstellungen §8× §7Rechtsklick")) {
                    Settings.openSettings(p);

                } else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Spieler Verstecker §8× §7Rechtsklick")) {
                    Playerhider.openPlayerHider(p);

                }
            }
        }
    }

    @EventHandler
    public void onInteractArmoStand(final PlayerInteractAtEntityEvent e) {
        SecretManager.onInteractArmorStand(e);
    }
}
