package com.fit.NoBase.Controller;

import com.fit.NoBase.Model.OracleNoSQLConnect;
import com.fit.NoBase.Model.Table;
import oracle.kv.FaultException;
import oracle.kv.KVStore;
import oracle.kv.StatementResult;
import oracle.kv.table.TableAPI;

/**
 * Created by nartoan on 09/05/2017.
 */
public class CreateTable {
    public CreateTable() {

    }

    private void create() {
        KVStore kvStore = (new OracleNoSQLConnect()).getConnect();
        TableAPI tableAPI = kvStore.getTableAPI();
        StatementResult statementResult = null;
        String statement = null;

        try {
            kvStore.executeSync(Table.title_table);
            kvStore.executeSync(Table.title_aka_title_table);
            kvStore.executeSync(Table.title_cast_info_table);
            kvStore.executeSync(Table.title_company_table);
            kvStore.executeSync(Table.title_infor_movie_table);
            kvStore.executeSync(Table.title_complete_cast_table);
        } catch (IllegalArgumentException Ie) {
            System.out.println("Sai cu phap !!!");
            Ie.printStackTrace();
        } catch (FaultException e) {
            System.out.println("Truy van khong thanh con!!!");
        }
    }
}
