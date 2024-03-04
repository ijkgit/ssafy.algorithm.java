package a2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	private static int T;
	private static int N;
	private static int M;
	private static int[][] map;
	private static boolean[][] v;
	private static int targetX, targetY;
	private static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 2) map[i][j] = 1;
					if (map[i][j] == 3) {
						targetX = i;
						targetY = j;
					}
				}
			}
			
			
			ans = Integer.MAX_VALUE;
			// 해당 depth 로 못가면 depth 를 증가시켜보자
			for (int d = 1; d < 50; d++) {
				v = new boolean[N][M];
				dfs(N - 1, 0, d);
				if (ans != Integer.MAX_VALUE)
					break;
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private static void dfs(int x, int y, int depth) {
		// 목적지 도달 시 끝
		if (x == targetX && y == targetY) {
			ans = Math.min(ans, depth);
			return;
		}

		// 가지치기 1
		if (depth >= ans)
			return;

		// 해당 레벨 범위 내로 이동 가능?
		v[x][y] = true;
		for (int i = 1; i <= depth; i++) {
			if (x - i >= 0 && map[x - i][y] != 0 && !v[x - i][y]) {
				// 가능하면 위로 가자!
				dfs(x - i, y, depth);
			} else { // 불가능하면 한칸 오른쪽으로 가보자!
				if (y < M - 1 && map[x][y + 1] != 0 && !v[x][y + 1]) {
					dfs(x, y + 1, depth);
				}

				// 왼쪽도 가보자!
				else if (y > 0 && map[x][y - 1] != 0 && !v[x][y - 1]) {
					dfs(x, y - 1, depth);
				}
			}
			// 아래도 가보자..
			if (x + i < N && map[x+i][y] != 0 && !v[x+i][y]) {
				dfs(x + i, y, depth);
			}
		}
	}
}
