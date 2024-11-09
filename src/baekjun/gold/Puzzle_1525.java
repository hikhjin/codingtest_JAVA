package baekjun.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Puzzle_1525 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                String tmp = st.nextToken();
                if (tmp.equals("0")) {
                    sb.append("9");
                } else sb.append(tmp);
            }
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        queue.offer(sb.toString());
        map.put(sb.toString(), 0);

        while (!queue.isEmpty()) {
            String cur = queue.poll();
            int loc = cur.indexOf("9");
            int x = loc / 3;
            int y = loc % 3;

            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                int move = nx * 3 + ny;

                if (nx >= 0 && nx < 3 && ny >= 0 && ny < 3) {
                    StringBuilder next = new StringBuilder(cur);
                    char tmp = next.charAt(move);
                    next.setCharAt(loc, tmp);
                    next.setCharAt(move, '9');
                    String nextStr = next.toString();

                    if (!map.containsKey(nextStr)) {
                        queue.offer(nextStr);
                        map.put(nextStr, map.get(cur) + 1);
                    }
                }
            }
        }
        if (map.containsKey("123456789")) {
            System.out.println(map.get("123456789"));
        } else System.out.println(-1);
    }
}
