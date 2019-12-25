/*=============================================================================
|   Source code:  FibDemo.java
|    Assignment:  Program #6 - FibDemo (Final Project)
|
|          Language:  Java
|       Compile/Run:
|         * in order to run the demo program, make sure every required class
|           files including, FibSequence.java, LoopFibSequence.java,
|           FastFibSequence, Sequence.java, UserInvalidValueException.java,
|           are in placed into the same directory (folder) when running
|           following commands.
|        ** Also make sure to place the input file as expected style in the
|            same directory (folder) before running.
|       *** When running the compiled demo, make sure to include an input &
|             output filenames as following in command-line.
|
| 	              javac FibDemo.java FibSequence.java FastFibSequence.java
|                        LoopFibSequence.java Sequence.java
|                        UserInvalidValueException.java
|	                java FibDemo input_filename output_filename
|
|+-----------------------------------------------------------------------------
|
|  Description:  This program is a demo program for implemneted custom Fibonacci
|                Sequence classes. It will use those classes which are defined
|                based on the Sequence Interface provided in Wiley's Big Java:
|                Object Oriented prgramming textbook. It will read the user
|                input as a file which is expected by user to be provided when
|                running the program. Then by using three custom Fibonnaci
|                sequences, it will generate three tables and writes them to
|                the output file. Output & input file names must be Included
|                in the command-line script as arguments when running the demo.
|                The output will be three tables of generated Fibonnaci numbers
|                based on given values as input by the user. output tables will
|                include the running time of each different Fibonnaci sequence.
|
|
|
|        Input:  User will be provding an input file which needs to be placed
|                in the same directory as Demo and other classes when running.
|                There should be ONLY two integers in the input file seperated
|                by a new line. The first integer in the input file dictates
|                where the Fibonacci sequence will start, beginning with the
|                next Fibonacci number. The second integer in the input file
|                will be the number of Fibonacci numbers to be displayed in
|                the tables. The integer values in the input file will be
|                [0-12] & [1-36], respectively, and inclusive. Errors may occur
|                if provided values do not meet the requirements.
|
|
|       Output:  The program will ouput three tables, each containing a series
|                of Fibonacci series (all three must have same values) but
|                every table is computed with a different Fibonnaci Sequence
|                class. Every table will also icnlude the required running time
|                taken in order to produce that specific table. The output
|                containing the tables and run-times will not be shown on the
|                command-line, instead, it will be written to an output file
|                which user will provide its name when running the Demo.
|                Although tables are not showing on command-line, errors and
|                other necesssary information regarding the process might be
|                going on command-line to notify the user about the process.

|                * EXTRA CREDIT: Program will check if an output file with the
|                  provided name is already exist or not. In case of exisiting,
|                  it will OVER WRITE the file.
|
|      Process:  The program's steps are as follows:
|
|                      1.  The program will get two command-line arguments
|                          when runs. The first one will be the provided input
|                          filename and the second will be the output.
|                      2.  Then it will process the input file.
|                      3.  Three different Fibonnaci Sequences will run based
|                          on processed input. Run-time being measured at the
|                          same time.
|                      4.  Output tables will be written to the output file
|
|
|                No particular algorithms are used. The Java IO package
|                is accessed to perform console I/O and thus avoid use
|                of a third-party I/O package.
|
|   Required Features Not Included:  None.
|
|   Known Bugs:  None.
|
|*===========================================================================*/


import java.io.File;
import java.io.IOException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner; /* to read command-line arguments */
import java.lang.Math; /* to round up values */
import java.lang.ArithmeticException;

public class FibDemo
{
   private static final int ARGS_DEFAULT = 2; /* default number of cmd arguments */
   // First Integer Range Constants
   private static final int MIN_START_FIB = 0;
   private static final int MAX_START_FIB = 12;
   // Second Integer Range Constants
   private static final int MIN_COUNT_FIB = 1;
   private static final int MAX_COUNT_FIB = 36;
   // Max wait time before letting user know how long the task is taking
   private static final long MAX_WAIT_TIME = 1500000000;
   private static final double NANO = 1e9d;
   // Maximum value for a Fibonnaci number before integer overflow
   private static final int MAX_FIB_BEFORE_OF = 1836311903;
   // Following is the measured worst run time for recursive sequence in seconds
   private static final int WORST_RUN_TIME = 45;


   public static void main(String[] args) throws IOException
   {
     if (args.length != ARGS_DEFAULT)
     {
       showUsage();
     } else {
       int startFib = 0;
       int countFib = 0;
       String inFileName = args[0];
       String outFileName = args[1];
       try
       {
         File inFile = new File(inFileName);
         Scanner inputScanner = new Scanner(inFile);
         try
         {
           int[] inputValues = readInputFile(inputScanner);
           startFib = inputValues[0];
           countFib = inputValues[1];
           getTables(startFib, countFib, outFileName);
         } finally
         {
           inputScanner.close();
         }
       } catch (FileNotFoundException exception)
       {
         System.out.println("Error: Input File Not Found!");
         exception.printStackTrace();
         showUsage();
       } catch (EOFException exception)
       {
         System.out.println(exception.getMessage());
         exception.printStackTrace();
       } catch (UserInvalidValueException exception)
       {
         System.out.println(exception.getMessage());
         exception.printStackTrace();
       }
     }
   } // end main

   /*-------------------------- readInputFile ---------------------------
   |  Method readInputFile(input)
   |
   |  Purpose: Processes the provided input file and read the expected
   |           two integer values. It will throw expcetions in case of
   |           wrong input values, empty file, or invalid number of
   |           arguments in input file. At the end it will return an
   |           integer array of size 2 containing the read values.
   |
   |  @param   Scanner input : read the provided input file.
   |
   |  @return  int[] read values from input file.
   |
   *-------------------------------------------------------------------*/
   private static int[] readInputFile(Scanner input) throws IOException
   {
     int[] readValues = new int[2];
     if (!input.hasNext())
     {
       throw new EOFException("Error :Input File is Empty, Please Provide a valid input\n");
     } else {
       readValues[0] = readIntInput(input);
       if (readValues[0] > MAX_START_FIB || readValues[0] < MIN_START_FIB)
       {
         throw new UserInvalidValueException("Error :First value must be an integer in [0-12] inclusive\n");
       }
       //inputScanner.next();
       readValues[1] = readIntInput(input);
       if (readValues[1] > MAX_COUNT_FIB || readValues[1] < MIN_COUNT_FIB)
       {
         throw new UserInvalidValueException("Error: Second value must be an integer in [1-36] inclusive\n");
       }
       if (input.hasNext())
       {
         throw new EOFException("Error: Invalid amount of argument values in input file.\n");
       }
     }
     return readValues;

   }

   /*----------------------------- getTables -----------------------------
   |  Method getTables(start, count, outFileName)
   |
   |  Purpose: Prepares the output file and calls the other method to write
   |           the tables to output. It will store the run-time of each
   |           table in an array of size 3.
   |         + It will also check if an output file with the specified name
   |           exists, in that case it will handle the excpetion and
   |           OVER WRITE the output file.
   |
   |  @param   int start : indicates where each sequence should start
   |           int count : indicates how many values will be sequenced
   |           String outFileName : name for the output file
   |
   |  @return  None.
   |
   *-------------------------------------------------------------------*/
   private static void getTables(int start, int count, String outFileName) throws IOException
   {
     long[] runTimesNano = new long[3];
     File outFile = new File(outFileName); // check if exists
     try
     {
       if (outFile.exists())
       {
         throw new IOException("Overwriting exisiting output file\n");
       } else {
         outFile.createNewFile();
       }
     } catch (IOException exception)
     {
       System.out.println(exception.getMessage());
     }
     try
     {
       PrintWriter output = new PrintWriter(outFile);
       try
       {

         FibSequence fib1 = new FibSequence(start);
         FastFibSequence fib2 = new FastFibSequence(start);
         LoopFibSequence fib3 = new LoopFibSequence(start);


         output.printf("\nIterative - Expected\n");
         runTimesNano[0] = writeTableToFile(fib3, count, output);
         output.printf(String.format("Time to compute: %,d nanoseconds, %.4f seconds\n"
                      , runTimesNano[0], runTimesNano[0]/NANO));

         output.printf("\nNormal Recursive\n");
         runTimesNano[1] = writeTableToFile(fib1, count, output);
         output.printf(String.format("Time to compute: %,d nanoseconds, %.4f seconds\n"
                      , runTimesNano[1], runTimesNano[1]/NANO));

         output.printf("\nFast Recursive\n");
         runTimesNano[2] = writeTableToFile(fib2, count, output);
         output.printf(String.format("Time to compute: %,d nanoseconds, %.4f seconds\n"
                      , runTimesNano[2], runTimesNano[2]/NANO));
       } finally
       {
         output.close();
       }
     } catch (IOException exception)
     {
       exception.printStackTrace();
     }
   } // end method

   /*------------------------- writeTableToFile --------------------------
   |  Method writeTableToFile(fibSeq, count, out)
   |
   |  Purpose: Uses the provided sequence to get Fibonnaci numbers as many
   |           times as count describes. It will record the time data to
   |           computed the running-time after each the loop. At the same
   |           time inside the loop, values will be written to the ouput
   |           by using the passed PrintWriter as a 'squarish table'.
   |           + It will notify the user if a loop is taking a long time,
   |             by exposing the worst case running time.
   |           ++ It will detect integer Arithmetic Overflow and instead
   |             of writing overflow values will write '**********' in
   |             the table. We already computed a series of Fibonnaci to
   |             observe what is the last element in the series before
   |             the Integer overflows. We use that value as a trigger for
   |             noticing the overflow in our demo program.
   |
   |  @param   Sequence passed Fibonnaci Sequence
   |           int  count of how many Fibonnaci numbers in table
   |           PrintWriter out  for writing to output file
   |
   |  @return long run-time in nanoseconds
   |
   *-------------------------------------------------------------------*/
   private static long writeTableToFile(Sequence fibSeq, int count, PrintWriter out)
   {
     // Squarish Table
     int columns = (int) Math.ceil(Math.sqrt(count));
     int rows = columns;
     int remainCount = count;
     int fibValue = 0;
     boolean longWait = false;
     boolean overflowStatus = false;
     long startTime = System.nanoTime();
     for (int row = 0; row < rows && remainCount != 0; row++)
     {
       for (int col = 0; col < columns && remainCount != 0; col++)
       {
         if (((System.nanoTime() - startTime) >= MAX_WAIT_TIME) && !longWait)
         {
           longWait = true;
           System.out.println(String.format(
            "This process may take up to %d seconds to finish\n", WORST_RUN_TIME));
         }
         try
         {
           fibValue = fibSeq.next();
           if (overflowStatus)
           {
             out.printf(String.format("%11s","**********"));
             remainCount--;
             throw new ArithmeticException("Arithmetic Overflow occured while sequencing\n");
           } else {
             out.printf(String.format("%11d", fibValue));
             remainCount--;
           }
         } catch (ArithmeticException exception)
         {
           System.out.println(exception.getMessage());
         }
         if (fibValue == MAX_FIB_BEFORE_OF)
         {
           overflowStatus = true;
         }
       }
       out.printf("\n");
     }
     return (System.nanoTime() - startTime);

   } // end method

   /*--------------------------- readIntInput ----------------------------
   |  Method readIntInput(inputScanner)
   |
   |  Purpose: Reads and returnsthe integer values from input file Scanner.
   |           It will thrwo a UserInvalidValueException in case of values
   |           not being integer or not exisiting.
   |
   |  @param   Scanner input file scanner
   |
   |  @return  int  read value from input
   |
   *-------------------------------------------------------------------*/
   private static int readIntInput(Scanner inputScanner) throws UserInvalidValueException
   {
     int intValue = 0;

     if (!inputScanner.hasNextInt())
     {
       throw new UserInvalidValueException("Invalid Input Values\n"+
              "Input file must contain two postivie integers spereated by"+
              " a new line. Their range is as follows [0-12] & [1-36]\n");
     } else {
       intValue = inputScanner.nextInt();
     }
     return intValue;
   } // end method
   /*---------------------------- showUsage -----------------------------
   |  Method showUsage()
   |
   |  Purpose:  Prints the usage for this program. This method is being
   |            called in case of an invalid command-line arguments.
   |            Program will exit using System.exit(1) after prompting.
   |
   |  @return  none
   |
   *-------------------------------------------------------------------*/
   private static void showUsage()
   {
     System.out.println("Invalid command-line arguments!");
     System.out.println("Enter valid input and output filenames.");
     System.out.println("\t-> Usage: java FibDemo 'input' 'output'");
     System.exit(1);
   } // end method
} // end class
