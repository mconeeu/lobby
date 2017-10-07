/*
 * Copyright (c) 2017 Dominik L., BamDev, Rufus Maiwald and the MC ONE Minecraftnetwork. All rights reserved
 * You are not allowed to decompile the code
 */

package eu.mcone.lobby.mysql;

import org.bukkit.Bukkit;

import java.sql.*;

public class MySQL {

    private String host;
    private int port;
    private String database;
    private String username;
    private String password;

    private static Connection con;

    public MySQL(String host, int port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database+"?autoreconnect=true", username, password);
                Bukkit.getConsoleSender().sendMessage("[MySQL.connect] MySQL connection established!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Bukkit.getConsoleSender().sendMessage("[MySQL.connect] connection already established!");
        }
    }

    public void close() {
        if (isConnected()) {
            try {
                con.close();
                Bukkit.getConsoleSender().sendMessage("[MySQL.close] MySQL connection closed!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Bukkit.getConsoleSender().sendMessage("[MySQL.close] no MySQL connection available!");
        }
    }

    public static void execute(String qry) {
        if (isConnected()) {
            try {
                PreparedStatement preparedstatement = con.prepareStatement(qry);
                preparedstatement.executeUpdate();
                Bukkit.getConsoleSender().sendMessage("[MySQL.execute] \""+qry+"\" executed!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Bukkit.getConsoleSender().sendMessage("[MySQL.execute] no MySQL connection available!");
        }
    }

    public static ResultSet select(String qry) {
        if (isConnected()) {
            try {
                PreparedStatement preparedStatement = con.prepareStatement(qry);
                Bukkit.getConsoleSender().sendMessage("[MySQL.select] \""+qry+"\" executed!");
                return preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Bukkit.getConsoleSender().sendMessage("[MySQL.select] no MySQL connection available!");
        }
        return null;
    }

    private static boolean isConnected() {
        return con != null;
    }

}
