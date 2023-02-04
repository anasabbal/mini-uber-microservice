package com.nas.wallet.dto.mapper;


import com.nas.wallet.dto.TransactionWalletDto;
import com.nas.wallet.model.TransactionWallet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionWalletMapper {
    TransactionWalletDto toDto(TransactionWallet transactionWallet);
}
