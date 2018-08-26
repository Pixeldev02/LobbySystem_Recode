package de.pixeldev02.lobbysystem.utils;

public class Inventorys {

    public enum GUI{
        TELEPORTER("§8» §6Navigator", 5*9),SWITCHER("§8» §6Lobbywechsler", 5*9),SERVERLIST("§a§lServer §8» §c§l", 5*9),SETTINGS("§8» §6Einstellungen", 3*9),GADGETS("§8» §6Gadgets", 5*9), HIDER("§8» §6Spielerverstecker", 3*9),
        SETTINGSMENU("§8» §6Menü", 3*9);

        private String name;
        private Integer size;

        GUI(String name, Integer size) {
            this.name = name;
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public Integer getSize() {
            return size;
        }
    }
}
