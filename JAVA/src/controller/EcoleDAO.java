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
public class EcoleDAO extends DAO<Ecole> {
    
    public EcoleDAO(Connection conn) 
    {
        super(conn);
    }
    
     //Pas encore implémentée
    public boolean create(Ecole obj)
    {
        return false;
    }


    //Pas encore implémentée 
    public boolean delete(Ecole obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean update(Ecole obj)
    {
        return false;
    }
    
    
    public Ecole find(int id)
    {
        Ecole ecole = new Ecole();
        
        
        try{
            String sql = "SELECT * FROM ecole WHERE id = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setInt(1, id);     
            pst.executeQuery();
            
            if(pst.executeQuery().first())
            {
                   ecole = new Ecole(id, pst.executeQuery().getString("nom"));
            }
            /*
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM ecole WHERE id = " + id);
            
            if(result.first())
            {
                ecole = new Ecole(id, result.getString("nom"));
            }*/
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return ecole;
    }
    
}
