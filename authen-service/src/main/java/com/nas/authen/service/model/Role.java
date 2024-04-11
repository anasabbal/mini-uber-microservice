package com.nas.authen.service.model;


import com.nas.authen.service.enums.RoleType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(value = "USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Role {
    @Id
    protected String id;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;
    private RoleType roleType;

    public static Role createRole(final String roleName){
        final Role role = new Role();
        role.setRoleType(RoleType.valueOf(roleName.toUpperCase()));
        return role;
    }
}
