package org.lili.cassandra;

import org.apache.cassandra.dht.Murmur3Partitioner;
import org.apache.cassandra.dht.tokenallocator.OfflineTokenAllocator;
import org.apache.cassandra.utils.OutputHandler;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author lili
 * @date 2022/10/17 15:56
 */
public class OfflineTokenAllocatorTest {
    @Test
    void testAllocate() {
        List<OfflineTokenAllocator.FakeNode> allocate = OfflineTokenAllocator.allocate(5, 8, new int[]{1, 2, 3},
                new OutputHandler.SystemOutput(true, true), new Murmur3Partitioner());
        System.out.println(allocate);
    }
}
