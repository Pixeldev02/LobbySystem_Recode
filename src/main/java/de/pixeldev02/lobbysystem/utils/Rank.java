package de.pixeldev02.lobbysystem.utils;

public enum Rank {

    ADMIN(200, "Admin", "§4", "§4Admin", "Admin"),
    SRDEV(200, "SrDeveloper", "§b", "§bSrDev", "Dev"),
    SPIELER(10, "Spieler", "§7", "§7Spieler", "Spieler");


    private int access_level;
    private String rankname;
    private String rankcolor;
    private String scoreboardname;
    private String shortname;

    private Rank(int access_level, String rankname, String rankcolor, String scoreboardname, String shortName) {
        this.access_level = access_level;
        this.rankname = rankname;
        this.rankcolor = rankcolor;
        this.scoreboardname = scoreboardname;
        this.shortname = shortName;
    }

    public static Rank fromString(String getRank) {
        for (Rank rank : Rank.values()) {
            if(!rank.name().equalsIgnoreCase(getRank)) continue;
            return rank;
        }
        return SPIELER;
    }

    public static String getRanks() {
        StringBuilder stringBuilder = new StringBuilder();
        int current = 1;
        for(Rank rank : Rank.values()) {
            if(current == Rank.values().length) {
                stringBuilder.append(rank.getRankcolor() + rank.getRankname());
            } else {
                stringBuilder.append(rank.getRankcolor() + rank.getRankname() + ", ");
            }
            current ++;
        }
        return stringBuilder.toString();
    }

    public int getAccess_level() {
        return access_level;
    }

    public String getRankcolor() {
        return rankcolor;
    }

    public String getRankname() {
        return rankname;
    }

    public String getScoreboardname() {
        return scoreboardname;
    }

    public String getShortname() {
        return shortname;
    }

    public boolean isTeam() {
        return this.getAccess_level() >= 50;
    }

    public boolean isHigherRank(Rank r) {
        return this.getAccess_level() > r.getAccess_level();
    }

    public boolean isLowerRank(Rank r) {
        return this.getAccess_level() < r.getAccess_level();
    }

    public boolean isSameRank(Rank r) {
        return this.getAccess_level() == r.getAccess_level();
    }
}
