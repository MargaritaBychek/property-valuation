package dataBase.Tables;

import model.Flat;

import java.util.ArrayList;

public interface IFlat {
    public Flat findFlat(String Number);
    public void insertFlat(Flat obj);
    public void updateFlat(Flat flat,String id);
    public void deleteFlat(String number);
}
