import java.util.Scanner;

// ZHU ENYAO A0206188B
public class trainPassengers{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int capacity = sc.nextInt();
        int stops = sc.nextInt();
        int passengers = 0;
        boolean possible = true;
        int wait = 0;
        for (int i=0; i<stops; i++){
            sc.nextLine();
            int left = sc.nextInt();
            int board = sc.nextInt();
            int stay = sc.nextInt();
            wait = stay;
            passengers -= left;
            if (passengers<0){
                possible = false;
            }
            passengers += board;
            if (passengers<capacity && wait>0){
                possible = false;
            }
            if (passengers>capacity){
                possible = false;
            }
        }
        if (passengers != 0){
            possible = false;
        }
        if (wait != 0){
            possible = false;
        }
        if (possible == true){
            System.out.println("possible");
        }else {System.out.println("impossible");}
    }
}
