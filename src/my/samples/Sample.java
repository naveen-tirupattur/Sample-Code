/**
 * Main Class
 */
package my.samples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * @author Naveen Tirupattur
 *
 */
public class Sample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int a[] = {1,2,3, 5, 7, 8,10, 12};
		int b[] = {2,3, 6, 9, 10, 11,12};
		for(Integer i:findIntersection(a, b))
		{
			System.out.println("Intersection: "+i);
		}
		
		
	}
	public static int fibo(int n)
	{
		int a =1, b= 1;
		for(int i=3;i<=n;i++)
		{
			int c = a+b;
			a = b;
			b = c;
		}
		return b;

	}

	public static String itoa(int num)
	{
		char[] sA = new char[5];		
		boolean isNeg = false;
		int i=0;

		if(num < 0)
		{
			isNeg = true;
			num *= -1;
		}

		while(num != 0)
		{			
			sA[i++] = (char)((num%10) + '0');
			num /= 10;			
		}

		StringBuffer b = new StringBuffer();
		if(isNeg) b.append('-');

		while(i>0)
		{
			b.append(sA[--i]);
		}
		return b.toString();
	}
	public static void reverseWords(String word)
	{
		int st=0, en=0;
		int len = word.length();
		char[] strArray = word.toCharArray();

		reverse(strArray,0,len-1);
		while(en < len)
		{
			if(strArray[en]!= ' ')				
			{
				st = en;
				while(en<len && strArray[en]!= ' ')
					en++;

				en--;
				strArray = reverse(strArray,st,en);
			}
			en++;
		}

		System.out.println("Reversed: "+new String(strArray,0,len));

	}

	public static char[] reverse(char[] s, int startPos, int endPos)
	{
		char temp='0';
		while(startPos<endPos)
		{			
			temp = s[startPos];
			s[startPos] = s[endPos];
			s[endPos] = temp;
			endPos--;
			startPos++;
		}	

		return s;
	}	

	public static float power(float x, int y)
	{
		float temp = 0;
		if(y==0) return 1;
		temp = power(x,y/2);		
		if(y%2 == 0)
			return temp*temp;
		else
		{
			if(y > 0)		
				return x*temp*temp;
			else
				return temp*temp/x;
		}
	}

	public static int numberOfOnes(int number)
	{

		int count=0;
		while(number !=0)
		{
			number = number & (number - 1);
			count++;

		}
		return count;
	}

	public static void permute(String str)
	{
		int length = str.length();
		boolean used[] = new boolean[length];
		StringBuffer out = new StringBuffer();
		char[] charArray = str.toCharArray();

		doPermute(charArray, used, out, length, 0);

	}
	public static void doPermute(char[] charArray,boolean[] used, StringBuffer out, int length, int level)
	{
		if(level == length)
		{
			System.out.println(out.toString());
			return;
		}
		for(int i=0;i<length;i++)
		{
			if(used[i]) continue;
			out.append(charArray[i]);
			used[i] = true;
			doPermute(charArray,used,out,length,level+1);
			used[i]=false;
			out.setLength(out.length() - 1);
		}
	}

	public static void combine(String str)
	{

		int length = str.length();		
		StringBuffer out = new StringBuffer();
		char[] charArray = str.toCharArray();

		doCombine(charArray, out, length, 0);

	}

	public static void doCombine(char[] charArray, StringBuffer out, int length, int start)
	{
		for(int i=start;i<length;i++)
		{
			out.append(charArray[i]);
			System.out.println(out.toString()+"\n");
			if(i<length-1)
			{
				doCombine(charArray,out,length,i+1);
			}
			out.setLength(out.length()-1);
		}
	}

	public static String removeChars(String src, String rem)
	{
		boolean flag[] = new boolean[128];
		char[] s = src.toCharArray();
		char[] r = rem.toCharArray();
		int len = src.length();
		int sr=0,ds=0;

		for(int i=0;i<r.length;i++)
			flag[(int)r[i]] = true;

		while(sr<len)
		{
			if(!flag[(int)s[sr]])
				s[ds++]=s[sr];
			sr++;
		}
		return new String(s,0,ds);

	}

	public static int atoi(String word)
	{
		boolean isNeg = false;
		int num = 0;
		int i=0, len = word.length();

		char sA[] = word.toCharArray();

		if(sA[0] == '-')
		{
			isNeg = true;
			i=1;
		}
		while(i<len)
		{
			num *= 10;
			num += sA[i++] - '0';

		}

		if(isNeg) num *= -1;

		return num;
	}	

	// method to test LRUCache
	public static void testLRU()
	{
		CLRUCache<Integer, Integer> cache = new CLRUCache<Integer, Integer>(5);
		cache.put(1,1);
		cache.put(2,2);
		cache.put(3,3);		
		cache.put(4,4);
		cache.put(5, 5);
		cache.put(6, 6);
		cache.put(7, 7);		
		cache.get(2);
		cache.get(5);
		cache.put(8, 8);		
		cache.get(4);
		cache.get(7);
		cache.put(1,1);
		cache.put(2,2);
		cache.put(3,3);


		for(Integer i:cache.display())
		{
			System.out.println(i);
		}
	}

	//Method to find median of two sorted arrays	
	public static int findMedian(int[] array1, int[] array2,int low1, int high1, int low2, int high2, int size)
	{

		int m1 = (low1 + high1)/2;
		int m2 = (low2 + high2)/2;

		if(size == 2) return (Math.max(array1[low1],array2[low2])+Math.min(array1[high1],array2[high1]))/2;

		if(array1[m1] == array2[m2]) return array1[m1];

		if(array1[m1] < array2[m2]) 
			return findMedian(array1,array2,m1,high1,low2,m2,size/2);
		else
			return findMedian(array1,array2,low1,m1,m2,high2,size/2);		

	}	

	//Method to find median of two sorted arrays
	public static int findMedian1(int[] array1, int[] array2, int left, int right, int size)
	{
		if(left > right) findMedian1(array2,array1,0,size-1,size);

		int i = (left + right)/2;
		int j = size - i - 1;

		//Termination condition
		if(array1[i] > array2[j] &&( j == size-1 || array1[i]<= array2[j+1]))
		{
			if(array1[i-1]<array2[j] || i == 0)
				return (array1[i]+array2[j])/2;
			else
				return (array1[i-1]+array1[i])/2;
		}

		//Check if the median is on left side of array1
		if(array1[i] > array2[j] && j!= size-1 && array1[i]>= array2[j+1])
			return findMedian1(array1,array2,left,i-1,size);
		else
			return findMedian1(array1,array2,i+1,right,size);

	}

	//Method to find kth smallest element in two sorted arrays
	public static int findKthSmallest(int[] a, int[] b, int l1, int h1, int l2, int h2, int m, int n, int k)
	{
		if(m+n < k) return -1;

		int i = (l1+h1)/2;
		int j = k-1-i;

		if(a[i] < b[j] && a[i] > b[j-1]) return a[i];
		else
		if(b[j]<a[i] && b[j] > a[i-1]) return b[j];

		if(a[i] < b[j]) return findKthSmallest(a, b, i+1, h1, l2, j-1, m, n, k);
		else
			return findKthSmallest(a, b, l1, i-1, j+1, h2, m, n, k);		
		
	}
	
	//Method to find intersection in 2 sorted arrays
	public static List<Integer> findIntersection(int[] a, int[] b)
	{
		
		//Create a list to store all the intersections
		List<Integer> intersectionList = new ArrayList<Integer>();
		
		int m = a.length;
		int n = b.length;
		int i = 0, j = 0;
		
		while(i < m && j < n)
		{
			if(a[i] > b[j])
			{
				j++;
			}else if(a[i] < b[j])
			{
				i++;
			}else
			{
				intersectionList.add(Integer.valueOf(a[i]));
				i++;
				j++;
			}
				
		}
		return intersectionList;
	}
	
}

