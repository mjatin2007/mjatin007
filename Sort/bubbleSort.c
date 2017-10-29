#include	"sortHeader.h"

//  PURPOSE:  To sort the 'arrayLen' integers in array 'array[]' in ascending
//	order according to the bubble-sort algorithm.  No return value.
void		bubbleSort	(int		arrayLen,
				 int*		array
				)
{
  //  I.  Application validity check:

  //  II.  Sort 'array[]':
  int	haveExchanged;
  //int	arrayLenMinusOne	= arrayLen-1;

  //  II.A.  Each iteration redoes the inner loop while the inner loop
  //  	     reported that it exchanged at least one adjacent pair:
  do
  {
    //  II.A.1.  Note that have not exchanged any pairs yet:
    haveExchanged	= 0;

    //  II.A.2.  Go thru all 'array[]' and exchange any pairs that are
    //		 improperly ordered:
    int	index;

    for  (index = 0;  index < arrayLen-1;  index++)
      if  (array[index] > array[index+1])
      {
	exchange(array,index,index+1);
	haveExchanged	= 1;
      }

  }
  while  (haveExchanged);

  //  III.  Finished:

}
