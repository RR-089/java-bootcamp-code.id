package challenge;

public class Matrix {
    // Nomor 1
    public static int[][] generateDiagonalMatrixV1(int n) {
        int[][] result = new int[n][n];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                if (col > row) {
                    result[row][col] = 10;
                } else if (col < row) {
                    result[row][col] = 20;
                } else {
                    result[row][col] = col + 1;
                }
            }
        }

        return result;
    }

    // Nomor 2
    public static int[][] generateDiagonalMatrixV2(int n) {
        int[][] result = new int[n][n];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                if (col > row) {
                    result[row][col] = 20;
                } else if (col < row) {
                    result[row][col] = 10;
                } else {
                    result[row][col] = result[row].length - row;
                }
            }
        }

        return result;
    }

    // Nomor 3
    public static int[][] generateMatrixV1(int n) {
        /*
          n diasumsikan matriks yang kosong 5 row 5 col
          karena pada soal yang bisa dikaitkan dengan 5 ada ukuran matriks
          yang tidak ada nilainya
         */

        int[][] result = new int[n + 2][n + 2];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                if (row == 0 || row == result.length - 1
                        || col == 0 || col == result[row].length - 1) {
                    result[row][col] = col + row;
                }
            }
        }

        return result;
    }

    // Nomor 4
    public static int[][] generateMatrixV2(int n) {
        int[][] result = new int[n + 1][n + 1];


        for (int row = 0; row < result.length - 1; row++) {
            for (int col = 0; col < result[row].length - 1; col++) {

                result[row][col] = row + col;
                result[row][result[row].length - 1] += row + col;
                result[result.length - 1][col] += row + col;

                if (col == result[row].length - 2) {
                    int lastColValue = result[row][result[row].length - 1];
                    result[result.length - 1][result[row].length - 1] += lastColValue;
                }

            }
        }


        return result;
    }

    public static void displayMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.printf("%5s", anInt);
            }
            System.out.println();
        }

    }
}
