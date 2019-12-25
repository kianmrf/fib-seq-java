/*=======================================================================
|   Source code:  FibSequence.java
|
|         Class:  FibSequence
|    Assignment:  Program #6 - Fibonnaci Sequence (Final Project)
|
|        Language:  Java
|     Compile/Run:
|           * To use this class run the FibDemo after compiling.
|          ** Make sure to include Sequence.java in the same directory
|             when compiling\running.
|
| 	               javac FibSequence.java
|
|
|        Purpose:  This class is based on Sequence interface
|                  which has been implemented in Cay S. Horstmann,
|                  Big Java, Chapter 10 book's source code.
|                  It extended the Sequence interface and omptimized
|                  for sequencing Fibonacci numbers (recursively).
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
|   Constructors:  - FibSequence() : creates a FibSequence object.
|                                    starting with first element of
|                                    Fibonacci series.
|                  - FibSequence(int startFib) : creates a FibSequence
|                                    starting with the next elemnt after
|                                    the provided parameter startFib.
|
|  Class Methods:  - fib(int n) return (boolean)
|                    This private method returns the value for nth element
|                    of Fibonacci series recursively.
|
|  Instance Methods:
|                   - next() return (int)
|                     returns next Fibonnaci number in the series.
|
|*===========================================================================*/

public class FibSequence implements Sequence
{

   private static final int FIRST_TWO = 2;
   private static final int FIRST_FIBS_VALUE = 1;

   private int currentFib = 0;

   /*--------------------------- FibSequence ----------------------------
   |  Method FibSequence()
   |
   |  Purpose:  Constructor, creates a FibSequence object.
   |            starting with first element of Fibonacci series.
   |  @return  none
   *-------------------------------------------------------------------*/
   public FibSequence()
   {
     currentFib = FIRST_FIBS_VALUE;
   } // end method
   /*--------------------------- FibSequence ----------------------------
   |  Method FibSequence(startFib)
   |
   |  Purpose:  Constructor, creates a FibSequence object.
   |            starting with the next element in Fibonnci series after
   |            the startFib element.
   |
   |  @param   int startFib : indicates where sequence of the series
   |                          should begin.
   |  @return  none
   *-------------------------------------------------------------------*/
   public FibSequence(int startFib)
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
   |            series recursively and return it to the caller.
   |            It computes the following formula but recursively:
   |
   |               Fib(n) = Fib(n-1) + Fib(n-2) & F(1) = F(2) = 1
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
       return fib(n - 1) + fib(n - 2);
     }
   } // end method
} // end class
