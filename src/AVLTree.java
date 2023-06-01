import java.util.LinkedList;
import java.util.Queue;

public class AVLTree {
    Node root; //raiz da árvore AVL

    // método para realizar uma rotação à direita em um nó
    Node rightRotate(Node y) {
        System.out.println("Right rotation performed on the node " + y.key);
        Node x = y.left;
        Node T2 = x.right;

        // realizando a rotação
        x.right = y;
        y.left = T2;

        // atualizando o campo pai dos nós y e x
        y.parent = x;
        if (T2 != null) {
            T2.parent = y;
        }

        // atualiza as alturas dos nós y e x
        y.height = AVLUtils.max(AVLUtils.getHeight(y.left), AVLUtils.getHeight(y.right)) + 1;
        x.height = AVLUtils.max(AVLUtils.getHeight(x.left), AVLUtils.getHeight(x.right)) + 1;

        return x; // retorna nova raiz
    }

    Node leftRotate(Node x) {
        System.out.println("Left rotation performed on the node " + x.key);
        Node y = x.right;
        Node T2 = y.left;

        // realizando a rotação
        y.left = x;
        x.right = T2;

        // atualizando o campo pai dos nós x e y
        x.parent = y;
        if (T2 != null) {
            T2.parent = x;
        }

        // atualiza as alturas dos nós x e y
        x.height = AVLUtils.max(AVLUtils.getHeight(x.left), AVLUtils.getHeight(x.right)) + 1;
        y.height = AVLUtils.max(AVLUtils.getHeight(y.left), AVLUtils.getHeight(y.right)) + 1;

        return y; // retorna nova raiz
    }

    // método para inserir um novo nó na árvore AVL
    Node insert(Node node, int key) {
        // se o nó for nulo, cria um novo nó com a chave fornecida
        if (node == null)
            return (new Node(key));

        // se a chave for menor que a chave do nó, insere à esquerda
        if (key < node.key) {
            node.left = insert(node.left, key);
            if (node.left != null)
                node.left.parent = node; // update parent field
        }
        // se a chave for maior que a chave do nó, insere à direita
        else if (key > node.key) {
            node.right = insert(node.right, key);
            if (node.right != null)
                node.right.parent = node; // update parent field
        }
        else
            //chaves duplicadas não são permitidas, retorna o mesmo nó
            return node;

        // atualiza a altura do nó pai
        node.height = 1 + AVLUtils.max(AVLUtils.getHeight(node.left), AVLUtils.getHeight(node.right));

        // obtém o fator de balanceamento para verificar se o nó se tornou desbalanceado
        int balance = AVLUtils.getBalance(node);

        // se o nó está desbalanceado, existem 4 casos
        // caso 1: inserção à esquerda na subárvore à esquerda do nó
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // caso 2: inserção à direita na subárvore à direita do nó
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // caso 3: inserção à direita na subárvore à esquerda do nó
        if (balance > 1 && key > node.left.key) {
            System.out.println("Double rotation (left-right) performed on the node " + node.key);
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // caso 4: inserção à esquerda na subárvore à direita do nó
        if (balance < -1 && key < node.right.key) {
            System.out.println("Double rotation (right-left) performed on the node " + node.key);
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // retorna o nó sem alterações, se não houve desbalanceamento
        return node;
    }


    // método para percorrer a árvore em pré-ordem (raiz, esquerda, direita)
    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }


    //IMPRIME O NÍVEL DE CADA NODE
    void printLevelOrder() {
        if (root == null) {
            return;
        }

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 1));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            Node node = pair.node;
            int level = pair.level;

            if (level == 1) {
                System.out.println("camada" + level + ": " + node.key);
            } else {
                System.out.println("camada" + level + " do " + node.parent.key + ": " + node.key);
            }

            if (node.left != null) {
                queue.add(new Pair(node.left, level + 1));
            }

            if (node.right != null) {
                queue.add(new Pair(node.right, level + 1));
            }
        }
    }
}


