package edu.ssafy.im.BOJ.No3985;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int l = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());

		boolean[] visited = new boolean[l];
		int[] arr = new int[n];
		int[] count = new int[n];
		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			StringTokenizer st = new StringTokenizer(input);

			int p = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			arr[i] = k - p;

			for (int j = p; j <= k; j++) {
				if (!visited[j]) {
					visited[j] = true;
					count[i]++;
				}
			}
		}
		
		int max = 0;
		int ans = 0;
		for (int i = 0; i < n; i++) {
			if (arr[i] > max) {
				ans = i+1;
			} 
		}
		sb.append(ans + "\n");
		
		max = 0;
		ans = 0;
		for (int i = 0; i < n; i++) {
			if (count[i] > max) {
				ans = i+1;
			}
		}
		sb.append(ans);
		
		System.out.print(sb);
	}
}
