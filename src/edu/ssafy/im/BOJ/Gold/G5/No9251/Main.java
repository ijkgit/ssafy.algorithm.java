package edu.ssafy.im.BOJ.Gold.G5.No9251;

import java.io.*;

public class Main {
    private static char[] b;
    private static char[] a;
    private static int[][] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        a = br.readLine().toCharArray();
        b = br.readLine().toCharArray();
        ans = new int[a.length+1][b.length+1];

        sb.append(LCS());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int LCS() {
        for (int i = 1; i < ans.length; i++) {
            for (int j = 1; j < ans[0].length; j++) {
                if (a[i-1] == b[j-1]) ans[i][j] = ans[i-1][j-1] + 1;
                else ans[i][j] = Math.max(ans[i-1][j], ans[i][j-1]);
            }
        }

        return ans[a.length][b.length];
    }
}
