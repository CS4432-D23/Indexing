# Indexing

Implementing a Simplified Version of Indexing in DBMSs

Name: William Huang
WPI ID: 876998229

## Section 1: How to compile and execute my code  

1. Put data blocks (files) in the `Project2Dataset` directory
2. Run the `main` class
3. Type the command you like to run

If a index is made, they should be stored in the `Memory` class as Array of String and a HashMap<Integer, ArrayList<String>>

Here are a list to commands to run:  
`CREATE INDEX ON Project2Dataset (RandomV)`  
`SELECT * FROM Project2Dataset WHERE RandomV = {v}` where `v` is a RandomV  
`SELECT * FROM Project2Dataset WHERE RandomV > {v1} AND RandomV < {v2}` where `v1` and `v2` are a range of RandomV's  
`SELECT * FROM Project2Dataset WHERE RandomV != {v}` where `v` is a RandomV  

## Section 2: Test Results

Commands should work as intended

## Section 3: Additional Design Decisions

There were some things I implemented in the program that were beyond the design guidelines. Some of which included:

- A `EXIT` command which breaks the while loop in the main class and exits the program
- I made the 'Memory' class into a singleton to avoid multiple instances of it
