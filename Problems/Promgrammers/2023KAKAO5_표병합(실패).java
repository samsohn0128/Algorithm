import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class Solution {
    private static final int MAX_LENGTH = 50;
    private static final String EMPTY = "EMPTY";
    private static String[] grid = new String[MAX_LENGTH * MAX_LENGTH];
    private static int[] roots = new int[MAX_LENGTH * MAX_LENGTH];
    private static List<String> answer = new ArrayList<>();

    public static String[] solution(String[] commands) {
        init();
        Arrays.stream(commands)
                .map(Solution::makeCommand).filter(Objects::nonNull)
                .forEach(Command::doCommand);
//        printGrid();
        return answer.toArray(new String[0]);
    }

    private static void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            System.out.print(grid[i] + "\t");
            if ((i + 1) % MAX_LENGTH == 0)
                System.out.println("\n");
        }
    }

    private static void init() {
        for (int i = 0; i < roots.length; i++) {
            roots[i] = i;
            grid[i] = EMPTY;
        }
    }

    private static Command makeCommand(String command) {
        String[] commandAndArgs = command.split(" ");
        if ("UPDATE".equals(commandAndArgs[0])) {
            return makeUpdateCommand(commandAndArgs);
        } else if ("MERGE".equals(commandAndArgs[0])) {
            return new Merge(Integer.parseInt(commandAndArgs[1]), Integer.parseInt(commandAndArgs[2]),
                    Integer.parseInt(commandAndArgs[3]), Integer.parseInt(commandAndArgs[4]));
        } else if ("UNMERGE".equals(commandAndArgs[0])) {
            return new Unmerge(Integer.parseInt(commandAndArgs[1]), Integer.parseInt(commandAndArgs[2]));
        } else if ("PRINT".equals(commandAndArgs[0])) {
            return new Print(Integer.parseInt(commandAndArgs[1]), Integer.parseInt(commandAndArgs[2]));
        } else {
            return null;
        }
    }

    private static Command makeUpdateCommand(String[] commandAndArgs) {
        if (commandAndArgs.length == 4) {
            return new UpdateByRC(Integer.parseInt(commandAndArgs[1]), Integer.parseInt(commandAndArgs[2]),
                    commandAndArgs[3]);
        } else {
            return new UpdateByValue(commandAndArgs[1], commandAndArgs[2]);
        }
    }

    public static void main(String[] args) {
//        String[] commands = {"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"};
//        String[] commands = {"UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2", "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"};
        String[] commands = {"UPDATE 1 1 a", "UPDATE 1 2 b", "MERGE 1 1 1 3", "PRINT 1 3"};
        String[] answer = solution(commands);
        System.out.println(Arrays.toString(answer));
    }

    private static abstract class Command {
        public abstract void doCommand();

        protected int find(int x) {
            if (roots[x] == x) {
                return x;
            }
            return roots[x] = find(roots[x]);
        }

        protected int getSerializedIndex(int r, int c) {
            return (r - 1) * MAX_LENGTH + (c - 1);
        }
    }

    private static class UpdateByRC extends Command {
        private int r;
        private int c;
        private String value;

        public UpdateByRC(int r, int c, String value) {
            this.r = r;
            this.c = c;
            this.value = value;
        }

        @Override
        public void doCommand() {
            grid[find(getSerializedIndex(r, c))] = value;
        }
    }

    private static class UpdateByValue extends Command {
        private String value1;
        private String value2;

        public UpdateByValue(String value1, String value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        @Override
        public void doCommand() {
            for (int i = 1; i <= MAX_LENGTH; i++) {
                for (int j = 1; j <= MAX_LENGTH; j++) {
                    int root = find(getSerializedIndex(i, j));
                    if (value1.equals(grid[root])) {
                        grid[root] = value2;
                    }
                }
            }
        }
    }

    private static class Merge extends Command {
        private int r1;
        private int c1;
        private int r2;
        private int c2;

        public Merge(int r1, int c1, int r2, int c2) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
        }

        @Override
        public void doCommand() {
            int root1 = find(getSerializedIndex(r1, c1));
            int root2 = find(getSerializedIndex(r2, c2));
            roots[root2] = root1;
            if (EMPTY.equals(grid[root1])) {
                grid[root1] = grid[root2];
            }
        }
    }

    private static class Unmerge extends Command {
        private int r;
        private int c;

        public Unmerge(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public void doCommand() {
            int serializedIndex1 = getSerializedIndex(r, c);
            int root1 = find(serializedIndex1);
            grid[serializedIndex1] = grid[root1];
            roots[serializedIndex1] = serializedIndex1;

            for (int i = 1; i <= MAX_LENGTH; i++) {
                for (int j = 1; j <= MAX_LENGTH; j++) {
                    if (r == i && c == j) {
                        continue;
                    }

                    int serializedIndex2 = getSerializedIndex(i, j);
                    int root2 = find(serializedIndex2);
                    if (root1 == root2) {
                        grid[serializedIndex2] = EMPTY;
                        roots[serializedIndex2] = serializedIndex2;
                    }
                }
            }
        }
    }

    private static class Print extends Command {
        private int r;
        private int c;

        public Print(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public void doCommand() {
            answer.add(grid[find(getSerializedIndex(r, c))]);
        }
    }
}