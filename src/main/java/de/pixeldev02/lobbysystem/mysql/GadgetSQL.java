package de.pixeldev02.lobbysystem.mysql;

import de.pixeldev02.lobbysystem.Lobbysystem;
import org.bukkit.Bukkit;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GadgetSQL {

    public void registerPlayer(final UUID uuid) throws SQLException, ClassNotFoundException {
        Bukkit.getScheduler().runTaskAsynchronously(Lobbysystem.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    ResultSet rsp = Lobbysystem.getInstance().getMySQL().onQuery("SELECT * FROM `Gadgets` WHERE UUID='" + uuid + "'");
                    if (!rsp.next()) {
                        Lobbysystem.getInstance().getMySQL().onUpdate("INSERT INTO `Gadgets` (UUID,items,heads,pets,particles,specials) VALUES ('" + uuid + "', ';', '0', '0', '0', '0');");
                    }
                    rsp.close();
                }
                catch (Exception ex) {
                }

            }
        });
    }

    public String getGadgets(UUID uuid) throws SQLException, ClassNotFoundException {
        try {
            ResultSet rsp = Lobbysystem.getInstance().getMySQL().onQuery("SELECT * FROM `Gadgets` WHERE UUID='" + uuid + "'");
            if (rsp.next()) {
                return rsp.getString("items");
            }
            rsp.close();
        }
        catch (Exception ex) {
            return null;
        }
        return null;
    }

    public void setGadgets(UUID uuid, String valv) throws SQLException, ClassNotFoundException {
        Bukkit.getScheduler().runTaskAsynchronously(Lobbysystem.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                Lobbysystem.getInstance().getMySQL().onUpdate("UPDATE `Gadgets` SET  `items` =  '" + valv + "' WHERE  `UUID` ='" + uuid + "';");
            }
        });
    }

    @SuppressWarnings("null")
    public boolean hasItem(int id, UUID uuid) throws ClassNotFoundException, SQLException {
        ArrayList<Integer> items = new ArrayList<Integer>();
        try {
            String[] split;
            for (int length = (split = this.getGadgets(uuid).split(";")).length, i = 0; i < length; ++i) {
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

    public void setHead(UUID uuid, Integer valv) throws SQLException, ClassNotFoundException {
        Bukkit.getScheduler().runTaskAsynchronously(Lobbysystem.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                Lobbysystem.getInstance().getMySQL().onUpdate("UPDATE `Gadgets` SET  `heads` =  '" + valv + "' WHERE  `UUID` ='" + uuid + "';");
            }
        });
    }

    public void setPets(UUID uuid, Integer valv) throws SQLException, ClassNotFoundException {
        Bukkit.getScheduler().runTaskAsynchronously(Lobbysystem.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                Lobbysystem.getInstance().getMySQL().onUpdate("UPDATE `Gadgets` SET  `pets` =  '" + valv + "' WHERE  `UUID` ='" + uuid + "';");
            }
        });
    }

    public void setParticles(UUID uuid, Integer valv) throws SQLException, ClassNotFoundException {
        Bukkit.getScheduler().runTaskAsynchronously(Lobbysystem.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                Lobbysystem.getInstance().getMySQL().onUpdate("UPDATE `Gadgets` SET  `particles` =  '" + valv + "' WHERE  `UUID` ='" + uuid + "';");
            }
        });
    }

    public void setSpecials(UUID uuid, Integer valv) throws SQLException, ClassNotFoundException {
        Bukkit.getScheduler().runTaskAsynchronously(Lobbysystem.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                Lobbysystem.getInstance().getMySQL().onUpdate("UPDATE `Gadgets` SET  `specials` =  '" + valv + "' WHERE  `UUID` ='" + uuid + "';");
            }
        });
    }

    public Integer getHeads(UUID uuid) throws SQLException, ClassNotFoundException {
        try {
            ResultSet rsp = Lobbysystem.getInstance().getMySQL().onQuery("SELECT * FROM `Gadgets` WHERE UUID='" + uuid + "'");
            if (rsp.next()) {
                return rsp.getInt("heads");
            }
            rsp.close();
        }
        catch (Exception ex) {
            return null;
        }
        return null;
    }

    public Integer getPets(UUID uuid) throws SQLException, ClassNotFoundException {
        try {
            ResultSet rsp = Lobbysystem.getInstance().getMySQL().onQuery("SELECT * FROM `Gadgets` WHERE UUID='" + uuid + "'");
            if (rsp.next()) {
                return rsp.getInt("pets");
            }
            rsp.close();
        }
        catch (Exception ex) {
            return null;
        }
        return null;
    }

    public Integer getParticles(UUID uuid) throws SQLException, ClassNotFoundException {
        try {
            ResultSet rsp = Lobbysystem.getInstance().getMySQL().onQuery("SELECT * FROM `Gadgets` WHERE UUID='" + uuid + "'");
            if (rsp.next()) {
                return rsp.getInt("particles");
            }
            rsp.close();
        }
        catch (Exception ex) {
            return null;
        }
        return null;
    }

    public Integer getSpecials(UUID uuid) throws SQLException, ClassNotFoundException {
        try {
            ResultSet rsp = Lobbysystem.getInstance().getMySQL().onQuery("SELECT * FROM `Gadgets` WHERE UUID='" + uuid + "'");
            if (rsp.next()) {
                return rsp.getInt("specials");
            }
            rsp.close();
        }
        catch (Exception ex) {
            return null;
        }
        return null;
    }
}
