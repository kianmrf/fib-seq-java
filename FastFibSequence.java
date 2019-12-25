/*=======================================================================
|   Source code:  FastFibSequence.java
|
|         Class:  FastFibSequence
|    Assignment:  Program #6 - Fibonnaci Sequence (Final Project)
|
|        Language:  Java
|     Compile/Run:
|           * To use this class run the FibDemo after compiling.
|          ** Make sure to include Sequence.java in the same directory
|             when compiling\running.
|
| 	               javac FastFibSequence.java
|
|
|        Purpose:  This class is based on Sequence interface
|                  which has been implemented in Cay S. Horstmann,
|                  Big Java, Chapter 10 book's source code.
|                  It extended the Sequence interface and omptimized
|                  for sequencing Fibonacci numbers using the fast recursive
|                  methods.
|
|  Inherits From:  Sequence
|
|
|     Interfaces:  Sequence
|
|
| +-----------------------------------------------------------------------
|
|      Constants:  int FIRST_FIBS_VALUE = 1, this is the value for the
|                  first two Fibonnaci numbers.
|
| +-----------------------------------------------------------------------
|
|   Constructors:  - FastFibSequence() : creates a FastFibSequence object.
|                                    starting with first element of
|                                    Fibonacci series.
|                  - FastFibSequence(int startFib) : creates a Seqeunce
|                                    starting with the next elemnt after
|                                    the provided parameter startFib.
|
|  Class Methods:  - fib(int n) return (boolean)
|                    This private method returns the value for nth element
|                    of Fibonacci series using fast recursion.
|
|  Instance Methods:
|                   - next() return (int)
|                     returns next Fibonnaci number in the series.
|
|*===========================================================================*/

import java.util.ArrayList; /* to store found Fibs for search optimization */

public class FastFibSequence implements Sequence
{

   private static final int FIRST_TWO = 2;
   private static final int FIRST_FIBS_VALUE = 1;

   private ArrayList<Integer> foundFibs = new ArrayList<Integer>();
   private int currentFib = 0;

   /*------------------------- FastFibSequence --------------------------
   |  Method FastFibSequence()
   |
   |  Purpose:  Constructor, creates a FastFibSequence object.
   |            starting with first element of Fibonacci series.
   |           + Also adds the first Fib element to the foundFibs array.
   |  @return  none
   *-------------------------------------------------------------------*/
   public FastFibSequence()
   {
     currentFib = FIRST_FIBS_VALUE;
     foundFibs.add(FIRST_FIBS_VALUE);
   } // end method
   /*------------------------- FastFibSequence --------------------------
   |  Method FastFibSequence(startFib)
   |
   |  Purpose:  Constructor, creates a FastFibSequence object.
   |            starting with next element of Fibonacci series after the
   |            provided startFib element.
   |           + Also adds the first Fib element to the foundFibs array.
   |
   |  @param   int startFib : indicates where sequence of the series
   |                          should begin.
   |  @return  none
   *-------------------------------------------------------------------*/
   public FastFibSequence(int startFib)
   {
     currentFib = startFib + 1;
     foundFibs.add(FIRST_FIBS_VALUE);
   } // end method
   /*------------------------------- next -------------------------------
   |  Method next()
   |
   |  Purpose:  Iterator method. It returns the next Fibonnaci number
   |            in the series by utilzing the fib(n) method.
   |
   |  @return  int nextFibVal : next Fibonnci number value
   *-------------------------------------------------------------------*/
   public int next()
   {
     int nextFibVal = fib(currentFib);
     currentFib++;
     return nextFibVal;
   } // end method
   /*------------------------------- fib --------------------------------
   |  Method fib(n)
   |
   |  Purpose:  This provate method computes the n_th element in Fibonnci
   |            series recursively and return it to the caller.
   |            It computes the following formula but recursively:
   |
   |               Fib(n) = Fib(n-1) + Fib(n-2) & F(1) = F(2) = 1
   |            * The only diffrence with the normal FibSequence is that
   |              in this method found Fibonnaci values will be all saved
   |              to the foundFibs array of integers. Also before it
   |              computes the Nth element will check if it exists in the
   |              array to be more effcient and save time. This method is
   |              extremely faster than original recursive one.
   |
   |  @param   int n: Nth element of the series.
   |
   |  @return  int :  Fibonnaci value for Nth element.
   *-------------------------------------------------------------------*/
   private int fib(int n)
   {
     int fibVal = 0;
     if (n <= foundFibs.size())
     {
       fibVal = foundFibs.get(n-1);
       return fibVal;
     } else {
       if (n <= FIRST_TWO)
       {
         fibVal = FIRST_FIBS_VALUE;
         foundFibs.add(fibVal);
         return fibVal;
       } else {
         fibVal =  fib(n - 1) + fib(n - 2);
         foundFibs.add(fibVal);
         return fibVal;
       }
     }

   } // end method
} // end class
