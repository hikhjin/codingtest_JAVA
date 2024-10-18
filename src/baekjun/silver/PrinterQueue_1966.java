package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PrinterQueue_1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 문서의 개수
            int M = Integer.parseInt(st.nextToken()); // 몇 번째 문서
            st = new StringTokenizer(br.readLine());
            Queue<Doc> queue = new LinkedList<>();
            for (int j = 0; j < N; j++) {
                queue.offer(new Doc(Integer.parseInt(st.nextToken()), j));
            }

            int cnt = 0;
            while (!queue.isEmpty()) {
                Doc doc = queue.poll();
                boolean flag = true;

                for (Doc q : queue) {
                    if (doc.importance < q.importance) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    cnt++;
                    if (doc.idx == M) {
                        sb.append(cnt).append("\n");
                    }
                } else queue.offer(doc);
            }
        }
        System.out.println(sb);
    }
}

class Doc {
    int importance;
    int idx;

    public Doc(int importance, int idx) {
        this.importance = importance;
        this.idx = idx;
    }
}
