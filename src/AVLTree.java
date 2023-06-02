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


    // Método para imprimir a árvore nível por nível
    void printLevelOrder() {

        // Se a raiz for nula, a árvore está vazia, então não há nada para imprimir
        if (root == null) {
            return;
        }

        // Cria uma fila para armazenar os pares de nó e nível.
        // A fila é usada para fazer uma "travessia por nível" da árvore.
        Queue<Pair> queue = new LinkedList<>();

        // Adiciona a raiz à fila, com nível 1
        queue.add(new Pair(root, 1));

        // Enquanto a fila não estiver vazia, continuamos a processar os nós
        while (!queue.isEmpty()) {
            // Retira o próximo par da fila
            Pair pair = queue.poll();

            // Extrai o nó e o nível do par
            Node node = pair.node;
            int level = pair.level;

            // Imprime o nó e o seu nível. Se o nó estiver no primeiro nível (a raiz), imprime uma mensagem.
            // Se o nó não for a raiz, imprime o nó, o nível e o pai do nó
            if (level == 1) {
                System.out.println("camada" + level + ": " + node.key);
            } else {
                System.out.println("camada" + level + " do " + node.parent.key + ": " + node.key);
            }

            // Se o nó tem um filho à esquerda, adiciona o filho à fila, com um nível a mais do que o nó atual
            if (node.left != null) {
                queue.add(new Pair(node.left, level + 1));
            }

            // Se o nó tem um filho à direita, adiciona o filho à fila, com um nível a mais do que o nó atual
            if (node.right != null) {
                queue.add(new Pair(node.right, level + 1));
            }
        }
    }


    //método que deleta um nó da árvore:
    Node deleteNode(Node root, int key)
    {
        // PASSO 1: DELETAÇÃO PADRÃO EM UMA BST(Binary Search Tree)
        if (root == null)
            return root;

        // Se a chave a ser deletada é menor que a chave da raiz,
        // então ela está na subárvore esquerda
        if (key < root.key)
            root.left = deleteNode(root.left, key);

            // Se a chave a ser deletada é maior que a chave da raiz,
            // então ela está na subárvore direita
        else if(key > root.key)
            root.right = deleteNode(root.right, key);

            // Se a chave a ser deletada é igual a chave da raiz,
            // então este é o nó a ser deletado
        else
        {
            // nó com apenas um filho ou sem filhos
            if((root.left == null) || (root.right == null))
            {
                Node temp = null;
                if(temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                // Sem filho
                if(temp == null)
                {
                    temp = root;
                    root = null;
                }
                else   // Um filho
                    root = temp; // Copia o filho não-null para root
            }
            else
            {
                // Nó com dois filhos: pega o menor da subárvore direita
                Node temp = AVLUtils.minValueNode(root.right);

                // Copia o sucessor inorder para esse nó
                root.key = temp.key;

                // Deleta o sucessor inorder
                root.right = deleteNode(root.right, temp.key);
            }
        }

        // Se a árvore tem apenas um nó então retorna
        if (root == null)
            return root;

        // PASSO 2: ATUALIZA A ALTURA DO NÓ ATUAL
        root.height = Math.max((root.left != null ? root.left.height : 0), (root.right != null ? root.right.height : 0)) + 1;

        // PASSO 3: PEGA O FATOR DE BALANCEAMENTO DO NÓ
        int balance = AVLUtils.getBalance(root);


        // Se o nó está desbalanceado, ENTÃO EXISTEM 4 PASSOS:
        // Caso 1) Esquerda Esquerda
        if (balance > 1 && AVLUtils.getBalance(root.left) >= 0)
            return rightRotate(root);
        // Caso 2) Esquerda Direita
        if (balance > 1 && AVLUtils.getBalance(root.left) < 0)
        {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        // Caso 3) Direita Direita
        if (balance < -1 && AVLUtils.getBalance(root.right) <= 0)
            return leftRotate(root);
        // Caso 4) Direita Esquerda
        if (balance < -1 && AVLUtils.getBalance(root.right) > 0)
        {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    //método auxiliar que será usado para pesquisar um nós específico na árvore:
    public Node search(Node root, int key) {
        // Caso base: se a raiz for nula ou se a raiz bater com o procurado...
        if (root==null || root.key==key)
            return root;

        // Se a chave pesquisada for maior que a chave raiz, vamos para a direita...
        if (root.key < key)
            return search(root.right, key);

        // Se a chave pesquisada for menor que a chave raiz, vamos para a esquerda...
        return search(root.left, key);
    }



}


