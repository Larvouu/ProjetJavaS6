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
        pst2.setString(3, "");
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
