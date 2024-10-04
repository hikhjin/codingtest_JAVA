package baekjun.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Teaching_1062 {
    static int n, k;
    static boolean[] visited;
    static int ans = 0;
    static String[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 단어 개수
        k = Integer.parseInt(st.nextToken()); // 가르칠 수 있는 글자 개수

        if (k < 5) {
            System.out.println(0);
            return;
        } else if (k == 26) {
            System.out.println(n);
            return;
        }

        words = new String[n];

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            word = word.replace("anta", "");
            word = word.replace("tica", "");
            words[i] = word;
        }

        visited = new boolean[26];
        visited['a' - 'a'] = true;
        visited['c' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['n' - 'a'] = true;

        backtracking(0, 0);

        System.out.println(ans);

    }

    public static void backtracking(int idx, int cnt) {
        if (cnt == k - 5) {
            int tmp = 0;
            for (int i = 0; i < n; i++) {
                boolean flag = true;
                for (int j = 0; j < words[i].length(); j++) {
                    if (!visited[words[i].charAt(j) - 'a']) {
                        flag = false;
                        break;
                    }
                }
                if (flag) tmp++;
            }
            ans = Math.max(ans, tmp);
            return;
        }

        for (int i = idx; i < 26; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtracking(i, cnt + 1);
                visited[i] = false;
            }
        }
    }
}
