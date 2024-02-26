package baekjun.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pipe_17070 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int [][] arr = new int[N][N]; // 집 배열

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }



        for (int i = 0; i < arr.length; i++) {
                    int[] inArr = arr[i];
                    for (int j = 0; j < inArr.length; j++) {
                        System.out.print(inArr[j] + " ");
                    }
                    System.out.println();
                }
    }
}
