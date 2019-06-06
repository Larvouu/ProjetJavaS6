/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import modele.Classe;

/**
 *
 * @author ghias
 */
public class ClasseDAO extends DAO<Classe>{
    
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
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM classe WHERE id = " + id);
            
            if(result.first())
            {
                //La classe prend ses attributs via constructeur surchargé
                classe = new Classe(id, result.getString("nom"));
                //On déclare les attributs manquants
                AnneeScolaireDAO anneeScolaireDAO = new AnneeScolaireDAO(this.connect);
                EcoleDAO ecoleDAO = new EcoleDAO(this.connect);
                NiveauDAO niveauDAO = new NiveauDAO(this.connect);
                //On affilie à la classe la bonne année scolaire
                //En appelant la methode find de AnneeScolaireDAO
                classe.setAnneeScolaire(anneeScolaireDAO.find(result.getInt("anneeScolaire_id")));
                //puis on fait pareil pour école...
                classe.setEcole(ecoleDAO.find(result.getInt("ecole_id")));
                //... et niveau
                classe.setNiveau(niveauDAO.find(result.getInt("niveau_id")));
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return classe;
    }
    
}
