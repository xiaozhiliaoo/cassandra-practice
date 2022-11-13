package org.lili.cassandra.hotel.model;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

/**
 * @author lili
 * @date 2022/11/5 17:51
 */
@Table(name = TableName.reservations_by_guest,
        readConsistency = "QUORUM",
        writeConsistency = "QUORUM",
        caseSensitiveKeyspace = false,
        caseSensitiveTable = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationsByGuest {

    @PartitionKey
    @Column(name = "guest_last_name")
    private String guest_last_name;
    @ClusteringColumn
    @Column(name = "hotel_id")
    private String hotelId;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private String endDate;
    @Column(name = "room_number")
    private Integer roomNumber;
    @Column(name = "confirm_number")
    private Integer confirmNumber;
    @Column(name = "guest_id")
    private UUID guestId;

}
