import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class conformity{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        HashMap<ArrayList, Integer> pop = new HashMap<>();
        for(int i=0; i<n; i++){
            String[] combiarr = sc.nextLine().split(" ");
            ArrayList<Integer> combi = new ArrayList<Integer>();
            for(int q=0; q<combiarr.length; q++){
                combi.add(Integer.parseInt(combiarr[q]));
            }
            Collections.sort(combi);
            if(pop.containsKey(combi)){
                int count = pop.get(combi);
                pop.put(combi, count+1);
            }else{pop.put(combi, 1);}
        }
        
        int max = 0; int total = 0;
        for (Map.Entry<ArrayList, Integer> entry : pop.entrySet()) {
            if (entry.getValue()>max){
                max = entry.getValue();
                total = entry.getValue();
            }
            else if(entry.getValue()==max){
                total += entry.getValue();
            }
        }
        System.out.print(total);
        //for j=1, find largest
        //set counter = 0, loop again to add all the largest into counter
        
    }
}
