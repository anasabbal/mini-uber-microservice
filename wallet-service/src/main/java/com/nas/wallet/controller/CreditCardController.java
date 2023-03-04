package com.nas.wallet.controller;


import com.nas.wallet.command.CreditCardCommand;
import com.nas.wallet.dto.CreditCardDto;
import com.nas.wallet.dto.mapper.CreditCardMapper;
import com.nas.wallet.model.CreditCard;
import com.nas.wallet.service.creditcard.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.nas.core.constants.ResourcePath.V1;
import static com.nas.core.constants.ResourcePath.CREDIT_CARD;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(V1 + CREDIT_CARD)
@RequiredArgsConstructor
public class CreditCardController {

    private final CreditCardService creditCardService;
    private final CreditCardMapper creditCardMapper;


    @PostMapping("/{walletId}")
    public ResponseEntity<CreditCardDto> create(@PathVariable("walletId") final String walletId, @RequestBody final CreditCardCommand command){
        final CreditCard creditCard = creditCardService.create(walletId, command);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(creditCard.getId()).toUri();
        return ResponseEntity.created(uri).body(creditCardMapper.toDto(creditCard));
    }
}
