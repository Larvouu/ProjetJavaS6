/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import modele.DetailBulletin;

/**
 *
 * @author ghias
 */
public class DetailBulletinDAO extends DAO<DetailBulletin> {
    
    public DetailBulletinDAO(Connection conn) 
    {
        super(conn);
    }
    
     //Pas encore implémentée
    public boolean create(DetailBulletin obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean delete(DetailBulletin obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean update(DetailBulletin obj)
    {
        return false;
    }
    
    
    public DetailBulletin find(int id)
    {
        DetailBulletin detailBulletin = new DetailBulletin();
        
        
        try{
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM detail_bulletin WHERE id = " + id);
            
            if(result.first())
            {
                detailBulletin = new DetailBulletin(id, result.getString("appreciation"));
                
                EnseignementDAO enseignementDAO = new EnseignementDAO(this.connect);
                BulletinDAO bulletinDAO = new BulletinDAO(this.connect);
                detailBulletin.setBulletin(bulletinDAO.find(result.getInt("bulletin_id")));
                detailBulletin.setEnseignement(enseignementDAO.find(result.getInt("enseignement_id")));
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return detailBulletin;
    }
    
}
