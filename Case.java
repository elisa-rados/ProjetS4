
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
    private float cote;
    private boolean accessible;   //vrai pour les cases accessibles et faux sinon
    private boolean pion= false;
    private boolean monstre= false;  
    private int posX;   //position par rapport à l'horizontal
    private int posY;   //position par rapport à la verticale
    private boolean blocDePierre = false;
    
    public Case(int x, int y, boolean acc){
        this.posX=x;
        this.posY=y;
        this.accessible=acc;
    }
    
    public boolean getBlocDePierre(){
        return this.blocDePierre;
    }
    
    public void setBlocDePierre(boolean b){
        this.blocDePierre=b;
    }
    
    public boolean getPion(){
        return this.pion;
    }
    
    public void setPion(boolean p){
        this.pion=p;
    }
    
    public boolean getMonstre(){
        return this.monstre;
    }
    
    public void setMonstre(boolean m){
        this.monstre=m;
    }
    
     public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

}
