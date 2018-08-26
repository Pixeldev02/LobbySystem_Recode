package de.pixeldev02.lobbysystem.invetorys;

import de.pixeldev02.lobbysystem.manager.InventoryCreator;
import de.pixeldev02.lobbysystem.manager.ItemManager;
import de.pixeldev02.lobbysystem.utils.Inventorys;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Playerhider {

    public static ArrayList<Player> hideAllPlayers = new ArrayList<>();
    public static ArrayList<Player> hideExceptTeam = new ArrayList<>();


    public static void openPlayerHider(final Player p) {
        InventoryCreator inv = new InventoryCreator(Inventorys.GUI.HIDER.getName(), Inventorys.GUI.HIDER.getSize());

        inv.fillInv((short) 14);

        final ItemStack all = new ItemManager(Material.STAINED_CLAY).setData((short) 14).setDisplayName("§8» §cAlle Spieler verstecken").build();
        final ItemStack team = new ItemManager(Material.STAINED_CLAY).setData((short) 10).setDisplayName("§8» §5Team/Freunde anzeigen").build();
        final ItemStack show = new ItemManager(Material.STAINED_CLAY).setData((short) 5).setDisplayName("§8» §aAlle Spieler anzeigen").build();

        inv.setItem(10, show);
        inv.setItem(13, team);
        inv.setItem(16, all);

        inv.openInv(p);
    }
}
