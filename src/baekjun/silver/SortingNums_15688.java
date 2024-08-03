package baekjun.silver;

import java.io.*;

public class SortingNums_15688 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[2000001];

        for (int i = 0; i < n; i++) {
            arr[Integer.parseInt(br.readLine()) + 1000000]++;
        }

        for (int i = 0; i < arr.length; i++) {
            while (arr[i] != 0) {
                sb.append(i-1000000).append("\n");
                arr[i]--;
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
