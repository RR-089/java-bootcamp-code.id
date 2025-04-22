package challenge;

import java.util.Scanner;

public class Iteration {

    // Nomor 1
    public static void displaySquare(int side){
        int counter = 1;
        for(int i = 0; i < side; i++){
            for(int j = 0; j < side; j++){
                System.out.print(counter);
                counter++;
            }
            System.out.println();
        }
    }

    // Nomor 2
    public static void displaySequenceTriangleV1(int row){
        for(int i = 0; i < row; i++){
            int firstNumber = i + 1;

            for(int j = 0; j < firstNumber; j++){
                System.out.print(firstNumber + j);
            }

            System.out.println();
        }
    }

    // Nomor 3
    public static void displaySequenceTriangleV2(int row){
        for(int i = 0; i < row; i++){
            for(int j = i; j < row; j++){
                System.out.print(j + 1);
            }
            System.out.println();
        }
    }

    // Nomor 4
    public static void displaySequenceTriangleV3(int row){
        for(int i = 0; i < row; i++){
            for(int j = 0; j < row; j++){
                if(j >= i){
                    System.out.print(j + 1);
                } else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    // Nomor 5
    public static void displaySquareDiagonalV1(int side){
        for(int i = 0; i < side; i++){
            int currentOuter = i + 1;

            for(int j = 0; j < side; j++){
                int currentInner = j + 1;

                if(currentInner == currentOuter){
                    System.out.print(currentInner);
                } else if (currentInner < currentOuter) {
                    System.out.print("20");
                } else{
                    System.out.print("10");
                }

            }

            System.out.println();
        }
    }

    // Nomor 6
    public static void displaySquareDiagonalV2(int side){
        for (int i = side; i > 0; i--) {
            int currentOuter = i;

            for (int j = side; j > 0; j--) {
                int currentInner = j;

                if(currentInner == currentOuter){
                    System.out.print(currentInner);
                } else if (currentInner > currentOuter) {
                    System.out.print("10");
                } else {
                    System.out.print("20");
                }


            }
            System.out.println();
        }
    }

    // Nomor 7
    public static void askAndDisplayTriangle(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input jumlah baris piramid : ");
        int row = scanner.nextInt();
        displayTriangle(row);
        scanner.close();
    }

    public static void displayTriangle(int row){
        for (int i = row; i >= 1; i--) {
            int length = i * 2 - 1;

            for (int j = 0; j < length; j++) {
                if(j < i){
                    System.out.print(i - j);
                } else {
                    System.out.print(j - i + 2);
                }
            }

            System.out.println();
        }
    }

    // Nomor 8 (Skip)

    // Nomor 9
    public static void displayNumberSequencesV1(int row){
        for (int i = 0; i < row; i++) {

            if((i + 1) % 2 == 0){
                for(int j = 0; j < row; j ++){
                    System.out.print(j + 1);
                }
            } else {
                for (int j = row; j > 0; j--) {
                    System.out.print(j);
                }
            }

            System.out.println();
        }
    }

    // Nomor 10
    public static void displayOddEvenSequences(int row){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if((i % 2 == 0 && j % 2 != 0)
                || (i % 2 != 0 && j % 2 == 0)){
                    System.out.print(j + 1);
                } else {
                    System.out.print("-");
                }
            }

            //if(i % 2 == 0){
            //    for (int j = 0; j < row; j++) {
            //        if(j % 2 != 0){
            //            System.out.print(j + 1);
            //        } else {
            //            System.out.print("-");
            //        }
            //    }
            //}else{
            //    for (int j = 0; j < row; j++) {
            //        if(j % 2 == 0){
            //            System.out.print(j + 1);
            //        } else{
            //            System.out.print("-");
            //        }
            //    }
            //}

            System.out.println();
        }

    }
}
