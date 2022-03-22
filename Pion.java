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
    
    private int noir, blanc;  //pour les numéros des 2 faces
    private boolean surPlateau = false;  //vrai sur le pion est sur le plateau, faux sinon
    private int x,y; //coordonnées du pion x: colonnes et y: lignes
    private int direction;
    private String faceVisible= "blanc";
    private PlateauDeJeu p;
    
    
    public Pion( int face, int pile){
        this.blanc=face;
        this.noir=pile;
        
    }
    
    /*public void setX(int x){
        this.x=x;
    }
    
    public void setY(int y){
        this.y=y;
    }*/
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public int getNoir(){  //avec x la face visible
        return noir;
    }

    public int getBlanc() {
        return blanc;
    }

    public boolean getSurPlateau() {
        return surPlateau;
    }

    public void setSurPlateau(boolean surPlateau) {
        this.surPlateau = surPlateau;
    }
    
    private void changerFaceVisible(){
        if (this.faceVisible=="blanc"){
            this.faceVisible="noir";
        }
        else{
            this.faceVisible="blanc";
        }
    }
    
    public void mettrePionPlateau(PlateauDeJeu plateau){ //mettre un pion sur le plateau
        this.x=0;
        this.y=0;
        this.surPlateau=true;
        this.p=plateau;
        plateau.getPlateau()[0][0].setPion(true);
    }
    
    //deplacement d'une case d'un pion
    private void deplacerPion(int direction){
        // "1 : haut"
        //"2 : bas"
        //"3 : gauche"
        //"4 : droite"
        if(direction==1){
            this.y=this.y-1;
        }
        if(direction==2){
            this.y=this.y+1;
        }
        if(direction==3){
            this.x=this.x-1;
        }
        if(direction==4){
            this.x=this.x+1;
        }
    }
    
    private int demanderDirection(){
        Scanner sc = new Scanner (System.in);
        int choix;

        System.out.println("Où voulez-vous aller?\n"
                    + "0: terminer mon tour\n"
                    + "1 : haut\n"
                    + "2 : bas\n"
                    + "3 : gauche\n"
                    + "4 : droite");
        
        choix=sc.nextInt();
        return choix;
    }
    
    
    private boolean verifBordPlateau(int y, int x){ //les coordonnées passées en paramètres sont les coordonnées que l'on souhaite verifier
        boolean pasAuBord = true; //vrai initialement
        
        if(y<0 || y>10 || x<0 || x>15 || (y==0 && (x==12 || x==13 || x==14 || x==15)) || (y==1 && (x==13
                || x==14 || x==15)) || (y==2 && (x==14 || x==15)) || (y==3 && x==16) || (y==7 && x==1) || 
                (y==8 && (x==1 || x==2)) || (y==9 && (x==1 || x==2 || x==3)) || (y==10 && (x==1 || x==2 || 
                x==3 || x==4))){
            pasAuBord =  false;
        }
        
        return pasAuBord;
    }
    
    private boolean verifMonstre(int y, int x){
        boolean pasMonstre = true;
        
        if(p.getPlateau()[y][x].getMonstre()==true){
            pasMonstre=false;
        }
        
        return pasMonstre;
    }
    
    private boolean verifPion(int y, int x){
        boolean pasPion = true;
        
        if(p.getPlateau()[y][x].getPion()==true){
            pasPion=false;
        }
        
        return pasPion;
    }
    
    private boolean verifFlaqueSang(int y, int x){
        boolean pasFlaque = true;
        if(y<11 && y>-1 && x<16 && x>-1 ){
        if(p.getPlateau()[y][x].getClass().getName()=="finstere_flure.Flaque"){
            pasFlaque=false;
        }
        }
        else{
            pasFlaque=true;
        }
        return pasFlaque;
    }
    
    private boolean verifBlocPierre(int y, int x){
        boolean pasBloc = true;
        
        if(p.getPlateau()[y][x].getBlocDePierre()){
            pasBloc=false;
        }
        
        return pasBloc;
    }
    
    private int nombreDeDeplacementsPossibles(){
        if(this.faceVisible=="blanc"){
            return blanc;
        }
        else{
            return noir;
        }
    }
    
    private void retournerPion(){
        if(this.faceVisible=="blanc"){
            this.faceVisible="noir";
        }
        else{
            this.faceVisible="blanc";
        }
    }
    
    private int empecherArret(){
        direction= this.demanderDirection();
        while(direction==0){
            System.out.println("Vous ne pouvez pas vous arrêter ici");
            direction= this.demanderDirection();
        }
        return direction;
    }
    
    private boolean toutesLesVerificationsBloc(int y, int x){

        if(!this.verifBordPlateau(y, x)){
            return false;
        }
        else if(!this.verifMonstre(y, x)){
            return false;
        }
        else if(!this.verifBlocPierre(y, x)){
            return false;
        }
        else if(!this.verifPion(y, x)){
            return false;
        }
        else return true;
    }
    
    private boolean toutesLesVerificationsPion(int y, int x){ //servira a verifier ce qu'il y a apres une flaque et pourrait empecher un pion de s'engager sur la flaque
        if(!this.verifBordPlateau(y, x)){
            return false;
        }
        else if(!this.verifMonstre(y, x)){
            return false;
        }
        else return true;
    }
    
    public void Deplacement(){
        int i= this.nombreDeDeplacementsPossibles();
        int direction=this.demanderDirection(); //première fois qu'on demande la direction
        System.out.println(direction);
        p.getPlateau()[this.y][this.x].setPion(false); //le pion va changer d'emplacement
        while(i!=0){
            if(direction!=0){
                System.out.println(direction);
                if(direction==1){ //l'utilisateur veut aller en haut
                    if(this.verifBordPlateau(this.y-1, this.x)){
                        if(this.verifMonstre(this.y-1, this.x)){
                            if(this.verifFlaqueSang(this.y-1, this.x)){
                                if(this.verifBlocPierre(this.y-1, this.x)){
                                    if(this.verifPion(this.y-1, this.x)){ 
                                        this.deplacerPion(direction); //s'il n'y a aucun pb de deplacement
                                        i--;
                                        direction=this.demanderDirection();
                                    }
                                    else{ //s'il y a un pion, on part du principe qu'il y a une issue possible autour du pion pour le moment
                                        if(i>1){ //le pion a encore un deplacement possible apres, il peut passer sur le pion
                                            this.deplacerPion(direction);
                                            i--;
                                            direction=this.empecherArret();
                                        }
                                        else{
                                            System.out.println("Vous ne pouvez pas vous arrêter sur un pion");
                                            direction=this.demanderDirection();
                                        }
                                    }
                                }
                                //s'il y a un bloc que faire
                                else{
                                    if(this.toutesLesVerificationsBloc(y-2, x) && this.verifFlaqueSang(y-2, x)){ //s'il n'y a rien après le bloc
                                        i--;
                                        p.getPlateau()[y-2][x].setBlocDePierre(true);
                                        p.getPlateau()[y-1][x].setBlocDePierre(false); //echange cases bloc de pierre et case non bloc de pierre
                                        this.deplacerPion(direction);
                                        direction=this.demanderDirection();
                                    }
                                    else if (!this.verifFlaqueSang(y-2, x)){
                                        //s'il y a une flaque de sang après le bloc
                                        boolean finFlaque=false;
                                        int k=y-2,j=x;
                                        int nbCases=0;
                                        while(!finFlaque){
                                            finFlaque=p.getPlateau()[k][j].getClass().getName()!="finstere_flure.Flaque";
                                            k--;
                                            if(!finFlaque){ //compteur du nombre de cases flaques dans cette direction
                                                nbCases++;
                                            }
                                        }
                                        if(this.toutesLesVerificationsBloc(y-2-nbCases, x)){//si la case après la flaque est vide
                                            p.getPlateau()[y-1][x].setBlocDePierre(false); //le bloc de pierre n'est plus sur cette case
                                            p.getPlateau()[y-2-nbCases][x].setBlocDePierre(true); //le bloc de pierre est poussé sur la case apres la flaque
                                            this.deplacerPion(direction); //le pion est deplace apres avoir decale le bloc de pierre
                                            i--;
                                            direction=this.demanderDirection();
                                        }
                                        else  { //si il y a quelque chose sur la case apres la flaque
                                            p.getPlateau()[y-1][x].setBlocDePierre(false); //le bloc de pierre n'est plus sur cette case
                                            p.getPlateau()[y-2-(nbCases-1)][x].setBlocDePierre(true); //le bloc de pierre est sur cette case, la derniere case flaque
                                            this.deplacerPion(direction); //le pion est deplace apres avoir decale le bloc de pierre
                                            i--;
                                            direction=this.demanderDirection();
                                        }
                                    }
                                    else{ //s'il y a un pion, un autre bloc, le monstre ou le bord du plateau après le bloc
                                        System.out.println("Vous ne pouvez pas pousser ce bloc de pierre, veuillez changer de direction");
                                        direction=this.demanderDirection();
                                    }
                                }
                            }
                            //s'il y a une flaque de sang 
                            else{
                                boolean finFlaque=false;
                                int k=y-1,j=x;
                                int nbCases=0;
                                while(!finFlaque){
                                    finFlaque=p.getPlateau()[k][j].getClass().getName()!="finstere_flure.Flaque";
                                    k--;
                                    if(!finFlaque){ //compteur du nombre de cases flaques dans cette direction
                                        nbCases++;
                                    }
                                }
                                if(this.toutesLesVerificationsPion(y-nbCases-1, x)){ //si il n'y a ni monstre ni bord de plateau sur la case après la flaque
                                    if(this.verifBlocPierre(y-nbCases, x)){
                                        //s'il n'y a pas de bloc de pierre coince d'un tour precedent sur la flaque d'hemoglobine
                                        if(this.verifPion(y-1-nbCases, x)){
                                            //s'il n'y a pas de pion sur la case juste apres la flaque
                                            if(this.verifBlocPierre(y-1-nbCases, x)){
                                                //s'il n'y a pas de bloc de pierre apres la flaque = s'il n'y a aucun pb apres la flaque
                                                for(int a=0; a<nbCases+1; a++){ //jusqu'a apres la flaque
                                                    this.deplacerPion(direction);
                                                }
                                                i--;
                                                direction=this.demanderDirection(); 
                                            }
                                            else{
                                                //s'il y a un bloc de pierre apres la flaque
                                                //il faut vérifier que le bloc de pierre peut etre decale
                                                if(this.toutesLesVerificationsBloc(y-2-nbCases, x)){ //on verifie que la case apres le bloc est vide
                                                    p.getPlateau()[y-nbCases-1][x].setBlocDePierre(false);
                                                    p.getPlateau()[y-nbCases-2][x].setBlocDePierre(true); //echange de la case bloc de pierre
                                                    for(int a=0; a<nbCases+1; a++){ //jusqu'a apres la flaque, pour mettre le pion
                                                        this.deplacerPion(direction);
                                                    }
                                                    i--;
                                                    direction=this.demanderDirection(); 
                                                }
                                                else{
                                                    //la case apres le bloc n'est pas vide, le pion ne pourra pas pousser le bloc, il devra donc changer de direction, necessite au moins un deplacement
                                                     if(i>1){
                                                        for(int a=0; a<nbCases-1; a++){ //jusqu'a l'avant derniere case de la flaque, pour mettre le pion
                                                             this.deplacerPion(direction);
                                                        } 
                                                        i--;
                                                        direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                                    }
                                                     else{ //il n'a pas assez de deplacement disponible
                                                         System.out.println("Vous ne pourrez pas vous arreter sur la flaque, changez de direction\n");
                                                     }
                                                }
                                            }
                                        }
                                        else{
                                            //s'il y a un pion sur la case juste apres la flaque, il faut verifier que plus d'un deplacement est encore disponible
                                            if(i>1){
                                               for(int a=0; a<nbCases+1; a++){ 
                                                        this.deplacerPion(direction);
                                                        i--;
                                                        direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                                } 
                                            }
                                            else{
                                                System.out.println("Vous ne pourrez pas vous arreter sur le pion, changez de direction\n");
                                                direction=this.demanderDirection();
                                            }
                                        }
                                    }
                                    else{
                                        //s'il y a un bloc coince d'un tour precedent sur la flaque d'hemoglobine a continuer
                                        //si apres la flaque il n'y a ni monstre ni bord ni pion ni bloc (le bloc sur la flaque peut etre pousse)
                                        if(this.toutesLesVerificationsBloc(y-nbCases-1, x)){
                                            //si sur la case encore après il n'y a pas non plus
                                            if(this.toutesLesVerificationsBloc(y-nbCases-2, x)){
                                                p.getPlateau()[y-nbCases][x].setBlocDePierre(false);
                                                p.getPlateau()[y-nbCases-2][x].setBlocDePierre(true); //echange de la case bloc de pierre
                                                 for(int a=0; a<nbCases+1; a++){ //jusqu'a apres la flaque, pour mettre le pion
                                                        this.deplacerPion(direction);
                                                 }
                                                 i--;
                                                 direction=this.demanderDirection(); 
                                            }
                                            else{
                                                //le bloc est pousse jusqu'a la case vide mais ne peut aller plus loin, le pion va etre coince sur la flaque
                                                if(i>1){
                                                    for(int a=0; a<nbCases-1; a++){ //jusqu'a l'avant derniere case de la flaque, pour mettre le pion
                                                             this.deplacerPion(direction);
                                                        } 
                                                    i--;
                                                    direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                                }
                                                else{
                                                    System.out.println("Vous ne pourrez pas vous arreter sur la flaque, changez de direction");
                                                    direction=this.demanderDirection();
                                                }
                                            }
                                        }
                                        else{
                                            //la premiere case n'est pas libre, le bloc ne peut pas etre pousse 
                                            if(i>1){
                                                for(int a=0; a<nbCases-2; a++){ //jusqu'a la case avant le bloc, pour mettre le pion
                                                    this.deplacerPion(direction);
                                                } 
                                                i--;
                                                direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                            }
                                            else{
                                                System.out.println("Vous ne pourrez pas vous arreter sur la flaque, changez de direction");
                                                direction=this.demanderDirection();
                                            }
                                        }
                                        
                                    }
                                }
                                else{
                                    //il y a un monstre ou le bord du plateau apres la flaque, le pion va etre bloque sur la flaque
                                    //s'il y a un bloc bloqué sur la flaque
                                    if(p.getPlateau()[y-nbCases][x].getBlocDePierre()==true){
                                        if(i>1){
                                                for(int a=0; a<nbCases-2; a++){ //jusqu'a la case avant le bloc, pour mettre le pion
                                                    this.deplacerPion(direction);
                                                } 
                                                i--;
                                                direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                            }
                                            else{
                                                System.out.println("Vous ne pourrez pas vous arreter sur la flaque, changez de direction");
                                                direction=this.demanderDirection();
                                            }
                                    }
                                    else{
                                        if(i>1){
                                                    for(int a=0; a<nbCases-1; a++){ //jusqu'a l'avant derniere case de la flaque, pour mettre le pion
                                                             this.deplacerPion(direction);
                                                        } 
                                                    i--;
                                                    direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                                }
                                                else{
                                                    System.out.println("Vous ne pourrez pas vous arreter sur la flaque, changez de direction");
                                                    direction=this.demanderDirection();
                                                }
                                    }
                                    
                                }
                                        
                            }
                            
                        }
                        //s'il y a le monstre sur la case
                        else{
                            System.out.println("Vous ne pouvez pas passer sur le monstre");
                            direction=this.demanderDirection();
                        }
                    }
                    //si on est au bord du tableau
                    else{
                        System.out.println("Vous avez atteint le bord du plateau, changez de direction");
                        direction=this.demanderDirection();
                    }
                }
            else if(direction==2){ //l'utilisateur veut aller en bas
                    if(this.verifBordPlateau(this.y+1, this.x)){
                        if(this.verifMonstre(this.y+1, this.x)){
                            if(this.verifFlaqueSang(this.y+1, this.x)){
                                if(this.verifBlocPierre(this.y+1, this.x)){
                                    if(this.verifPion(this.y+1, this.x)){ 
                                        this.deplacerPion(direction); //s'il n'y a aucun pb de deplacement
                                        i--;
                                        direction=this.demanderDirection();
                                    }
                                    else{ //s'il y a un pion, on part du principe qu'il y a une issue possible autour du pion pour le moment
                                        if(i>1){ //le pion a encore un deplacement possible apres, il peut passer sur le pion
                                            this.deplacerPion(direction);
                                            i--;
                                            direction=this.empecherArret();
                                        }
                                        else{
                                            System.out.println("Vous ne pouvez pas vous arrêter sur un pion");
                                            direction=this.demanderDirection();
                                        }
                                    }
                                }
                                //s'il y a un bloc que faire
                                else{
                                    if(this.toutesLesVerificationsBloc(y+2, x) && this.verifFlaqueSang(y+2, x)){ //s'il n'y a rien après le bloc
                                        i--;
                                        p.getPlateau()[y+2][x].setBlocDePierre(true);
                                        p.getPlateau()[y+1][x].setBlocDePierre(false); //echange cases bloc de pierre et case non bloc de pierre
                                        this.deplacerPion(direction);
                                        direction=this.demanderDirection();
                                    }
                                    else if (!this.verifFlaqueSang(y+2, x)){
                                        //s'il y a une flaque de sang après le bloc
                                        boolean finFlaque=false;
                                        int k=y+2,j=x;
                                        int nbCases=0;
                                        while(!finFlaque){
                                            finFlaque=p.getPlateau()[k][j].getClass().getName()!="finstere_flure.Flaque";
                                            k++;
                                            if(!finFlaque){ //compteur du nombre de cases flaques dans cette direction
                                                nbCases++;
                                            }
                                        }
                                        if(this.toutesLesVerificationsBloc(y+2+nbCases, x)){//si la case après la flaque est vide
                                            p.getPlateau()[y+1][x].setBlocDePierre(false); //le bloc de pierre n'est plus sur cette case
                                            p.getPlateau()[y+2+nbCases][x].setBlocDePierre(true); //le bloc de pierre est poussé sur la case apres la flaque
                                            this.deplacerPion(direction); //le pion est deplace apres avoir decale le bloc de pierre
                                            i--;
                                            direction=this.demanderDirection();
                                        }
                                        else  { //si il y a quelque chose sur la case apres la flaque
                                            p.getPlateau()[y+1][x].setBlocDePierre(false); //le bloc de pierre n'est plus sur cette case
                                            p.getPlateau()[y+2+(nbCases-1)][x].setBlocDePierre(true); //le bloc de pierre est sur cette case, la derniere case flaque
                                            this.deplacerPion(direction); //le pion est deplace apres avoir decale le bloc de pierre
                                            i--;
                                            direction=this.demanderDirection();
                                        }
                                    }
                                    else{ //s'il y a un pion, un autre bloc, le monstre ou le bord du plateau après le bloc
                                        System.out.println("Vous ne pouvez pas pousser ce bloc de pierre, veuillez changer de direction");
                                        direction=this.demanderDirection();
                                    }
                                }
                            }
                            //s'il y a une flaque de sang 
                            else{
                                boolean finFlaque=false;
                                int k=y+1,j=x;
                                int nbCases=0;
                                while(!finFlaque){
                                    finFlaque=p.getPlateau()[k][j].getClass().getName()!="finstere_flure.Flaque";
                                    k++;
                                    if(!finFlaque){ //compteur du nombre de cases flaques dans cette direction
                                        nbCases++;
                                    }
                                }
                                if(this.toutesLesVerificationsPion(y+nbCases+1, x)){ //si il n'y a ni monstre ni bord de plateau sur la case après la flaque
                                    if(this.verifBlocPierre(y+nbCases, x)){
                                        //s'il n'y a pas de bloc de pierre coince d'un tour precedent sur la flaque d'hemoglobine
                                        if(this.verifPion(y+1+nbCases, x)){
                                            //s'il n'y a pas de pion sur la case juste apres la flaque
                                            if(this.verifBlocPierre(y+1+nbCases, x)){
                                                //s'il n'y a pas de bloc de pierre apres la flaque = s'il n'y a aucun pb apres la flaque
                                                for(int a=0; a<nbCases+1; a++){ //jusqu'a apres la flaque
                                                    this.deplacerPion(direction);
                                                }
                                                i--;
                                                direction=this.demanderDirection(); 
                                            }
                                            else{
                                                //s'il y a un bloc de pierre apres la flaque
                                                //il faut vérifier que le bloc de pierre peut etre decale
                                                if(this.toutesLesVerificationsBloc(y+2+nbCases, x)){ //on verifie que la case apres le bloc est vide
                                                    p.getPlateau()[y+nbCases+1][x].setBlocDePierre(false);
                                                    p.getPlateau()[y+nbCases+2][x].setBlocDePierre(true); //echange de la case bloc de pierre
                                                    for(int a=0; a<nbCases+1; a++){ //jusqu'a apres la flaque, pour mettre le pion
                                                        this.deplacerPion(direction);
                                                    }
                                                    i--;
                                                    direction=this.demanderDirection(); 
                                                }
                                                else{
                                                    //la case apres le bloc n'est pas vide, le pion ne pourra pas pousser le bloc, il devra donc changer de direction, necessite au moins un deplacement
                                                     if(i>1){
                                                        for(int a=0; a<nbCases-1; a++){ //jusqu'a l'avant derniere case de la flaque, pour mettre le pion
                                                             this.deplacerPion(direction);
                                                        } 
                                                        i--;
                                                        direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                                    }
                                                     else{ //il n'a pas assez de deplacement disponible
                                                         System.out.println("Vous ne pourrez pas vous arreter sur la flaque, changez de direction\n");
                                                     }
                                                }
                                            }
                                        }
                                        else{
                                            //s'il y a un pion sur la case juste apres la flaque, il faut verifier que plus d'un deplacement est encore disponible
                                            if(i>1){
                                               for(int a=0; a<nbCases+1; a++){ 
                                                        this.deplacerPion(direction);
                                                        i--;
                                                        direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                                } 
                                            }
                                            else{
                                                System.out.println("Vous ne pourrez pas vous arreter sur le pion, changez de direction\n");
                                                direction=this.demanderDirection();
                                            }
                                        }
                                    }
                                    else{
                                        //s'il y a un bloc coince d'un tour precedent sur la flaque d'hemoglobine a continuer
                                        //si apres la flaque il n'y a ni monstre ni bord ni pion ni bloc (le bloc sur la flaque peut etre pousse)
                                        if(this.toutesLesVerificationsBloc(y+nbCases+1, x)){
                                            //si sur la case encore après il n'y a pas non plus
                                            if(this.toutesLesVerificationsBloc(y+nbCases+2, x)){
                                                p.getPlateau()[y+nbCases][x].setBlocDePierre(false);
                                                p.getPlateau()[y+nbCases+2][x].setBlocDePierre(true); //echange de la case bloc de pierre
                                                 for(int a=0; a<nbCases+1; a++){ //jusqu'a apres la flaque, pour mettre le pion
                                                        this.deplacerPion(direction);
                                                 }
                                                 i--;
                                                 direction=this.demanderDirection(); 
                                            }
                                            else{
                                                //le bloc est pousse jusqu'a la case vide mais ne peut aller plus loin, le pion va etre coince sur la flaque
                                                if(i>1){
                                                    for(int a=0; a<nbCases-1; a++){ //jusqu'a l'avant derniere case de la flaque, pour mettre le pion
                                                             this.deplacerPion(direction);
                                                        } 
                                                    i--;
                                                    direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                                }
                                                else{
                                                    System.out.println("Vous ne pourrez pas vous arreter sur la flaque, changez de direction");
                                                    direction=this.demanderDirection();
                                                }
                                            }
                                        }
                                        else{
                                            //la premiere case n'est pas libre, le bloc ne peut pas etre pousse 
                                            if(i>1){
                                                for(int a=0; a<nbCases-2; a++){ //jusqu'a la case avant le bloc, pour mettre le pion
                                                    this.deplacerPion(direction);
                                                } 
                                                i--;
                                                direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                            }
                                            else{
                                                System.out.println("Vous ne pourrez pas vous arreter sur la flaque, changez de direction");
                                                direction=this.demanderDirection();
                                            }
                                        }
                                        
                                    }
                                }
                                else{
                                    //il y a un monstre ou le bord du plateau apres la flaque, le pion va etre bloque sur la flaque
                                    //s'il y a un bloc bloqué sur la flaque
                                    if(p.getPlateau()[y+nbCases][x].getBlocDePierre()==true){
                                        if(i>1){
                                                for(int a=0; a<nbCases-2; a++){ //jusqu'a la case avant le bloc, pour mettre le pion
                                                    this.deplacerPion(direction);
                                                } 
                                                i--;
                                                direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                            }
                                            else{
                                                System.out.println("Vous ne pourrez pas vous arreter sur la flaque, changez de direction");
                                                direction=this.demanderDirection();
                                            }
                                    }
                                    else{
                                        if(i>1){
                                                    for(int a=0; a<nbCases-1; a++){ //jusqu'a l'avant derniere case de la flaque, pour mettre le pion
                                                             this.deplacerPion(direction);
                                                        } 
                                                    i--;
                                                    direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                                }
                                                else{
                                                    System.out.println("Vous ne pourrez pas vous arreter sur la flaque, changez de direction");
                                                    direction=this.demanderDirection();
                                                }
                                    }
                                    
                                }
                                        
                            }
                            
                        }
                        //s'il y a le monstre sur la case
                        else{
                            System.out.println("Vous ne pouvez pas passer sur le monstre");
                            direction=this.demanderDirection();
                        }
                    }
                    //si on est au bord du tableau
                    else{
                        System.out.println("Vous avez atteint le bord du plateau, changez de direction");
                        direction=this.demanderDirection();
                    }
                }else if(direction==3){ //l'utilisateur veut aller à gauche
                    if(this.verifBordPlateau(this.y, this.x-1)){
                        if(this.verifMonstre(this.y, this.x-1)){
                            if(this.verifFlaqueSang(this.y, this.x-1)){
                                if(this.verifBlocPierre(this.y, this.x-1)){
                                    if(this.verifPion(this.y, this.x-1)){ 
                                        this.deplacerPion(direction); //s'il n'y a aucun pb de deplacement
                                        i--;
                                        direction=this.demanderDirection();
                                    }
                                    else{ //s'il y a un pion, on part du principe qu'il y a une issue possible autour du pion pour le moment
                                        if(i>1){ //le pion a encore un deplacement possible apres, il peut passer sur le pion
                                            this.deplacerPion(direction);
                                            i--;
                                            direction=this.empecherArret();
                                        }
                                        else{
                                            System.out.println("Vous ne pouvez pas vous arrêter sur un pion");
                                            direction=this.demanderDirection();
                                        }
                                    }
                                }
                                //s'il y a un bloc que faire
                                else{
                                    if(this.toutesLesVerificationsBloc(y, x-2) && this.verifFlaqueSang(y, x-2)){ //s'il n'y a rien après le bloc
                                        i--;
                                        p.getPlateau()[y][x-2].setBlocDePierre(true);
                                        p.getPlateau()[y][x-1].setBlocDePierre(false); //echange cases bloc de pierre et case non bloc de pierre
                                        this.deplacerPion(direction);
                                        direction=this.demanderDirection();
                                    }
                                    else if (!this.verifFlaqueSang(y, x-2)){
                                        //s'il y a une flaque de sang après le bloc
                                        boolean finFlaque=false;
                                        int k=y,j=x-2;
                                        int nbCases=0;
                                        while(!finFlaque){
                                            finFlaque=p.getPlateau()[k][j].getClass().getName()!="finstere_flure.Flaque";
                                            k++;
                                            if(!finFlaque){ //compteur du nombre de cases flaques dans cette direction
                                                nbCases++;
                                            }
                                        }
                                        if(this.toutesLesVerificationsBloc(y, x-2-nbCases)){//si la case après la flaque est vide
                                            p.getPlateau()[y][x-1].setBlocDePierre(false); //le bloc de pierre n'est plus sur cette case
                                            p.getPlateau()[y][x-2-nbCases].setBlocDePierre(true); //le bloc de pierre est poussé sur la case apres la flaque
                                            this.deplacerPion(direction); //le pion est deplace apres avoir decale le bloc de pierre
                                            i--;
                                            direction=this.demanderDirection();
                                        }
                                        else  { //si il y a quelque chose sur la case apres la flaque
                                            p.getPlateau()[y][x-1].setBlocDePierre(false); //le bloc de pierre n'est plus sur cette case
                                            p.getPlateau()[y][x-2-(nbCases-1)].setBlocDePierre(true); //le bloc de pierre est sur cette case, la derniere case flaque
                                            this.deplacerPion(direction); //le pion est deplace apres avoir decale le bloc de pierre
                                            i--;
                                            direction=this.demanderDirection();
                                        }
                                    }
                                    else{ //s'il y a un pion, un autre bloc, le monstre ou le bord du plateau après le bloc
                                        System.out.println("Vous ne pouvez pas pousser ce bloc de pierre, veuillez changer de direction");
                                        direction=this.demanderDirection();
                                    }
                                }
                            }
                            //s'il y a une flaque de sang 
                            else{
                                boolean finFlaque=false;
                                int k=y,j=x-1;
                                int nbCases=0;
                                while(!finFlaque){
                                    finFlaque=p.getPlateau()[k][j].getClass().getName()!="finstere_flure.Flaque";
                                    k++;
                                    if(!finFlaque){ //compteur du nombre de cases flaques dans cette direction
                                        nbCases++;
                                    }
                                }
                                if(this.toutesLesVerificationsPion(y, x-nbCases-1)){ //si il n'y a ni monstre ni bord de plateau sur la case après la flaque
                                    if(this.verifBlocPierre(y, x-nbCases)){
                                        //s'il n'y a pas de bloc de pierre coince d'un tour precedent sur la flaque d'hemoglobine
                                        if(this.verifPion(y, x-1-nbCases)){
                                            //s'il n'y a pas de pion sur la case juste apres la flaque
                                            if(this.verifBlocPierre(y, x-1-nbCases)){
                                                //s'il n'y a pas de bloc de pierre apres la flaque = s'il n'y a aucun pb apres la flaque
                                                for(int a=0; a<nbCases+1; a++){ //jusqu'a apres la flaque
                                                    this.deplacerPion(direction);
                                                }
                                                i--;
                                                direction=this.demanderDirection(); 
                                            }
                                            else{
                                                //s'il y a un bloc de pierre apres la flaque
                                                //il faut vérifier que le bloc de pierre peut etre decale
                                                if(this.toutesLesVerificationsBloc(y, x-2-nbCases)){ //on verifie que la case apres le bloc est vide
                                                    p.getPlateau()[y][x-nbCases-1].setBlocDePierre(false);
                                                    p.getPlateau()[y][x-nbCases-2].setBlocDePierre(true); //echange de la case bloc de pierre
                                                    for(int a=0; a<nbCases+1; a++){ //jusqu'a apres la flaque, pour mettre le pion
                                                        this.deplacerPion(direction);
                                                    }
                                                    i--;
                                                    direction=this.demanderDirection(); 
                                                }
                                                else{
                                                    //la case apres le bloc n'est pas vide, le pion ne pourra pas pousser le bloc, il devra donc changer de direction, necessite au moins un deplacement
                                                     if(i>1){
                                                        for(int a=0; a<nbCases-1; a++){ //jusqu'a l'avant derniere case de la flaque, pour mettre le pion
                                                             this.deplacerPion(direction);
                                                        } 
                                                        i--;
                                                        direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                                    }
                                                     else{ //il n'a pas assez de deplacement disponible
                                                         System.out.println("Vous ne pourrez pas vous arreter sur la flaque, changez de direction\n");
                                                     }
                                                }
                                            }
                                        }
                                        else{
                                            //s'il y a un pion sur la case juste apres la flaque, il faut verifier que plus d'un deplacement est encore disponible
                                            if(i>1){
                                               for(int a=0; a<nbCases+1; a++){ 
                                                        this.deplacerPion(direction);
                                                        i--;
                                                        direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                                } 
                                            }
                                            else{
                                                System.out.println("Vous ne pourrez pas vous arreter sur le pion, changez de direction\n");
                                                direction=this.demanderDirection();
                                            }
                                        }
                                    }
                                    else{
                                        //s'il y a un bloc coince d'un tour precedent sur la flaque d'hemoglobine a continuer
                                        //si apres la flaque il n'y a ni monstre ni bord ni pion ni bloc (le bloc sur la flaque peut etre pousse)
                                        if(this.toutesLesVerificationsBloc(y, x-nbCases-1)){
                                            //si sur la case encore après il n'y a pas non plus
                                            if(this.toutesLesVerificationsBloc(y, x-nbCases-2)){
                                                p.getPlateau()[y][x-nbCases].setBlocDePierre(false);
                                                p.getPlateau()[y][x-nbCases-2].setBlocDePierre(true); //echange de la case bloc de pierre
                                                 for(int a=0; a<nbCases+1; a++){ //jusqu'a apres la flaque, pour mettre le pion
                                                        this.deplacerPion(direction);
                                                 }
                                                 i--;
                                                 direction=this.demanderDirection(); 
                                            }
                                            else{
                                                //le bloc est pousse jusqu'a la case vide mais ne peut aller plus loin, le pion va etre coince sur la flaque
                                                if(i>1){
                                                    for(int a=0; a<nbCases-1; a++){ //jusqu'a l'avant derniere case de la flaque, pour mettre le pion
                                                             this.deplacerPion(direction);
                                                        } 
                                                    i--;
                                                    direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                                }
                                                else{
                                                    System.out.println("Vous ne pourrez pas vous arreter sur la flaque, changez de direction");
                                                    direction=this.demanderDirection();
                                                }
                                            }
                                        }
                                        else{
                                            //la premiere case n'est pas libre, le bloc ne peut pas etre pousse 
                                            if(i>1){
                                                for(int a=0; a<nbCases-2; a++){ //jusqu'a la case avant le bloc, pour mettre le pion
                                                    this.deplacerPion(direction);
                                                } 
                                                i--;
                                                direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                            }
                                            else{
                                                System.out.println("Vous ne pourrez pas vous arreter sur la flaque, changez de direction");
                                                direction=this.demanderDirection();
                                            }
                                        }
                                        
                                    }
                                }
                                else{
                                    //il y a un monstre ou le bord du plateau apres la flaque, le pion va etre bloque sur la flaque
                                    //s'il y a un bloc bloqué sur la flaque
                                    if(p.getPlateau()[y][x-nbCases].getBlocDePierre()==true){
                                        if(i>1){
                                                for(int a=0; a<nbCases-2; a++){ //jusqu'a la case avant le bloc, pour mettre le pion
                                                    this.deplacerPion(direction);
                                                } 
                                                i--;
                                                direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                            }
                                            else{
                                                System.out.println("Vous ne pourrez pas vous arreter sur la flaque, changez de direction");
                                                direction=this.demanderDirection();
                                            }
                                    }
                                    else{
                                        if(i>1){
                                                    for(int a=0; a<nbCases-1; a++){ //jusqu'a l'avant derniere case de la flaque, pour mettre le pion
                                                             this.deplacerPion(direction);
                                                        } 
                                                    i--;
                                                    direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                                }
                                                else{
                                                    System.out.println("Vous ne pourrez pas vous arreter sur la flaque, changez de direction");
                                                    direction=this.demanderDirection();
                                                }
                                    }
                                    
                                }
                                        
                            }
                            
                        }
                        //s'il y a le monstre sur la case
                        else{
                            System.out.println("Vous ne pouvez pas passer sur le monstre");
                            direction=this.demanderDirection();
                        }
                    }
                    //si on est au bord du tableau
                    else{
                        System.out.println("Vous avez atteint le bord du plateau, changez de direction");
                        direction=this.demanderDirection();
                    }
                }else if(direction==4){ //l'utilisateur veut aller à gauche
                    if(this.verifBordPlateau(this.y, this.x+1)){
                        if(this.verifMonstre(this.y, this.x+1)){
                            if(this.verifFlaqueSang(this.y, this.x+1)){
                                if(this.verifBlocPierre(this.y, this.x+1)){
                                    if(this.verifPion(this.y, this.x+1)){ 
                                        this.deplacerPion(direction); //s'il n'y a aucun pb de deplacement
                                        i--;
                                        direction=this.demanderDirection();
                                    }
                                    else{ //s'il y a un pion, on part du principe qu'il y a une issue possible autour du pion pour le moment
                                        if(i>1){ //le pion a encore un deplacement possible apres, il peut passer sur le pion
                                            this.deplacerPion(direction);
                                            i--;
                                            direction=this.empecherArret();
                                        }
                                        else{
                                            System.out.println("Vous ne pouvez pas vous arrêter sur un pion");
                                            direction=this.demanderDirection();
                                        }
                                    }
                                }
                                //s'il y a un bloc que faire
                                else{
                                    if(this.toutesLesVerificationsBloc(y, x+2) && this.verifFlaqueSang(y, x+2)){ //s'il n'y a rien après le bloc
                                        i--;
                                        p.getPlateau()[y][x+2].setBlocDePierre(true);
                                        p.getPlateau()[y][x+1].setBlocDePierre(false); //echange cases bloc de pierre et case non bloc de pierre
                                        this.deplacerPion(direction);
                                        direction=this.demanderDirection();
                                    }
                                    else if (!this.verifFlaqueSang(y, x+2)){
                                        //s'il y a une flaque de sang après le bloc
                                        boolean finFlaque=false;
                                        int k=y,j=x+2;
                                        int nbCases=0;
                                        while(!finFlaque){
                                            finFlaque=p.getPlateau()[k][j].getClass().getName()!="finstere_flure.Flaque";
                                            k++;
                                            if(!finFlaque){ //compteur du nombre de cases flaques dans cette direction
                                                nbCases++;
                                            }
                                        }
                                        if(this.toutesLesVerificationsBloc(y, x+2+nbCases)){//si la case après la flaque est vide
                                            p.getPlateau()[y][x+1].setBlocDePierre(false); //le bloc de pierre n'est plus sur cette case
                                            p.getPlateau()[y][x+2+nbCases].setBlocDePierre(true); //le bloc de pierre est poussé sur la case apres la flaque
                                            this.deplacerPion(direction); //le pion est deplace apres avoir decale le bloc de pierre
                                            i--;
                                            direction=this.demanderDirection();
                                        }
                                        else  { //si il y a quelque chose sur la case apres la flaque
                                            p.getPlateau()[y][x+1].setBlocDePierre(false); //le bloc de pierre n'est plus sur cette case
                                            p.getPlateau()[y][x+2+(nbCases-1)].setBlocDePierre(true); //le bloc de pierre est sur cette case, la derniere case flaque
                                            this.deplacerPion(direction); //le pion est deplace apres avoir decale le bloc de pierre
                                            i--;
                                            direction=this.demanderDirection();
                                        }
                                    }
                                    else{ //s'il y a un pion, un autre bloc, le monstre ou le bord du plateau après le bloc
                                        System.out.println("Vous ne pouvez pas pousser ce bloc de pierre, veuillez changer de direction");
                                        direction=this.demanderDirection();
                                    }
                                }
                            }
                            //s'il y a une flaque de sang 
                            else{
                                boolean finFlaque=false;
                                int k=y,j=x+1;
                                int nbCases=0;
                                while(!finFlaque){
                                    finFlaque=p.getPlateau()[k][j].getClass().getName()!="finstere_flure.Flaque";
                                    k++;
                                    if(!finFlaque){ //compteur du nombre de cases flaques dans cette direction
                                        nbCases++;
                                    }
                                }
                                if(this.toutesLesVerificationsPion(y, x+nbCases+1)){ //si il n'y a ni monstre ni bord de plateau sur la case après la flaque
                                    if(this.verifBlocPierre(y, x+nbCases)){
                                        //s'il n'y a pas de bloc de pierre coince d'un tour precedent sur la flaque d'hemoglobine
                                        if(this.verifPion(y, x+1+nbCases)){
                                            //s'il n'y a pas de pion sur la case juste apres la flaque
                                            if(this.verifBlocPierre(y, x+1+nbCases)){
                                                //s'il n'y a pas de bloc de pierre apres la flaque = s'il n'y a aucun pb apres la flaque
                                                for(int a=0; a<nbCases+1; a++){ //jusqu'a apres la flaque
                                                    this.deplacerPion(direction);
                                                }
                                                i--;
                                                direction=this.demanderDirection(); 
                                            }
                                            else{
                                                //s'il y a un bloc de pierre apres la flaque
                                                //il faut vérifier que le bloc de pierre peut etre decale
                                                if(this.toutesLesVerificationsBloc(y, x+2+nbCases)){ //on verifie que la case apres le bloc est vide
                                                    p.getPlateau()[y][x+nbCases+1].setBlocDePierre(false);
                                                    p.getPlateau()[y][x+nbCases+2].setBlocDePierre(true); //echange de la case bloc de pierre
                                                    for(int a=0; a<nbCases+1; a++){ //jusqu'a apres la flaque, pour mettre le pion
                                                        this.deplacerPion(direction);
                                                    }
                                                    i--;
                                                    direction=this.demanderDirection(); 
                                                }
                                                else{
                                                    //la case apres le bloc n'est pas vide, le pion ne pourra pas pousser le bloc, il devra donc changer de direction, necessite au moins un deplacement
                                                     if(i>1){
                                                        for(int a=0; a<nbCases-1; a++){ //jusqu'a l'avant derniere case de la flaque, pour mettre le pion
                                                             this.deplacerPion(direction);
                                                        } 
                                                        i--;
                                                        direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                                    }
                                                     else{ //il n'a pas assez de deplacement disponible
                                                         System.out.println("Vous ne pourrez pas vous arreter sur la flaque, changez de direction\n");
                                                     }
                                                }
                                            }
                                        }
                                        else{
                                            //s'il y a un pion sur la case juste apres la flaque, il faut verifier que plus d'un deplacement est encore disponible
                                            if(i>1){
                                               for(int a=0; a<nbCases+1; a++){ 
                                                        this.deplacerPion(direction);
                                                        i--;
                                                        direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                                } 
                                            }
                                            else{
                                                System.out.println("Vous ne pourrez pas vous arreter sur le pion, changez de direction\n");
                                                direction=this.demanderDirection();
                                            }
                                        }
                                    }
                                    else{
                                        //s'il y a un bloc coince d'un tour precedent sur la flaque d'hemoglobine 
                                        //si apres la flaque il n'y a ni monstre ni bord ni pion ni bloc (le bloc sur la flaque peut etre pousse)
                                        if(this.toutesLesVerificationsBloc(y, x+nbCases+1)){
                                            //si sur la case encore après il n'y a pas non plus
                                            if(this.toutesLesVerificationsBloc(y, x+nbCases+2)){
                                                p.getPlateau()[y][x+nbCases].setBlocDePierre(false);
                                                p.getPlateau()[y][x+nbCases+2].setBlocDePierre(true); //echange de la case bloc de pierre
                                                 for(int a=0; a<nbCases+1; a++){ //jusqu'a apres la flaque, pour mettre le pion
                                                        this.deplacerPion(direction);
                                                 }
                                                 i--;
                                                 direction=this.demanderDirection(); 
                                            }
                                            else{
                                                //le bloc est pousse jusqu'a la case vide mais ne peut aller plus loin, le pion va etre coince sur la flaque
                                                if(i>1){
                                                    for(int a=0; a<nbCases-1; a++){ //jusqu'a l'avant derniere case de la flaque, pour mettre le pion
                                                             this.deplacerPion(direction);
                                                        } 
                                                    i--;
                                                    direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                                }
                                                else{
                                                    System.out.println("Vous ne pourrez pas vous arreter sur la flaque, changez de direction");
                                                    direction=this.demanderDirection();
                                                }
                                            }
                                        }
                                        else{
                                            //la premiere case n'est pas libre, le bloc ne peut pas etre pousse 
                                            if(i>1){
                                                for(int a=0; a<nbCases-2; a++){ //jusqu'a la case avant le bloc, pour mettre le pion
                                                    this.deplacerPion(direction);
                                                } 
                                                i--;
                                                direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                            }
                                            else{
                                                System.out.println("Vous ne pourrez pas vous arreter sur la flaque, changez de direction");
                                                direction=this.demanderDirection();
                                            }
                                        }
                                        
                                    }
                                }
                                else{
                                    //il y a un monstre ou le bord du plateau apres la flaque, le pion va etre bloque sur la flaque
                                    //s'il y a un bloc bloqué sur la flaque
                                    if(p.getPlateau()[y][x+nbCases].getBlocDePierre()==true){
                                        if(i>1){
                                                for(int a=0; a<nbCases-2; a++){ //jusqu'a la case avant le bloc, pour mettre le pion
                                                    this.deplacerPion(direction);
                                                } 
                                                i--;
                                                direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                            }
                                            else{
                                                System.out.println("Vous ne pourrez pas vous arreter sur la flaque, changez de direction");
                                                direction=this.demanderDirection();
                                            }
                                    }
                                    else{
                                        if(i>1){
                                                    for(int a=0; a<nbCases-1; a++){ //jusqu'a l'avant derniere case de la flaque, pour mettre le pion
                                                             this.deplacerPion(direction);
                                                        } 
                                                    i--;
                                                    direction=this.empecherArret(); //impossible de s'arreter sur cette case
                                                }
                                                else{
                                                    System.out.println("Vous ne pourrez pas vous arreter sur la flaque, changez de direction");
                                                    direction=this.demanderDirection();
                                                }
                                    }
                                    
                                }
                                        
                            }
                            
                        }
                        //s'il y a le monstre sur la case
                        else{
                            System.out.println("Vous ne pouvez pas passer sur le monstre");
                            direction=this.demanderDirection();
                        }
                    }
                    //si on est au bord du tableau
                    else{
                        System.out.println("Vous avez atteint le bord du plateau, changez de direction");
                        direction=this.demanderDirection();
                    }
                }
            }
            else{
            //si le joueur veut et peut s'arrêter
            i=0;
            System.out.println("Votre tour est terminé");
            }
        }
        p.getPlateau()[this.y][this.x].setPion(true); //nouvel emplacement du pion
        this.retournerPion();
    }
}
