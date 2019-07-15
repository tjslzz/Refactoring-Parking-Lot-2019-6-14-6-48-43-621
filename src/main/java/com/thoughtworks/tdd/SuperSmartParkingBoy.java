package com.thoughtworks.tdd;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy{

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) { super(parkingLots); }

    @Override
    public Ticket park(Car car) { return lotPark(parkingLots.stream().reduce((pre,cur)->pre.getEmpty()*1.0/pre.getCapacity()*1.0 >= cur.getEmpty()*1.0/cur.getCapacity()*1.0?pre:cur).orElse(null),car); }
}
