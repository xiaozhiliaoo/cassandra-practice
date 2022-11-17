package org.lili.cassandra;

import com.datastax.driver.core.*;
import com.datastax.driver.core.policies.DefaultRetryPolicy;
import com.datastax.driver.mapping.MappingManager;

import java.util.Objects;

/**
 * @author lili
 * @date 2021/10/26 14:38
 */
public final class Client {
    public static final String NODES = "10.108.160.30";
    public static final String KEYSPACE = "adaplearn_tiku_test";

    private static Cluster cluster;
    private static Session session;

    public static Session session() {
        if (Objects.nonNull(session)) {
            return session;
        } else {
            init();
            return session;
        }
    }

    public static Cluster cluster() {
        if (Objects.nonNull(cluster)) {
            return cluster;
        } else {
            init();
            return cluster;
        }
    }

    public static MappingManager mappingManager() {
        return new MappingManager(session());
    }


    public static void init() {
        QueryOptions queryOption = new QueryOptions().setFetchSize(1000)
                .setConsistencyLevel(ConsistencyLevel.QUORUM);
        SocketOptions socketOptions = new SocketOptions()
                .setConnectTimeoutMillis(2 * 1000)
                .setReadTimeoutMillis(20 * 1000);
        cluster = com.datastax.driver.core.Cluster.builder()
                .withQueryOptions(queryOption)
                .withSocketOptions(socketOptions)
                .withRetryPolicy(DefaultRetryPolicy.INSTANCE)
                .addContactPoints(NODES)
                .withoutJMXReporting()
                .build();
        session = cluster.connect(KEYSPACE);
    }
}
