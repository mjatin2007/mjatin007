#include <stdio.h>

void add ( int *a, int b)
{

*a=*a+b; 

}

void call_add() {
  int x = 1, y = 2;

  add(&x, y);           

  printf ("%d\n", x);
}



int main() {
  call_add();
}

