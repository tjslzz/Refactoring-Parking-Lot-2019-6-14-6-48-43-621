package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingStoryOneTest {

    private List<ParkingLot> parkingLots = new ArrayList<>();
    private List<Car> cars = new ArrayList<>();
    private BasicParkingBoy basicParkingBoy;

    @BeforeEach
    public void setUp(){
        parkingLots.add(new ParkingLot(10));
        cars.add(new Car());
        cars.add(new Car());
        basicParkingBoy = new BasicParkingBoy(parkingLots);
    }

    @Test
    public void shout_return_car_when_call_fetch_given_hasTicket(){
        Ticket ticket = basicParkingBoy.park(cars.get(0));
        Car fetch = basicParkingBoy.fetch(ticket);

        Assertions.assertSame(cars.get(0),fetch);
    }

    @Test
    public void shout_return_car_when_call_fetch_given_correctTicket_with_manyCars(){
        BasicParkingBoy basicParkingBoy = new BasicParkingBoy(parkingLots);
        basicParkingBoy.park(cars.get(0));
        Ticket ticket2 = basicParkingBoy.park(cars.get(1));
        Car fetch = basicParkingBoy.fetch(ticket2);

        Assertions.assertSame(cars.get(1),fetch);
    }

    @Test
    public void shout_return_null_when_call_fetch_given_wrongTicket_or_NoTicket(){
        BasicParkingBoy basicParkingBoy = new BasicParkingBoy(parkingLots);
        basicParkingBoy.park(cars.get(0));
        basicParkingBoy.park(cars.get(1));
        Car fetch1 = basicParkingBoy.fetch(new Ticket());
        Car fetch2 = basicParkingBoy.fetch(null);

        Assertions.assertSame(null,fetch1);
        Assertions.assertSame(null,fetch2);
    }

    @Test
    public void shout_return_null_when_call_fetch_given_usedTicket(){
        BasicParkingBoy basicParkingBoy = new BasicParkingBoy(parkingLots);
        Ticket ticket1 = basicParkingBoy.park(cars.get(0));
        basicParkingBoy.park(cars.get(1));
        basicParkingBoy.fetch(ticket1);
        Car fetch = basicParkingBoy.fetch(ticket1);

        Assertions.assertSame(null,fetch);
    }

    @Test
    public void shout_return_null_when_call_fetch_given_hasTicket_whit_limited_Lot(){
        BasicParkingBoy basicParkingBoy = new BasicParkingBoy(parkingLots);
        for (int i = 0; i < 10; i++) basicParkingBoy.park(new Car());
        Ticket ticket = basicParkingBoy.park(new Car());

        Assertions.assertSame(null,ticket);
    }

    @Test
    public void shout_return_null_when_call_fetch_given_nullCar_or_parkedCar(){
        BasicParkingBoy basicParkingBoy = new BasicParkingBoy(parkingLots);
        basicParkingBoy.park(cars.get(0));
        Ticket ticket1 = basicParkingBoy.park(cars.get(0));
        Ticket ticket2 = basicParkingBoy.park(null);

        Assertions.assertSame(null,ticket1);
        Assertions.assertSame(null,ticket2);
    }
}
