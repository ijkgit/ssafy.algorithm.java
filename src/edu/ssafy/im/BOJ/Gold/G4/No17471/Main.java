package edu.ssafy.im.BOJ.Gold.G4.No17471;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private boolean checkNull;

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    private int n;
    private int[] arr;
    private int[][] graph;
    private int[] sel;
    private int ans = Integer.MAX_VALUE;
    private static int TOTAL = 0;

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            TOTAL += arr[i];
        }

        graph = new int[n][n]; // 중복 입력 처리를 위한 인접 행렬 생성
        checkNull = true;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
//			int from = Integer.parseInt(st.nextToken()) - 1; // zero base
            Integer.parseInt(st.nextToken());
            int from = i;
            while (st.hasMoreTokens()) {
                checkNull = false;
                int to = Integer.parseInt(st.nextToken()) - 1;
                if (to == from) continue;
                graph[from][to] = 1;
                graph[to][from] = 1;
            }
        }

//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(Arrays.toString(graph[i]));
//        }

        sol();
        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void sol() {
        if (!checkNull) {
            for (int i = 1; i < n; i++) {
                sel = new int[i];
                permutation(0, 0);
            }
        }

        ans = ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void permutation(int k, int v) {
        if (k == sel.length) {
//			System.out.println(Arrays.toString(sel));
            dfs(sel[0], 1 << sel[0], 1);
            return;
        }

        for (int i = 0; i < n; i++) {
            if ((v & (1 << i)) == 0) {
                sel[k] = i;
                permutation(k + 1, v |= 1 << i);
            }
        }
    }

    private void setTOTAL() {
        TOTAL = 0;
        for (int i = 0; i < n; i++) {
            TOTAL += arr[i];
        }
        for (int i = 0; i < sel.length; i++) {
            TOTAL -= arr[sel[i]];
        }
    }

    private boolean check() {
        int[] rev = new int[n-sel.length];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int s : sel) {
                if (i == s) flag = false;
            }
            if(flag) {
                rev[idx] = i;
                i++;
            }
        }
        return tmp(rev[0], 1 << rev[0], 1);
    }

    private boolean tmp(int i, int v, int depth) {
        if (depth == sel.length) {
            return true;
        }
        for (int j = 0; j < sel.length; j++) {
            if (graph[i][sel[j]] != 0) {
                if ((v & (1 << sel[j])) == 0) {
                    tmp(sel[j], v |= 1 << sel[j], depth + 1);
                }
            }
        }
        return false;
    }

    private void dfs(int i, int v, int depth) {
        if (depth == sel.length) {
            if (!check()) return;
            setTOTAL();
            int sum = 0;
            for (int j = 0; j < sel.length; j++) {
                sum += arr[sel[j]];
            }
            ans = Math.min(ans, Math.abs(TOTAL - sum));
            return;
        }

        for (int j = 0; j < sel.length; j++) {
            if (graph[i][sel[j]] != 0) {
                if ((v & (1 << sel[j])) == 0) {
                    dfs(sel[j], v |= 1 << sel[j], depth + 1);
                }
            }
        }
    }
}
