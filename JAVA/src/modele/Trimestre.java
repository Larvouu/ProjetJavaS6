/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 * Un Trimestre est défini par un id, un numéro, un début et une fin. Cette classe n'a pas de méthode propre à elle.
 * @author ghias
 */
public class Trimestre {
    
    //attributs
    private int id;
    private int numero;
    private String debut;
    private String fin;
    
    private AnneeScolaire anneeScolaire;
   
    //constructeur par défaut
    public Trimestre(){};
           
    //constructeur surchargé
    public Trimestre(int id, int numero, String debut, String fin)
    {
        this.id = id;
        this.numero = numero;
        this.debut = debut;
        this.fin = fin;
        
    }
        
    //////////////////////////////////
    // --------- METHODES --------- //
    //////////////////////////////////
        
    //////////////////////////////////
    // ------- METHODES FIN ------- //
    //////////////////////////////////
    
    
    //getters
    public int getId(){return id;}
    public int getNumero(){return numero;}
    public String getDebut(){return debut;}
    public String getFin(){return fin;}
    public AnneeScolaire getAnneeScolaire(){return anneeScolaire;}
    
    //setters
    public void setId(int id)
    {
        this.id = id;
    }
    
    public void setNumero(int numero)
    {
        this.numero = numero;
    }
    
    public void setDebut(String debut)
    {
        this.debut = debut;
    }
    
    public void setFin(String fin)
    {
        this.fin = fin;
    }
    
    public void setAnneeScolaire(AnneeScolaire anneeScolaire)
    {
        this.anneeScolaire = anneeScolaire;
    }
}
