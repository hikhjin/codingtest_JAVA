package baekjun.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Avoid_18428 {
    static char[][] board;
    static int N;
    static List<int[]> teachers = new LinkedList<>();
    static boolean ans = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = st.nextToken().charAt(0);
                if (board[i][j] == 'T') {
                    teachers.add(new int[]{i, j});
                }
            }
        }
        obstacle(0); // 장애물 세우기

        if (ans) System.out.println("YES");
        else System.out.println("NO");
    }

    static void obstacle(int index) {
        if (index == 3) {
            if (bfs()) {
                ans = true;
                return;
            }
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 'X') {
                    board[i][j] = 'O';
                    obstacle(index + 1);
                    board[i][j] = 'X';
                }
            }
        }
    }

    static boolean bfs() {
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Queue<int[]> queue = new LinkedList<>();

        for (int[] t : teachers) {
            queue.offer(t);
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] dir : dirs) {
                int nx = cur[0];
                int ny = cur[1];

                while (true) {
                    nx += dir[0];
                    ny += dir[1];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) break;
                    if (board[nx][ny] == 'S') return false;
                    if (board[nx][ny] == 'O') break;
                }
            }
        }
        return true;
    }
}
