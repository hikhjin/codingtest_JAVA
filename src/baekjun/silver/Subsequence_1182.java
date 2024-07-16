package baekjun.silver;

import java.io.*;
import java.util.StringTokenizer;

public class Subsequence_1182 {
    static int[] arr;
    static int ans = 0;
    static int N, S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, 0);

        if (S == 0) { // S == 0 이어서 아무것도 더하지 않았을 경우(공집합)
            ans--;
        }

        System.out.println(ans);
    }

    static void solve(int sum, int idx) {
//        if (sum == S) {
//            ans++;
//            return;
//        }
//        if (idx == N) { // sum이 S와 같지 않은데 남은 정수를 다 더했으면 return
//            return;
//        }

//        for (int i = 0; i < arr.length; i++) {
//            if (!check[i]) {
//                sum += arr[i];
//                check[i] = true;
//                solve(sum, idx+1);
//                check[i] = false;
//            }
//        }

        if (idx == N) {
            if (sum == S) {
                ans++;
            }
            return;
        }

        solve(sum, idx + 1); // 자기 자신 포함
        solve(sum + arr[idx], idx + 1); // 자기 자신 포함하지 않을 경우
    }
}
