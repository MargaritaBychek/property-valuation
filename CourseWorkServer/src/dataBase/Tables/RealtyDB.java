package dataBase.Tables;

import model.Realty;

import java.util.ArrayList;

public class RealtyDB implements IRealty {
    private static RealtyDB instance;
    private ConnectionDB dbConnection;
    private RealtyDB(){dbConnection= ConnectionDB.getInstance();}
    public static synchronized RealtyDB getInstance() {
        if (instance == null) {
            instance = new RealtyDB();
        }
        return instance;
    }
    public int findMyRealty(String Number)
    {
        String str="SELECT * From  realty WHERE registration_No='"+Number+"'";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        int id=0;
        for (String[] items: result) {
        id=Integer.parseInt(items[1]);
        }
        return id;
    }
    public Realty findRealty(String Number)
    {
        String str="SELECT * From  realty WHERE registration_No='"+Number+"'";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        int id=0;
        Realty realty=new Realty();
        for (String[] items: result) {
            realty.setRegistration_No(items[0]);
            realty.setIDOwner(Integer.parseInt(items[1]));
            realty.setType(items[2]);
            realty.setState(items[3]);
            realty.setStateCoefficient(Double.parseDouble(items[4]));
            realty.setCity(items[5]);
            realty.setAddress(items[6]);
            realty.setRooms(Integer.parseInt(items[7]));
            realty.setSquareFloor(Double.parseDouble(items[8]));
            realty.setHighCeil(Double.parseDouble(items[9]));
            realty.setThickWall(Double.parseDouble(items[10]));
            realty.setMaterial(items[11]);
            realty.setMaterialCoefficient(Double.parseDouble(items[12]));
            realty.setAge(Integer.parseInt(items[13]));
        }
        return realty;
    }
    public ArrayList<Realty> selectAllRealty()
    {
        String str = "SELECT * From  realty";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Realty> listRealty = new ArrayList<>();

        for (String[] items: result){
            Realty realty=new Realty();
            realty.setRegistration_No(items[0]);
            realty.setIDOwner(Integer.parseInt(items[1]));
            realty.setType(items[2]);
            realty.setState(items[3]);
            realty.setStateCoefficient(Double.parseDouble(items[4]));
            realty.setCity(items[5]);
            realty.setAddress(items[6]);
            realty.setRooms(Integer.parseInt(items[7]));
            realty.setSquareFloor(Double.parseDouble(items[8]));
            realty.setHighCeil(Double.parseDouble(items[9]));
            realty.setThickWall(Double.parseDouble(items[10]));
            realty.setMaterial(items[11]);
            realty.setMaterialCoefficient(Double.parseDouble(items[12]));
            realty.setAge(Integer.parseInt(items[13]));
            listRealty.add(realty);
        }
        return listRealty;
    }

    public ArrayList<Realty> selectMyRealty(int id)
    {
        String str = "SELECT * From  realty WHERE id_owner="+id;
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Realty> listRealty = new ArrayList<>();
        for (String[] items: result){
            Realty realty=new Realty();
            realty.setRegistration_No(items[0]);
            realty.setIDOwner(Integer.parseInt(items[1]));
            realty.setType(items[2]);
            realty.setState(items[3]);
            realty.setStateCoefficient(Double.parseDouble(items[4]));
            realty.setCity(items[5]);
            realty.setAddress(items[6]);
            realty.setRooms(Integer.parseInt(items[7]));
            realty.setSquareFloor(Double.parseDouble(items[8]));
            realty.setHighCeil(Double.parseDouble(items[9]));
            realty.setThickWall(Double.parseDouble(items[10]));
            realty.setMaterial(items[11]);
            realty.setMaterialCoefficient(Double.parseDouble(items[12]));
            realty.setAge(Integer.parseInt(items[13]));
            listRealty.add(realty);
        }
        return listRealty;
    }
    public void insertRealty(Realty obj)
    {
        String insert="INSERT INTO realty (registration_No,id_owner,type,state,stateCoefficient,city,address,rooms,squareFloor,highCeil,thickWall,material,materialCoefficient,age) " +
                "VALUES('"+
                obj.getRegistration_No()+"','"+obj.getIDOwner()+"','"+
                obj.getType()+"','"+
                obj.getState()+"','"+obj.getStateCoefficient()+"','"+obj.getCity()+"','"+obj.getAddress()+"','"+
                obj.getRooms()+"','"+
                obj.getSquareFloor()+"','"+obj.getHighCeil()+"','"+
                obj.getThickWall()+"','"+obj.getMaterial()+"','"+obj.getMaterialCoefficient()+"','"+
                obj.getAge()+"')";
        dbConnection.execute(insert);
    }
    public void updateRealty(Realty realty,String id)
    {
      String str="UPDATE realty SET " +
              "realty.registration_No='" +realty.getRegistration_No()+
              "',realty.state='" +realty.getState()+
              "',realty.stateCoefficient='"+realty.getStateCoefficient()+
              "',realty.city='" +realty.getCity()+
              "',realty.address='" +realty.getAddress()+
              "',realty.rooms='" +realty.getRooms()+
              "',realty.squareFloor='" +realty.getSquareFloor()+
              "',realty.highCeil='" +realty.getHighCeil()+
              "',realty.thickWall='" +realty.getThickWall()+
              "',realty.material='" +realty.getMaterial()+
              "',realty.materialCoefficient='" +realty.getMaterialCoefficient()+
              "',realty.age='"+realty.getAge() +"' " +
            " WHERE realty.registration_No='"+id+"'";
        dbConnection.execute(str);
    }
    public void deleteRealty(String number)
    {
        String delete="DELETE FROM realty WHERE registration_No='"+number+"'";
        dbConnection.execute(delete);
    }
}
