/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere_flure;

/**
 *
 * @author abigail.berthe
 */
public class Manche {
    private int numManche = 1;
    
     public Manche(int n){
        this.numManche=n;
    }
    
    public int getNumManche(){
        return this.numManche;
    }
    public void arretManche(){
            System.out.println("Fin de manche!");
            numManche++;     
    }
}
