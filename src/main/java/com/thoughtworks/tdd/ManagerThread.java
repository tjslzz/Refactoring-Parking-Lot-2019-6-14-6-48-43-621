package com.thoughtworks.tdd;

import java.util.concurrent.Callable;

public class ManagerThread implements Callable<String> {
    private ParkingBoy parkingBoy;
    private Ticket ticket;

    public ManagerThread(ParkingBoy parkingBoy, Ticket ticket) {
        this.parkingBoy = parkingBoy;
        this.ticket = ticket;
    }

    @Override
    public String call(){
        if(parkingBoy.getSemaphore().availablePermits() == 0) {return"sorry, Boy busy";}
        else parkingBoy.fetch(ticket);return "ok";
    }
}
