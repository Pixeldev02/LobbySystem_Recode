package de.pixeldev02.lobbysystem.listeners;

import de.dytanic.cloudnet.api.CloudAPI;
import de.pixeldev02.lobbysystem.Lobbysystem;
import de.pixeldev02.lobbysystem.gadgets.GadgetItemRegister;
import de.pixeldev02.lobbysystem.invetorys.Settings;
import de.pixeldev02.lobbysystem.manager.SecretManager;
import de.pixeldev02.lobbysystem.gadgets.GadgetItem;
import de.pixeldev02.lobbysystem.gadgets.GadgetItemMain;
import de.pixeldev02.lobbysystem.gadgets.GadgetItemType;
import de.pixeldev02.lobbysystem.invetorys.Gadgets;
import de.pixeldev02.lobbysystem.invetorys.Navigator;
import de.pixeldev02.lobbysystem.invetorys.Playerhider;
import de.pixeldev02.lobbysystem.manager.BuildManager;
import de.pixeldev02.lobbysystem.manager.ItemManager;
import de.pixeldev02.lobbysystem.utils.Data;
import de.pixeldev02.lobbysystem.utils.Inventorys;
import de.pixeldev02.lobbysystem.utils.SettingsMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClickNavigator(final InventoryClickEvent e) throws FileNotFoundException, SQLException, ClassNotFoundException {
        final Player p = (Player) e.getWhoClicked();

        SecretManager.onClick(e);
        GadgetItemRegister.onClickHeads(e);

        // --------------------- Spieler ---------------------
        if(e.getInventory().equals(e.getInventory())){
            if(BuildManager.canBuild((Player) e.getWhoClicked())) {
                e.setCancelled(false);
            } else {
                e.setCancelled(true);
            }
        }
        // --------------------- Spieler ---------------------

        // --------------------- NAVIGATOR ---------------------
        if(e.getInventory().getName().equals(Inventorys.GUI.TELEPORTER.getName())) {
            e.setCancelled(true);

            if(e.getCurrentItem() != null) {
                if(e.isLeftClick()) {
                    if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§e§lSpawn")) {
                        Lobbysystem.getInstance().getTeleportManager().teleportwithboost(p, "spawn");

                    } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§7» §e§lBedwars §7«")) {
                        Lobbysystem.getInstance().getTeleportManager().teleportwithboost(p, "bedwars");

                    } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§7» §e§lEpicKnock §7«")) {
                        Lobbysystem.getInstance().getTeleportManager().teleportwithboost(p, "epicknock");

                    } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§7» §c§lTeam §7«")) {
                        Lobbysystem.getInstance().getTeleportManager().teleportwithboost(p, "team");

                    } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§7» §5§lChest Opening §7«")) {
                        Lobbysystem.getInstance().getTeleportManager().teleportwithboost(p, "chestopening");
                    }

                } else if(e.isRightClick()) {
                    if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§7» §e§lBedwars §7«")) {
                        Navigator.openQuick("BW8x1", p);

                    } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§7» §e§lEpicKnock §7«")) {
                        Navigator.openQuick("EpicKnock", p);

                    } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§7» §e§lTowerFight §7«")) {
                        Navigator.openQuick("TowerFight", p);
                    }
                }
            }
        }

        if(e.getInventory().getName().equalsIgnoreCase(Inventorys.GUI.SERVERLIST.getName() + "BW8x1")) {
            if(e.getCurrentItem() != null) {
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cZurück")) {
                    Navigator.openNavigatorInv(p);
                }
                if(e.getCurrentItem().getType() == Material.STAINED_CLAY && e.getCurrentItem().getItemMeta().getDisplayName().startsWith("§a§l")) {
                    String type = e.getCurrentItem().getItemMeta().getDisplayName().replace("§a§l", "");
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    DataOutputStream dos = new DataOutputStream(baos);

                    try {
                        dos.writeUTF("Connect");
                        dos.writeUTF(type);
                        p.sendPluginMessage(Lobbysystem.getInstance(), "BungeeCord", baos.toByteArray());
                        p.sendMessage(Data.prefix + "Du wirst jetzt auf den Server §a" + type + " §7verschoben.");
                        baos.close();
                        dos.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }

        if(e.getInventory().getName().equalsIgnoreCase(Inventorys.GUI.SERVERLIST.getName() + "EpicKnock")) {
            if(e.getCurrentItem() != null) {
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cZurück")) {
                    Navigator.openNavigatorInv(p);
                }
                if(e.getCurrentItem().getType() == Material.STAINED_CLAY && e.getCurrentItem().getItemMeta().getDisplayName().startsWith("§a§l")) {
                    Bukkit.broadcastMessage("asd");
                    String type = e.getCurrentItem().getItemMeta().getDisplayName().replace("§a§l", "");
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    DataOutputStream dos = new DataOutputStream(baos);

                    try {
                        dos.writeUTF("Connect");
                        dos.writeUTF(type);
                        p.sendPluginMessage(Lobbysystem.getInstance(), "BungeeCord", baos.toByteArray());
                        p.sendMessage(Data.prefix + "Du wirst jetzt auf den Server §a" + type + " §7verschoben.");
                        baos.close();
                        dos.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }

        if(e.getInventory().getName().equalsIgnoreCase(Inventorys.GUI.SERVERLIST.getName() + "TowerFight")) {
            if(e.getCurrentItem() != null) {
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cZurück")) {
                    Navigator.openNavigatorInv(p);
                }
                if(e.getCurrentItem().getType() == Material.STAINED_CLAY && e.getCurrentItem().getItemMeta().getDisplayName().startsWith("§a§l")) {
                    Bukkit.broadcastMessage("asd");
                    String type = e.getCurrentItem().getItemMeta().getDisplayName().replace("§a§l", "");
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    DataOutputStream dos = new DataOutputStream(baos);

                    try {
                        dos.writeUTF("Connect");
                        dos.writeUTF(type);
                        p.sendPluginMessage(Lobbysystem.getInstance(), "BungeeCord", baos.toByteArray());
                        p.sendMessage(Data.prefix + "Du wirst jetzt auf den Server §a" + type + " §7verschoben.");
                        baos.close();
                        dos.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }

        if(e.getInventory().getName().equalsIgnoreCase("§8» §6Lobbywechsler")) {
            if(e.getCurrentItem() != null) {
                String type = e.getCurrentItem().getItemMeta().getDisplayName().replace("§a", "").replace("§6", "");
                if(!type.equalsIgnoreCase(CloudAPI.getInstance().getServerId())) {
                    if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
                        return;
                    }

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    DataOutputStream dos = new DataOutputStream(baos);

                    try {
                        dos.writeUTF("Connect");
                        dos.writeUTF(type);
                        p.sendPluginMessage(Lobbysystem.getInstance(), "BungeeCord", baos.toByteArray());
                        p.sendMessage(Data.prefix + "Du wirst jetzt auf den Server §a" + type + " §7verschoben.");
                        baos.close();
                        dos.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    p.closeInventory();
                } else {
                    p.sendMessage(Data.prefix + "§7Du bist bereits auf §c" + CloudAPI.getInstance().getServerId());
                    p.closeInventory();
                }
            }
        }

        if(e.getInventory().getName().equalsIgnoreCase(Inventorys.GUI.SETTINGS.getName())) {
            if(e.getCurrentItem() != null) {
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aGadgets §8«")) {
                    Gadgets.openGadgets(p);
                }

                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cEinstellungen §8«")) {
                    Settings.openSettingsMenu(p);
                }
            }
        }

        if(e.getInventory().getName().equalsIgnoreCase(Inventorys.GUI.GADGETS.getName())) {
            if(e.getCurrentItem() != null) {
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eKöpfe")) {
                    Gadgets.openHeads(p);
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cSpecials")) {
                    Gadgets.openSpecials(p);
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aHaustiere")) {
                    Gadgets.openPets(p);
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §5Partikel")) {
                    Gadgets.openParticle(p);
                }
            }
        }

        if(e.getInventory().getName().equalsIgnoreCase(Inventorys.GUI.HIDER.getName())) {
            if(e.getCurrentItem() != null) {
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cAlle Spieler verstecken")) {
                    if(Playerhider.hideAllPlayers.contains(p)) {
                        p.sendMessage(Data.prefix + "§cEs sind bereits alle Spieler versteckt!");
                        p.closeInventory();

                    } else {
                        if(Playerhider.hideExceptTeam.contains(p)) {
                            Playerhider.hideExceptTeam.remove(p);
                        }
                        Playerhider.hideAllPlayers.add(p);
                        Bukkit.getOnlinePlayers().forEach((all) -> {
                            p.hidePlayer(all);
                        });
                        p.sendMessage(Data.prefix + "Du siehst nun keine Spieler mehr.");
                        p.closeInventory();
                    }

                } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §5Team/Freunde anzeigen")) {
                    if (Playerhider.hideExceptTeam.contains(p)) {
                        p.sendMessage(Data.prefix + "§cEs sind bereits alle Spieler bis auf das Team und Freunde versteckt!");
                        p.closeInventory();

                    } else {
                        if(Playerhider.hideAllPlayers.contains(p)) {
                            Playerhider.hideAllPlayers.remove(p);
                        }
                        Playerhider.hideExceptTeam.add(p);
                        Bukkit.getOnlinePlayers().forEach((all) -> {
                            if(!all.hasPermission("lobby.hidermid")) {
                                p.hidePlayer(all);
                            } else {
                                p.showPlayer(all);
                            }
                        });
                        p.sendMessage(Data.prefix + "Du siehst nun nurnoch Teammitglieder und Freunde.");
                        p.closeInventory();
                    }

                } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aAlle Spieler anzeigen")) {
                    if (!Playerhider.hideExceptTeam.contains(p) && !Playerhider.hideAllPlayers.contains(p)) {
                        p.sendMessage(Data.prefix + "§cDu siehst bereits alle Spieler!");
                        p.closeInventory();

                    } else {
                        if(Playerhider.hideExceptTeam.contains(p)) {
                            Playerhider.hideExceptTeam.remove(p);

                        } else if(Playerhider.hideAllPlayers.contains(p)) {
                            Playerhider.hideAllPlayers.remove(p);
                        }
                        Bukkit.getOnlinePlayers().forEach((all) -> {
                            p.showPlayer(all);
                        });
                        p.sendMessage(Data.prefix + "Du siehst nun wieder alle Spieler.");
                        p.closeInventory();
                    }
                }
            }
        }

        if(e.getInventory().getName().equals(Inventorys.GUI.SETTINGSMENU.getName())) {
            e.setCancelled(true);

            for(SettingsMenu type : SettingsMenu.values()) {
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(type.getDisplayname())) {
                    e.getClickedInventory().setItem(type.getBackfront() - 1, new ItemManager(Material.INK_SACK)
                            .setData((short) 8).setDisplayName(type.getDisplayname()).build());
                    e.getClickedInventory().setItem(type.getBackfront() + 1,
                            new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 14)
                                    .setDisplayName(type.getOffName()).build());
                }
            }
        }
    }

    @EventHandler
    public void onClose(final InventoryCloseEvent e) {
        final Player p = (Player) e.getPlayer();

        if(e.getInventory().getName().equals(Inventorys.GUI.TELEPORTER.getName())) {
            p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1L, 2L);
        }

        if(e.getInventory().getName().equalsIgnoreCase("§a§lLobbywechsler")) {
            p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1L, 2L);
        }
    }
    // --------------------- NAVIGATOR ---------------------
}
