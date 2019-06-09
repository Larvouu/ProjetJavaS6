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

import modele.Bulletin;
import controller.DAO;
import modele.*;
/**
 *
 * @author ghias
 */
public class BulletinDAO extends DAO<Bulletin> {
    
    public BulletinDAO(Connection conn) 
    {
        super(conn);
    }
    
    public void Afficher_bulletinDAO(Personne eleve)
    {
        int id_eleve=0;
        String classe_id = null;
        System.out.println("****Bulletin trimestrielle *****");
        try 
            {
               String sql_ideleve = "SELECT id FROM personne WHERE nom = ? AND prenom = ?";
               PreparedStatement pst_ideleve = connect.prepareStatement(sql_ideleve);
               pst_ideleve.setString(1, eleve.getNom());
                pst_ideleve.setString(2, eleve.getPrenom());
                ResultSet rs_ideleve= pst_ideleve.executeQuery();
                rs_ideleve.next();
                System.out.println("            Nom : " +eleve.getNom() + "           Prenom : " + eleve.getPrenom());
                id_eleve= rs_ideleve.getInt("id");
                eleve.setId(rs_ideleve.getInt("id"));
         
               
                ///On recupere la classe
               String sql_classe = "SELECT classe_id FROM inscription where personne_id= ?";
               PreparedStatement pst_classe =connect.prepareStatement(sql_classe);
               pst_classe.setInt(1, id_eleve);
               ResultSet rs_classe = pst_classe.executeQuery();
               if(rs_classe.next())
               {
                   classe_id = rs_classe.getString("classe_id");
                   System.out.println("En " +classe_id);
            
               }
               
               
               //On recupere le Bulletin
               int id_inscription=0;
               String sql_idinscription = "SELECT id FROM inscription WHERE personne_id= ?";
               PreparedStatement pst_idinscription = connect.prepareStatement(sql_idinscription);
               pst_idinscription.setInt(1, id_eleve);
               ResultSet rs_idinscription = pst_idinscription.executeQuery();
               if(rs_idinscription.next()==true)
               {
                  id_inscription=rs_idinscription.getInt("id");
                   
               }
               
                int id_bulletin=0;
               String sql_bull = "SELECT id FROM bulletin WHERE inscription_id= ?";
               PreparedStatement pst_bull = connect.prepareStatement(sql_bull);
               pst_bull.setInt(1, id_inscription);
               ResultSet rs_bull = pst_bull.executeQuery();
               if(rs_bull.next()==true)
               {
                  id_bulletin=rs_bull.getInt("id");
                  
               }
               ///On recupere les infos de chaque matiere
                int id_db=0;
                String nom_matiere=null;
                String nomprof=null;
                String prenomprof=null;
                int id_enseignement=0;
                int id_prof=0;
               String sql_db = "SELECT id FROM detailbulletin WHERE bulletin_id= ?";
               PreparedStatement pst_db = connect.prepareStatement(sql_db);
               pst_db.setInt(1, id_bulletin);
               ResultSet rs_db = pst_db.executeQuery();
               while(rs_db.next()==true)
               {
                     id_db=rs_db.getInt("id");
             
                   //Ensignement id
                   String sql_ens = "SELECT enseignement_id FROM detailbulletin WHERE id= ?";
                  
                   PreparedStatement pst_ens=connect.prepareStatement(sql_ens);
                   pst_ens.setInt(1, id_db);
                   ResultSet rs_ens = pst_ens.executeQuery();
                 
                   if(rs_ens.next())
                   {
                       
                   id_enseignement=rs_ens.getInt("enseignement_id");
      
                
                   
                 //nom matiere
                   String sql_mat = "SELECT discipline_id FROM enseignement WHERE id= ?";
                   PreparedStatement pst_mat=connect.prepareStatement(sql_mat);
                   pst_mat.setInt(1, id_enseignement);
                   ResultSet rs_mat = pst_mat.executeQuery();
                  if( rs_mat.next()){
                   nom_matiere=rs_mat.getString("discipline_id");
                   System.out.println("** " + nom_matiere + " ** ");
                   
                   
                  //prof
                   String sql_prof = "SELECT personne_id FROM enseignement WHERE id=?";
                   PreparedStatement pst_prof=connect.prepareStatement(sql_prof);
                   pst_prof.setInt(1, id_enseignement);
                   ResultSet rs_prof = pst_prof.executeQuery();
                   if(rs_prof.next()){
                   id_prof=rs_prof.getInt("personne_id");
                 
                   String sql_nomprof = "SELECT nom FROM personne WHERE id=?";
                   PreparedStatement pst_nomprof=connect.prepareStatement(sql_nomprof);
                   pst_nomprof.setInt(1, id_prof);
                   ResultSet rs_nomprof = pst_nomprof.executeQuery();
                   if(rs_nomprof.next())
                   { 
                   nomprof=rs_nomprof.getString("nom");
                  
                   
                    String sql_prenomprof = "SELECT prenom FROM personne WHERE id=?";
                   PreparedStatement pst_prenomprof=connect.prepareStatement(sql_prenomprof);
                   pst_prenomprof.setInt(1, id_prof);
                   ResultSet rs_prenomprof = pst_prenomprof.executeQuery();
                   if(rs_prenomprof.next())
                   {
                   prenomprof=rs_prenomprof.getString("prenom");
                    System.out.println("Dispensé par Mr ou Mme  " + nomprof+ " " +prenomprof);
           
                   
                   /// Calcul des moyennes par matières
                   double moyenne=0;
                   int somme=0;
                   int nb_note=0;
                   String sql_note="SELECT note FROM evaluation WHERE detailbulletin_id = ?";
                   PreparedStatement pst_note=connect.prepareStatement(sql_note);
                   pst_note.setInt(1, id_db);
                   ResultSet rs_note=pst_note.executeQuery();
                   while(rs_note.next()==true)
                   {
                       somme+=+rs_note.getInt("note");
                       nb_note++;
                   }
                   moyenne=somme/nb_note;
                   String appreciation=null;
                   String sql_app="SELECT appreciation FROM evaluation WHERE detailbulletin_id = ?";
                   PreparedStatement pst_app=connect.prepareStatement(sql_app);
                   pst_app.setInt(1, id_db);
                   ResultSet rs_app=pst_app.executeQuery();
                   if(rs_app.next())
                   {
                       appreciation=rs_app.getString("appreciation");
                       System.out.println(appreciation);
                   }
                       
                       
                   
                   }
                   }
                   } 
                   
               }
                  
            }
               } 
               
           
            }
                       
           
           catch (SQLException exception)
            {
                exception.printStackTrace();
            }
    }
            
            
    
    
    
     //Pas encore implémentée
    public boolean create(Bulletin obj)
    {

        return false;
    }


   public int create_bulletin(Bulletin obj, Personne eleve) {
        int id_inscription = 0;
        System.out.println("eleve id " +eleve.getId());
        int trimestre = 0;
        int bulletin_id = 0;
        String appreciation=null;

        ///il faut lier ce bulletin à l'inscription d'un élève
        try {

                String sql="SELECT id FROM inscription where personne_id = ?";
                PreparedStatement pst = connect.prepareStatement(sql);
                pst.setInt(1, eleve.getId());
                ResultSet  rs	=pst.executeQuery();

                rs.next();

                id_inscription = rs.getInt("id");
                System.out.println("inscription id" +id_inscription);

            System.out.println("Dans quel trimestre vous situez-vous ");
            Scanner sc = new Scanner(System.in);

            trimestre = sc.nextInt();

// On checke si un bulletin au meme trimestre et au meme id inscription existe deja
            try {

                String sql_existe="SELECT id FROM bulletin where trimestre_id = ? AND inscription_id = ?";
                PreparedStatement pst_existe = connect.prepareStatement(sql_existe);
                pst_existe.setInt(1, trimestre);
                pst_existe.setInt(2, id_inscription);
                ResultSet  rs_existe=pst_existe.executeQuery();
if (rs_existe.next()==true)
{
    rs_existe.next();
    System.out.println("Un bulletin existe dejà pour cet élève");
}
else
{

    try {
        String sql2 = "INSERT INTO bulletin (trimestre_id, inscription_id,appreciation) VALUES (?,?,?)";
        PreparedStatement pst2 = connect.prepareStatement(sql2);
        pst2.setInt(1, trimestre);
        pst2.setInt(2, id_inscription);
        System.out.println("Veuillez rentrer l'appreciation generale du bulletin");
        appreciation=sc.nextLine();
        sc.nextLine();
        pst2.setString(3, appreciation);
        pst2.executeUpdate();
    } catch (SQLException exception) {
        exception.printStackTrace();
    }

} } catch (SQLException exception) {
                exception.printStackTrace();
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }


        try {


            String sql2 = "SELECT id FROM bulletin where trimestre_id = ? AND inscription_id= ?";
            PreparedStatement pst2 = connect.prepareStatement(sql2);
            pst2.setInt(1, trimestre);
            pst2.setInt(2, id_inscription);
            ResultSet rs2 = pst2.executeQuery();

            rs2.next();

            bulletin_id = rs2.getInt("id");
            System.out.println("bulletin id : " + bulletin_id);
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return bulletin_id; //l'id du bulletin

    //Pas encore implémentée 
   }
    public boolean delete(Bulletin obj)
    {
        return false;
    }
    
    //Pas encore implémentée 
    public boolean update(Bulletin obj)
    {
        return false;
    }
    
    
    public Bulletin find(int id)
    {
        Bulletin bulletin = new Bulletin();
        Personne pers = new Personne();
        
        
        try{
            String sql = "SELECT * FROM bulletin WHERE id = ?";
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setInt(1, id);     
            pst.executeQuery();
            
            if(pst.executeQuery().first())
            {
                bulletin = new Bulletin(id, pst.executeQuery().getString("appreciation"));
                
                TrimestreDAO trimestreDAO = new TrimestreDAO(this.connect);
                InscriptionDAO inscriptionDAO = new InscriptionDAO(this.connect);
                
                bulletin.setInscription(inscriptionDAO.find(pst.executeQuery().getInt("inscription_id")));
                bulletin.setTrimestre(trimestreDAO.find(pst.executeQuery().getInt("trimestre_id")));
            }
            /*ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM bulletin WHERE id = " + id);
            
            if(result.first())
            {
                bulletin = new Bulletin(id, result.getString("appreciation"));
                
                TrimestreDAO trimestreDAO = new TrimestreDAO(this.connect);
                InscriptionDAO inscriptionDAO = new InscriptionDAO(this.connect);
                
                bulletin.setInscription(inscriptionDAO.find(result.getInt("inscription_id")));
                bulletin.setTrimestre(trimestreDAO.find(result.getInt("trimestre_id")));
            }*/
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        
        return bulletin;
    }
    
}
