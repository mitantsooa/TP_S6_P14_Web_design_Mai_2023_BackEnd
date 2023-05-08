package portefeuille.model;

import java.util.ArrayList;

public class ListePaginate {
    ArrayList liste;
    int nbrpage;
    int brparpage;

    public void setBrparpage(int brparpage) {
        this.brparpage = brparpage;
    }

    public void setListe(ArrayList liste) {
        this.liste = liste;
    }

    public void setNbrpage(int nbrpage) {
        this.nbrpage = nbrpage;
    }

    public int getBrparpage() {
        return brparpage;
    }

    public ArrayList getListe() {
        return liste;
    }

    public int getNbrpage() {
        return nbrpage;
    }

}
