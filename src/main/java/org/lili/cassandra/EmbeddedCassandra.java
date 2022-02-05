package org.lili.cassandra;

import org.apache.cassandra.service.EmbeddedCassandraService;

import java.io.IOException;

/**
 * @author lili
 * @date 2022/1/23 0:48
 */
public class EmbeddedCassandra {
    public static void main(String[] args) throws IOException {
        EmbeddedCassandraService ecs = new EmbeddedCassandraService();
        ecs.start();
    }
}
