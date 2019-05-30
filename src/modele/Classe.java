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
public class Classe {
    
    //attributs
    private int id;
    private String nom;
   
    //constructeur par défaut
    public Classe(){};
           
    //constructeur surchargé
    public Classe(int id, String nom)
    {
        this.id = id;
        this.nom = nom;
    }
    
    
    //getters
    public int getId(){return id;}
    public String getNom(){return nom;}
    
    //setters
    public void setId(int id)
    {
        this.id = id;
    }
    
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    
}
