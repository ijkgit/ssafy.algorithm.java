package edu.ssafy.im.BOJ.No1244;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int sw = Integer.parseInt(br.readLine());
		
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		int[] arr = new int[sw];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); 
		}
		
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			input = br.readLine();
			st = new StringTokenizer(input);
			
			int s = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
						
			if (s == 1) {
				int count = 1;
				int oriC = c;
				do {
					arr[c-1] = arr[c-1] == 1 ? 0 : 1;
					count++;
					c = oriC * count;
				} while(c-1 < sw);
			} else if (s == 2) {
				int newX1 = c-1;
				int newX2 = c-1;
				arr[c-1] = arr[c-1] == 1 ? 0 : 1;
				do {
					newX1--;
					newX2++;
					if (0 <= newX1 && newX2 < sw) {
						if (arr[newX1] == arr[newX2]) {
							arr[newX1] = arr[newX1] == 1 ? 0 : 1;
							arr[newX2] = arr[newX1];
						}
						else {
							break;
						}
					} else {
						break;
					}
				} while (true);
			}
		}
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
			if((i+1)%20==0) {
				sb.append("\n");
			} else {
				sb.append(" ");
			}
		}
		System.out.print(sb);
	}
}
