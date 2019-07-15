package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingStoryThreeTest {
    @Test
    public void shout_return_car_when_call_fetch_given_hasTicket_with_number_11_Cars(){
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));parkingLots.add(new ParkingLot(10));
        BasicParkingBoy basicParkingBoy = new BasicParkingBoy(parkingLots);
        for (int i = 0; i < 10; i++) basicParkingBoy.park(new Car());
        Car car = new Car();
        Ticket ticket = basicParkingBoy.park(car);

        Assertions.assertSame(0,basicParkingBoy.getParkingLots().get(0).getEmpty());
        Assertions.assertSame(9,basicParkingBoy.getParkingLots().get(1).getEmpty());

        Car fetch = basicParkingBoy.fetch(ticket);

        Assertions.assertSame(car,fetch);
        Assertions.assertSame(10,basicParkingBoy.getParkingLots().get(1).getEmpty());
    }
}
