import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;

public class App2 {

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
            oceano = new char[Integer.parseInt(data[0])][Integer.parseInt(data[1])];
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

            for (int coluna = 1; coluna < 3; coluna++) {
                // System.out.print(oceano[pos[coluna][0]][pos[coluna][1]]);
            }

            linha = pos[1][0];// 2
            int coluna = pos[1][1];// 1
            int linhaFinal = pos[2][0];// 0
            int colunaFinal = pos[2][1];// 2

            //// Ideia: dividir em metodos, se um nao der volta e vai pelo outro;

            while (oceano[linha][coluna] != '2') {
                System.out.println(" ooo ");

                if (linha >= linhaFinal) {// && oceano[linha][coluna] != -1
                    if (oceano[linha][coluna] != '*') {
                        if (linha > linhaFinal) {
                            linha--;
                        }                        System.out.println(" xxx ");

                        if (coluna > colunaFinal) {
                            p.push(linha, coluna);
                            coluna--;
                        } else if (coluna < colunaFinal) {
                            System.out.println(" iii ");

                            p.push(linha, coluna);
                            coluna++;
                        }
                    } else {
                        System.out.println(" vvv ");

                        coluna++;
                        if (!p.isEmpty())
                            p.pop();
                    }

                } else if (linha < linhaFinal) {
                    linha++;
                    if (oceano[linha][coluna] != '*') {
                        if (coluna > colunaFinal) {
                            if (oceano[linha][coluna] != '*') {
                                p.push(linha, coluna);
                                coluna--;
                            } else {
                                coluna++;
                                if (!p.isEmpty())
                                    p.pop();
                            }
                        } else if (coluna < colunaFinal) {
                            if (oceano[linha][coluna] != '*') {
                                p.push(linha, coluna);
                                coluna++;
                            } else {
                                coluna++;
                                if (!p.isEmpty())
                                    p.pop();
                            }
                        }

                    } else {
                        linha--;
                        if (!p.isEmpty())
                            p.pop();
                    }
                }
            }
            System.out.println(linha);
            System.out.println(coluna);

            // while (!p.isEmpty()) {
            // System.out.println(p.pop());
            // }

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
