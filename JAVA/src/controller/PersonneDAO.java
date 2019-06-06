/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

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
        String nom = obj.getNom();
        String prenom = obj.getPrenom();
        String type= obj.getType();
      //  String classe=obj.getClasse();
        boolean b=true;

        try{
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("INSERT INTO personne (nom,prenom,type,classe) VALUES ('"+nom+"','"+prenom+"','"+type+"')");
                    // ResultSet.CONCUR_READ_ONLY).executeQuery("INSERT INTO personne (nom,prenom,type,classe) VALUES ('"+nom+"','"+prenom+"','"+type+"','"+classe+"')");

            if(result.first())
            {
                b= true;
            }
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
    public boolean delete(Personne obj)
    {

        String nom = obj.getNom();
        String prenom = obj.getPrenom();
        String type= obj.getType();
        boolean b=true;

        try{
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("DELETE FROM personne WHERE nom = '"+nom+"' AND prenom = '"+prenom+"' AND type ='"+type+"'");


            if(result.first())
            {
               b=true;

            }
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
        switch(modif)
        {
            case "nom":
                String new_nom;
                try{
                    System.out.println("Quel le nouveau nom  ?");
                    new_nom=sc.next();
                    ResultSet result = this.connect.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY).executeQuery("UPDATE personne set nom ='"+new_nom+"'");

                    if(result.first())
                    {
                        b=true;

                    }
                }
                catch (SQLException exception)
                {
                    exception.printStackTrace();
                    b= false;
                }
                break;

            case "prenom":
                String new_prenom=null;
                try{
                    System.out.println("Quel le nouveau prenom  ?");
                    new_prenom=sc.next();
                    ResultSet result = this.connect.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY).executeQuery("UPDATE personne set prenom ='"+new_prenom+"'");

                    if(result.first())
                    {
                        b=true;

                    }
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
                    System.out.println("Quel le nouveau type  ?");
                    new_type=sc.next();
                    ResultSet result = this.connect.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY).executeQuery("UPDATE personne set type ='"+new_type+"'");

                    if(result.first())
                    {
                        b=true;

                    }
                }
                catch (SQLException exception)
                {
                    exception.printStackTrace();
                    b= false;
                }
                break;

            case "classe":
                String new_classe;
                try{
                    System.out.println("Quel la nouvelle classe  ?");
                    new_classe=sc.next();
                    ResultSet result = this.connect.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY).executeQuery("UPDATE personne set classe ='"+new_classe+"'");

                    if(result.first())
                    {
                        b=true;

                    }
                }
                catch (SQLException exception)
                {
                    exception.printStackTrace();
                    b= false;
                }
                break;

            case "ecole":
                String new_ecole;
                try{
                    System.out.println("Quel le nouveau ecole  ?");
                    new_ecole=sc.next();
                    ResultSet result = this.connect.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY).executeQuery("UPDATE personne set ecole ='"+new_ecole+"'");

                    if(result.first())
                    {
                        b=true;

                    }
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
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM personne WHERE id = " + id);
            
            if(result.first())
            {
                personne = new Personne(id, result.getString("nom") , result.getString("prenom"), result.getString("type"));
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return personne;
    }
    
}
