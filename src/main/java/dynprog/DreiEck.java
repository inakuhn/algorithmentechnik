package dynprog;

/**
 * Created by Ina Kuhn on 29.06.2017.
 */

import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

public class DreiEck {



    static void process(int a[][], int n, int s[][],
                        int vPred[][], int w[]) {
        //Basis case
        s[0][0] = a[0][0];
        vPred[0][0] = -1;
        for (int i = 1; i < n; i++) {
            //Down Botter Berechnung
            s[i][0] = a[i][0] + s[i - 1][0];
            vPred[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                if (s[i - 1][j - 1] >= s[i - 1][j]) {
                    vPred[i][j] = 0;
                    s[i][j] = a[i][j] + s[i - 1][j - 1];
                } else {
                    vPred[i][j] = 1;
                    s[i][j] = a[i][j] + s[i - 1][j];
                }
            }
        }
        w[n - 1] = 0;
        for (int j = 0; j <= n - 1; j++)
            if (s[n - 1][j] > s[n - 1][w[n - 1]]) {
                w[n - 1] = j;
            }
        for (int i = n - 2; i >= 0; i--) {
            if (vPred[i + 1][w[i + 1]] == 0)
                w[i] = w[i + 1] - 1;
            else
                w[i] = w[i + 1];
        }
    }

    static void writeData(PrintStream out, int s[][],
                          int w[], int n) {
        out.print(" Maximale Summe = ");
        out.println(s[n - 1][w[n - 1]]);
        for (int i = 0; i < n; i++) {
            out.println(w[i] + 1);
        }
    }

    public static void main(String[] args) throws IOException {

        int n = 5;
        int a[][] = new int[n][n];
        int s[][] = new int[n][n];
        int vPred[][] = new int[n][n];
        int w[] = new int[n];
        initializewith(-1, a, n);
        initializewith(0, s, n);
        initializeRandon(a, n);
        process(a, n, s, vPred, w);
        printMa(s, n);
        printMa(a, n);
        for(int i = 0; i < w.length; i++){
            System.out.print(" "+w[i]+", ");
        }


    }


    public static void initializewith(int number, int a[][], int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                a[i][j] = number;
            }
        }
    }

    public static void printMa(int a[][], int n) {
        System.out.println("------------------------------------");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("\t" + a[i][j] + "|\t");
            }
            System.out.println();

        }
        System.out.println("------------------------------------");
    }

    public static void initializeRandon(int a[][], int n) {
        Random rn = new Random();
        int nt = 2 - 1 + 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j <= n / 2) {
                    int rd = rn.nextInt() % nt;
                    int randomNum = 5 + rd;
                    a[i][j] = j+i;
                }
            }

        }
    }
}

