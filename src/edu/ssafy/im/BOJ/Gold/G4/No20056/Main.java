package edu.ssafy.im.BOJ.Gold.G4.No20056;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int K;
    private static final int[][] direction = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    private static ArrayDeque<FireBall>[][] map, map2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayDeque[N][N];
        map2 = new ArrayDeque[N][N];
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayDeque<>();
                map2[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c].offer(new FireBall(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        for (int k = 0; k < K; k++) {

            // 1. 모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다.
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    while (!map[r][c].isEmpty()) {
                        FireBall fb = map[r][c].poll();
                        int nr = r + direction[fb.d][0] * fb.s;
                        int nc = c + direction[fb.d][1] * fb.s;
                        nr = nr < 0 ? N + (nr % N == 0 ? -N : nr % N) : nr % N;
                        nc = nc < 0 ? N + (nc % N == 0 ? -N : nc % N) : nc % N;
                        map2[nr][nc].offer(fb);
                    }
                }
            }

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map2[r][c].size() == 1) {
                        map[r][c].offer(map2[r][c].poll());
                    }
                    // 2. 이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다.
                    else if (map2[r][c].size() >= 2) {
                        int nm = 0, ns = 0, nd = 0, a = 0, b = 0;
                        int size = map2[r][c].size();
                        // 2-1. 같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
                        while (!map2[r][c].isEmpty()) {
                            FireBall fb = map2[r][c].poll();
                            nm += fb.m;
                            ns += fb.s;
                            if (fb.d % 2 == 0) a++;
                            else b++;
                        }
                        // 2-3-1. 질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋이다.
                        nm = (int) Math.floor((double) nm / 5.0);
                        // 2-3-2. 속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.
                        ns = (int) Math.floor((double) ns / (double) size);

                        // 2-4. 질량이 0인 파이어볼은 소멸되어 없어진다.
                        if (nm == 0) continue;

                        // 2-3-3. 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다.
                        if (a != 0 && b != 0) nd = 1;
                        for (int i = 0; i < 8; i+=2) {
                            // 2-2. 파이어볼은 4개의 파이어볼로 나누어진다.
                            map[r][c].offer(new FireBall(nm, ns, nd + i));
                        }
                    }
                }
            }
        }

        // 마법사 상어가 이동을 K번 명령한 후, 남아있는 파이어볼 질량의 합을 구해보자.
        int ans = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                while (!map[r][c].isEmpty()) {
                    ans += map[r][c].poll().m;
                }
            }
        }
        return ans;
    }

    static class FireBall {
        int m, s, d;

        public FireBall(int m, int s, int d) {
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}