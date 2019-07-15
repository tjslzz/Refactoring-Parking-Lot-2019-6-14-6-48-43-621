package com.thoughtworks.tdd;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

public abstract class ParkingBoy {
    protected List<ParkingLot> parkingLots;
    protected String errorMessage;
    private final Semaphore semaphore;

    public ParkingBoy(List<ParkingLot> parkingLots) { this.parkingLots = parkingLots;semaphore = new Semaphore(1);}

    public abstract Ticket park(Car car);

    public Car fetch(Ticket ticket){
        try{
            semaphore.acquire();
            if(ticket == null){ this.errorMessage = "Please provide your parking ticket.";return null; }
            else {
                ParkingLot parkingLot = parkingLots.stream().filter(p ->p.getParkingLot().containsKey(ticket)).collect(Collectors.toList()).get(0);
                Car car = parkingLot.fetch(ticket);
                this.errorMessage = parkingLot.getErrorMessage();
                Thread.sleep(1000);
                return car;
            }
        }
        catch (Exception e){ this.errorMessage = "Unrecognized parking ticket.";return null; }
        finally { semaphore.release(); }
    }

    public Ticket lotPark(List<ParkingLot> parkingLots,Car car){
        try{
            ParkingLot parkingLot = parkingLots.get(0);
            Ticket ticket = parkingLot.park(car);
            this.errorMessage = parkingLot.getErrorMessage();
            return ticket;
        }
        catch (Exception e){ this.errorMessage = "Not enough position.";return null; }
    }

    public Ticket lotPark(ParkingLot parkingLot,Car car){
        try{
            Ticket ticket = parkingLot.park(car);
            this.errorMessage = parkingLot.getErrorMessage();
            return ticket;
        }
        catch (Exception e){ this.errorMessage = "Not enough position.";return null; }
    }


    public String getErrorMessage() { return errorMessage; }

    public List<ParkingLot> getParkingLots() { return parkingLots; }

    public Semaphore getSemaphore() { return semaphore; }

}
