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
import java.util.Scanner;
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
        boolean b = true;
        Scanner sc = new Scanner(System.in);
        
        String new_app;
        try{
            System.out.println("Quelle est la nouvelle appreciation ?");
            new_app = sc.next();   
            String sql = "UPDATE datailbulletin set appreciation =? WHERE id=?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, new_app);
            pst.setInt(2, obj.getId());
            pst.executeUpdate();
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
            b= false;
        }
        
        return b;
    }
    
    
    public DetailBulletin find(int id)
    {
        DetailBulletin detailBulletin = new DetailBulletin();
        
        
        try{
            String sql = "SELECT * FROM detailbulletin WHERE id = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setInt(1, id);     
            pst.executeQuery();
            
            if(pst.executeQuery().first())
            {
                  detailBulletin = new DetailBulletin(id, pst.executeQuery().getString("appreciation"));
                  
                  EnseignementDAO enseignementDAO = new EnseignementDAO(this.connect);
                  BulletinDAO bulletinDAO = new BulletinDAO(this.connect);
                   
                  detailBulletin.setBulletin(bulletinDAO.find(pst.executeQuery().getInt("bulletin_id")));
                  detailBulletin.setEnseignement(enseignementDAO.find(pst.executeQuery().getInt("enseignement_id")));
            }
            /*ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM detailbulletin WHERE id = " + id);
            
            if(result.first())
            {
                detailBulletin = new DetailBulletin(id, result.getString("appreciation"));
                
                EnseignementDAO enseignementDAO = new EnseignementDAO(this.connect);
                BulletinDAO bulletinDAO = new BulletinDAO(this.connect);
                detailBulletin.setBulletin(bulletinDAO.find(result.getInt("bulletin_id")));
                detailBulletin.setEnseignement(enseignementDAO.find(result.getInt("enseignement_id")));
            }*/
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return detailBulletin;
    }
    
}
