package model;
import java.io.Serializable;
import java.util.Objects;
public class Appraiser extends Users implements Serializable{
    //private int idAppraiser;
    private String No_Licence;
    private int Popularity;
    private int Amount;

//    public int getIdAppraiser() {
//        return idAppraiser;
//    }
//
//    public void setIdAppraiser(int idAppraiser) {
//        this.idAppraiser = idAppraiser;
//    }

    public String getNo_Licence() {
        return No_Licence;
    }

    public void setNo_Licence(String no_Licence) {
        No_Licence = no_Licence;
    }

    public int getPopularity() {
        return Popularity;
    }

    public void setPopularity(int popularity) {
        Popularity = popularity;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appraiser appraiser = (Appraiser) o;
        return getIdAppraiser() == appraiser.getIdAppraiser() &&
                getPopularity() == appraiser.getPopularity() &&
                getAmount() == appraiser.getAmount() &&
                Objects.equals(getNo_Licence(), appraiser.getNo_Licence());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdAppraiser(), getNo_Licence(), getPopularity(), getAmount());
    }

    @Override
    public String toString() {
        return "Appraiser{" +
                "idAppraiser=" + idAppraiser +
                ", No_Licence='" + No_Licence + '\'' +
                ", Popularity=" + Popularity +
                ", Amount=" + Amount +
                '}';
    }
}
