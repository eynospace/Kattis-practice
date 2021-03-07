import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.io.OutputStream;

public class assignWork{
    public static void main(String[] args){
        Kattio sc = new Kattio(System.in);
        int n = sc.getInt();
        int t = sc.getInt();
        ArrayList<Researcher> r = new ArrayList<>();
        for(int i=0; i<n; i++){
            int a = sc.getInt();
            int s = sc.getInt();
            Researcher person = new Researcher(a,s);
            r.add(person);
        }
        Collections.sort(r);
        int counter = 0;
        PriorityQueue<Integer> q1 = new PriorityQueue<Integer>();
        PriorityQueue<Integer> q2 = new PriorityQueue<Integer>();
        for(int i=0; i<n; i++){
            int a=q1.size();
            for(int j=0; j<a; j++){
                if(q1.peek()<=r.get(i).arr){
                    int update = q1.poll()+t;
                    q2.offer(update);
                }
                else break;
            }
            int b=q2.size();
            for(int j=0; j<b; j++){
                if(q2.peek()<r.get(i).arr){
                    q2.poll();
                }
                else break;
            }
            if(q2.poll()!=null){
                counter++;
            }
            int value = r.get(i).arr + r.get(i).stay;
            q1.offer(value);

        }
        sc.print(counter);
        sc.close();
    }
}

class Researcher implements Comparable<Researcher>{
    int arr;
    int stay;
    public Researcher(int arr, int stay){
        this.arr = arr;
        this.stay = stay;
    }
    public int compareTo(Researcher other){
        return this.arr - other.arr;
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
