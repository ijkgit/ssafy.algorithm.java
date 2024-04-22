import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Algo3_대전_05반_임재경 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Box> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			pq.offer(new Box(w, h, c));
		}
		
		ArrayList<Box> list = new ArrayList<>();
		while (pq.isEmpty()) list.add(pq.poll());
		
		int[] a = new int[N];
		int cur = 0;
		for (int i = 0; i < N; i++) {
			if (list.get(i).c > list.get(a[cur]).c) {
				cur = 0;
				a[0] = i;
			} else {
				a[cur++] = i;
			}
		}
		
		for (int a1 : a) {
			sb.append(a1 + 1).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static class Box implements Comparable<Box> {
		int w, h, c;

		public Box(int w, int h, int c) {
			super();
			this.w = w;
			this.h = h;
			this.c = c;
		}

		@Override
		public int compareTo(Box o) {
			if (this.w == o.w) return o.c - this.c;
			return o.w - this.w;
		}
	}
}
