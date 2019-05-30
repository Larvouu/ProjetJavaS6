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
public class Bulletin {
    
    //attributs
    private int id;
    private String appreciation;
    
    private Trimestre trimestre;
   
    //constructeur par défaut
    public Bulletin(){};
           
    //constructeur surchargé
    public Bulletin(int id, String appreciation)
    {
        this.id = id;
        this.appreciation = appreciation;
    }
        
    //////////////////////////////////
    // --------- METHODES --------- //
    //////////////////////////////////
  
    
    //////////////////////////////////
    // ------- METHODES FIN ------- //
    //////////////////////////////////
    
    //getters
    public int getId(){return id;}
    public String getAppreciation(){return appreciation;}
    public Trimestre getTrimestre(){return trimestre;}
    
    //setters
    public void setId(int id)
    {
        this.id = id;
    }
    
     public void setAppreciation(String appreciation)
    {
        this.appreciation = appreciation;
    } 
     
    public void setTrimestre(Trimestre trimestre)
    {
        this.trimestre = trimestre;
    }
     
   
}
