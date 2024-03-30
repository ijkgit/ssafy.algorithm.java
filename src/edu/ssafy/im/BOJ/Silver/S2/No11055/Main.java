package edu.ssafy.im.BOJ.Silver.S2.No11055;

import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] A, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        dp = new int[N];
        dp[0] = A[0];
        for (int i = 1; i < N; i++) {
            dp[i] = A[i];
            for (int j=0; j < i; j++) {
                if (A[i] > A[j]) dp[i] = Math.max(A[i] + dp[j], dp[i]);
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int d : dp) ans = Math.max(ans, d);
        return ans;
    }
}