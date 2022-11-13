package org.lili.cassandra.hotel.model;

import com.datastax.driver.mapping.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lili
 * @date 2022/11/5 17:28
 */
@Table(name = TableName.hotels_by_poi,
        readConsistency = "QUORUM",
        writeConsistency = "QUORUM",
        caseSensitiveKeyspace = false,
        caseSensitiveTable = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelsByPoi {
    @PartitionKey
    @Column(name = "poi_name")
    private String poiName;

    @ClusteringColumn
    @Column(name = "hotel_id")
    private String hotelId;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    @Frozen
    private Address address;
}
