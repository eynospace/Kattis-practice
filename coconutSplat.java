import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class coconutSplat{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int syllabus = sc.nextInt();
        int n = sc.nextInt();
        LinkedList<Players> lst = new LinkedList<Players>();
        for(int i=0; i<n; i++){
            Players player = new Players(i+1, 3);
            lst.add(player);
        }
        while(lst.size()>1){
            for(int j=0; j<syllabus-1; j++){
                lst.offerLast(lst.pollFirst());
            }
            Players curr = lst.pollFirst();
            if(curr.state==3){
                Players newplayer1 = new Players(curr.num,2);
                lst.addFirst(newplayer1);
                lst.addFirst(newplayer1);
            }
            else if(curr.state==2){
                Players newplayer2 = new Players(curr.num,1);
                lst.addLast(newplayer2);
            }
            else if(curr.state==1){continue;}
        }
        int number = lst.element().num;
        System.out.print(number);
    }
}
class Players{
   int num; int state;
    public Players(int num, int state){
        this.num = num;
        this.state = state;
    }
}