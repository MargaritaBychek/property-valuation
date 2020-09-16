package dataBase.Tables;

import model.Commission;

import java.util.ArrayList;

public interface ICommission {
    public ArrayList<Commission> selectCommission();
    public ArrayList<Commission> selectMyCommission(int id);
    public ArrayList<Commission> selectAcceptCommission(int id);
    public Commission findCommission(int id);
    public void Voted(int id);
    public void changeStatusCommission(Commission commission);
    public void endCommission(Commission commission);
    public void insertCommission(Commission obj);
    public void updateCommission(Commission obj);
    public void deleteCommission(int id);
    public void deleteCommissionRealty(String number);
}
