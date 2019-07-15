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
    public String call(){ return parkingBoy.getSemaphore().availablePermits() == 0?"sorry, Boy busy":"ok"; }

    public Ticket getTicket() { return ticket; }
}
