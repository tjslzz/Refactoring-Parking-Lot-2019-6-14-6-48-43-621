package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<Ticket,Car> parkingLot = new HashMap<>();
    private Integer capacity;

    public ParkingLot(Integer capacity) { this.capacity = capacity;}

    public Ticket storage(Car car){
        if(parkingLot.containsValue(car) || car == null) return null;
        else{
            Ticket ticket = new Ticket();
            parkingLot.put(ticket,car);
            return ticket;
        }
    }

    public Car outOfLibrary(Ticket ticket){ return parkingLot.remove(ticket); }

    public Integer getCapacity() { return capacity; }

    public Map<Ticket, Car> getParkingLot() { return parkingLot; }

    public Integer getEmpty(){return capacity - parkingLot.size();}
}
