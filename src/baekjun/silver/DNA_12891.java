package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DNA_12891 {
    static int[] checkDNA = new int[4];
    static int[] arr = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()); // 문자열 길이
        int P = Integer.parseInt(st.nextToken()); // 비밀번호 길이
        String s = br.readLine();
        int ans = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < P; i++) { // 슬라이딩 윈도우 초기화
            addChar(s.charAt(i));
        }
        if (checkDNA()) ans++;

        int start = 0;
        int end = P;
        while (end < S) {
            addChar(s.charAt(end));
            removeChar(s.charAt(start));
            if (checkDNA()) ans++;
            start++;
            end++;
        }
        System.out.println(ans);
    }
    static void addChar(char c) {
        switch (c) {
            case 'A': checkDNA[0]++; break;
            case 'C': checkDNA[1]++; break;
            case 'G': checkDNA[2]++; break;
            case 'T': checkDNA[3]++; break;
        }
    }

    static void removeChar(char c) {
        switch (c) {
            case 'A': checkDNA[0]--; break;
            case 'C': checkDNA[1]--; break;
            case 'G': checkDNA[2]--; break;
            case 'T': checkDNA[3]--; break;
        }
    }

    static boolean checkDNA() {
        for (int i = 0; i < 4; i++) {
            if (checkDNA[i] < arr[i]) {
                return false;
            }
        }
        return true;
    }
}
