#include<stdio.h>

int main() 
{
  int small_numbers[] = {3, 6, 2, 6, 1, 0, 6};
  int big_numbers[ ] = {1717986912, 1717986912};
  int the_sum;

  the_sum = sum_array(small_numbers,7);
  // should print 24
  printf("This output should be 24\n%d\n", the_sum);
  the_sum = sum_array(big_numbers, 2);
  // should print a negative number  
printf("This output should be negative\n%d\n", the_sum);
  );
}

int sum_array(int a[], int b)
{
   int i, the_sum=0;
   for (i=0; i<b; i++)
   {
	 the_sum = the_sum + a[i];
   }
   return(the_sum);
}