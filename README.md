# OS Scheduling Simulator

## Overview

The OS Scheduling Simulator is a Java program that demonstrates how operating systems schedule processes using common CPU scheduling algorithms.

This project simulates process scheduling using **First Come First Serve (FCFS)** and **Shortest Job First (SJF)** scheduling algorithms with arrival times. It calculates important scheduling metrics such as start time, finish time, and waiting time for each process.

The goal of this project was to practice operating system concepts and implement scheduling algorithms in Java.

---

## Scheduling Algorithms Implemented

### First Come First Serve (FCFS)

Processes are executed in the order they arrive. The CPU schedules the first process that enters the ready queue.

### Shortest Job First (SJF) – Non-Preemptive

Processes are scheduled based on the shortest burst time among the processes that have arrived. Once a process starts executing, it runs until completion.

---

## Features

* Simulates process scheduling
* Supports arrival times
* Implements multiple scheduling algorithms
* Calculates scheduling metrics:

  * Start Time
  * Finish Time
  * Waiting Time
* Object-oriented process model
* Console-based output

---

## Technologies Used

* Java
* Object-Oriented Programming
* Java Collections (ArrayList, List)
* Comparator sorting

---

## Program Structure

```
src
└── Schedule
    └── SchedulerDemo.java
```

### Key Classes

**Proc**

Represents a process in the system.

Attributes include:

* Process ID
* Arrival Time
* Burst Time
* Start Time
* Finish Time
* Waiting Time

**SchedulerDemo**

Main class that runs the scheduling simulation and demonstrates the FCFS and SJF algorithms.

---

## How to Run

1. Clone the repository

git clone https://github.com/Delorian13/OS_Scheduling.git

2. Open the project in an IDE

Recommended IDEs:

* Eclipse
* IntelliJ IDEA
* VS Code

3. Run the program

Run the file:

```
SchedulerDemo.java
```

The console will display the scheduling results for the processes.

---

## Example Output

Example scheduling results:

```
Process  Arrival  Burst  Start  Finish  Wait
P1       0        5      0      5       0
P2       1        3      5      8       4
P3       2        2      8      10      6
```

---

## What I Learned

Through this project I improved my understanding of:

* CPU scheduling algorithms
* Process management concepts in operating systems
* Algorithm implementation in Java
* Using lists and comparators for scheduling logic
* Designing small simulation programs

---

## Future Improvements

* Create a **visual scheduling timeline (Gantt chart)**
* Add user input for processes
* Convert the simulator to a graphical interface

---

## Author

Delorian Williams
Computer Science Student
Aspiring Software Engineer
