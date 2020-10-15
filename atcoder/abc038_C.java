import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

public class Main {
	static final long MOD=1000000007;
	static final long MOD2=998244353;
	static boolean[] visited;
	static ArrayDeque<Integer> arrayDeque;
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out);
		InputReader sc=new InputReader(System.in);
		int N=sc.nextInt();
		int[] w=new int[N];
		int[] h=new int[N];
		ArrayList<Pair> arrayList=new ArrayList<>();
		for (int i = 0; i < h.length; i++) {
			w[i]=sc.nextInt();
			h[i]=sc.nextInt();
			arrayList.add(new Pair(h[i], w[i]));
		}
		Collections.sort(arrayList);
		BIT bit=new BIT(100001);
		int[] dp=new int[N];
		for (int i = 0; i < dp.length; i++) {
			int ww=arrayList.get(i).y;
			dp[i]=bit.max(ww-1)+1;
			bit.update(ww, dp[i]);
		}
		int max=0;
		for (int i = 0; i < dp.length; i++) {
			max=Math.max(max, dp[i]);
		}
		System.out.println(max);
	} 
	static class BIT{
		int size;
		int[] bit;
		public BIT(int n) {
			size=n;
			bit=new int[size];
		}
		public int max(int i){
			int s=0;
			while (i>0) {
				s=Math.max(bit[i], s);
				i-=(i&(-i));//iの二進表現での一番右の１を消す
			}
			return s;
		}
		public void update(int i,int x) {
			while (i<size) {
				bit[i]=Math.max(x, bit[i]);
				i+= (i&(-i));
			}
		}
	}
	static class Pair implements Comparable<Pair>{
    	public int x;
    	public int y;
    	public Pair(int x,int y) {
    		this.x=x;
    		this.y=y;
    	}
    	@Override
    	public boolean equals(Object obj) {
    		if(obj instanceof Pair) {
    			Pair other = (Pair) obj;
    			return other.x==this.x && other.y==this.y;
    		}
    		return false;
    	}//同値の定義
    	@Override
    	public int hashCode() {
    		return Objects.hash(this.x,this.y);
    	}//これ書かないと正しく動作しない（要　勉強）
    	@Override
    	public int compareTo( Pair p){
    		if (this.x>p.x) {
    			return 1;
    		}
    		else if (this.x<p.x) {
    			return -1;
    		}
    		else {
    			if (this.y<p.y) {
					return 1;
				}else {
					return -1;
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
