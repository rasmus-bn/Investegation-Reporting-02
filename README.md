# Investigation & Reporting 02 - Letter frequencies
I was given an algorithm for which I was tasked to optimize with regards to time.

## How I measured the performance.
In my program I have a loop over each method 100 times. I ran the program 3 times on 2 different computers and I ran it from the console the console.
The code blocks where I get the time only includes what is used for that specific algorithm so ex. printing and outputting was not included in the time calculation. Collected the data form the 3 runs in each box-plot diagram to better show the data.

## Bottlenecks
I believe the biggest bottlenecks in the program are reading the file and looping through every character.

## Why I think the algorithm is slow.
I think the algorithm has to things that slows it down:
1. It uses FileReader which is not buffered so reads directly from file every time read() is called. 
2. The read() method returns an int which is the stores as a long which is quite unnecessary.

## My solution
I tried 2 different approaches.

### 1st approach
Wrapped the FileReader in a BufferedReader and changed the HashMap from *<Integer, Long>* to *<Integer, Integer>*.

### 2nd approach
I wondered if there was a solution using a lambda expression and there was. I used *Files.lines(Paths.get(fileName)).forEach()* and passed in the tallyChars3 method.

### Measurements
Desktop (with an SSD)
![alt text](https://raw.githubusercontent.com/rasmus-bn/Investegation-Reporting-02/master/files/desktop-box-plot.png)
Laptop (with HDD)
![alt text](https://raw.githubusercontent.com/rasmus-bn/Investegation-Reporting-02/master/files/laptop-box-plot.png)

The diagram shows improvements in time for the last 2 algorithms. The diagram shows greater improvement on laptop than on the desktop. This is likely due to the type of drive types in the PCs.