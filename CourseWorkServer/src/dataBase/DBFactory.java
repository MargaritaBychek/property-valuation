package dataBase;

import dataBase.Tables.*;


public class DBFactory extends AbstractFactory{
    public UsersDB getUsers() {
        return UsersDB.getInstance();
    }
    public AppraiserDB getAppraiser(){return AppraiserDB.getInstance();}
    public CustomerDB getCustomer(){return CustomerDB.getInstance();}
    public CommissionDB getCommission(){ return CommissionDB.getInstance();}
    public RealtyDB getRealty(){return  RealtyDB.getInstance();}
    public FlatDB getFlat(){return FlatDB.getInstance();}
    public HouseDB getHouse(){return HouseDB.getInstance();}
    public OfficeDB getOffice(){return OfficeDB.getInstance();}
}
