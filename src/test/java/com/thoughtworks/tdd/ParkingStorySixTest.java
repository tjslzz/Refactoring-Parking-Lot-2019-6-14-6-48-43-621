package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ParkingStorySixTest {

    @Test
    public void shout_return_car_when_call_fetch_given_hasTicket_on_manager_choose_parkingBoy(){
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        List<ParkingBoy> ParkingBoys = new ArrayList<>();
        ParkingBoys.add(new BasicParkingBoy(parkingLots));
        ParkingBoys.add(new SmartParkingBoy(parkingLots));
        ParkingBoys.add(new SuperSmartParkingBoy(parkingLots));
        Manager manager = new Manager(parkingLots, ParkingBoys);
        Car car = new Car();
        Ticket ticket = manager.chooseParkingBoy(0).park(car);
        Car fetch = manager.chooseParkingBoy(0).fetch(ticket);

        Assertions.assertSame(car,fetch);
    }

    @Test
    public void shout_return_car_when_call_fetch_given_hasTicket_on_manager(){
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        Manager manager = new Manager(parkingLots,new ArrayList<>());
        Car car = new Car();
        Ticket ticket = manager.park(car);
        Car fetch = manager.fetch(ticket);

        Assertions.assertSame(car,fetch);
    }

    @Test
    public void shout_return_message_when_call_fetch_given_command_but_boy_not() throws ExecutionException, InterruptedException {
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        List<ParkingBoy> ParkingBoys = new ArrayList<>();
        ParkingBoys.add(new BasicParkingBoy(parkingLots));
        ParkingBoys.add(new SmartParkingBoy(parkingLots));
        ParkingBoys.add(new SuperSmartParkingBoy(parkingLots));
        Manager manager = new Manager(parkingLots, ParkingBoys);
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingBoy parkingBoy = manager.chooseParkingBoy(0);
        Ticket ticket1 = parkingBoy.park(car1);
        Ticket ticket2 = parkingBoy.park(car2);

        ManagerThread managerThread1 = manager.command(ticket1,parkingBoy);
        ManagerThread managerThread2 = manager.command(ticket2,parkingBoy);
        FutureTask<String> task1 = new FutureTask<>(managerThread1);
        FutureTask<String> task2 = new FutureTask<>(managerThread2);
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        thread1.start();
        thread2.start();
        task1.get();

        Assertions.assertEquals("sorry, Boy busy",task2.get());
    }
}
