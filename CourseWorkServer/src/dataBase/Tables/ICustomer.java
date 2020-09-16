package dataBase.Tables;

import model.Customer;

import java.util.ArrayList;

public interface ICustomer {
    public void insertCustomer(Customer obj);
    public Customer findCustomer(int id);
    public void updateCustomer(Customer obj);
    public void deleteCustomer(int number);
}
