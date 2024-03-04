package programmers.lv3;

import java.util.*;

class Solution {

    static int[] visited;

    public int solution(String begin, String target, String[] words) {
        visited = new int[words.length];

        int answer = 0;
        int cnt = 0;
        for (int i = 0; i < words.length; i++) {
            if (target.equals(words[i])) {
                cnt++;
            }
        }
        if (cnt == 0) { // words 안에 target이 없는 경우
            return answer;
        }

        answer = BFS(begin, target, words);
        return answer;
    }

    static boolean check(String start, String word) { // 한 글자만 다른지 확인
        int cnt = 0;
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) != word.charAt(i)) { // 글자가 다를 때마다 cnt 증가
                cnt++;
            }
        }
        if (cnt == 1) {
            return true; // 한 글자만 다르면 true
        } else {
            return false;
        }
    }

    static int cntIndex(String word, String[] words) { // visited 인덱스 구하는 메서드
        for (int i = 0; i < words.length; i++) {
            if (word.equals(words[i])) {
                return i;
            }
        } return 0;
    }

    static int BFS(String begin, String target, String[] words) {
        Queue<String> q = new LinkedList<>();
        q.offer(begin); // 큐에 begin 넣기

        visited[cntIndex(begin, words)] = 1; // 시작점 visited 1로 설정

        while(!q.isEmpty()) {
            String now = q.poll();
            for (int i = 0; i < words.length; i++) {
                String next;
                if (check(now, words[i]) == true) {
                    next = words[i];

                    if(next.equals(target)) { // next와 target과 같으면 답 리턴
                        System.out.println(visited[cntIndex(now, words)]);
                        return visited[cntIndex(now, words)];
                    }
                    q.offer(next);
                    visited[i] = visited[cntIndex(now, words)] + 1;
                }
            }
        } return 0;
    }
}

public class words {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        s.solution(	"hit", "cog", words);
    }
}