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

import modele.*;

/**
 *
 * @author ghias
 */
public class DetailBulletinDAO extends DAO<DetailBulletin> {
    
    public DetailBulletinDAO(Connection conn) 
    {
        super(conn);
    }
    

    public boolean create_detailbulletin(DetailBulletin obj, Personne eleve, Personne prof) {

        int enseignement_id = 0;
        int bulletin_id;
        String nom_matiere = null;
        int inscription_id = 0;
        String classe_id = null;

        ///Mais pour avoir un detail bulletin il faut avoir un Bulletin
        BulletinDAO bulletinDAO = new BulletinDAO(this.connect);
        Bulletin bulletin = new Bulletin();
        bulletin_id = bulletinDAO.create_bulletin(bulletin, eleve);
        int id_prof = 0;
        try {
            //on recupere l'id du prof qui crée le detail bulletin
            String sql_prof = "SELECT id FROM personne where nom = ? AND prenom = ? ";
            PreparedStatement pst_prof = connect.prepareStatement(sql_prof);
            pst_prof.setString(1, prof.getNom());
            pst_prof.setString(2, prof.getPrenom());
            ResultSet rs_prof_id = pst_prof.executeQuery();
            if (rs_prof_id.next()) {
                id_prof = rs_prof_id.getInt("id");
                System.out.println("prof nom : " + prof.getNom());
                System.out.println("prof id : " + prof.getPrenom());
                System.out.println("prof id : " + id_prof);

                ///On peut maintenant créer le detail bulletin
                System.out.println("Veuillez ecrire l'enseignement concerné (Francais, Maths, Sciences, Musique, Art)");
                Scanner sc = new Scanner(System.in);
                nom_matiere = sc.next();

                //On recupere l'id inscription à partit du bulletin pour qu'on a crée car on va vouloir recuperer l'eleve

                String sql_inscription_id = "SELECT inscription_id FROM bulletin where id = ? ";
                PreparedStatement pst2 = connect.prepareStatement(sql_inscription_id);
                pst2.setInt(1, bulletin_id);
                ResultSet rs_inscription_id = pst2.executeQuery();
                if (rs_inscription_id.next()) {
                    inscription_id = rs_inscription_id.getInt("inscription_id");
                    System.out.println("inscription id : " + inscription_id);

                    //on veut recupérer la classe de l'élève


                    String sql_classe = "SELECT classe_id FROM inscription where id = ? ";
                    PreparedStatement pst_classe = connect.prepareStatement(sql_classe);
                    pst_classe.setInt(1, inscription_id);
                    ResultSet rs_classe = pst_classe.executeQuery();
                    if (rs_classe.next()) {
                        classe_id = rs_classe.getString("classe_id");
                        System.out.println("classe id : " + classe_id);
                        System.out.println("Prof id : " + id_prof);
//On recupere l'enseignement car maintenant on a les données du prof et de l'eleve et la matière qu'il veut noyer
                        String sql_enseignement = "SELECT id FROM enseignement where classe_id = ? AND discipline_id = ? AND personne_id = ?";
                        PreparedStatement pst_enseignement = connect.prepareStatement(sql_enseignement);
                        pst_enseignement.setString(1, classe_id);
                        pst_enseignement.setString(2, nom_matiere);
                        pst_enseignement.setInt(3, id_prof);
                        ResultSet rs_enseignement = pst_enseignement.executeQuery();
                        if (rs_enseignement.next()) {
                            enseignement_id = rs_enseignement.getInt("id");
                            System.out.println("enseignement id : " + enseignement_id);
                            String sql = "INSERT INTO detailbulletin (bulletin_id,enseignement_id,appreciation) VALUES (?,?,?)";
                            PreparedStatement pst = connect.prepareStatement(sql);
                            System.out.println("bulletin id : " + bulletin_id);
                            pst.setInt(1, bulletin_id);
                            System.out.println("enseignement id : " + enseignement_id);
                            pst.setInt(2, enseignement_id);
                            pst.setString(3, " ");
                            pst.executeUpdate();
                        } else System.out.println("Hola Inna");
                    }
                }
            }
        }
        catch(SQLException exception){
                exception.printStackTrace();
            }


            return false;

    }



    public boolean create(DetailBulletin obj) {
        return false;
    }
    //Pas encore implémentée 
    public boolean delete(DetailBulletin obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean update(DetailBulletin obj)
    {
        return false;
    }
    
    
    public DetailBulletin find(int id)
    {
        DetailBulletin detailBulletin = new DetailBulletin();
        
        
        try{
            String sql = "SELECT * FROM detailbulletin WHERE id = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setInt(1, id);     
            pst.executeQuery();
            
            if(pst.executeQuery().first())
            {
                  detailBulletin = new DetailBulletin(id, pst.executeQuery().getString("appreciation"));
                  
                  EnseignementDAO enseignementDAO = new EnseignementDAO(this.connect);
                  BulletinDAO bulletinDAO = new BulletinDAO(this.connect);
                   
                  detailBulletin.setBulletin(bulletinDAO.find(pst.executeQuery().getInt("bulletin_id")));
                  detailBulletin.setEnseignement(enseignementDAO.find(pst.executeQuery().getInt("enseignement_id")));
            }
            /*ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM detailbulletin WHERE id = " + id);
            
            if(result.first())
            {
                detailBulletin = new DetailBulletin(id, result.getString("appreciation"));
                
                EnseignementDAO enseignementDAO = new EnseignementDAO(this.connect);
                BulletinDAO bulletinDAO = new BulletinDAO(this.connect);
                detailBulletin.setBulletin(bulletinDAO.find(result.getInt("bulletin_id")));
                detailBulletin.setEnseignement(enseignementDAO.find(result.getInt("enseignement_id")));
            }*/
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return detailBulletin;
    }
    
}
