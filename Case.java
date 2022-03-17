
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
public class Case {
    float cote;
    boolean accessible;   //vrai pour les cases accessibles et faux sinon
    boolean pion=false;
    public Case(float a, boolean acc){
        this.cote=a;
        this.accessible=acc;
    }
    
}
