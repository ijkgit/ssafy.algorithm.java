package edu.ssafy.im.BOJ.Bronze.B2.No3040;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int arr[] = new int[9];
		for (int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			sum += arr[i];
		}

		int goal = sum - 100;
		int index1 = 0;
		int index2 = 0;

		for (int i = 0; i < 8; i++) {
			for (int j = i + 1; j < 9; j++) {
				if (arr[i] + arr[j] == goal) {
					index1 = i;
					index2 = j;
					break;
				}
			}
		}
		for (int i = 0; i < 9; i++) {
			if (i == index1 || i == index2)
				continue;
			System.out.println(arr[i]);
		}
	}

}
