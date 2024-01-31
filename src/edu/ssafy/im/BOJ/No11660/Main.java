package edu.ssafy.im.BOJ.No11660;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[n][n];
		for (int i = 0; i < arr.length; i++) {
			input = br.readLine();
			st = new StringTokenizer(input);
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		int[] sum = new int[n*n+1];
		sum[1] = arr[0][0];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if(i==0&&j==0) continue;
				sum[i*n+j+1] = sum[i*n+j] + arr[i][j];
			}
		}
		System.out.println(Arrays.toString(sum));
		
		for (int i = 0; i < m; i++) {
			input = br.readLine();
			st = new StringTokenizer(input);
			int x1 = Integer.parseInt(st.nextToken())-1;
			int y1 = Integer.parseInt(st.nextToken())-1;
			int x2 = Integer.parseInt(st.nextToken())-1;
			int y2 = Integer.parseInt(st.nextToken())-1;
			int ans = 0;
			if(x1 != x2 && y1 != y2) {
				for(int j=0; j<x2; j++)
					ans += sum[(x1+j)*n+y2+1] - sum[(x1+j)*n+y1];
			}
			else {
				ans = sum[x2*n+y2+1] - sum[x1*n+y1];
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
}
