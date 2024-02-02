package edu.ssafy.im.BOJ.G4.No6987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	int ans;
	int[][] arr;
	boolean[] visited;
	int n = 5;
	StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		new Main().sol();
	}

	private void sol() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		

		// 불가능한 경우
		// 1. 승 무 패 가 5 초과
		// 2. 승 무 패 의 합이 5 가 아닌 경우
		// 3. 팀의 승 무 패가 맞지 않는 경우

		// 입력
		for (int t = 0; t < 4; t++) {
			String input = br.readLine();
			StringTokenizer st = new StringTokenizer(input);
			arr = new int[6][3];
			ans = 1;
			L: for (int i = 0; i < 6; i++) {
				int sum = 0;
				for (int j = 0; j < 3; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					sum += arr[i][j];
					// 1. 승 무 패 가 5 초과
					if (arr[i][j] > 5) {
						ans = 0;
						break L;
					}
				}
				// 2. 승 무 패 의 합이 5 가 아닌 경우
				if (sum > 5) {
					ans = 0;
					break L;
				}
			}

			visited = new boolean[6];
			if (ans == 1) {
				Arrays.sort(arr, (o1, o2) -> o2[0] - o1[0]);
				recursive(0, 0);
			}

			sb.append(ans);
		}
		System.out.print(sb);
	}

	private void recursive(int i, int c) {
		// basis part
		System.out.println(Arrays.toString(visited));
		if(i == 6)
			return;
		
		// inductive part
		if (!visited[i]) {
			visited[i] = true;
			if (arr[i][0] != 0 && arr[i + 1][2] != 0) {
				arr[i][0]--;
				arr[i+1][2]--;
				c++;
			} else if (arr[i][1] != 0 && arr[i+1][1] != 0) {
				arr[i][1]--;
				arr[i+1][1]--;
			}
		}
		recursive(i + 1, c);
		visited[i] = false;
		recursive(i + 1, c);
	}
}
