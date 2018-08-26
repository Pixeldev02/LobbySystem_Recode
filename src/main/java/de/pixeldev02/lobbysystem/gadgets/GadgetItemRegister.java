package de.pixeldev02.lobbysystem.gadgets;

import de.pixeldev02.lobbysystem.Lobbysystem;
import de.pixeldev02.lobbysystem.manager.ItemManager;
import de.pixeldev02.lobbysystem.manager.SkullManager;
import de.pixeldev02.lobbysystem.mysql.GadgetSQL;
import de.pixeldev02.lobbysystem.utils.Inventorys;
import de.pixeldev02.lobbysystem.utils.NameFetcher;
import de.pixeldev02.lobbysystem.utils.SkullCacher;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GadgetItemRegister {

    public static HashMap<String, GadgetHeads> headsActive = new HashMap<>();

    public static void registerItems() {
        setPets();
        setPartikel();
        setSpecials();
        setHeads();
    }

    private static void setPets() {
        new GadgetItem(1, new ItemManager(Material.MONSTER_EGG).setData((short)90).setDisplayName("§8» §cSchwein §8«").build(), GadgetItemType.PET, GadgetItemRarity.COMMON, 10);
        new GadgetItem(2, new ItemManager(Material.MONSTER_EGG).setData((short)91).setDisplayName("§8» §rSchaf §8«").build(), GadgetItemType.PET, GadgetItemRarity.COMMON, 11);
        new GadgetItem(3, new ItemManager(Material.MONSTER_EGG).setData((short)50).setDisplayName("§8» §2Creeper §8«").build(), GadgetItemType.PET, GadgetItemRarity.RARE, 12);
        new GadgetItem(4, new ItemManager(Material.MONSTER_EGG).setData((short)95).setDisplayName("§8» §eHund §8«").build(), GadgetItemType.PET, GadgetItemRarity.RARE, 13);
        new GadgetItem(5, new ItemManager(Material.MONSTER_EGG).setData((short)98).setDisplayName("§8» §eKatze §8«").build(), GadgetItemType.PET, GadgetItemRarity.EPIC, 14);
        new GadgetItem(6, new ItemManager(Material.MONSTER_EGG).setData((short)58).setDisplayName("§8» §5Enderman §8«").build(), GadgetItemType.PET, GadgetItemRarity.LEGENDARY, 15);
        new GadgetItem(7, new ItemManager(Material.MONSTER_EGG).setData((short)62).setDisplayName("§8» §5Enderman §8«").build(), GadgetItemType.PET, GadgetItemRarity.LEGENDARY, 16);

    }

    private static void setPartikel() {
        new GadgetItem(8, new ItemManager(Material.INK_SACK).setData((short)1).setDisplayName("§8» §cHerzen §8«").build(), GadgetItemType.PARTIKEL, GadgetItemRarity.COMMON, 10);
        new GadgetItem(9, new ItemManager(Material.NOTE_BLOCK).setDisplayName("§8» §5Noten §8«").build(), GadgetItemType.PARTIKEL, GadgetItemRarity.COMMON, 11);
        new GadgetItem(10, new ItemManager(Material.SNOW_BALL).setDisplayName("§8» §rSchnee §8«").build(), GadgetItemType.PARTIKEL, GadgetItemRarity.RARE, 12);
        new GadgetItem(11, new ItemManager(Material.BLAZE_POWDER).setDisplayName("§8» §eFlammen §8«").build(), GadgetItemType.PARTIKEL, GadgetItemRarity.RARE, 13);
        new GadgetItem(12, new ItemManager(Material.SLIME_BALL).setDisplayName("§8» §aSchleim §8«").build(), GadgetItemType.PARTIKEL, GadgetItemRarity.EPIC, 14);
        new GadgetItem(13, new ItemManager(Material.FIREWORK_CHARGE).setDisplayName("§8» §7Rauch §8«").build(), GadgetItemType.PARTIKEL, GadgetItemRarity.LEGENDARY, 15);
        new GadgetItem(14, new ItemManager(Material.WATER_BUCKET).setDisplayName("§8» §bRegen §8«").build(), GadgetItemType.PARTIKEL, GadgetItemRarity.LEGENDARY, 16);
    }

    private static void setSpecials() {
        new GadgetItem(15, new ItemManager(Material.FISHING_ROD).setDisplayName("§8» §aEnterhaken §8«").build(), GadgetItemType.SPECIALS, GadgetItemRarity.COMMON, 10);
        new GadgetItem(16, new ItemManager(Material.ENDER_PEARL).setDisplayName("§8» §5Enderperle §8«").build(), GadgetItemType.SPECIALS, GadgetItemRarity.COMMON, 11);
        new GadgetItem(17, new ItemManager(Material.STICK).setDisplayName("§8» §cWerfer §8«").build(), GadgetItemType.SPECIALS, GadgetItemRarity.RARE, 12);
        new GadgetItem(18, new ItemManager(Material.EGG).setDisplayName("§8» §eFarbbombe §8«").build(), GadgetItemType.SPECIALS, GadgetItemRarity.RARE, 13);

    }

    private static void setHeads() {
        new GadgetItem(22, new ItemManager(Material.SKULL_ITEM, (short) 3).setSkullOwner(NameFetcher.getName("1588abbb-e45b-49e6-9e43-8b83c5d5f812")).setDisplayName("§8» §bUngespielt §8«").build(), GadgetItemType.HEAD, GadgetItemRarity.COMMON, 10 );

       /* new GadgetItem(23, GadgetHeads.YOUTUBERHEADS.PALUTEN.getItem(), GadgetItemType.HEAD, GadgetItemRarity.COMMON, 11 );

        new GadgetItem(24, GadgetHeads.YOUTUBERHEADS.GERMANLETSPLAY.getItem(), GadgetItemType.HEAD, GadgetItemRarity.COMMON, 12 );

        new GadgetItem(25, GadgetHeads.YOUTUBERHEADS.GOMMEHD.getItem(), GadgetItemType.HEAD, GadgetItemRarity.COMMON, 13 );

        new GadgetItem(26, GadgetHeads.YOUTUBERHEADS.MAUDADO.getItem(), GadgetItemType.HEAD, GadgetItemRarity.COMMON, 14 );

        new GadgetItem(27, GadgetHeads.YOUTUBERHEADS.REWINSIDE.getItem(), GadgetItemType.HEAD, GadgetItemRarity.COMMON, 15 );

        new GadgetItem(28, GadgetHeads.YOUTUBERHEADS.ZINUS.getItem(), GadgetItemType.HEAD, GadgetItemRarity.COMMON, 16 );*/
    }


    public static ItemStack getItem(Player p, GadgetItem i) throws ClassNotFoundException, SQLException {
        ItemStack re = new ItemStack(Material.REDSTONE_BLOCK);
        if (i != null) {
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("§7Seltenheit: " + GadgetItemRarity.getName(i.getRarity()));
            lore.add("§8» §cNicht im Besitz");
            if (i.getType() == GadgetItemType.PARTIKEL) {
                re = new ItemManager(i.getDisplay()).setDisplayName(i.getDisplay().getItemMeta().getDisplayName()).addLoreAll(lore).build();
            }
            if (i.getType() == GadgetItemType.HEAD) {
                re = new ItemManager(i.getDisplay()).setDisplayName(i.getDisplay().getItemMeta().getDisplayName()).addLoreAll(lore).build();
            }
            if (i.getType() == GadgetItemType.PET) {
                re = new ItemManager(i.getDisplay()).setDisplayName(i.getDisplay().getItemMeta().getDisplayName()).addLoreAll(lore).build();
            }
            if (i.getType() == GadgetItemType.SPECIALS) {
                re = new ItemManager(i.getDisplay()).setDisplayName(i.getDisplay().getItemMeta().getDisplayName()).addLoreAll(lore).build();
            }
            if (Lobbysystem.getInstance().getGadgetSQL().hasItem(i.getId(), p.getUniqueId())) {
                ItemStack reBe = i.getDisplay().clone();
                ItemMeta meta = reBe.getItemMeta();
                List<String> lore2 = new ArrayList<>();
                lore2.add("");
                lore2.add("§7Seltenheit: " + GadgetItemRarity.getName(i.getRarity()));
                lore2.add("§8» §aIm Besitz");
                meta.setLore(lore2);
                reBe.setItemMeta(meta);
                re = reBe;
            }
        }
        return re;
    }

    public static void getInventoryByType(Inventory inv, GadgetItemType type, Player p) throws ClassNotFoundException, SQLException {

        for (GadgetItem gi : GadgetItemMain.currentItems.values()) {
            if (gi.getType() == type) {
                try {
                    inv.setItem(gi.getSlot(), getItem(p, gi));
                }
                catch (ClassNotFoundException | SQLException ex2) {
                    Exception ex = null;
                    ex.printStackTrace();
                }
            }
        }
        p.playSound(p.getLocation(), Sound.CHEST_OPEN, 1L, 2L);
    }

    private static void setGadgetHead(final Player p, GadgetHeads head, Integer ID) throws SQLException, ClassNotFoundException {
        if(Lobbysystem.getInstance().getGadgetSQL().hasItem(ID, p.getUniqueId())) {
            if (headsActive.containsKey(p.getUniqueId().toString())) {
                headsActive.remove(p.getUniqueId().toString());
                p.getInventory().setHelmet(null);
                p.getInventory().setHelmet(head.getItem());
                p.getOpenInventory().close();
                headsActive.put(p.getUniqueId().toString(), head);
                p.sendMessage("§5Gadgets §8× §7Du hast den Kopf von §a" + head.getName() + " §7aufgesetzt");

            } else {
                headsActive.put(p.getUniqueId().toString(), head);
                p.getInventory().setHelmet(null);
                p.getInventory().setHelmet(head.getItem());
                p.getOpenInventory().close();
                p.sendMessage("§5Gadgets §8× §7Du hast den Kopf von §a" + head.getName() + " §7aufgesetzt");
            }
        }
    }

    public static void removeHeadGadget(final Player p) {
        if(headsActive.containsKey(p.getUniqueId().toString())) {
            headsActive.remove(p.getUniqueId().toString());
            p.getInventory().setHelmet(null);
            p.getOpenInventory().close();
            p.sendMessage("§5Gadgets §8× §7Du hast deinen aktuellen Kopf §centfernt.");
        } else {
            p.sendMessage("§5Gadgets §8× §7Du hast aktuell keinen Kopf aufgesetzt.");
        }
    }

    public static void onClickHeads(final InventoryClickEvent e) throws SQLException, ClassNotFoundException {
        final Player p = (Player) e.getWhoClicked();

        if(e.getInventory().getName().equalsIgnoreCase(Inventorys.GUI.GADGETS.getName())) {
            if(e.getCurrentItem() != null) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bUngespielt §8«")) {
                    setGadgetHead(p, new GadgetHeads("§8» §bUngespielt §8«", "1588abbb-e45b-49e6-9e43-8b83c5d5f812", GadgetItemRarity.COMMON, 22), 22);

                } /*else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(GadgetHeads.YOUTUBERHEADS.PALUTEN.getName())) {
                    setGadgetHead(p, GadgetHeads.YOUTUBERHEADS.PALUTEN, 23);

                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(GadgetHeads.YOUTUBERHEADS.GERMANLETSPLAY.getName())) {
                    setGadgetHead(p, GadgetHeads.YOUTUBERHEADS.GERMANLETSPLAY, 24);

                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(GadgetHeads.YOUTUBERHEADS.GOMMEHD.getName())) {
                    setGadgetHead(p, GadgetHeads.YOUTUBERHEADS.GOMMEHD, 25);

                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(GadgetHeads.YOUTUBERHEADS.MAUDADO.getName())) {
                    setGadgetHead(p, GadgetHeads.YOUTUBERHEADS.MAUDADO, 26);

                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(GadgetHeads.YOUTUBERHEADS.REWINSIDE.getName())) {
                    setGadgetHead(p, GadgetHeads.YOUTUBERHEADS.REWINSIDE, 27);

                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(GadgetHeads.YOUTUBERHEADS.ZINUS.getName())) {
                    setGadgetHead(p, GadgetHeads.YOUTUBERHEADS.ZINUS, 28);

                }*/ else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §4Köpfe entfernen")) {
                    removeHeadGadget(p);
                }
            }
        }
    }
}
