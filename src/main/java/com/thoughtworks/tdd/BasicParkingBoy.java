package com.thoughtworks.tdd;

import java.util.List;
import java.util.stream.Collectors;

public class BasicParkingBoy extends ParkingBoy{

    public BasicParkingBoy(List<ParkingLot> parkingLots) { super(parkingLots); }

    @Override
    public Ticket park(Car car){ return lotPark(parkingLots.stream().filter(p -> p.getEmpty() > 0).collect(Collectors.toList()),car); }
}
