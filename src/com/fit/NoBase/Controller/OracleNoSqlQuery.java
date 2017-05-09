package com.fit.NoBase.Controller;

import com.fit.NoBase.Model.OracleNoSQLConnect;
import oracle.kv.*;
import oracle.kv.table.Row;
import oracle.kv.table.Table;
import oracle.kv.table.TableAPI;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nartoan on 09/05/2017.
 */
public class OracleNoSqlQuery {
    private KVStore connect = null;
    public OracleNoSqlQuery(KVStore kvStore) {
        this.connect = kvStore;
        insertData();
    }

    public static void main(String[] args) {
        OracleNoSqlQuery oracleNoSqlQuery
                = new OracleNoSqlQuery(new OracleNoSQLConnect().getConnect());
        System.out.println(oracleNoSqlQuery.getConnect());
    }

    /**
     * Test query with Key/Value API
     */
    private void query() {
        List<String> majorPath = Arrays.asList("Login", "Toan");
        Key key = Key.createKey(majorPath);


        byte[] bytes = "Do Biet".getBytes();
        Value value = Value.createValue(bytes);

        connect.putIfAbsent(key, value);

        // Read
        ValueVersion vv2 = connect.get(key);
        Value value2 = vv2.getValue();
        System.out.println(value2.toString());
    }

    /**
     * Test query with table API
     */
    private void insertData() {
        TableAPI tableAPI = this.connect.getTableAPI();
        StatementResult statementResult = null;
        String statement = null;

        try {
            Table table = tableAPI.getTable("title");

            // Get a Row instance
            Row row = table.createRow();

            //Put data into row
            row.put("id",2);
            row.put("title", "toan");

            //write data tp table
            tableAPI.put(row, null, null);

            /**
             *  write data into child table
             */
            Table myChildTable = tableAPI.getTable("title.complete_cast");
            Row childRow = myChildTable.createRow();
            childRow.put("id", 1);
            childRow.put("id_cast", 1);
            childRow.put("subject", "test thoi ma");
            tableAPI.put(childRow, null,null);

        } catch (IllegalArgumentException Ie) {
            System.out.println("Sai cu phap !!!");
            Ie.printStackTrace();
        } catch (FaultException e) {
            System.out.println("Truy van khong thanh con!!!");
        }
    }



    public KVStore getConnect() {
        return connect;
    }

    public void setConnect(KVStore connect) {
        this.connect = connect;
    }
}
