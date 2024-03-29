package edu.ssafy.im.BOJ.Platinum.No1786;

import java.io.*;

public class Main {
    private static char[] T, P;
    private static int[] F;
    private static StringBuilder ans = new StringBuilder();
    private static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        T = br.readLine().toCharArray();
        P = br.readLine().toCharArray();

        KMP();

        sb.append(cnt).append("\n");
        sb.append(ans.toString());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void KMP() {
        getFailureFunction();
        search();
    }

    private static void getFailureFunction() {
        F = new int[P.length];
        int i = 1, j = 0;

        while (i < P.length) {
            if (P[i] == P[j]) F[i++] = ++j;
            else if (j > 0) j = F[j - 1];
            else i++;
        }
    }

    private static void search() {
        int i = 0, j = 0;
        while (i < T.length) {
            if (T[i] != P[j]) {
                if (j > 0) j = F[j - 1];
                else i++;
            } else {
                if (j == P.length - 1) {
                    ans.append(i - j + 1).append(" ");
                    cnt++;
                    j = F[j];
                } else j++;
                i++;
            }
        }
    }
}