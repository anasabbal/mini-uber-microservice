package com.nas.location.models;

import com.nas.location.command.AddressCommand;

public class Location {
    private DistanceMatrixRow[] matrixRows;


    public static <T extends AddressCommand> Location create(final T command){
        return null;
    }
}