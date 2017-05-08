package com.fit.NoBase.Controller;

import com.fit.NoBase.Model.OracleNoSQLConnect;
import oracle.kv.KVStore;
import oracle.kv.Key;
import oracle.kv.Value;
import oracle.kv.ValueVersion;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nartoan on 09/05/2017.
 */
public class OracleNoSqlQuery {
    private KVStore connect = null;
    public OracleNoSqlQuery(KVStore kvStore) {
        this.connect = kvStore;
        query();
    }

    public static void main(String[] args) {
        OracleNoSqlQuery oracleNoSqlQuery
                = new OracleNoSqlQuery(new OracleNoSQLConnect().getConnect());
        System.out.println(oracleNoSqlQuery.getConnect());
    }

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

    public KVStore getConnect() {
        return connect;
    }

    public void setConnect(KVStore connect) {
        this.connect = connect;
    }
}
