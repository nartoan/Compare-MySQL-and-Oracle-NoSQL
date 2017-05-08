package com.fit.NoBase.Model;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

/**
 * Created by nartoan on 09/05/2017.
 */
public class MySQLConnect {

    public MySQLConnect(){

    }

    public Connection getConnect() {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/imdb?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=GMT";
        String username = "root";
        String password = "admin";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
