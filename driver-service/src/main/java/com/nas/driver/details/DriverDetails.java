package com.nas.driver.details;

import com.nas.core.details.BankAccount;
import com.nas.core.details.DriverLocationDto;
import com.nas.core.details.WalletDetails;
import lombok.Getter;


@Getter
public class DriverDetails {
    private String driverId;
    private String firstName;
    private String lastName;
    private DriverLocationDto driverLocationDto;
    private BankAccount bankAccount;
    private WalletDetails walletDetails;

    public DriverDetails(String driverId, String firstName, String lastName,
                           DriverLocationDto driverLocationDto,
                           BankAccount bankAccount,
                           WalletDetails walletDetails) {
        this.driverId = driverId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.driverLocationDto = driverLocationDto;
        this.bankAccount = bankAccount;
        this.walletDetails = walletDetails;
    }
}
