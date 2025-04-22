package challenge;

public class MathAndString {

    // Nomor 1
    public static int reverseInteger(int number) {
        boolean isNegative = number < 0;

        String numberString = Integer.toString(Math.abs(number));

        StringBuilder reverse = new StringBuilder(numberString).reverse();

        int reversedNumber = Integer.parseInt(reverse.toString());

        return isNegative ? -reversedNumber : reversedNumber;
    }

    // Nomor 2
    public static boolean isPalindrome(int number) {
        String numberString = Integer.toString(Math.abs(number));

        StringBuilder reverse = new StringBuilder(numberString).reverse();

        return reverse.toString().equals(numberString);
    }

    // Nomor 3
    public static String capitalize(String text) {
        String[] words = text.split(" ");

        StringBuilder capitalizedString = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                String capitalizedWord =
                        Character.toUpperCase(word.charAt(0))
                                + word.substring(1).toLowerCase()
                                + " ";

                capitalizedString.append(capitalizedWord);
            }
        }

        return capitalizedString.toString().trim();
    }

    // Nomor 4
    public static boolean isNoDuplicateChar(String word) {
        word = word.toUpperCase().replace(" ", "");

        StringBuilder seenChars = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);

            if (seenChars.indexOf(String.valueOf(currentChar)) != -1) {
                return false;
            }

            seenChars.append(currentChar);
        }

        return true;
    }

    // Nomor 5
    public static boolean isBrace(String word) {
        word = word.replace(" ", "");

        int counter = 0;

        for (char brace : word.toCharArray()) {
            if (brace == '(') {
                counter++;
            }else if (brace == ')') {
                counter--;

                if(counter < 0){
                    return  false;
                }
            }
        }

        return counter == 0;
    }


}
