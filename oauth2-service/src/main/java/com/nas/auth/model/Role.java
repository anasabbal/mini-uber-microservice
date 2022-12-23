package com.nas.auth.model;


import com.nas.auth.enums.RoleType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Role extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_TYPE")
    private RoleType roleType;

    public static Role create(){
        final Role role = new Role();
        role.roleType = RoleType.CUSTOMER;

        return role;
    }
}
