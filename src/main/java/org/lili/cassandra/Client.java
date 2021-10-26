package org.lili.cassandra;

import com.datastax.driver.core.*;
import com.datastax.driver.core.policies.DefaultRetryPolicy;
import com.datastax.driver.core.querybuilder.BuiltStatement;
import com.datastax.driver.core.querybuilder.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lili
 * @date 2021/10/26 14:38
 */
public class Client {
    private String nodes;
    private String keyspace;

    private Cluster cluster;
    private Session session;


    public void init() {
        QueryOptions queryOption = new QueryOptions().setFetchSize(1000)
                .setConsistencyLevel(ConsistencyLevel.QUORUM);
        SocketOptions socketOptions = new SocketOptions()
                .setConnectTimeoutMillis(2 * 1000)
                .setReadTimeoutMillis(20 * 1000);
        this.cluster = com.datastax.driver.core.Cluster.builder()
                .withQueryOptions(queryOption)
                .withSocketOptions(socketOptions)
                .withRetryPolicy(DefaultRetryPolicy.INSTANCE)
                .addContactPoints(nodes.split(";"))
                .withoutJMXReporting()
                .build();
        this.session = this.cluster.connect(keyspace);
    }

    public List<String> select(Integer id) {
        List<String> a = new ArrayList<>();
        Client client = new Client();
        BuiltStatement select = QueryBuilder.select().from("TABLE_NAME")
                .where(QueryBuilder.eq("id", id))
                .allowFiltering();
        ResultSet resultSet = client.session.execute(select);
        for (Row row : resultSet) {
            String aaa = row.getString("aaa");
            a.add(aaa);
        }
        return a;


    }
}
