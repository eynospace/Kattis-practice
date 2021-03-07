import java.util.Scanner;
import java.util.TreeMap;
import java.lang.Long;

public class kattisQuest{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        TreeMap<Query, Integer> quests = new TreeMap<>();
            for(int i=0; i<n; i++){
            //read command
            String[] command = sc.nextLine().split(" ");
            //if command.equals add
            if(command[0].equals("add")){
                long E = Long.parseLong(command[1]);
                long G = Long.parseLong(command[2]);
                Query q = new Query(E, G);
                if(quests.containsKey(q)){
                    int count = quests.get(q)+1;
                    quests.put(q, count);
                }else{quests.put(q, 1);}
            }
            if(command[0].equals("query")){
                long Einput = Long.parseLong(command[1]);
                long reward = 0;
                while(Einput>0){
                    Query input = new Query(Einput, 999999999);
                    if(quests.floorKey(input)==null){
                        break;
                    }
                    if(quests.isEmpty()){
                        break;
                    }
                    Query k = quests.floorKey(input);
                    Einput -= k.E;
                    reward += k.G;
                    if(quests.get(k)==1){
                        quests.remove(k);
                    }else{
                        int count2 = quests.get(k)-1;
                        quests.put(k, count2);
                    }
                }
                System.out.print(reward);
                System.out.print("\n");
            }
        }
    }
}

class Query implements Comparable<Query>{
    long E;
    long G;
    public Query(long E, long G){
        this.E = E;
        this.G = G;
    }
    public int compareTo(Query other){
        if((int)this.E == (int)other.E){
            return (int)this.G - (int)other.G;
        }else return (int)this.E - (int)other.E;
    }
}
