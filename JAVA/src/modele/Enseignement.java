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
    
    private Classe classe;
    private Discipline discipline;
   
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
}
