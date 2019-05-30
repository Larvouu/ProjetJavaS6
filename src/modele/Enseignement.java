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
public class Enseignement {
    
    //attributs
    private int id;
   
    //constructeur par défaut
    public Enseignement(){};
           
    //constructeur surchargé
    public Enseignement(int id)
    {
        this.id = id;
    }
    
    
    //getters
    public int getId(){return id;}
    
    //setters
    public void setId(int id)
    {
        this.id = id;
    }
    
}
