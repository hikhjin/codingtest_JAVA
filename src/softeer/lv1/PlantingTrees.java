package softeer.lv1;

import java.util.*;
import java.io.*;

public class PlantingTrees {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] mulArr = new int[(n*(n-1))/2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int idx = 0;

        for (int i = 0; i < n; i++) {
            int check = i;
            while (check < n-1) {
                int mul = arr[i] * arr[check+1];
                mulArr[idx] = mul;
                check++;
                idx++;
            }
        }

        Arrays.sort(mulArr);
        System.out.println(mulArr[mulArr.length-1]);

    }
}
