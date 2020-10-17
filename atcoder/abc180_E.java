import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Main {
	static final long MOD=1000000007;
	static final long MOD2=998244353;
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out);
		InputReader sc=new InputReader(System.in);
		int V=sc.nextInt();
		long[][] edge=new long[V][V];
		long[][] XYZ=new long[V][3];
		for (int i = 0; i < V; i++) {
			XYZ[i]=sc.nextLongArray(3);
		}
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				edge[i][j]=Math.abs(XYZ[i][0]-XYZ[j][0])+Math.abs(XYZ[i][1]-XYZ[j][1])+Math.max(0, XYZ[j][2]-XYZ[i][2]);
			}
		}
		long[][] dp=new long[V][1<<V];
		for (int j = 0; j < dp.length; j++) {
			Arrays.fill(dp[j], Long.MAX_VALUE/2);
		}
		dp[0][0]=0;
		for (int j2 = 0; j2 < 1<<V; j2++) {
			for (int j = 0; j < dp.length; j++) {
				for (int i = 0; i < dp.length; i++) {
					dp[i][j2|(1<<i)]=Math.min(dp[i][j2|(1<<i)], dp[j][j2]+edge[j][i]);
				}
			}
		}
		System.out.println(dp[0][(1<<V)-1]);
 	} 
		static class InputReader { 
		private InputStream in;
		private byte[] buffer = new byte[1024];
		private int curbuf;
		private int lenbuf;
		public InputReader(InputStream in) {
			this.in = in;
			this.curbuf = this.lenbuf = 0;
		}
 
		public boolean hasNextByte() {
			if (curbuf >= lenbuf) {
				curbuf = 0;
				try {
					lenbuf = in.read(buffer);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (lenbuf <= 0)
					return false;
			}
			return true;
		}
 
		private int readByte() {
			if (hasNextByte())
				return buffer[curbuf++];
			else
				return -1;
		}
 
		private boolean isSpaceChar(int c) {
			return !(c >= 33 && c <= 126);
		}
 
		private void skip() {
			while (hasNextByte() && isSpaceChar(buffer[curbuf]))
				curbuf++;
		}
 
		public boolean hasNext() {
			skip();
			return hasNextByte();
		}
 
		public String next() {
			if (!hasNext())
				throw new NoSuchElementException();
			StringBuilder sb = new StringBuilder();
			int b = readByte();
			while (!isSpaceChar(b)) {
				sb.appendCodePoint(b);
				b = readByte();
			}
			return sb.toString();
		}
 
		public int nextInt() {
			if (!hasNext())
				throw new NoSuchElementException();
			int c = readByte();
			while (isSpaceChar(c))
				c = readByte();
			boolean minus = false;
			if (c == '-') {
				minus = true;
				c = readByte();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res = res * 10 + c - '0';
				c = readByte();
			} while (!isSpaceChar(c));
			return (minus) ? -res : res;
		}
 
		public long nextLong() {
			if (!hasNext())
				throw new NoSuchElementException();
			int c = readByte();
			while (isSpaceChar(c))
				c = readByte();
			boolean minus = false;
			if (c == '-') {
				minus = true;
				c = readByte();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res = res * 10 + c - '0';
				c = readByte();
			} while (!isSpaceChar(c));
			return (minus) ? -res : res;
		}
 
		public double nextDouble() {
			return Double.parseDouble(next());
		}
 
		public int[] nextIntArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = nextInt();
			return a;
		}
 
		public long[] nextLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++)
				a[i] = nextLong();
			return a;
		}
 
		public char[][] nextCharMap(int n, int m) {
			char[][] map = new char[n][m];
			for (int i = 0; i < n; i++)
				map[i] = next().toCharArray();
			return map;
		}
	}
}