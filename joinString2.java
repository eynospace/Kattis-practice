import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

//ZHU ENYAO A0206188B

public class joinString2{
    public static void main(String[] agrs) throws IOException {
        Kattio br = new Kattio(System.in);
        int n = br.getInt();
        ArrayList<ListNode> lst = new ArrayList<ListNode>();
        for(int i=0; i<n; i++){
            ListNode str = new ListNode(br.getWord(), null, null);
            lst.add(str);
            lst.get(i).setTail(str);
        }
        int a = 0;
        for(int l=0; l<n-1; l++){
            a = br.getInt()-1;
            int b = br.getInt()-1;
            ListNode anode = lst.get(a);
            ListNode bnode = lst.get(b);
            anode.tail.setNext(bnode);
            anode.setTail(bnode.tail);
            
        }
        ListNode node = lst.get(a);
        br.print(node.item);
        while(node.next!=null){
            node = node.next;
            br.print(node.item);
        }
        br.close();
    }
}

class ListNode {

	public String item;
    public ListNode next;
    public ListNode tail;


	public ListNode(String val, ListNode n, ListNode t) { 
		this.item = val; 
        this.next = n; 
        this.tail = t;
	}

    public void setItem(String val) { item = val; }


    public void setNext(ListNode n) { next = n; }

    public void setTail(ListNode t) {tail = t;}
    
    public boolean hasNext() {
        if( next == null){
            return false;
        }else{return true;}
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