import java.util.*;

public class BatteryRuntimeOptimizer {

    /**
     * Returns the minimum total runtime to execute all tasks.
     * If impossible, returns -1.
     */
    public static double minimumTotalRuntime(
            double batteryCapacity,
            double initialBattery,
            int[][] tasks,
            double chargeRate
    ) {
        double currentBattery = initialBattery;
        double totalTime = 0.0;

        for (int i = 0; i < tasks.length; i++) {
            int duration = tasks[i][0];
            int drainRate = tasks[i][1];

            double requiredBattery = duration * drainRate;

            // Impossible case
            if (requiredBattery > batteryCapacity) {
                return -1;
            }

            // Charge only if needed
            if (currentBattery < requiredBattery) {
                double neededCharge = requiredBattery - currentBattery;
                double idleTime = neededCharge / chargeRate;

                totalTime += idleTime;
                currentBattery += neededCharge;
            }

            // Execute task
            currentBattery -= requiredBattery;
            totalTime += duration;
        }

        // Round to one decimal place
        return Math.round(totalTime * 10.0) / 10.0;
    }

    // -------- Main Method (Named Input) --------
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter battery capacity (mAh): ");
        double batteryCapacity = sc.nextDouble();

        System.out.print("Enter initial battery level (mAh): ");
        double initialBattery = sc.nextDouble();

        System.out.print("Enter charging rate (mAh per second): ");
        double chargeRate = sc.nextDouble();

        System.out.print("Enter number of tasks: ");
        int n = sc.nextInt();

        int[][] tasks = new int[n][2];

        for (int i = 0; i < n; i++) {
            System.out.print(
                "Enter duration (seconds) and drain rate (mAh/sec) for task " + (i + 1) + ": "
            );
            tasks[i][0] = sc.nextInt(); // duration
            tasks[i][1] = sc.nextInt(); // drain rate
        }

        double result = minimumTotalRuntime(
                batteryCapacity,
                initialBattery,
                tasks,
                chargeRate
        );

        System.out.println("\nMinimum Total Runtime: " + result);
        sc.close();
    }
}
