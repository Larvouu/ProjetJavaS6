/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;

import modele.*;
import controller.*;

/**
 *
 * @author ghias
 */
public class EvaluationDAO extends DAO<Evaluation> {
    
    public EvaluationDAO(Connection conn) 
    {
        super(conn);
    }
    
     //Pas encore implémentée
    public boolean create(Evaluation obj)
    {


        return false;
    }

    /**
     * Methods pour ajouter une note
     * @param obj
     * @param prof
     * @return
     */

    public boolean create_eval(Evaluation obj, Personne prof)
    {
        ///Pour créer une note l'eleve doit d'abord avoir un detail bulletin (matiere)
        DetailBulletinDAO detailBulletinDAO  = new DetailBulletinDAO(this.connect);
        DetailBulletin detailBulletin=new DetailBulletin();

        int monid=0;
        Scanner sc = new Scanner(System.in);
        Personne eleve = new Personne();
        //On recupere nom et prenom de l'eleve a modifier
        System.out.println("Pour quel élève souhaitez-vous ajouter une note");
        System.out.println("NOM");
        eleve.setNom(sc.next());
        System.out.println("PRENOM");
        eleve.setPrenom(sc.next());
        // on recupere ainsi l'id

            try
            {
                String sql="SELECT id FROM personne where nom = ? AND prenom = ? ";

                PreparedStatement pst = connect.prepareStatement(sql);
                pst.setString(1, eleve.getNom());
                pst.setString(2, eleve.getPrenom());
                ResultSet rs	=pst.executeQuery();

                rs.next();

                System.out.println(eleve.getNom());
                System.out.println(eleve.getPrenom());

                    monid = rs.getInt("id");
                    eleve.setId(rs.getInt("id"));
                    System.out.println("hola eleve1 id" +eleve.getId());


            }

        catch (SQLException exception)
            {
                exception.printStackTrace();
            }
           ///On a l'identité de l'eleve a qui on veut rajouter une note donc on crée mainentn la sous partie du bulletin
        detailBulletinDAO.create_detailbulletin(detailBulletin, eleve, prof);

        return false;

        }




    //Pas encore implémentée 
    public boolean delete(Evaluation obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean update(Evaluation obj)
    {
        return false;
    }
    
    
    public Evaluation find(int id)
    {
        Evaluation evaluation = new Evaluation();
        
        
        try{
            String sql = "SELECT * FROM evaluation WHERE id = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setInt(1, id);     
            pst.executeQuery();
            
            if(pst.executeQuery().first())
            {
                   evaluation = new Evaluation(id, pst.executeQuery().getFloat("note"), pst.executeQuery().getString("appreciation"));
                   
                   DetailBulletinDAO detailBulletinDAO = new DetailBulletinDAO(this.connect);
                   
                   evaluation.setDetailBulletin(detailBulletinDAO.find(pst.executeQuery().getInt("detailBulletin_id")));
            }
            /*
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM evaluation WHERE id = " + id);
            
            if(result.first())
            {
                evaluation = new Evaluation(id, result.getFloat("note"), result.getString("appreciation"));
                DetailBulletinDAO detailBulletinDAO = new DetailBulletinDAO(this.connect);
                evaluation.setDetailBulletin(detailBulletinDAO.find(result.getInt("detailBulletin_id")));
            }*/
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return evaluation;
    }
    
}
