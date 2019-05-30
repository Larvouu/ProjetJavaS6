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
public class Evaluation {
    
    //attributs
    private int id;
    private float note;
    private String appreciation;
   
    //constructeur par défaut
    public Evaluation(){};
           
    //constructeur surchargé
    public Evaluation(int id, float note, String appreciation)
    {
        this.id = id;
        this.note = note;
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
    public float getNote(){return note;}
    public String getAppreciation(){return appreciation;}    
    
    //setters
    public void setId(int id)
    {
        this.id = id;
    }
    
    public void setNote(float note)
    {
        this.note = note;
    }
    
    public void setId(String appreciation)
    {
        this.appreciation = appreciation;
    }
    
}
