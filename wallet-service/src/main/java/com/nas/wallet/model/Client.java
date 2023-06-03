package com.nas.wallet.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "BUYERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Client extends BaseEntity {

    @Column(name = "ACCOUNT_ID")
    private String accountId;

    public static Client create(final String accountId){
        final Client client = new Client();

        client.accountId = accountId;

        return client;
    }
}
