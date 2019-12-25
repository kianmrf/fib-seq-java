/*=======================================================================
|   Source code:  UserInvalidValueException.java
|
|         Class:  UserInvalidValueException
|    Assignment:  Program #6 - Fibonnaci Sequence (Final Project)
|
|        Language:  Java
|     Compile/Run:
|           * To use a demo of this class run FibDemo after compiling.
| 	               javac UserInvalidValueException.java
|
|
|        Purpose:  This class is extended IllegalArgumentException which
|                  is a Java API exception.
|                  Big Java, Chapter 10 book's source code.
|                  It extended the Sequence interface and omptimized
|                  for sequencing Fibonacci numbers using the fast recursive
|                  methods.
|
|  Inherits From:  java.lang.IllegalArgumentException
|
|     Interfaces:  None.
|
|
| +-----------------------------------------------------------------------
|
|      Constants:  None.
|
| +-----------------------------------------------------------------------
|
|   Constructors:  - UserInvalidValueException() : Contructs the excpetion
|                           object without a message.
|                  - UserInvalidValueException(String msg) : Constructs
|                           the excpetion with custom message provided as
|                           the paramter msg.
|
|  Class Methods:      None.
|
|  Instance Methods:   None.
|
|*===========================================================================*/
import java.lang.IllegalArgumentException;

public class UserInvalidValueException extends IllegalArgumentException
{

  /*-------------------- UserInvalidValueException ----------------------
  |  Method UserInvalidValueException()
  |
  |  Purpose:  Constructor, creates a UserInvalidValueException without
  |            any specific exception messages.
  |  @return  none
  *-------------------------------------------------------------------*/
   public UserInvalidValueException()
   {
     // Constructor
   } // end method

   /*-------------------- UserInvalidValueException ----------------------
   |  Method UserInvalidValueException(msg)
   |
   |  Purpose:  Constructor, creates a UserInvalidValueException with
   |            the provided string parameter as the exception message.
   |
   |  @param   String msg : provided excpetion message
   |  @return  none
   *-------------------------------------------------------------------*/
   public UserInvalidValueException(String msg)
   {
     super(msg);
   } // end method

} // end class
