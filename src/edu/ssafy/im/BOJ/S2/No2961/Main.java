package edu.ssafy.im.BOJ.S2.No2961;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] s, b;
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		s = new int[n];
		b = new int[n];
		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			StringTokenizer st = new StringTokenizer(input);
			s[i] = Integer.parseInt(st.nextToken());
			b[i] = Integer.parseInt(st.nextToken());
		}
		ans = Integer.MAX_VALUE;
		powerSet(0, new boolean[n]);
		sb.append(ans);
		System.out.println(sb);
	}
	
	private static void powerSet(int i, boolean[] v) { 
		// basis part
		if(i == v.length) {
			int ss=1, bs=0, c=0;
			for (int j = 0; j < v.length; j++) {
				if(v[j]) {
					ss *= s[j];
					bs += b[j];
				} else {
					c++;
				}
			}
			if(c != v.length) {
				ans = Math.min(ans, Math.abs(ss - bs));
			}
			return;
		}
		
		// inductive part
		v[i] = true;
		powerSet(i+1, v);
		v[i] = false;
		powerSet(i+1, v);
	}
}
