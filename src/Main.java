import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        double[] c1 = {1, 2, 3};
        double[] c2 = {-1, -2, -4};
        Polynom p1 = new Polynom(c1);
        Polynom p2 = new Polynom(c2);
        Polynom p3 = p1.plus(p2);
        System.out.println(p1);
        System.out.println("P1(1)="+p1.invoke(1));
        System.out.println(p2);
        System.out.println("P2(-2)="+p2.invoke(-2));
        System.out.println(p3);
        System.out.println("P3(5)="+p3.invoke(5));
        System.out.println(p3.minus(p3));
        System.out.println(p3.times(p3));
        System.out.println(p3.times(4));
        var dots = new HashMap<Double,Double>();
        dots.put(-1.0,-1.0);
        dots.put(0.0,0.0);
        dots.put(1.0,1.0);
        var lagr = new Lagrange(dots);
        System.out.println(lagr);
    }
}
