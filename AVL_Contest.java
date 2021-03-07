import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.util.*;

public class AVL_Contest{
    public static void main(String[] args){
        Kattio sc = new Kattio(System.in);
        int n = sc.getInt();
        int m = sc.getInt();
        AVL T = new AVL();
        Node initial_team1 = new Node(1, 0, 0);
        T.insert(initial_team1);
        int[] team = new int[2*(n+1)];
        for(int i=0; i<m; i++){
            int t = sc.getInt();
            int p = sc.getInt();
            Node node = new Node(t, team[2*t], team[2*t+1]);
            //delete repeated node
            T.delete(node);
            //update score & penalty in arr
            team[2*t]++;
            team[2*t+1]+=p;
            //insert updated node
            Node new_node = new Node(t, team[2*t], team[2*t+1]);
            T.insert(new_node);
            //check team1 rank
            Node team1 = new Node(1, team[2], team[3]);
            int rank = T.rank(T.root, team1);
            sc.print(rank+"\n");
        }
        sc.close();
    }
}

class Node implements Comparable<Node>{
    int t;
    int s;
    int p;

    public Node(int t, int s, int p){
        this.t = t;
        this.s = s;
        this.p = p;
    }

    public int compareTo(Node other){
        if(this.s == other.s){
            if(this.p == other.p){
                return other.t - this.t;
            }else return other.p - this.p;
        }
        else return  this.s - other.s;
    }
}

class AVLVertex{
    AVLVertex(Node v){
        key = v;
        left = right = null;
        height = 0;
        size = 1;
    }
    public AVLVertex left, right;
    public Node key;
    public int height;
    public int size;
}

class AVL{
    public AVLVertex root;
    public AVL(){root = null;}
    public int height(AVLVertex T){
        if(T==null) return -1;
        else return T.height;
    }
    public int updateheight(AVLVertex T){
        return Math.max(height(T.left),height(T.right))+1;
    }
    public int updatesize(AVLVertex T){
        return size(T.left)+size(T.right)+1;
    }
    public int size(AVLVertex T){
        if(T==null) return 0;
        else return T.size;
    }
    public void inorder() { 
        inorder(root);
        System.out.println();
    }
    
    public void inorder(AVLVertex T) {
        if (T == null) return;
        inorder(T.left);                               
        System.out.print(T.key);                    
        inorder(T.right);                             
    }

    public Node search(Node v){
        AVLVertex res = search(root, v);
        if(res==null) return null;
        else return res.key;
    }

    public AVLVertex search(AVLVertex T, Node v){
        if(T==null) return null;
        else if(T.key.compareTo(v)==0) return T;
        else if(T.key.compareTo(v)<0) return search(T.right,v);
        else return search(T.left, v);
    }


    public Node findMin(AVLVertex T){
        if(T.left==null) return T.key;
        else return findMin(T.left);
    }

    public Node findMax(AVLVertex T){
        if(T.right==null) return T.key;
        else return findMax(T.right);
    }

    public void insert(Node v){root = insert(root,v);}

    public AVLVertex insert(AVLVertex T, Node v){
        if (T == null) return new AVLVertex(v);          // insertion point is found

        if (T.key.compareTo(v) <0) {                                      // search to the right
          T.right = insert(T.right, v);
          T.right.height = updateheight(T.right);
          T.right.size = updatesize(T.right);
        }
        else {                                                 // search to the left
          T.left = insert(T.left, v);
          T.left.height = updateheight(T.left);
          T.left.size = updatesize(T.left);
        }
        T.height = updateheight(T);
        T.size = updatesize(T);
        T = rebalance(T);
        return T; 
    }

    public void delete(Node v){root = delete(root,v);}

    public AVLVertex delete(AVLVertex T, Node v){
        if(T==null) return T;
        if(T.key.compareTo(v)<0){
            T.right = delete(T.right,v);
        }
        else if(T.key.compareTo(v)>0){
            T.left = delete(T.left, v);
        }
        else{
            if(T.left==null && T.right==null) 
                T=null;
            else if (T.left == null && T.right != null) {   // only one child at right        
                T = T.right;                                                 // bypass T        
              }
            else if (T.left != null && T.right == null) {    // only one child at left        
                T = T.left;  
            }
            else{
                Node successorV = findMin(T.right);
                T.key = successorV;
                T.right = delete(T.right, successorV);
            }
        }
        if(T!=null){
            T.height = updateheight(T);
            T.size = updatesize(T);
        }
        T = rebalance(T);
        return T;
    }

    public AVLVertex rebalance(AVLVertex T){
        if(bf(T)==2){
            if(0<=bf(T.left) && bf(T.left)<=1){
                T = rightRotate(T);
            }
            else if(bf(T.left)==-1){
                T.left = leftRotate(T.left);
                T = rightRotate(T);
            }
        }
        else if(bf(T)==-2){
            if(-1<=bf(T.right) && bf(T.right)<=0){
                T = leftRotate(T);
            }
            else if(bf(T.right)==1){
                T.right = rightRotate(T.right);
                T = leftRotate(T);
            }
        }
        return T;
    }

    public int bf(AVLVertex T){
        if(T==null) return 0;
        return height(T.left) - height(T.right);
    }

    public AVLVertex leftRotate(AVLVertex T){
        AVLVertex w = T.right;
        T.right = w.left;
        w.left = T;
        T.height = updateheight(T);
        T.size = updatesize(T);
        w.height = updateheight(w);
        w.size = updatesize(w);
        return w;
    }

    public AVLVertex rightRotate(AVLVertex T){
        AVLVertex w = T.left;
        T.left = w.right;
        w.right = T;
        T.height = updateheight(T);
        T.size = updatesize(T);
        w.height = updateheight(w);
        w.size = updatesize(w);
        return w;
    }

    public int rank(AVLVertex T, Node v){
        int _rank = 0;
        if(T==null) return 0;
        if(T.key.compareTo(v)==0) _rank = size(T.right)+1;
        else if(T.key.compareTo(v)>0){
            _rank = rank(T.left,v)+size(T.right)+1;;
        }
        else if(T.key.compareTo(v)<0){
            _rank = rank(T.right, v);
        }
        return _rank;
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
