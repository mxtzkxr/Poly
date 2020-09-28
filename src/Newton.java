import java.util.HashMap;
class Pairs{
    private int i;
    private int j;
    public Pairs(int i,int j){
        this.i = i;
        this.j = j;
    }

}
public class Newton extends Polynom{
    private final HashMap<Double,Double> dots;
    private final HashMap<Pairs,Double> pairs;
    private  Double[] keys;
    //private Double h;
    public Newton(HashMap<Double,Double> v) {
        dots = v;
        keys = dots.keySet().toArray(new Double[0]);
        pairs = new HashMap<>();
        //Double Sn = 0.0;
        //Double a0=0.0;
       // for (var k:dots.keySet()){
       //     a0=k;
       //     break;
      //  }
      //  for(var k:dots.keySet()){
      //      if(a0>=k){
       //         a0 = k;
      //      }
      //      Sn+=k;
      //  }
      //  h = 2*Sn/(dots.size()*(dots.size()-1)) - 2*a0/(dots.size()-1);
        createPoly();
    }
    private void createPoly(){
        Polynom p = new Polynom();
        Double a0=0.0;
         for (var k:dots.keySet()){
            a0=k;
             break;
          }
         int counter = 1;
         int n = dots.size();
         double previousKey=a0;
        p.plus(new Polynom((new double[]{a0})));
        for (int i =1;i<=dots.size();i++){
            Polynom w = new Polynom();
            for(int k = 0;k<i;k++){
                w.times(new Polynom(new double[]{-keys[k],1}));
            }
            w.times(razdRazn(0,i));



            if(k!=a0) {

                if (!pairs.containsKey(new Pairs(counter,counter-1))){
                    Double d = dots.get(k)-dots.get(previousKey)/(k-previousKey);//разделенная разность первого рода
                    pairs.put(new Pairs(counter,counter-1),d);
                }
                //counter - еще и значение максимального порядка
                int i = 1;
                while (pairs.containsKey(new Pairs(counter-1,counter-i))){
                    // проще вместить в класс пар сами узлы, а не выдуманные индексы для этих пар
                    i++;
                }
                p = p.plus(fundamental(k).times(dots.get(k)));
            }
            counter++;
        }
    }
    private Double razdRazn(int i,int j){
        var ind = new Pairs(i,j);
        if (pairs.containsKey(ind)){
            return pairs.get(ind);
        }
        Double rr = razdRazn(i+1,j)-razdRazn(i,j-1)/(keys[j]-keys[i]);
        pairs.put(ind,rr);
        return rr;
    }
    private Polynom fundamental(double x){
        Polynom p = new Polynom(new double[]{1.0});
        try{
            for(var k :dots.keySet()){
                if(Math.abs(x-k)>=ZERO) {
                    p = p.times(new Polynom(new double[]{-k, 1}).div(x - k));
                }
            }
        }catch (Exception e)
        {
            p = new Polynom();
        }
        coef = p.coef.clone();
        return p;

    }

}
