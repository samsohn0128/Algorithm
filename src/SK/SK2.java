public class SK2 {
    private static int[][] answer;
    private static Node[] nodes = new Node[4];

    public static int[][] solution(int n, boolean clockwise) {
        init(n);
        if (n == 1) {
            answer[0][0] = 1;
            return answer;
        }
        int direction = 0;
        if (clockwise) {
            for (int i = 0; i < (n + 1) / 2; i++) {
                printAnswer();
                switch (direction) {
                    case 0:
                        nodes[0] = goRight(nodes[0], i + 1);
                        nodes[1] = goDown(nodes[1], i + 1);
                        nodes[2] = goUp(nodes[2], i + 1);
                        nodes[3] = goLeft(nodes[3], i + 1);
                        break;
                    case 1:
                        nodes[0] = goDown(nodes[0], i + 1);
                        nodes[1] = goLeft(nodes[1], i + 1);
                        nodes[2] = goRight(nodes[2], i + 1);
                        nodes[3] = goUp(nodes[3], i + 1);
                        break;
                    case 2:
                        nodes[0] = goLeft(nodes[0], i + 1);
                        nodes[1] = goUp(nodes[1], i + 1);
                        nodes[2] = goDown(nodes[2], i + 1);
                        nodes[3] = goRight(nodes[3], i + 1);
                        break;
                    case 3:
                        nodes[0] = goUp(nodes[0], i + 1);
                        nodes[1] = goRight(nodes[1], i + 1);
                        nodes[2] = goLeft(nodes[2], i + 1);
                        nodes[3] = goDown(nodes[3], i + 1);
                        break;
                }
                direction = (direction + 1) % 4;
            }
            if (n % 2 == 1) {
                direction = direction - 1 >= 0 ? direction - 1 : direction - 1 + 4;
                switch (direction) {
                    case 0:
                        nodes[0] = goRight(nodes[0], (n + 1) / 2 - 1);
                        break;
                    case 1:
                        nodes[0] = goDown(nodes[0], (n + 1) / 2 - 1);
                        break;
                    case 2:
                        nodes[0] = goLeft(nodes[0], (n + 1) / 2 - 1);
                        break;
                    case 3:
                        nodes[0] = goUp(nodes[0], (n + 1) / 2 - 1);
                        break;
                }
            }
        } else {
            for (int i = 0; i < (n + 1) / 2; i++) {
                printAnswer();
                switch (direction) {
                    case 0:
                        nodes[0] = goDown(nodes[0], i + 1);
                        nodes[1] = goLeft(nodes[1], i + 1);
                        nodes[2] = goRight(nodes[2], i + 1);
                        nodes[3] = goUp(nodes[3], i + 1);
                        break;
                    case 1:
                        nodes[0] = goRight(nodes[0], i + 1);
                        nodes[1] = goDown(nodes[1], i + 1);
                        nodes[2] = goUp(nodes[2], i + 1);
                        nodes[3] = goLeft(nodes[3], i + 1);
                        break;
                    case 2:
                        nodes[0] = goUp(nodes[0], i + 1);
                        nodes[1] = goRight(nodes[1], i + 1);
                        nodes[2] = goLeft(nodes[2], i + 1);
                        nodes[3] = goDown(nodes[3], i + 1);
                        break;
                    case 3:
                        nodes[0] = goLeft(nodes[0], i + 1);
                        nodes[1] = goUp(nodes[1], i + 1);
                        nodes[2] = goDown(nodes[2], i + 1);
                        nodes[3] = goRight(nodes[3], i + 1);
                        break;
                }
                direction = (direction + 1) % 4;
            }
            if (n % 2 == 1) {
                direction = direction - 1 >= 0 ? direction - 1 : direction - 1 + 4;
                switch (direction) {
                    case 0:
                        nodes[0] = goDown(nodes[0], (n + 1) / 2 - 1);
                        break;
                    case 1:
                        nodes[0] = goRight(nodes[0], (n + 1) / 2 - 1);
                        break;
                    case 2:
                        nodes[0] = goUp(nodes[0], (n + 1) / 2 - 1);
                        break;
                    case 3:
                        nodes[0] = goLeft(nodes[0], (n + 1) / 2 - 1);
                        break;
                }
            }
        }

        return answer;
    }

    private static void init(int n) {
        answer = new int[n][n];
        nodes[0] = new Node(0, 0, 1);
        nodes[1] = new Node(0, n - 1, 1);
        nodes[2] = new Node(n - 1, 0, 1);
        nodes[3] = new Node(n - 1, n - 1, 1);
    }

    private static Node goRight(Node node, int size) {
        while (node.x < answer.length - size) {
            answer[node.y][node.x++] = node.num++;
        }
        node.x--;
        node.num--;
        return node;
    }

    private static Node goLeft(Node node, int size) {
        while (node.x >= size) {
            answer[node.y][node.x--] = node.num++;
        }
        node.x++;
        node.num--;
        return node;
    }

    private static Node goDown(Node node, int size) {
        while (node.y < answer.length - size) {
            answer[node.y++][node.x] = node.num++;
        }
        node.y--;
        node.num--;
        return node;
    }

    private static Node goUp(Node node, int size) {
        while (node.y >= size) {
            answer[node.y--][node.x] = node.num++;
        }
        node.y++;
        node.num--;
        return node;
    }

    private static class Node {
        int y;
        int x;
        int num;

        public Node(int y, int x, int num) {
            this.y = y;
            this.x = x;
            this.num = num;
        }
    }

    public static void main(String[] args) {
        boolean clockwise = true;
        int n = 51;
        solution(n, clockwise);
        System.out.println("answer@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        printAnswer();
    }

    private static void printAnswer() {
        System.out.println("===============================");
        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < answer[i].length; j++) {
                System.out.print(answer[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("===============================");
    }
}