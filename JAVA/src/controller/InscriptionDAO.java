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

import modele.Classe;
import modele.*;
import modele.Niveau;
import modele.Personne;

/**
 *Permet de gerer les Inscriptions grace a la bdd
 * @author Inna
 * @author ghias
 *
 */
public class InscriptionDAO extends DAO<Inscription>
{
    
    private static int nb_cp;
    private static int nb_ce1;
    private static int nb_ce2;
    private static int nb_cm1;
    private static int nb_cm2;
    
    public InscriptionDAO( Connection conn)
    {
        super(conn);
    }

    public boolean create (Inscription obj) {return false;}
/**
     * Methods pour creer une inscription  une note
     *
     * @param obj Inscription
     * @param  personne Personne
     * @param classe Classe
     * @param niveauSelection String
     * @return boolean 
     */
    //Pas encore implémentée
    public boolean create_inscription(Inscription obj,Personne personne, Classe classe, String niveauSelection)
    {
      Scanner sc = new Scanner(System.in);
        boolean b=true;

        

        String classe_id=null;
        String sql2;
        int nb_a=0;
        int nb_b=0;
        
        String sql_nb;
        PreparedStatement  pst_nb;
        ResultSet rs_nb;


        try {
            String sql = "INSERT INTO inscription (classe_id, personne_id) VALUES (?,?)";
            PreparedStatement pst = connect.prepareStatement(sql);


            switch (niveauSelection) {
                case "CP":
                    
                    sql_nb = "SELECT nbInscrits FROM classe WHERE nom = ?";
                    pst_nb = connect.prepareStatement(sql_nb);
                    pst_nb.setString(1, "CP_A");
                    rs_nb = pst_nb.executeQuery();
                    
                    if(rs_nb.next())
                    {
                        nb_a = rs_nb.getInt("nbInscrits");
                   
                    }
                    
                    sql_nb = "SELECT nbInscrits FROM classe WHERE nom = ?";
                    pst_nb = connect.prepareStatement(sql_nb);
                    pst_nb.setString(1, "CP_B");
                    rs_nb = pst_nb.executeQuery();
                    
                    if(rs_nb.next())
                    {
                        nb_b = rs_nb.getInt("nbInscrits");
                   
                    }
                    
                    if (nb_a < nb_b || nb_a == nb_b) {

                        classe_id="CP_A";
                        pst.setString(1, classe_id);
                        System.out.println("Id = " +personne.getId());
                        pst.setInt(2, personne.getId());
                        pst.executeUpdate();
                        nb_a++;
                        
                        sql_nb = "UPDATE classe SET nbInscrits=? WHERE nom = ?";
                        pst_nb = connect.prepareStatement(sql_nb);
                        pst_nb.setInt(1, nb_a);
                        pst_nb.setString(2, "CP_A");
                        pst_nb.executeUpdate();


                    }
                    else if (nb_b < nb_a){

                        classe_id="CP_B";
                        pst.setString(1,  classe_id);
                        pst.setInt(2, personne.getId());
                        pst.executeUpdate();
                        nb_b++;
                        
                        sql_nb = "UPDATE classe SET nbInscrits=? WHERE nom = ?";
                        pst_nb = connect.prepareStatement(sql_nb);
                        pst_nb.setInt(1, nb_b);
                        pst_nb.setString(2, "CP_B");
                        pst_nb.executeUpdate();
                       
                    }


                break;


                case "CE1":
                    
                    /////////////////////////
                    sql_nb = "SELECT nbInscrits FROM classe WHERE nom = ?";
                    pst_nb = connect.prepareStatement(sql_nb);
                    pst_nb.setString(1, "CE1_A");
                    rs_nb = pst_nb.executeQuery();
                    
                    if(rs_nb.next())
                    {
                        nb_a = rs_nb.getInt("nbInscrits");
                   
                    }
                    
                    sql_nb = "SELECT nbInscrits FROM classe WHERE nom = ?";
                    pst_nb = connect.prepareStatement(sql_nb);
                    pst_nb.setString(1, "CE1_B");
                    rs_nb = pst_nb.executeQuery();
                    
                    if(rs_nb.next())
                    {
                        nb_b = rs_nb.getInt("nbInscrits");
                   
                    }
                        //////////////////////////
                    
                    
                    
                    if (nb_a < nb_b || nb_a == nb_b) {

                        

                        pst.setString(1, "CE1_A");
                        classe_id="CE1_A";
                        pst.setInt(2, personne.getId());
                        pst.executeUpdate();
                        nb_a++;
                        
                        sql_nb = "UPDATE classe SET nbInscrits=? WHERE nom = ?";
                        pst_nb = connect.prepareStatement(sql_nb);
                        pst_nb.setInt(1, nb_a);
                        pst_nb.setString(2, "CE1_A");
                        pst_nb.executeUpdate();

                    }

                    else if (nb_b < nb_a) {


                        pst.setString(1, "CE1_B");
                        classe_id="CE1_B";
                        pst.setInt(2, personne.getId());
                        pst.executeUpdate();
                        nb_b++;
                        
                        sql_nb = "UPDATE classe SET nbInscrits=? WHERE nom = ?";
                        pst_nb = connect.prepareStatement(sql_nb);
                        pst_nb.setInt(1, nb_b);
                        pst_nb.setString(2, "CE1_B");
                        pst_nb.executeUpdate();

                    }

                    break;

                case "CE2":
                    sql_nb = "SELECT nbInscrits FROM classe WHERE nom = ?";
                    pst_nb = connect.prepareStatement(sql_nb);
                    pst_nb.setString(1, "CE2_A");
                    rs_nb = pst_nb.executeQuery();
                    
                    if(rs_nb.next())
                    {
                        nb_a = rs_nb.getInt("nbInscrits");
                   
                    }
                    
                    sql_nb = "SELECT nbInscrits FROM classe WHERE nom = ?";
                    pst_nb = connect.prepareStatement(sql_nb);
                    pst_nb.setString(1, "CE2_B");
                    rs_nb = pst_nb.executeQuery();
                    
                    if(rs_nb.next())
                    {
                        nb_b = rs_nb.getInt("nbInscrits");
                   
                    }
                    
                    if (nb_a < nb_b || nb_a == nb_b) {


                        pst.setString(1, "CE2_A");
                        classe_id="CE2_A";
                        pst.setInt(2, personne.getId());
                        pst.executeUpdate();
                        nb_a++;
                        
                        
                        sql_nb = "UPDATE classe SET nbInscrits=? WHERE nom = ?";
                        pst_nb = connect.prepareStatement(sql_nb);
                        pst_nb.setInt(1, nb_a);
                        pst_nb.setString(2, "CE2_A");
                        pst_nb.executeUpdate();

                    }

                    else if (nb_b < nb_a) {


                        pst.setString(1, "CE2_B");
                        classe_id="CE2_B";
                        pst.setInt(2, personne.getId());
                        pst.executeUpdate();
                        nb_b++;
                        
                        sql_nb = "UPDATE classe SET nbInscrits=? WHERE nom = ?";
                        pst_nb = connect.prepareStatement(sql_nb);
                        pst_nb.setInt(1, nb_b);
                        pst_nb.setString(2, "CE2_B");
                        pst_nb.executeUpdate();

                    }

                    break;

                case "CM1":
                    
                    
                    sql_nb = "SELECT nbInscrits FROM classe WHERE nom = ?";
                    pst_nb = connect.prepareStatement(sql_nb);
                    pst_nb.setString(1, "CM1_A");
                    rs_nb = pst_nb.executeQuery();
                    
                    if(rs_nb.next())
                    {
                        nb_a = rs_nb.getInt("nbInscrits");
                   
                    }
                    
                    sql_nb = "SELECT nbInscrits FROM classe WHERE nom = ?";
                    pst_nb = connect.prepareStatement(sql_nb);
                    pst_nb.setString(1, "CM1_B");
                    rs_nb = pst_nb.executeQuery();
                    
                    if(rs_nb.next())
                    {
                        nb_b = rs_nb.getInt("nbInscrits");
                   
                    }
                    
                    if (nb_a < nb_b || nb_a == nb_b) {

                        pst.setString(1, "CM1_A");
                        classe_id="CM1_A";
                        pst.setInt(2, personne.getId());
                        pst.executeUpdate();
                        nb_a++;
                        
                        sql_nb = "UPDATE classe SET nbInscrits=? WHERE nom = ?";
                        pst_nb = connect.prepareStatement(sql_nb);
                        pst_nb.setInt(1, nb_a);
                        pst_nb.setString(2, "CM1_A");
                        pst_nb.executeUpdate();
                    }

                    else if (nb_b < nb_a) {


                        pst.setString(1, "CM1_B");
                        classe_id="CM1_B";
                        pst.setInt(2, personne.getId());
                        pst.executeUpdate();
                        nb_b++;
                        
                        sql_nb = "UPDATE classe SET nbInscrits=? WHERE nom = ?";
                        pst_nb = connect.prepareStatement(sql_nb);
                        pst_nb.setInt(1, nb_b);
                        pst_nb.setString(2, "CM1_B");
                        pst_nb.executeUpdate();

                    }
                    break;


                case "CM2":
                    
                    sql_nb = "SELECT nbInscrits FROM classe WHERE nom = ?";
                    pst_nb = connect.prepareStatement(sql_nb);
                    pst_nb.setString(1, "CM2_A");
                    rs_nb = pst_nb.executeQuery();
                    
                    if(rs_nb.next())
                    {
                        nb_a = rs_nb.getInt("nbInscrits");
                   
                    }
                    
                    sql_nb = "SELECT nbInscrits FROM classe WHERE nom = ?";
                    pst_nb = connect.prepareStatement(sql_nb);
                    pst_nb.setString(1, "CM2_B");
                    rs_nb = pst_nb.executeQuery();
                    
                    if(rs_nb.next())
                    {
                        nb_b = rs_nb.getInt("nbInscrits");
                   
                    }
                    
                    if (nb_a < nb_b || nb_a == nb_b) {

                        pst.setString(1, "CM2_A");
                        classe_id="CM2_A";
                        pst.setInt(2, personne.getId());
                        pst.executeUpdate();
                        nb_a++;
                        
                         sql_nb = "UPDATE classe SET nbInscrits=? WHERE nom = ?";
                        pst_nb = connect.prepareStatement(sql_nb);
                        pst_nb.setInt(1, nb_a);
                        pst_nb.setString(2, "CM2_A");
                        pst_nb.executeUpdate();
                    }

                   else if (nb_b < nb_a) {


                        pst.setString(1, "CM2_B");
                        classe_id="CM2_B";
                        pst.setInt(2, personne.getId());
                        pst.executeUpdate();
                        nb_b++;
                        
                        sql_nb = "UPDATE classe SET nbInscrits=? WHERE nom = ?";
                        pst_nb = connect.prepareStatement(sql_nb);
                        pst_nb.setInt(1, nb_b);
                        pst_nb.setString(2, "CM2_B");
                        pst_nb.executeUpdate();
                        
                        
                    }
                    break;

            }

            PersonneDAO personneDAO=new PersonneDAO(this.connect);


               ClasseDAO classe_eleveDAO = new ClasseDAO(this.connect);
                //On met l'attribut Classe de l'inscription au nom de la classe
                obj.setClasse(classe_eleveDAO.find(classe_id));
                //On met l'attribut personne de l'inscription a l'id de la personne
                obj.setPersonne(personneDAO.find(personne.getId()));



        }

        catch (SQLException exception)
        {
            exception.printStackTrace();
            b = false;
        }



        return b;

    }
    
    //Pas encore implémentée 
    public boolean delete(Inscription obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean update(Inscription obj)
    {
        return false;
    }
    
    /**
     * Methods pour trouver une inscription
     *
     * @param id int
     * @return Inscription 
     */
    public Inscription find(int id)
    {
        Inscription inscription = new Inscription();
        
        
        try{
            String sql = "SELECT * FROM inscription WHERE id = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setInt(1, id);     
            //pst.executeQuery();
            ResultSet rs_find = pst.executeQuery();
            //rs_find.next();
            
            
            inscription = new Inscription(id);

            PersonneDAO personneDAO = new PersonneDAO(this.connect);
            ClasseDAO classeDAO = new ClasseDAO(this.connect);

            if(rs_find.next())
            {
                inscription.setPersonne(personneDAO.find(rs_find.getInt("personne_id")));
                inscription.setClasse(classeDAO.find(rs_find.getString("classe_id")));
            }
            else{System.out.println("Pas de resultat pour la requete");}
            
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return inscription;
    }

    public Inscription find(String id)
    {
        Inscription inscription = new Inscription();


        return inscription;
    }

    public int getNbCP(){return nb_cp;}
    public int getNbCE1(){return nb_ce1;}
    public int getNbCE2(){return nb_ce2;}
    public int getNbCM1(){return nb_cm1;}
    public int getNbCM2(){return nb_cm2;}
    
    public void setNbCP(int n)
    {
        nb_cp=n;
    }
    
    public void setNbCE1(int n)
    {
        nb_ce1=n;
    }
    
    public void setNbCE2(int n)
    {
        nb_ce2=n;
    }
    
    public void setNbCM1(int n)
    {
        nb_cm1=n;
    }
    
    public void setNbCM2(int n)
    {
        nb_cm2=n;
    }

}
