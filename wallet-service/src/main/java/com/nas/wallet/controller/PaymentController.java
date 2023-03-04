package com.nas.wallet.controller;


import com.nas.wallet.command.PaymentCommand;
import com.nas.wallet.dto.PaymentDto;
import com.nas.wallet.dto.mapper.PaymentMapper;
import com.nas.wallet.model.Payment;
import com.nas.wallet.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.nas.core.constants.ResourcePath.V1;
import static com.nas.core.constants.ResourcePath.WALLET_PAYMENT;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(V1 + WALLET_PAYMENT)
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @PostMapping
    public ResponseEntity<PaymentDto> create(@RequestBody final PaymentCommand paymentCommand){
        final Payment payment = paymentService.create(paymentCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(payment.getId()).toUri();
        return ResponseEntity.created(uri).body(paymentMapper.toDto(payment));
    }
}
