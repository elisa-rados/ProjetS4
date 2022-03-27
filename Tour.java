/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere_flure;

import java.util.Scanner;

/**
 *
 * @author abigail.berthe
 */
public class Tour {
    private int numTour= 1;
    private Scanner sc= new Scanner(System.in);
    
    public Tour(Manche m, int n){
       if(m.getNumManche()==1 && this.numTour==1){
           int nbpions= 4; //2 joueurs jouent 2 pions (pour les premiers défis)
       }
       else{
          this.numTour=n; 
           
       }
    }
    public int getNumTour() {
        return numTour;
    }
    
    public void choixPions(){ //permet de choisir les deux pions
        int pion1, pion2;
        System.out.println("Choisissez les deux pions que vous souhaitez jouer pour le premier tour: \n"
                + "1. pion valeur 1\n"
                + "2. pion valeur 3\n"
                + "3. pion valeur 4\n"
                + "4. pion valeur 5\n"
                + "\n"
                + "pion 1: ");
        pion1= sc.nextInt();
        System.out.println("pion 2:");
        pion2= sc.nextInt();
    }
    
        //conditions d'arrêt d'un tour et Arrêt d'une manche si on est au 7ème tour
    public void arretTour(Joueur j, Manche m){
        if(j.getNbPionSortis()>2 || j.getNbPionMort()==4){  //si le nombre de pions sortis est égal à 3 ou tous les pions sont morts
            System.out.println("Fin de tour!\n");
            numTour++;
            if(numTour<7){
            System.out.println("Tour "+numTour+1);  //on passe au tour suivant
            }
            else {
                m.arretManche();  //on passe à la manche suivante
            }   
        }
}
    
}
