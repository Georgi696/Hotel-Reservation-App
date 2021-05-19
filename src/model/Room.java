package model;

import java.util.Objects;

public class Room implements IRoom {
    private String roomNumber;
    private Double price;
    private RoomType roomType;

    public Room(String roomNumber,Double price, RoomType roomType) {
        super();
        if (roomNumber == null){
            System.out.println("The Room number not existing");
        }
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    @Override
    public String getRoomNumber() {
        // TODO Auto-generated method stub
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
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
        return Objects.equals(roomNumber, room.roomNumber) && Objects.equals(price, room.price) && roomType == room.roomType;
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


    public void setEnumerations(RoomType roomType) {
        this.roomType = roomType;
    }
}
