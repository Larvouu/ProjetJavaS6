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
 *Permet de gerer les personnes grace a la bdd
 * @author ghias
 *
 */
public class TrimestreDAO extends DAO<Trimestre> {
    
    public TrimestreDAO(Connection conn) 
    {
        super(conn);
    }
    
     //Pas encore implémentée
    public boolean create(Trimestre obj)
    {
        //hello
        return false;
    }



    //Pas encore implémentée 
    public boolean delete(Trimestre obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean update(Trimestre obj)
    {
        return false;
    }
    
    
    public Trimestre find(int id)
    {
        Trimestre trimestre = new Trimestre();
        
        
        try{
            String sql = "SELECT * FROM trimestre WHERE id = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setInt(1, id);     
            pst.executeQuery();
            
            if(pst.executeQuery().first())
            {
                  trimestre = new Trimestre(id, pst.executeQuery().getInt("numero"), pst.executeQuery().getString("debut"), pst.executeQuery().getString("fin"));
            }

            /*
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM trimestre WHERE id = " + id);
            
            if(result.first())
            {
                trimestre = new Trimestre(id, result.getInt("numero"), result.getString("debut"), result.getString("fin"));
                //Mais trimestre a aussi une année scolaire, donc... On déclare une AnneeScolaireDAO
                AnneeScolaireDAO annScoDAO = new AnneeScolaireDAO(this.connect);
                //Et on lui donne la bonne année scolaire
                trimestre.setAnneeScolaire(annScoDAO.find(result.getInt("anneeScolaire_id")));
            } */
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return trimestre;
    }
    
}
