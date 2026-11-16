package pd11;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            new SalesApplication().run();
        } catch (IOException e) {
            System.err.println("Can't find file: " + e.getMessage());
        }
    }
}