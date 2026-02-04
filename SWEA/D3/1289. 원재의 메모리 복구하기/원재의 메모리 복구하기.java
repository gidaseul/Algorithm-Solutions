import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		
        Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();

		for (int i=1; i<=T; i++) {
			String s = sc.next();
			int currentNum = 0;
			int countNum = 0;
			
			for (int j=0; j<s.length();j++) {
					int bit = s.charAt(j) - '0';
					
					if(bit!=currentNum) {
						countNum++;
						currentNum = bit;
					}
			}
			System.out.println("#"+i+" "+countNum);
		}
	
	}
}