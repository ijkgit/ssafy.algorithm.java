package edu.ssafy.im.BOJ.Gold.G3.No2638;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[][] map;
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0));

        int[][] cheese = new int[N][M];
        cheese[0][0] = 1;

        int time = 0;
        Queue<Point> q2 = new ArrayDeque<>();
        while(!q.isEmpty()) {
            while(!q.isEmpty()) {
                Point p = q.poll();
                for (int[] d : direction) {
                    int nx = p.x + d[0];
                    int ny = p.y + d[1];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                    if (cheese[nx][ny] == -1) continue;

                    // 공기와 치즈의 변이 닿은 경우 카운트해준다.
                    if (map[nx][ny] == 1) cheese[nx][ny]++;

                    // 공기 방문처리
                    if (map[nx][ny] == 0) {
                        cheese[nx][ny] = -1;
                        q.offer(new Point(nx, ny));
                    }

                    // 2변 이상이 공기와 접촉했을 경우 방문 처리 후 치즈 queue에 넣는다.
                    if (cheese[nx][ny] == 2) {
                        cheese[nx][ny] = -1;
                        q2.offer(new Point(nx, ny));
                    }
                }
            }

            // 탐색을 마친 경우, 한시간이 지난다.
            // 만약 치즈가 존재하지 않는 경우, 탐색은 끝난다.
            if (q2.isEmpty()) break;
            time++;

            // 치즈의 좌표를 탐색 큐에 다시 넣어준다.
            // 치즈가 존재했던 좌표부터 탐색을 다시 시작한다.
            while(!q2.isEmpty()) {
                Point p = q2.poll();
                q.offer(p);
            }
        }

        return time;
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
