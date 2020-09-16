package dataBase.Tables;
import model.Commission;

import java.util.ArrayList;

public class CommissionDB implements ICommission {
    private static CommissionDB instance;
    private ConnectionDB dbConnection;
    private CommissionDB(){dbConnection= ConnectionDB.getInstance();}
    public static synchronized CommissionDB getInstance() {
        if (instance == null) {
            instance = new CommissionDB();
        }
        return instance;
    }

    public ArrayList<Commission> selectCommission()
    {
        String str = "SELECT * From commission ";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Commission> listCommission = new ArrayList<>();

        for (String[] items: result){
            Commission commission= new Commission();
            commission.setIdCommission(Integer.parseInt(items[0]));
            commission.setDate(items[1]);
            commission.setTime(items[2]);
            commission.setId_appraiser(Integer.parseInt(items[3]));
            commission.setRegistration_No(items[4]);
            commission.setStatus(items[5]);
            listCommission.add(commission);
        }
        return listCommission;
    }
    public ArrayList<Commission> selectMyCommission(int id)
    {
        String str = "SELECT * From commission WHERE commission.id_appraiser="+id;
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Commission> listCommission = new ArrayList<>();

        for (String[] items: result){
            Commission commission= new Commission();
            commission.setIdCommission(Integer.parseInt(items[0]));
            commission.setDate(items[1]);
            commission.setTime(items[2]);
            commission.setId_appraiser(Integer.parseInt(items[3]));
            commission.setRegistration_No(items[4]);
            commission.setStatus(items[5]);
            listCommission.add(commission);
        }
        return listCommission;
    }
    public ArrayList<Commission> selectAcceptCommission(int id)
    {
        String str = "SELECT * From commission WHERE commission.id_appraiser="+id+" and commission.status='Принята'";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Commission> listCommission = new ArrayList<>();

        for (String[] items: result){
            Commission commission= new Commission();
            commission.setIdCommission(Integer.parseInt(items[0]));
            commission.setDate(items[1]);
            commission.setTime(items[2]);
            commission.setId_appraiser(Integer.parseInt(items[3]));
            commission.setRegistration_No(items[4]);
            commission.setStatus(items[5]);
            listCommission.add(commission);
        }
        return listCommission;
    }
    public Commission findCommission(int id)
    {
        String str = "SELECT * From commission WHERE commission.id_commission="+id;
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
       Commission commission=new Commission();
        for (String[] items: result){
           commission.setIdCommission(Integer.parseInt(items[0]));
            commission.setDate(items[1]);
            commission.setTime(items[2]);
            commission.setId_appraiser(Integer.parseInt(items[3]));
            commission.setRegistration_No(items[4]);
            commission.setStatus(items[5]);
            if(Integer.parseInt(items[6])==1)
            commission.setVoted(true);
            else
                commission.setVoted(false);
        }
        return commission;
    }
    public void Voted(int id)
    {
        String str = "UPDATE commission SET commission.isVoted="+ 1+
                " WHERE commission.id_commission="+id;
        dbConnection.execute(str);
    }
    public void changeStatusCommission(Commission commission)
    {
        String str = "UPDATE commission SET commission.status='"+ commission.getStatus()+
                "' WHERE commission.id_commission="+commission.getIdCommission();
        dbConnection.execute(str);
    }

    public void endCommission(Commission commission)
    {
        String str = "UPDATE commission SET commission.status='Завершена' " +
                "WHERE commission.id_commission="+commission.getIdCommission();
        dbConnection.execute(str);
    }
    public void insertCommission(Commission obj)
    {
        String insert="INSERT INTO commission" +
                "(id_commission,date, time, id_appraiser, registration_No,status) VALUES(null,'"+
                obj.getDate()+"','"+obj.getTime()+"','"+
                obj.getId_appraiser()+"','"+obj.getRegistration_No()+"','"+obj.getStatus()+"')";
        dbConnection.execute(insert);
    }
    public void updateCommission(Commission obj)
    {
        String str = "UPDATE commission SET " +
                "commission.date='" +obj.getDate()+
                "',commission.time='" +obj.getTime()+
                "',commission.id_appraiser='" +obj.getId_appraiser()+
                "',commission.registration_No='"+obj.getRegistration_No()+
                "',commission.status='Отправлена'"+
                " WHERE id_commission= "+obj.getIdCommission();
        dbConnection.execute(str);
    }
    public void deleteCommission(int id)
    {
        String delete="DELETE FROM commission WHERE id_commission='"+id+"'";
        dbConnection.execute(delete);
    }
    public void deleteCommissionRealty(String number)
    {
        String delete="DELETE FROM commission WHERE registration_No='"+number+"'";
        dbConnection.execute(delete);
    }
}
