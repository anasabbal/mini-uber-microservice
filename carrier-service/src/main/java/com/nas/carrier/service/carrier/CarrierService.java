package com.nas.carrier.service.carrier;

import com.nas.carrier.command.CarrierCommand;
import com.nas.carrier.model.Carrier;

public interface CarrierService {

    Carrier create(final CarrierCommand carrierCommand);
}
