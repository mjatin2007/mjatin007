#include	"sortHeader.h"

//  PURPOSE:  To sort the 'arrayLen' integers in array 'array[]' in ascending
//	order according to the insertion-sort algorithm.  No return value.
void		insertionSort	(int		arrayLen,
				 int*		array
				)
{
  //  I.  Application validity check:

  //  II.  Sort 'array[]':
  //int	arrayLenMinusOne	= arrayLen-1;

  //  II.A.  Each iteration finds the smallest number from the remaining
  // 	     (upper) portion of the array and places it at 'outerIndex':
  int	innerIndex;
  int	outerIndex;

  for  (outerIndex = 0;  outerIndex < arrayLen-1;  outerIndex++)
    for  (innerIndex = outerIndex+1;  innerIndex < arrayLen;  innerIndex++)
      if  (array[outerIndex] > array[innerIndex])
	exchange(array,outerIndex,innerIndex);

  //  III.  Finished:

}
