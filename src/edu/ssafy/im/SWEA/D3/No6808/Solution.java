package edu.ssafy.im.SWEA.D3.No6808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

	static int[] selected;
	static boolean visited[];
	static int GYU[];
	static boolean[] cards;
	static long ans;
	static long lose;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			ans = 0;
			lose = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			cards = new boolean[19];
			visited = new boolean[19];
			GYU = new int[9];
			selected = new int[9];
			for (int i = 0; i < 9; i++) {
				int in = Integer.parseInt(st.nextToken());
				cards[in] = true;
				GYU[i] = in;
				// 규영 true
			}
			recursive(0, 0);
			sb.append("#").append(test_case).append(" ").append(ans).append(" ").append((lose)).append('\n');
		}
		System.out.println(sb);

	}

	private static void recursive(int idx, int k) {
		if (idx == selected.length) {
			int PointGYU = 0;
			int PointINY = 0;
			for (int i = 0; i < 9; i++) {
				if (selected[i] > GYU[i]) {
					PointINY += (selected[i] + GYU[i]);
				} else if (selected[i] < GYU[i]) {
					PointGYU += (selected[i] + GYU[i]);
				}
			}
			if (PointINY < PointGYU)
				ans++;
			else if (PointINY > PointGYU)
				lose++;
			return;

		}
		for (int i = 1; i <= 18; i++) {
			if (cards[i])
				continue;
			if (!visited[i]) { // 인영
				visited[i] = true;
				selected[idx] = i;
				recursive(idx + 1, k + 1);
				visited[i] = false;
			}

		}

	}

}
