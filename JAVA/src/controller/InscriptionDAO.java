/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import modele.Inscription;

/**
 *
 * @author ghias
 */
public class InscriptionDAO extends DAO<Inscription>{
    
    public InscriptionDAO(Connection conn) 
    {
        super(conn);
    }
    
     //Pas encore implémentée
    public boolean create(Inscription obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean delete(Inscription obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean update(Inscription obj)
    {
        return false;
    }
    
    
    public Inscription find(int id)
    {
        Inscription inscription = new Inscription();
        
        
        try{
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM inscription WHERE id = " + id);
            
            if(result.first())
            {
                inscription = new Inscription(id);
                
                PersonneDAO personneDAO = new PersonneDAO(this.connect);
                ClasseDAO classeDAO = new ClasseDAO(this.connect);
                
                inscription.setPersonne(personneDAO.find(result.getInt("personne_id")));
                inscription.setClasse(classeDAO.find(result.getInt("classe_id")));
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return inscription;
    }
}
