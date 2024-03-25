package baekjun.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Searching_1543 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine(); // 문서
        String w = br.readLine(); // 검색할 단어
        int cnt = 0;

        while (s.contains(w)) { // s안에 w가 있다면
            int idx = s.indexOf(w);
            s = s.substring(idx+w.length());
            cnt++;
        }
        System.out.println(cnt);
    }

}
