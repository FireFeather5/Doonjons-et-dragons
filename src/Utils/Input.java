package Utils;

import java.util.Scanner;

public class Input {

    Scanner scan;
    private final static String[] _ord = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    public Input() {
        scan = new Scanner(System.in);
    }

    public int[] position() throws NumberFormatException, StringIndexOutOfBoundsException, NumberFormatException {

        String position = scan.nextLine();

        String pos1 = position.substring(0, 1);
        String pos2 = position.substring(1);

        int[] posInt = new int[2];

        for (int i = 0; i < 26; i++) {
            if (pos1.equals(_ord[i])) {
                posInt[1] = i + 1;
            }
        }
        posInt[0] = Integer.parseInt(pos2);

        return posInt;
    }
}
