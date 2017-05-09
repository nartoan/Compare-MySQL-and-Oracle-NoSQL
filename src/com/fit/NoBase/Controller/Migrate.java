package com.fit.NoBase.Controller;

import com.fit.NoBase.Model.MySQLConnect;
import com.fit.NoBase.Model.OracleNoSQLConnect;
import oracle.kv.KVStore;
import oracle.kv.StatementResult;
import oracle.kv.table.Row;
import oracle.kv.table.Table;
import oracle.kv.table.TableAPI;

import java.sql.*;

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
            String sql = "SELECT * FROM title LIMIT 10";
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
                int col = resultSetMetaData.getColumnCount();
                for (int j = 1; j <= col; j++) {
                    if (resultSet.getString(j) != null) {
                        if (j != 4) {
                            int type = resultSetMetaData.getColumnType(j);
                            if (type == Types.INTEGER) {
                                row.put(resultSetMetaData.getColumnName(j), resultSet.getInt(j));
                            } else {
                                row.put(resultSetMetaData.getColumnName(j), resultSet.getString(j));
                            }
                        } else {
                            Statement statementSQL2 = connectSQL.createStatement();
                            ResultSet resultSet2 = statementSQL2.executeQuery("SELECT * FROM kind_type");
                            resultSet2.next();

                            row.put("kind", resultSet2.getString(2));

                            resultSet2.close();
                            statementSQL2.close();
                        }
                    }
                }
                System.out.println(dem++ + " : ++++" + row);
                tableAPI.put(row, null, null);
            }

            resultSet.close();
            statementSQL.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
