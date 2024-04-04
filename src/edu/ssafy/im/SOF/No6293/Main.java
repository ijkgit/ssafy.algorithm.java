package edu.ssafy.im.SOF.No6293;

import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] A;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        for (int i=0; i<N; i++) {
            for (int j=0; j<i; j++) {
                if (A[i] > A[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int ans = 0;
        for (int i=0; i<N; i++) {
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }
}
