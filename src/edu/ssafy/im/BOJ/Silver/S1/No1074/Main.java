package edu.ssafy.im.BOJ.Silver.S1.No1074;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private int cnt = 0, ans;	
	private int r;
	private int c;
	public static void main(String[] args) throws IOException {
		new Main().sol();
	}
	private void sol() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		division(n, r, c);
	}
	private void division(int n, int x, int y) {
		if(n == 2) {
			for (int i = x; i < x+2; i++) {
				for (int j = y; j < y+2; j++) {
					if(x == r && y == c) {
						ans = cnt;
						return;
					}
					cnt++;
				}
			}
		}
		
		division(n/2, n/2, n/2);
		division(n/2, n/2, n);
		division(n/2, n, n/2);
		division(n/2, n, n);
	}
}
