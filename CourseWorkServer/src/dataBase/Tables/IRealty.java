package dataBase.Tables;

import model.Realty;

import java.util.ArrayList;

public interface IRealty {
    public int findMyRealty(String Number);
    public Realty findRealty(String Number);
    public ArrayList<Realty> selectAllRealty();
    public ArrayList<Realty> selectMyRealty(int id);
    public void insertRealty(Realty obj);
    public void updateRealty(Realty realty,String id);
    public void deleteRealty(String number);
}
