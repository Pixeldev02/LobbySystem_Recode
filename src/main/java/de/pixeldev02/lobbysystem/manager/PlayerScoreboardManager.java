package de.pixeldev02.lobbysystem.manager;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.player.permission.PermissionGroup;
import de.pixeldev02.lobbysystem.Lobbysystem;
import de.pixeldev02.lobbysystem.mysql.PlayerSecretSQL;
import de.pixeldev02.lobbysystem.mysql.SecretSQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class PlayerScoreboardManager {

    private Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

    private int animationTick = 0;

    private String[] animation = new String[]
            {
                    "§c§lE", "§c§lE", "§c§lEp", "§c§lEp", "§c§lEpi", "§c§lEpi", "§c§lEpic", "§c§lEpic", "§c§lEpicJ",
                    "§c§lEpicJ", "§c§lEpicJu", "§c§lEpicJu", "§c§lEpicJum", "§c§lEpicJum",
                    "§c§lEpicJump", "§c§lEpicJump",
                    "§c§lEpicJump", "§c§lEpicJump",
                    "§c§lEpicJump", "§c§lEpicJump",
                    "§c§lEpicJump", "§c§lEpicJump",
                    "§c§lEpicJump", "§c§lEpicJump",
                    "§c§lEpicJump", "§c§lEpicJump",
                    "§c§lEpicJump", "§c§lEpicJump",
                    "§c§lEpicJump", "§c§lEpicJump", "§c§lEpicJump",
                    "§4§lE§c§lpicJump", "§4§lEp§c§licJump", "§4§lEpi§c§lcJump", "§c§lE§4§lpic§c§lJump", "§c§lEp§4§licJ§c§lump",
                    "§c§lEpi§4§lcJu§c§lmp", "§c§lEpic§4§lJum§c§lp", "§c§lEpicJ§4§lump", "§c§lEpicJu§4§lmp", "§c§lEpicJum§4§lp",
                    "§c§lEpicJump", "§c§lEpicJump", "§c§lEpicJump", "§c§lEpicJump", "§c§lEpicJump", "§c§lEpicJump", "§c§lEpicJump",
                    "§c§lEpicJump",  "§c§lEpicJump",  "§c§lEpicJump",  "§c§lEpicJump",  "§c§lEpicJump",
                    "", "", "", "", "", "", "", "", "", "", "", "",
                    "§6§lMinigames Netzwerk", "§6§lMinigames Netzwerk", "§6§lMinigames Netzwerk",
                    "§6§lMinigames Netzwerk", "§6§lMinigames Netzwerk", "§6§lMinigames Netzwerk",
                    "§6§lMinigames Netzwerk", "§6§lMinigames Netzwerk", "§6§lMinigames Netzwerk",
                    "", "", "", "", "", "", "", "", "", "", "", "",
                    "§6§lMinigames Netzwerk", "§6§lMinigames Netzwerk" ,"§6§lMinigames Netzwerk" ,
                    "§6§lMinigames Netzwerk", "§6§lMinigames Netzwerk" ,"§6§lMinigames Netzwerk",
                    "§6§lMinigames Netzwerk","§6§lMinigames Netzwerk", "§6§lMinigames Netzwerk",
                    "", "", "", "", "", "", "", "", "", "", "", ""
            };

    public void setPlayerBoard(Player p) {
        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("aaa", "bbb");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(animation[animationTick]);

        obj.getScore("§7§m------------------").setScore(16);

        obj.getScore("§7Rang:").setScore(15);
        obj.getScore(ChatColor.GRAY.toString()).setScore(14);
        obj.getScore(ChatColor.RED.toString()).setScore(13);

        obj.getScore("§7Onlinezeit:").setScore(12);
        obj.getScore(ChatColor.MAGIC.toString()).setScore(11);
        obj.getScore(ChatColor.GREEN.toString()).setScore(10);

        obj.getScore("§7Coins:").setScore(9);
        obj.getScore(ChatColor.GOLD.toString()).setScore(8);
        obj.getScore(ChatColor.STRIKETHROUGH.toString()).setScore(7);

        obj.getScore("§7Secrets:").setScore(6);
        obj.getScore(ChatColor.DARK_RED.toString()).setScore(5);
        obj.getScore(ChatColor.DARK_BLUE.toString()).setScore(4);

        obj.getScore("§7Server:").setScore(3);
        obj.getScore(ChatColor.YELLOW.toString()).setScore(2);
        obj.getScore(ChatColor.DARK_GRAY.toString()).setScore(1);



        CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId());
        PermissionGroup permissionGroup = CloudAPI.getInstance().getPermissionGroup(cloudPlayer.getPermissionEntity().getGroups().iterator().next().getGroup());
        String prefix = permissionGroup.getPrefix();


        Team Rang = scoreboard.registerNewTeam("rank");
        Rang.setPrefix("§8» " + prefix);
        Rang.addEntry(ChatColor.GRAY.toString());

        Team OnlineZeit = scoreboard.registerNewTeam("onlinetime");
        OnlineZeit.setPrefix("§8» §b" + Lobbysystem.getInstance().getOnlinetimeManager().getPlayerOnlineTime(p));
        OnlineZeit.addEntry(ChatColor.MAGIC.toString());

        Team Coins = scoreboard.registerNewTeam("coins");
        Coins.setPrefix("§8» §e" /*+ CoinsAPI.getCoins(p)*/); //TODO COINS
        Coins.addEntry(ChatColor.GOLD.toString());

        Team Server = scoreboard.registerNewTeam("server");
        Server.setPrefix("§8» §a" + Lobbysystem.getInstance().getServerManager().getPlayerServerName(p));
        Server.addEntry(ChatColor.YELLOW.toString());

        Team Secrets = scoreboard.registerNewTeam("secrets");
        Secrets.setPrefix("§8» §c" + PlayerSecretSQL.getSecretsFromPlayers(p.getUniqueId()).size() +"§7/§c" + SecretSQL.getNumberSecrets());
        Secrets.addEntry(ChatColor.DARK_RED.toString());

        Team admin = scoreboard.registerNewTeam("000admin");
        admin.setPrefix("§4Admin §8× §4");

        Team konzept = scoreboard.registerNewTeam("001konzept");
        konzept.setPrefix("§cKonzept §8× §c");

        Team design = scoreboard.registerNewTeam("002design");
        design.setPrefix("§eDesign §8× §e");

        Team srdev = scoreboard.registerNewTeam("003srdevveloper");
        srdev.setPrefix("§bSrDev §8× §b");

        Team dev = scoreboard.registerNewTeam("004dev");
        dev.setPrefix("§bDev §8× §b");

        Team jrdev = scoreboard.registerNewTeam("005jrdev");
        jrdev.setPrefix("§bJrDev §8× §b");

        Team srmod = scoreboard.registerNewTeam("006srmod");
        srmod.setPrefix("§cSrMod §8× §c");

        Team mod = scoreboard.registerNewTeam("007mod");
        mod.setPrefix("§cMod §8× §c");

        Team srbuilder = scoreboard.registerNewTeam("008srbuilder");
        srbuilder.setPrefix("§aSrBuild §8× §a");

        Team builder = scoreboard.registerNewTeam("009builder");
        builder.setPrefix("§aBuild §8× §a");

        Team sup = scoreboard.registerNewTeam("010Sup");
        sup.setPrefix("§9Sup §8× §9");

        Team jrsup = scoreboard.registerNewTeam("011JrSup");
        jrsup.setPrefix("§9JrSup §8× §9");

        Team vip = scoreboard.registerNewTeam("012vip");
        vip.setPrefix("§5VIP §8× §5");

        Team premiumplus = scoreboard.registerNewTeam("013premium+");
        premiumplus.setPrefix("§eP+ §8× §e");

        Team epic = scoreboard.registerNewTeam("014epic");
        epic.setPrefix("§2Epic §8× §2");

        Team premium = scoreboard.registerNewTeam("014premium");
        premium.setPrefix("§6");

        Team beta = scoreboard.registerNewTeam("015beta");
        beta.setPrefix("§3Beta §8× §7");

        Team spieler = scoreboard.registerNewTeam("016");
        spieler.setPrefix("§7");

        for (Player all : Bukkit.getOnlinePlayers()) {
            CloudPlayer player = CloudAPI.getInstance().getOnlinePlayer(all.getUniqueId());

            if (player.getPermissionEntity().isInGroup("Admin")) {
                admin.addPlayer(all);
            } else if (player.getPermissionEntity().isInGroup("Konzept")) {
                konzept.addPlayer(all);
            } else if (player.getPermissionEntity().isInGroup("Design")) {
                design.addPlayer(all);
            } else if (player.getPermissionEntity().isInGroup("SrDev")) {
                srdev.addPlayer(all);
            } else if (player.getPermissionEntity().isInGroup("Dev")) {
                dev.addPlayer(all);
            } else if (player.getPermissionEntity().isInGroup("JrDev")) {
                jrdev.addPlayer(all);
            } else if (player.getPermissionEntity().isInGroup("SrMod")) {
                srmod.addPlayer(all);
            } else if (player.getPermissionEntity().isInGroup("Mod")) {
                mod.addPlayer(all);
            } else if (player.getPermissionEntity().isInGroup("SrBuilder")) {
                srbuilder.addPlayer(all);
            } else if (player.getPermissionEntity().isInGroup("Builder")) {
                builder.addPlayer(all);
            } else if (player.getPermissionEntity().isInGroup("Sup")) {
                sup.addPlayer(all);
            } else if (player.getPermissionEntity().isInGroup("JrSup")) {
                jrsup.addPlayer(all);
            } else if (player.getPermissionEntity().isInGroup("VIP")) {
                vip.addPlayer(all);
            } else if (player.getPermissionEntity().isInGroup("Premium+")) {
                premiumplus.addPlayer(all);
            } else if (player.getPermissionEntity().isInGroup("Epic")) {
                epic.addPlayer(all);
            } else if (player.getPermissionEntity().isInGroup("Premium")) {
                premium.addPlayer(all);
            } else if (player.getPermissionEntity().isInGroup("Spieler")) {
                spieler.addPlayer(all);
            } else if (player.getPermissionEntity().isInGroup("Beta")) {
                beta.addPlayer(all);
            } else {
                spieler.addPlayer(all);
            }
        }

        p.setScoreboard(scoreboard);
    }

    public void animate() {
        animationTick ++;

        Bukkit.getScheduler().runTask(Lobbysystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(Player all : Bukkit.getOnlinePlayers()) {
                    if(all.getScoreboard() != null) {
                        all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(animation[animationTick]);
                    } else {
                        return;
                    }
                }
            }
        });

        if(animationTick == animation.length) {
            animationTick = 0;
        }
    }

    public void updateScoreboard(final Player p, Scoreboard sb) {
        CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId());
        PermissionGroup permissionGroup = CloudAPI.getInstance().getPermissionGroup(cloudPlayer.getPermissionEntity().getGroups().iterator().next().getGroup());
        String prefix = permissionGroup.getPrefix();

        Team Rang = sb.getTeam("rank");
        Rang.setPrefix("§8» " + prefix);

        Team OnlineZeit = sb.getTeam("onlinetime");
        OnlineZeit.setPrefix("§8» §b" + Lobbysystem.getInstance().getOnlinetimeManager().getPlayerOnlineTime(p));

        Team Coins = sb.getTeam("coins");
        Coins.setPrefix("§8» §e" /*+ CoinsAPI.getCoins(p)*/); //TODO COINS

        Team Server = sb.getTeam("server");
        Server.setPrefix("§8» §a" + Lobbysystem.getInstance().getServerManager().getPlayerServerName(p));

        Team Secrets = sb.getTeam("secrets");
        Secrets.setPrefix("§8» §c" + PlayerSecretSQL.getSecretsFromPlayers(p.getUniqueId()).size() +"§7/§c" + SecretSQL.getNumberSecrets());
    }
}
