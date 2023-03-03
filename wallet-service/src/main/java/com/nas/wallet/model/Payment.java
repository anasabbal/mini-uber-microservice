package com.nas.wallet.model;


import com.nas.wallet.command.PaymentCommand;
import com.nas.wallet.enums.PaymentStatus;
import com.nas.wallet.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "BUYERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseEntity {

    static String generateBarCode(int l)
    {
        String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";

        StringBuilder s = new StringBuilder(l);
        int i;
        for ( i=0; i<l; i++) {
            int ch = (int)(alphaNumeric.length() * Math.random());
            s.append(alphaNumeric.charAt(ch));
        }
        return s.toString();
    }

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CLIENT")
    @OneToOne
    private Client client;

    @Column(name = "CREDIT_CARD")
    @OneToOne
    private CreditCard creditCard;

    @Column(name = "PAYMENT_STATUS")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "BAR_CODE")
    private String barCode;

    @Column(name = "PAYMENT_TYPE")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @ManyToOne(optional = false)
    private Wallet wallet;

    public static Payment create(final PaymentCommand command){
        final Payment payment = new Payment();

        payment.amount = command.getAmount();
        payment.paymentType = PaymentType.valueOf(command.getPaymentStatus());
        extracted(payment);
        payment.paymentStatus = PaymentStatus.IN_PROCESS;

        return payment;
    }

    private static void extracted(Payment payment) {
        if(payment.paymentType.equals(PaymentType.CREDIT_CARD))
            payment.barCode = null;
        else
            payment.barCode = generateBarCode(20);
    }

    public void linkToWallet(Wallet wallet){
        this.wallet = wallet;
    }
}
