package com.nas.wallet.dto.mapper;


import com.nas.wallet.dto.WalletDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", imports = {BalanceMapper.class, TransactionWalletMapper.class})
public interface WalletMapper {
    WalletDto toDto(Wallet wallet);
}