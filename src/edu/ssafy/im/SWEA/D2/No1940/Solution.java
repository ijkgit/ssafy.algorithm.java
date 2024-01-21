package edu.ssafy.im.SWEA.D2.No1940;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int test_case = Integer.parseInt(br.readLine());

        for (int t = 1; t <= test_case; t++) {
            int n = Integer.parseInt(br.readLine());

            int speed = 0;
            int ans = 0;
            for (int i = 0; i < n; i++) {
                String input = br.readLine();
                StringTokenizer st = new StringTokenizer(input);

                int direction = Integer.parseInt(st.nextToken());

                if (direction == 2)
                    direction = -1;

                if (direction != 0) {
                    int pace = Integer.parseInt(st.nextToken());
                    speed += direction * pace;
                    if (speed < 0)
                        speed = 0;
                }
                ans += speed;
            }
            sb.append("#" + t + " " + ans + "\n");
        }
        System.out.println(sb);
    }
}
