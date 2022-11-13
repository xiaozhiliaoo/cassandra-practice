package org.lili.cassandra.hotel.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.FrozenValue;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author lili
 * @date 2022/11/5 17:51
 */
@Table(name = TableName.guests,
        readConsistency = "QUORUM",
        writeConsistency = "QUORUM",
        caseSensitiveKeyspace = false,
        caseSensitiveTable = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guests {

    @PartitionKey
    private UUID guest_id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "title")
    private String title;
    @Column(name = "emails")
    private Set<String> emails;
    @Column(name = "phone_numbers")
    private List<String> phoneNumbers;
    @Column(name = "addresses")
    @FrozenValue
    private Map<String, Address> addresses;

}
