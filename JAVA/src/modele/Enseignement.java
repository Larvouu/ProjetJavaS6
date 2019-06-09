/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 * Un enseignement est défini par une classe, une discipline, et une personne. Cette classe n'a pas de méthode propre à elle.
 * @author ghias
 */
public class Enseignement {
    
    //attributs
    private int id;
    
    private Classe classe;
    private Discipline discipline;
    private Personne personne;
   
    //constructeur par défaut
    public Enseignement(){};
           
    //constructeur surchargé
    public Enseignement(int id)
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
    public Discipline getDiscipline(){return discipline;}
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
    
    public void setDiscipline(Discipline discipline)
    {
        this.discipline = discipline;
    }
    
    public void setPersonne(Personne personne)
    {
        this.personne = personne;
    }
}
