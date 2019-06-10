/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import modele.Inscription;
import vue.JframePrincipal;
import vue.JpanelPageAccueil;
import vue.JpanelPageConnexion;
import vue.JpanelPageEleve;
import vue.JpanelPageEnseignant;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import vue.JpanelAdmin;
import modele.*;
import vue.JChartLine;
import vue.JpanelAfficherEleveClasse;
import vue.JpanelAjouterEleveForm;
import vue.JpanelRechercheProf;
import vue.JpanelRechercherClasse;
import vue.JpanelRechercherEleve;
import vue.JpanelRechercherEleveParEnseignant;
import vue.JpanelSupprEleveForm;


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
    private static JpanelAdmin pageAdmin;
    private static JpanelAjouterEleveForm pageAjouterEleveForm;
    private static JpanelSupprEleveForm pageSupprEleveForm;
    private static JpanelRechercherEleve pageRechercherEleve;
    private static JpanelRechercheProf pageRechercherProf;
    private static JpanelRechercherEleveParEnseignant pageRechercherEleveParEnseignant;
    private static JpanelRechercherClasse pageRechercherClasse;
    //private static JpanelAfficherEleveClasse pageAfficherEleveClasse;
    
    
    private static Connexion maConnexion;
    private static String name_bdd;
    private static String login_bdd;
    private static String pw_bdd ="";
    private static boolean connexionBDDOk;
    
    private static String nomUser;
    private static String prenomUser;
    private static String statutUser;
    
    private static String nomComparateur;
    private static String prenomComparateur;
    private static String statutComparateur;
    private static boolean nomPrenomExist = false;
    
    private static PersonneDAO personneDAO;
    private static Personne personne;
    
    private static boolean isPageAdmin = false;
    
    
    private static JChartLine graph;//je suis là
   
    

    //La méthode main
    public static void main(String[] args) {

        //Le seul JFrame
        jframe1 = new JframePrincipal();
        jframe1.setLocationRelativeTo(null);
        jframe1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Les différents JPanel
        pageAccueil = new JpanelPageAccueil();
        pageEnseignant = new JpanelPageEnseignant();
        pageConnexion = new JpanelPageConnexion();
        pageEleve = new JpanelPageEleve();
        pageAdmin = new JpanelAdmin();
        pageAjouterEleveForm = new JpanelAjouterEleveForm();
        pageSupprEleveForm = new JpanelSupprEleveForm();
        pageRechercherEleve = new JpanelRechercherEleve();
        pageRechercherProf = new JpanelRechercheProf();
        pageRechercherEleveParEnseignant = new JpanelRechercherEleveParEnseignant();
        pageRechercherClasse = new JpanelRechercherClasse();
  
        graph = new JChartLine("Graphe notes de l'elève / moyennnes de sa classe"); 

        //Par défaut c'est la page d'accueil sur le Jframe
        jframe1.setContentPane(pageAccueil);
        jframe1.setVisible(true);
        
        graph.pack();  
        graph.setSize(600, 400);  

        


        //Si appuie sur bouton Connexion à la bdd
        pageAccueil.getButtonConnexion().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {

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
                if ((name_bdd.equals("ecole")) && (login_bdd.equals("root")) && ((pw_bdd.equals("root")||pw_bdd.equals("")))) 
                {
                    System.out.println("Connexion à la base de données successfull");
                    try 
                    {
                        //Connexion à la bdd
                        maConnexion = new Connexion("ecole", "root", ""); //inna
                        
                        JOptionPane.showMessageDialog(pageAccueil , "SUCCES : Connexion à la bdd");
                    } 
                    catch (SQLException | ClassNotFoundException ex) 
                    {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                    connexionBDDOk = true;
    
                    //On change de page : pageAccueil -> pageConnexion
                    jframe1.remove(pageAccueil);
                    jframe1.setContentPane(pageConnexion);
                    jframe1.setVisible(true);

                } 
                else 
                {
                    JOptionPane.showMessageDialog(pageAccueil,"Vous n'avez pas saisi de bonnes donnees");
                }

                
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

                    
                    //Si le nom et prenom entrés existe dans la bdd et que c'est le bon statut qui a été selectionné 
                    if (nomPrenomExist && statutUser.equals(statutComparateur)) 
                    {
                        
                        //Selon si le user a selcetionné "eleve" ou "prof", le rediriger sur  la bonne page
                        if("eleve".equals(pageConnexion.getButtonGroup().getSelection().getActionCommand()))
                        {
                            
                            
                           personneDAO = new PersonneDAO(maConnexion.getConnection());
                            
                            JOptionPane.showMessageDialog(pageConnexion, "Bonjour "+statutUser+" "+prenomComparateur +" "+nomComparateur+".");
                            jframe1.remove(pageConnexion);
                            
                            
                            //On set les infos de l'élève qui vient de se connecter AVANT D'AFFICHER LA PAGE ELEVE
                            pageEleve.getJLabelNom().setText(nomUser);
                            pageEleve.getJLabelPrenom().setText(prenomUser);
                            //String requete2 = "SELECT classe_id FROM personne where nom = '" + nomUser + "' AND prenom = '" + prenomUser + "' ";
                                
                            try 
                            {
                                String sql_e = "SELECT * FROM personne where nom = '" + nomUser + "' AND prenom = '" + prenomUser + "' ";
                                PreparedStatement pst_e = maConnexion.getConnection().prepareStatement(sql_e);
                                ResultSet rs_e = pst_e.executeQuery();
                                
                                if(rs_e.next())
                                {
                                    String sql_e2 = "SELECT * FROM inscription WHERE personne_id = ?";
                                    PreparedStatement pst_e2 = maConnexion.getConnection().prepareStatement(sql_e2);
                                    pst_e2.setInt(1, rs_e.getInt("id"));
                                    ResultSet rs_e2 = pst_e2.executeQuery();
                                    
                                    if(rs_e2.next())
                                    {
                                        String classeEleve = rs_e2.getString("classe_id");
                                        pageEleve.getJLabelClasse().setText(classeEleve);
                                    }
                                    
                                }

                            } 
                            catch (SQLException ex) 
                            {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                
                            personne = new Personne();
                            personne.setNom(nomUser);
                            personne.setPrenom(prenomUser);
                            personne.setType(statutUser);
                                    
                                    
                            //On affiche la page élève
                            jframe1.setContentPane(pageEleve);
                            jframe1.setVisible(true);
                        }
                        else if ("prof".equals(pageConnexion.getButtonGroup().getSelection().getActionCommand()))
                        {
                            isPageAdmin=false;
                            pageEnseignant.getjLabelNomProf().setText(nomUser);
                            pageEnseignant.getjLabelPrenomProf().setText(prenomUser);
                            
                            personneDAO = new PersonneDAO(maConnexion.getConnection());
                            personne = new Personne();

                            personne.setNom(nomUser);
                            personne.setPrenom(prenomUser);
                            personne.setType(statutUser);
                            
                            JOptionPane.showMessageDialog(pageConnexion, "Bonjour "+statutUser+" "+prenomComparateur +" "+nomComparateur+".");
                            jframe1.remove(pageConnexion);
                            jframe1.setContentPane(pageEnseignant);
                            jframe1.setVisible(true);

                        }
                        else 
                            JOptionPane.showMessageDialog(pageConnexion, "Vos saisies sont incorrectes.");

                    } 
                    else if(nomPrenomExist==false)
                    {
                        JOptionPane.showMessageDialog(pageConnexion, "Vos saisies sont incorrectes. Ré-essayez.");
                    
                    }
                    else if (nomPrenomExist)
                    {
                        JOptionPane.showMessageDialog(pageConnexion, "Vous n'êtes pas un "+statutUser+" ! ");

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(pageConnexion, "Vos saisies sont inexactes.");

                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Vous n'êtes pas connecté au serveur/à la bdd.");
                }
               
                
            }
        });
        
        //ici il faudrait faire la meme chose pour le modifier des profs enifn que ce soit le mem bouton quoi
        pageEleve.getJButtonModifInfos().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                   personneDAO.update(personne);

                }
           });

        /**
         * Bouton "Mon Bulletin" de la page Eleve
         */
        pageEleve.getjButtonBulletin().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                  BulletinDAO bulletinDAO = new BulletinDAO(maConnexion.getConnection());
                  Bulletin bulletin= new Bulletin();
                  
                  bulletinDAO.Afficher_bulletinDAO(personne);
                  
                    
                }
           });
        
        /**
         * Un eleve peut voir l'évolution de ses notes 
         */
        pageEleve.getjButtonEvolutionNotes().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    PersonneDAO personneDAO_afficherGraphe = new PersonneDAO(maConnexion.getConnection());
                    if(personneDAO_afficherGraphe.afficherGraphe(personne,graph))
                    {
                        graph.setVisible(true); //je suis là
                    }
                    else{
                        System.out.println("Probleme lors des requetes SQL : pas de correspondance");
                    }
                    //graph.getDataset().addValue(50, graph.getSeries1(), "Note XXX");
                    
                }
           });

        /**
         * Methode AddActionListener du bouton "Supprimer un élève"
         * 
         */
        pageEnseignant.getJButtonSuppEleve().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                isPageAdmin=false;
                jframe1.remove(pageEnseignant);
                jframe1.setContentPane(pageSupprEleveForm);
                jframe1.setVisible(true);
                
            
            }

        });
        
        pageSupprEleveForm.getjButtonSubmitSupprPersonne().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                PersonneDAO pers = new PersonneDAO(maConnexion.getConnection());
                Personne personne_a_supprimer = new Personne();
                
                //récupération nom et prénom de l'élève à supprimer entrés par le user
                String nomString = pageSupprEleveForm.getjTextField1().getText();
                String prenomString = pageSupprEleveForm.getjTextField2().getText();

                personne_a_supprimer.setNom(nomString);
                personne_a_supprimer.setPrenom(prenomString);
              
                
                if(isPageAdmin==false)
                {
                    if(pers.delete(personne_a_supprimer))
                    {

                        JOptionPane.showMessageDialog(pageSupprEleveForm, "Succès de la supression.");
                        jframe1.remove(pageSupprEleveForm);
                        jframe1.setContentPane(pageEnseignant);
                        jframe1.setVisible(true);
                    }
                }
                else if(isPageAdmin)
                {
                    if(pers.delete(personne_a_supprimer))
                    {
                     JOptionPane.showMessageDialog(pageSupprEleveForm, "Succès de la supression!");
                        jframe1.remove(pageSupprEleveForm);
                        jframe1.setContentPane(pageAdmin);
                        jframe1.setVisible(true);
                    }
                }
                
            }

        });
        
        pageAdmin.getjButtonSupprEleve().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                isPageAdmin=true;
                jframe1.remove(pageAdmin);
                jframe1.setContentPane(pageSupprEleveForm);
                jframe1.setVisible(true);
                
            
            }

        });
        
///CREER UN ELEVE
       pageEnseignant.getJButtonAddEleve().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                  //Utile pour récupérer ce que le user a selectionné
                pageAjouterEleveForm.getjRadioButtonCP().setActionCommand("CP");
                pageAjouterEleveForm.getjRadioButtonCE1().setActionCommand("CE1");
                pageAjouterEleveForm.getjRadioButtonCE2().setActionCommand("CE2");
                pageAjouterEleveForm.getjRadioButtonCM1().setActionCommand("CM1");
                pageAjouterEleveForm.getjRadioButtonCM2().setActionCommand("CM2");
                
                jframe1.remove(pageEnseignant);
                jframe1.setContentPane(pageAjouterEleveForm);
                jframe1.setVisible(true);
                isPageAdmin = false;
                
                
            }

        });
       
       pageAjouterEleveForm.getjButtonCreerEleve().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                PersonneDAO pdao = new PersonneDAO(maConnexion.getConnection());
                String niveauSelection = pageAjouterEleveForm.getbuttonGroup1().getSelection().getActionCommand();
                String nomSaisi = pageAjouterEleveForm.getjTextFieldNomEleve().getText();
                String prenomSaisi = pageAjouterEleveForm.getjTextFieldPrenomEleve().getText();
                
                System.out.println("Le niveau sélectionné : " + niveauSelection);
                System.out.println("Nom élève entré : " + nomSaisi);
                System.out.println("Prénom entré : " + prenomSaisi);

                Personne eleve_a_add = new Personne();
                pdao.createEleveParEnseignant(eleve_a_add, nomSaisi, prenomSaisi, niveauSelection);
                
                InscriptionDAO inscription_DAO=new InscriptionDAO(maConnexion.getConnection());
                Inscription inscription=new Inscription();
                Classe classe = new Classe();
                
                if(isPageAdmin==false)
                {
                    if(inscription_DAO.create_inscription(inscription, eleve_a_add, classe, niveauSelection))
                    {
                        JOptionPane.showMessageDialog(pageAjouterEleveForm, "L'élève "+nomSaisi+" "+prenomSaisi+" a bien été inscrit en "+niveauSelection+".");
                        jframe1.remove(pageAjouterEleveForm);
                        jframe1.setContentPane(pageEnseignant);
                        jframe1.setVisible(true);
                    }
                }
                else if(isPageAdmin==true)
                {
                    if(inscription_DAO.create_inscription(inscription, eleve_a_add, classe, niveauSelection))
                    {
                        JOptionPane.showMessageDialog(pageAjouterEleveForm, "L'élève "+nomSaisi+" "+prenomSaisi+" a bien été inscrit en "+niveauSelection+".");
                        jframe1.remove(pageAjouterEleveForm);
                        jframe1.setContentPane(pageAdmin);
                        jframe1.setVisible(true);
                    }
                }
              
            }
        });


       //Modifier infos d'un prof
        pageEnseignant.getjButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                personneDAO.update(personne);

            }
        });
        
        //BOUTONS DE DECONNEXION QUI FAIIT RETOURNER A PAGE DE CONNEXION
        pageEleve.getButtonDeconnexion().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                JOptionPane.showMessageDialog(pageEleve, "Vous êtes déconnecté.");
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
                
                personne=null;
                personneDAO=null;
                
            }
        });
        
         pageEnseignant.getButtonDeconnexion().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                isPageAdmin=false;
                JOptionPane.showMessageDialog(pageEnseignant, "Vous êtes déconnecté.");
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
                personne=null;
                personneDAO=null;
                
            }
        });

         pageConnexion.getjButtonConnexionAdmin().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                isPageAdmin=true;
                jframe1.remove(pageConnexion);
                jframe1.setContentPane(pageAdmin);
                jframe1.setVisible(true);
                
                personne=null;
                personneDAO=null;
                
            }
        }
        );

         /**
          * Rechercher un élève depuis la page Enseignant
          */
        pageEnseignant.getjButtonRechercherEleve().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
               jframe1.remove(pageEnseignant);
               jframe1.setContentPane(pageRechercherEleveParEnseignant);
               jframe1.setVisible(true);
            }
        });
        
        pageRechercherEleveParEnseignant.getjButtonRechercher().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                 /////////////////////////////
                JFrame frame = new JFrame();
                //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JpanelAfficherEleveClasse pageAfficherEleveClasse = new JpanelAfficherEleveClasse();
                ////////////////////////////
                PersonneDAO personneDAO_admin = new PersonneDAO(maConnexion.getConnection());
               String nom = pageRechercherEleveParEnseignant.getjTextFieldNom().getText();
               String prenom = pageRechercherEleveParEnseignant.getjTextFieldPrenom().getText();
               
               if(personneDAO_admin.rechercherEleve(nom,prenom,pageAfficherEleveClasse))
               {
                   jframe1.remove(pageRechercherEleveParEnseignant);
                   jframe1.setContentPane(pageEnseignant);
                   jframe1.setVisible(true);
                   frame.remove(pageRechercherProf);
                    frame.setContentPane(pageAfficherEleveClasse);
                    frame.setSize(500, 400);
                    frame.setVisible(true);
               }
               
            }
        });
             
        pageEnseignant.getjButtonAfficherClasseDontJeSuisProf().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                /////////////////////////////
                JFrame frame = new JFrame();
                //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JpanelAfficherEleveClasse pageAfficherEleveClasse = new JpanelAfficherEleveClasse();
                ////////////////////////////
                PersonneDAO personneDAO_cdjsp = new PersonneDAO(maConnexion.getConnection());
                if(personneDAO_cdjsp.rechercherClassesDontJeSuisProf(personne,pageAfficherEleveClasse))
                {
                    frame.remove(pageRechercherProf);
                    frame.setContentPane(pageAfficherEleveClasse);
                    frame.setSize(500, 400);
                    frame.setVisible(true);
                }
                
            }
        });
           
       
        pageEnseignant.getjButtonAjouterNoteEleve().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println("bouton ajouter note");

                EvaluationDAO evaluationDAO = new EvaluationDAO(maConnexion.getConnection());
                Evaluation evaluation= new Evaluation();
                System.out.println("Une note va etre ajoutée par ");
                System.out.println("Nom user " +personne.getNom());
                System.out.println("PreNom user " +personne.getPrenom());
                evaluationDAO.create_eval(evaluation, personne);


            }
        });
        
        
        /**
         * RECHERCHER UN ELEVE A PARTIR DE LA PAGE ADMIN
         */
        
        pageAdmin.getjButtonRechercherEleve().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                jframe1.remove(pageAdmin);
                jframe1.setContentPane(pageRechercherEleve);
                jframe1.setVisible(true);
                
            }
        });
        
        pageRechercherEleve.getjButtonRechercherEleve().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                /////////////////////////////
                JFrame frame = new JFrame();
               
                JpanelAfficherEleveClasse pageAfficherEleveClasse = new JpanelAfficherEleveClasse();
                ////////////////////////////
               PersonneDAO personneDAO_admin = new PersonneDAO(maConnexion.getConnection());
               String nom = pageRechercherEleve.getjTextFieldNomRecherche().getText();
               String prenom = pageRechercherEleve.getjTextFieldPrenomRecherche().getText();
               if(personneDAO_admin.rechercherEleve(nom,prenom,pageAfficherEleveClasse))
               {
                    
                   frame.remove(pageRechercherEleve);
                    frame.setContentPane(pageAfficherEleveClasse);
                    frame.setSize(500, 400);
                    frame.setVisible(true);
                   
               }
            }
        });
        
        pageRechercherEleve.getjButtonPrecedent().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
               
               jframe1.remove(pageRechercherEleve);
               jframe1.setContentPane(pageAdmin);
               jframe1.setVisible(true);
            }
        });
        
        /**
         * RECHERCHER UN ENSEIGNANT A PARTIR DE LA PAGE ADMIN
         */
        
        pageAdmin.getjButtonRechercherProf().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
               jframe1.remove(pageAdmin);
               jframe1.setContentPane(pageRechercherProf);
               jframe1.setVisible(true);
            }
        });
        
        pageRechercherProf.getjButtonRechercherProf().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                /////////////////////////////
                JFrame frame = new JFrame();
                //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JpanelAfficherEleveClasse pageAfficherEleveClasse = new JpanelAfficherEleveClasse();
                ////////////////////////////
               String nom = pageRechercherProf.getjTextFieldNom().getText();
               String prenom = pageRechercherProf.getjTextFieldPrenom().getText();
               PersonneDAO personneDAO_admin = new PersonneDAO(maConnexion.getConnection());
               if(personneDAO_admin.rechercherProf(nom, prenom,pageAfficherEleveClasse))
               {
                   frame.remove(pageRechercherProf);
                    frame.setContentPane(pageAfficherEleveClasse);
                    frame.setSize(500, 400);
                    frame.setVisible(true);
               }
               
            }
        });
        
        pageRechercherProf.getJButtonPrecedent().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
               jframe1.remove(pageRechercherProf);
               jframe1.setContentPane(pageAdmin);
               jframe1.setVisible(true);
               
            }
        });
        
        /**
         * Bouton "Rechercher une classe" à partir de la page Admin
         */
        pageAdmin.getjButtonRechercherClasse().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                jframe1.remove(pageAdmin);
                jframe1.setContentPane(pageRechercherClasse);
                jframe1.setVisible(true);
                
            
            }
        });
        
        pageRechercherClasse.getjButtonRechercherClasse().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                JFrame frame = new JFrame();
                //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JpanelAfficherEleveClasse pageAfficherEleveClasse = new JpanelAfficherEleveClasse();

                //Utile pour récupérer ce que le user a selectionné
                pageRechercherClasse.getjRadioButtonCP_A().setActionCommand("CP_A");
                pageRechercherClasse.getjRadioButtonCP_B().setActionCommand("CP_B");
                pageRechercherClasse.getjRadioButtonCE1_A().setActionCommand("CE1_A");
                pageRechercherClasse.getjRadioButtonCE1_B().setActionCommand("CE1_B");
                pageRechercherClasse.getjRadioButtonCE2_A().setActionCommand("CE2_A");
                pageRechercherClasse.getjRadioButtonCE2_B().setActionCommand("CE2_B");
                pageRechercherClasse.getjRadioButtonCM1_A().setActionCommand("CM1_A");
                pageRechercherClasse.getjRadioButtonCM1_B().setActionCommand("CM1_B");
                pageRechercherClasse.getjRadioButtonCM2_A().setActionCommand("CM2_A");
                pageRechercherClasse.getjRadioButtonCM2_B().setActionCommand("CM2_B");
                
                pageRechercherClasse.getjRadioButton1().setActionCommand("2011");
                pageRechercherClasse.getjRadioButton2().setActionCommand("2012");
                pageRechercherClasse.getjRadioButton3().setActionCommand("2013");
                pageRechercherClasse.getjRadioButton4().setActionCommand("2014");
                
                String classeSelection = pageRechercherClasse.getbuttonGroup1().getSelection().getActionCommand();
                int anneeSelection = Integer.parseInt(pageRechercherClasse.getbuttonGroup2().getSelection().getActionCommand());
                
               
                ClasseDAO classeDAO_recherche = new ClasseDAO(maConnexion.getConnection());
                if(classeDAO_recherche.rechercherClasse(classeSelection, anneeSelection, pageAfficherEleveClasse))
                {
                    
                    frame.remove(pageRechercherClasse);
                    frame.setContentPane(pageAfficherEleveClasse);
                    frame.setSize(500, 700);
                    frame.setVisible(true);

                }
            }
        });
        
        pageRechercherClasse.getjButtonPrecedent().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                jframe1.remove(pageRechercherClasse);
                jframe1.setContentPane(pageAdmin);
                jframe1.setVisible(true);
            }
        });
        
        //Fin des boutons liés à la recherche d'une classe
        
        /**
         * Ajout/Suppr un élève à partir de la page Admin
         */
        pageAdmin.getjButtonAjoutEleve().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                
                  //Utile pour récupérer ce que le user a selectionné
                pageAjouterEleveForm.getjRadioButtonCP().setActionCommand("CP");
                pageAjouterEleveForm.getjRadioButtonCE1().setActionCommand("CE1");
                pageAjouterEleveForm.getjRadioButtonCE2().setActionCommand("CE2");
                pageAjouterEleveForm.getjRadioButtonCM1().setActionCommand("CM1");
                pageAjouterEleveForm.getjRadioButtonCM2().setActionCommand("CM2");
                
                jframe1.remove(pageAdmin);
                jframe1.setContentPane(pageAjouterEleveForm);
                jframe1.setVisible(true);
                PersonneDAO personneDAO = new PersonneDAO(maConnexion.getConnection());
            }
        });
        
        //Fin des boutons liés à l'ajout/suppression d'un élève
        
        pageAdmin.getjButtonModifNomProf().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println("1");
                PersonneDAO personneDAO_modifNomProf = new PersonneDAO(maConnexion.getConnection());
                personneDAO_modifNomProf.modifierProfDepuisAdmin("nom");
            }
        });
        
        pageAdmin.getjButtonModifPrenomProf().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println("2");
                PersonneDAO personneDAO_modifPrenomProf = new PersonneDAO(maConnexion.getConnection());
                personneDAO_modifPrenomProf.modifierProfDepuisAdmin("prenom");
                
                //Ca a transformé la discipline Physique en Philo --> OK
                //Mais il reste à changer discipline_id de enseignement puis ce sera parfait
            }
        });
        
        pageAdmin.getjButtonModifEnseignement().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println("3");
                PersonneDAO personneDAO_modifEnseignementProf = new PersonneDAO(maConnexion.getConnection());
                personneDAO_modifEnseignementProf.modifierDisciplineProfDepuisAdmin();
            }
        });
        
        //Pour antoiinou
        pageAdmin.getjButtonModifNomEleve().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
               
            }
        });
                
                
        pageAdmin.getjButtonModifPrenomEleve().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
               
            }
        });
                
        pageAdmin.getjButtonChangerEleveClasse().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
               
            }
        });
        
        pageAdmin.getjButtonSeDeconnecter().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
               isPageAdmin=false;
               jframe1.remove(pageAdmin);
               jframe1.setContentPane(pageConnexion);
               jframe1.setVisible(true);
               personneDAO=null;
            }
        });
        
        pageAdmin.getjButtonDeconnexion().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                isPageAdmin=false;
               jframe1.remove(pageAdmin);
               jframe1.setContentPane(pageConnexion);
               jframe1.setVisible(true);
               personneDAO=null;
            }
        });
       

    }
}
