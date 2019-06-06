/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
     //Pas encore implémentée
    public boolean create(Evaluation obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean delete(Evaluation obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean update(Evaluation obj)
    {
        return false;
    }
    
    
    public Evaluation find(int id)
    {
        Evaluation evaluation = new Evaluation();
        
        
        try{
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM evaluation WHERE id = " + id);
            
            if(result.first())
            {
                evaluation = new Evaluation(id, result.getFloat("note"), result.getString("appreciation"));
                DetailBulletinDAO detailBulletinDAO = new DetailBulletinDAO(this.connect);
                evaluation.setDetailBulletin(detailBulletinDAO.find(result.getInt("detailBulletin_id")));
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return evaluation;
    }
    
}
