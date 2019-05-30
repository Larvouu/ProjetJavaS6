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
public class Personne {
    
    //attributs
    private int id;
    private String nom;
    private String prenom;
    private String type;
   
    //constructeur par défaut
    public Personne(){};
           
    //constructeur surchargé
    public Personne(int id, String nom, String prenom, String type)
    {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        
//        if(this.type == )
//            ...
//        else if(this.type == )
//            ...
//        else 
//            exception
    }
    
    
    //getters
    public int getId(){return id;}
    
    //setters
    public void setId(int id)
    {
        this.id = id;
    }
    
}
