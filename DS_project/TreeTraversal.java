import java.util.Scanner;

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class QueueNode {
    TreeNode data;
    QueueNode next;

    public QueueNode(TreeNode data) {
        this.data = data;
        this.next = null;
    }
}

class Queue {
     QueueNode front;
     QueueNode rear;

    public Queue() {
        this.front = this.rear = null;
    }

    boolean isEmpty() {
        return front == null;
    }

    void enqueue(TreeNode newNode) {
        QueueNode newNodeInQueue = new QueueNode(newNode);
        if (isEmpty()) {
            front = rear = newNodeInQueue;
        } else {
            rear.next = newNodeInQueue;
            rear = newNodeInQueue;
        }
    }

    TreeNode dequeue() {
        if (isEmpty()) {
            return null;
        }
        QueueNode removedNode = front;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return removedNode.data;
    }
}

class BinaryTree {
    TreeNode root;

    void insert(int data)//insert tree node
     {
        root = insertRecursive(root, data);
    }

    TreeNode insertRecursive(TreeNode current, int data) {
        if (current == null) {
            return new TreeNode(data);
        }

        if (data < current.data) {
            current.left = insertRecursive(current.left, data);
        } else if (data > current.data) {
            current.right = insertRecursive(current.right, data);
        }

        return current;
    }

    void delete(int data) //delete node in tree
    {
        root = deleteRecursive(root, data);
    }

    TreeNode deleteRecursive(TreeNode current, int data) {
        if (current == null) {
            return current;
        }

        if (data < current.data) {
            current.left = deleteRecursive(current.left, data);
        } else if (data > current.data) {
            current.right = deleteRecursive(current.right, data);
        } else {
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }

            current.data = minValue(current.right);
            current.right = deleteRecursive(current.right, current.data);
        }

        return current;
    }

    int minValue(TreeNode node) // to cheak minimum velue of tree
    {
        int minValue = node.data;
        while (node.left != null) {
            minValue = node.left.data;
            node = node.left;
        }
        return minValue;
    }

    void traverseTree(int choice) {
        switch (choice) {
            case 1:
                System.out.print("In-Order Traversal: ");
                inOrderTraversal(root);
                break;
            case 2:
                System.out.print("Pre-Order Traversal: ");
                preOrderTraversal(root);
                break;
            case 3:
                System.out.print("Post-Order Traversal: ");
                postOrderTraversal(root);
                break;
            case 4:
                System.out.print("Level-Order Traversal: ");
                levelOrderTraversal(root);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    void inOrderTraversal(TreeNode root)//for inorder
     {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.data + " ");
            inOrderTraversal(root.right);
        }
    }

    void preOrderTraversal(TreeNode root) //for preorder
    {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    void postOrderTraversal(TreeNode root) //for postorder
    {
        if (root != null) {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.print(root.data + " ");
        }
    }

    void levelOrderTraversal(TreeNode root) //for leval order
     {
        if (root == null) {
            return;
        }

        Queue queue = new Queue();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.dequeue();
            System.out.print(current.data + " ");

            if (current.left != null) {
                queue.enqueue(current.left);
            }

            if (current.right != null) {
                queue.enqueue(current.right);
            }
        }
    }
    void printTree() {
        printTree(root, 0);
    }
    void printTree(TreeNode node, int level) //display tree
    {
        if (node == null) {
            return;
        }

        printTree(node.right, level + 1);

        for (int i = 0; i < level; i++) {
            System.out.print("    "); 
        }
        System.out.println(node.data);

        printTree(node.left, level + 1);
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Welcome to tree traversal..");
        System.out.println("This tree is a binary search tree...");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Insert a node");
            System.out.println("2. Delete a node");
            System.out.println("3. Perform traversal");
            System.out.println("4. display tree");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the value to insert: ");
                    int insertData = scanner.nextInt();
                    tree.insert(insertData);
                    break;
                case 2:
                    System.out.print("Enter the value to delete: ");
                    int deleteData = scanner.nextInt();
                    tree.delete(deleteData);
                    break;
                case 3:
                    System.out.println("Choose a traversal method:");
                    System.out.println("1. In-Order");
                    System.out.println("2. Pre-Order");
                    System.out.println("3. Post-Order");
                    System.out.println("4. Level-Order");
                    int traversalChoice = scanner.nextInt();
                    tree.traverseTree(traversalChoice);
                    break;
                case 4:
                    System.out.println("Binary Search Tree:");
                    tree.printTree();
                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}

