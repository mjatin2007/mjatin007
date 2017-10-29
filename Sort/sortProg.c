#include	<stdlib.h>
#include	<stdio.h>
#include	"sortHeader.h"


#define		ARRAY_LEN	65536
#define		TEXT_LEN	16

int		array[ARRAY_LEN];


//  PURPOSE:  To initialize array 'array[]' of length 'arrayLen' with (pseudo-)
//	random values.  No return value.
void		initializeArray	(int		arrayLen,
				 int*		array
				)
{
  //  I.  Application validity check:

  //  II.  Give 'array[]' random values.
  int	i;

  for  (i = 0;  i < arrayLen;  i++)
    array[i] = rand() % 1024;

  //  III.  Finished:
}



//  PURPOSE:  To exchange the element at index 'i' with that at index 'j' in
//	array 'array'.  No return value.
void		exchange	(int*		array,
				 int		i,
				 int		j
				)
{
  //  I.  Application validity check:

  //  II.  Do exchange:
  int	temp	= array[i];

  array[i]	= array[j];
  array[j]	= temp;

  //  III.  Finished:
}


//  PURPOSE:  To run the integer-sorting program.  Ignores command line
//	arguments.  Returns 'EXIT_SUCCESS' to OS.
int		main		()
{
  initializeArray(ARRAY_LEN,array);

  int	choice;

  do
  {
    char	text[TEXT_LEN];

    printf
	("How would you like to sort %d integers:\n"
	 "1: Insertion-sort\n"
	 "2: Bubble-sort\n"
	 "Your choice? ",
	 ARRAY_LEN
	);
    fgets(text,TEXT_LEN,stdin);
    choice = atoi(text);
  }
  while  ( (choice < 1) || (choice > 2) );

  switch  (choice)
  {
  case 1 :
    insertionSort(ARRAY_LEN,array);
    break;
  case 2 :
    bubbleSort(ARRAY_LEN,array);
    break;
  }

  int i;

  for (i = 0;  i < ARRAY_LEN;  i++)
    printf("%d\n",array[i]);

  return(EXIT_SUCCESS);

}
