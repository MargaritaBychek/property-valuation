package dataBase;

import dataBase.Tables.*;

public abstract class AbstractFactory {
   public abstract UsersDB getUsers();
   public abstract AppraiserDB getAppraiser();
   public abstract CustomerDB getCustomer();
   public abstract CommissionDB getCommission();
   public abstract RealtyDB getRealty();
   public abstract FlatDB getFlat();
   public abstract HouseDB getHouse();
   public abstract OfficeDB getOffice();
}
