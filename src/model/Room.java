package model;

import java.util.Objects;

public class Room implements IRoom {
    private String roomNumber;
    private Double price;
    private RoomType enumerations;

    public Room(String roomNumber,Double price, RoomType enumerations) {
        super();
        if (roomNumber == null){
            System.out.println("The Room number not existing");
        }
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumerations = enumerations;
    }

    @Override
    public String getRoomNumber() {
        // TODO Auto-generated method stub
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return null;
    }

    @Override
    public RoomType getRoomType() {
        return null;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomNumber, room.roomNumber) && Objects.equals(price, room.price) && enumerations == room.enumerations;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomNumber(), getRoomPrice(), getRoomType());
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }


    public void setPrice(Double price) {
        this.price = price;
    }


    public void setEnumerations(RoomType enumerations) {
        this.enumerations = enumerations;
    }
}
