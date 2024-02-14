package edu.ssafy.im.BOJ.Silver.S1.No1074;

import java.io.*;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) throws IOException {
        new Main2().sol();
    }

    private void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int ans = 0;
        while (n != 0) {
            if (c / n < 1) {
                if (r / n >= 1) {
                    ans += 2 * n * n;
                }
            } else {
                if (r / n < 1) {
                    ans += n * n;
                } else {
                    ans += 3 * n * n;
                }
            }
            c %= n;
            r %= n;
            n /= 2;
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
