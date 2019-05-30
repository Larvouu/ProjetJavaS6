/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import modele.AnneeScolaire;

/**
 *
 * @author ghias
 * https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java/26830-liez-vos-tables-avec-des-objets-java-le-pattern-dao
 */
public class AnneeScolaireDAO extends DAO<AnneeScolaire>{
    
    public AnneeScolaireDAO(Connection conn) 
    {
        //appelle le constructeur surchargé de DAO<T>
        super(conn);
    }
    
    //Pas encore implémentée -- type de la méthode peut-être à changer
    public boolean create(AnneeScolaire obj)
    {
        //ajouter une AnneeScolaire via requete SQL
        return false;
    }
    
    //Pas encore implémentée -- type de la méthode peut-être à changer
    public boolean delete(AnneeScolaire obj)
    {
        //supprimer une AnneeScolaire via requete SQL
        return false;
    }
    
    //Pas encore implémentée -- type de la méthode peut-être à changer
    public boolean update(AnneeScolaire obj)
    {
        //Comme recherche mais sans créer un nouvel objet, on utilise l'objet en paramètre et on lui fait prendre des nouvelles 
        //valeurs en lisant la bdd ?
        //(Probablement ...)
        return false;
    }
    
    //méthode de recherche de l'annee scolaire via BDD
    public AnneeScolaire find(int id)
    {
        //On instancie une nouvelle année scolaire (vide, par défaut, elle va se remplir après tqt)
        AnneeScolaire anneeScolaire = new AnneeScolaire();
        
        
        try{
            //Commande pour rechercher dans la BDD via une requete SQL, ici select tout dans anneescolaire où l'id correspond à l'id rentré en paramètre
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM anneescolaire WHERE id = " + id);
            
            //Si on a un résultat = si la recherche abouti
            if(result.first())
            {
                //Cette fois l'année scolaire prend la valeur de l'anneeScolaire recherchée
                //avec l'appel du constructeur surchargé
                anneeScolaire = new AnneeScolaire(id/*, result.getString("autre_attribut_de_la_table"), ... etc.*/);
            }
        }
        catch (SQLException exception)
        {
            //si pas de résultat --> affichage du message d'exception
            exception.printStackTrace();
        }
        
        //Retourne l'anneeScolaire fraichement créée en s'appuyant sur la table de la bdd
        return anneeScolaire;
    }
    
}
