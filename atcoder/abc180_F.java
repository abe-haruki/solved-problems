import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Main {
	static final long MOD=1000000007;
	static final long MOD2=998244353;
	static long inv2=modInv(2);
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out);
		InputReader sc=new InputReader(System.in);
		int N=sc.nextInt();
		int M=sc.nextInt();
		int L=sc.nextInt();
		System.out.println((f(N, M, L)-f(N, M, L-1)+MOD)%MOD);
 	} 
	static long f(int N,int M,int l) {
		long[][] dp=new long[N+1][M+1];
		dp[0][0]=1;
		Binomial bi=new Binomial(MOD);
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= M; j++) {
				for(int j2 = 1; j2 <= l; j2++) {
					if (i-j2>=0&&j-(j2-1)>=0) {
						dp[i][j]+=(((dp[i-j2][j-(j2-1)]*path(j2, bi))%MOD)*bi.nCk(N-(i-j2)-1, j2-1, MOD))%MOD;
						dp[i][j]%=MOD;
					}
					if (j2==1) {
						
					}else {
						if (i-j2>=0&&j-j2>=0) {
							dp[i][j]+=(((dp[i-j2][j-j2]*circle(j2, bi))%MOD)*bi.nCk(N-(i-j2)-1, j2-1, MOD))%MOD;
							dp[i][j]%=MOD;
						}
					}
				}
			}
		}
		return dp[N][M];
	}
	static long path(int n,Binomial bi){
		if (n==1) {
			return 1;
		}else {
			return (bi.fac[n]*inv2)%MOD;
		}
	}
	static long circle(int n,Binomial bi) {
		if (n<=2) {
			return 1;
		}else {
			return (bi.fac[n-1]*inv2)%MOD;
		}
	}
	static long modPow(long x, long y) {
        long z = 1;
        while (y > 0) {
            if (y % 2 == 0) {
                x = (x * x) % MOD;
                y /= 2;
            } else {
                z = (z * x) % MOD;
                y--;
            }
        }
        return z;
    }//xのy乗mod
 
    static long modInv(long x) {
        return modPow(x, MOD - 2);
    }//xのmodでの逆元
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
class Binomial{
	int MAX = 510000;//ほしいサイズ
	long[] fac=new long[MAX];
	long[] finv=new long[MAX];
	long[] inv=new long[MAX];
	public Binomial(long MOD){
		fac[0] = fac[1] = 1;
	    finv[0] = finv[1] = 1;
	    inv[1] = 1;
	    for (int i = 2; i < MAX; i++){
	        fac[i] = fac[i - 1] * i % MOD;
	        inv[i] = MOD - inv[(int) (MOD%i)] * (MOD / i) % MOD;
	        finv[i] = finv[i - 1] * inv[i] % MOD;
	    }//facがx!、finvがx!の逆元,10^7くらいまでのテーブル(MAXまで)
	}
	long nCk(int n,int k,long MOD) {
	    if (n < k) return 0;
	    if (n < 0 || k < 0) return 0;
	    return fac[n] * (finv[k] * finv[n - k] % MOD) % MOD;
	}
}