CS442 Design Patterns
Fall 2016
Assignment 5 README FILE

Due Date: Thursday, December 15, 2016
Submission Date: Friday, December 16, 2016
Grace Period Used This Project: 1 Day
Grace Period Remaining: 0 Days
Author: Charles DiGiovanna
e-mail: cdigiov1@binghamon.edu

PURPOSE:

To interpret java objects.

PERCENT COMPLETE:

100%

PARTS THAT ARE NOT COMPLETE:

N/A

BUGS:

N/A

SAMPLE OUTPUT:

(from console)

Firsts: 2
Unique Firsts: 1
Seconds: 2
Unique Seconds: 2

TO COMPILE:

ant all

TO RUN:

ant run -Darg0=<in-file> -Darg1=<debug-level>

i.e.
ant run -Darg0=input.txt -Darg1=0

EXTRA CREDIT:

N/A

BIBLIOGRAPHY:

N/A

ACKNOWLEDGEMENT:

N/A

DATA STRUCTURE JUSTIFICATION:

I used a red-black tree to store distinct words and their frequencies. The self-balancing in the tree guaranteed efficient O(log(n)) insertions and O(log(n)) lookups.
