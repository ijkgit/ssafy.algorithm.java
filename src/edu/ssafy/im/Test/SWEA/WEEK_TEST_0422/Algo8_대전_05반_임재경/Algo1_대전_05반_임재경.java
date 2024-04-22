import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Algo1_대전_05반_임재경 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			// 경주장 입력
			int[] arr = new int[10];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 10; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			

			int ans = 0;
			for (int i = 0; i < 5; i++) {
				// 토끼 입력
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				boolean flag = false;
				for (int j = 1; j < 10; j++) {
					int diff = arr[j-1] - arr[j];
					
					if (diff <= 0) { // 오르막
						if (A + diff < 0) {
							flag = true; break;
						}
					} else { // 내리막
						if (B - diff < 0) {
							flag = true; break;
						}
					}
				}
				
				if (!flag) ans++;
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
