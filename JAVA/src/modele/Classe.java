/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author ghias
 */
public class Classe {
    
    //attributs
    private int id;
    private String nom;
    
    private AnneeScolaire anneeScolaire;
    private Niveau niveau;
    private Ecole ecole;
   
    //constructeur par défaut
    public Classe(){};
           
    //constructeur surchargé
    public Classe(int id, String nom)
    {
        this.id = id;
        this.nom = nom;
    }
        
    //////////////////////////////////
    // --------- METHODES --------- //
    //////////////////////////////////
 
        
    //////////////////////////////////
    // ------- METHODES FIN ------- //
    //////////////////////////////////
    
    
    //getters
    public int getId(){return id;}
    public String getNom(){return nom;}
    public AnneeScolaire getAnneeScolaire(){return anneeScolaire;}
    public Ecole getEcole(){return ecole;}
    public Niveau getNiveau(){return niveau;}
    
    //setters
    public void setId(int id)
    {
        this.id = id;
    }
    
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    
    public void setAnneeScolaire(AnneeScolaire anneeScolaire)
    {
        this.anneeScolaire = anneeScolaire;
    }
    
    public void setNiveau(Niveau niveau)
    {
        this.niveau = niveau;
    }
 
    public void setEcole(Ecole ecole)
    {
        this.ecole = ecole;
    }
    
}
