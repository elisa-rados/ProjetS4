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
public class Pion {
    
    int x, y;  //pour les numéros des 2 faces
    boolean surPlateau;  //vrai sur le pion est sur le plateau, faux sinon
    public Pion( int faceVisible, int face2, boolean verif){
     this.x=faceVisible;
    this.y=face2;
    this.surPlateau=verif;
}
    public int getX(){  //avec x la face visible
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isSurPlateau() {
        return surPlateau;
    }

    public void setSurPlateau(boolean surPlateau) {
        this.surPlateau = surPlateau;
    }
    
    
    //permet de déplacer le pion d'un nombre de cases maximale= au numéro de la face visible
    public boolean deplacerPion(){
        boolean horizontal=true;
        boolean occuper=true;
        int dep=0;   //valeur du déplacement
        if(dep<=getX()){
            //demander s'il veut déplacer le pion horizontalement ou verticalement et de combien
            if(horizontal){
                //à compléter
        
            }
            else{
               //à compléter 
            }
    }
      return true; 
    }
    public boolean retourner(){
      int a=getX();
      a=getY();
      return true;
    }
    
}
