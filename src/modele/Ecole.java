/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author ghias
 */
public class Ecole {
    
    //attributs
    private String nom;
   
    //constructeur par défaut
    public Ecole(){};
           
    //constructeur surchargé
    public Ecole(String nom)
    {
        this.nom = nom;
    }
    
    
    //getters
    public String getNom(){return nom;}
    
    //setters
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    
}
