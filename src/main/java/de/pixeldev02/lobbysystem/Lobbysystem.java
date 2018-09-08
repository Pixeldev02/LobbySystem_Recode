package de.pixeldev02.lobbysystem;

import de.pixeldev02.lobbysystem.commands.BuildCommand;
import de.pixeldev02.lobbysystem.commands.LocationCommand;
import de.pixeldev02.lobbysystem.manager.SecretManager;
import de.pixeldev02.lobbysystem.gadgets.GadgetItemRegister;
import de.pixeldev02.lobbysystem.listeners.*;
import de.pixeldev02.lobbysystem.manager.*;
import de.pixeldev02.lobbysystem.mysql.GadgetSQL;
import de.pixeldev02.lobbysystem.mysql.MySQL;
import de.pixeldev02.lobbysystem.mysql.PlayerSecretSQL;
import de.pixeldev02.lobbysystem.mysql.SecretSQL;
import de.pixeldev02.lobbysystem.utils.Onlinetime;
import de.pixeldev02.lobbysystem.utils.QuickJoin;
import de.pixeldev02.lobbysystem.utils.TeleportAnimation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Lobbysystem extends JavaPlugin {

    private static Lobbysystem instance;

    private PlayerScoreboardManager playerScoreboard;
    private Onlinetime onlinetimeManager;
    private MySQL mySQL;
    private LocationManager locationManager;
    //private RedisManager redisManager;
    private ServerManager serverManager;
    private TeleportAnimation teleportManager;
    private QuickJoin quickJoinManager;
    private GadgetSQL gadgetSQL;
    private BroadcastManager broadcastManager;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("[LobbySystem] Das Plugin wurde aktiviert");
        instance = this;
        init();
        startScoreboardTask();

        Bukkit.getScheduler().runTaskLater(this, new Runnable() {
            @Override
            public void run() {
                SecretSQL.getAllSecrets();
                SecretSQL.loopSecrets();
            }
        }, 2*20);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[LobbySystem] Das Plugin wurde deaktiviert");
    }

    private void init() {
        registerCommands();
        registerListeners();

        // Utils
        onlinetimeManager = new Onlinetime(this);
        locationManager = new LocationManager(this);
        //redisManager = new RedisManager(this);
        serverManager = new ServerManager(this);
        playerScoreboard = new PlayerScoreboardManager();
        teleportManager = new TeleportAnimation();
        quickJoinManager = new QuickJoin();
        gadgetSQL = new GadgetSQL();
        broadcastManager = new BroadcastManager();
        GadgetItemRegister.registerItems();

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        createConfig();

        mySQL = new MySQL(this, this.getConfig().getString("MySQL.host"), this.getConfig().getInt("MySQL.port"),
                this.getConfig().getString("MySQL.database"), this.getConfig().getString("MySQL.username"), this.getConfig().getString("MySQL.password"));

    }

    private void registerCommands() {
        getCommand("set").setExecutor(new LocationCommand(this));
        getCommand("build").setExecutor(new BuildCommand());
    }

    private void registerListeners() {
        final PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new JoinListener(this), this);
        pm.registerEvents(new InteractListener(), this);
        pm.registerEvents(new InventoryClickListener(), this);
        pm.registerEvents(new ProtectionListener(), this);
        pm.registerEvents(new PlayerQuitListener(), this);
        pm.registerEvents(new ChatListener(), this);
        pm.registerEvents(new HotbarListener(), this);
        pm.registerEvents(new ParticleListener(), this);
        pm.registerEvents(new LoginListener(), this);
    }

    private void startScoreboardTask() {

        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                Lobbysystem.getInstance().getPlayerScoreboard().animate();
            }
        }, 0, 1);

        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach((all) -> {
                    Lobbysystem.getInstance().getPlayerScoreboard().updateScoreboard(all, all.getScoreboard());
                });
            }
        }, 20, 20);
    }

    private void createConfig() {
        getConfig().options().copyDefaults(true);

        if(this.getConfig().getString("MySQL.host").equals("")) {
            getConfig().set("MySQL.host", "localhost");
            getConfig().set("MySQL.port", 3306);
            getConfig().set("MySQL.database", "LobbySystem");
            getConfig().set("MySQL.username", "root");
            getConfig().set("MySQL.password", "test123");
        }

        this.saveConfig();
    }

    public static Lobbysystem getInstance() {
        return instance;
    }

    public PlayerScoreboardManager getPlayerScoreboard() {
        return playerScoreboard;
    }

    public Onlinetime getOnlinetimeManager() {
        return onlinetimeManager;
    }

    public MySQL getMySQL() {
        return mySQL;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }

    //public RedisManager getRedisManager() {
       // return redisManager;
    //}

    public ServerManager getServerManager() {
        return serverManager;
    }

    public TeleportAnimation getTeleportManager() {
        return teleportManager;
    }

    public QuickJoin getQuickJoinManager() {
        return quickJoinManager;
    }

    public GadgetSQL getGadgetSQL() {
        return gadgetSQL;
    }
}
