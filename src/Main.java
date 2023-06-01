import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //CRIANDO UMA BASE INICIAL PARA TER UMA ÁRVORE DE TESTE
        System.out.println("Hello world! I'm the Main class! I'm running ok...");

        AVLTree tree = new AVLTree();

        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 25);
        tree.root = tree.insert(tree.root, -59);
        tree.root = tree.insert(tree.root, -109);


        //////////////////////////////////////////////

        //COMUNICAÇÃO COM O USUÁRIO

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Insert a node");
            System.out.println("2. Preorder traversal");
            System.out.println("3. Exit");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Please enter a number to insert into the tree:");
                    int number = scanner.nextInt();
                    tree.root = tree.insert(tree.root, number);
                    break;
                case 2:
                    System.out.println("Preorder traversal of constructed tree is : ");
                    tree.preOrder(tree.root);
                    System.out.println("Level order traversal of constructed tree is : ");
                    tree.printLevelOrder();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}