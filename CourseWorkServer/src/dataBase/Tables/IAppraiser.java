package dataBase.Tables;

import model.Appraiser;

public interface IAppraiser {
    public void insertAppraiser(Appraiser obj);
    public Appraiser findAppraiser(int id);
    public void updateAppraiser(Appraiser obj);
    public void vote(Appraiser obj,int mark);
    public void deleteAppraiser(int number);
}
