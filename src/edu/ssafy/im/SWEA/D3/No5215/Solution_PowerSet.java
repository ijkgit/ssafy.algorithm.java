package edu.ssafy.im.SWEA.D3.No5215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_PowerSet {
	int n, l, ans;
	int[] happy, score;

	public static void main(String[] args) throws IOException {
		new Solution_PowerSet().sol();
	}

	private void sol() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testCase; t++) {
			String input = br.readLine();
			StringTokenizer st = new StringTokenizer(input);

			n = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());

			happy = new int[n];
			score = new int[n];
			for (int i = 0; i < n; i++) {
				input = br.readLine();
				st = new StringTokenizer(input);
				happy[i] = Integer.parseInt(st.nextToken());
				score[i] = Integer.parseInt(st.nextToken());
			}

			ans = 0;
			powerSet(0, new boolean[n]);
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	private void powerSet(int idx, boolean[] sel) {
		// basis part
		if (idx == n) {
			int happySum = 0, scoreSum = 0;
			for (int i = 0; i < sel.length; i++) {
				if(sel[i]) {
					happySum += happy[i];
					scoreSum += score[i];
				}
			}
			if (scoreSum > l)
				return;
			if (happySum > ans)
				ans = happySum;
			return;
		}

		// inductive part
		sel[idx] = true;
		powerSet(idx + 1, sel);
		sel[idx] = false;
		powerSet(idx + 1, sel);
	}
}
