package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MeetingRooms2_1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[1] == o2[1]) return o1[0] - o2[0]; // 끝나는 시간 같으면 시작 시간으로 오름차순 정렬
            return o1[1] - o2[1];
        });

        int cnt = 0;
        int end = 0;
        for (int[] meeting : arr) {
            if (meeting[0] >= end) {
                end = meeting[1];
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
