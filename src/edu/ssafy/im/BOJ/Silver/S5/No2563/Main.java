package edu.ssafy.im.BOJ.Silver.S5.No2563;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        new Main().sol();
    }

    private void sol() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        boolean[][] graph = new boolean[100][100];
        for (int k = 0; k < n; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for (int i = x; i < x + 10; i++) {
                for (int j = y; j < y + 10; j++) {
                    graph[i][j] = true;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (graph[i][j]) ans++;
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}