/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import modele.Enseignement;

/**
 *
 * @author ghias
 */
public class EnseignementDAO extends DAO<Enseignement> {
    
    public EnseignementDAO(Connection conn) 
    {
        super(conn);
    }
    
     //Pas encore implémentée
    public boolean create(Enseignement obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean delete(Enseignement obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean update(Enseignement obj)
    {
        return false;
    }
    
    
    public Enseignement find(int id)
    {
        Enseignement enseignement = new Enseignement();
        
        
        try{
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM enseignement WHERE id = " + id);
            
            if(result.first())
            {
                enseignement = new Enseignement(id);
                
                PersonneDAO personneDAO = new PersonneDAO(this.connect);
                ClasseDAO classeDAO = new ClasseDAO(this.connect);
                DisciplineDAO disciplineDAO = new DisciplineDAO(this.connect);
                
                enseignement.setPersonne(personneDAO.find(result.getInt("personne_id")));
                enseignement.setClasse(classeDAO.find(result.getInt("classe_id")));
                enseignement.setDiscipline(disciplineDAO.find(result.getInt("discipline_id")));
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return enseignement;
    }
    
    
}
