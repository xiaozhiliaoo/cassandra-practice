package org.lili.cassandra.hotel.model;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lili
 * @date 2022/11/5 17:42
 */
@Table(name = TableName.amenities_by_room,
        readConsistency = "QUORUM",
        writeConsistency = "QUORUM",
        caseSensitiveKeyspace = false,
        caseSensitiveTable = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmenitiesByRoom {

    @PartitionKey(0)
    @Column(name = "hotel_id")
    private String hotelId;

    @PartitionKey(1)
    @Column(name = "room_number")
    private Integer roomNumber;


    @Column(name = "amenity_name")
    @ClusteringColumn
    private Integer amenityName;


    @Column(name = "description")
    private String description;

}
