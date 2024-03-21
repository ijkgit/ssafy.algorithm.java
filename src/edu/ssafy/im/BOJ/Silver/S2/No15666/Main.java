package edu.ssafy.im.BOJ.Silver.S2.No15666;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] sel, A;
    private static boolean[] v;
    private static StringBuilder sb = new StringBuilder();
    private static HashSet<StringBuilder> ans = new HashSet<>();

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
        Arrays.sort(A);

        sel = new int[M];
        v = new boolean[10001];
        combination(0);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void combination(int k) {
        if (k == M) {
//            for (int s : sel) sb.append(s).append(" ");
//            sb.append("\n");
            for (int s : sel) System.out.print(s + " ");
            System.out.println();
            return;
        }

        for (int i = 0; i < N; i++) {
            if(!v[A[i]]) {
                sel[k] = A[i];
                combination(k + 1);
                v[A[i]] = true;
                combination(k);
                v[A[i]] = false;
            }
        }
    }
}
