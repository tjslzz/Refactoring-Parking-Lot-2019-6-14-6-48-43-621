package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingStoryOneTest {
    @Test
    public void shout_return_car_when_call_fetch_given_hasTicket(){
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        Car car = new Car();
        BasicParkingBoy basicParkingBoy = new BasicParkingBoy(parkingLots);
        Ticket ticket = basicParkingBoy.park(car);
        Car fetch = basicParkingBoy.fetch(ticket);

        Assertions.assertSame(car,fetch);
    }

    @Test
    public void shout_return_car_when_call_fetch_given_correctTicket_with_manyCars(){
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        Car car1 = new Car();
        Car car2 = new Car();
        BasicParkingBoy basicParkingBoy = new BasicParkingBoy(parkingLots);
        basicParkingBoy.park(car1);
        Ticket ticket2 = basicParkingBoy.park(car2);
        Car fetch = basicParkingBoy.fetch(ticket2);

        Assertions.assertSame(car2,fetch);
    }

    @Test
    public void shout_return_null_when_call_fetch_given_wrongTicket_or_NoTicket(){
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        Car car1 = new Car();
        Car car2 = new Car();
        BasicParkingBoy basicParkingBoy = new BasicParkingBoy(parkingLots);
        basicParkingBoy.park(car1);
        basicParkingBoy.park(car2);
        Car fetch1 = basicParkingBoy.fetch(new Ticket());
        Car fetch2 = basicParkingBoy.fetch(null);

        Assertions.assertSame(null,fetch1);
        Assertions.assertSame(null,fetch2);
    }

    @Test
    public void shout_return_null_when_call_fetch_given_usedTicket(){
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        Car car1 = new Car();
        Car car2 = new Car();
        BasicParkingBoy basicParkingBoy = new BasicParkingBoy(parkingLots);
        Ticket ticket1 = basicParkingBoy.park(car1);
        basicParkingBoy.park(car2);
        basicParkingBoy.fetch(ticket1);
        Car fetch = basicParkingBoy.fetch(ticket1);

        Assertions.assertSame(null,fetch);
    }

    @Test
    public void shout_return_null_when_call_fetch_given_hasTicket_whit_limited_Lot(){
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        BasicParkingBoy basicParkingBoy = new BasicParkingBoy(parkingLots);
        for (int i = 0; i < 10; i++) basicParkingBoy.park(new Car());
        Ticket ticket = basicParkingBoy.park(new Car());

        Assertions.assertSame(null,ticket);
    }

    @Test
    public void shout_return_null_when_call_fetch_given_nullCar_or_parkedCar(){
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        BasicParkingBoy basicParkingBoy = new BasicParkingBoy(parkingLots);
        Car car = new Car();
        basicParkingBoy.park(car);
        Ticket ticket1 = basicParkingBoy.park(car);
        Ticket ticket2 = basicParkingBoy.park(null);

        Assertions.assertSame(null,ticket1);
        Assertions.assertSame(null,ticket2);
    }
}
