package org.lili.cassandra.hotel.model;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lili
 * @date 2022/11/5 17:38
 */
@Table(name = TableName.available_rooms_by_hotel_date,
        readConsistency = "QUORUM",
        writeConsistency = "QUORUM",
        caseSensitiveKeyspace = false,
        caseSensitiveTable = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableRoomsByHotelDate {

    @PartitionKey
    @Column(name = "hotel_id")
    private String hotelId;

    @Column(name = "date")
    @ClusteringColumn(0)
    private Date date;

    @Column(name = "room_number")
    @ClusteringColumn(1)
    private Integer roomNumber;
    @Column(name = "is_available")

    private Boolean isAvailable;


}
