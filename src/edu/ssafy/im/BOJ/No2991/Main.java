package edu.ssafy.im.BOJ.No2991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        input = br.readLine();
        st = new StringTokenizer(input);
        int[] arrivedTime = new int[3];
        for (int i = 0; i < 3; i++) {
            arrivedTime[i] = Integer.parseInt(st.nextToken());
        }

        for (int at : arrivedTime) {
            int ans = 0;
            if (0 < (at % (a + b)) && (at % (a + b)) <= a) {
                ans++;
            }
            if (0 < (at % (c + d)) && (at % (c + d)) <= c) {
                ans++;
            }
            sb.append(ans + "\n");
        }

        System.out.print(sb);
    }
}
