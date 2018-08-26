package de.pixeldev02.lobbysystem.secrets;

import org.bukkit.Location;

public class Secret {

    Integer id;
    Location loc;
    String name;
    SecretRarity rarity;

    public Secret(Integer id, Location loc, String name, SecretRarity rarity) {
        this.id = id;
        this.loc = loc;
        this.name = name;
        this.rarity = rarity;
        SecretRegister.currentItems.put(loc, this);
        SecretRegister.idsOfItems.put(id, this);

        SecretRegister.locSecret.put(id, loc);
        SecretRegister.nameSecret.put(id, name);
        SecretRegister.raritySecret.put(id, rarity);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRarity(SecretRarity rarity) {
        this.rarity = rarity;
    }

    public Integer getId() {
        return id;
    }

    public Location getLoc() {
        return loc;
    }

    public String getName() {
        return name;
    }

    public SecretRarity getRarity() {
        return rarity;
    }
}
