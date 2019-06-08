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
 *
 * @author ghias
 */
public class InscriptionDAO extends DAO<Inscription>{
    
    public InscriptionDAO( Connection conn)
    {
        super(conn);
    }

    public boolean create (Inscription obj) {return false;}

    //Pas encore implémentée
    public boolean create_inscription(Inscription obj,Personne personne, Classe classe)
    {


      String niveau;
      Scanner sc = new Scanner(System.in);
        boolean b=true;

        int nb_cp=0;
        int nb_ce1=0;
        int nb_ce2=0;
        int nb_cm1=0;
        int nb_cm2=0;

        String classe_id=null;
        String sql2;


        System.out.println("Quel est le niveau de l'élève ? ");
        niveau = sc.next();


        try {
            String sql = "INSERT INTO inscription (classe_id, personne_id) VALUES (?,?)";
            PreparedStatement pst = connect.prepareStatement(sql);


            switch (niveau) {
                case "CP":
                    if (nb_cp == 0) {

                        classe_id="CP_A";
                        pst.setString(1, classe_id);
                        System.out.println("Id = " +personne.getId());
                        pst.setInt(2, personne.getId());
                        pst.executeUpdate();
                        nb_cp++;

                    }

                    else if (nb_cp == 1) {


                        classe_id="CP_B";
                        pst.setString(1,  classe_id);
                        pst.setInt(1, personne.getId());
                        pst.executeUpdate();
                        nb_cp = 0;
                    }


                break;


                case "CE1":
                    if (nb_ce1 == 0) {


                        pst.setString(1, "CE1_A");
                        classe_id="CE1_A";
                        pst.setInt(2, personne.getId());
                        pst.executeUpdate();
                        nb_cp++;

                    }

                    else if (nb_ce1 == 1) {


                        pst.setString(1, "CE1_B");
                        classe_id="CE1_B";
                        pst.setInt(2, personne.getId());
                        pst.executeUpdate();
                        nb_cp = 0;

                    }

                    break;

                case "CE2":
                    if (nb_ce2 == 0) {


                        pst.setString(1, "CE2_A");
                        classe_id="CE2_A";
                        pst.setInt(2, personne.getId());
                        pst.executeUpdate();
                        nb_cp++;

                    }

                    else if (nb_ce2 == 1) {


                        pst.setString(1, "CE2_B");
                        classe_id="CE2_B";
                        pst.setInt(2, personne.getId());
                        pst.executeUpdate();
                        nb_cp = 0;

                    }

                    break;

                case "CM1":
                    if (nb_cm1 == 0) {

                        pst.setString(1, "CM1_A");
                        classe_id="CM1_A";
                        pst.setInt(2, personne.getId());
                        pst.executeUpdate();
                        nb_cm1++;
                    }

                    else if (nb_cm1 == 1) {


                        pst.setString(1, "CM1_B");
                        classe_id="CM1_B";
                        pst.setInt(2, personne.getId());
                        pst.executeUpdate();
                        nb_cm1 = 0;

                    }
                    break;


                case "CM2":
                    if (nb_cm2 == 0) {


                        pst.setString(1, "CM2_A");
                        classe_id="CM2_A";
                        pst.setInt(2, personne.getId());
                        pst.executeUpdate();
                        nb_cm2++;
                    }

                   else if (nb_cm2 == 1) {


                        pst.setString(1, "CM2_B");
                        classe_id="CM2_B";
                        pst.setInt(2, personne.getId());
                        pst.executeUpdate();
                        nb_cm2 = 0;
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


}
