package server;

import dataBase.DBFactory;
import model.*;
import server.methods.Methods;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerWork implements Runnable{

    protected Socket clientAccepted = null;
    ObjectInputStream sois;
    ObjectOutputStream soos;

    public ServerWork(Socket clientSocket) {
        this.clientAccepted = clientSocket;
    }

    @Override
    public void run() {
        try {
            //создание потоков вв/выв
            sois = new ObjectInputStream(clientAccepted.getInputStream());
            soos = new ObjectOutputStream(clientAccepted.getOutputStream());
            try {
                while(true) {
                    String marker = sois.readObject().toString();
                    switch (marker) {
                        case "start": {
                            Users user = (Users) sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            Users userRes = dbFactory.getUsers().findUser(user);
                            if ((userRes.getIdUsers() == 0)&& (userRes.getRole() == 0))
                                soos.writeObject("error");
                            else {
                                soos.writeObject("ok");
                                soos.writeObject(userRes);
                            }
                        }break;
                        case "registerAppraiser": {
                            Users user = (Users) sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            if(dbFactory.getUsers().isLoginUnique(user.getLogin()))
                            {dbFactory.getUsers().insertUser(user);
                            int id = dbFactory.getUsers().findIDUser(user);
                            Appraiser appraiser = (Appraiser) sois.readObject();
                            appraiser.setIdAppraiser(id);
                            dbFactory.getAppraiser().insertAppraiser(appraiser);
                            soos.writeObject("ok");
                            soos.writeObject(id);}
                            else
                                soos.writeObject("error");
                        }break;
                        case "registerCustomer": {
                            Users user = (Users) sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            if(dbFactory.getUsers().isLoginUnique(user.getLogin()))
                           { dbFactory.getUsers().insertUser(user);
                            int id = dbFactory.getUsers().findIDUser(user);
                            Customer customer = (Customer) sois.readObject();
                            customer.setIdCustomer(id);
                            dbFactory.getCustomer().insertCustomer(customer);
                               soos.writeObject("ok");
                               soos.writeObject(id);}
                            else
                                soos.writeObject("error");
                        }break;
                        case "updateCustomer":{
                            Users users=(Users)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            dbFactory.getUsers().updateUser(users);
                            Customer customer=(Customer)sois.readObject();
                            dbFactory.getCustomer().updateCustomer(customer);
                            soos.writeObject("ok");
                        }break;
                        case "updateAppraiser":{
                            Users users=(Users)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            dbFactory.getUsers().updateUser(users);
                            Appraiser appraiser=(Appraiser)sois.readObject();
                            dbFactory.getAppraiser().updateAppraiser(appraiser);
                            soos.writeObject("ok");
                        }break;
                        case "updateAdmin":{
                            Users users=(Users)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            dbFactory.getUsers().updateUser(users);
                            soos.writeObject("ok");
                        }break;
                        case "deleteCustomer": {
                            int id = (int) sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            dbFactory.getUsers().deleteUser(id);
                            dbFactory.getCustomer().deleteCustomer(id);
                            soos.writeObject("ok");
                        }break;
                        case "deleteAppraiser": {
                            int id = (int) sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            dbFactory.getUsers().deleteUser(id);
                            dbFactory.getAppraiser().deleteAppraiser(id);
                            soos.writeObject("ok");
                        }break;
                        case "selectUsers": {
                            int role=(int)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            ArrayList<Users> list = dbFactory.getUsers().selectUsers(role);
                            soos.writeObject(list);
                        }break;
                        case "selectallUsers": {
                            DBFactory dbFactory = new DBFactory();
                            ArrayList<Users> list = dbFactory.getUsers().selectallUsers();
                            soos.writeObject(list);
                        }break;
                        case "changeRole": {
                            Users users=(Users)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            dbFactory.getUsers().changeRole(users);
                        }break;
                        case "findCustomer": {
                            int id=(int)sois.readObject();
                            DBFactory dbFactory=new DBFactory();
                            Customer customer=dbFactory.getCustomer().findCustomer(id);
                            soos.writeObject(customer);
                        }break;
                        case "findAppraiser": {
                            int id = (int) sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            Appraiser finding = dbFactory.getAppraiser().
                                    findAppraiser(id);
                            soos.writeObject(finding);
                        }  break;
                        case "vote": {
                            Commission commission = (Commission) sois.readObject();
                            int mark = (int) sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            Appraiser appraiser= dbFactory.getAppraiser().findAppraiser(commission.getId_appraiser());
                            dbFactory.getAppraiser().vote(appraiser,mark);
                            dbFactory.getCommission().Voted(commission.getIdCommission());
                            soos.writeObject("ok");
                        }break;
                        case "addCommission":{
                            Commission commission=(Commission)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            dbFactory.getCommission().insertCommission(commission);
                            soos.writeObject("ok");
                        }break;
                        case "updateCommission":{
                            Commission commission=(Commission)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            dbFactory.getCommission().updateCommission(commission);
                            soos.writeObject("ok");
                        }break;
                        case "deleteCommission":{
                          int id= (int) sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            dbFactory.getCommission().deleteCommission(id);
                            soos.writeObject("ok");
                        }break;
                        case "selectCommissions": {
                            DBFactory dbFactory = new DBFactory();
                            ArrayList<Commission> list1 = dbFactory.getCommission().selectCommission();
                            soos.writeObject(list1);
                        } break;
                        case "selectMyCommissions": {
                            int id=(int)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            ArrayList<Commission> list1 = dbFactory.getCommission().selectMyCommission(id);
                            soos.writeObject(list1);
                        } break;
                        case"moreDetailsCommission":{
                            Commission commission=(Commission)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            String appr=dbFactory.getUsers().findUserID(commission.getId_appraiser());
                            Realty realty=dbFactory.getRealty().findRealty(commission.getRegistration_No());
                            String own=dbFactory.getUsers().findUserID(realty.getIDOwner());
                            int id=commission.getIdCommission();
                            commission=dbFactory.getCommission().findCommission(id);
                            soos.writeObject(commission);
                            soos.writeObject(realty);
                            soos.writeObject(appr);
                            soos.writeObject(own);
                        }break;
                        case "selectAcceptCommissions": {
                            int id=(int)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            ArrayList<Commission> list1 = dbFactory.getCommission().selectAcceptCommission(id);
                            soos.writeObject(list1);
                        } break;
                        case"changeStatusCommission": {
                          Commission commission=(Commission)sois.readObject();
                          DBFactory dbFactory=new DBFactory();
                          dbFactory.getCommission().changeStatusCommission(commission);
                        }break;
                        case "endCommission": {
                            Commission commission=(Commission)sois.readObject();
                            DBFactory dbFactory=new DBFactory();
                            dbFactory.getCommission().endCommission(commission);
                        }break;
                        case "selectAllRealty": {
                            DBFactory dbFactory = new DBFactory();
                            ArrayList<Realty> list2 = dbFactory.getRealty().selectAllRealty();
                            soos.writeObject(list2);
                        }break;
                        case "selectMyRealty": {
                            int id=(int)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            ArrayList<Realty> list = dbFactory.getRealty().selectMyRealty(id);
                            soos.writeObject(list);
                        }break;
                        case "findMyRealty": {
                            String num=(String)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            int id=dbFactory.getRealty().findMyRealty(num);
                            soos.writeObject(id);
                        }break;
                        case"addFlat": {
                           Realty realty=(Realty)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            dbFactory.getRealty().insertRealty(realty);
                            String Number=realty.getRegistration_No();
                            Flat flat=(Flat)sois.readObject();
                            flat.setRegistration_No(Number);
                            dbFactory.getFlat().insertFlat(flat);
                            soos.writeObject("ok");
                        }break;
                        case"addHouse": {
                            Realty realty=(Realty)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            dbFactory.getRealty().insertRealty(realty);
                            String Number=realty.getRegistration_No();
                            House house=(House) sois.readObject();
                            house.setRegistration_No(Number);
                            dbFactory.getHouse().insertHouse(house);
                            soos.writeObject("ok");
                        }break;
                        case"addOffice": {
                            Realty realty=(Realty)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            dbFactory.getRealty().insertRealty(realty);
                            String Number=realty.getRegistration_No();
                            Office office=(Office)sois.readObject();
                            office.setRegistration_No(Number);
                            dbFactory.getOffice().insertOffice(office);
                            soos.writeObject("ok");
                        }break;
                        case"findFlat": {
                            String Number=(String)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            Flat finding=dbFactory.getFlat().findFlat(Number);
                            soos.writeObject(finding);
                        }break;
                        case"findHouse": {
                            String Number=(String)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            House finding=dbFactory.getHouse().findHouse(Number);
                            soos.writeObject(finding);
                        }break;
                        case"findOffice": {
                            String Number=(String)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            Office finding=dbFactory.getOffice().findOffice(Number);
                            soos.writeObject(finding);
                        }break;
                        case "updateFlat":{
                        String id=(String)sois.readObject();
                        Realty realty=(Realty)sois.readObject();
                        DBFactory dbFactory = new DBFactory();
                        dbFactory.getRealty().updateRealty(realty,id);
                        Flat flat =(Flat)sois.readObject();
                        dbFactory.getFlat().updateFlat(flat,id);
                        soos.writeObject("ok");
                        }break;
                        case "updateHouse":{
                            String id=(String)sois.readObject();
                            Realty realty=(Realty)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            dbFactory.getRealty().updateRealty(realty,id);
                            House house=(House)sois.readObject();
                            dbFactory.getHouse().updateHouse(house,id);
                            soos.writeObject("ok");
                        }break;
                        case "updateOffice":{
                            String id=(String)sois.readObject();
                            Realty realty=(Realty)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            dbFactory.getRealty().updateRealty(realty,id);
                            Office office=(Office)sois.readObject();
                            dbFactory.getOffice().updateOffice(office,id);
                            soos.writeObject("ok");
                        }break;
                        case "deleteRealty":{
                            String str=(String)sois.readObject();
                            String type=(String)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            dbFactory.getRealty().deleteRealty(str);
                            dbFactory.getCommission().deleteCommissionRealty(str);
                            switch (type)
                            {
                                case "Квартира":
                                    dbFactory.getFlat().deleteFlat(str);
                                    break;
                                case "Дом":
                                    dbFactory.getHouse().deleteHouse(str);
                                    break;
                                case"Офис":
                                    dbFactory.getOffice().deleteOffice(str);
                                    break;
                            }
                            soos.writeObject("ok");
                        }break;
                        case "assessRealty": {
                            Commission commission=(Commission)sois.readObject();
                            int id=(int)sois.readObject();
                            DBFactory dbFactory = new DBFactory();
                            Realty realty=dbFactory.getRealty().findRealty(commission.getRegistration_No());
                            String type=realty.getType();
                            double V[]=new double[1];
                            V[0]=0;
                            switch (type) {
                                case "Квартира": {
                                    Flat flat = dbFactory.getFlat().findFlat(realty.getRegistration_No());
                                    V[0] = Methods.assessFlat(realty, flat,commission, id);
                                }break;
                                case"Дом":
                                {
                                    House house=dbFactory.getHouse().findHouse(realty.getRegistration_No());
                                    V[0]=Methods.assessHouse(realty,house,commission,id);
                                }break;
                                case"Офис":
                                {
                                    Office office=dbFactory.getOffice().findOffice(realty.getRegistration_No());
                                    V[0]=Methods.assessOffice(realty,office,commission,id);
                                }break;
                            }
                            soos.writeObject(V[0]);
                        }break;
                        case"writeReport":
                        {
                            String FileName=(String)sois.readObject();
                            String str=(String)sois.readObject();
                            FileWriter writer= null;
                            try {
                                writer = new FileWriter(FileName,false);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                assert writer != null;
                                writer.write(str);
                                writer.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }break;
                        case"readReport":
                        {
                            String FileName=(String)sois.readObject();
                            FileReader reader= null;
                            final String[] info = {""};
                            try {
                                reader = new FileReader(FileName);
                                if(reader==null)
                                {
                                    soos.writeObject("Error");
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            Scanner scan = new Scanner(reader);
                            while(scan.hasNextLine())
                            {
                                info[0]=info[0]  +scan.nextLine()+"\n";
                            }
                            try {
                                reader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            soos.writeObject("ok");
                            soos.writeObject(info[0]);
                        }break;
                        case "exit":{
                            soos.close();
                            sois.close();
                            System.out.println("Client " + clientAccepted.getInetAddress().toString() + " disconnected.");
                        }break;

                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Client disconnected.");
        }
    }
}
