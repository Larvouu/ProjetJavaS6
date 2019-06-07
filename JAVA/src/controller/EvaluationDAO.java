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
import modele.Evaluation;

/**
 *
 * @author ghias
 */
public class EvaluationDAO extends DAO<Evaluation> {
    
    public EvaluationDAO(Connection conn) 
    {
        super(conn);
    }
    
    //Inutilisée
    public boolean create(Evaluation obj){return false;}
    
    
    public boolean create_eval(Evaluation obj, DetailBulletin detailBulletin)
    {
        Float note = obj.getNote();
        String appreciation = obj.getAppreciation();
        boolean b=true;
        
        try{
            String sql = "INSERT INTO Evaluation (note,appreciation,detailbulletin_id) VALUES (?,?,?)";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setFloat(1, note);
            pst.setString(2, appreciation);
            pst.setInt(3, detailBulletin.getId());
            
            pst.executeUpdate();

        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
            b= false;
        }
        return b;
    }
    
    //Pas encore implémentée 
    public boolean delete(Evaluation obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean update(Evaluation obj)
    {        
        boolean b = true;
        String modif = null;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Voulez-vous modifier note ou appreciation ?");
        modif=sc.next();
        switch(modif)
        {
            case "note":
                Float new_note;
                try{
                    System.out.println("Quelle est la nouvelle note  ? (. au lieu de , pour une virgule)");
                    new_note=sc.nextFloat();
                    String sql = "UPDATE evaluation set note =? WHERE id=?";
                    PreparedStatement pst = connect.prepareStatement(sql);
                    pst.setFloat(1, new_note);
                    pst.setInt(2, obj.getId());
                    pst.executeUpdate();
                }
                catch (SQLException exception)
                {
                    exception.printStackTrace();
                    b= false;
                }
                break;
                
            case "appreciation":
                String new_app;
                try{
                    System.out.println("Quelle est la nouvelle appreciation ?");
                    new_app=sc.next();
                    String sql = "UPDATE evaluation set appreciation =? WHERE id=?";
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
                break;
        }
        return b;
    }
    
    
    public Evaluation find(int id)
    {
        Evaluation evaluation = new Evaluation();
        
        
        try{
            String sql = "SELECT * FROM evaluation WHERE id = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setInt(1, id);     
            pst.executeQuery();
            
            if(pst.executeQuery().first())
            {
                   evaluation = new Evaluation(id, pst.executeQuery().getFloat("note"), pst.executeQuery().getString("appreciation"));
                   
                   DetailBulletinDAO detailBulletinDAO = new DetailBulletinDAO(this.connect);
                   
                   evaluation.setDetailBulletin(detailBulletinDAO.find(pst.executeQuery().getInt("detailBulletin_id")));
            }
            /*
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM evaluation WHERE id = " + id);
            
            if(result.first())
            {
                evaluation = new Evaluation(id, result.getFloat("note"), result.getString("appreciation"));
                DetailBulletinDAO detailBulletinDAO = new DetailBulletinDAO(this.connect);
                evaluation.setDetailBulletin(detailBulletinDAO.find(result.getInt("detailBulletin_id")));
            }*/
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return evaluation;
    }
    
}
