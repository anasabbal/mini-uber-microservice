package com.nas.wallet.dto.mapper;


import com.nas.wallet.dto.PaymentDto;
import com.nas.wallet.model.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PaymentMapper {

    public abstract PaymentDto toDto(Payment payment);
}