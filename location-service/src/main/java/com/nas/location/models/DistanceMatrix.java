package com.nas.location.models;

public class DistanceMatrix {
    private Address originAddress;
    private Address destinationAddress;
    private DistanceMatrixRow[] rows;

    public static DistanceMatrix create(Address originAddress, Address destinationAddress, DistanceMatrixRow[] rows) {
        final DistanceMatrix distanceMatrix = new DistanceMatrix();

        distanceMatrix.originAddress = originAddress;
        distanceMatrix.destinationAddress = destinationAddress;
        distanceMatrix.rows = rows;

        return distanceMatrix;
    }
}