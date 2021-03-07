import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.util.*;

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

public class TenKinds {
    public static void main(String[] args){
        Kattio sc = new Kattio(System.in);
        int row = sc.getInt();
        int col = sc.getInt();
        int[][] grid = new int[row][col];
        int[][] visited = new int[row][col];
        Queue<int[]> q = new LinkedList<int[]>();
        for(int i=0; i<row; i++){
            String [] arr = sc.getWord().split("");
            for(int j=0; j<arr.length; j++){
                grid[i][j] = Integer.parseInt(arr[j]);
                visited[i][j] = 0;
            }
        }
        visited = BFS(q, grid, visited, row, col);
        int query = sc.getInt();
        for(int i=0; i<query; i++){
            int r1 = sc.getInt()-1;
            int c1 = sc.getInt()-1;
            int r2 = sc.getInt()-1;
            int c2 = sc.getInt()-1;
            int a = grid[r1][c1];
            if(visited[r1][c1]==visited[r2][c2]){
                if(a==0) sc.print("binary"+"\n");
                else sc.print("decimal"+"\n");
            }
            else sc.print("neither"+"\n");
        }
        sc.close();
    }
    public static int[][] BFS(Queue<int[]> q, int[][] grid, int[][] visited, int row, int col){
        int grp = 1;
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(visited[i][j]==0){
                    visited[i][j]=grp;
                    int a =grid[i][j];
                    q.offer(new int[]{i,j});
                    while(!q.isEmpty()){
                        int[] coor = q.poll();
                        int up = coor[0]+1;
                        int down = coor[0]-1;
                        int left = coor[1]-1;
                        int right = coor[1]+1;
                        if(up<row){
                            if(grid[up][coor[1]]==a && visited[up][coor[1]]==0){
                                int[] curr = new int[] {up, coor[1]};
                                q.offer(curr);
                                visited[curr[0]][curr[1]]=grp;
                            }
                        }
                        if(0<=down && down<row){
                            if(grid[down][coor[1]]==a && visited[down][coor[1]]==0){
                                int[] curr = new int[] {down, coor[1]};
                                q.offer(curr);
                                visited[curr[0]][curr[1]]=grp;
                            }
                        }
                        if(0<=left && left<col){
                            if(grid[coor[0]][left]==a && visited[coor[0]][left]==0){
                                int[] curr = new int[] {coor[0], left};
                                q.offer(curr);
                                visited[curr[0]][curr[1]]=grp;
                            }
                        }
                        if(0<=right && right<col){
                            if(grid[coor[0]][right]==a && visited[coor[0]][right]==0){
                                int[] curr = new int[] {coor[0], right};
                                q.offer(curr);
                                visited[curr[0]][curr[1]]=grp;
                            }
                        }
                    }
                    grp+=1;
                }
            }
        }
        return visited;
    }
}