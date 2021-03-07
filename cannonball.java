import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
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

public class cannonball {
    public static void main(String[] args){
        Kattio sc = new Kattio(System.in);
        double start_x = sc.getDouble();
        double start_y = sc.getDouble();
        Vertex start = new Vertex(start_x, start_y, 0);
        double end_x = sc.getDouble();
        double end_y = sc.getDouble();
        int n = sc.getInt();
        Vertex end = new Vertex(end_x, end_y, n+1);
        if(n==0) sc.print(Math.sqrt(Math.pow((start_x-end_x),2)+Math.pow((start_y-end_y), 2))/5);
        else{
            ArrayList<Edge> edgelst = new ArrayList<>();
            Vertex[] arr = new Vertex[n+2];
            arr[0] = start;
            arr[n+1] = end;
            for(int i=0; i<n; i++){
                Vertex cannon = new Vertex(sc.getDouble(), sc.getDouble(), i+1);
                arr[i+1] = cannon;
            }
            for(int i=0; i<arr.length-1; i++){
                for(int j=1; j<arr.length; j++){
                    double dist = Math.sqrt(Math.pow((arr[i].x - arr[j].x), 2)+Math.pow((arr[i].y - arr[j].y), 2));
                    double time1 = dist/5;
                    double time2 = 10000000.0;
                    if(i>0){
                        time2 = 2 + Math.abs((dist-50)/5);
                    }
                    edgelst.add(new Edge(arr[i], arr[j], Math.min(time1, time2)));
                }
            }
            // for(int i=0; i<edgelst.size(); i++){
            //     sc.print(edgelst.get(i).from.num + " " + edgelst.get(i).to.num + " " + edgelst.get(i).weight);
            //     sc.print("\n");
            // }
            double[] distarr = new double[n+2];
            for(int i=0; i<distarr.length; i++){
                if(i!=0) distarr[i] = 10000000.0;
                else distarr[i] = 0;
            }
            for(int i=1; i<arr.length-1; i++){
                for(int j=0; j<edgelst.size(); j++){
                    Edge curr = edgelst.get(j);
                    if(distarr[curr.from.num]+curr.weight<distarr[curr.to.num]){
                        distarr[curr.to.num] = distarr[curr.from.num]+curr.weight;
                    }
                }
            }
            sc.print(distarr[n+1]);
        }
        sc.close();
    }
}
class Vertex{
    double x;
    double y;
    int num;
    public Vertex(double x, double y, int num){
        this.x = x;
        this.y = y;
        this.num = num;
    }
}
class Edge{
    Vertex from;
    Vertex to;
    double weight;

    public Edge(Vertex from, Vertex to, double weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}