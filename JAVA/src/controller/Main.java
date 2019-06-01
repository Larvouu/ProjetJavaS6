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
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vue.JframePrincipal;
import vue.JpanelPageAccueil;
import vue.JpanelPageConnexion;
import vue.JpanelPageEleve;
import vue.JpanelPageEnseignant;

/**
 *
 * @author ghias
 */
public class Main {

    private static JframePrincipal jframe1;
    private static JpanelPageAccueil pageAccueil;
    private static JpanelPageEnseignant pageEnseignant;
    private static JpanelPageConnexion pageConnexion;
    private static JpanelPageEleve pageEleve;
    
    private static Connexion maConnexion;
    private static String name_bdd;
    private static String login_bdd;
    private static String pw_bdd;
    private static boolean connexionBDDOk;
    
    private static String nomUser;
    private static String prenomUser;
    private static String statutUser;
    private static String nomComparateur;
    private static String prenomComparateur;
    private static String statutComparateur;
    private static boolean nomPrenomExist = false;
   
    

    //La méthode main
    public static void main(String[] args) {

        /**
         * FUSION MAIN DE Inna & Sarah
         */
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        String statut = null;

        
        /**
         * MAIN DE SARAH (partie graphique)
         */
        
        //Le seul JFrame
        jframe1 = new JframePrincipal();

        //Les différents JPanel
        pageAccueil = new JpanelPageAccueil();
        pageEnseignant = new JpanelPageEnseignant();
        pageConnexion = new JpanelPageConnexion();
        pageEleve = new JpanelPageEleve();

        //Par défaut c'est la page d'accueil sur le Jframe
        jframe1.setContentPane(pageAccueil);
        jframe1.setVisible(true);

        //Si appuie sur bouton Connexion à la bdd
        pageAccueil.getButtonConnexion().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                
//                System.out.println("Vous avez appuyé sur Connexion");
//                System.out.println("Le nom du serveur entré : " + pageAccueil.getTextNomServeur().getText());
                
                
                //name_bdd prend la valeur de ce qui a été écrit dans le TextField
                name_bdd = pageAccueil.getTextNomBDD().getText();
                System.out.println("Le nom de bdd entré : " + pageAccueil.getTextNomBDD().getText());
                
                
                //login_bdd prend la valeur de ce qui a été écrit dans le TextField
                login_bdd = pageAccueil.getTextLogin().getText();
                System.out.println("Le login entré : " + pageAccueil.getTextLogin().getText());
                
                //pw_bdd prend la valeur de ce qui a été écrit dans le TextField
                System.out.println("Le mdp entré : " + pageAccueil.getTextNomMdp().getText());
                pw_bdd = pageAccueil.getTextNomMdp().getText();

                
                //Si le user a saisi les bonnes infos pour se connecter à la bdd
                if ((name_bdd.equals("ecole")) && (login_bdd.equals("root")) && (pw_bdd.equals("root"))) 
                {
                    System.out.println("Connexion à la base de données successfull");
                    try 
                    {
                        //Connexion à la bdd
                        maConnexion = new Connexion("ecole", "root", "");
                        JOptionPane.showMessageDialog(null, "SUCCES : Connexion à la bdd");
                    } 
                    catch (SQLException | ClassNotFoundException ex) 
                    {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    connexionBDDOk = true;

                } 
                else 
                {
                    System.out.println("Vous n'avez pas saisi de bonnes donnees");
                }

                //On change de page : pageAccueil -> pageConnexion
                jframe1.remove(pageAccueil);
                jframe1.setContentPane(pageConnexion);
                jframe1.setVisible(true);
            }
        });

        
        //Si appuie sur bouton Connexion (identification d'un eleve ou enseignant)
        pageConnexion.getButtonSubmitConnexion().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(connexionBDDOk)
                {
                    //Utile pour récupérer ce que le user a selectionné
                    pageConnexion.getJRadioButtonEleve().setActionCommand("eleve");
                    pageConnexion.getJRadioButtonProfesseur().setActionCommand("prof");

                    //petits println de vérifications
                    System.out.println("Vous avez appuyé sur submit du formulaire");
                    System.out.println("Le nom du user entré : " + pageConnexion.getTextNom().getText());
                    System.out.println("Le nom de bdd entré : " + pageConnexion.getTextPrenom().getText());
                    System.out.println("Statut sélectionné: "+pageConnexion.getButtonGroup().getSelection().getActionCommand());
                    
                    
                    //Initialisation de ces 3 variables
                    nomUser=pageConnexion.getTextNom().getText();
                    prenomUser=pageConnexion.getTextPrenom().getText();
                    statutUser=pageConnexion.getButtonGroup().getSelection().getActionCommand();

                    //Assurons-nous que le nom et prénom entrés existent vraiment dans la bdd
                    String requete = "SELECT nom,prenom,type FROM personne where nom = '" + nomUser + "' AND prenom = '" + prenomUser + "' ";

                    ResultSet rs;

                    try 
                    {

                        rs = maConnexion.getStmt().executeQuery(requete);
                        if(rs.next()) 
                        {
                            nomComparateur= rs.getString("nom");
                            prenomComparateur=rs.getString("prenom");
                            statutComparateur=rs.getString("type");
                            
                            nomPrenomExist=true;

                        }
                    } 
                    catch (SQLException ex) 
                    {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        
                    }
                    System.out.println("nom Comparateur : "+nomComparateur);
                    System.out.println("nomUser : "+nomUser);
                    System.out.println("prenom Comparateur : "+prenomComparateur);
                    System.out.println("prenomUser : "+prenomUser);
                    
//                    System.out.println("statutComparateur : "+statutComparateur);
//                    System.out.println("pageConnexion.getButt : "+statutUser);
                    
                    //Si le nom et prenom entrés existe dans la bdd et que c'est le bon statut qui a été selectionné 
                    if (nomPrenomExist && statutUser.equals(statutComparateur)) 
                    {
                        
                        //Selon si le user a selcetionné "eleve" ou "prof", le rediriger sur  la bonne page
                        if("eleve".equals(pageConnexion.getButtonGroup().getSelection().getActionCommand()))
                        {
                            JOptionPane.showMessageDialog(null, "Nom et prenom et statut ont matché ! "+nomComparateur +" "+prenomComparateur+" "+statutUser);
                            jframe1.remove(pageConnexion);
                            jframe1.setContentPane(pageEleve);
                            jframe1.setVisible(true);
                        }
                        else if ("prof".equals(pageConnexion.getButtonGroup().getSelection().getActionCommand()))
                        {
                            JOptionPane.showMessageDialog(null, "Nom et prenom et statut ont matché ! "+nomComparateur +" "+prenomComparateur+" "+statutUser);
                            jframe1.remove(pageConnexion);
                            jframe1.setContentPane(pageEnseignant);
                            jframe1.setVisible(true);
                        }
                        else 
                            System.out.println("Vous n'avez pas selcetionné votre statut OU BIEN vous n'êtes pas un "+statutUser+" !");



                    } 
                    else if(nomPrenomExist==false)
                    {
                        JOptionPane.showMessageDialog(null, "Votre nom et prénom n'existe pas dans la bdd.");

                    
                    }
                    else if (nomPrenomExist)
                    {
                        JOptionPane.showMessageDialog(null, "Vous n'êtes pas un "+statutUser+" ! ");

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Vos saisies sont inexactes.");

                    }

                }
               
                
            }
        });

        
        //BOUTONS DE DECONNEXION QUI FAIIT RETOURNER A PAGE DE CONNEXION
        pageEleve.getButtonDeconnexion().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                jframe1.remove(pageEleve);
                jframe1.setContentPane(pageConnexion);
                jframe1.setVisible(true);
                nomUser="";
                prenomUser="";
                statutUser="";
                nomComparateur="";
                prenomComparateur="";
                statutComparateur="";
                nomPrenomExist=false;
                JOptionPane.showMessageDialog(null, "Vous êtes déconnecté.");
            }
        });
        
         pageEnseignant.getButtonDeconnexion().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                jframe1.remove(pageEnseignant);
                jframe1.setContentPane(pageConnexion);
                jframe1.setVisible(true);
                nomUser="";
                prenomUser="";
                statutUser="";
                nomComparateur="";
                prenomComparateur="";
                statutComparateur="";
                nomPrenomExist=false;
                JOptionPane.showMessageDialog(null, "Vous êtes déconnecté.");
            }
        });
        
        
        
        
        /**
         * MAIN DE INNA (modifier infos d'un élève)
         */

        /*
         Scanner sc = new Scanner(System.in);
         boolean quit = false;
         String login_bdd =null;
         String pw_bdd=null;
         String name_bdd=null;
         String name=null;
         String firstname=null;
         String statut=null;

         String requete = null;
         */
        //Connexion à la base de données

        /*

         System.out.println("Nom BBD ?");
         name_bdd = sc.next();

         System.out.println("login BBD ?");
         login_bdd = sc.next();

         System.out.println("pw BBD ?");
         pw_bdd = sc.next();
         */
        //char pw = pw_bdd.charAt(0);
        
        
        /*try {
            if (connexionBDDOk) {
                maConnexion = new Connexion("ecole", "root", ""); // Inna il faut enlever le « root » pour le mdp
                //Connexion maConnexion = new Connexion("ecole", "root", ""); // Inna il faut enlever le « root » pour le mdp
                System.out.println("Connexion à la base de données successfull");
                    /// Connexion en tant qu'élève ou professeur
                ///Si ses nom prenoms appartiennent à la table en tant qu'élève connexion en tant qu'élève
                System.out.println("Nom ?");
                prenomUser = sc.next();

                System.out.println("prenom ?");
                nomUser = sc.next();

                System.out.println("statut ?");
                statut = sc.next();

                    ///Recuperer le statut ou prenom et nom correspondent
                requete = "SELECT type FROM personne where nom = '" + prenomUser + "' AND prenom = '" + nomUser + "' ";
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
                } while (!quit);

            } else {
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
                
                */

    }
}
