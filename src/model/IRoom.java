package model;

public interface IRoom {
    String getRoomNumber();
    Double getRoomPrice();
    RoomType getRoomType();
    default boolean isFree(){
        return false;
    }
}
