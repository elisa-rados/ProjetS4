/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetS4;

/**
 *
 * @author Utilisateur
 */
abstract class Carte {
    int nbPasMax; //si CartePas: nombre exact, si CarteProie inférieur à ce nb
    
    public Carte(int pas){
        this.nbPasMax=pas;
    }
} 
