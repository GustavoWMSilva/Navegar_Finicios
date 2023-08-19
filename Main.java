import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
//import java.util.Stack;

public class Main {

    private static int operacao = 0;
    private static char[][] oceano;
    private static int[][] pos;
    private static int combustivelTotal;
    private static int linhaFinal;
    private static int colunaFinal;
    private static boolean[][] visitado;
    private static int linhas, colunas;
    private static int j, v, total;

    public static void InicializaPosVisitada(int linhaT, int colunaT) {

        for (int i = 0; i < linhaT; i++) {
            for (int j = 0; j < colunaT; j++) {
                visitado[i][j] = false;
                operacao++;
            }
        }
    }

    public static void main(String[] args) {
        long tStart = System.currentTimeMillis();
        Path path = Paths.get("caso20.txt");
        BufferedReader reader;

        try {
            reader = Files.newBufferedReader(path, Charset.defaultCharset());

            String line = null;
            String data[] = reader.readLine().split("\\s+");
            linhas = Integer.parseInt(data[0]);
            colunas = Integer.parseInt(data[1]);
            oceano = new char[linhas][colunas];
            pos = new int[10][2];

            int linha = 0;

            while ((line = reader.readLine()) != null) {
                data = line.split("");

                for (int coluna = 0; coluna < colunas; coluna++) {
                    oceano[linha][coluna] = data[coluna].charAt(0);

                    if (!data[coluna].equals(".") && !data[coluna].equals("*")) {
                        int porto = Integer.parseInt(data[coluna]);
                        pos[porto][0] = linha;
                        pos[porto][1] = coluna;
                    }
                    operacao++;
                }

                linha++;
            }

            j = 1;
            v = 0;
            for (int i = 2; i < 10; i++) {
                buscaPorto(i);
            }
            buscaPorto(1);

            System.out.println("& " + total);
            System.out.println("& " + operacao);
            System.out.println("& " + (System.currentTimeMillis() - tStart) + " ms");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void caminhamentoLargura(int linhaInicial, int colunaInicial,
            boolean[][] visitado) {
        Queue<Integer> filaLinhas = new LinkedList<>();
        Queue<Integer> filaColunas = new LinkedList<>();
        Queue<Integer> filaCombustivel = new LinkedList<>();

        filaLinhas.add(linhaInicial);
        filaColunas.add(colunaInicial);
        filaCombustivel.add(0);

        while (!filaLinhas.isEmpty()) {
            int linha = filaLinhas.poll();
            int coluna = filaColunas.poll();
            int combustivel = filaCombustivel.poll();

            if (linha == linhaFinal && coluna == colunaFinal) {
                combustivelTotal = combustivel;
                operacao++;
                return;
            }

            // Movimento para cima (Norte)
            if (linha - 1 >= 0 && oceano[linha - 1][coluna] != '*' && !visitado[linha -
                    1][coluna]) {
                filaLinhas.add(linha - 1);
                filaColunas.add(coluna);
                filaCombustivel.add(combustivel + 1);
                visitado[linha - 1][coluna] = true;
                operacao++;
            }

            // Movimento para baixo (Sul)
            if (linha + 1 < oceano.length && oceano[linha + 1][coluna] != '*' &&
                    !visitado[linha + 1][coluna]) {
                filaLinhas.add(linha + 1);
                filaColunas.add(coluna);
                filaCombustivel.add(combustivel + 1);
                visitado[linha + 1][coluna] = true;
                operacao++;

            }

            // Movimento para a esquerda (Oeste)
            if (coluna - 1 >= 0 && oceano[linha][coluna - 1] != '*' &&
                    !visitado[linha][coluna - 1]) {
                filaLinhas.add(linha);
                filaColunas.add(coluna - 1);
                filaCombustivel.add(combustivel + 1);
                visitado[linha][coluna - 1] = true;
                operacao++;

            }

            // Movimento para a direita (Leste)
            if (coluna + 1 < oceano[0].length && oceano[linha][coluna + 1] != '*' &&
                    !visitado[linha][coluna + 1]) {
                filaLinhas.add(linha);
                filaColunas.add(coluna + 1);
                filaCombustivel.add(combustivel + 1);
                visitado[linha][coluna + 1] = true;
                operacao++;

            }
        }

        // Se chegou aqui, significa que não foi possível alcançar o porto final
        combustivelTotal = -1;
    }

    public static void buscaPorto(int i) {
        int linhaInicial = pos[j][0];
        int colunaInicial = pos[j][1];

        combustivelTotal = 0;

        linhaFinal = pos[i][0];
        colunaFinal = pos[i][1];

        visitado = new boolean[linhas][colunas];
        InicializaPosVisitada(linhas, colunas);

        visitado[linhaInicial][colunaInicial] = true;

        // editado para o Latex
        caminhamentoLargura(linhaInicial, colunaInicial, visitado);
        if (combustivelTotal != -1) {
            System.out.println(j+"rightarrow"+i+"& " + combustivelTotal+ "&&&\\");
            total += combustivelTotal;
            j++;
            j += v;
            v = 0;
            
        } else {
            v++;
            System.out.print(j+"nrightarrow"+ i+"& ----&&&\\");
        }
    }
}
