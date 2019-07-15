package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingStoryTwoTest {
    @Test
    public void shout_return_error_message_when_call_fetch_given_usedTicket_or_wrongTicket(){
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        BasicParkingBoy basicParkingBoy = new BasicParkingBoy(parkingLots);
        Car car = new Car();
        Ticket ticket = basicParkingBoy.park(car);
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
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        BasicParkingBoy basicParkingBoy = new BasicParkingBoy(parkingLots);
        basicParkingBoy.fetch(null);

        Assertions.assertEquals("Please provide your parking ticket.", basicParkingBoy.getErrorMessage());
    }

    @Test
    public void shout_return_error_message_when_call_fetch_given_hasTicket_whit_limited_Lot(){
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        BasicParkingBoy basicParkingBoy = new BasicParkingBoy(parkingLots);
        for (int i = 0; i < 10; i++) basicParkingBoy.park(new Car());
        basicParkingBoy.park(new Car());

        Assertions.assertEquals("Not enough position.", basicParkingBoy.getErrorMessage());
    }
}
