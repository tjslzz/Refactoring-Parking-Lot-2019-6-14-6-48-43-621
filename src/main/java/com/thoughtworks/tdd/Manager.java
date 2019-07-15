package com.thoughtworks.tdd;

import java.util.List;

public class Manager extends BasicParkingBoy {
    private List<ParkingBoy> ParkingBoys;

    public Manager(List<ParkingLot> parkingLots,List<ParkingBoy> ParkingBoys) { super(parkingLots);this.ParkingBoys = ParkingBoys; }

    public ParkingBoy chooseParkingBoy(Integer index){
        return ParkingBoys.get(index);
    }

    public ManagerThread  command(Ticket ticket,ParkingBoy parkingBoy){
        return new ManagerThread(parkingBoy,ticket);
    }
}
