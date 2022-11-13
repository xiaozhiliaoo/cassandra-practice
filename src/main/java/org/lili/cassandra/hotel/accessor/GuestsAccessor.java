package org.lili.cassandra.hotel.accessor;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import org.lili.cassandra.hotel.model.Guests;

/**
 * @author lili03
 */
@Accessor
public interface GuestsAccessor {

    @Query("SELECT * FROM guests where guest_id=?")
    Result<Guests> getByGuests(String uuid);
}
