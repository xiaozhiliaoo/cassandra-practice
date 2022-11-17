package org.lili.cassandra;

import org.apache.cassandra.gms.FailureDetector;
import org.apache.cassandra.locator.InetAddressAndPort;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author lili
 * @date 2022/11/16 10:43
 */
public class FailureDetectorTest {
    @Test
    void test1() throws Exception {
        FailureDetector fd = new FailureDetector();
        fd.isAlive(InetAddressAndPort.getByAddress(InetAddress.getByName("39.156.66.10")));
    }
}
