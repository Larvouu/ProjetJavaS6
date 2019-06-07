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
import modele.*;

/**
 *
 * @author ghias
 */
public class BulletinDAO extends DAO<Bulletin> {
    
       public boolean create_eval(Bulletin obj, DetailBulletin detailBulletin){return false;}
    
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
        boolean b = true;
        Scanner sc = new Scanner(System.in);
        
        String new_app;
        try{
            System.out.println("Quelle est la nouvelle appreciation ?");
            new_app = sc.next();   
            String sql = "UPDATE bulletin set appreciation =? WHERE id=?";
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
    
    
    public Bulletin find(int id)
    {
        Bulletin bulletin = new Bulletin();
        
        
        try{
            String sql = "SELECT * FROM bulletin WHERE id = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setInt(1, id);     
            pst.executeQuery();
            
            if(pst.executeQuery().first())
            {
                bulletin = new Bulletin(id, pst.executeQuery().getString("appreciation"));
                
                TrimestreDAO trimestreDAO = new TrimestreDAO(this.connect);
                InscriptionDAO inscriptionDAO = new InscriptionDAO(this.connect);
                
                bulletin.setInscription(inscriptionDAO.find(pst.executeQuery().getInt("inscription_id")));
                bulletin.setTrimestre(trimestreDAO.find(pst.executeQuery().getInt("trimestre_id")));
            }
            /*ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM bulletin WHERE id = " + id);
            
            if(result.first())
            {
                bulletin = new Bulletin(id, result.getString("appreciation"));
                
                TrimestreDAO trimestreDAO = new TrimestreDAO(this.connect);
                InscriptionDAO inscriptionDAO = new InscriptionDAO(this.connect);
                
                bulletin.setInscription(inscriptionDAO.find(result.getInt("inscription_id")));
                bulletin.setTrimestre(trimestreDAO.find(result.getInt("trimestre_id")));
            }*/
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return bulletin;
    }
    
}
