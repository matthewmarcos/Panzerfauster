public class Main {

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        PazerfausterServer server = new PazerfausterServer(port);
    }
}