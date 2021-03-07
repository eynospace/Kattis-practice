import java.util.*;

public class peaSoup{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
            int numOfResto = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < numOfResto; i++){
                int numOfitems = Integer.parseInt(sc.nextLine());
                String nameOfResto = sc.nextLine();
                boolean pea = false, pancakes = false;
                for (int j = 0; j < numOfitems; j++){
                    String item = sc.nextLine();
                    if (item.equals("pea soup")){
                        pea = true;
                    } else if (item.equals("pancakes")){
                        pancakes = true;
                    }
                }
                if (pea && pancakes){
                    System.out.println(nameOfResto);
                    System.exit(0);
                } 
            
            }
            System.out.println("Anywhere is fine I guess");

    }
}