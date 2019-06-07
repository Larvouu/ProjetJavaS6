/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import modele.*;

/**
 *
 * @author ghias
 * inspiré du pattern DAO de openclassroom : 
 * https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java/26830-liez-vos-tables-avec-des-objets-java-le-pattern-dao
 */
public abstract class DAO<T> {
    
    protected Connection connect = null;
 
    public DAO(Connection conn){
        //se connecte à la bdd (visiblement)
        this.connect = conn;
    }

    //méthode de création
    public abstract boolean create(T obj);
    
    //methode de creation d'une évaluation
    public abstract boolean create_eval(T obj, DetailBulletin detailBulletin);

    //méthode de suppression
    public abstract boolean delete(T obj);

    //méthode de màj
    public abstract boolean update(T obj);

    //méthode de recherche
    public abstract T find(int id);

    
}
