package edu.ssafy.im.BOJ.Silver.S3.No1463.first;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int X = Integer.parseInt(br.readLine());
		
		int[] dp = new int[X+1];
		dp[1] = 0;
		
		for (int i = 2; i <= X; i++) {
			dp[i] = dp[i-1] + 1;
			if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i/3] + 1);
			if (i % 2 == 0)	dp[i] = Math.min(dp[i], dp[i/2] + 1);
		}
		
		sb.append(dp[X]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
