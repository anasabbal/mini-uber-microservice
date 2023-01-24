package com.nas.carrier.command;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CarrierCommand {
    private Set<JobCommand> jobCommands;
}
