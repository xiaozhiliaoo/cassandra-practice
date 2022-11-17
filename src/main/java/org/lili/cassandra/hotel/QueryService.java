package org.lili.cassandra.hotel;

import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.BuiltStatement;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Truncate;
import com.datastax.driver.core.schemabuilder.SchemaBuilder;
import com.datastax.driver.core.schemabuilder.SchemaStatement;
import com.datastax.driver.mapping.Mapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.lili.cassandra.hotel.model.*;
import org.springframework.jmx.export.annotation.ManagedOperation;

import java.util.*;

import static org.lili.cassandra.Client.*;

/**
 * @author lili
 * @date 2022/11/5 18:24
 */

public class QueryService {


    public List<ReservationsByConfirmationView> queryBuilderMaterializedView(String confirmNumber) {
        BuiltStatement selectOne = QueryBuilder.select("hotel_id", "start_date", "room_number",
                        "confirm_number", "end_date", "guest_id")
                .from(TableName.reservations_by_confirmation).where(QueryBuilder.eq("confirm_number", confirmNumber));
        ResultSet resultSet = session().execute(selectOne);
        Iterator<Row> iterator = resultSet.iterator();
        List<ReservationsByConfirmationView> views = Lists.newArrayList();
        while (iterator.hasNext()) {
            Row next = iterator.next();
            String hotel_id = next.getString("hotel_id");
            LocalDate start_date = next.getDate("start_date");
            short room_number = next.getShort("room_number");
            String confirm_number = next.getString("confirm_number");
            LocalDate end_date = next.getDate("end_date");
            UUID guest_id = next.getUUID("guest_id");
            views.add(new ReservationsByConfirmationView(hotel_id, start_date, room_number, confirm_number, end_date, guest_id));
        }
        return views;
    }


    public ReservationsByConfirmationView mappingMaterializedView(String hotelId) {
        Mapper<ReservationsByConfirmationView> mapper = mappingManager()
                .mapper(ReservationsByConfirmationView.class);
        ReservationsByConfirmationView view = mapper.get("1", hotelId,
                LocalDate.fromYearMonthDay(2022, 11, 9),
                Short.parseShort("1"));
        return view;
    }


    @ManagedOperation(description = "插入Guest")
    public void insertGuest() {
        UserType userType = cluster().getMetadata()
                .getKeyspace(KEYSPACE)
                .getUserType("address");

        UDTValue address = userType.newValue()
                .setString("street", "马连洼")
                .setString("city", "北京")
                .setString("state_or_province", "北京直辖市")
                .setString("postal_code", "10000")
                .setString("country", "中国");
        Map<String, UDTValue> maps = new HashMap<>();
        maps.put("1", address);
        maps.put("2", address);
        maps.put("3", address);
        Insert guest = QueryBuilder.insertInto(TableName.guests)
                .value("guest_id", UUID.randomUUID())
                .value("first_name", "li")
                .value("last_name", "li")
                .value("title", "java developer")
                .value("emails", Sets.newHashSet("1", "2", "3"))
                .value("phone_numbers", Lists.newArrayList("110", "120", "119"))
                .value("addresses", maps)
                .ifNotExists();
        session().execute(guest);
        return;
    }

    @ManagedOperation(description = "mapperSaveGuestUsingOption 插入Guest")
    public void mapperSaveGuestUsingOption() {
        Mapper<Guests> mapper = mappingManager().mapper(Guests.class);
        Guests guests = new Guests();
        guests.setGuest_id(UUID.randomUUID());
        //guests.setFirstName("li");
        guests.setLastName("li2");
        guests.setTitle("java");
        guests.setEmails(Sets.newHashSet("1", "2", "3"));
        guests.setPhoneNumbers(Lists.newArrayList("110", "120", "119"));
        HashMap<String, Address> maps = Maps.newHashMap();
        Address address = new Address("1", "1", "1", "1", "1");
        maps.put("1", address);
        maps.put("2", address);
        maps.put("3", address);
        guests.setAddresses(maps);

        //mapper.save(guests, Mapper.Option.ifNotExists(true));
        //mapper.save(guests, Mapper.Option.ttl(10));
        //mapper.save(guests, Mapper.Option.saveNullFields(true));
        mapper.save(guests, Mapper.Option.timestamp(System.currentTimeMillis()));
        return;
    }


    @ManagedOperation(description = "初始化所有数据(By QueryBuilder)")
    public void initTableData() {
        UserType userType = cluster().getMetadata()
                .getKeyspace(KEYSPACE)
                .getUserType("address");
        UDTValue address = userType.newValue()
                .setString("street", "马连洼")
                .setString("city", "北京")
                .setString("state_or_province", "北京直辖市")
                .setString("postal_code", "10000")
                .setString("country", "中国");
        Insert insertHotel = QueryBuilder.insertInto(TableName.hotels)
                .value("id", "1")
                .value("address", address)
                .value("name", "11111")
                .value("phone", "1111111")
                .value("pois", Sets.newHashSet("1", "2", "3"))
                .ifNotExists();
        session().execute(insertHotel);

        Insert insertByDate = QueryBuilder.insertInto(TableName.reservations_by_hotel_date)
                .value("hotel_id", "1")
                .value("start_date", LocalDate.fromMillisSinceEpoch(System.currentTimeMillis()))
                // 字符串2022-11-09不行，
                // 手贱的写入了joda的LocalDate不行，
                // JDK的LocalDate.now()，不行
                // JDK的LocalDate.now().toString()，不行
                // new Date()，不行
                // new Date().toString()，不行
                // com.datastax.driver.core.LocalDate
                .value("room_number", Short.parseShort("1"))
                .value("confirm_number", "1")
                .value("end_date", LocalDate.fromMillisSinceEpoch(System.currentTimeMillis() + 1000000))
                .value("guest_id", UUID.randomUUID())
                .ifNotExists();


        session().execute(insertByDate);
        return;
    }

    public void truncateTableData() {
        Truncate truncate = QueryBuilder.truncate(TableName.hotels);
        Truncate truncate2 = QueryBuilder.truncate(TableName.reservations_by_hotel_date);
        session().execute(truncate);
        session().execute(truncate2);

        return;
    }

    public void createTableSchema() {
        SchemaStatement create = SchemaBuilder.createTable(TableName.hotels2)
                .addPartitionKey("id", DataType.text())
                .addColumn("name", DataType.text())
                .addColumn("phone", DataType.text())
                .addColumn("address", DataType.text())
                .addColumn("pois", DataType.set(DataType.text()));
        session().execute(create);
        return;
    }

    public void dropTableSchema() {
        SchemaStatement drop = SchemaBuilder.dropTable(TableName.hotels2);
        session().execute(drop);
        return;
    }


    @ManagedOperation(description = "Q1. Find hotels near given poi")
    public List<HotelsByPoi> hotels_by_poi(String poiName) {
        return Collections.emptyList();
    }

    @ManagedOperation(description = "Q2. Find information about a hotel")
    public List<Hotel> hotels(String hotelId) {
        return Collections.emptyList();
    }

    @ManagedOperation(description = "Q3. Find pois near a hotel")
    public List<PoisByHotel> pois_by_hotel(String hotelId, String poiName) {
        return Collections.emptyList();
    }

    @ManagedOperation(description = "Q4. Find available rooms by hotel / date")
    public List<AvailableRoomsByHotelDate> available_rooms_by_hotel_date(String hotelId, Date date, Integer roomNumber) {
        return Collections.emptyList();
    }

    @ManagedOperation(description = "Q5. Find amenities for a room")
    public List<AmenitiesByRoom> amenities_by_room(String hotelId, Integer roomNumber, String amenityName) {
        return Collections.emptyList();
    }

    @ManagedOperation(description = "Q6. Find reservations by confirmation")
    public List<ReservationsByConfirmationView> reservations_by_confirmation(Integer confirmNumber, String hotelId,
                                                                             Date startDate, Integer roomNumber) {
        return Collections.emptyList();
    }

    @ManagedOperation(description = "Q7. Find reservations by hotel and date")
    public List<ReservationsByHotelDate> reservations_by_hotel_date(String hotelId, Date startDate, Integer roomNumber) {
        return Collections.emptyList();
    }

    @ManagedOperation(description = "Q8. Find reservations by guest name")
    public List<ReservationsByGuest> reservations_by_guest(String guestLastName, String hotelId) {
        return Collections.emptyList();
    }

    @ManagedOperation(description = "Q9. Find guest by ID")
    public List<Guests> guests(UUID guestId) {
        return Collections.emptyList();
    }
}
