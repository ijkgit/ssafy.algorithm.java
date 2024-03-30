package edu.ssafy.im.BOJ.Gold.G2.No12015;

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
        int cursor = 1;
        for (int i = 1; i < N; i++) {
            if (dp[cursor - 1] < A[i]) dp[cursor++] = A[i];
            else dp[lowerBound(A[i], 0, cursor - 1)] = A[i];
        }
        return cursor;
    }

    private static int lowerBound(int target, int start, int end) {
        int mid;
        while (start < end) {
            mid = (start + end) >>> 1;
            if (dp[mid] < target) start = mid + 1;
            else end = mid;
        }
        return start;
    }
}