public class Node {
    //Key = é um inteiro, a informação principal que o nó armazena. Cada nó tem sua chave
    //Height = é um inteiro que representa a altura do nó na árvore. É usada para ajudar a manter a árvore balanceada. A altura de um nó é o número de arestas no caminho mais longo do nó até uma folha.
    //left e right = são os ponteiros que apontam para nó da direita ou da esquerda. Usamos isso para saber para que lado ir na hora de balancear a árvore.
    //parent = é um ponteiro que aponta para o nó pai. Usamos isso para imprimir ao usuário qual nó puxa de qual nó

    //DECCLARAÇÃO DAS VARIÁVEIS:
    int key, height;
    Node left, right, parent;

    //CONSTRUCTOR
    Node(int chave) {
        key = chave;
        height = 1;
        parent = null;
    }
}
