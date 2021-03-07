
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class Teque{
    public static HashMap<Integer, Integer> first = new HashMap<>();
    public static HashMap<Integer, Integer> second = new HashMap<>();
    //keys for hashmap, if adds to front of first, then firstmin -1
    //if adds to back of first, firstmax +1
    //same for second
    static int firstmin = 0;
    static int firstmax = 0;
    static int secondmin = 0;
    static int secondmax = 0;
    public static void main(String[] args) throws IOException{
        Kattio sc = new Kattio(System.in);
        int n = sc.getInt();

        for(int i=0; i<n; i++){
            String command = sc.getWord();
            int number = sc.getInt();
            if (command.equals("push_back")){
                push_back(number);
            }
            else if(command.equals("push_front")){
                push_front(number);
            }
            else if(command.equals("push_middle")){
                push_middle(number);
            }else if(command.equals("get")){
                int num = get(number);
                sc.print(num);
                sc.print("\n");
            }
        }
        sc.close();
    }
    //need to balance the two hashmap so that push_middle will be push to key=(size+1)/2
    public static void balance(){
        //if second larger then remove first item from second and add to the back to first
        if(first.size()+1<second.size()){
            first.put(firstmax, second.get(secondmin));
            firstmax+=1;
            secondmin+=1;
            second.remove(secondmin-1);
        }
        //else if first larger then remove last of first and add to front of back
        else if(first.size()>second.size()){
            firstmax-=1;
            secondmin-=1;
            second.put(secondmin, first.get(firstmax));
            first.remove(firstmax);
        }
    }

    public static void push_front(int x){
        firstmin-=1;
        first.put(firstmin, x);
        balance();
    }

    public static void push_back(int x){
        secondmax+=1;
        second.put(secondmax-1, x);
        balance();
    }
    
    public static void push_middle(int x){
        //if first and second same size then add to the front of second
        if(first.size()==second.size()){
            secondmin-=1;
            second.put(secondmin, x);
        }
        //
        else{
            firstmax+=1;
            first.put(firstmax-1, second.get(secondmin));
            second.put(secondmin, x);
        }
        balance();
    }
    
    public static int get(int x){
        int diff = firstmax - firstmin;
        if(x < diff){
            return first.get(firstmin + x);
        }
        else{
            x -= diff;
            return second.get(secondmin + x);
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
