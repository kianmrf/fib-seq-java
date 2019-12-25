/*=======================================================================
|   Source code:  LoopFibSequence.java
|
|         Class:  LoopFibSequence
|    Assignment:  Program #6 - Fibonnaci Sequence (Final Project)
|
|        Language:  Java
|     Compile/Run:
|           * To use this class run the FibDemo after compiling.
|          ** Make sure to include Sequence.java in the same directory
|             when compiling\running.
|
| 	               javac LoopFibSequence.java
|
|
|        Purpose:  This class is based on Sequence interface
|                  which has been implemented in Cay S. Horstmann,
|                  Big Java, Chapter 10 book's source code.
|                  It extended the Sequence interface and omptimized
|                  for sequencing Fibonacci numbers by using Loop and
|                  Iterative format. (Big Java: Chapter 13 Page 600-601)
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
|                  int FIRST_FIB_NO_ONE = 3, Indicates what is the first
|                  element in the series that does not have a 1 value.
|
| +-----------------------------------------------------------------------
|
|   Constructors:  - LoopFibSequence() : creates a LoopFibSequence object.
|                                        starting with first element of
|                                        Fibonacci series.
|                  - LoopFibSequence(int startFib) : creates an object
|                                    starting with the next elemnt after
|                                    the provided parameter startFib.
|
|  Class Methods:  - fib(int n) return (boolean)
|                    This private method returns the value for nth element
|                    of Fibonacci series by using the iterative method.
|
|  Instance Methods:
|                   - next() return (int)
|                     returns next Fibonnaci number in the series.
|
|*===========================================================================*/

public class LoopFibSequence implements Sequence
{

   private static final int FIRST_TWO = 2;
   private static final int FIRST_FIBS_VALUE = 1;
   // first fib element with a value other than one
   private static final int FIRST_FIB_NO_ONE = 3;

   private int currentFib = 0;

   /*------------------------- LoopFibSequence --------------------------
   |  Method LoopFibSequence()
   |
   |  Purpose:  Constructor, creates a LoopFibSequence object.
   |            starting with first element of Fibonacci series.
   |  @return  none
   *-------------------------------------------------------------------*/
   public LoopFibSequence()
   {
     currentFib = FIRST_FIBS_VALUE;
   } // end method
   /*------------------------- LoopFibSequence --------------------------
   |  Method LoopFibSequence(startFib)
   |
   |  Purpose:  Constructor, creates a LoopFibSequence object.
   |            starting with the next element in Fibonnci series after
   |            the startFib element.
   |
   |  @param   int startFib: indicates where sequence of the series
   |                          should begin.
   |  @return  none
   *-------------------------------------------------------------------*/
   public LoopFibSequence(int startFib)
   {
     // Constructor
     currentFib = startFib + 1;
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
     int nextFibVal = 0;
     nextFibVal = fib(currentFib);
     currentFib++;
     return nextFibVal;
   } // end method

   /*------------------------------- fib --------------------------------
   |  Method fib(n)
   |
   |  Purpose:  This provate method computes the n_th element in Fibonnci
   |            series using iteration and return it to the caller.
   |            It computes the following formula but iteratively:
   |
   |               Fib(n) = Fib(n-1) + Fib(n-2) & F(1) = F(2) = 1
   |             * Big Java: Chapter 13 Page 600-601 : In this procedure
   |               we start from the 3rd element in series and by using a
   |               loop we iterate through all of the elements untill it
   |               reaches the Nth element. This is quite faster than the
   |               recursive one.
   |
   |  @param   int n: Nth element of the series.
   |
   |  @return  int :  Fibonnaci value for Nth element.
   *-------------------------------------------------------------------*/
   private int fib(int n)
   {
     if (n <= FIRST_TWO)
     {
       return FIRST_FIBS_VALUE;
     } else {
       int olderValue = FIRST_FIBS_VALUE;
       int oldValue = FIRST_FIBS_VALUE;
       int newValue = FIRST_FIBS_VALUE;
       for (int index = FIRST_FIB_NO_ONE; index <= n; index++)
       {
         newValue = olderValue + oldValue;
         olderValue = oldValue;
         oldValue = newValue;
       }
       return newValue;
     }
   } // end method
} // end class
