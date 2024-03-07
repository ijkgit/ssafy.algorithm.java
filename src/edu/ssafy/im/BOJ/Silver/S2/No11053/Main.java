package edu.ssafy.im.BOJ.Silver.S2.No11053;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int[] arr;
	private static int ans;
	private static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		dp = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			sol(i);
		}
		
		for (int i = 0; i < N; i++) {
			ans = Math.max(ans, dp[i]);
		}
		
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private static int sol(int i) {
		if (dp[i] == 0) {
			dp[i] = 1;
			
			for (int j = i-1; j >= 0; j--) {
				if (arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], sol(j) + 1);
				}
			}
		}
		return dp[i];
	}
}
