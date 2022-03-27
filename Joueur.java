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
public class Joueur {
    //joueur
    String nom;   //pseudo du joueur
    ArrayList<Pion> tab=new ArrayList<>();   //les pions qu'il a en sa possession
    int nbPionMort=0;
    int nbPionSortis=0;
    
    public Joueur(String e, ArrayList possession){
        this.nom=e;
        this.tab=possession;
    }
     public int getNbPionMort() {
        return nbPionMort;
    }

    public int getNbPionSortis() {
        return nbPionSortis;
    }
    
     public int plusMonstrueux(){
        Random rand = new Random();
        int nbJoueurs=2;
        int numJMonstrueux= rand.nextInt(2+1);
        System.out.println("le joueur: "+numJMonstrueux+"commence");
        return numJMonstrueux;
    }
  
    
    public void enleverPionListe(Pion p){
        for(int i=0; i<tab.size(); i++){
            if (tab.get(i).equals(p)){
                tab.remove(i);
            }
        }
    }
        
    
    // Pour remettre un pion après la première manche
     public void remettre(Pion p){
         tab.add(p);   
        }
    
    public boolean sortir(Pion p){
         if(p.getY()==11 && p.getY()==16 && p.demanderDirection()==3){  
              nbPionSortis++;
         }
              return true;
         
    }  
          public boolean mort(Pion p,Monstre a, Manche m){
        if(p.getX()==a.getX() && p.getY()==a.getY()){   // si le Pion et le monstre sont sur la même case
        if(m.getNumManche()==1){
            p.setSurPlateau(false);    // le pion est enlevé du plateau
            nbPionMort++;            
        
        return false;       //si la méthode retourne false, remettre (méthode) pourra être appelée par la suite
        }
        else{
            p.setSurPlateau(false);    
            nbPionMort++;
            enleverPionListe(p);
            return true;   //le pion est définitivement mort
        } 
        } 
        else
            return false;
    } 
    
}
