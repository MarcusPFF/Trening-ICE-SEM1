package util;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    Connection conn;

    public void connect(String url) {

        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }
    /* Ret dette så navnene giver mening

     */
    public ArrayList<String> selectPlayers() {
        // initialize a List to return the selected data as string elements
        ArrayList<String> data = new ArrayList<>();
        // make the query string
        String sql = "SELECT name, balance, id FROM Players";

        try {
            Statement stmt = conn.createStatement();

            // execute the query
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                //read each row of the result set ( = response from the query execution)
                String row = rs.getString("name") + ", " + rs.getInt("balance") + ", " + rs.getString("id");
                //add the string to the ArrayList
                data.add(row);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    public ArrayList<String> addPlayer(String n, int b) {
        ArrayList<String> data = new ArrayList<>();
        String sql = "INSERT INTO PLAYERS (name, balance) VALUES (\'" + n + "\'," + b + ")";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String row = rs.getString("name") + ", " + rs.getInt("balance");
                data.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
}
