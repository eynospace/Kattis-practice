import java.util.LinkedList;
import java.util.Queue;
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

public class Islands {
    public static void main(String[] args){
        Kattio sc = new Kattio(System.in);
        int row = sc.getInt();
        int col = sc.getInt();
        boolean[][] visited = new boolean[row][col];
        String[][] grid = new String[row][col];
        for(int i=0; i<row; i++){
            String[] arr = sc.getWord().split("");
            for(int j=0; j<arr.length; j++){
                grid[i][j]=arr[j];
                visited[i][j]=false;
            }
        }
        int res = 0;
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                String curr_cell = grid[i][j];
                boolean curr_visited = visited[i][j];
                if(curr_cell.equals("L") && !curr_visited){
                    res+=1;
                    DFS(i, j, grid, visited, row, col);
                }
            }
        }
        sc.print(res);
        sc.close();
    }
    public static void DFS(int i, int j, String[][] grid, boolean[][] visited, int row, int col){
        visited[i][j]=true;
        int up=i+1;
        if(up<row){
            if(grid[up][j].equals("C")||grid[up][j].equals("L")){
                if(!visited[up][j]) DFS(up, j, grid, visited, row, col);
            }
        }
        int down=i-1;
        if(0<=down && down<row){
            if(grid[down][j].equals("C")||grid[down][j].equals("L")){
                if(!visited[down][j]) DFS(down, j, grid, visited, row, col);
            }
        }
        int left=j-1;
        if(0<=left && left<col){
            if(grid[i][left].equals("C")||grid[i][left].equals("L")){
                if(!visited[i][left]) DFS(i, left, grid, visited, row, col);
            }
        }
        int right=j+1;
        if(0<=right && right<col){
            if(grid[i][right].equals("C")||grid[i][right].equals("L")){
                if(!visited[i][right]) DFS(i, right, grid, visited, row, col);
            }
        }
    }
}
