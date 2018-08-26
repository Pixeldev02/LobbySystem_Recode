package de.pixeldev02.lobbysystem.utils;

public class Heads {

    public enum TEAM_UNCLAIMED {
        NICODEVHD("§c§lNicoDevHD", "§fNormal", "§c§lNicht im Besitz"),ALEXKANNS("§c§lAlexkanns", "§fNormal", "§c§lNicht im Besitz"),ANDIDERCHEGGA("§c§lAndiderChegga", "§fNormal", "§c§lNicht im Besitz")
        ,PIXELDEV02("§c§lPixelDev02", "§fNormal", "§c§lNicht im Besitz"),SUPAABOY("§c§lSupaaboy", "§fNormal", "§c§lNicht im Besitz"),CLIENTFEEGA("§c§lClientFeega", "§fNormal", "§c§lNicht im Besitz"),
        RENE0055("§c§lRene0055", "§fNormal", "§c§lNicht im Besitz");

        private String name;
        private String rarity;
        private String claimed;

        TEAM_UNCLAIMED(String name, String rarity, String claimed) {
            this.name = name;
            this.rarity = rarity;
            this.claimed = claimed;
        }

        public String getName() {
            return name;
        }

        public String getRarity() {
            return "§7Seltenheit: " + rarity;
        }

        public String getClaimed() {
            return claimed;
        }
    }

    public enum YOUTUBE_UNCLIAMED {
        UNGESPIELT("§c§lUngespielt", "§fNormal", "§c§lNicht im Besitz"),PALUTEN("§c§lPaluten", "§fNormal", "§c§lNicht im Besitz"),GERMANLETSPLAY("§c§GermanLetsPlay", "§fNormal", "§c§lNicht im Besitz")
        ,GOMMEHD("§c§lGommeHD", "§fNormal", "§c§lNicht im Besitz"),MAUDADO("§c§lMaudado", "§fNormal", "§c§lNicht im Besitz"),LPMITKEV("§c§lLPMitKev", "§bSelten", "§c§lNicht im Besitz"),
        REWINSIDE("§c§lRewinside", "§bSelten", "§c§lNicht im Besitz"), ZANDER("§c§lZanderLP", "§bSelten", "§c§lNicht im Besitz"), MRMOREGAME("§c§lMrMoreGame", "§bSelten", "§c§lNicht im Besitz"),
        EARLIBOY("§c§lEarliboy", "§6Legendär", "§c§lNicht im Besitz"), ZINUS("§c§lZinus", "§6Legendär", "§c§lNicht im Besitz"), STURMWAFFEL("§c§lSturmWaffelHD", "§6Legendär", "§c§lNicht im Besitz"),
        FLOOTASTISCH("§c§lFlooTastisch", "§6Legendär", "§c§lNicht im Besitz"), ABGEGRIEFT("§c§lAbgegrieft", "§6Legendär", "§c§lNicht im Besitz");

        private String name;
        private String rarity;
        private String claimed;

        YOUTUBE_UNCLIAMED(String name, String rarity, String claimed) {
            this.name = name;
            this.rarity = rarity;
            this.claimed = claimed;
        }

        public String getName() {
            return name;
        }

        public String getRarity() {
            return "§7Seltenheit: " + rarity;
        }

        public String getClaimed() {
            return claimed;
        }
    }
}
