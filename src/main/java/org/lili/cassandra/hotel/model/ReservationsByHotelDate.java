package org.lili.cassandra.hotel.model;

import com.datastax.driver.core.LocalDate;
import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author lili
 * @date 2022/11/5 17:45
 */
@Table(name = TableName.reservations_by_hotel_date,
        readConsistency = "QUORUM",
        writeConsistency = "QUORUM",
        caseSensitiveKeyspace = false,
        caseSensitiveTable = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationsByHotelDate {

    @PartitionKey
    @Column(name = "hotel_id")
    private String hotelId;

    @PartitionKey(1)
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "confirm_number")
    private String confirmNumber;

    @ClusteringColumn
    @Column(name = "room_number")
    private Short roomNumber;

    @Column(name = "guest_id")
    private UUID guestId;

    public static ReservationsByHotelDate data1() {
        return new ReservationsByHotelDate("1", LocalDate.fromMillisSinceEpoch(System.currentTimeMillis()),
                LocalDate.fromMillisSinceEpoch(System.currentTimeMillis() + 100000), "1",
                Short.parseShort("1"), UUID.randomUUID());
    }
}
