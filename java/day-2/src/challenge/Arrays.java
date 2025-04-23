package challenge;

public class Arrays {

    // Nomor 1
    public static int[] orderEvenBeforeOdd(int[] array) {
        int evenIndex = 0, oddIndex = array.length - 1;

        while (evenIndex < oddIndex) {
            if (array[evenIndex] % 2 == 0) {
                evenIndex++;
            } else if (array[oddIndex] % 2 != 0) {
                oddIndex--;
            } else {
                int temp = array[evenIndex];
                array[evenIndex] = array[oddIndex];
                array[oddIndex] = temp;
                evenIndex++;
                oddIndex--;
            }
        }

        sortArray(array, 0, evenIndex - 1);
        sortArray(array, evenIndex, array.length - 1);

        return array;
    }

    public static void sortArray(int[] array, int start, int end) {
        for (int i = start; i < end; i++) {
            for (int j = i + 1; j <= end; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    // Nomor 2
    public static boolean isPalindrome(String[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            if (!array[i].equalsIgnoreCase(array[array.length - 1 - i])) {
                return false;
            }
        }
        return true;
    }

    // Nomor 3
    public static int[] addOnePlus(int[] array) {
        StringBuilder numberBuilder = new StringBuilder();
        
        for (int num : array) {
            numberBuilder.append(num);
        }

        long number = Long.parseLong(numberBuilder.toString());
        number += 1;

        String resultString = Long.toString(number);

        int[] result = new int[resultString.length()];

        for (int i = 0; i < resultString.length(); i++) {
            result[i] = Character.getNumericValue(resultString.charAt(i));
        }

        return result;
    }

    public static void displayArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
