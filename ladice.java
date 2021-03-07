import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class ladice{
    public static void main(String[] args){
        Kattio sc = new Kattio(System.in);
        int N = sc.getInt();
        int L = sc.getInt();
        UnionFind disjointSet = new UnionFind(L+1);
        for(int i=0; i<N; i++){
            int A = sc.getInt();
            int B = sc.getInt();
            disjointSet.unionSet(A, B);
            //use function to check if there is empty drawer in a set, true -> LADICA else SMECE
            if(disjointSet.check(A)){
                sc.print("LADICA"+"\n");
            }else {
                sc.print("SMECE"+"\n");
            }
        }
        sc.close();
    }
}

class UnionFind {                                              
    public int[] p;
    public int[] rank;
    public int numSets;
    //used to record full drawer in a set
    public int[] full;
    //total to record size of a set
    public int[] total;
  
    public UnionFind(int N) {
      p = new int[N];
      rank = new int[N];
      numSets = N;
      full =  new int[N];
      total = new int[N];
      for(int i=0; i<N; i++){
          total[i]=1;
      }

      for (int i = 0; i < N; i++) {
        p[i] = i;
        rank[i] = 0;
      }
    }
  
    public int findSet(int i) { 
      if (p[i] == i) return i;
      else {
        p[i] = findSet(p[i]);
        return p[i]; 
      } 
    }
  
    public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }
  
    public void unionSet(int i, int j) { 
      if (!isSameSet(i, j)) { 
        numSets--; 
        int x = findSet(i), y = findSet(j);
        // rank is used to keep the tree short
        if (rank[x] > rank[y]){
            p[y] = x;
            full[x]+=full[y];
            total[x]+=total[y];
        }
           
        else { 
            p[x] = y;
            full[y]+=full[x];
            total[y]+=total[x];
            if (rank[x] == rank[y]) 
                rank[y] = rank[y]+1; 
        } 
      } 
    }
  
    public int numDisjointSets() { return numSets; }

    public boolean check(int i){
        int x = findSet(i);
        // one drawer in the set becomes full before the set gets checked
        full[x]+=1;
        //compare used drawer and total drawer in a set, if all drawers in the same set are full then false
        if(full[x]<=total[x]){
            return true;
        }else {
            full[x]-=1;
            return false;
        }
    }
  }

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
