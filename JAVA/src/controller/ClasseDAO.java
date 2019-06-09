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
import java.util.ArrayList;
import java.util.Scanner;
import modele.*;

/**
 *
 * @author ghias
 */
public class ClasseDAO extends DAO<Classe>{
    
    public ClasseDAO(Connection conn) 
    {
        super(conn);
    }
    
     //Pas encore implémentée
    public boolean create(Classe obj)
    {
        return false;
    }
    public boolean create_inscription(Classe obj, Personne personne) {return false;}


    //Pas encore implémentée 
    public boolean delete(Classe obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean update(Classe obj)
    {
        return false;
    }
    
    public Classe find(int id)
    {
        Classe classe = new Classe();
        return classe;
    }

    public Classe find(String id)
    {
        Classe classe = new Classe();
        
        
        try{
            String sql = "SELECT * FROM classe WHERE nom = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, id);
            pst.executeQuery();
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())
            {
                 classe = new Classe(id);
            }
            /*ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM classe WHERE id = " + id);
            
            if(result.first())
            {
                classe = new Classe(id, result.getString("nom"));
            }*/
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return classe;
    }
    
    public boolean rechercherClasse(String nomClasseSelection, int anneeSelection)
    {
        boolean b = true;
        boolean boucle1 = false;
        ArrayList<Integer> arl = new ArrayList<Integer>();
        
        try{
            //requete
            String sql = "SELECT * FROM classe WHERE nom = ? AND anneescolaire_id=?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, nomClasseSelection);
            pst.setInt(2, anneeSelection);
            ResultSet rs_find=pst.executeQuery();

            //if j'ai un résultat
            if(rs_find.next())
            {
                boucle1=true;
                //Je fais mes petits traitements
                System.out.println("-------   Informations concernant la classe   --------");
                System.out.println("Ecole : "+rs_find.getString("ecole_id"));
                System.out.println("Nom : "+ rs_find.getString("nom"));
                System.out.println("Annee : "+rs_find.getInt("anneescolaire_id"));
                System.out.println("Niveau : "+rs_find.getString("niveau_id"));
            }
            else
            {
                b = false;
                System.out.println("Cette classe n'existe pas");
            }
            
            if(boucle1)
            {
                String sql2 = "SELECT * FROM inscription WHERE classe_id=?"; 
                PreparedStatement pst2 = connect.prepareStatement(sql2);
                pst2.setString(1, nomClasseSelection);
                ResultSet rs2 = pst2.executeQuery();
                
                while(rs2.next())
                {
                    arl.add(rs2.getInt("personne_id"));
                  
                }
                
                
                System.out.println("-------   Eleves de la classe   --------");
                
                for(int i = 0 ; i < arl.size(); i++)
                {
                    String sql3 = "SELECT * FROM personne WHERE id=?";
                    PreparedStatement pst3 = connect.prepareStatement(sql3);
                    pst3.setInt(1, arl.get(i));
                    ResultSet rs3 = pst3.executeQuery();
                    
                    if(rs3.next())
                    {
                        
                        System.out.println("Nom: "+rs3.getString("nom"));
                        System.out.println("Prenom : "+ rs3.getString("prenom"));
                       
                        System.out.println("");
                    }
                }
                
            }
            
            
            
        }
        catch (SQLException exception)
        {
            System.out.println(exception);
        }
        return b;
    }
    
     
    
}
