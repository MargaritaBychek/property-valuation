package dataBase.Tables;

import model.House;

import java.util.ArrayList;

public class HouseDB implements IHouse {
    private static HouseDB instance;
    private ConnectionDB dbConnection;
    private HouseDB(){dbConnection= ConnectionDB.getInstance();}
    public static synchronized HouseDB getInstance() {
        if (instance == null) {
            instance = new HouseDB();
        }
        return instance;
    }
    public House findHouse(String Number) {
        String str="SELECT * From  house WHERE registration_No='"+Number+"'";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        House house=new House();
        for (String[] items: result) {
            house.setRegistration_No(items[0]);
            house.setNumberFloors(Integer.parseInt(items[1]));
            house.setSquareLand(Double.parseDouble(items[2]));
            if(Integer.parseInt(items[3])==1)
                house.setCHS(true);
            if(Integer.parseInt(items[4])==1)
                house.setAqueduct(true);
        }
        return house;
    }
    public void insertHouse(House obj)
    {
        int i=0,k=0;
        if(obj.isCHS())
            i=1;
        if(obj.isAqueduct())
            k=1;
        String insert="INSERT INTO house" +
                "(registration_No,numberFloors,squareLand, isCHS,isAqueduct)"+
                "VALUES('"+
                obj.getRegistration_No()+"','"+obj.getNumberFloors()+"','"+
                obj.getSquareLand()+"','"+
                i +"','"+k+"')";
        dbConnection.execute(insert);
    }
    public void updateHouse(House obj,String id) {
        int i=0,k=0;
        if(obj.isCHS())
            i=1;
        if(obj.isAqueduct())
            k=1;
        String str = "UPDATE house SET " +
                "house.registration_No='"+obj.getRegistration_No()+
                "',house.numberFloors='"+obj.getNumberFloors()+
                "',house.squareLand='"+obj.getSquareLand()+
                "',house.isCHS='"+i+
                "',house.isAqueduct='"+k+"'"+
                " WHERE house.registration_No='"+id+"'";
        dbConnection.execute(str);
    }
    public void deleteHouse(String number)
    {
        String delete="DELETE FROM house WHERE registration_No='"+number+"'";
        dbConnection.execute(delete);
    }
}
