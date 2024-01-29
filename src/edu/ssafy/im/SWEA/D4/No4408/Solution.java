package edu.ssafy.im.SWEA.D4.No4408;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		boolean arr[] = new boolean[200];
		ArrayList[] array = new ArrayList<Integer>();
		for (int t = 1; t <= testCase; t++) {
			int n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				String input = br.readLine();
				StringTokenizer st = new StringTokenizer(input);

				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());

				int si, ei;
				if (s % 2 == 0) {
					si = s / 2 - 1;
				} else {
					si = s / 2;
				}
				if (e % 2 == 0) {
					ei = e / 2 - 1;
				} else {
					ei = e / 2;
				}

				boolean status = true;
				for (int j = si; j <= ei; j++) {
					if (arr[j]) {
						status = false;
						break;
					}
				}
				if (status) {
					for (int j = si; j <= ei; j++) {
						arr[j] = true;
					}
				} else {
					array.add(new ArrayList<Integer>());
				}
			}
		}
	}
}
