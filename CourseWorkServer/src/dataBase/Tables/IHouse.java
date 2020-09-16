package dataBase.Tables;

import model.House;

import java.util.ArrayList;

public interface IHouse {
    public House findHouse(String Number);
    public void insertHouse(House obj);
    public void updateHouse(House obj,String id);
    public void deleteHouse(String number);
}
