package de.pixeldev02.lobbysystem.utils;

import org.bukkit.Material;

public enum SettingsMenu {

    SCOREBOARD(10, "§8» §eScoreboard", "§7Möchtes du das Scoreboard aktivieren?", Material.WATCH, 10+4, 10+6, "scoreboard", "§8» §aScoreboard", "§8» §cScoreboard"),
    CHATSOUND(19, "§8» §eChat-Erwähnungssound", "§7Willst du einen Sound bekommen wenn dein Name im Chat auftaucht?", Material.NETHER_STAR, 19+4, 19+6, "chatsound", "§8» §aChatsound", "§8» §cChatsound"),
    TELEPORTTOSPAWNONJOIN(28, "§8» §eJointeleportion", "§7Willst du beim Server eintritt zum Spawn teleportiert werden?", Material.ENDER_PEARL, 28+4, 28+6, "teleportjoin", "§8» §aSpawn", "§8» §cLetze Position"),
    LOBBYCHAT(37, "§8» §eLobbyChat", "§7Willst du den Chat der Lobby sehen?", Material.PAPER, 37+4, 37+6, "lobbychat", "§8» §aLobbyChat", "§8» §cLobbyChat");

    private int id;
    private String displayname;
    private String desc;
    private Material material;
    private int slotback1;
    private int slotfront1;
    private String perm;
    private String onName;
    private String offName;

    SettingsMenu(int id, String displayname, String desc, Material m, int slotfront1, int slotback1, String name, String onName, String offName){
        this.id = id;
        this.displayname = displayname;
        this.desc = desc;
        this.material = m;
        this.slotfront1 = slotfront1;
        this.slotback1 = slotback1;
        this.perm = name;
        this.onName = onName;
        this.offName = offName;
    }

    public String getDesc() {
        return desc;
    }

    public String getDisplayname() {
        return displayname;
    }

    public int getSlot() {
        return id;
    }

    public Material getMaterial() {
        return material;
    }

    public int getBackfront() {
        return slotback1;
    }

    public int getForfront() {
        return slotfront1;
    }

    public String getName() {
        return perm;
    }

    public String getOnName() {
        return onName;
    }

    public String getOffName() {
        return offName;
    }
}
