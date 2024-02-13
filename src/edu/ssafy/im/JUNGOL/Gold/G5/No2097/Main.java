package edu.ssafy.im.JUNGOL.Gold.G5.No2097;

import java.io.*;
import java.util.*;

public class Main {
    private int[][] graph;
    private int m;
    private int ans = Integer.MAX_VALUE;
    private List<Integer> sel = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Main().sol();
    }

    private void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][n];
        for (int i = 0; i < graph.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < graph.length; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void dfs(int i, int v) {
        if(i == m) {
            int sum = 0;
            for(int s: sel) {
                sum += s;
            }
            ans = Math.min(ans, sum);
            return;
        }

        for (int j = 0; j < graph.length; j++) {
            if((v & (1 << j)) == 0 && i != j) {
                System.out.println(sel.toString());
                sel.add(j);
                dfs(i+1, v | 1 << j);
            }
        }
    }
}
