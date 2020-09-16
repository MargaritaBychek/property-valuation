package dataBase.Tables;

import model.Appraiser;

import java.util.ArrayList;

public class AppraiserDB implements IAppraiser {
    private static AppraiserDB instance;
    private ConnectionDB dbConnection;
    private AppraiserDB(){dbConnection= ConnectionDB.getInstance();}
    public static synchronized AppraiserDB getInstance() {
        if (instance == null) {
            instance = new AppraiserDB();
        }
        return instance;
    }
    public void insertAppraiser(Appraiser obj)
    {
        String insert="INSERT INTO appraiser" +
                "(id_appraiser,licence_No,popularity,amount) VALUES('"+
                obj.getIdAppraiser()+"','"+obj.getNo_Licence()+"',0,0)";
        dbConnection.execute(insert);
    }
    public Appraiser findAppraiser(int id)
    {
        String find = "SELECT * From appraiser Where id_appraiser = " + id;
        ArrayList<String[]> result = dbConnection.getArrayResult(find);
        Appraiser appraiser = new Appraiser();
        for (String[] items: result)
        {

            appraiser.setIdAppraiser(Integer.parseInt(items[0]));
            appraiser.setNo_Licence(items[1]);
            appraiser.setPopularity(Integer.parseInt(items[2]));
            appraiser.setAmount(Integer.parseInt(items[3]));
        }
        return appraiser;
    }
    public void updateAppraiser(Appraiser obj)
    {
        String str = "UPDATE appraiser SET " +
                "appraiser.licence_No='"+obj.getNo_Licence()+"'"+
                " WHERE id_appraiser= "+obj.getIdAppraiser();
        dbConnection.execute(str);
    }
    public void vote(Appraiser obj,int mark)
    {
        int m=obj.getPopularity();
        int n=obj.getAmount();
        int prev=m*n;
        n++;
        m=(prev+mark)/n;
        String str = "UPDATE appraiser SET " +
                "appraiser.popularity='"+m+"'"+
                ",appraiser.amount='"+n+"'"+
                " WHERE id_appraiser= "+obj.getIdAppraiser();
        dbConnection.execute(str);
    }
    public void deleteAppraiser(int number)
    {
        String delete="DELETE FROM appraiser WHERE id_appraiser="+number;
        dbConnection.execute(delete);
    }
}
