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
import modele.*;

/**
 *
 * @author ghias
 */
public class NiveauDAO extends DAO<Niveau>{
    
       public boolean create_eval(Niveau obj, DetailBulletin detailBulletin){return false;}
    
    public NiveauDAO(Connection conn) 
    {
        super(conn);
    }
    
     //Pas encore implémentée
    public boolean create(Niveau obj)
    {
        return false;
    }



    //Pas encore implémentée 
    public boolean delete(Niveau obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean update(Niveau obj)
    {
        return false;
    }
    
    
    public Niveau find(int id)
    {
        Niveau niveau = new Niveau();
        
        
        try{
            String sql = "SELECT * FROM niveau WHERE id = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setInt(1, id);     
            pst.executeQuery();
            
            if(pst.executeQuery().first())
            {
                   niveau = new Niveau(id, pst.executeQuery().getString("nom"));
            }
            
            /*ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM niveau WHERE id = " + id);
            
            if(result.first())
            {
                niveau = new Niveau(id, result.getString("nom"));
            }*/
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return niveau;
    }
    
}
