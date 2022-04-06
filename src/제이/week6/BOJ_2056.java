import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Consumer;

import static java.lang.Integer.parseInt;

public class BOJ_2056 {

    private static int NO_RELATION = 0;
    private static int STARTED = -1;

    private static int taskCount;
    private static int[] relations;
    private static Task[] tasks;

    public static void main(String[] args) throws Exception {
        readInput();

        List<Task> startedTask = new ArrayList<>();
        List<Task> finishedTask = new ArrayList<>();

        // 선행 관계가 없거나 완료된 Task 들을 시작할 목록에 추가
        addStartableTask(startedTask);

        int time = 0;

        while(startedTask.size() > 0) {
            time++;

            for (Task currTask : startedTask) {
                currTask.tick(); // Task 작업 수행 (시간 -1)

                // 현재 Task 끝났으면, 이 Task 를 기다리는 Task 의 선행 관계 개수에 -1
                if (currTask.isFinished()) {
                    currTask.waitingTasks.forEach(Main::removeRelationOfWatingTask);
                    finishedTask.add(currTask);
                }
            }

            // 끝난 Task 들 목록에서 제거
            startedTask.removeAll(finishedTask);
            finishedTask.clear();

            // 선행 조건이 없거나 완료된 Task 들을 시작할 목록에 추가
            addStartableTask(startedTask);
        }

        System.out.println(time);
    }

    private static void removeRelationOfWatingTask(int index) {
        relations[index]--;
    }

    private static void addStartableTask(List<Task> startedTask) {
        for (int i = 1; i < relations.length; i++) {
            if (relations[i] == NO_RELATION) {
                startedTask.add(tasks[i]);
                relations[i] = STARTED;
            }
        }
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        taskCount = parseInt(br.readLine());

        relations = new int[taskCount+1];
        tasks = new Task[taskCount+1];

        for (int currIndex = 1; currIndex <= taskCount; currIndex++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Task task = new Task();
            task.index = currIndex;
            task.time = parseInt(st.nextToken());

            int relatedCount = parseInt(st.nextToken());
            relations[currIndex] = relatedCount;

            for (int j = 0; j < relatedCount; j++) {
                int relatedIndex = parseInt(st.nextToken());
                tasks[relatedIndex].waitingTasks.add(currIndex);
            }

            tasks[currIndex] = task;
        }

        // for (Task task : tasks) {
        //     System.out.println(task);
        // }
    }
}

class Task {
    int index;
    int time;
    Set<Integer> waitingTasks = new HashSet<>();

    public void tick() {
        time--;
    }

    public boolean isFinished() {
        return time == 0;
    }

    @Override
    public String toString() {
        return "Task{" +
                "index=" + index +
                ", time=" + time +
                ", waitingTasks=" + waitingTasks +
                '}';
    }
}