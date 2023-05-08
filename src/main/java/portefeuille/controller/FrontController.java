package portefeuille.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.util.ProxyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import portefeuille.Service.AllServiceBudjet;
import portefeuille.model.Admin;
import portefeuille.model.Article;
import portefeuille.model.Categorie;
import portefeuille.model.ContenueIA;
import portefeuille.model.Data;
import portefeuille.model.ListePaginate;
import portefeuille.model.Type;
import portefeuille.repository.DepenseRepository;
import portefeuille.repository.ModelRepository;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin()
@Controller
public class FrontController {

    @Autowired
    ModelRepository repModel;

    @Autowired
    DepenseRepository depREP;

    @CrossOrigin()

    @ResponseBody
    @RequestMapping(value = "/api/types", method = RequestMethod.GET, produces = "application/json")
    public Data getCategories() {
        ArrayList<Type> cates = null;
        Data data = new Data();
        try {
            cates = new Type().getTypes(null);
            data.setListe(cates);
            data.setMess("Request Success");
            data.setStatus("200");
            return data;
        } catch (Exception e) {
            data.setListe(null);
            data.setMess("Error Cause By : " + e.getMessage());
            data.setStatus("500");
            return data;
        }
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/api/articles", method = RequestMethod.GET, produces = "application/json")
    public Data getArticles() {
        Data data = new Data();
        ArrayList<Article> res = null;
        try {
            res = new Article().getArticles(null);
            data.setListe(res);
            data.setMess("Request Success");
            data.setStatus("200");
            return data;
        } catch (Exception e) {
            data.setListe(null);
            data.setMess("Error Cause By : " + e.getMessage());
            data.setStatus("500");
            return data;
        }
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/api/article", method = RequestMethod.POST, produces = "application/json")
    public Data CreateArticle(@RequestBody Article art) {
        Data data = new Data();
        try {
            art.CreateArticle(null);
            data.setListe(null);
            data.setMess("Request Success");
            data.setStatus("200");
            return data;
        } catch (Exception e) {
            data.setMess("Error Cause By : " + e.getMessage());
            data.setStatus("500");
            return data;
        }
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = "application/json")
    public Data Login(@RequestBody Admin admin) {
        Data data = new Data();
        try {
            ArrayList<Admin> res = admin.Login(null);
            data.setListe(res);
            data.setMess("Request Success");
            data.setStatus("200");
            return data;
        } catch (Exception e) {
            data.setListe(null);
            data.setMess("Error Cause By : " + e.getMessage());
            data.setStatus("500");
            return data;
            // TODO: handle exception
        }
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/api/contenueIA", method = RequestMethod.POST, produces = "application/json")
    public Data CreateContenue(@RequestBody ContenueIA contenue) {
        Data data = new Data();
        try {
            contenue.CreateContenue(null);
            data.setListe(null);
            data.setMess("Request Success ");
            data.setStatus("200");
            return data;
        } catch (Exception e) {
            data.setMess("Error Cause BY : " + e.getMessage());
            data.setStatus("500");
            return data;
            // TODO: handle exception
        }
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/api/contenues/{offset}&&{nbrPage}", method = RequestMethod.GET, produces = "application/json")
    public Data getContenue(@PathVariable int offset, @PathVariable int nbrPage) {
        Data data = new Data();
        ArrayList<ContenueIA> res = null;
        try {
            ListePaginate paginate = new ContenueIA().getListePContenue(offset, nbrPage);
            res = paginate.getListe();
            data.setListe(res);
            data.setNbrpage(paginate.getNbrpage());
            data.setMess("Request Success");
            data.setStatus("200");
            return data;
        } catch (Exception e) {
            data.setListe(null);
            data.setMess("Error Cause By : " + e.getMessage());
            data.setStatus("500");
            return data;
        }
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/api/contenueIA", method = RequestMethod.PUT, produces = "application/json")
    public Data UpdateContenue(@RequestBody ContenueIA contenue) {
        Data data = new Data();
        try {
            contenue.UpdateContenueIA(null);
            data.setListe(null);
            data.setMess("Request Success ");
            data.setStatus("200");
            return data;
        } catch (Exception e) {
            data.setMess("Error Cause BY : " + e.getMessage());
            data.setStatus("500");
            return data;
            // TODO: handle exception
        }
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/api/articles/{id}", method = RequestMethod.GET, produces = "application/json")
    public Data getOneContenue(@PathVariable int id) {
        Data data = new Data();
        try {
            ContenueIA cont = new ContenueIA();
            cont.setId(id);
            cont = cont.getOneContenueIA(null);

            data.setObj(cont);
            data.setMess("Request Valider");
            data.setStatus("200");
        } catch (Exception e) {
            data.setMess("Error cause By : " + e.getMessage());
            data.setStatus("500");
            // TODO: handle exception
        }
        return data;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/api/type", method = RequestMethod.POST, produces = "application/json")
    public Data CreateType(@RequestBody Type type) {
        Data data = new Data();
        try {
            type.CreateType(null);
            data.setListe(null);
            data.setMess("Request Success ");
            data.setStatus("200");
            return data;
        } catch (Exception e) {
            data.setMess("Error Cause BY : " + e.getMessage());
            data.setStatus("500");
            return data;
            // TODO: handle exception
        }
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/api/RechMulti/{dateM}&&{dateP}&&{type}&&{offset}", method = RequestMethod.GET, produces = "application/json")
    public Data RechercheMulti(@PathVariable String dateM, @PathVariable String dateP,
            @PathVariable int type, @PathVariable int offset) {
        Data data = new Data();
        try {
            ContenueIA cont = new ContenueIA();
            Date dtM = Date.valueOf(dateM);
            Date dtP = Date.valueOf(dateP);

            ListePaginate pagin = cont.getRechMulti(dtM, dtP, type, offset, 2);

            /// data.setObj(cont);
            data.setListe(pagin.getListe());
            data.setNbrpage(pagin.getNbrpage());
            data.setMess("Request Valider");
            data.setStatus("200");
        } catch (Exception e) {
            data.setMess("Error cause By : " + e.getMessage());
            data.setStatus("500");
            // TODO: handle exception
        }
        return data;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/api/ListeContenue/{mot}", method = RequestMethod.GET, produces = "application/json")
    public Data Recherche(@PathVariable String mot) {
        Data data = new Data();
        try {
            ContenueIA cont = new ContenueIA();

            ListePaginate pagin = cont.getRECHNORMALE(mot, 0);

            /// data.setObj(cont);
            data.setListe(pagin.getListe());
            //// data.setNbrpage(pagin.getNbrpage());
            data.setMess("Request Valider");
            data.setStatus("200");
        } catch (Exception e) {
            data.setMess("Error cause By : " + e.getMessage());
            data.setStatus("500");
            // TODO: handle exception
        }
        return data;
    }

    /*
     * @CrossOrigin()
     * 
     * @RequestMapping(value = "/addCateg", method = RequestMethod.GET, produces =
     * "application/json")
     * 
     * @ResponseBody
     * public String InsetCateg(HttpServletRequest req) {
     * Categorie categ = new Categorie();
     * try {
     * if (req.getParameter("categorie") != null &&
     * req.getParameter("type_categorie") != null) {
     * categ.setCategorie(req.getParameter("categorie"));
     * categ.setType_categorie(Integer.parseInt(req.getParameter("type_categorie")))
     * ;
     * repModel.insertCategorie(categ);
     * 
     * }
     * } catch (Exception e) {
     * return e.getMessage();
     * // System.out.print(e);
     * // TODO: handle exception
     * }
     * return "valider";
     * 
     * }
     * 
     * @CrossOrigin()
     * 
     * @ResponseBody
     * 
     * @RequestMapping(value = "/categories", method = RequestMethod.GET, produces =
     * "application/json")
     * public ArrayList<Categorie> getCategories(HttpServletRequest req) {
     * ArrayList<Categorie> categs = null;
     * try {
     * int type = Integer.parseInt(req.getParameter("type"));
     * categs = repModel.getCategories(type);
     * } catch (Exception e) {
     * // TODO: handle exception
     * }
     * return categs;
     * }
     * 
     * @CrossOrigin()
     * 
     * @ResponseBody
     * 
     * @RequestMapping(value = "/depense", method = RequestMethod.POST, produces =
     * "application/json")
     * public MessageRetour insertDepense(@RequestBody Depense depense) throws
     * Exception {
     * MessageRetour mess = new MessageRetour();
     * try {
     * depense.setMontantC(depense.getMontant());
     * repModel.insertDepense(depense);
     * mess.setStatus("200");
     * mess.setMessage("Request Create Depense Success");
     * } catch (Exception e) {
     * mess.setStatus("500");
     * mess.setMessage("Error Cause By : " + e.getMessage());
     * // TODO: handle exception
     * }
     * return mess;
     * }
     * 
     * @CrossOrigin
     * 
     * @ResponseBody
     * 
     * @RequestMapping(value = "/personne", method = RequestMethod.POST, produces =
     * "application/json")
     * public MessageRetour requestMethodName(@RequestBody Personne pers) {
     * MessageRetour mess = new MessageRetour();
     * try {
     * repModel.insertPersonne(pers);
     * mess.setStatus("200");
     * mess.setMessage("Request Save Succes");
     * } catch (Exception e) {
     * mess.setStatus("500");
     * mess.setMessage("Error Cause By " + e.getMessage());
     * // TODO: handle exception
     * }
     * return mess;
     * }
     * 
     * @CrossOrigin()
     * 
     * @ResponseBody
     * 
     * @RequestMapping(value = "/personnes", method = RequestMethod.GET, produces =
     * "application/json")
     * public ArrayList<Personne> getPersonnes() {
     * ArrayList<Personne> pers = null;
     * try {
     * pers = repModel.getPersonnes();
     * } catch (Exception e) {
     * // TODO: handle exception
     * }
     * return pers;
     * }
     * 
     * @CrossOrigin
     * 
     * @ResponseBody
     * 
     * @RequestMapping(value = "/depenses/{offset}", method = RequestMethod.GET,
     * produces = "application/json")
     * public ArrayList<Depense> getDepenses(@PathVariable int offset) {
     * ArrayList<Depense> des = null;
     * try {
     * des = repModel.getDepenses(offset);
     * } catch (Exception e) {
     * // TODO: handle exception
     * }
     * return des;
     * }
     * 
     * @CrossOrigin
     * 
     * @ResponseBody
     * 
     * @RequestMapping(value = "/emprunt", method = RequestMethod.POST, produces =
     * "application/json")
     * public MessageRetour FaireEmprunt(@RequestBody Emprunt emprt) {
     * MessageRetour mess = new MessageRetour();
     * try {
     * emprt.setMontantC(emprt.getMontant());
     * emprt.setDateremboursementC(emprt.getDateremboursement());
     * repModel.CreateEmprunt(emprt);
     * mess.setStatus("200");
     * mess.setMessage("Request Valider");
     * } catch (Exception e) {
     * mess.setStatus("500");
     * mess.setMessage("Error Cause By : " + e.getMessage());
     * // TODO: handle exception
     * }
     * return mess;
     * }
     * 
     * @CrossOrigin
     * 
     * @ResponseBody
     * 
     * @RequestMapping(value = "/depense/{id}", method = RequestMethod.DELETE,
     * produces = "application/json")
     * public MessageRetour DeleteDepense(@PathVariable int id) {
     * MessageRetour mess = new MessageRetour();
     * try {
     * Depense dep = new Depense();
     * dep.setId(id);
     * depREP.DeleteDepense(dep);
     * mess.setStatus("200");
     * mess.setMessage("Request Delete Valider");
     * } catch (Exception e) {
     * mess.setStatus("500");
     * mess.setMessage("Error cause by : " + e.getMessage());
     * // TODO: handle exception
     * }
     * return mess;
     * }
     * 
     * @CrossOrigin
     * 
     * @ResponseBody
     * 
     * @RequestMapping(value = "/search/{desc}&&{daty1}&&{daty2}&&{idcategorie}",
     * method = RequestMethod.GET, produces = "application/json")
     * public ArrayList<Depense> Recherche(@PathVariable String desc, @PathVariable
     * Date daty1, @PathVariable Date daty2,
     * 
     * @PathVariable int idcategorie) {
     * ArrayList<Depense> des = null;
     * try {
     * des = depREP.Search(desc, daty1, daty2, idcategorie);
     * } catch (Exception e) {
     * // TODO: handle exception
     * }
     * return des;
     * }
     * 
     * @CrossOrigin
     * 
     * @ResponseBody
     * 
     * @RequestMapping(value = "/depenseU", method = RequestMethod.POST, produces =
     * "application/json")
     * public MessageRetour DeleteDepense(@RequestBody Depense depense) {
     * MessageRetour mess = new MessageRetour();
     * try {
     * 
     * depREP.UpdateDepense(depense);
     * mess.setStatus("200");
     * mess.setMessage("Request Update Valider");
     * } catch (Exception e) {
     * mess.setStatus("500");
     * mess.setMessage("Error cause by : " + e.getMessage());
     * // TODO: handle exception
     * }
     * return mess;
     * }
     * 
     * @CrossOrigin
     * 
     * @ResponseBody
     * 
     * @RequestMapping(value = "/reportcaisse", method = RequestMethod.POST,
     * produces = "application/json")
     * public MessageRetour CreateReport(@RequestBody ReportCaisse report) {
     * MessageRetour mess = new MessageRetour();
     * try {
     * report.setMontantC(report.getMontant());
     * repModel.CreateReportCaisse(report);
     * mess.setStatus("200");
     * mess.setMessage("Create Report Succes");
     * } catch (Exception e) {
     * mess.setStatus("500");
     * mess.setMessage("Error Create Report cause By : " + e.getMessage());
     * // TODO: handle exception
     * }
     * return mess;
     * }
     * 
     * @CrossOrigin
     * 
     * @ResponseBody
     * 
     * @RequestMapping(value = "/emprunts/{offset}", method = RequestMethod.GET,
     * produces = "application/json")
     * public ArrayList<V_EmpruntDetails> getEmprunts(@PathVariable int offset) {
     * ArrayList<V_EmpruntDetails> result = null;
     * try {
     * result = repModel.getV_EmpruntDetails(offset);
     * } catch (Exception e) {
     * // TODO: handle exception
     * }
     * return result;
     * }
     * 
     * @CrossOrigin
     * 
     * @ResponseBody
     * 
     * @RequestMapping(value = "/payementEmprunt", method = RequestMethod.POST,
     * produces = "application/json")
     * public MessageRetour PayementEmprunt(@RequestBody PayementEmprunt emprt)
     * throws Exception {
     * MessageRetour mess = new MessageRetour();
     * try {
     * emprt.setMontantC(emprt.getMontant());
     * repModel.getPayement(emprt);
     * mess.setStatus("200");
     * mess.setMessage("Payement Valider");
     * return mess;
     * } catch (Exception e) {
     * //// System.out.println(e);
     * mess.setStatus("500");
     * mess.setMessage("Error Cause By : " + e.getMessage());
     * /// System.out.println(e.getMessage());
     * return mess;
     * // TODO: handle exception
     * }
     * }
     * 
     * @CrossOrigin
     * 
     * @ResponseBody
     * 
     * @RequestMapping(value = "/budjet", method = RequestMethod.GET, produces =
     * "application/json")
     * public Budjet getBudjetNOW() {
     * // return new SomeData();
     * Budjet budjet = null;
     * try {
     * budjet = new AllServiceBudjet().getBudjetNow();
     * } catch (Exception e) {
     * System.out.println(e);
     * // TODO: handle exception
     * }
     * return budjet;
     * }
     */

    /*
     * @Autowired
     * FrontOfficerepository repOFF;
     * 
     * @Autowired
     * ProduitRepository ProdREP;
     * 
     * @Autowired
     * PhotoProduit photo;
     * 
     * @Autowired
     * HistoriqueDAO histo;
     * 
     * @Autowired
     * UtilisateurRepository logREP;
     * 
     * @Autowired
     * private MongoTemplate mongoTemplate;
     * 
     * @Autowired
     * private HistoriqueRepository HistoREP;
     * 
     * ArrayList error = new ArrayList<>();
     * String message;
     * int status;
     * 
     * @Autowired
     * ProduitRepository rep;
     * 
     * private Data data = new Data();
     * ///// https://enchere-production.up.railway.app
     * 
     * @ResponseBody
     * 
     * @CrossOrigin("https://exquisite-marigold-63df7f.netlify.app/")
     * 
     * @RequestMapping(value = "/FicheEncheres", method = RequestMethod.GET,
     * produces = "application/json")
     * public ArrayList<Data> getFicheEncheres() {
     * ArrayList<Data> __data = new ArrayList<>();
     * try {
     * ArrayList<FicheEncheres> ficheEncheres = repOFF.getFicheProduit();
     * new FicheEncheres().SetImage(ficheEncheres, ProdREP);
     * data.setData(ficheEncheres);
     * __data.add(data);
     * } catch (Exception e) {
     * // TODO: handle exception
     * status = 500;
     * message = "Une erreur s'est produite : " + e;
     * Erreur __error = new Erreur(status, message);
     * error.add(__error);
     * data.setData(error);
     * }
     * return __data;
     * 
     * }
     * 
     * @ResponseBody
     * 
     * @CrossOrigin("https://exquisite-marigold-63df7f.netlify.app/")
     * 
     * @RequestMapping(value = "/FicheEncheres/{offset}", method =
     * RequestMethod.GET, produces = "application/json")
     * public ArrayList<Data> getFicheEncheresPAGINE(@PathVariable int offset) {
     * ArrayList<Data> __data = new ArrayList<>();
     * try {
     * ArrayList<FicheEncheres> ficheEncheres =
     * repOFF.getFicheProduitPAGINE(offset);
     * new FicheEncheres().SetImage(ficheEncheres, ProdREP);
     * data.setData(ficheEncheres);
     * __data.add(data);
     * } catch (Exception e) {
     * // TODO: handle exception
     * status = 500;
     * message = "Une erreur s'est produite : " + e;
     * Erreur __error = new Erreur(status, message);
     * error.add(__error);
     * data.setData(error);
     * }
     * return __data;
     * 
     * }
     * 
     * @RequestMapping(value = "/login", method = RequestMethod.POST, produces =
     * "application/json")
     * 
     * @ResponseBody
     * 
     * @CrossOrigin("https://exquisite-marigold-63df7f.netlify.app/")
     * public ArrayList<Data> Login(HttpServletRequest request) {
     * ArrayList<Data> __data = new ArrayList<>();
     * if (request.getParameter("email") != null && request.getParameter("mdp") !=
     * null) {
     * try {
     * Utilisateur retour = new Utilisateur();
     * retour.setMail(request.getParameter("email"));
     * retour.setMotdepass(request.getParameter("mdp"));
     * Token token = logREP.Login(retour);
     * ArrayList<Token> TokenRes = new ArrayList<>();
     * TokenRes.add(token);
     * 
     * data.setData(TokenRes);
     * __data.add(data);
     * } catch (Exception e) {
     * // TODO: handle exception
     * status = 500;
     * message = "Une erreur s'est produite : " + e;
     * Erreur __error = new Erreur(status, message);
     * error.add(__error);
     * data.setData(error);
     * }
     * } else {
     * // TODO: handle exception
     * status = 500;
     * message = "email ou votre mot de passe est vide";
     * Erreur __error = new Erreur(status, message);
     * error.add(__error);
     * data.setData(error);
     * }
     * return __data;
     * }
     * 
     * @ResponseBody
     * 
     * @CrossOrigin("https://exquisite-marigold-63df7f.netlify.app/")
     * 
     * @RequestMapping(value = "/FicheEncheres", method = RequestMethod.POST,
     * produces = "application/json")
     * public ArrayList<Data> rechercheAvance(HttpServletRequest request) {
     * ArrayList<Data> __data = new ArrayList<>();
     * try {
     * float prix = -1;
     * if (request.getParameter("prix") != null) {
     * prix = Float.parseFloat(request.getParameter("prix"));
     * }
     * FicheEncheres fiche = new FicheEncheres();
     * fiche.setCategorie(request.getParameter("categorie"));
     * fiche.setNom(request.getParameter("produit"));
     * fiche.setStatus(request.getParameter("status"));
     * if (request.getParameter("dateFin") != null) {
     * fiche.setDatefin(LocalDateTime.parse(request.getParameter("dateFin")));
     * }
     * fiche.setPrix(prix);
     * ArrayList<FicheEncheres> ficheEncheres = repOFF.rechercheAvancer(fiche,
     * request.getParameter("motCle"));
     * 
     * new FicheEncheres().SetImage(ficheEncheres, ProdREP);
     * data.setData(ficheEncheres);
     * __data.add(data);
     * } catch (Exception e) {
     * // TODO: handle exception
     * status = 500;
     * message = "Une erreur s'est produite : " + e;
     * Erreur __error = new Erreur(status, message);
     * error.add(__error);
     * data.setData(error);
     * }
     * return __data;
     * }
     * 
     * @RequestMapping(value = "/Encherir/{prix}&&{produitid}&&{id}", method =
     * RequestMethod.GET, produces = "application/json")
     * 
     * @ResponseBody
     * 
     * @CrossOrigin("https://exquisite-marigold-63df7f.netlify.app/")
     * public ArrayList<Data> Enherire(@PathVariable(value = "prix") double prix,
     * 
     * @PathVariable(value = "produitid") int produitid,
     * 
     * @PathVariable(value = "id") int id) {
     * // Token tok = new Token().ToToken(token);
     * long date = System.currentTimeMillis();
     * Date dt = new Date(date);
     * Produit produit = ProdREP.getById(produitid);
     * HistoriqueUtilisateur historique = null;
     * historique = HistoREP.getDernierHistorique(produitid);
     * historique.setPrix(prix);
     * historique.setNom(produit.getNom());
     * 
     * historique.setDuree(LocalTime.parse(produit.getDuree().toString()));
     * historique.setUtilisateuridvendeur(produit.getId());
     * historique.setDateencheriser(Date.valueOf(dt.toString()));
     * System.out.print(produitid + "===TESTYUIOP" + prix);
     * historique.setProduitid(produitid);
     * // historique.set
     * 
     * historique.setUtilisateuridacheteur(id);
     * EtatSolde solde = null;
     * try {
     * solde = rep.FaireEncherir(historique);
     * } catch (Exception ex) {
     * ex.printStackTrace();
     * }
     * 
     * ArrayList<EtatSolde> etatSolde = new ArrayList<>();
     * etatSolde.add(solde);
     * ArrayList<Data> __data = new ArrayList<>();
     * data.setData(etatSolde);
     * __data.add(data);
     * return __data;
     * }
     * 
     * @CrossOrigin("https://exquisite-marigold-63df7f.netlify.app/")
     * 
     * @ResponseBody
     * 
     * @RequestMapping(value = "/HistoriqueUtilisateurs/{iduser}", method =
     * RequestMethod.GET, produces = "application/json")
     * 
     * public ArrayList<Data> getHistoriqueUtildateurs(@PathVariable int iduser) {
     * ArrayList<Data> _data = new ArrayList<>();
     * try {
     * ArrayList<HistoriqueUtilisateur> histoUtil =
     * HistoREP.getHistoriqueUsers(iduser);
     * data.setData(histoUtil);
     * _data.add(data);
     * } catch (Exception e) {
     * // TODO: handle exception
     * e.printStackTrace();
     * status = 500;
     * message = "Une erreur s'est produite : " + e;
     * Erreur __error = new Erreur(status, message);
     * error.add(__error);
     * data.setData(error);
     * }
     * return _data;
     * }
     * 
     * @CrossOrigin("https://exquisite-marigold-63df7f.netlify.app/")
     * 
     * @ResponseBody
     * 
     * @RequestMapping(value = "/ProduitCategories", method = RequestMethod.GET,
     * produces = "application/json")
     * public ArrayList<Data> getCategorie() {
     * 
     * ArrayList<Data> _data = new ArrayList<>();
     * try {
     * ArrayList<Categorie> produit = repOFF.getCategorie();
     * data.setData(produit);
     * _data.add(data);
     * 
     * } catch (Exception e) {
     * e.printStackTrace();
     * status = 500;
     * message = "Une erreur s'est produite : " + e;
     * Erreur __error = new Erreur(status, message);
     * error.add(__error);
     * data.setData(error);
     * // TODO: handle exception
     * }
     * return _data;
     * /// return new SomeData();
     * }
     * 
     * @CrossOrigin("https://exquisite-marigold-63df7f.netlify.app/")
     * 
     * @ResponseBody
     * 
     * @RequestMapping(value = "/encheresPropres/{iduser}", method =
     * RequestMethod.GET, produces = "application/json")
     * public ArrayList<Data> getHistoriquePropre(@PathVariable(value = "iduser")
     * int id) {
     * ArrayList<Data> _data = new ArrayList<Data>();
     * try {
     * ArrayList<HistoriqueUtilisateur> histo = HistoREP.getPropreHistorique(id);
     * System.out.print(histo.size());
     * data.setData(histo);
     * _data.add(data);
     * } catch (Exception e) {
     * e.printStackTrace();
     * status = 500;
     * message = "Une erreur s'est produite : " + e;
     * Erreur __error = new Erreur(status, message);
     * error.add(__error);
     * data.setData(error);
     * // TODO: handle exception
     * }
     * return _data;
     * }
     * 
     * @CrossOrigin("https://exquisite-marigold-63df7f.netlify.app/")
     * 
     * @ResponseBody
     * 
     * @RequestMapping(value = "/encheresNormes/{iduser}", method =
     * RequestMethod.GET, produces = "application/json")
     * public ArrayList<Data> getHistoriqueNorme(@PathVariable(value = "iduser") int
     * id) {
     * ArrayList<Data> _data = new ArrayList<Data>();
     * try {
     * ArrayList<HistoriqueUtilisateur> histo = HistoREP.getHistoriqueNormale(id);
     * data.setData(histo);
     * _data.add(data);
     * } catch (Exception e) {
     * status = 500;
     * message = "Une erreur s'est produite : " + e;
     * Erreur __error = new Erreur(status, message);
     * error.add(__error);
     * data.setData(error);
     * // TODO: handle exception
     * }
     * return _data;
     * }
     * 
     * @CrossOrigin("https://exquisite-marigold-63df7f.netlify.app/")
     * 
     * @ResponseBody
     * 
     * @RequestMapping(value = "/photos/{idproduit}", method = RequestMethod.GET,
     * produces = "application/json")
     * public ArrayList<Data> getPhotos(@PathVariable(value = "idproduit") int
     * idproduit) {
     * ArrayList<Data> _data = new ArrayList<>();
     * try {
     * ArrayList<Photo> photo = ProdREP.getPhoto(idproduit);
     * data.setData(photo);
     * _data.add(data);
     * } catch (Exception e) {
     * // TODO: handle exception
     * status = 500;
     * message = "Une erreur s'est produite : " + e;
     * Erreur __error = new Erreur(status, message);
     * error.add(__error);
     * data.setData(error);
     * }
     * return _data;
     * }
     * 
     * @RequestMapping(value = "/Encheres", method = RequestMethod.POST, produces =
     * "application/json")
     * 
     * @ResponseBody
     * 
     * @CrossOrigin
     * public ArrayList<Data> CreateEnchere(@RequestBody String body,
     * HttpServletRequest request) {
     * try {
     * // Token token = new Token().ToToken(tok);
     * ArrayList<Data> __data = new ArrayList<>();
     * Produit produit = new Produit();
     * produit.setNom(request.getParameter("nom"));
     * produit.setUtilisateurid(Integer.parseInt(request.getParameter(
     * "idUtilisateur")));
     * produit.setCategorieid(Integer.parseInt(request.getParameter("categorieid")))
     * ;
     * produit.setDateencheriser(LocalDateTime.now());
     * produit.setDuree(LocalTime.parse(request.getParameter("duree")));
     * produit.setPrix(Double.parseDouble(request.getParameter("prix")));
     * // produit.setDateencheriser(new);
     * int idprod = ProdREP.InsertProduit(produit);
     * Photo[] sary = photo.ToPhoto(idprod, body);
     * produit.setPhoto(sary);
     * ArrayList<Produit> ReturnProduit = new ArrayList<>();
     * ReturnProduit.add(produit);
     * data.setData(ReturnProduit);
     * __data.add(data);
     * return __data;
     * } catch (Exception e) {
     * // TODO: handle exception
     * e.printStackTrace();
     * }
     * return null;
     * }
     */

    // @RequestMapping(value = "/historiques", method = RequestMethod.GET, produces
    // = "application/json")
    // @ResponseBody
    // public List<HistoriqueUtilisateur> getHistoriques() {
    // return HistoREP.getHistoriqueAll();
    // }

}
