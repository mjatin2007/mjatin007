#include <stdio.h>
#include <string.h> // you should NOT include this; I have done so for testing purposes
#include "jatin.h"


int main() {
  int t1, t2;
  char sc1, sc2;
  char str1[100], str2[100];
  while (1) {
    printf("Enter a temperature and a scale, or 0 X  for the next problem\n");
    scanf("%d %c", &t1, &sc1);
    if (t1 == 0 || sc1 == 'X' || sc1=='x') break;
    convert_temp(t1, sc1, &t2, &sc2);
    printf("%d %c = %d %c\n", t1, sc1, t2, sc2);
  }
  while(1)
  {
  int numbers[5];
  printf("Type 5 numbers or 0 for next problem\n");
  read_ints(numbers, 5);
  printf("You typed ");
  int i;
  for (i=0; i<5; i++)
    printf("%d ", numbers[i]);
  printf("The sum is ");
  printf("%d.\n", sum_array(numbers, 5));
  break;
  }
  while (1) {
    printf("Type a word for compare, or -1 for the next problem\n");
    scanf("%s", str1);
    if (strcmp(str1, "-1") == 0)
      break;
    printf("Type another word for compare\n");
    scanf("%s", str2);
    printf("strcmp406 returns %d\n", strcmp406(str1, str2));
  }
  while (1) {
    printf("Type a word for concatenation, or -1 for the next problem\n");
    scanf("%s", str1);
    if (strcmp(str1, "-1") == 0)
      break;
    printf("Type another word for concat\n");
    scanf("%s", str2);
    printf("strcat406 returns %s\n", strcat406(str1, str2));
    printf("str1 is %s\n", str1);
  }
}
