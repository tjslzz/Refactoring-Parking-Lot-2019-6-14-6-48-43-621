package com.thoughtworks.tdd;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy{

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) { return lotPark(parkingLots.stream().reduce((pre,cur)->pre.getEmpty() >= cur.getEmpty()?pre:cur).orElse(null),car); }
}
