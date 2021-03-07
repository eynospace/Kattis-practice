
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}


public class lostMap {
    public static void main(String[] args){
        Kattio sc = new Kattio(System.in);
        int n = sc.getInt();
        ArrayList<Pair>[] adjlst = new ArrayList[n+1];
        boolean[] taken = new boolean[n+1];
        Queue<Pair> q = new PriorityQueue<>();
        for(int i=1; i<n+1; i++){
            taken[i]=false;
            ArrayList<Pair> lst = new ArrayList<>();
            for(int j=1; j<n+1;j++){
                int dist = sc.getInt();
                if(dist!=0) lst.add(new Pair(i, j, dist));
            }
            adjlst[i]=lst;
        }
        taken[1]=true;
        for(int i=0; i<n-1; i++){
            q.add(adjlst[1].get(i));
        }
        while(!q.isEmpty()){
            Pair curr = q.poll();
            if(taken[curr.neighbor]==false){
                sc.print(curr.self+" "+curr.neighbor+"\n");
                taken[curr.neighbor]=true;
                for(int i=0; i<n-1; i++){
                    q.add(adjlst[curr.neighbor].get(i));
                }
            }
        }
        sc.close();
    }
}
class Pair implements Comparable<Pair>{
    int self;
    int neighbor;
    int weight;

    public Pair(int self, int neighbor, int weight){
        this.self = self;
        this.neighbor = neighbor;
        this.weight = weight;
    }
    
    public int compareTo(Pair other){
        if(this.weight == other.weight){
            if(this.self == other.self){
                return this.neighbor - other.neighbor;
            }else return this.self - other.self;
        }
        return this.weight - other.weight;
    }
}