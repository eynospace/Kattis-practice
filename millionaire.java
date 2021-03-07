import java.util.ArrayList;
import java.util.PriorityQueue;
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

public class millionaire{
    public static void main(String[] args){
        Kattio sc = new Kattio(System.in);
        int row = sc.getInt();
        int col = sc.getInt();
        Vertex[][] grid = new Vertex[row][col];
        PriorityQueue<IntegerPair> pq = new PriorityQueue<>();
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                grid[i][j] = new Vertex(sc.getInt(), i, j);
            }
        }
        int[][] d = new int[row][col];
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(i==0 && j==0) d[i][j]=0;
                else d[i][j]=1000000000;
            }
        }
        pq.offer(new IntegerPair(0, grid[0][0]));
        while(!pq.isEmpty()){
            IntegerPair curr = pq.poll();
            if(curr.d == d[curr.u.r][curr.u.c]){
                int up, down, left, right, diff;
                up = curr.u.r+1;
                down = curr.u.r-1;
                left = curr.u.c-1;
                right = curr.u.c+1;
                Vertex next;
                //up
                if(up<row){
                    next = grid[up][curr.u.c];
                    diff = next.w - curr.u.w;
                    if(Math.max(d[curr.u.r][curr.u.c], diff)<d[next.r][next.c]){
                        d[next.r][next.c]=Math.max(d[curr.u.r][curr.u.c], diff);
                        pq.offer(new IntegerPair(d[next.r][next.c], next));
                    }
                }
                //down
                if(0<=down){
                    next = grid[down][curr.u.c];
                    diff = next.w - curr.u.w;
                    if(Math.max(d[curr.u.r][curr.u.c], diff)<d[next.r][next.c]){
                        d[next.r][next.c]=Math.max(d[curr.u.r][curr.u.c], diff);
                        pq.offer(new IntegerPair(d[next.r][next.c], next));
                    }
                }
                //left
                if(0<=left){
                    next = grid[curr.u.r][left];
                    diff = next.w - curr.u.w;
                    if(Math.max(d[curr.u.r][curr.u.c], diff)<d[next.r][next.c]){
                        d[next.r][next.c]=Math.max(d[curr.u.r][curr.u.c], diff);
                        pq.offer(new IntegerPair(d[next.r][next.c], next));
                    }
                }
                //right 
                if(right<col){
                    next = grid[curr.u.r][right];
                    diff = next.w - curr.u.w;
                    if(Math.max(d[curr.u.r][curr.u.c], diff)<d[next.r][next.c]){
                        d[next.r][next.c]=Math.max(d[curr.u.r][curr.u.c], diff);
                        pq.offer(new IntegerPair(d[next.r][next.c], next));
                    }
                }
            }
        }
        sc.print(d[row-1][col-1]);
        sc.close();

    }

}
class Vertex{
    int w;
    int r;
    int c;
    public Vertex(int w, int r, int c){
        this.w = w;
        this.r = r;
        this.c = c;
    }
}
class IntegerPair implements Comparable<IntegerPair>{
    int d;
    Vertex u;
    public IntegerPair(int d, Vertex u){
        this.d = d;
        this.u = u;

    }
    public int compareTo(IntegerPair o){
        if(this.d == o.d){
            return this.u.w - o.u.w;
        }else return this.d - o.d;
    }
}