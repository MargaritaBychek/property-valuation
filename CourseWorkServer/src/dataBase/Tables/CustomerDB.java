package dataBase.Tables;

import model.Customer;

import java.util.ArrayList;

public class CustomerDB implements ICustomer {
    private static CustomerDB instance;
    private ConnectionDB dbConnection;
    private CustomerDB(){dbConnection=ConnectionDB.getInstance();}
    public static synchronized CustomerDB getInstance() {
        if (instance == null) {
            instance = new CustomerDB();
        }
        return instance;
    }
    public void insertCustomer(Customer obj)
    {
        String insert="INSERT INTO customer" +
                "(id_Customer,email,passport_series,passport_number) VALUES("+
                obj.getIdCustomer()+",'"+obj.getEmail()+"','"+
                obj.getPassportSeria()+"',"+obj.getPassportNo()+")";
        dbConnection.execute(insert);
    }
    public Customer findCustomer(int id)
    {
        String find = "SELECT * From customer Where id_customer = " + id;
        ArrayList<String[]> result = dbConnection.getArrayResult(find);
       Customer customer = new Customer();
        for (String[] items : result) {
            customer.setIdCustomer(Integer.parseInt(items[0]));
            customer.setEmail(items[1]);
            customer.setPassportSeria(items[2]);
            customer.setPassportNo(Integer.parseInt(items[3]));
        }
        return customer;
    }
    public void updateCustomer(Customer obj)
    {
        String str = "UPDATE customer SET " +
                "customer.email='" +obj.getEmail()+
                "',customer.passport_series='" +obj.getPassportSeria()+
                "',customer.passport_number='"+obj.getPassportNo()+"'"+
                " WHERE id_customer= "+obj.getIdCustomer();
        dbConnection.execute(str);
    }
    public void deleteCustomer(int number)
    {
        String delete="DELETE FROM customer WHERE id_customer="+number;
        dbConnection.execute(delete);
    }
}
