package com.nas.location.models;

public class DistanceMatrixRow {
    private DistanceMatrixElement[] elements;

    public static DistanceMatrixRow create(DistanceMatrixElement[] elements){
        final DistanceMatrixRow distanceMatrixRow = new DistanceMatrixRow();

        distanceMatrixRow.elements = elements;

        return distanceMatrixRow;
    }
}
