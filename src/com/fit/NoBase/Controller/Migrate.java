package com.fit.NoBase.Controller;

import com.fit.NoBase.Model.MySQLConnect;
import com.fit.NoBase.Model.OracleNoSQLConnect;
import oracle.kv.KVStore;
import oracle.kv.table.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by nartoan on 10/05/2017.
 */
public class Migrate {
    private KVStore connectNoSQL = null;
    private Connection connectSQL = null;

    public static void main(String[] args) {
        Migrate migrate = new Migrate();
        migrate.start();
    }

    public Migrate() {
        connectNoSQL = (new OracleNoSQLConnect()).getConnect();
        connectSQL = (new MySQLConnect()).getConnect();
    }

    public void start() {
        try {
            /**
             * Query trong mysql
             */
            Statement statementSQL = connectSQL.createStatement();
            String sql = "SELECT id FROM title";
            ResultSet resultSet = statementSQL.executeQuery(sql);

            /**
             * Query trong oracle nosql
             */
            TableAPI tableAPI = connectNoSQL.getTableAPI();
            Table table = tableAPI.getTable("title");
            Row row = table.createRow();

            int dem = 1;
            while (resultSet.next()) {
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

                /**
                 * Insert data into table title
                 */
                int index = resultSet.getInt(1);
                System.out.println(dem++ + ".......");
//                insertInto_complete_cast(index, tableAPI);
 //               int col = resultSetMetaData.getColumnCount();
//                for (int j = 1; j <= col; j++) {
//                    if (resultSet.getString(j) != null) {
//                        if (j != 4) {
//                            int type = resultSetMetaData.getColumnType(j);
//                            if (type == Types.INTEGER) {
//                                row.put(resultSetMetaData.getColumnName(j), resultSet.getInt(j));
//                            } else {
//                                row.put(resultSetMetaData.getColumnName(j), resultSet.getString(j));
//                            }
//                        } else {
//                            Statement statementSQL2 = connectSQL.createStatement();
//                            ResultSet resultSet2 = statementSQL2.executeQuery("SELECT * FROM kind_type");
//                            resultSet2.next();
//
//                            row.put("kind", resultSet2.getString(2));
//
//                            resultSet2.close();
//                            statementSQL2.close();
//                        }
//                    }
//                }
//                //Insert keyword
//               // row.put("keyword", "");

//                System.out.println(dem++ + " : ++++" + row);
//                tableAPI.put(row, null, null);
            }

            resultSet.close();
            statementSQL.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private ArrayList insertKeyWord(int id_movie) {
        ArrayList list = new ArrayList();
        try {
            Statement statementSQL2 = connectSQL.createStatement();
            ResultSet resultSet2 = statementSQL2.executeQuery(
                    "SELECT keyword FROM movie_keyword JOIN keyword ON keyword.id = movie_keyword.keyword_id WHERE movie_id =" + id_movie);

            int i = 1;
            while (resultSet2.next()) {
                list.add(resultSet2.getString(i++));
            }

            resultSet2.close();
            statementSQL2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    private void insertInto_complete_cast(int id_movie,TableAPI tableAPI) {
        try {
            Table table = tableAPI.getTable("title.complete_cast");
            Row row = table.createRow();

            Statement statementSQL2 = connectSQL.createStatement();
            ResultSet resultSet2 = statementSQL2.executeQuery(
                    "SELECT status_id, kind as subject FROM imdb.complete_cast " +
                    "JOIN comp_cast_type ON comp_cast_type.id = subject_id " +
                    "WHERE movie_id = " + id_movie);
            int i = 1;
            Statement statementSQL3 = connectSQL.createStatement();
            ResultSet resultSet3 = null;
            while (resultSet2.next()) {
                 resultSet3 = statementSQL3.executeQuery(
                        "SELECT kind FROM imdb.comp_cast_type WHERE id =" + resultSet2.getInt(1));
                resultSet3.next();
                row.put("id", id_movie);
                row.put("id_cast", i++);
                row.put("subject", resultSet2.getString(2));
                row.put("status", resultSet3.getString(1));

                tableAPI.put(row, null,null);
                System.out.println(row);
            }

            resultSet2.close();
            statementSQL2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertInto_company(int id_movie,TableAPI tableAPI) {
        try {
            Table table = tableAPI.getTable("title");
            Row row = table.createRow();

            Statement statementSQL2 = connectSQL.createStatement();
            ResultSet resultSet2 = statementSQL2.executeQuery(
                    "SELECT keyword FROM movie_keyword JOIN keyword ON keyword.id = movie_keyword.keyword_id WHERE movie_id =" + id_movie);

            while (resultSet2.next()) {

            }

            resultSet2.close();
            statementSQL2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertInto_infor_movie(int id_movie,TableAPI tableAPI) {
        try {
            Table table = tableAPI.getTable("title");
            Row row = table.createRow();

            Statement statementSQL2 = connectSQL.createStatement();
            ResultSet resultSet2 = statementSQL2.executeQuery(
                    "SELECT keyword FROM movie_keyword JOIN keyword ON keyword.id = movie_keyword.keyword_id WHERE movie_id =" + id_movie);

            while (resultSet2.next()) {

            }

            resultSet2.close();
            statementSQL2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertInto_aka_title(int id_movie,TableAPI tableAPI) {
        try {
            Table table = tableAPI.getTable("title");
            Row row = table.createRow();

            Statement statementSQL2 = connectSQL.createStatement();
            ResultSet resultSet2 = statementSQL2.executeQuery(
                    "SELECT keyword FROM movie_keyword JOIN keyword ON keyword.id = movie_keyword.keyword_id WHERE movie_id =" + id_movie);

            while (resultSet2.next()) {

            }

            resultSet2.close();
            statementSQL2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertInto_cast_info(int id_movie,TableAPI tableAPI) {
        try {
            Table table = tableAPI.getTable("title");
            Row row = table.createRow();

            Statement statementSQL2 = connectSQL.createStatement();
            ResultSet resultSet2 = statementSQL2.executeQuery(
                    "SELECT keyword FROM movie_keyword JOIN keyword ON keyword.id = movie_keyword.keyword_id WHERE movie_id =" + id_movie);

            while (resultSet2.next()) {

            }

            resultSet2.close();
            statementSQL2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
