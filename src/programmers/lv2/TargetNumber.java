package programmers.lv2;

class Solution_TargetNumber {

    static int answer = 0;

    public int solution(int[] numbers, int target) {
        DFS(numbers, target, 0, 0);
        return answer;
    }

    static void DFS(int[] numbers, int target, int sum, int idx) {
        if (idx == numbers.length) { // 마지막 인덱스이고
            if (sum == target) { // 총합이 target과 같으면 answer 증가
                answer++;
            }
        } else {
            DFS(numbers, target, sum + numbers[idx], idx+1);

            DFS(numbers, target, sum - numbers[idx], idx+1);
        }

    }
}
