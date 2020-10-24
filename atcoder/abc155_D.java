import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;


public class Main {
	static final long MOD=1000000007;
	static final long MOD2=998244353;
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out);
		InputReader sc=new InputReader(System.in);
		int N=sc.nextInt();
		long K=sc.nextLong();
		long[] A=sc.nextLongArray(N);
		Arrays.sort(A);
		long from=-1000000000000000000l;
		long to=1000000000000000000l;
		while ((to-from)>=1) {
			long mid=(to-from)/2+from;
			if (f(A, mid, K)) {
				to=mid;
			}
			else {
				from=mid+1;
			}
		}
		System.out.println(to);
 	} 
	public static int lower_bound(long[] a, int fromIndex, int toIndex, long val) {
		if (val > a[toIndex])
			return toIndex + 1;
		if (val <= a[fromIndex])
			return fromIndex;
		int lb = fromIndex - 1, ub = toIndex;
		while (ub - lb > 1) {
			int mid = (ub - lb)/2+lb;
			if (a[mid] >= val) {
				ub = mid;
			} else {
				lb = mid;
			}
		}
		return ub;
	}//val以上の一番左にある要素のindexを返す（指定した範囲で）
 
	public static int upper_bound(long[] a, int fromIndex, int toIndex, long val) {
		if (val >= a[toIndex])
			return toIndex + 1;
		if (val < a[fromIndex])
			return fromIndex;
		int lb = fromIndex - 1, ub = toIndex;
		while (ub - lb > 1) {
			int mid = (ub - lb)/2+lb;
			if (a[mid] > val) {
				ub = mid;
			} else {
				lb = mid;
			}
		}
		return ub;
	}//valより大きい一番左にある要素のindexを返す。
	static boolean f(long[] A,long mid,long K) {
		long sum=0;
		for (int i = 0; i < A.length-1; i++) {
			if (A[i]>0) {
				int index=upper_bound(A, i+1, A.length-1, g(mid, A[i]));
				sum+=index-(i+1);
			}else if (A[i]==0) {
				if (mid>=0) {
					sum+=(A.length-1-i);
				}
			}else {
				int index=lower_bound(A, i+1, A.length-1, g(mid, A[i]));
				sum+=(A.length-1-index+1);
			}
		}
		if (sum<K) {
			return false; 
		}else {
			return true;
		}
	}
	static long g(long a,long b) {
		if (a>=0) {
			return a/b;
		}else {
			if (b>0) {
				if (a%b==0) {
					return a/b;
				}else {
					return a/b-1;
				}
			}else {
				if (a%b==0) {
					return a/b;
				}else {
					return a/b+1;
				}
			}
		}
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

