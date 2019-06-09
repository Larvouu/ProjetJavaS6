/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import modele.Inscription;
import modele.Personne;

/**
 *
 * @author ghias
 */
public class PersonneDAO extends DAO<Personne>{
    
    public PersonneDAO(Connection conn)
    {
        super(conn);
    }
    

    /**
     * Méthode de création
     * @param obj
     * @return boolean
     */


    public boolean create(Personne obj)
    {
        String nomString;
        String prenomString;
        String typeString;
        Scanner sc = new Scanner(System.in);

        System.out.println("Rentrer le nom de la personne a ajouter");
        nomString=sc.next();
        obj.setNom(nomString);

        System.out.println("Rentrer le prenom de la personne a ajouter");
        prenomString=sc.next();
        obj.setNom(prenomString);
        System.out.println("Rentrer le type de la personne a ajouter");
        typeString=sc.next();
        obj.setNom(typeString);


        boolean b=true;


        try{
            String sql = "INSERT INTO personne (nom,prenom,type) VALUES (?,?,?)";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, nomString);
            pst.setString(2, prenomString);
            pst.setString(3,typeString);
            pst.executeUpdate();
            int monid=0;




            String requete = "SELECT id FROM personne where nom = '" + nomString + "' AND prenom = '" + prenomString + "' ";
            ResultSet rs = this.connect.prepareStatement(requete).executeQuery();
            if (rs.next()) {
                monid = rs.getInt("id");
                obj.setId(rs.getInt("id"));
            }


            System.out.println("mon Id a l'intreiru create pers = "+monid);
            System.out.println("Id a l'intreiru create pers = " +obj.getId());


        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
            b= false;
        }

        return b;
    }
    
    
    /**
     * Methode de Sarah : create avec 4 paramètres
     */
    
    public boolean createEleveParEnseignant(Personne obj, String nomEleve, String prenomEleve, String niveauSelection)
    {
        String typeEleve = "eleve";

        obj.setNom(nomEleve);
        obj.setNom(prenomEleve);
        obj.setNom(typeEleve);

        boolean b=true;

        try{
            String sql = "INSERT INTO personne (nom,prenom,type) VALUES (?,?,?)";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, nomEleve);
            pst.setString(2, prenomEleve);
            pst.setString(3,typeEleve);
            pst.executeUpdate();
            int monid=0;

            String requete = "SELECT id FROM personne where nom = '" + nomEleve+ "' AND prenom = '" + prenomEleve + "' ";
            ResultSet rs = this.connect.prepareStatement(requete).executeQuery();
            if (rs.next()) 
            {
                monid = rs.getInt("id");
                obj.setId(rs.getInt("id"));
            }


            System.out.println("mon Id a l'intreiru create pers = "+monid);
            System.out.println("Id a l'intreiru create pers = " +obj.getId());


        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
            b= false;
        }

        return b;
    }

    /**
     * Méthode suppression
     * @param obj
     * @return boolean
     */
    @Override
   public boolean delete(Personne obj)
    {

        String nom = obj.getNom();
        String prenom = obj.getPrenom();
        boolean deleteOk = false;
        
        boolean b=true;
        
        
        
              int idPersonneASuppr=0;//pour sql1
                    String nom_classe_id=null; //pour sql2
                    int nbInscrits=0;
                    
                    
                    System.out.println("delete ok");
                   
                    
                    try 
                    {
                    String sql1 = "SELECT id FROM personne WHERE nom=? AND prenom=?";
                    String sql2 = "SELECT classe_id FROM inscription WHERE personne_id=?";
                    String sql3 = "SELECT nbInscrits FROM classe WHERE nom=?";
                    String sql4 = "UPDATE classe SET nbInscrits=? WHERE nom=?";
                        //SQL1
                         PreparedStatement pst1 = connect.prepareStatement(sql1);
                        pst1.setString(1, nom);
                        pst1.setString(2, prenom);
                        ResultSet rs1 = pst1.executeQuery();
                        System.out.println("Nom : "+nom);
                        System.out.println("Prenom : "+prenom);
                        
                        if(rs1.next())
                        {
                            idPersonneASuppr = rs1.getInt("id");
                            System.out.println("id personne "+idPersonneASuppr);
                        }
                        //System.out.println("id personne "+idPersonneASuppr);
                        
                        //SQL2
                        PreparedStatement pst2 = connect.prepareStatement(sql2);
                        pst2.setInt(1, idPersonneASuppr);
                        ResultSet rs2 = pst2.executeQuery();
                        
                        if(rs2.next())
                        {
                            nom_classe_id = rs2.getString("classe_id");
                            System.out.println("nomclasse "+nom_classe_id);
                        }
                        System.out.println("nomclasse "+nom_classe_id);
                        
                        //SQL3
                        PreparedStatement pst3 = connect.prepareStatement(sql3);
                        pst3.setString(1, nom_classe_id);
                        ResultSet rs3= pst3.executeQuery();
                        
                        if(rs3.next())
                        {
                            nbInscrits = rs3.getInt("nbInscrits");
                            System.out.println("nbinscrits "+nbInscrits);
                        }
                        System.out.println("nbinscrits "+nbInscrits);
                        
                        if (nbInscrits>0)
                        {
                            nbInscrits--;
                        }
                        
                        //SQL4
                        PreparedStatement pst4 = connect.prepareStatement(sql4);
                        pst4.setInt(1, nbInscrits);
                        pst4.setString(2, nom_classe_id);
                        pst4.executeUpdate();
                        
                        
                        //On delete aussi dans inscription 
                        String sql5="DELETE FROM inscription WHERE personne_id=?";
                        PreparedStatement pst = connect.prepareStatement(sql5);
                        pst.setInt(1, idPersonneASuppr);
                        pst.executeUpdate();
                       
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
        

        try
        {
            String sql = "DELETE FROM personne WHERE nom = ? AND prenom = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.executeUpdate();
            
            deleteOk = true;
            
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
            b= false;
        }
        
      
        
        
        
        
        return b;
    }
   
   

    /**
     * Méthode update
     * @param obj
     * @return boolean
     */
    public boolean update(Personne obj)
    {

        String nom = obj.getNom();
        String prenom = obj.getPrenom();
        String type= obj.getType();
        boolean b=true;
        String modif=null;
        Scanner sc = new Scanner(System.in);

        System.out.println("Quel champ souhaitez-vous modifier ? ");
        modif=sc.next();
        switch(modif)
        {
            case "nom":
                String new_nom;
                try{
                    System.out.println("Quel le nouveau nom  ?");
                    new_nom=sc.next();
                    String sql = "UPDATE personne set nom =? WHERE prenom=? AND type=?";
                    PreparedStatement pst = connect.prepareStatement(sql);
                    pst.setString(1, new_nom);
                    pst.setString(2, prenom);
                    pst.setString(3, type);
                    pst.executeUpdate();

               
                }
                catch (SQLException exception)
                {
                    exception.printStackTrace();
                    b= false;
                }
                break;

            case "prenom":
                String new_prenom;
                try{
                    System.out.println("Quel est le nouveau prenom  ?");
                    new_prenom=sc.next();
                    String sql = "UPDATE personne set prenom =? WHERE nom=? AND type=?";
                    PreparedStatement pst = connect.prepareStatement(sql);
                    pst.setString(1, new_prenom);
                    pst.setString(2, nom);
                    pst.setString(3, type);
                    pst.executeUpdate();


                }
                catch (SQLException exception)
                {
                    exception.printStackTrace();
                    b= false;
                }
                break;

            case "type": 
                String new_type;
                try{
                    System.out.println("Quel est le nouveau type  ?");
                    new_type=sc.next();
                    String sql = "UPDATE personne set type =? WHERE prenom=? AND nom=?";
                    PreparedStatement pst = connect.prepareStatement(sql);
                    pst.setString(1, new_type);
                    pst.setString(2, prenom);
                    pst.setString(3, nom);
                    pst.executeUpdate();


                }
                catch (SQLException exception)
                {
                    exception.printStackTrace();
                    b= false;
                }
                break;

        }



        return b;


    }
    
    
    public Personne find(int id)
    {
        Personne personne = new Personne();
        
        
        try{


            String sql = "SELECT * FROM personne WHERE id = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setInt(1, id);
            //pst.executeQuery();
            ResultSet rs_find=pst.executeQuery();

            if(rs_find.next())
            {
                personne = new Personne(id, rs_find.getString("nom") , rs_find.getString("prenom"), rs_find.getString("type"));
            }
            


        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return personne;
    }
    
    //rechercher un eleve
    public void rechercherEleve(String nom, String prenom)
    {
        
        try{
            //Premiere requete
            String sql = "SELECT * FROM personne WHERE prenom = ? AND nom = ? AND type = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, prenom);
            pst.setString(2, nom);
            pst.setString(3, "eleve");
            ResultSet rs_find=pst.executeQuery();

            //if j'ai un résultat
            if(rs_find.next())
            {
                //Je fais mes petits traitements
                System.out.println("-------   Informations concernant l'eleve   --------");
                System.out.println("Id : "+ rs_find.getInt("id"));
                System.out.println("Prenom : "+rs_find.getString("prenom"));
                System.out.println("Nom : "+rs_find.getString("nom"));
                InscriptionDAO inscriptionDAO = new InscriptionDAO(this.connect);
                
                // Deuxieme requete
                String sql2 = "SELECT * FROM inscription WHERE personne_id = ?";
                PreparedStatement pst2 = connect.prepareStatement(sql2);
                pst2.setInt(1, rs_find.getInt("id"));
                ResultSet rs_2=pst2.executeQuery();
                
                //if j'ai un résultat de la requete deux
                if(rs_2.next())
                {
                    System.out.println("Classe : " + inscriptionDAO.find(rs_2.getInt("id")).getClasse().getNom());
                }
            }
        }
        catch (SQLException exception)
        {
            System.out.println(exception);
        }
    }
    
    //rechercher un prof
     public void rechercherProf()
    {
        String prenom;
        String nom;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("---------- RECHERCHER UN PROF ---------");
        System.out.println("Entrer le prenom");
        prenom = sc.next();
        System.out.println("Entrer le nom");
        nom = sc.next();
        
        try{
            //Premiere requete
            String sql = "SELECT * FROM personne WHERE prenom = ? AND nom = ? AND type = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, prenom);
            pst.setString(2, nom);
            pst.setString(3, "prof");
            ResultSet rs_find=pst.executeQuery();

            //if j'ai un résultat
            if(rs_find.next())
            {
                //Je fais mes petits traitements
                System.out.println("-------   Informations concernant le prof   --------");
                System.out.println("Id : "+ rs_find.getInt("id"));
                System.out.println("Prenom : "+rs_find.getString("prenom"));
                System.out.println("Nom : "+rs_find.getString("nom"));
                EnseignementDAO enseignementDAO = new EnseignementDAO(this.connect);
                
                // Deuxieme requete
                String sql2 = "SELECT * FROM enseignement WHERE personne_id = ?";
                PreparedStatement pst2 = connect.prepareStatement(sql2);
                pst2.setInt(1, rs_find.getInt("id"));
                ResultSet rs_2=pst2.executeQuery();
                
                //if j'ai un résultat de la requete deux
                while(rs_2.next())
                {
                    System.out.println("Classe : " + enseignementDAO.find(rs_2.getInt("id")).getClasse().getNom());
                    System.out.println("Discipline  : " + enseignementDAO.find(rs_2.getInt("id")).getDiscipline().getNom());
                } 
            }
        }
        catch (SQLException exception)
        {
            System.out.println(exception);
        }
    }
     
     public void rechercherClassesDontJeSuisProf(Personne personne)
     {
         String prenom = personne.getPrenom();
        String nom = personne.getNom();
        
        Scanner sc = new Scanner(System.in);
        /*System.out.println("---------- IDENTIFIEZ VOUS ---------");
        System.out.println("Prenom");
        prenom = sc.next();
        System.out.println("Nom");
        nom = sc.next();*/
        
        try{
            //Premiere requete
            String sql = "SELECT * FROM personne WHERE prenom = ? AND nom = ? AND type = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, prenom);
            pst.setString(2, nom);
            pst.setString(3, "prof");
            ResultSet rs_find=pst.executeQuery();

            //if j'ai un résultat
            if(rs_find.next())
            {
                
                EnseignementDAO enseignementDAO = new EnseignementDAO(this.connect);
                
                // Deuxieme requete
                String sql2 = "SELECT * FROM enseignement WHERE personne_id = ?";
                PreparedStatement pst2 = connect.prepareStatement(sql2);
                pst2.setInt(1, rs_find.getInt("id"));
                ResultSet rs_2=pst2.executeQuery();
                
                
                System.out.println("------------ VOUS ENSEIGNEZ DANS LES CLASSES SUIVANTES ---------------");
                //if j'ai un résultat de la requete deux
                while(rs_2.next())
                {
                    System.out.println("Classe : " + enseignementDAO.find(rs_2.getInt("id")).getClasse().getNom());
                    System.out.println("Discipline  : " + enseignementDAO.find(rs_2.getInt("id")).getDiscipline().getNom());
                    System.out.println();
                } 
            }
        }
        catch (SQLException exception)
        {
            System.out.println(exception);
        }
     }
     
     public void modifierProfDepuisAdmin(String choix)
     {
        String nom = "";
        String prenom = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("--------- QUEL PROF VOULEZ VOUS MODIFIER ? ---------- ");
        System.out.println("Prenom : ");
        prenom = sc.next();
        System.out.println("Nom : ");
        nom=sc.next();
        switch(choix)
        {
            case "nom":
                String new_nom;
                try{
                    System.out.println("Quel est le nouveau nom  ?");
                    new_nom=sc.next();
                    String sql = "UPDATE personne set nom =? WHERE prenom=? AND nom=? AND type=?";
                    PreparedStatement pst = connect.prepareStatement(sql);
                    pst.setString(1, new_nom);
                    pst.setString(2, prenom);
                    pst.setString(3, nom);
                    pst.setString(4, "prof");
                    pst.executeUpdate();
                    
                    System.out.println();
                    System.out.println("La modification a bien été prise en compte.");
                    System.out.println();

               
                }
                catch (SQLException exception)
                {
                    exception.printStackTrace();
                }
                break;

            case "prenom":
                String new_prenom;
                try{
                    System.out.println("Quel est le nouveau prenom  ?");
                    new_prenom=sc.next();
                    String sql = "UPDATE personne set prenom =? WHERE nom=? AND prenom=? AND type=?";
                    PreparedStatement pst = connect.prepareStatement(sql);
                    pst.setString(1, new_prenom);
                    pst.setString(2, nom);
                    pst.setString(3, prenom);
                    pst.setString(4, "prof");
                    pst.executeUpdate();
                    
                    System.out.println();
                    System.out.println("La modification a bien été prise en compte.");
                    System.out.println();


                }
                catch (SQLException exception)
                {
                    exception.printStackTrace();
                }
                break;
        }
     }
     
     public void modifierDisciplineProfDepuisAdmin()
     {
        String nom = "";
        String prenom = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("--------- QUEL PROF VOULEZ VOUS MODIFIER ? ---------- ");
        System.out.println("Prenom : ");
        prenom = sc.next();
        System.out.println("Nom : ");
        nom=sc.next();
        
        
        String new_dicipline;
        System.out.println("Quelle est la nouvelle matière enseignée ?");
        new_dicipline = sc.next();
        
        try{
            //Premiere requete pour select la personne
            String sql = "SELECT * FROM personne WHERE prenom = ? AND nom = ? AND type = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, prenom);
            pst.setString(2, nom);
            pst.setString(3, "prof");
            ResultSet rs_find=pst.executeQuery();

            if(rs_find.next())
            {                
                System.out.println("La requete 1 a marché");
                // Deuxieme requete pour select l'enseignement relié à cette personne
                String sql2 = "SELECT * FROM enseignement WHERE personne_id = ?";
                PreparedStatement pst2 = connect.prepareStatement(sql2);
                pst2.setInt(1, rs_find.getInt("id"));
                ResultSet rs_2=pst2.executeQuery();
                
                if(rs_2.next())
                {
                    System.out.println("La requete 2 a marché");
                    // Troisième requete pour select la discipline de l'enseignement relié à cette personne
                    String sql3 = "SELECT * FROM discipline WHERE nom = ?";
                    PreparedStatement pst3 = connect.prepareStatement(sql3);
                    pst3.setString(1, rs_2.getString("discipline_id"));
                    ResultSet rs_3=pst3.executeQuery();
                    
                    if(rs_3.next())
                    {
                        System.out.println("La requete 3 a marché");
                        
                        //Change le nom de la discipline
                        String sql4 = "UPDATE discipline set nom =? WHERE nom=?";
                        PreparedStatement pst4 = connect.prepareStatement(sql4);
                        pst4.setString(1, new_dicipline);
                        pst4.setString(2, rs_3.getString("nom"));
                        pst4.executeUpdate();
                        
                        System.out.println();
                        System.out.println("La modification de nom de discipline a bien été prise en compte.");
                        System.out.println();
                        
                        //Change le discipline_id dans enseignement pour concorder avec le premier changement
                        String sql5 = "UPDATE enseignement set discipline_id =? WHERE personne_id=?";
                        PreparedStatement pst5 = connect.prepareStatement(sql5);
                        pst5.setString(1, new_dicipline);
                        pst5.setInt(2, rs_find.getInt("id"));
                        pst5.executeUpdate();
                        
                        System.out.println();
                        System.out.println("La modification de discipline_id de enseignement a bien été prise en compte.");
                        System.out.println();                        
                    }
                } 
            }
        }
        catch (SQLException exception)
        {
            System.out.println(exception);
        }
     }
    
}
