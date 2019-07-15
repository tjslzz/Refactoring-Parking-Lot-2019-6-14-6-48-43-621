package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingStoryFiveTest {
    private List<ParkingLot> parkingLots = new ArrayList<>();
    private List<Car> cars = new ArrayList<>();
    private SuperSmartParkingBoy superSmartParkingBoy;

    @BeforeEach
    public void setUp(){
        parkingLots.add(new ParkingLot(20));
        parkingLots.add(new ParkingLot(10));
        cars.add(new Car());
        cars.add(new Car());
        superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
    }

    @Test
    public void shout_return_car_when_call_fetch_given_hasTicket_with_superSmartBoy(){
        for (int i = 0; i < 15; i++) superSmartParkingBoy.park(new Car());
        Car car = new Car();
        Ticket ticket = superSmartParkingBoy.park(car);

        Assertions.assertSame(9, superSmartParkingBoy.getParkingLots().get(0).getEmpty());
        Car fetch = superSmartParkingBoy.fetch(ticket);
        Assertions.assertSame(car,fetch);
        Assertions.assertSame(10, superSmartParkingBoy.getParkingLots().get(0).getEmpty());
    }
}
