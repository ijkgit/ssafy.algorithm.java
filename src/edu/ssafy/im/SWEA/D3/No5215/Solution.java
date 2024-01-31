package edu.ssafy.im.SWEA.D3.No5215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	int n, l;
	int[] happy, score;
	int ans;

	public static void main(String[] args) throws IOException {
		new Solution().sol();
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
			recursion(0, 0, 0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	private void recursion(int i, int happySum, int scoreSum) {
		// basis part
		if (scoreSum > l)
			return;
		if (happySum > ans)
			ans = happySum;
		if (i == n)
			return;

		// inductive part
		recursion(i + 1, happySum + happy[i], scoreSum + score[i]);
		recursion(i + 1, happySum, scoreSum);
	}
}
