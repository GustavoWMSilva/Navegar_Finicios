import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;

public class App3 {

    private static char[][] oceano;
    private static boolean posVisitada[][];
    private static LinkedList<int[]> fila = new LinkedList<>();


    public static void InicializaPosVisitada(int linhaT, int colunaT) {
        posVisitada = new boolean[linhaT][colunaT];

        for (int i = 0; i < linhaT; i++) {
            for (int j = 0; j < colunaT; j++) {
                posVisitada[i][j] = false;
            }
        }
    }

    public static void main(String args[]) {
        Path path = Paths.get("caso0.txt");
        BufferedReader reader;

        try {
            reader = Files.newBufferedReader(path, Charset.defaultCharset());

            String line = null;
            String data[] = reader.readLine().split("\\s+");
            int colunaT = Integer.parseInt(data[1]);
            char[][] oceano = new char[Integer.parseInt(data[0])][Integer.parseInt(data[1])];
            int linha = 0;

            int[][] pos = new int[10][2];
            PilhaEncadeada p = new PilhaEncadeada();

            while ((line = reader.readLine()) != null) {
                data = line.split("");

                for (int coluna = 0; coluna < colunaT; coluna++) {
                    if (data[coluna].equals(".") || data[coluna].equals("*")) {
                        oceano[linha][coluna] = data[coluna].charAt(0);
                    } else {
                        oceano[linha][coluna] = data[coluna].charAt(0);
                        pos[Integer.parseInt(data[coluna])][0] = linha;
                        pos[Integer.parseInt(data[coluna])][1] = coluna;
                    }
                    System.out.print(oceano[linha][coluna]);
                }
                System.out.println();
                linha++;
            }

            int linhaInicial = pos[1][0];
            int colunaInicial = pos[1][1];
            int linhaFinal = pos[2][0];
            int colunaFinal = pos[2][1];

             linha = linhaInicial;
            int coluna = colunaInicial;
            int combustivelTotal = 0;

            while (oceano[linha][coluna] != '2') {
                int proximaLinha = linha;
                int proximaColuna = coluna;
                System.out.println(1);
                if (linha > linhaFinal) {
                    System.out.println(2);

                    proximaLinha--;
                } else if (linha < linhaFinal) {
                    System.out.println(3);

                    proximaLinha++;
                } else if (coluna > colunaFinal) {
                    System.out.println(4);

                    proximaColuna--;
                } else if (coluna < colunaFinal) {
                    System.out.println(5);

                    proximaColuna++;
                }

                int distancia = Math.abs(proximaLinha - linha) + Math.abs(proximaColuna - coluna);
                combustivelTotal += distancia;

                if (oceano[proximaLinha][proximaColuna] != '*') {
                    System.out.println(6);

                    p.push(proximaLinha, proximaColuna);
                    linha = proximaLinha;
                    coluna = proximaColuna;
                } else {
                    System.out.println(7);

                    if (!p.isEmpty()) {
                        p.pop();
                    }
                }
            }

            System.out.println("Quantidade total de combustível necessária: " + combustivelTotal);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static int recurs(int linha, int coluna, int encontrar) {
        int erro = 1;

        if (oceano[linha][coluna] == '.' && !posVisitada[linha][coluna]) {
            int pos[] = { linha, coluna };
            posVisitada[linha][coluna] = true;
            fila.add(pos);
            linha++;
            recurs(linha, coluna, encontrar);
        } else if (oceano[linha][coluna] == '2') {
            erro = 1;
        }

        if (erro == 0) {
            linha--;
        } else if (erro == -1) {
            coluna--;
        }

        return 0;
    }
}
