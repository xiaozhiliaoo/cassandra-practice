package org.lili.cassandra.hotel;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lili.cassandra.hotel.model.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

class QueryServiceTest {

    private final static QueryService service = new QueryService();


    @Test
    void queryBuilderMaterializedView() {
    }

    @Test
    void mappingMaterializedView() {
    }

    @Test
    @DisplayName("初始化数据")
    void initTableData() {
        service.initTableData();
    }

    @Test
    @DisplayName("清空表数据")
    void truncateTableData() {
        service.truncateTableData();
    }

    @Test
    @DisplayName("创建表结构")
    void createTableSchema() {
        service.createTableSchema();
    }

    @Test
    @DisplayName("删除表结构")
    void dropTableSchema() {
        service.dropTableSchema();
    }

    @Test
    @DisplayName("Q1. Find hotels near given poi")
    void hotels_by_poi() {
        List<HotelsByPoi> hotelsByPois = service.hotels_by_poi("");
    }

    @Test
    @DisplayName("Q2. Find information about a hotel")
    void hotels() {
        List<Hotel> hotels = service.hotels("");
    }

    @Test
    @DisplayName("Q3. Find pois near a hotel")
    void pois_by_hotel() {
        List<PoisByHotel> poisByHotels = service.pois_by_hotel("", "");
    }

    @Test
    @DisplayName("Q4. Find available rooms by hotel / date")
    void available_rooms_by_hotel_date() {
        List<AvailableRoomsByHotelDate> availableRoomsByHotelDates = service.available_rooms_by_hotel_date("", new Date(), 1);
    }

    @Test
    @DisplayName("Q5. Find amenities for a room")
    void amenities_by_room() {
        service.amenities_by_room("1", 1, "1");
    }

    @Test
    @DisplayName("Q6. Find reservations by confirmation")
    void reservations_by_confirmation() {
        service.reservations_by_confirmation(1, "1", new Date(), 1);
    }

    @Test
    @DisplayName("Q7. Find reservations by hotel and date")
    void reservations_by_hotel_date() {
        List<ReservationsByHotelDate> reservationsByHotelDates = service.reservations_by_hotel_date("", new Date(), 1);
    }

    @Test
    @DisplayName("Q8. Find reservations by guest name")
    void reservations_by_guest() {
        List<ReservationsByGuest> reservationsByGuests = service.reservations_by_guest("", "");
    }

    @Test
    @DisplayName("Q9. Find guest by ID")
    void guests() {
        List<Guests> guests = service.guests(UUID.randomUUID());
    }

    @BeforeClass
    static void setUp() {
    }

    @AfterClass
    static void tearDown() {
    }

    @Test
    void insertGuest() {
        service.insertGuest();
    }

    @Test
    void mapperSaveGuestUsingOption() {
        service.mapperSaveGuestUsingOption();
    }
}