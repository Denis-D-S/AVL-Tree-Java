class Pair {
    // node: É uma instância de um nó da árvore. Essa variável é usada para conserguirmos referenciar um nó específico da árvore...
    Node node;

    // level: É um número inteiro que representa o nível do nó na árvore. O nível de um nó é definido como a distância
    // (ou número de arestas) entre o nó e a raiz da árvore. A raiz tem nível 0, filhos tem nível 1,
    // os filhos dos filhos tem nível 2, etc.
    int level;

    // CONESTRUCTOR::
    // O construtor recebe um nó e um nível como parâmetros, e inicializa as variáveis "node" e "level" com estes valores.
    Pair(Node node, int level) {
        this.node = node;
        this.level = level;
    }
}
