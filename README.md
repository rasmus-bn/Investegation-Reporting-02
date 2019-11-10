# Investigation & Reporting 02 - Letter frequencies

## How I measured the performance.
In my program I have a loop over each method 100 times. I ran the program 3 times on 2 different computers and I ran it from the console the console.
The code blocks where I get the time only includes what is used for that specific algorithm so ex. printing and outputting was not included in the time calculation. Collected the data form the 3 runs in each box-plot diagram to better show the data.

## Bottlenecks
I believe the biggest bottlenecks in the program are reading the file and looping through every character.

## Why I think the algorithm is slow.
I think the algorithm has to things that slows it down:
1. It uses FileReader which is not buffered so reads directly from file every time read() is called. 
2. The read() method returns an int which is the stores as a long which is quite unnecessary.






