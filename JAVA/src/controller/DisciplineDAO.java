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
 *Permet de gerer les matieres grace a la bdd
 * @author ghias
 *
 */
public class DisciplineDAO extends DAO<Discipline>{
    
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
     /**
 * Find discipline DAO 
 * @param id  int
 * @return  Discipline
 * 
 */
    public Discipline find(int id)
    {
     Discipline discipline = new Discipline();
     
        System.out.println("Fallait pas utiliser cette méthode (String vs Int)");
        return discipline;
    }
    public Discipline find(String id)
    {
        Discipline discipline = new Discipline();
        
        
        try{
            String sql = "SELECT * FROM discipline WHERE nom = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setString(1, id); 
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())
            {
                discipline = new Discipline(rs.getString("nom"));
            }
            
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return discipline;
    }
    
    
}
