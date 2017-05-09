package com.fit.NoBase.Controller;

import com.fit.NoBase.Model.OracleNoSQLConnect;
import oracle.kv.*;
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

    private void insertData() {
        TableAPI tableAPI = this.connect.getTableAPI();
        StatementResult statementResult = null;
        String statement = null;

        try {

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
