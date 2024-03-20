package edu.ssafy.im.BOJ.Silver.S2.No15666;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] sel, A;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        sel = new int[M];
        combination(0, 0);
    }

    private static void combination(int k, int v) {
        if (k == M) {
            for (int s : sel) sb.append(s).append(" ");
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            `sel[k] = A[i];
        }
    }
}
