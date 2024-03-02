package programmers.lv2;

class Solution {
    static boolean[] visited;
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                answer += 1; // 네트워크 하나마다 1 카운트
                DFS(i, computers);
            }
        }
        return answer;
    }

    static void DFS(int x, int[][] computers) {
        visited[x] = true; // 방문 표시

        int n = visited.length;

        for (int i = 0; i < n; i++) {
            if (!visited[i] && computers[x][i] == 1) { // 방문하지 않았는데 연결되어 있을 때
                visited[i] = true;
                DFS(i, computers);
            }
        }
    }
}
