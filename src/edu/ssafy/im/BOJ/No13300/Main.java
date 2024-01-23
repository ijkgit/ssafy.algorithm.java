package edu.ssafy.im.BOJ.No13300;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[2][6];
        for (int i = 0; i < n; i++) {
            input = br.readLine();
            st = new StringTokenizer(input);

            int s = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[s][y-1]++;
        }

        int ans = 0;
        for (int s = 0; s < 2; s++) {
            for (int y = 0; y < 6; y++) {
                ans += Math.ceil((double)arr[s][y]/(double)k);
            }
        }

        sb.append(ans);
        System.out.print(sb);
    }
}
