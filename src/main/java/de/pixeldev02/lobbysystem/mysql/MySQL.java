package de.pixeldev02.lobbysystem.mysql;

import de.pixeldev02.lobbysystem.Lobbysystem;

import java.sql.*;

public class MySQL {

    private Lobbysystem plugin;

    public MySQL(Lobbysystem plugin, String host, Integer port, String database, String username, String password) {
        this.plugin = plugin;
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;

        connect();
        setUp();
    }

    public static Connection con;

    private String host;
    private int port;
    private String database;
    private String password ;
    private String username;

    public void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
            System.out.println("[LobbySystem] MySQL >> Verbunden");
            this.setUp();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUp() {
        //this.onUpdate("CREATE TABLE IF NOT EXISTS Quests(UUID VARCHAR(64), Name VARCHAR(16), Bewohner_Chris INT, Fischer_Alex INT, Holzfäller_Flo INT, Gehilfe_Alec INT, Little_John INT)");
        this.onUpdate("CREATE TABLE IF NOT EXISTS LobbyPlayer(UUID VARCHAR(64), Name VARCHAR(16), Onlinezeit VARCHAR(255))");
        this.onUpdate("CREATE TABLE IF NOT EXISTS Gadgets(UUID VARCHAR(255), items VARCHAR(255), heads INT, pets INT, particles INT, specials INT)");
        this.onUpdate("CREATE TABLE IF NOT EXISTS Settings(UUID VARCHAR(255), Name VARCHAR(16))");
        this.onUpdate("CREATE TABLE IF NOT EXISTS Secrets(ID INT, Location VARCHAR(255), Name VARCHAR(32), Rarity INT)");
        this.onUpdate("CREATE TABLE IF NOT EXISTS PlayerSecret(UUID VARCHAR(255), Secrets VARCHAR(255))");

    }

    public void onUpdate(String qry)
    {
        try
        {
            PreparedStatement statement = (PreparedStatement) con.prepareStatement(qry);
            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ResultSet onQuery(String q) {
        ResultSet rs = null;
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(q);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean isCon() {
        return con != null;
    }

    public Connection getConnection() {
        return con;
    }
}
