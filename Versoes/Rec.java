public class Rec {
    private static Integer[][] array = new Integer[3][3];

    public static void main(String[] args) {
        array[0][0] = 0;
        array[0][1] = 0;
        array[0][2] = 2;

        array[1][0] = 0;
        array[1][1] = -1;
        array[1][2] = -1;

        array[2][0] = 0;
        array[2][1] = 1;
        array[2][2] = 0;

        int linhaInicial = 2;
        int colunaInicial = 1;

        int linhaAlvo = encontrarLinha(linhaInicial, colunaInicial);
        int colunaAlvo = encontrarColuna(colunaInicial, linhaInicial);

        System.out.println(linhaAlvo + " " + colunaAlvo);
    }

    public static int encontrarLinha(int linhaAtual, int colunaInicial) {
        if (linhaAtual >= array.length) {
            return -1; // Chegou ao final das linhas sem encontrar a linha alvo
        }

        if (array[linhaAtual][colunaInicial] == linhaAtual) {
            return linhaAtual; // Encontrou a linha alvo
        }

        int proximaLinha = linhaAtual + 1;
        return encontrarLinha(proximaLinha, colunaInicial);
    }

    public static int encontrarColuna(int colunaAtual, int linhaInicial) {
        if (colunaAtual >= array[0].length) {
            return -1; // Chegou ao final das colunas sem encontrar a coluna alvo
        }

        if (array[linhaInicial][colunaAtual] == colunaAtual) {
            return colunaAtual; // Encontrou a coluna alvo
        }

        int proximaColuna = colunaAtual + 1;
        return encontrarColuna(proximaColuna, linhaInicial);
    }
}
