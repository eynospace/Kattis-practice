import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//ZHU ENYAO A0206188B
public class bestRelayTeam{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int numOfRunners = Integer.parseInt(sc.nextLine());
        // create new obejct array then loop all runners into the array
        SortByLast[] arr = new SortByLast[numOfRunners];
        for (int i=0; i<numOfRunners; i++){
            String runner = sc.next();
            Double firstLeg = sc.nextDouble();
            double secondLeg = sc.nextDouble();
            arr[i] = new SortByLast(runner, firstLeg, secondLeg);
            sc.nextLine();
        }
        // sort both first and second leg runners timings in ascending order 
        Arrays.sort(arr);
        // randomly form a temporary team as best team, update later if the record is smaller
        ArrayList<SortByLast> bestTeam = new ArrayList<SortByLast>();
        bestTeam.add(arr[0]);
        bestTeam.add(arr[1]);
        bestTeam.add(arr[2]);
        bestTeam.add(arr[3]);
        double res=arr[0].firstLeg;
        for(int a=1; a<4; a++){
            res+=arr[a].secondLeg;
        }
        // add first runner, then loop to add others
        for (int i=0; i<numOfRunners; i++){
            ArrayList<SortByLast> currTeam = new ArrayList<SortByLast>();
            currTeam.add(arr[i]);
            double record = arr[i].firstLeg;
            //loop in the next 3 runners (makes sure the currTeam has no more than 4 runners)
            for (int j=0; currTeam.size()<4; j++){
                //if same runner then continue, else add
                if(arr[i]!=arr[j]){
                    currTeam.add(arr[j]);
                    record+=arr[j].secondLeg;
                }
            }// update bestTeam
            if(record<res){
                bestTeam = currTeam;
                res = record;
            }
        }
        System.out.printf("%.9f\n",res);
        for(int k=0; k<4; k++){
            System.out.print(bestTeam.get(k).runner+"\n");
        }
    }
}
class SortByLast implements Comparable<SortByLast>{
    String runner;
    double firstLeg;
    double secondLeg;
    public SortByLast(String runner, double firstLeg, double secondLeg){
        this.runner = runner;
        this.firstLeg = firstLeg;
        this.secondLeg = secondLeg;
    }
    public int compareTo(SortByLast other){
        if(secondLeg<other.secondLeg){
            return -1;
        }
        else if(secondLeg>other.secondLeg){
            return 1;
        }else return 0;
    }
}