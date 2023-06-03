package com.nas.customer.service.payload;


import com.customer.service.dto.customer.CustomerDto;
import com.nas.core.details.BankAccount;
import com.nas.core.details.DriverLocationDto;
import com.nas.core.details.WalletDetails;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDetails {
    private CustomerDto customer;
    private DriverLocationDto driverLocationDto;
    private BankAccount bankAccount;
    private WalletDetails walletDetails;

    public CustomerDetails(CustomerDto customer,
                           DriverLocationDto driverLocationDto,
                           BankAccount bankAccount,
                           WalletDetails walletDetails) {
        this.customer = customer;
        this.driverLocationDto = driverLocationDto;
        this.bankAccount = bankAccount;
        this.walletDetails = walletDetails;
    }
}
