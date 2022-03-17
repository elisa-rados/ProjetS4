/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetS4;

/**
 *
 * @author rados
 */

public class Point{
    private double x,y ;
    public Point (double abs, double ord){
        this.x = abs;
        this.y = ord;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.Y;
    }
    public void setX(double abs){
        this.x=abs;
    }
     public void setY(double abs){
        this.y=abs;
    }
}

public class Segment {
    private Point p1,p2;
    
    public Segment (Point un, Point deux){
        this.p1=un;
        this.p2=deux;
    }
    
    public Segment(double a, double b, double c, double d){
        this.p1=new Point(a,b);
        this.p2=new Point(c,d);
    }
    public Point getP1(){//accès en lecture
        return this.p1
    }
    public void setP1(Point p){//accès en écriture
        this.p1=p; //c'est un passage par référence
    }
     public Point getP2(){//accès en lecture
        return this.p2;
    }
    public void setP2(Point p){//accès en écriture
        this.p2=p; //c'est un passage par référence
    }
}

