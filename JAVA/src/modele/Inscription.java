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
public class Inscription {
    
    //attributs
    private int id;
    
    private Classe classe;
   
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
    
    //setters
    public void setId(int id)
    {
        this.id = id;
    }
    
    public void setClasse(Classe classe)
    {
        this.classe = classe;
    }

    
}
