package edu.ssafy.im.BOJ.Gold.G4.No3190;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, K, L;
    private static int[][] map;
    private static int[] X;
    private static char[] C;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    private static int ans;
    private static int[][] navigation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = 1;
        }

        L = Integer.parseInt(br.readLine());
        X = new int[L];
        C = new char[L];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            X[i] = Integer.parseInt(st.nextToken());
            C[i] = st.nextToken().charAt(0);
        }

        sol();
        sb.append(ans+1);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void sol() {
        int d = 0, idx = 0;
        ans = 0;
        int headX = 0, headY = 0;
        int tailX = 0, tailY = 0;
        navigation = new int[N][N];
        map[headX][headY] = 2;
        while (true) {
            if (idx != X.length) {
                if (X[idx] == ans) {
                    if(C[idx] == 'L') {
                        d = d - 1 == -1 ? 3 : d - 1;
                    } else if(C[idx] == 'D') {
                        d = d + 1 == 4 ? 0 : d + 1;
                    }
                    idx++;
                }
                navigation[headX][headY] = d;
            }

            int nx = headX + dx[d];
            int ny = headY + dy[d];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) break;
            if (map[nx][ny] == 2) break;

            if (map[nx][ny] != 1) {
                map[tailX][tailY] = 0;
                int ntx = tailX + dx[navigation[tailX][tailY]];
                int nty = tailY + dy[navigation[tailX][tailY]];
                tailX = ntx;
                tailY = nty;
            }

            headX = nx;
            headY = ny;
            map[headX][headY] = 2;
            ans++;
        }
    }
}
