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
public class ClasseDAO extends DAO<Classe>{
    
       public boolean create_eval(Classe obj, DetailBulletin detailBulletin){return false;}
    
    public ClasseDAO(Connection conn) 
    {
        super(conn);
    }
    
     //Pas encore implémentée
    public boolean create(Classe obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean delete(Classe obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean update(Classe obj)
    {
        return false;
    }
    
    
    public Classe find(int id)
    {
        Classe classe = new Classe();
        
        
        try{
            String sql = "SELECT * FROM classe WHERE id = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setInt(1, id);     
            pst.executeQuery();
            
            if(pst.executeQuery().first())
            {
                 classe = new Classe(id, pst.executeQuery().getString("nom"));
                 
                 EcoleDAO ecoleDAO = new EcoleDAO(this.connect);
                 NiveauDAO niveauDAO = new  NiveauDAO(this.connect);
                 AnneeScolaireDAO anneeScolaireDAO = new AnneeScolaireDAO(this.connect);
                 
                 classe.setEcole(ecoleDAO.find(pst.executeQuery().getInt("ecole_id")));
                 classe.setNiveau(niveauDAO.find(pst.executeQuery().getInt("niveau_id")));
                 classe.setAnneeScolaire(anneeScolaireDAO.find(pst.executeQuery().getInt("anneescolaire_id")));
            }
            /*ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM classe WHERE id = " + id);
            
            if(result.first())
            {
                classe = new Classe(id, result.getString("nom"));
            }*/
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return classe;
    }
    
}
