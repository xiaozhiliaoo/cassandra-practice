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
 * reservations_by_hotel_date的物化视图。物化视图也建模成Table进行查询
 *
 * @author lili
 * @date 2022/11/5 17:56
 */
@Table(name = TableName.reservations_by_confirmation,
        readConsistency = "QUORUM",
        writeConsistency = "QUORUM",
        caseSensitiveKeyspace = false,
        caseSensitiveTable = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationsByConfirmationView {


    @ClusteringColumn(0)
    @Column(name = "hotel_id")
    private String hotelId;
    @Column(name = "start_date")
    @ClusteringColumn(1)

    private LocalDate startDate;
    @Column(name = "room_number")
    @ClusteringColumn(2)
    private Short roomNumber;

    @PartitionKey
    @Column(name = "confirm_number")
    private String confirmNumber;


    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "guest_id")
    private UUID guestId;

}
