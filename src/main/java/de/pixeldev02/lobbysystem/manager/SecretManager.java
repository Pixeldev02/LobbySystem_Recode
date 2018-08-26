package de.pixeldev02.lobbysystem.manager;

import de.pixeldev02.lobbysystem.Lobbysystem;
import de.pixeldev02.lobbysystem.mysql.PlayerSecretSQL;
import de.pixeldev02.lobbysystem.mysql.SecretSQL;
import de.pixeldev02.lobbysystem.secrets.Secret;
import de.pixeldev02.lobbysystem.secrets.SecretRarity;
import de.pixeldev02.lobbysystem.secrets.SecretRegister;
import de.pixeldev02.lobbysystem.utils.Data;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SecretManager {

    public static String name = "";
    public static SecretRarity rarity = SecretRarity.LEICHT;
    public static boolean inEdit = false;


    public static void onClick(final InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();

        if(e.getInventory().getName().equalsIgnoreCase("§bRaritäts Auswahl")) {
            if(e.getCurrentItem() != null) {
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aLeicht")) {
                    rarity = SecretRarity.LEICHT;
                    final ItemStack secret1 = SkullManager.getHeadLore("http://textures.minecraft.net/texture/f310d3fd429778f3e9721df865e566e54617d5906a893ca0e7afd77171fd90", "§a§lSecret §8- §e" + name, Arrays.asList("", "§bRarität: §aLeicht"));
                    BuildManager.toggle(p);
                    p.getInventory().setItem(0, secret1);
                    p.closeInventory();
                    p.setGameMode(GameMode.CREATIVE);
                } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bMittel")) {
                    rarity = SecretRarity.MITTEL;
                    final ItemStack secret1 = SkullManager.getHeadLore("http://textures.minecraft.net/texture/f310d3fd429778f3e9721df865e566e54617d5906a893ca0e7afd77171fd90", "§a§lSecret §8- §e" + name, Arrays.asList("", "§bRarität: §bMittel"));
                    BuildManager.toggle(p);
                    p.getInventory().setItem(0, secret1);
                    p.closeInventory();
                    p.setGameMode(GameMode.CREATIVE);
                } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cSchwer")) {
                    rarity = SecretRarity.SCHWER;
                    final ItemStack secret1 = SkullManager.getHeadLore("http://textures.minecraft.net/texture/f310d3fd429778f3e9721df865e566e54617d5906a893ca0e7afd77171fd90", "§a§lSecret §8- §e" + name, Arrays.asList("", "§bRarität: §cSchwer"));
                    BuildManager.toggle(p);
                    p.getInventory().setItem(0, secret1);
                    p.closeInventory();
                    p.setGameMode(GameMode.CREATIVE);
                }
            }
        }
    }

    public static void onInteract(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();

        if(e.getItem() != null) {
            if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lSecret §8- §7<§eName§7>")) {
                new AnvilGUI(Lobbysystem.getInstance(), p, "Name", (player, reply) -> {
                    if(reply != null) {
                        name = reply;
                        final ItemStack secret1 = SkullManager.getHead("http://textures.minecraft.net/texture/f310d3fd429778f3e9721df865e566e54617d5906a893ca0e7afd77171fd90", "§a§lSecret §8- §e" + name);
                        p.getInventory().setItem(0, secret1);
                        return  null;
                    }
                    return "Bitte gebe einen Namen an";
                });

            } else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lSecret §8- §e" + name) && !inEdit) {
                final Inventory inv = Bukkit.createInventory(null, InventoryType.BREWING, "§bRaritäts Auswahl");

                final ItemStack leicht = SkullManager.getHead("http://textures.minecraft.net/texture/85484f4b6367b95bb16288398f1c8dd6c61de988f3a8356d4c3ae73ea38a42", "§8» §aLeicht");
                final ItemStack mittel = SkullManager.getHead("http://textures.minecraft.net/texture/d1137b9bf435c4b6b88faeaf2e41d8fd04e1d9663d6f63ed3c68cc16fc724","§8» §bMittel");
                final ItemStack schwer = SkullManager.getHead("http://textures.minecraft.net/texture/533a5bfc8a2a3a152d646a5bea694a425ab79db694b214f156c37c7183aa", "§8» §cSchwer");

                inv.setItem(0, leicht);
                inv.setItem(1, mittel);
                inv.setItem(2, schwer);

                inEdit = true;

                p.openInventory(inv);
            }
        }
    }

    public static void onInteractArmorStand(final PlayerInteractAtEntityEvent e) {
        final Player p = e.getPlayer();

        if(e.getRightClicked() instanceof ArmorStand) {
            if(e.getRightClicked().getCustomName().equalsIgnoreCase("§6§lSecret Rolf")) {
                final InventoryCreator inv = new InventoryCreator("§aDeine Secrets", 6*9);

                final ItemStack placeholder = new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 14).setDisplayName(" ").build();
                HashMap<Integer, Secret> playerSecrets = new HashMap<>();

                for(int i = 0; i < SecretManager.getPlayerSecrets(p).size(); i++) {
                    Integer id = SecretManager.getPlayerSecrets(p).get(i);
                    playerSecrets.put(id, new Secret(id, Lobbysystem.getInstance().getLocationManager().getLocationString(SecretSQL.getLocation(id)), SecretSQL.getName(id), SecretRegister.getRarityByID(id)));

                    for(Secret secret : playerSecrets.values()) {
                        final ItemStack secret1 = SkullManager.getHeadLore("http://textures.minecraft.net/texture/f310d3fd429778f3e9721df865e566e54617d5906a893ca0e7afd77171fd90", "§a§lSecret §8- §c"
                                + secret.getName(), Arrays.asList("§7§m------------------------------", "", "§7ID: §c" + secret.getId(), "§7Schwierigkeit: §c" + SecretRegister.getRarity(secret.getRarity())));

                        inv.setItem(i, secret1);

                    }
                }

                inv.openInv(p);
            }
        }
    }

    public static void onPlace(final BlockPlaceEvent e) {
        if(e.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lSecret §8- §e" + name)) {
            e.setCancelled(false);
            new Secret(SecretRegister.setID(), e.getBlock().getLocation(), name, rarity);
            e.getPlayer().sendMessage(Data.prefix + "Du hast ein neues Secret erstellt");
            SecretSQL.createSecret(SecretRegister.setID(), Lobbysystem.getInstance().getLocationManager().getStringLocation(e.getBlock().getLocation()), name, SecretRegister.getRarity(rarity));
            inEdit = false;
            e.getPlayer().getInventory().clear();
            Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
                @Override
                public void run() {
                    BuildManager.toggle(e.getPlayer());
                }
            }, 1*20);

        } else {
            e.setCancelled(true);
        }
    }

    public static List<Integer> getPlayerSecrets(final Player p) {
        ArrayList<Integer> secrets = PlayerSecretSQL.getSecretsFromPlayers(p.getUniqueId());
        Integer[] secretArray = secrets.stream().toArray(Integer[]::new);
        Arrays.sort(secretArray);

        ArrayList<Integer> returnList = new ArrayList<>();

        /*Integer[] returnInt = Arrays.(0, 0);
        Bukkit.broadcastMessage("§b" + secrets.size());
        for(int i = 0; i < secrets.size(); i++) {
            returnInt = secretArray[i];
            Bukkit.broadcastMessage("newINT");
        }*/

        for(int i = 0; i < secretArray.length; i++) {
            returnList.add(secretArray[i]);
        }
        return returnList;
    }
}
