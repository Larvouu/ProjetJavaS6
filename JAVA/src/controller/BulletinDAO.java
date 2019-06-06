/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import modele.Bulletin;

/**
 *
 * @author ghias
 */
public class BulletinDAO extends DAO<Bulletin> {
    
    public BulletinDAO(Connection conn) 
    {
        super(conn);
    }
    
     //Pas encore implémentée
    public boolean create(Bulletin obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean delete(Bulletin obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean update(Bulletin obj)
    {
        return false;
    }
    
    
    public Bulletin find(int id)
    {
        Bulletin bulletin = new Bulletin();
        
        
        try{
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM bulletin WHERE id = " + id);
            
            if(result.first())
            {
                bulletin = new Bulletin(id, result.getString("appreciation"));
                
                TrimestreDAO trimestreDAO = new TrimestreDAO(this.connect);
                InscriptionDAO inscriptionDAO = new InscriptionDAO(this.connect);
                
                bulletin.setInscription(inscriptionDAO.find(result.getInt("inscription_id")));
                bulletin.setTrimestre(trimestreDAO.find(result.getInt("trimestre_id")));
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return bulletin;
    }
    
}
