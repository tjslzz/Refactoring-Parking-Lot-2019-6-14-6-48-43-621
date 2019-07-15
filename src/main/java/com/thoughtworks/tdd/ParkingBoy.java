package com.thoughtworks.tdd;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

public abstract class ParkingBoy {
    protected List<ParkingLot> parkingLots;
    protected String errorMessage;
    private final Semaphore semaphore;
    private static final Long SLEEP_TIME = Long.valueOf(1000);

    public ParkingBoy(List<ParkingLot> parkingLots) { this.parkingLots = parkingLots;semaphore = new Semaphore(1);}

    public abstract Ticket park(Car car);

    public Car fetch(Ticket ticket){
        try{
            semaphore.acquire();
            return fetchCar(ticket);
        }
        catch (Exception e){ this.errorMessage = "Unrecognized parking ticket.";return null; }
        finally { semaphore.release(); }
    }

    public Ticket lotPark(ParkingLot parkingLot,Car car){
        try{ return parkCar(parkingLot,car); }
        catch (Exception e){ this.errorMessage = "Not enough position.";return null; }
    }


    public String getErrorMessage() { return errorMessage; }

    public List<ParkingLot> getParkingLots() { return parkingLots; }

    public Semaphore getSemaphore() { return semaphore; }

    private Car fetchCar(Ticket ticket) throws InterruptedException {
        if(ticket == null){ this.errorMessage = "Please provide your parking ticket.";return null; }
        else { return fetchCarForValidTicket(ticket); }
    }

    private Ticket parkCar(ParkingLot parkingLot,Car car){
        Ticket ticket = parkingLot.storage(car);
        return ticket;
    }

    private Car fetchCarForValidTicket(Ticket ticket) throws InterruptedException {
        ParkingLot parkingLot = parkingLots.stream().filter(p ->p.getParkingLot().containsKey(ticket)).collect(Collectors.toList()).get(0);
        Car car = parkingLot.outOfLibrary(ticket);
        if(car == null)this.errorMessage = "Unrecognized parking ticket.";
        Thread.sleep(SLEEP_TIME);
        return car;
    }
}
