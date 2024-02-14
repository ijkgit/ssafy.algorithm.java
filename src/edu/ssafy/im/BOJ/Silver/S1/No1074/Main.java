package edu.ssafy.im.BOJ.Silver.S1.No1074;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private int ans = 0;
    private int r, c;
    private StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        new Main().sol();
    }

    private void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        division(n, 0, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void division(int n, int x, int y) {
        if (x == r && y == c) {
            System.out.println(ans);
            return;
        }

        if (n == 1) {
            ans++;
            return;
        }

        if (x > r || y > c || x + n < r || y + n < c) {
            ans += n * n;
            return;
        }

        n /= 2;
        division(n, x, y);
        division(n, x, y + n);
        division(n, x + n, y);
        division(n, x + n, y + n);
    }
}
