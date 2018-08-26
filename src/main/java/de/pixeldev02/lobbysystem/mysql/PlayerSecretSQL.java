package de.pixeldev02.lobbysystem.mysql;

import de.pixeldev02.lobbysystem.Lobbysystem;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class PlayerSecretSQL {

   public static void registerPlayer(final UUID uuid) throws SQLException, ClassNotFoundException {
        Bukkit.getScheduler().runTaskAsynchronously(Lobbysystem.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    ResultSet rsp = Lobbysystem.getInstance().getMySQL().onQuery("SELECT * FROM `PlayerSecret` WHERE UUID='" + uuid + "'");
                    if (!rsp.next()) {
                        Lobbysystem.getInstance().getMySQL().onUpdate("INSERT INTO `PlayerSecret` (UUID,Secrets) VALUES ('" + uuid + "', ';');");
                    }
                    rsp.close();
                }
                catch (Exception ex) {
                }
            }
        });
    }

    public static String getSecrets(UUID uuid) throws SQLException, ClassNotFoundException {
        try {
            ResultSet rsp = Lobbysystem.getInstance().getMySQL().onQuery("SELECT * FROM `PlayerSecret` WHERE UUID='" + uuid + "'");
            if (rsp.next()) {
                return rsp.getString("Secrets");
            }
            rsp.close();
        }
        catch (Exception ex) {
            return null;
        }
        return null;
    }

    public static void setSecret(UUID uuid, String value) throws SQLException, ClassNotFoundException {
        Bukkit.getScheduler().runTaskAsynchronously(Lobbysystem.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                Lobbysystem.getInstance().getMySQL().onUpdate("UPDATE `PlayerSecret` SET  `Secrets` =  '" + value + "' WHERE  `UUID` ='" + uuid + "';");
            }
        });
    }

    public static boolean hasSecret(int id, UUID uuid) throws ClassNotFoundException, SQLException {
        ArrayList<Integer> items = new ArrayList<Integer>();
        try {
            String[] split;
            for (int length = (split = getSecrets(uuid).split(";")).length, i = 0; i < length; ++i) {
                String s = split[i];
                if (!s.equalsIgnoreCase("") && !s.equalsIgnoreCase(";") && !s.equalsIgnoreCase(" ")) {
                    items.add(Integer.parseInt(s));
                }
            }
        }
        catch (ClassNotFoundException | SQLException ex2) {
            Exception ex = null;
            Exception e = ex;
            e.printStackTrace();
        }
        return items.contains(id);
    }

    public static ArrayList<Integer> getSecretsFromPlayers(UUID uuid) {
        ArrayList<Integer> items = new ArrayList<Integer>();
        try {
            String[] split;
            for (int length = (split = getSecrets(uuid).split(";")).length, i = 0; i < length; ++i) {
                String s = split[i];
                if (!s.equalsIgnoreCase("") && !s.equalsIgnoreCase(";") && !s.equalsIgnoreCase(" ")) {
                    items.add(Integer.parseInt(s));
                }
            }
        }
        catch (ClassNotFoundException | SQLException ex2) {
            Exception ex = null;
            Exception e = ex;
            e.printStackTrace();
        }
        return items;
    }
}
