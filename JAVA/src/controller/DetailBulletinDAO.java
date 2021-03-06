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
 *Permet de gerer les sous parties du bulletin grace a la bdd
 * @author ghias
 * @author Inna
 */
public class DetailBulletinDAO extends DAO<DetailBulletin> {

    public DetailBulletinDAO(Connection conn) {
        super(conn);
    }
 /**
 * Create Detail Bulletin DAO 
 * @param obj DetailBulletin
 * @param  eleve Personne
 * @param  prof Personne
 * @return int DetailBulletin_id
 * 
 */
    public int create_detailbulletin(DetailBulletin obj, Personne eleve, Personne prof) {

        int enseignement_id = 0;
        int bulletin_id;
        String nom_matiere = null;
        int inscription_id = 0;
        String classe_id = null;
        int detailbulletin_id = 0;
         Scanner sc = new Scanner(System.in);
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
                

                ///On peut maintenant créer le detail bulletin
                do
                {
                System.out.println("Veuillez ecrire l'enseignement concerné");
               
                nom_matiere = sc.next();
                }while(!(nom_matiere.equals("francais"))&&!(nom_matiere.equals("maths"))&&!(nom_matiere.equals("sciences"))&&!(nom_matiere.equals("musique"))&&!(nom_matiere.equals("EPS")));
                

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
                       
//On recupere l'enseignement car maintenant on a les données du prof et de l'eleve et la matière qu'il veut noter
                        String sql_enseignement = "SELECT id FROM enseignement where classe_id = ? AND discipline_id = ? AND personne_id = ?";
                        PreparedStatement pst_enseignement = connect.prepareStatement(sql_enseignement);
                        pst_enseignement.setString(1, classe_id);
                        pst_enseignement.setString(2, nom_matiere);
                        pst_enseignement.setInt(3, id_prof);
                        ResultSet rs_enseignement = pst_enseignement.executeQuery();
                        if (rs_enseignement.next()) {
                            enseignement_id = rs_enseignement.getInt("id");
                        
                            //Si le sa matiere est deja dans le bulletin il ne faut pas la réajouter

                            try {
                                String sql_existe = "SELECT id FROM detailbulletin where bulletin_id = ? AND enseignement_id = ?";
                                PreparedStatement pst_existe = connect.prepareStatement(sql_existe);
                                pst_existe.setInt(1, bulletin_id);
                                pst_existe.setInt(2, enseignement_id);
                                ResultSet rs_existe = pst_existe.executeQuery();
                                if (rs_existe.next() == true) {
                                    rs_existe.next();
                                    System.out.println("Une sous partie pour cette matière existe deja, les notes y seront ajoutées");
                                } else {

                                    String sql = "INSERT INTO detailbulletin (bulletin_id,enseignement_id,appreciation) VALUES (?,?,?)";
                                    PreparedStatement pst = connect.prepareStatement(sql);
                                   
                                    pst.setInt(1, bulletin_id);
                                   
                                    pst.setInt(2, enseignement_id);
                                    System.out.println("Veuillez rentrer votre appreciation ?");
                                    sc.nextLine();
                                    String appreciation = sc.nextLine();
                                    pst.setString(3, appreciation);
                                    pst.executeUpdate();

                                }
                            }catch (SQLException exception) { exception.printStackTrace();}

                            try {
                                //on recupere l'id du detail bulletin id
                                String sql_DBI = "SELECT id FROM detailbulletin where bulletin_id = ? AND enseignement_id = ?";
                                PreparedStatement pst_DBI = connect.prepareStatement(sql_DBI);
                                pst_DBI.setInt(1, bulletin_id);
                                pst_DBI.setInt(2, enseignement_id);
                     
                                ResultSet rs_DBI = pst_DBI.executeQuery();
                                if (rs_DBI.next()) {

                                    detailbulletin_id = rs_DBI.getInt("id");
                                }

                            } catch (SQLException exception) {
                                exception.printStackTrace();

                            }
                        }
                    }
                }

            }

        } catch (SQLException exception)
        {
            exception.printStackTrace();

        }
        return detailbulletin_id;
    }
    





    

    public boolean create(DetailBulletin obj) {
        return false;
    }

    //Pas encore implémentée 
    public boolean delete(DetailBulletin obj) {
        return false;
    }

    //Pas encore implémentée 
    public boolean update(DetailBulletin obj) {
        return false;
    }
 /**
 * Find DetailBulletin DAO 
 * @param id  int
 * @return  DetailBulletin
 * 
 */
    public DetailBulletin find(int id) {
        DetailBulletin detailBulletin = new DetailBulletin();

        try {
            String sql = "SELECT * FROM detailbulletin WHERE id = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeQuery();

            if (pst.executeQuery().first()) {
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
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return detailBulletin;
    }

}
