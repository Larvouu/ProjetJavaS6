/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vue.JframePrincipal;
import vue.JpanelPageAccueil;
import vue.JpanelPageEnseignant;

/**
 *
 * @author ghias
 */
public class Main {

    private static JframePrincipal jframe1;
    private static JpanelPageAccueil pageAccueil;
    private static JpanelPageEnseignant pageEnseignant;

    //La méthode main
    public static void main(String[] args) {

        /**
         * MAIN DE SARAH (partie graphique)
         */
        jframe1 = new JframePrincipal();
        pageAccueil = new JpanelPageAccueil();
        pageEnseignant = new JpanelPageEnseignant();


        jframe1.setContentPane(pageAccueil);
        jframe1.setVisible(true);

        pageAccueil.getButtonConnexion().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Vous avez appuyé sur Connexion");
                System.out.println("Le nom de bdd que vous avez entré : " + pageAccueil.getTextNomBDD().getText());
                jframe1.remove(pageAccueil);
                jframe1.setContentPane(pageEnseignant);
                jframe1.setVisible(true);
            }
        });


        /**
         * MAIN DE INNA (modifier infos d'un élève)
         */

        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        String login_bdd =null;
        String pw_bdd=null;
        String name_bdd=null;
        String name=null;
        String firstname=null;
        String statut=null;

        String requete = null;


        //Connexion à la base de données



        System.out.println("Nom BBD ?");
        name_bdd = sc.next();

        System.out.println("login BBD ?");
        login_bdd = sc.next();

        System.out.println("pw BBD ?");
        pw_bdd = sc.next();
        //char pw = pw_bdd.charAt(0);






            try {
                if ( (name_bdd.equals("ecole") ) && (login_bdd.equals("root")) && (pw_bdd.equals("root")) ) {
                    Connexion maConnexion = new Connexion("ecole", "root", ""); // Inna il faut enlever le « root » pour le mdp
                    System.out.println("Connexion à la base de données successfull");
                    /// Connexion en tant qu'élève ou professeur
                    ///Si ses nom prenoms appartiennent à la table en tant qu'élève connexion en tant qu'élève
                    System.out.println("Nom ?");
                    name = sc.next();

                    System.out.println("prenom ?");
                    firstname = sc.next();

                    System.out.println("statut ?");
                    statut = sc.next();

                    ///Recuperer le statut ou prenom et nom correspondent

                    requete = "SELECT type FROM personne where nom = '" + name + "' AND prenom = '" + firstname + "' ";
                    ResultSet rs = maConnexion.getStmt().executeQuery(requete);
                    while (rs.next()) {
                        statut = rs.getString("type");
                    }

                    do {
                        if (statut.equals("eleve")) {
                            System.out.println("Connecté en tant qu'élève");
                            //L'enfant a accès aux fonctions suivantes
                            //1. consulter ses notes toutes (et les moyennes)
                            //2. Consulter ses profs
                            //3. Consulter les graphs
                        }

                        if (statut.equals("enseignant")) {
                            System.out.println("Connecte en tant qu'enseignant");

                            //L'enseignant a accès aux fonctions suivantes
                            //1. Afficher la liste de tous ces élèves
                            //2. Consulter les moyennes de ses classes
                            //3. Consulter les notes par élève
                            //4.Consulter les notes par classes par interro
                            //5. rechercher un eleve
                        }

                        System.out.println("Que souhaitez vous faire ?");
                        System.out.println("1. Afficher les tables");
                        System.out.println("2. Insérer des données");
                        System.out.println("3. Modifier les données");

                        int choix_menu = 0;
                        choix_menu = sc.nextInt();
                        switch (choix_menu) {
                            case 1:
                                //REQUETE SELECT
                                ResultSet résultats = null;

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
                                    rs = maConnexion.getStmt().executeQuery(requete);
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
                            case 2:
                                //DEBUT INSERER DONNEES
                                System.out.println("Dans quelle table voulezvous inserer des données ?");
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
                            case 3:
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
                                int champ_choix = 0;
                                champ_choix = sc.nextInt();

                                switch (champ_choix) {
                                    case 1:
                                        System.out.println("Quelle champ souhaitez vous modifier ?");
                                        System.out.println("1. nom");
                                        System.out.println("2. prenom");
                                        System.out.println("3. type");
                                        int choix_col_update = 0;
                                        choix_col_update = sc.nextInt();
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
                                                System.out.println("Veuillezrentrer le nouveau type");
                                                type_personne_update = sc.next();

                                                maConnexion.getStmt().executeUpdate(" UPDATE personne set  type='" + type_personne_update + "' where nom = '" + amodifier + "' ");
                                                break;
                                        }
                                    case 2:
                                        break;
                                    case 3:
                                        break;
                                }
                                break;
                        }
                    }while (!quit) ;

                }else
                {
                    System.out.println("Erreur ");
                    System.out.println(name_bdd);
                    System.out.println(login_bdd);
                    System.out.println(pw_bdd);
                    quit = true;
                }
            } catch (SQLException s) {
                System.out.println(s + " SQL excep");
            } catch (ClassNotFoundException e) {
                System.out.println(e + "Class Not Found !");

            }

        }
    }
