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
 * @date 2022/11/5 17:35
 */
@Table(name = TableName.pois_by_hotel,
        readConsistency = "QUORUM",
        writeConsistency = "QUORUM",
        caseSensitiveKeyspace = false,
        caseSensitiveTable = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoisByHotel {

    @PartitionKey
    @Column(name = "hotel_id")
    private String hotelId;

    @ClusteringColumn
    @Column(name = "poi_name")
    private String poiName;

    @Column(name = "description")
    private String description;

}
