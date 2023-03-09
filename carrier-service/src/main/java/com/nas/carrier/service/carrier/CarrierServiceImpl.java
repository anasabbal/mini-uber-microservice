package com.nas.carrier.service.carrier;


import com.nas.carrier.command.CarrierCommand;
import com.nas.carrier.model.Carrier;
import com.nas.carrier.repository.CarrierRepository;
import com.nas.core.util.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarrierServiceImpl implements CarrierService{

    private final CarrierRepository carrierRepository;

    @Override
    public Carrier create(CarrierCommand carrierCommand) {
        log.info("[+] Begin creating carrier with payload {}", JSONUtil.toJSON(carrierCommand));
        final Carrier carrier = Carrier.create(carrierCommand);
        log.info("[+] Carrier with id {} created succsfully", carrier.getId());
        return carrierRepository.save(carrier);
    }
}