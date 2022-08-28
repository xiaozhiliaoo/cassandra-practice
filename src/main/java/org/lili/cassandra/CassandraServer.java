package org.lili.cassandra;

import org.apache.cassandra.service.EmbeddedCassandraService;

/**
 * @author lili
 * @date 2022/7/16 23:10
 */
public class CassandraServer {

    public static void main(String[] args) throws Exception {
        EmbeddedCassandraService cassandra = new EmbeddedCassandraService();
        cassandra.start();
    }
}
