#include <stdio.h>

int strlen1()
{
char s[]="abcdef",i;
    for(i=0; s[i]!='\0'; i++);
    printf("Length of string: %d",i);
    return 0;
}

int main()
{
 return strlen1();   
}

