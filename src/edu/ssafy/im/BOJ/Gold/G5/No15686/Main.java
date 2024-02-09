package edu.ssafy.im.BOJ.Gold.G5.No15686;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private int[] sel;
    private int m;
    private int ans = Integer.MAX_VALUE;
    List<Point> startList, targetList;

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        sel = new int[m];
        startList = new ArrayList<>();
        targetList = new ArrayList<>();
        for (int x = 0; x < n; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < n; y++) {
                int location = Integer.parseInt(st.nextToken());
                if (location == 1) {
                    startList.add(new Point(x, y));
                } else if (location == 2) {
                    targetList.add(new Point(x, y));
                }
            }
        }
        sol(0, 0);

        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void sol(int i, int k) {
        // basis part
        if (k == sel.length) {
            int distanceSum = 0;
            for (int s = 0; s < startList.size(); s++) {
                int sx = startList.get(s).x;
                int sy = startList.get(s).y;

                int shortestDistance = Integer.MAX_VALUE;
                for (int targetIndex : sel) {
                    int tx = targetList.get(targetIndex).x;
                    int ty = targetList.get(targetIndex).y;

                    int distance = Math.abs(sx - tx) + Math.abs(sy - ty);
                    shortestDistance = Math.min(shortestDistance, distance);
                }

                distanceSum += shortestDistance;
            }
            ans = Math.min(ans, distanceSum);
            return;
        }
        if (i == targetList.size()) {
            return;
        }

        // inductive part
        sel[k] = i;
        sol(i + 1, k + 1);
        sol(i + 1, k);
    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
