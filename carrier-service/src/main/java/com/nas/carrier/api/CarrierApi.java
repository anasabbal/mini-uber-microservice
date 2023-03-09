package com.nas.carrier.api;


import com.nas.carrier.command.CarrierCommand;
import com.nas.carrier.command.JobCommand;
import com.nas.carrier.dto.CarrierDto;
import com.nas.carrier.dto.JobDto;
import com.nas.carrier.dto.mapper.CarrierMapper;
import com.nas.carrier.model.Carrier;
import com.nas.carrier.service.carrier.CarrierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.nas.core.constants.ResourcePath.CARRIERS;
import static com.nas.core.constants.ResourcePath.V1;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(V1 + CARRIERS)
@RequiredArgsConstructor
public class CarrierApi {


    private final CarrierService carrierService;
    private final CarrierMapper carrierMapper;


    @PostMapping
    public ResponseEntity<CarrierDto> create(@RequestBody final CarrierCommand carrierCommand){
        final Carrier carrier = carrierService.create(carrierCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(carrier.getId()).toUri();
        return ResponseEntity.created(uri).body(carrierMapper.toDto(carrier));
    }

}
