package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingStoryFourTest {
    @Test
    public void shout_return_car_when_call_fetch_given_hasTicket_with_smartBoy(){
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));parkingLots.add(new ParkingLot(10));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        for (int i = 0; i < 10; i++) smartParkingBoy.park(new Car());
        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);

        Assertions.assertSame(4, smartParkingBoy.getParkingLots().get(0).getEmpty());

        Car fetch = smartParkingBoy.fetch(ticket);

        Assertions.assertSame(car,fetch);
        Assertions.assertSame(5, smartParkingBoy.getParkingLots().get(0).getEmpty());
    }
}
