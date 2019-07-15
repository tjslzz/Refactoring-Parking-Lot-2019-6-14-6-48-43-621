package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<Ticket,Car> parkingLot = new HashMap<>();
    private Integer capacity;
    private String errorMessage;

    public ParkingLot(Integer capacity) { this.capacity = capacity;}

    public Ticket park(Car car){
        if(parkingLot.containsValue(car) || car == null) return null;
        else{
            Ticket ticket = new Ticket();
            parkingLot.put(ticket,car);
            return ticket;
        }
    }

    public Car fetch(Ticket ticket){
        Car car = parkingLot.remove(ticket);
        if(car == null)errorMessage = "Unrecognized parking ticket.";
        return car;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Integer getCapacity() { return capacity; }

    public Map<Ticket, Car> getParkingLot() { return parkingLot; }

    public Integer getEmpty(){return capacity - parkingLot.size();}
}
