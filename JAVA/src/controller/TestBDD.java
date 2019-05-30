/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testbdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Sarah
 */
public class TestBDD {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestJframe().setVisible(true);
            }
        });

        //DEBUT PARTIE INNA
        try {
            //Connexion à la base de données
            Connexion maConnexion = new Connexion("ecole", "root", "");
            System.out.println("Connexion à la base de données successfull");

            System.out.println("Que souhaitez vous faire ?");
            System.out.println("1. Afficher les tables");
            System.out.println("2. Insérer des données");
            System.out.println("3. Modifier les données");
            Scanner sc = new Scanner(System.in);
            int choix_menu=0;
            choix_menu=sc.nextInt();

            switch(choix_menu)
            {
                case 1 :
                    //REQUETE SELECT
                    ResultSet résultats = null;
                    String requete = null;
                    System.out.println("Quel table voulez vous afficher ?");
                    System.out.println("1. Personne");
                    System.out.println("2. Eleve");
                    System.out.println("3. blabla");

                    int choix_table = sc.nextInt();
                    try {
                        switch (choix_table) {
                            case 1:
                                requete = "SELECT * FROM personne";

                                break;

                            case 2:
                                requete = "SELECT * FROM personne";
                                break;

                            case 3:
                                requete = "SELECT * FROM personne";
                                break;

                        }

                        ResultSet rs = maConnexion.getStmt().executeQuery(requete);
                        while (rs.next()) {
                            String type = rs.getString("type");
                            System.out.println("type = " + type);
                            String prenom = rs.getString("prenom");
                            System.out.println("prenom = " + prenom);
                            String nom = rs.getString("nom");
                            System.out.println("nom = " + nom);
                        }

                    } catch (SQLException e) {

                        //traitement de l'exception
                        System.out.println("exectepeiton SQL" + e);

                    }
                    break;

                    ///FIN AFFICHER TABLE


                case 2 :
                    //DEBUT INSERER DONNEES

                    System.out.println("Dans quelle table voulez-vous inserer des données ?");
                    System.out.println("1. Personne");
                    System.out.println("2. Eleve");
                    System.out.println("3. blabla");
                    String table_insert = null;
                    String nom_personne = null;
                    String prenom_personne = null;
                    String type_personne = null;




                        int choix_table_donnees = sc.nextInt();
                        switch (choix_table_donnees) {
                            case 1:
                                table_insert = "personne";
                                System.out.println("Veuillez rentrer le nouveau nom");
                                nom_personne = sc.next();
                                System.out.println("Veuillez rentrer le nouveau prenom");
                                prenom_personne = sc.next();
                                System.out.println("Veuillez rentrer le nouveau type");
                                type_personne = sc.next();
                                maConnexion.getStmt().executeUpdate("INSERT into personne (nom, prenom, type) VALUES (' " + nom_personne + " ',' " + prenom_personne + " ',' " + type_personne + " ')");
                                break;

                            case 2:
                                table_insert = "eleve";
                                break;

                            case 3:
                                table_insert = "blabla";
                                break;

                        }

                        break;

                        //FIN INSERER DONNEES


                        case 3 :

                            //DEBUT MODIF DONNEES
                            System.out.println("Quelle table souhaitez vous update");
                            System.out.println("1. Personne");
                            System.out.println("2. Eleve");
                            System.out.println("3. blabla");
                            String table_update = null;
                            String nom_personne_update = null;
                            String prenom_personne_update = null;
                            String type_personne_update = null;
                            String amodifier = null;
                            int champ_choix=0;
                            champ_choix=sc.nextInt();
                            switch (champ_choix) {
                                case 1:
                                    System.out.println("Quelle champ souhaitez vous modifier ?");
                                    System.out.println("1. nom");
                                    System.out.println("2. prenom");
                                    System.out.println("3. type");
                                    int choix_col_update = 0;
                                    choix_col_update=sc.nextInt();
                                    switch (choix_col_update) {

                                        case 1:
                                            System.out.println("Rentre le prenom de la personne a modifier");
                                            amodifier = sc.next();
                                            System.out.println("Veuillez rentrer le nouveau nom");
                                            nom_personne_update = sc.next();
                                            maConnexion.getStmt().executeUpdate(" UPDATE personne set nom='" + nom_personne_update + "' where prenom = '" + amodifier + "' ");

                                            break;

                                        case 2:
                                            System.out.println("Rentre le nom de la personne a modifier");
                                            amodifier = sc.next();
                                            System.out.println("Veuillez rentrer le nouveau prenom");
                                            prenom_personne_update = sc.next();
                                            maConnexion.getStmt().executeUpdate(" UPDATE personne set prenom='" + prenom_personne_update + "' where nom = '" + amodifier + "' ");

                                            break;

                                        case 3:
                                            System.out.println("Rentre le nom de la personne a modifier");
                                            amodifier = sc.next();
                                            System.out.println("Veuillez rentrer le nouveau type");
                                            type_personne_update = sc.next();
                                            maConnexion.getStmt().executeUpdate(" UPDATE personne set type='" + type_personne_update  + "' where nom = '" + amodifier + "' ");


                                            break;
                                    }
                                case 2:
                                    break;

                                case 3:
                                    break;

                            }

                       break;


                    }







            } catch (SQLException s) {
                System.out.println(s + " SQL excep");



        } catch (ClassNotFoundException e)
        {
            System.out.println(e + "gfzgugafigufgagfa");
        }
    }
}