/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetS4;

import java.util.ArrayList;

/**
 *
 * @author Utilisateur
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Joueur> joueur=new ArrayList<>();
        int nbJoueurs=2; //pour l'instant
        int manche = 1;
        
        System.out.println("Pseudo du joueur 1: ");
        Scanner sc = new Scanner (System.in);
        String ps1= sc.next();
        System.out.println("Quelle couleur voulez-vous jouer? ");
        String col1=sc.next();
        ArrayList<Pion> pionsj1=new ArrayList<>(); //liste de pions du joueur 1
        pionsj1.add(new Pion(6, 1, col1));
        pionsj1.add(new Pion(4, 3, col1));
        pionsj1.add(new Pion(3, 4, col1));
        pionsj1.add(new Pion(2, 5, col1)); 
        joueur.add(new Joueur(ps1, pionsj1));//ajout de j1 dans la liste des joueurs
        
        System.out.println("Pseudo du joueur 2: ");
        Scanner sc2 = new Scanner (System.in);
        String ps2= sc2.next();
        System.out.println("Quelle couleur voulez-vous jouer? ");
        String col2=sc.next();
        ArrayList<Pion> pionsj2=new ArrayList<>(); //liste de pions du joueur 2
        pionsj2.add(new Pion(6, 1, col2));
        pionsj2.add(new Pion(4, 3, col2));
        pionsj2.add(new Pion(3, 4, col2));
        pionsj2.add(new Pion(2, 5, col2)); 
        joueur.add(new Joueur(ps2, pionsj2));//ajout de j2 dans la liste des joueurs
        
        
        /*Pion p= new Pion (6,1); //pion créé pour test
        PlateauDeJeu plateau= new PlateauDeJeu();
        
        p.mettrePionPlateau(plateau);
        p.Deplacement();
        System.out.println("x(colonne) est: " + p.getX() + " y(ligne) est: "+ p.getY());*/
    }
    
    
    
    
}
