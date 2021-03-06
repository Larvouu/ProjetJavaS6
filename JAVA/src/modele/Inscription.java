/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 * Une Inscription est définie par un id, une classe, et une personne. Cette classe n'a pas de méthode propre à elle.
 * @author ghias
 */
public class Inscription {
    
    //attributs
    private int id;
    private Classe classe;
    private Personne personne;
   
    //constructeur par défaut
    public Inscription(){};
           
    //constructeur surchargé
    public Inscription(int id)
    {
        this.id = id;
    }
        
    //////////////////////////////////
    // --------- METHODES --------- //
    //////////////////////////////////

        
    //////////////////////////////////
    // ------- METHODES FIN ------- //
    //////////////////////////////////
    
    
    //getters
    public int getId(){return id;}
    public Classe getClasse(){return classe;}
    public Personne getPersonne(){return personne;}
    
    //setters
    public void setId(int id)
    {
        this.id = id;
    }
    
    public void setClasse(Classe classe)
    {
        this.classe = classe;
    }
    
    public void setPersonne(Personne personne)
    {
        this.personne = personne;
    }

    
}
