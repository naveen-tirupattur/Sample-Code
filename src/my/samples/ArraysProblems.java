package my.samples;

public class ArraysProblems {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		/*int[] numbers = {9, 3, 6, 5, 7, -1, 13, 14, -2, 12, 0};
		
		findSum(numbers, 12);*/
		
		int[] a = {1,2,7,8,9,5,6,3,4};
		
		System.out.println(find(a,0,8,4));
		/*int[] b = {1,3,5,7};
		
		int[] c = merge(a,b,4,4);
		for(int i=0;i<c.length;i++)
		{
			System.out.println(c[i]);
		}*/
		
	}
	
	public static void findSum(int[] numbers, int sum)
	{
		int first = 0, last = numbers.length - 1;
		while(first < last)
		{
			int s = numbers[first]+numbers[last];
			if(s == sum) {
				System.out.println("First: "+numbers[first]);
				System.out.println("Last: "+numbers[last]);
				first++;last--;
			}else if(s < sum)
			{
				first++;
			}else
			{
				last--;
			}
		}
		
	}
	
	public static int maxSum(int[] numbers)
	{
		int maxsum = 0, sum = 0;
		for(int i=0;i<numbers.length;i++)
		{
			sum+= numbers[i];
		if(maxsum < sum) maxsum = sum;
		else if(sum < 0) sum = 0;
		}
		return maxsum;		
	}

	public static void removeDuplicates(String word)
	{
		
		char[] str = word.toCharArray();
		boolean hit[] = new boolean[256];
		for(int i=0;i<256;i++) hit[i] = false;
		
		hit[str[0]] = true;
		
		int tail = 1;
		for(int j=1;j<str.length;j++)
		{
			if(!hit[str[j]]){
				str[tail] = str[j];
				tail++;
				hit[str[j]] = true;
			}
		}
		System.out.println(str);

	}
	
	public static int[] merge(int[] a, int[] b, int m, int n)
	{
		int k = m + n - 1;
		int i = m - 1;
		int j = n - 1;
		
		while(i >= 0 && j >= 0)
		{
			if(a[i] > b[j])
				a[k--] = a[i--];
			else
				a[k--] = b[j--];
		}
			while(j >= 0) a[k--] = b[j--];
		
		return a;
	}
	
	public static int find(int[] a, int l, int u, int x)
	{
		while( l <= u)
		{
			int m = (l+u)/2;
			if(x==a[m]) return m;
			else
				if(a[l]<= a[m])
				{
					if(x > a[m]) l = m+1;
					else
					if(x >= a[l]) u = m-1;
					else
						l = m+1;					
				}
				else if(x < a[m]) u = m-1;
				else if(x <= a[u]) l = m+1;
				else u = m -1 ;
			
		}
		return -1;
	}


}
