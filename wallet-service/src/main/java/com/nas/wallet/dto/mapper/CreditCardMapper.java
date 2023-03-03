package com.nas.wallet.dto.mapper;


import com.nas.wallet.dto.CreditCardDto;
import com.nas.wallet.model.CreditCard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CreditCardMapper {
    public abstract CreditCardDto toDto(CreditCard creditCard);
}
