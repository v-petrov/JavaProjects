package SSA.tasks;

import java.util.Arrays;

public class TaskDeadlines {

    public static class Task implements Comparable<Task> {
        int duration;
        int deadline;

        public Task(int duration, int deadline) {
            this.duration = duration;
            this.deadline = deadline;
        }

        @Override
        public int compareTo(Task o) {
            return this.duration - o.duration;
        }
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[3];
        tasks[0] = new Task(6, 10);
        tasks[1] = new Task(8, 15);
        tasks[2] = new Task(5, 12);

        Arrays.sort(tasks);

        System.out.println(maxReward(tasks));
    }

    private static int maxReward(Task[] tasks) {
        int time = 0;
        int reward = 0;

        for (Task task : tasks) {
            time += task.duration;
            reward += task.deadline - time;
        }

        return reward;
    }
}
