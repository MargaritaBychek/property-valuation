package dataBase.Tables;

import model.Office;

import java.util.ArrayList;

public class OfficeDB implements IOffice {
    private static OfficeDB instance;
    private ConnectionDB dbConnection;
    private OfficeDB(){dbConnection= ConnectionDB.getInstance();}
    public static synchronized OfficeDB getInstance() {
        if (instance == null) {
            instance = new OfficeDB();
        }
        return instance;
    }
    public Office findOffice(String Number) {
        String str="SELECT * From  office WHERE registration_No='"+Number+"'";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Office office=new Office();
        for (String[] items: result) {
            office.setRegistration_No(items[0]);
            office.setMetresToRoad(Integer.parseInt(items[1]));
            office.setMetroStation(items[2]);
            office.setBusStation(items[3]);
            if(Integer.parseInt(items[4])==1)
                office.setLoft(true);
            if(Integer.parseInt(items[5])==1)
                office.setParking(true);
            if(Integer.parseInt(items[6])==1)
                office.setCenter(true);
        }
        return office;
    }
    public void insertOffice(Office obj)
    {
        int i=0,k=0,l=0;
        if(obj.isLoft())
            i=1;
        if(obj.isParking())
            k=1;
        if(obj.isCenter())
            l=1;
        String insert="INSERT INTO office" +
                "(registration_No,metresToRoad,metroStation, busStation, isLoft,isParking, isCenter) " +
                "VALUES('"+
                obj.getRegistration_No()+"','"+obj.getMetresToRoad()+"','"+
                obj.getMetroStation()+"','"+ obj.getBusStation()+"','"+i +
                "','"+k+"','"+l + "')";
        dbConnection.execute(insert);
    }
    public void updateOffice(Office obj,String id) {
        int i=0,k=0,l=0;
        if(obj.isLoft())
            i=1;
        if(obj.isParking())
            k=1;
        if(obj.isCenter())
            l=1;
        String str = "UPDATE office SET " +
                "office.registration_No='"+obj.getRegistration_No()+
                "',office.metresToRoad='"+obj.getMetresToRoad()+
                "',office.metroStation='"+obj.getMetroStation()+
                "',office.busStation='"+obj.getBusStation()+
                "',office.isLoft='"+i+
                "',office.isParking='"+k+
                "',office.isCenter='"+l+"'"+
                " WHERE office.registration_No='"+id+"'";
        dbConnection.execute(str);
    }
    public void deleteOffice(String number)
    {
        String delete="DELETE FROM office WHERE registration_No='"+number+"'";
        dbConnection.execute(delete);
    }
}
