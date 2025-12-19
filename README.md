# 🔋 Battery Runtime Optimization

This repository contains a Java-based solution for optimizing the execution runtime of a battery-powered wearable device by intelligently managing battery charging and task execution.

---

## 📌 Problem Overview

A wearable device executes a sequence of tasks in a fixed order.  
Each task consumes battery at a constant rate, and between tasks, the device may enter an idle state to recharge.

### Constraints
- Tasks must be executed in order  
- Battery level must never go below **0**  
- Battery level must never exceed **maximum capacity**  
- Charging is allowed only during idle time  

### 🎯 Objective
Calculate the **minimum total runtime** (task execution time + charging time).  
If execution is not possible under constraints, return **-1**.

---

## ⚙️ Input Parameters

- **Battery Capacity (mAh)**
- **Initial Battery Level (mAh)**
- **Charging Rate (mAh per second)**
- **List of Tasks**, where each task contains:
  - Duration (seconds)
  - Drain Rate (mAh per second)

---

## 🧠 Key Idea (Greedy Strategy)

- Charging earlier than required increases total runtime  
- Charging more than required wastes time  
- **Optimal strategy:** charge only when necessary and only the minimum required amount  

This greedy approach guarantees the shortest possible runtime.

---

## 🔁 Flowchart

The flowchart below illustrates the complete decision process for battery charging and task execution:

![Battery Runtime Flowchart](https://github.com/Ranaprince19/Battery-Runtime-Optimization/blob/main/Battery_Flowchart.png)

**Flowchart Caption:**  
This flowchart represents a greedy algorithm that conditionally charges the battery and executes tasks sequentially to minimize total runtime under capacity constraints.

---
## 🧩 Pseudocode

```text
// Initialize battery and time
currentBattery = initialBattery
totalTime = 0

// Execute tasks one by one
for each task in tasks:

    // Calculate battery required for the task
    requiredBattery = duration * drainRate

    // If task cannot be executed even with full battery
    if requiredBattery > batteryCapacity:
        return -1

    // Charge battery only if insufficient
    if currentBattery < requiredBattery:
        neededCharge = requiredBattery - currentBattery
        idleTime = neededCharge / chargeRate
        currentBattery += neededCharge
        totalTime += idleTime

    // Execute the task
    currentBattery -= requiredBattery
    totalTime += duration

// Return final runtime rounded to one decimal place
return round(totalTime, 1)

```

Tasks:

10 sec, 5 mAh/sec

5 sec, 4 mAh/sec

8 sec, 6 mAh/sec


### Step-by-Step Execution

**Task 1**
- Required Battery = 10 × 5 = 50
- Current Battery = 20 → charge 30
- Charging Time = 30 / 5 = 6 sec
- Execute Task = 10 sec  
- Total Time = 16 sec

**Task 2**
- Required Battery = 5 × 4 = 20
- Current Battery = 0 → charge 20
- Charging Time = 4 sec
- Execute Task = 5 sec  
- Total Time = 25 sec

**Task 3**
- Required Battery = 8 × 6 = 48
- Current Battery = 0 → charge 48
- Charging Time = 9.6 sec
- Execute Task = 8 sec  
- Total Time = **42.6 sec**

### ✅ Final Output
42.6


---

## ⏱ Complexity Analysis

- **Time Complexity:** O(n)  
- **Space Complexity:** O(1)

---

## 🛠️ Technology Stack

- Java
- VS Code
- Git & GitHub

---

## 🏁 Conclusion

This project demonstrates how real-world system constraints can be efficiently solved using a greedy algorithm, resulting in a clean, optimal, and easily explainable solution suitable for firmware-level optimization problems.

---




