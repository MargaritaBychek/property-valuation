package dataBase.Tables;

import model.Office;

import java.util.ArrayList;

public interface IOffice {
    public Office findOffice(String Number);
    public void insertOffice(Office obj);
    public void updateOffice(Office obj,String id);
    public void deleteOffice(String number);
}
