# History_Grading_Pattern

# Description
A history professor likes to give exams in which students are asked to put several historical events into chronological
order. Students who order all the events correctly will receive full credit, but how should partial credit be awarded to
students who incorrectly rank one or more of the historical events?
After considering various options, the professor decides to give students 1 point for each event in the longest (not
necessarily contiguous) sequence of events which are in the correct order relative to each other. For example, if four
events are correctly ordered 1 2 3 4 then the order 1 3 2 4 would receive a score of 3 (event sequences 1 2 4 and 1 3 4
are both in the correct order relative to each other). Your task is to implement a program that applies this grading
strategy.

The problem can be solved using the hidden patterns dynamic programming approach studied in class (except that,
instead of patterns of individual characters, your program will analyze sequences of strings, with each string
representing a historical event).

Your assignment is to write a Java class named Patterns which provides public methods with the following signatures
and functionality:

Patterns(String filename): constructor to process a file containing the historical events in correct order, with one
event per line, as shown below. The first line contains an integer representing the number of events.
int grade(String filename): accepts a file containing a student’s responses (same format as before) and computes
the score (length of longest sequence of events in correct relative order).
String pattern(String filename): accepts a file containing a student’s responses (same format as before) and
returns a String containing the events that make up the longest sequence in the correct order, separated by commas.
You should initially test your methods using the PatternsTest class and sample text files provided. 
