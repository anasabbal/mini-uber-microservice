package com.nas.auth.model;


import com.nas.auth.command.UserCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_name"),
        @UniqueConstraint(columnNames = "email")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity{
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Role role;

    @Column(name = "CUSTOMER_ID")
    private String customerId;

    public static Account create(final UserCommand userCommand){
        final Account account = new Account();

        account.userName = userCommand.getUserName();
        account.email = userCommand.getEmail();
        account.password = userCommand.getPassword();
        account.role = Role.create();
        return account;
    }
}
