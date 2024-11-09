package baekjun.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Rain_14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int area = 0;
        int[] arr = new int[W];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < W; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(area);
    }
}
