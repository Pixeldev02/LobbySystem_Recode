package de.pixeldev02.lobbysystem.invetorys;

import de.pixeldev02.lobbysystem.Lobbysystem;
import de.pixeldev02.lobbysystem.gadgets.GadgetItemRegister;
import de.pixeldev02.lobbysystem.gadgets.GadgetItemType;
import de.pixeldev02.lobbysystem.manager.ItemManager;
import de.pixeldev02.lobbysystem.manager.SkullManager;
import de.pixeldev02.lobbysystem.utils.Inventorys;
import de.pixeldev02.lobbysystem.utils.SkullCacher;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.UUID;

public class Gadgets {

    private static Inventory inv = Bukkit.createInventory(null, Inventorys.GUI.GADGETS.getSize(), Inventorys.GUI.GADGETS.getName());

    public static void openGadgets(final Player p) {

        final ItemStack Köpfe = SkullManager.getHead("http://textures.minecraft.net/texture/8b2f46bbc98f3b9826a36539ed5b76cb70d7717d6b32f641aff2123e62844f", "§8» §eKöpfe");
        final ItemStack Specials = SkullManager.getHead("http://textures.minecraft.net/texture/5cb7c21cc43dc17678ee6f16591ffaab1f637c37f4f6bbd8cea497451d76db6d", "§8» §cSpecials");
        final ItemStack Haustiere = SkullManager.getHead("http://textures.minecraft.net/texture/9be8128ab36c5d6c4563a8b4a4b5164c155f2f26027537b3dc23f22a3695449", "§8» §aHaustiere");
        final ItemStack Partikel = SkullManager.getHead("http://textures.minecraft.net/texture/a3cfd94e925eab4330a768afcae6c128b0a28e23149eee41c9c6df894c24f3de", "§8» §5Partikel");
        final ItemStack Placeholder = new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 14).setDisplayName("§b").build();


        inv.clear();

        for(int i = 0; i < Inventorys.GUI.GADGETS.getSize(); i ++) {
            inv.setItem(i, Placeholder);
        }

        inv.setItem(13, Köpfe);
        inv.setItem(20, Specials);
        inv.setItem(24, Partikel);
        inv.setItem(31, Haustiere);

        p.openInventory(inv);
        p.playSound(p.getLocation(), Sound.CHEST_OPEN,  1L, 2L);
    }

    public static void openHeads(final Player p) {
        final ItemStack Air = new ItemManager(Material.AIR).build();
        final ItemStack Placeholder = new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 14).setDisplayName("§b").build();
        final ItemStack Köpfe = new ItemManager(SkullCacher.getHead(Bukkit.getOfflinePlayer(UUID.fromString("e2214300-91f2-46c6-9a75-bf7d5d2a0321")))).setDisplayName("§8» §eKöpfe §8«").build();
        final ItemStack removeHead = new ItemManager(Material.BARRIER).setDisplayName("§8» §4Köpfe entfernen").build();

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(36, Air);
                inv.setItem(37, Air);
                inv.setItem(38, Air);
                inv.setItem(39, Air);
                inv.setItem(40, Air);
                inv.setItem(41, Air);
                inv.setItem(42, Air);
                inv.setItem(43, Air);
                inv.setItem(44, Air);
            }
        }, 1);

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(27, Air);
                inv.setItem(28, Air);
                inv.setItem(29, Air);
                inv.setItem(30, Air);
                inv.setItem(31, Air);
                inv.setItem(32, Air);
                inv.setItem(33, Air);
                inv.setItem(34, Air);
                inv.setItem(35, Air);
            }
        }, 2);

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(18, Air);
                inv.setItem(19, Air);
                inv.setItem(20, Air);
                inv.setItem(21, Air);
                inv.setItem(22, Air);
                inv.setItem(23, Air);
                inv.setItem(24, Air);
                inv.setItem(25, Air);
                inv.setItem(26, Air);
            }
        }, 3);

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(9, Air);
                inv.setItem(10, Air);
                inv.setItem(11, Air);
                inv.setItem(12, Air);
                inv.setItem(13, Air);
                inv.setItem(14, Air);
                inv.setItem(15, Air);
                inv.setItem(16, Air);
                inv.setItem(17, Air);
            }
        }, 4);

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(0, Placeholder);
                inv.setItem(1, Placeholder);
                inv.setItem(2, Placeholder);
                inv.setItem(3, Placeholder);
                inv.setItem(4, Köpfe);
                inv.setItem(5, Placeholder);
                inv.setItem(6, Placeholder);
                inv.setItem(7, Placeholder);
                inv.setItem(8, Placeholder);
            }
        }, 5);

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(9, Air);
                inv.setItem(17, Air);
                try {
                    GadgetItemRegister.getInventoryByType(inv, GadgetItemType.HEAD, p);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, 6);


        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(36, Air);
                inv.setItem(37, Placeholder);
                inv.setItem(38, Placeholder);
                inv.setItem(39, Placeholder);
                inv.setItem(40, removeHead);
                inv.setItem(41, Placeholder);
                inv.setItem(42, Placeholder);
                inv.setItem(43, Placeholder);
                inv.setItem(44, Air);
            }
        }, 7);

        p.updateInventory();
    }

    public static void openSpecials(final Player p) {

        final ItemStack Air = new ItemManager(Material.AIR).build();
        final ItemStack Placeholder = new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 14).setDisplayName("§b").build();
        final ItemStack Specials = SkullManager.getHead("http://textures.minecraft.net/texture/5cb7c21cc43dc17678ee6f16591ffaab1f637c37f4f6bbd8cea497451d76db6d", "§8» §cSpecials §8«");
        final ItemStack removeSpecials = new ItemManager(Material.BARRIER).setDisplayName("§8» §4Specials entfernen").build();

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(36, Air);
                inv.setItem(37, Air);
                inv.setItem(38, Air);
                inv.setItem(39, Air);
                inv.setItem(40, Air);
                inv.setItem(41, Air);
                inv.setItem(42, Air);
                inv.setItem(43, Air);
                inv.setItem(44, Air);
            }
        }, 1);

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(27, Air);
                inv.setItem(28, Air);
                inv.setItem(29, Air);
                inv.setItem(30, Air);
                inv.setItem(31, Air);
                inv.setItem(32, Air);
                inv.setItem(33, Air);
                inv.setItem(34, Air);
                inv.setItem(35, Air);
            }
        }, 2);

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(18, Air);
                inv.setItem(19, Air);
                inv.setItem(20, Air);
                inv.setItem(21, Air);
                inv.setItem(22, Air);
                inv.setItem(23, Air);
                inv.setItem(24, Air);
                inv.setItem(25, Air);
                inv.setItem(26, Air);
            }
        }, 3);

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(9, Air);
                inv.setItem(10, Air);
                inv.setItem(11, Air);
                inv.setItem(12, Air);
                inv.setItem(13, Air);
                inv.setItem(14, Air);
                inv.setItem(15, Air);
                inv.setItem(16, Air);
                inv.setItem(17, Air);
            }
        }, 4);

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(0, Placeholder);
                inv.setItem(1, Placeholder);
                inv.setItem(2, Placeholder);
                inv.setItem(3, Placeholder);
                inv.setItem(4, Specials);
                inv.setItem(5, Placeholder);
                inv.setItem(6, Placeholder);
                inv.setItem(7, Placeholder);
                inv.setItem(8, Placeholder);
            }
        }, 5);

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(9, Air);
                inv.setItem(17, Air);
                try {
                    GadgetItemRegister.getInventoryByType(inv, GadgetItemType.SPECIALS, p);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, 6);


        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(36, Air);
                inv.setItem(37, Placeholder);
                inv.setItem(38, Placeholder);
                inv.setItem(39, Placeholder);
                inv.setItem(40, removeSpecials);
                inv.setItem(41, Placeholder);
                inv.setItem(42, Placeholder);
                inv.setItem(43, Placeholder);
                inv.setItem(44, Air);
            }
        }, 7);

        p.updateInventory();
    }

    public static void openPets(final Player p) {

        final ItemStack Air = new ItemManager(Material.AIR).build();
        final ItemStack Placeholder = new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 14).setDisplayName("§b").build();
        final ItemStack Pets = SkullManager.getHead("http://textures.minecraft.net/texture/9be8128ab36c5d6c4563a8b4a4b5164c155f2f26027537b3dc23f22a3695449", "§8» §aHaustiere §8«");
        final ItemStack removePets = new ItemManager(Material.BARRIER).setDisplayName("§8» §4Haustiere entfernen").build();

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(36, Air);
                inv.setItem(37, Air);
                inv.setItem(38, Air);
                inv.setItem(39, Air);
                inv.setItem(40, Air);
                inv.setItem(41, Air);
                inv.setItem(42, Air);
                inv.setItem(43, Air);
                inv.setItem(44, Air);
            }
        }, 1);

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(27, Air);
                inv.setItem(28, Air);
                inv.setItem(29, Air);
                inv.setItem(30, Air);
                inv.setItem(31, Air);
                inv.setItem(32, Air);
                inv.setItem(33, Air);
                inv.setItem(34, Air);
                inv.setItem(35, Air);
            }
        }, 2);

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(18, Air);
                inv.setItem(19, Air);
                inv.setItem(20, Air);
                inv.setItem(21, Air);
                inv.setItem(22, Air);
                inv.setItem(23, Air);
                inv.setItem(24, Air);
                inv.setItem(25, Air);
                inv.setItem(26, Air);
            }
        }, 3);

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(9, Air);
                inv.setItem(10, Air);
                inv.setItem(11, Air);
                inv.setItem(12, Air);
                inv.setItem(13, Air);
                inv.setItem(14, Air);
                inv.setItem(15, Air);
                inv.setItem(16, Air);
                inv.setItem(17, Air);
            }
        }, 4);

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(0, Placeholder);
                inv.setItem(1, Placeholder);
                inv.setItem(2, Placeholder);
                inv.setItem(3, Placeholder);
                inv.setItem(4, Pets);
                inv.setItem(5, Placeholder);
                inv.setItem(6, Placeholder);
                inv.setItem(7, Placeholder);
                inv.setItem(8, Placeholder);
            }
        }, 5);

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(9, Air);
                inv.setItem(17, Air);
                try {
                    GadgetItemRegister.getInventoryByType(inv, GadgetItemType.PET, p);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, 6);


        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(36, Air);
                inv.setItem(37, Placeholder);
                inv.setItem(38, Placeholder);
                inv.setItem(39, Placeholder);
                inv.setItem(40, removePets);
                inv.setItem(41, Placeholder);
                inv.setItem(42, Placeholder);
                inv.setItem(43, Placeholder);
                inv.setItem(44, Air);
            }
        }, 7);

        p.updateInventory();
    }

    public static void openParticle(final Player p) {

        final ItemStack Air = new ItemManager(Material.AIR).build();
        final ItemStack Placeholder = new ItemManager(Material.STAINED_GLASS_PANE).setData((short) 14).setDisplayName("§b").build();
        final ItemStack Particle = SkullManager.getHead("http://textures.minecraft.net/texture/a3cfd94e925eab4330a768afcae6c128b0a28e23149eee41c9c6df894c24f3de", "§8» §5Partikel §8«");
        final ItemStack removeParticle = new ItemManager(Material.BARRIER).setDisplayName("§8» §4Partikel entfernen").build();

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(36, Air);
                inv.setItem(37, Air);
                inv.setItem(38, Air);
                inv.setItem(39, Air);
                inv.setItem(40, Air);
                inv.setItem(41, Air);
                inv.setItem(42, Air);
                inv.setItem(43, Air);
                inv.setItem(44, Air);
            }
        }, 1);

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(27, Air);
                inv.setItem(28, Air);
                inv.setItem(29, Air);
                inv.setItem(30, Air);
                inv.setItem(31, Air);
                inv.setItem(32, Air);
                inv.setItem(33, Air);
                inv.setItem(34, Air);
                inv.setItem(35, Air);
            }
        }, 2);

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(18, Air);
                inv.setItem(19, Air);
                inv.setItem(20, Air);
                inv.setItem(21, Air);
                inv.setItem(22, Air);
                inv.setItem(23, Air);
                inv.setItem(24, Air);
                inv.setItem(25, Air);
                inv.setItem(26, Air);
            }
        }, 3);

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(9, Air);
                inv.setItem(10, Air);
                inv.setItem(11, Air);
                inv.setItem(12, Air);
                inv.setItem(13, Air);
                inv.setItem(14, Air);
                inv.setItem(15, Air);
                inv.setItem(16, Air);
                inv.setItem(17, Air);
            }
        }, 4);

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(0, Placeholder);
                inv.setItem(1, Placeholder);
                inv.setItem(2, Placeholder);
                inv.setItem(3, Placeholder);
                inv.setItem(4, Particle);
                inv.setItem(5, Placeholder);
                inv.setItem(6, Placeholder);
                inv.setItem(7, Placeholder);
                inv.setItem(8, Placeholder);
            }
        }, 5);

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(9, Air);
                inv.setItem(17, Air);
                try {
                    GadgetItemRegister.getInventoryByType(inv, GadgetItemType.PARTIKEL, p);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, 6);


        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                inv.setItem(36, Air);
                inv.setItem(37, Placeholder);
                inv.setItem(38, Placeholder);
                inv.setItem(39, Placeholder);
                inv.setItem(40, removeParticle);
                inv.setItem(41, Placeholder);
                inv.setItem(42, Placeholder);
                inv.setItem(43, Placeholder);
                inv.setItem(44, Air);
            }
        }, 7);

        p.updateInventory();
    }

}
