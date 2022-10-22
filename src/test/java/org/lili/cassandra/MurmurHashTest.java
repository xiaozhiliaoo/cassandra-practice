package org.lili.cassandra;

import org.apache.cassandra.dht.Murmur3Partitioner;
import org.apache.cassandra.dht.RandomPartitioner;
import org.apache.cassandra.utils.MurmurHash;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

/**
 * @author lili
 * @date 2022/10/17 14:57
 */
public class MurmurHashTest {
    @Test
    void test() {
        int hash32 = MurmurHash.hash32(ByteBuffer.wrap("jim".getBytes()), 0, 100, 1);
        System.out.println(hash32);
    }

    @Test
    void test2() {
        RandomPartitioner randomPartitioner = new RandomPartitioner();
        System.out.println(randomPartitioner.getToken(ByteBuffer.wrap("alibaba".getBytes())));
    }

    @Test
    void test3() {
        Murmur3Partitioner mp = new Murmur3Partitioner();
        System.out.println(mp.getToken(ByteBuffer.wrap("jim".getBytes())));
    }
}
