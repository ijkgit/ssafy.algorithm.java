package edu.ssafy.im.BOJ.Silver.S3.No17626;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	private static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N + 1];
		dp[1] = 1;
		
		int min = 0;
		for (int i = 1; i <= N; i++) {
			min = Integer.MAX_VALUE;
			
			for (int j = 1; j * j <= i; j++) {
				int k = i - j * j;
				min = Math.min(min, dp[k]);
			}
			
			dp[i] = min + 1;
		}
		
		sb.append(dp[N]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
