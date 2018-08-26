package de.pixeldev02.lobbysystem.secrets;

import de.pixeldev02.lobbysystem.mysql.PlayerSecretSQL;
import de.pixeldev02.lobbysystem.mysql.SecretSQL;
import de.pixeldev02.lobbysystem.utils.Data;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.sql.SQLException;
import java.util.HashMap;

public class SecretRegister {

    public static HashMap<Location, Secret> currentItems = new HashMap<Location, Secret>();
    public static HashMap<Integer, Secret> idsOfItems = new HashMap<Integer, Secret>();

    public static HashMap<Integer, Location> locSecret = new HashMap<>();
    public static HashMap<Integer, String> nameSecret = new HashMap<>();
    public static HashMap<Integer, SecretRarity> raritySecret = new HashMap<>();

    public static void registerItems() {

    }

    public static Integer getRarity(SecretRarity rarity) {
        if(rarity.equals(SecretRarity.LEICHT)) {
            return 1;
        } else if(rarity.equals(SecretRarity.MITTEL)) {
            return 2;
        } else if(rarity.equals(SecretRarity.SCHWER)) {
            return 3;
        } else {
            return 1;
        }
    }

    public static SecretRarity getRarityByID(Integer ID) {
        if(ID == 1) {
            return SecretRarity.LEICHT;
        } else if(ID == 2) {
            return SecretRarity.MITTEL;
        } else if(ID == 3) {
            return SecretRarity.SCHWER;
        } else {
            return SecretRarity.LEICHT;
        }
    }

    public static Integer setID() {
        return SecretSQL.getNumberSecrets() + 1;

    }

    public static void onInteractSecret(final PlayerInteractEvent e) throws SQLException, ClassNotFoundException {
        final Player p = e.getPlayer();
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if(e.getClickedBlock() != null) {
                for (Secret type : SecretRegister.idsOfItems.values()) {
                    if(e.getClickedBlock().getLocation().equals(type.getLoc())) {
                        if(PlayerSecretSQL.hasSecret(type.getId(), p.getUniqueId())) {
                            p.sendMessage( "§6Secrets §8× §7Du hast das Secret §c" + type.getName() +" §7bereits gefunden");
                            return;
                        } else {
                            PlayerSecretSQL.setSecret(p.getUniqueId(), PlayerSecretSQL.getSecrets(p.getUniqueId()) + ";" + type.getId());
                            p.sendMessage("§7§m---------------------------------");
                            p.sendMessage("                        §a§lSecret");
                            p.sendMessage("");
                            p.sendMessage("              §7Name: §b§l" + type.getName());
                            p.sendMessage("              §7Schwierigkeit: §c§l" + type.getRarity());
                            p.sendMessage("§7§m---------------------------------");
                        }
                    }
                }
            }
        }
    }
}
