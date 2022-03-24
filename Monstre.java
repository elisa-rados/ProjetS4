/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetS4;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author rados
 */
public class Monstre { //regarde et cherche le pion le plus proche à gauche droite et devant, puis se tourne vers le pion le plus proche et se déplace d'un nombre n de case(s) selon la carte piochée (cartes différentes des joueurs), si rien dans champ de vision continue sur la même trajectoire
    //haut, bas, gauche, droite, 1234
    private int x=0, y=0;
    private int sensMonstre = 4;//il commence en haut à gauche du plateau en regardant vers la droite
    private ArrayList<Carte> paquetDeCartes = new ArrayList<>();
    
    public Monstre (ArrayList<Carte> paquet){
        this.paquetDeCartes = new paquet<>();//pas sûre
    }
    
    
    public boolean regarder (){}
    
    //remplir paquet de cartes
    private ArrayList<Carte> remplirPaquet (ArrayList<Carte> paquet, Carte c) {
        paquet.add(c);
    }
    
    //mélanger paquet de cartes
    private ArrayList<Carte> mélangerPaquet (ArrayList<Carte> paquet){
        Collections.shuffle(paquet);
        System.out.println(paquet);
    }
    
    
}
//méthode pour afficher sa position
//méthode déplacement voir dans Pion


