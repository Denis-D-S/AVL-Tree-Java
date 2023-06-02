public class AVLUtils {

    //MÉTODO QUE OBTEM A ALTURA DE UM NÓ
    public static int getHeight(Node N) {
        if (N == null) {
            return 0;
        };

        return N.height;
    }

    //MÉTODO AUXILIAR QUE APENAS RETORNA O MAIOR DENTRE 2 NÚMEROS INTEIROS PASSADOS COMO PARÂMETRO
    //Ele é usado na árvore AVL para determinar a altura de um nó, que é um a mais do que o máximo das alturas de seus nós filhos à esquerda e à direita.
    public static int max(int a, int b) {
        return Math.max(a, b);
    }

    //MÉTODO QUE RETORNA O FATOR DE BALANCEAMENTO DE UM NÓ
    //Este método retorna a diferença entre as alturas dos nós filhos à esquerda e à direita do nó fornecido
    //Se o fator de balanceamento de algum nó é diferente de -1, 0 ou 1, então a árvore precisa ser reequilibrada.
    public static int getBalance(Node N) {
        if (N == null) {
            return 0;
        };

        return getHeight(N.left) - getHeight(N.right);
    }

    //MÉTODO QUE RETORNA O NÓ COM O MENOR VALOR ENCONTRADO NA ÁRVORE
    public static Node minValueNode(Node node)
    {
        Node current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;

        return current;
    }

}
