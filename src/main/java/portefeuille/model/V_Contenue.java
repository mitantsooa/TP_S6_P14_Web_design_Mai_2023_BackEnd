package portefeuille.model;

import java.lang.reflect.Array;
import java.sql.Date;

import portefeuille.AllAnnotation.PrimaryKey;

public class V_Contenue {
    @PrimaryKey
    int id;

    String titre;
    String introduction;
    int idtype;
    String motcle;
    String conclure;
    String image;
    String article;
    Date date_creation;
    String type;

    public void setArticle(String article) {
        this.article = article;
    }

    public void setConclure(String conclure) {
        this.conclure = conclure;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdtype(int idtype) {
        this.idtype = idtype;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setMotcle(String motcle) {
        this.motcle = motcle;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArticle() {
        return article;
    }

    public String getConclure() {
        return conclure;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public int getId() {
        return id;
    }

    public int getIdtype() {
        return idtype;
    }

    public String getImage() {
        return image;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getMotcle() {
        return motcle;
    }

    public String getTitre() {
        return titre;
    }

    public String getType() {
        return type;
    }
    //// public ArrayList<V_Contenue> getRecherche(Str){}

}
