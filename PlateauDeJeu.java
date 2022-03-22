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
public class PlateauDeJeu {
    private Case [][] plateau = new Case[11][16];
    
    
    public Case[][] getPlateau(){
        return plateau;
    }
    
    public void setPlateau(int y, int x, Case c){
        this.plateau[y][x]= c;
    }
    
    public PlateauDeJeu(){ //constructeur d'un plateau
        
        //les cases de base avant les spécificités
        for(int i=0; i<11; i++){
            for( int j=0;j<16;j++){
                this.plateau[i][j]=new Case(j, i, true);
            }
        }
        
        for(int y=0; y<11; y++){
            for(int x=0; x<16; x++){
                //cases non accessibles
                if((y==0 && (x==12 || x==13 || x==14 || x==15)) || (y==1 && (x==13
                || x==14 || x==15)) || (y==2 && (x==14 || x==15)) || (y==3 && x==16) || (y==7 && x==1) || 
                (y==8 && (x==1 || x==2)) || (y==9 && (x==1 || x==2 || x==3)) || (y==10 && (x==1 || x==2 || 
                x==3 || x==4))){
                    this.plateau[y][x].setAccessible(false);
                }
            }
        }
        //cases initialement avec des blocs
        this.plateau[2][2].setBlocDePierre(true);
        this.plateau[7][4].setBlocDePierre(true);
        this.plateau[9][5].setBlocDePierre(true);
        this.plateau[6][6].setBlocDePierre(true);
        this.plateau[4][7].setBlocDePierre(true);
        this.plateau[5][8].setBlocDePierre(true);
        this.plateau[9][8].setBlocDePierre(true);
        this.plateau[3][12].setBlocDePierre(true);
        this.plateau[7][12].setBlocDePierre(true);
        this.plateau[5][13].setBlocDePierre(true);
        this.plateau[8][14].setBlocDePierre(true);
        
        //cases flaque d'hémoglobine
        this.plateau[2][8]= new Flaque(8, 2, true);
        this.plateau[2][9]= new Flaque(9, 2, true);
        this.plateau[3][8]= new Flaque(8, 3, true);
        this.plateau[3][9]= new Flaque(9, 3, true);
        this.plateau[8][4]= new Flaque(4, 8, true);
        this.plateau[8][5]= new Flaque(5, 8, true);
        this.plateau[8][6]= new Flaque(6, 8, true);
        this.plateau[8][7]= new Flaque(7, 8, true);
    }
}
