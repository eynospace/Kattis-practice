import java.util.Arrays;
import java.util.Scanner;

// ZHU ENYAO A0206188B
public class sortOfSorting{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int numOfnames = Integer.parseInt(sc.nextLine());
        while (numOfnames !=0){
            SortByName arr[] = new SortByName[numOfnames];
            for(int i=0; i<numOfnames; i++){
                arr[i] = new SortByName(sc.nextLine());
            }
            Arrays.sort(arr);
            for(int j=0; j<arr.length; j++){
                System.out.print(arr[j].name+ "\n");
            }
            System.out.print("\n");
            numOfnames = Integer.parseInt(sc.nextLine());
        }
    }
}
class SortByName implements Comparable<SortByName>{
    String name;
    public SortByName(String name){
        this.name = name;
    }
    public int compareTo(SortByName other){
        if(name.charAt(0)-other.name.charAt(0)!=0){
            return name.charAt(0) - other.name.charAt(0);
        }else{return name.charAt(1) - other.name.charAt(1);}
    }
}
