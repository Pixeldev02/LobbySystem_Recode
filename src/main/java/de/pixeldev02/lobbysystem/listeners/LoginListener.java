package de.pixeldev02.lobbysystem.listeners;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.player.permission.PermissionGroup;
import de.pixeldev02.lobbysystem.Lobbysystem;
import de.pixeldev02.lobbysystem.manager.TitleManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

/*******************************************************************************
 *   Uhrheberrechtshinweis                                                     *
 *   Copyright © Christopher Arm 2018                                          *
 *   Erstellt: 06.09.2018 / 15:03                                             *
 *                                                                             *
 *   Alle Inhalte dieses Quelltextes sind Uhrheberrechtlich geschützt.         *
 *   Das Uhrheberrecht liegt, soweit nicht ausdrücklich gekenkzeichnet,        *    
 *   bei Christopher Arm. Alle Rechte vorbehalten.                             *
 *                                                                             *
 *   Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *   
 *   öffentlichen Zuganglichmachung oder andere Nutung                         *
 *   bedarf der ausdrücklichen, schriftlichen Zustimmung von Christopher Arm.  *
 ******************************************************************************/

public class LoginListener implements Listener {

    @EventHandler
    public void onLogin(final PlayerLoginEvent event) {
        final Player player = event.getPlayer();

        CloudPlayer p = CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId());
        PermissionGroup permissionGroup = CloudAPI.getInstance().getPermissionGroup(p.getPermissionEntity().getGroups().iterator().next().getGroup());

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                TitleManager.sendTitle(player, 20, 20, 20, "§c§lEpicJump", "§7Wilkommen " + permissionGroup.getSuffix() + player.getName());
            }
        }, 7);
    }
}
