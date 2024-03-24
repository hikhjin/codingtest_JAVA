package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class palindrome_1254 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int cnt = 0;

        for (int i = 0; i < S.length(); i++) {
            if (isPalindrome(S.substring(i))) { // 앞에서부터 하나씩 잘라서 펠린드롬인지 확인
                cnt = i; // 펠린드롬일 경우 추가로 붙여야 하는 문자 개수
                break;
            }
        }

        System.out.println(S.length() + cnt); // 원래 단어에 추가 문자 개수 더하기
    }
    static boolean isPalindrome(String s) { // 펠린드롬인지 확인
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(s.length()-i-1)) {
                return false;
            }
        }
        return true;
    }
}
