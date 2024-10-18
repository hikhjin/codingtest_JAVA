package baekjun.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Liar_1043 {
    static int[] node;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 사람 수
        int M = Integer.parseInt(st.nextToken()); // 파티 수

        node = new int[N+1];
        for (int i = 1; i <= N; i++) {
            node[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        if (t == 0) {
            System.out.println(M);
            return;
        }

        int[] truth = new int[t+1];
        for (int i = 1; i <= t; i++) {
            truth[i] = Integer.parseInt(st.nextToken());
        }

        int[] partyPeople = new int[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken());
            partyPeople[i] = prev;
            for (int j = 0; j < p-1; j++) {
                int cur = Integer.parseInt(st.nextToken());
                union(prev, cur);
            }
        }

        int cnt = 0; // 파티 카운트
        for (int i = 0; i < M; i++) {
            boolean flag = true;
            for (int j = 1; j <= t; j++) {
                if (find(node[truth[j]]) == find(node[partyPeople[i]])) {
                    flag = false;
                    break;
                }
            }
            if (flag) cnt++;
        }

        System.out.println(cnt);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            node[b] = a;
        }
    }

    static int find(int a) {
        if (a == node[a]) {
            return a;
        } else return find(node[a]);
    }
}
