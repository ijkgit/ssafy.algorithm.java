package edu.ssafy.im.BOJ.Gold.G4.No19942;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, mp, mf, ms, mv, ans = Integer.MAX_VALUE;
    private static int[] p, f, s, v, c;
    private static boolean[] ansString;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        mp = Integer.parseInt(st.nextToken());
        mf = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        mv = Integer.parseInt(st.nextToken());

        p = new int[N];
        f = new int[N];
        s = new int[N];
        v = new int[N];
        c = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            p[i] = Integer.parseInt(st.nextToken());
            f[i] = Integer.parseInt(st.nextToken());
            s[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] visited = new boolean[N];
        ansString = new boolean[N];
//        Arrays.fill(ansString, true);
        powerSet(0, 0, 0, 0, 0, 0, visited);

        ans = ans == Integer.MAX_VALUE ? -1 : ans;

        sb.append(ans);
        sb.append("\n");

//        System.out.println(Arrays.toString(ansString));
        if (ans != -1)
            for (int i = 0; i < ansString.length; i++) {
                if (ansString[i]) sb.append(i + 1).append(" ");
            }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void powerSet(int k, int sp, int sf, int ss, int sv, int sc, boolean[] visited) {
        if (sc >= ans) return;

        if (k == N) {
            if (sp < mp || sf < mf || ss < ms || sv < mv) return;
//            System.out.println(Arrays.toString(visited));
//            System.out.println(Arrays.toString(ansString));

            System.out.println(Arrays.toString(visited));
            System.out.println(Arrays.toString(ansString));

//            if (ans >= sc) {
                ansString = visited.clone();
//            }
            ans = Math.min(ans, sc);


            return;
        }

        visited[k] = true;
        powerSet(k + 1, sp + p[k], sf + f[k], ss + s[k], sv + v[k], sc + c[k], visited);

        visited[k] = false;
        powerSet(k + 1, sp, sf, ss, sv, sc, visited);


    }
}