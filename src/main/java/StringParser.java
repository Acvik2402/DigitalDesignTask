import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class StringParser {

    public String parse (@NotNull String str) {
        if (!(str.matches("[а-яА-Яa-zA-Z\\[\\]\\d]+"))) {
            throw new IllegalArgumentException();
        }
        checkBraces(str);
        StringBuilder res = new StringBuilder(str);
        while (res.toString().contains("[")) {
            int open = res.lastIndexOf("[");
            int close = res.indexOf("]", open);
            if (open==0||close-open==1||!(Character.isDigit(res.charAt(open - 1)))) {
                throw new IllegalArgumentException();
            }
            int countIndex = open - 1;
            while (Character.isDigit(countIndex - 1)) {
                countIndex--;
            }
            int count = Integer.parseInt(res.substring(countIndex, open));
            String substr = "";
            for (int i = 0; i < count; i++) {
                substr += res.substring(open + 1, close);
            }
            res.replace(countIndex, close+1, substr);


        }

        return res.toString();
    }


    private void checkBraces (@NotNull String str) {
        int openCount = 0;
        int closeCount = 0;
        for (char ch : str.toCharArray()) {
            if (ch == '[') {
                openCount++;
            }
            if (ch == ']') {
                closeCount++;
            }
        }
        if (openCount != closeCount || openCount == 0) {
            throw new IllegalArgumentException();
        }
    }

    public static void main (String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String str = scanner.nextLine();
            System.out.println(new StringParser().parse(str));
        }
    }
}
