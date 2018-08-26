package de.pixeldev02.lobbysystem.manager;

import de.pixeldev02.lobbysystem.Lobbysystem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class LocationManager {

    private Lobbysystem plugin;

    public LocationManager(Lobbysystem plugin) {
        this.plugin = plugin;
    }

    public void saveLocation(Location loc, String name) throws FileNotFoundException {
        for (File f : Objects.requireNonNull(Bukkit.getWorldContainer().listFiles())) {
            if (f.isDirectory() && f.getName().equals(loc.getWorld().getName())) {
                String newPath = f.getPath();
                newPath = newPath.replace("\\", "/");
                if (!newPath.endsWith("/"))
                    newPath += "/";
                newPath += "/saved_locations/" + name.toLowerCase() + ".loc";
                File f2 = new File(newPath);
                f2.getParentFile().mkdirs();
                if (f2.exists())
                    f2.delete();
                PrintWriter pw = new PrintWriter(f2);
                pw.write(toString(loc));
                pw.close();
                return;
            }
        }
        throw new FileNotFoundException("Could not find world container");
    }

    private String toString(Location loc) {
        return loc.getX() + "," + loc.getY() + "," + loc.getZ() + "," + loc.getYaw() + "," + loc.getPitch();
    }

    private Location toLocation(World world, String location) {
        String[] spl = location.split(",");
        return new Location(world, Double.parseDouble(spl[0]), Double.parseDouble(spl[1]), Double.parseDouble(spl[2]), Float.parseFloat(spl[3]), Float.parseFloat(spl[4]));
    }

    public Location getLocation(String worldName, String name) throws FileNotFoundException {
        for (File f : Bukkit.getWorldContainer().listFiles()) {
            if (f.isDirectory() && f.getName().equals(worldName)) {
                String newPath = f.getPath();
                newPath = newPath.replace("\\", "/");
                if (!newPath.endsWith("/"))
                    newPath += "/";
                newPath += "/saved_locations/" + name.toLowerCase() + ".loc";
                File f2 = new File(newPath);
                f2.getParentFile().mkdirs();
                if (!f2.exists())
                    throw new FileNotFoundException("Could not find location file.");
                String in = "";
                Scanner sc = new Scanner(new FileInputStream(f2));
                while (sc.hasNext())
                    in += sc.nextLine();
                sc.close();
                World world = Bukkit.getWorld(worldName);
                if (world == null)
                    throw new NullPointerException("Could not find world!");
                return toLocation(world, in);
            }
        }
        throw new FileNotFoundException("Could not find world container");
    }

    public String getString(String worldName, String name) {
        for (File f : Bukkit.getWorldContainer().listFiles()) {
            if (f.isDirectory() && f.getName().equals(worldName)) {
                String newPath = f.getPath();
                newPath = newPath.replace("\\", "/");
                if (!newPath.endsWith("/"))
                    newPath += "/";
                newPath += "/" + name.toLowerCase();
                File f2 = new File(newPath);
                f2.getParentFile().mkdirs();
                if (!f2.exists())
                    return "null";
                String in = "";
                Scanner sc;
                try {
                    sc = new Scanner(new FileInputStream(f2));
                    while (sc.hasNext())
                        in += sc.nextLine();
                    sc.close();
                } catch (FileNotFoundException e) {
                    return "null";
                }
                return in;
            }
        }
        return "null";
    }

    public String getStringLocation(final Location l) {
        if (l == null) {
            return "";
        }
        return l.getWorld().getName() + ":" + l.getBlockX() + ":" + l.getBlockY() + ":" + l.getBlockZ();
    }

    public Location getLocationString(final String s) {
        if (s == null || s.trim() == "") {
            return null;
        }
        final String[] parts = s.split(":");
        if (parts.length == 4) {
            final World w = Bukkit.getServer().getWorld(parts[0]);
            final int x = Integer.parseInt(parts[1]);
            final int y = Integer.parseInt(parts[2]);
            final int z = Integer.parseInt(parts[3]);
            return new Location(w, x, y, z);
        }
        return null;
    }
}
