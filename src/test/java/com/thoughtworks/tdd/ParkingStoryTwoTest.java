package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingStoryTwoTest {

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
    public void shout_return_error_message_when_call_fetch_given_usedTicket_or_wrongTicket(){
        Ticket ticket = basicParkingBoy.park(cars.get(0));
        basicParkingBoy.fetch(ticket);
        Car fetch1 = basicParkingBoy.fetch(ticket);
        Assertions.assertEquals("Unrecognized parking ticket.", basicParkingBoy.getErrorMessage());

        Car fetch2 = basicParkingBoy.fetch(null);
        Assertions.assertSame(null,fetch1);
        Assertions.assertSame(null,fetch2);
        Assertions.assertEquals("Please provide your parking ticket.", basicParkingBoy.getErrorMessage());
    }

    @Test
    public void shout_return_error_message_when_call_fetch_given_nullTicket(){
        basicParkingBoy.fetch(null);

        Assertions.assertEquals("Please provide your parking ticket.", basicParkingBoy.getErrorMessage());
    }

    @Test
    public void shout_return_error_message_when_call_fetch_given_hasTicket_whit_limited_Lot(){
        for (int i = 0; i < 10; i++) basicParkingBoy.park(new Car());
        basicParkingBoy.park(new Car());

        Assertions.assertEquals("Not enough position.", basicParkingBoy.getErrorMessage());
    }
}
