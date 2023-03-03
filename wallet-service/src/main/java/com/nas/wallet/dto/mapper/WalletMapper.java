package com.nas.wallet.dto.mapper;


import com.nas.wallet.dto.WalletDto;
import com.nas.wallet.model.Wallet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    WalletDto toDto(Wallet wallet);
}