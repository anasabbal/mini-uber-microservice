package com.nas.customer.service.payload;


import com.nas.core.details.BankAccount;
import com.nas.core.details.DriverLocationDto;
import com.nas.core.details.WalletDetails;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDetails {
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private DriverLocationDto driverLocationDto;
    private BankAccount bankAccount;
    private WalletDetails walletDetails;

    public CustomerDetails(String customerId, String firstName, String lastName, String email,
                           DriverLocationDto driverLocationDto,
                           BankAccount bankAccount,
                           WalletDetails walletDetails) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.driverLocationDto = driverLocationDto;
        this.bankAccount = bankAccount;
        this.walletDetails = walletDetails;
    }
}
