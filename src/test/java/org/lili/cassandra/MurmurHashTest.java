package org.lili.cassandra;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
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

    private static final String TEXT = "youdao";

    @Test
    void hash32() {
        int hash32 = MurmurHash.hash32(
                ByteBuffer.wrap(TEXT.getBytes()), 0, TEXT.getBytes().length, 1);
        System.out.println(hash32);
    }

    @Test
    void guavaHash() {
        HashFunction hashFunction = Hashing.murmur3_32(1);
        System.out.println(hashFunction.hashBytes(TEXT.getBytes()));
    }


}
