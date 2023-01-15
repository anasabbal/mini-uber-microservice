package com.nas.carrier.command;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class JobCommand {
    private String description;
    private Set<ApplicationSubmitCommand> applicationSubmitCommands;
}
