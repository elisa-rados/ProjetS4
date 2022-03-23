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
    
    public Tour(Manche m){
       if(m.getNumManche()==1 && this.numTour==1){
           int nbpions= 4; //2 joueurs jouent 2 pions (pour les premiers défis)
       }
       else{
           
           
       }
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
}
