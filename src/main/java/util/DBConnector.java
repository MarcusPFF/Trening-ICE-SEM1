package util;

import java.sql.*;

public class DBConnector {
    Connection connect;

    public void connectToDB(String url) {

        try {
            connect = DriverManager.getConnection(url);
            //Statement stmt = connect.createStatement();
            //ResultSet rs = stmt.executeQuery(sql) - Dette skal bruges i enten EM eller AM

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }
}
