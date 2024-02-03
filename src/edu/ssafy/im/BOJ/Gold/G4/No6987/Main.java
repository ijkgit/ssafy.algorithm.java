package edu.ssafy.im.BOJ.Gold.G4.No6987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    int arr[][], match[][];
    int ans;
    boolean flag;

    public static void main(String[] args) throws IOException {
        new Main().sol();
    }

    private void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 입력
        for (int t = 0; t < 4; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr = new int[6][3];
            ans = 1;
            boolean isPossible = true;
            L:
            for (int i = 0; i < 6; i++) {
                int sum = 0;
                for (int j = 0; j < 3; j++) {
                    // i : 팀 j : 승,무,패
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    sum += arr[i][j];
                    // 1. 승 무 패 가 5보다 큰 경우
                    if (arr[i][j] > 5) {
                        isPossible = false;
                        break L;
                    }
                }
                // 2. 승 무 패 의 합이 5 가 아닌 경우
                if (sum != 5) {
                    isPossible = false;
                    break;
                }
            }

            if (isPossible) {
                Arrays.sort(arr, (o1, o2) -> o2[0] - o1[0]); // 승수대로 내림차순 정렬
                // 팀 매칭
                int idx = 0;

                match = new int[15][2];
                for (int i = 0; i < 5; i++) {
                    for (int j = i + 1; j < 6; j++) {
                        match[idx][0] = i;
                        match[idx][1] = j;
                        idx++;
                    }
                }

                flag = false;
                recursive(0);
            }
            if(isPossible) {
                if(flag) {
                    ans = 1;
                }
                else {
                    ans = 0;
                }
            } else {
                ans = 0;
            }
            sb.append(ans).append(" ");
        }
        System.out.print(sb);
    }

    private void recursive(int matchCount) {
        // basis part
        if (flag) {
            return;
        }

        if (matchCount == 15) {
            flag = true;
            return;
        }

        // inductive part
        int i = match[matchCount][0];
        int j = match[matchCount][1];

//        System.out.println(i + " " + j);

        // 우리팀 승 상대팀 패
        if (arr[i][0] > 0 && arr[j][2] > 0) {
            arr[i][0]--;
            arr[j][2]--;
            recursive(++matchCount);
            arr[i][0]++;
            arr[j][2]++;
        }

        if (flag) {
            return;
        }

        // 우리팀 무 상대팀 무
        if (arr[i][1] > 0 && arr[j][1] > 0) {
            arr[i][1]--;
            arr[j][1]--;
            recursive(++matchCount);
            arr[i][1]++;
            arr[j][1]++;
        }

        if (flag) {
            return;
        }

        // 우리팀 패 상대팀 승
        if (arr[i][2] > 0 && arr[j][0] > 0) {
            arr[i][2]--;
            arr[j][0]--;
            recursive(++matchCount);
            arr[i][2]++;
            arr[j][0]++;
        }
    }
}
