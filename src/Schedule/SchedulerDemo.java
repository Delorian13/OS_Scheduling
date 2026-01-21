package Schedule;
// SchedulerDemo.java
// Author: Delorian Williams
// Purpose: FCFS and SJF (non-preemptive) scheduling with arrival times.
// This version avoids Streams/Collectors and packages for maximum compatibility.

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SchedulerDemo {

    // ---- Process model ----
    static class Proc {
        String id;
        int arrival;
        int completionProvided; // from prompt
        int burst;              // completion - arrival

        // results
        int start, finish, wait;

        Proc(String id, int arrival, int completionProvided) {
            this.id = id;
            this.arrival = arrival;
            this.completionProvided = completionProvided;
            this.burst = completionProvided - arrival;
        }

        Proc copy() {
            return new Proc(this.id, this.arrival, this.completionProvided);
        }
    }

    public static void main(String[] args) {
        // Input from the prompt
        List<Proc> input = new ArrayList<>();
        input.add(new Proc("P1", 0, 4));
        input.add(new Proc("P2", 1, 7));
        input.add(new Proc("P3", 2, 10));
        input.add(new Proc("P4", 3, 12));
        input.add(new Proc("P5", 4, 6));

        System.out.println("Derived Burst Times (completion - arrival):");
        for (Proc p : input) {
            System.out.println(p.id + ": arrival=" + p.arrival +
                               ", completion=" + p.completionProvided +
                               ", burst=" + p.burst);
        }
        System.out.println();

        // FCFS
        System.out.println("=== First Come, First Serve (FCFS) ===");
        List<Proc> fcfs = deepCopy(input);
        scheduleFCFS(fcfs);
        printSchedule(fcfs);
        System.out.printf("Average wait time (FCFS): %.2f%n%n", averageWait(fcfs));

        // SJF (non-preemptive)
        System.out.println("=== Shortest Job First (SJF, non-preemptive) ===");
        List<Proc> sjf = deepCopy(input);
        scheduleSJFNonPreemptive(sjf);
        printSchedule(sjf);
        System.out.printf("Average wait time (SJF): %.2f%n", averageWait(sjf));
    }

    // ---- Helpers ----
    private static List<Proc> deepCopy(List<Proc> src) {
        List<Proc> out = new ArrayList<>();
        for (Proc p : src) out.add(p.copy());
        return out;
    }

    private static void scheduleFCFS(List<Proc> procs) {
        // sort by arrival
        procs.sort(new Comparator<Proc>() {
            @Override public int compare(Proc a, Proc b) {
                return Integer.compare(a.arrival, b.arrival);
            }
        });
        int time = 0;
        for (Proc p : procs) {
            if (time < p.arrival) time = p.arrival;
            p.start = time;
            p.finish = p.start + p.burst;
            p.wait = p.start - p.arrival;
            time = p.finish;
        }
    }

    private static void scheduleSJFNonPreemptive(List<Proc> procs) {
        List<Proc> remaining = new ArrayList<>(procs);
        List<Proc> done = new ArrayList<>();
        int time = 0;

        while (!remaining.isEmpty()) {
            // gather available
            List<Proc> available = new ArrayList<>();
            for (Proc p : remaining) {
                if (p.arrival <= time) available.add(p);
            }

            if (available.isEmpty()) {
                // jump to next arrival
                int nextArrival = Integer.MAX_VALUE;
                for (Proc p : remaining) nextArrival = Math.min(nextArrival, p.arrival);
                time = nextArrival;
                continue;
            }

            // pick shortest burst (tie → earlier arrival)
            Proc chosen = available.get(0);
            for (Proc p : available) {
                if (p.burst < chosen.burst ||
                   (p.burst == chosen.burst && p.arrival < chosen.arrival)) {
                    chosen = p;
                }
            }

            chosen.start = time;
            chosen.finish = chosen.start + chosen.burst;
            chosen.wait = chosen.start - chosen.arrival;
            time = chosen.finish;

            remaining.remove(chosen);
            done.add(chosen);
        }

        // overwrite list with execution order
        procs.clear();
        procs.addAll(done);
    }

    private static double averageWait(List<Proc> procs) {
        int sum = 0;
        for (Proc p : procs) sum += p.wait;
        return procs.isEmpty() ? 0.0 : (sum * 1.0) / procs.size();
    }

    private static void printSchedule(List<Proc> procs) {
        System.out.printf("%-4s %-7s %-6s %-6s %-7s %-5s%n",
                "PID", "Arrive", "Burst", "Start", "Finish", "Wait");
        System.out.println("-------------------------------------------");
        for (Proc p : procs) {
            System.out.printf("%-4s %-7d %-6d %-6d %-7d %-5d%n",
                    p.id, p.arrival, p.burst, p.start, p.finish, p.wait);
        }
    }
}

