import java.util.ArrayList;
import java.util.Scanner;

public class weakVertices1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        while(n!=-1){
            int[][] adjMatrix = new int[n][n];
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    adjMatrix[i][j] = sc.nextInt();
                }
                sc.nextLine();
            }
            boolean[] res = new boolean[n];
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(adjMatrix[i][j]==1){
                        for(int k=0; k<n; k++){
                            if(adjMatrix[i][k]==1 && adjMatrix[j][k]==1){
                                res[i]=true;
                                break;
                            }
                        }
                    }
                }
            }

            for(int a=0; a<n; a++){
                if(res[a]==false){
                    System.out.print(a + " ");
                }
            }
            n = Integer.parseInt(sc.nextLine());
            System.out.print("\n");
        }
    }
}
