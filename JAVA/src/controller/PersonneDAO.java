/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
     //Pas encore implémentée
    public boolean create(Personne obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean delete(Personne obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean update(Personne obj)
    {
        return false;
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
