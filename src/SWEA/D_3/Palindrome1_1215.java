package SWEA.D_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Palindrome1_1215 {
    static char[][] arr;
    static int len;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 10; i++) {
            sb.append("#").append(i).append(" ");
            len = Integer.parseInt(br.readLine());
            arr = new char[8][8];
            for (int j = 0; j < 8; j++) {
                String s = br.readLine();
                for (int k = 0; k < 8; k++) {
                    arr[j][k] = s.charAt(k);
                }
            }

            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    makeStr(j, k);
                }
            }
            sb.append(cnt);
            cnt = 0;
            if (i != 10) sb.append("\n");
        }
        System.out.println(sb);
    }

    static void makeStr(int x, int y) {
        String str = "";
        for (int i = x; i < len+x; i++) {
            if (i > 7) break;
            str += arr[i][y];
            if (i == len+x-1) {
                if (isPalindrome(str)) cnt++;
            }
        }

        str = "";
        for (int i = y; i < len+y; i++) {
            if (i > 7) break;
            str += arr[x][i];
            if (i == len+y-1) {
                if (isPalindrome(str)) cnt++;
            }
        }
    }

    static boolean isPalindrome(String str) {
        for (int i = 0; i < len / 2; i++) {
            if (str.charAt(i) != str.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
