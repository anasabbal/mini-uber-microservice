package com.nas.wallet.dto.mapper;

import com.nas.wallet.dto.BalanceDto;
import com.nas.wallet.model.Balance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BalanceMapper {
    BalanceDto toDto(Balance balance);
}
