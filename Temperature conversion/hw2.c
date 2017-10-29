/* CSC 406 201/210 SUI2016

   Homework assignment 2.

   You must complete the 4 functions below.
   In addition, you must write a header file containing
   prototypes for these 4 functions.
*/
#include<stdio.h>
#include "jatin.h"

// converts between Fahrenheit ('F') and Celsius ('C').
void convert_temp(int t1, char sc1, int *t2, char *sc2) {
  if (sc1 == 'F' || sc1 == 'f') 
  {
*t2 = (t1-32)/1.8;

  }
  else if (sc1 == 'C' || sc1 == 'c') 
  {

*t2 = (1.8*t1)+32;

  }
  else
  {
	  printf("Enter valid temperature");
  }
}

//Function for Question-2
void read_ints(int a[ ], int s)
    {

        int i;
        for(i=0;i<s;i++)
        {
            scanf("%d",&a[i]);

        }
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

// return 0 if str1 and str2 are character-by-character the same,
// or a negative number if str1 is alphabetically before str2,
// or a positive number otherwise
int strcmp406(char str1[ ], char str2[ ]) 
{



  int c = 0;
 
   while (str1[c] == str2[c]) 
   {
      if (str1[c] == '\0' || str2[c] == '\0')
         break;
      c++;
   }
 
   if (str1[c] == '\0' && str2[c] == '\0')
      return str2[c]-str1[c];
   else
      return str2[c]-str1[c];
}

// In Python, str1 = str1 + str2
char *strcat406(char *str1, char *str2) {


  while(*str1)
      str1++;
 
   while(*str2)
   {
      *str1 = *str2;
      str2++;
      str1++;
   }
   *str1 = '\0';
   
   return str1;
}
