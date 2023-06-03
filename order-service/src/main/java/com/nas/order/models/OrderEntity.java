package com.nas.order.models;


import com.nas.order.data.UserData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderEntity extends BaseEntity{


    public <C extends UserData> void create(final C command){
        final OrderEntity orderEntity = new OrderEntity();
    }
}
