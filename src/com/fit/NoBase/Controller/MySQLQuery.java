package com.fit.NoBase.Controller;

import com.fit.NoBase.Model.MySQLConnect;

import java.sql.*;

/**
 * Created by nartoan on 09/05/2017.
 */
public class MySQLQuery {
    public static void main(String[] args) {
        MySQLConnect mySQLConnect = new MySQLConnect();
        Connection connection = mySQLConnect.getConnect();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM title LIMIT 500";
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("Data : "+ resultSet);

            while (resultSet.next()) {
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

                int col = resultSetMetaData.getColumnCount();
                for (int j = 1; j <= col; j++) {
                    System.out.println(resultSetMetaData.getColumnName(j) + ":" + resultSet.getString(j));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
