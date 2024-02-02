package edu.ssafy.im.BOJ.Silver.S3.No11659;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int arr[] = new int[n];
		int sum[] = new int[n+1];
		input = br.readLine();
		st = new StringTokenizer(input);
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		sum[1] = arr[0];
		for(int i=2; i<=n; i++) {
			sum[i] += sum[i-1] + arr[i-1];
		}
		
		for (int j = 0; j < m; j++) {
			input = br.readLine();
			st = new StringTokenizer(input);
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken());
			int ans = sum[b] - sum[a];
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
}
