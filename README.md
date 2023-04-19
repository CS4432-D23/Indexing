# Indexing

Implementing a Simplified Version of Indexing in DBMSs

## Section 1: How to compile and execute my code  

1. Put data blocks (files) in the `Project2Dataset` directory
2. Run the `main` class
3. Type the command you like to run

SOME NOTEs

## Section 2: Test Results

All of the test cases in the `testcase_commands_and_output.txt` work as intended

## Section 3: Additional Design Decisions

There were some things I implemented in the program that were beyond the design guidelines. Some of which included:

- A `SHOW` command which list of all current frames, their blockID's, pinned and dirty flag. This was for mostly debugging purposes to show what was currently in the buffer
- A `EXIT` command which writes any blocks to disk (with dirty flag = true) and exits the program. This was created for a way to exit the program and save the data in the buffer
- In the `BufferPool` class, I added a `numPinned` class variable and the `updateNumberOfPins()` method which never gets used (you can find them in the `BufferPool` class)
- I also made the 'BufferPool' class into a singleton to avoid multiple instances of it

If I had more time, I would of probably implemented LRU and Clock method for evicting frames. Would of made a bitmap or hashmap of the frames to improve the time complexity.