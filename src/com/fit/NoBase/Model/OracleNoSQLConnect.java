package com.fit.NoBase.Model;

import oracle.kv.*;

import java.util.Properties;

/**
 * Created by nartoan on 09/05/2017.
 */
public class OracleNoSQLConnect {
    public OracleNoSQLConnect() {}

    public KVStore getConnect() {
        try {
            KVStoreConfig kvStoreConfig = new KVStoreConfig("kvstore", "localhost:5000");
            Properties properties = new Properties();

            properties.setProperty(KVSecurityConstants.TRANSPORT_PROPERTY,
                    KVSecurityConstants.SSL_TRANSPORT_NAME);
            properties.setProperty
                    (KVSecurityConstants.SSL_TRUSTSTORE_FILE_PROPERTY,
                            "/home/nartoan/Documents/kv-4.3.11/kvroot/security/client.trust");

            kvStoreConfig.setSecurityProperties(properties);

            KVStore kvStore = KVStoreFactory.getStore(kvStoreConfig,
                    new PasswordCredentials("admin", "3pKP6Qg>]e5{".toCharArray()), null);

            return kvStore;
        } catch (AuthenticationFailureException e) {
            e.fillInStackTrace();
        }
        return null;
    }
}
