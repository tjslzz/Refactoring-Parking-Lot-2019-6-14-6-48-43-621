package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingStoryFourTest {

    private List<ParkingLot> parkingLots = new ArrayList<>();
    private List<Car> cars = new ArrayList<>();
    private SmartParkingBoy smartParkingBoy;

    @BeforeEach
    public void setUp(){
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(10));
        cars.add(new Car());
        cars.add(new Car());
        smartParkingBoy = new SmartParkingBoy(parkingLots);
    }

    @Test
    public void shout_return_car_when_call_fetch_given_hasTicket_with_smartBoy(){
        for (int i = 0; i < 10; i++) smartParkingBoy.park(new Car());
        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);

        Assertions.assertSame(4, smartParkingBoy.getParkingLots().get(0).getEmpty());

        Car fetch = smartParkingBoy.fetch(ticket);

        Assertions.assertSame(car,fetch);
        Assertions.assertSame(5, smartParkingBoy.getParkingLots().get(0).getEmpty());
    }
}
