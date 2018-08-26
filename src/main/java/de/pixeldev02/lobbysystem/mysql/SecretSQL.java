package de.pixeldev02.lobbysystem.mysql;

import de.pixeldev02.lobbysystem.Lobbysystem;
import de.pixeldev02.lobbysystem.secrets.Secret;
import de.pixeldev02.lobbysystem.secrets.SecretRarity;
import de.pixeldev02.lobbysystem.secrets.SecretRegister;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import javax.persistence.Lob;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SecretSQL {

    public static Integer numberSecrets = 0;

    public static boolean secretExists(Integer id) {
        try {
            ResultSet rs = Lobbysystem.getInstance().getMySQL().onQuery("SELECT * FROM Secrets WHERE ID= " + id + "");
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createSecret(Integer id, String location, String name, Integer rarity) {
        if (!secretExists(id)) {
            Lobbysystem.getInstance().getMySQL().onUpdate("INSERT INTO `Secrets`(`ID`, `Location`, `Name`, `Rarity`) VALUES ('" + id + "','" + location + "','" + name + "','" +  rarity + "')");
        }
    }

    public static void getAllSecrets() {
        ResultSet rs = Lobbysystem.getInstance().getMySQL().onQuery("SELECT COUNT(*) AS number FROM Secrets");

        try {
            while (rs.next()) {
                numberSecrets = rs.getInt("number");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Integer getNumberSecrets() {
        ResultSet rs = Lobbysystem.getInstance().getMySQL().onQuery("SELECT COUNT(*) AS number FROM Secrets");

        try {
            while (rs.next()) {
                return rs.getInt("number");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getLocation(Integer ID) {
        ResultSet rs = Lobbysystem.getInstance().getMySQL().onQuery("SELECT * FROM Secrets WHERE ID ='" + ID + "'");

        try {
            while (rs.next()) {
                return rs.getString("Location");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getName(Integer ID) {
        ResultSet rs = Lobbysystem.getInstance().getMySQL().onQuery("SELECT * FROM Secrets WHERE ID ='" + ID + "'");

        try {
            while (rs.next()) {
                return rs.getString("Name");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Integer getRarity(Integer ID) {
        ResultSet rs = Lobbysystem.getInstance().getMySQL().onQuery("SELECT * FROM Secrets WHERE ID ='" + ID + "'");

        try {
            while (rs.next()) {
                return rs.getInt("Rarity");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void loopSecrets() {

        Bukkit.getScheduler().runTaskLater(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < getNumberSecrets() + 1; i++) {
                    Location loc = Lobbysystem.getInstance().getLocationManager().getLocationString(getLocation(i));
                    SecretRarity rarity = SecretRegister.getRarityByID(getRarity(i));
                    new Secret(i, loc, getName(i), rarity);
                }
            }
        }, 5);
    }
}
