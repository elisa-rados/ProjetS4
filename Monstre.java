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
    private PlateauDeJeu plateau;
    
    public Monstre ( PlateauDeJeu p){
        this.creerPaquet();
        this.plateau=p;
    }
    
    public int nbCasesPion(int sens){
        int x=this.x;
        int y= this.y;
        int nbCases=0;
        boolean bloc=false;
        boolean trouve=false;
        
        if(sens==1){
            while(y>=0 && !trouve && !bloc){
                bloc= this.plateau.getPlateau()[y][x].getBlocDePierre()==true;
                if(!bloc){
                    nbCases++;
                    y--;
                    trouve= this.plateau.getPlateau()[y][x].getPion()==true;
                }
            }
            if(y<0 || bloc){
                return 20; //on retourne un chiffre trop élevé pour être possible si aucun pion n'a été trouvé
            }
        }else if(sens==2){
            while(y<=10 && !trouve && !bloc){
                bloc= this.plateau.getPlateau()[y][x].getBlocDePierre()==true;
                if(!bloc){
                    nbCases++;
                    y++;
                    trouve= this.plateau.getPlateau()[y][x].getPion()==true;
                }
            }
            if(y>10 || bloc){
                return 20; //on retourne un chiffre trop élevé pour être possible si aucun pion n'a été trouvé
            }
        }else if(sens==3){
            while(x>=0 && !trouve && !bloc){
                bloc= this.plateau.getPlateau()[y][x].getBlocDePierre()==true;
                if(!bloc){
                    nbCases++;
                    x--;
                    trouve= this.plateau.getPlateau()[y][x].getPion()==true;
                }
            }
            if(x<0 || bloc){
                return 20; //on retourne un chiffre trop élevé pour être possible si aucun pion n'a été trouvé
            }
        }else if(sens==4){
            while(x<=15 && !trouve && !bloc){
                bloc= this.plateau.getPlateau()[y][x].getBlocDePierre()==true;
                if(!bloc){
                    nbCases++;
                    y++;
                    trouve= this.plateau.getPlateau()[y][x].getPion()==true;
                }
            }
            if(x>15 || bloc){
                return 20; //on retourne un chiffre trop élevé pour être possible si aucun pion n'a été trouvé
            }
        }
        
        return nbCases;// si on n'a pas retourné avant parce qu'on n'avait pas trouvé de pion
    }
    
    public int regarder (){//renvoie le sens du monstre
        int sens;
        int haut;
        int bas;
        int gauche;
        int droite;
        
            if(this.sensMonstre==1){
                gauche= this.nbCasesPion(3);
                droite= this.nbCasesPion(4);
                haut= this.nbCasesPion(1);
                if((gauche<haut)&&(gauche<droite)){ //le pion le plus proche est dans cette direction
                    sens=3;
                    return sens;
                }else if((droite<haut)&&(droite<gauche)){ //le pion le plus proche est dans cette direction
                    sens=4;
                    return sens;
                }else{  //le pion est perturbé ou il n'y a pas de pions autour de lui ou le plus proche est dans cette direction
                    sens= 1;
                    return sens;
                }
            }else if(this.sensMonstre==2){
                gauche= this.nbCasesPion(3);
                droite= this.nbCasesPion(4);
                bas= this.nbCasesPion(2);
                if((gauche<bas)&&(gauche<droite)){ //le pion le plus proche est dans cette direction
                    sens=3;
                    return sens;
                }else if((droite<bas)&&(droite<gauche)){ //le pion le plus proche est dans cette direction
                    sens=4;
                    return sens;
                }else{  //le pion est perturbé ou il n'y a pas de pions autour de lui ou le plus proche est dans cette direction
                    sens= 2;
                    return sens;
                }
            }else if(this.sensMonstre==3){
                gauche= this.nbCasesPion(3);
                bas= this.nbCasesPion(2);
                haut= this.nbCasesPion(1);
                if((gauche<haut)&&(gauche<bas)){ //le pion le plus proche est dans cette direction
                    sens=3;
                    return sens;
                }else if((bas<haut)&&(bas<gauche)){ //le pion le plus proche est dans cette direction
                    sens=2;
                    return sens;
                }else{  //le pion est perturbé ou il n'y a pas de pions autour de lui ou le plus proche est dans cette direction
                    sens= 3;
                    return sens;
                }
            }else{
                bas= this.nbCasesPion(2);
                droite= this.nbCasesPion(4);
                haut= this.nbCasesPion(1);
                if((bas<haut)&&(bas<droite)){ //le pion le plus proche est dans cette direction
                    sens=2;
                    return sens;
                }else if((haut<droite)&&(haut<bas)){ //le pion le plus proche est dans cette direction
                    sens=1;
                    return sens;
                }else{  //le pion est perturbé ou il n'y a pas de pions autour de lui ou le plus proche est dans cette direction
                    sens= 4;
                    return sens;
                }
            }  
    }
    
    private void creerPaquet(){
        CartePas cpas5 = new CartePas(5);
        CartePas cpas7 = new CartePas(7); 
        CartePas cpas8 = new CartePas(8); 
        CartePas cpas72 = new CartePas(7); 
        CartePas cpas82 = new CartePas(8); 
        CartePas cpas10 = new CartePas(10);
        CarteProie cproie1 = new CarteProie(1);
        CarteProie cproie2 = new CarteProie(2);
        
        this.remplirPaquet(cpas5);
        this.remplirPaquet(cpas7);
        this.remplirPaquet(cpas8);
        this.remplirPaquet(cpas72);
        this.remplirPaquet(cpas82);
        this.remplirPaquet(cpas10);
        this.remplirPaquet(cproie1);
        this.remplirPaquet(cproie2);
        
        this.mélangerPaquet();
    }
    
    //remplir paquet de cartes
    private void remplirPaquet (Carte c) {
        this.paquetDeCartes.add(c);
    }
    
    //mélanger paquet de cartes
    private void mélangerPaquet (){
        Collections.shuffle(this.paquetDeCartes);
        System.out.println(this.paquetDeCartes);
    }
    
    //tirer une carte
    private Carte tirerCarte (Tour t){
        Carte cTirée = this.paquetDeCartes.get(0);
        while((t.getNumTour()==1) && (cTirée.getClass().getName()=="finstere_flure.CarteProie")){ //si la premiere carte tiree est une carte proie
            this.mélangerPaquet();
            cTirée = this.paquetDeCartes.get(0);
        }
        return(cTirée);
    }
    
    
}
//méthode pour afficher sa position
//méthode déplacement voir dans Pion

