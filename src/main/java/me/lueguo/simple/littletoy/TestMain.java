package me.lueguo.simple.littletoy;

public class TestMain {
    public static void main(String[] args) {
        Point<Integer> p1 = new Point<>() ;
        p1.setX(new Integer(100)) ;
        System.out.println(p1.getX());

        Point<Float> p2 = new Point<>() ;
        p2.setX(new Float(100.12f)) ;
        System.out.println(p2.getX());
    }
}

class Point<ch>{
    private ch x ;
    private ch y ;
    public void setX(ch x){
        this.x = x ;
    }
    public void setY(ch y){
        this.y = y ;
    }
    public ch getX(){
        return this.x ;
    }
    public ch getY(){
        return this.y ;
    }
};

