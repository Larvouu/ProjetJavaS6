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
public class DisciplineDAO extends DAO<Discipline>{
    
       public boolean create_eval(Discipline obj, DetailBulletin detailBulletin){return false;}
    
    public DisciplineDAO(Connection conn) 
    {
        super(conn);
    }
    
     //Pas encore implémentée
    public boolean create(Discipline obj)
    {
        return false;
    }



    //Pas encore implémentée 
    public boolean delete(Discipline obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean update(Discipline obj)
    {
        return false;
    }
    
    
    public Discipline find(int id)
    {
        Discipline discipline = new Discipline();
        
        
        try{
            String sql = "SELECT * FROM discipline WHERE id = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setInt(1, id);     
            pst.executeQuery();
            
            if(pst.executeQuery().first())
            {
                   discipline = new Discipline(id, pst.executeQuery().getString("nom"));
            }
            /*ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM discipline WHERE id = " + id);
            
            if(result.first())
            {
                discipline = new Discipline(id, result.getString("nom"));
            }*/
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return discipline;
    }
    
    
}
