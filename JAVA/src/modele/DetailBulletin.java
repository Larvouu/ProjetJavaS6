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
public class DetailBulletin {
    
    //attributs
    private int id;
    private String appreciation;
    
    private Bulletin bulletin;
    private Enseignement enseignement;
   
    //constructeur par défaut
    public DetailBulletin(){};
           
    //constructeur surchargé
    public DetailBulletin(int id, String appreciation)
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
    public Bulletin getBulletin(){return bulletin;}
    public Enseignement getEnseignement(){return enseignement;}
    
    //setters
    public void setId(int id)
    {
        this.id = id;
    }
    
     public void setAppreciation(String appreciation)
    {
        this.appreciation = appreciation;
    } 
     
    public void setBulletin(Bulletin bulletin)
    {
        this.bulletin = bulletin;
    }
    
    public void setEnseignement(Enseignement enseignement)
    {
        this.enseignement = enseignement;
    }
    
}
