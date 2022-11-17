package org.lili.cassandra;

import org.apache.cassandra.db.marshal.BytesType;
import org.apache.cassandra.dht.ByteOrderedPartitioner;
import org.apache.cassandra.dht.LocalPartitioner;
import org.apache.cassandra.dht.Murmur3Partitioner;
import org.apache.cassandra.dht.RandomPartitioner;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

/**
 * @author lili
 * @date 2022/11/15 21:58
 */
class PartitionerTest {

    public static final String ALIBABA = "alibaba";

    @Test
    void randomPartitioner() {
        RandomPartitioner randomPartitioner = new RandomPartitioner();
        System.out.println(randomPartitioner.getToken(ByteBuffer.wrap(ALIBABA.getBytes())));
    }

    @Test
    void murmur3Partitioner() {
        Murmur3Partitioner murmur3Partitioner = new Murmur3Partitioner();
        System.out.println(murmur3Partitioner.getToken(ByteBuffer.wrap(ALIBABA.getBytes())));
    }

    @Test
    void byteOrderedPartitioner() {
        ByteOrderedPartitioner byteOrderedPartitioner = new ByteOrderedPartitioner();
        System.out.println(byteOrderedPartitioner.getToken(ByteBuffer.wrap(ALIBABA.getBytes())));
    }
}
