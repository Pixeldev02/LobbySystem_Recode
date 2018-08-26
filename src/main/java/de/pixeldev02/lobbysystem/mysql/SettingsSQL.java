package de.pixeldev02.lobbysystem.mysql;

import de.pixeldev02.lobbysystem.Lobbysystem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingsSQL {

    public static boolean playerExists(String uuid) {
        try {
            ResultSet rs = Lobbysystem.getInstance().getMySQL().onQuery("SELECT * FROM Settings WHERE UUID= '" + uuid + "'");
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

    public static void createPlayer(String uuid) {
        if (!playerExists(uuid)) {
            Lobbysystem.getInstance().getMySQL().onUpdate("INSERT INTO `Settings`(`UUID`, `chatsound`, `lobbychat`, `scoreboard`, `teleportjoin`) VALUES ('" + uuid + "', '1','1','1','1')");
        }
    }


    public static Integer getAktiv(String uuid, String type) {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = Lobbysystem.getInstance().getMySQL().onQuery("SELECT * FROM Settings WHERE UUID= '" + uuid.replace("-", "") + "'");
                if ((rs.next()) && (Integer.valueOf(rs.getInt(type)) == null)) {
                }
                i = Integer.valueOf(rs.getInt(type));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static void setAktiv(String uuid, Integer aktiv, String type) {
        if (playerExists(uuid)) {
            Lobbysystem.getInstance().getMySQL().onUpdate("UPDATE Settings SET " + type + "= '" + aktiv + "' WHERE UUID= '" + uuid + "';");
        } else {
            setAktiv(uuid, aktiv, type);
        }
    }
}
