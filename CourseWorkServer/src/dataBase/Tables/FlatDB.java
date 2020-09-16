package dataBase.Tables;

import model.Flat;

import java.util.ArrayList;

public class FlatDB implements IFlat {
    private static FlatDB instance;
    private ConnectionDB dbConnection;
    private FlatDB(){dbConnection= ConnectionDB.getInstance();}
    public static synchronized FlatDB getInstance() {
        if (instance == null) {
            instance = new FlatDB();
        }
        return instance;
    }

    public Flat findFlat(String Number) {
        String str="SELECT * From  flat WHERE registration_No='"+Number+"'";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Flat flat=new Flat();
        for (String[] items: result) {
            flat.setRegistration_No(items[0]);
            if(Integer.parseInt(items[1])==1)
                flat.setLast_FirstFloor(true);
            if(Integer.parseInt(items[2])==1)
                flat.setCorner(true);
            if(Integer.parseInt(items[3])==1)
                flat.setRepair(true);
            if(Integer.parseInt(items[4])==1)
                flat.setLoggia(true);
            if(Integer.parseInt(items[5])==1)
                flat.setGreenZone(true);
            if(Integer.parseInt(items[6])==1)
                flat.setDormitoryArea(true);
        }
        return flat;
    }
    public void insertFlat(Flat obj)
    {
        int i=0,k=0,l=0,m=0,n=0,o=0;
        if(obj.isLast_FirstFloor())
            i=1;
        if(obj.isCorner())
            k=1;
        if(obj.isRepair())
            l=1;
        if(obj.isLoggia())
            m=1;
        if(obj.isGreenZone())
            n=1;
        if(obj.isDormitoryArea())
            o=1;
        String insert="INSERT INTO flat" +
                "(registration_No,isLast_FirstFloor,isCorner, isRepair, isLoggia,isGreenZone, isDormitoryArea) " +
                "VALUES('"+
                obj.getRegistration_No()+"','"+i+"','"+k+
                "','"+ l+"','"+m +"','"+n+"','"+o +"')";
        dbConnection.execute(insert);
    }
    public void updateFlat(Flat flat,String id) {
        int i=0,k=0,l=0,m=0,n=0,o=0;
        if( flat.isLast_FirstFloor())
            i=1;
        if( flat.isCorner())
            k=1;
        if( flat.isRepair())
            l=1;
        if( flat.isLoggia())
            m=1;
        if( flat.isGreenZone())
            n=1;
        if( flat.isDormitoryArea())
            o=1;
        String str = "UPDATE flat SET " +
                "flat.registration_No='"+flat.getRegistration_No()+
                "',flat.flat.isLast_FirstFloor='" +i+
                "',flat.isCorner='"+k+
                "', flat.isRepair='" +l+
                "', flat.isLoggia='" +m+
                "',flat.isGreenZone='" +n+
                "', flat.isDormitoryArea='"+o+"'"+
                " WHERE flat.registration_No='"+id+"'";
        dbConnection.execute(str);
    }
    public void deleteFlat(String number)
    {
        String delete="DELETE FROM flat WHERE registration_No='"+number+"'";
        dbConnection.execute(delete);
    }
}
