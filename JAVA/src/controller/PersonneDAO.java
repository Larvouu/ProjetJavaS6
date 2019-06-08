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
      
        
        boolean b=true;

        try
        {
            String sql = "DELETE FROM personne WHERE nom = ? AND prenom = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.executeUpdate();
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
    public void rechercherEleve()
    {
        String prenom;
        String nom;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("---------- RECHERCHER UN ELEVE ---------");
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
                if(rs_2.next())
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
    
}
