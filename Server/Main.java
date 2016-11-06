public class Main {
    public static void main(String[] args) {
        int port;

        if(args.length != 1) {
            System.out.println("Please add port number");
            return;
        }

        port = Integer.parseInt(args[0]);
        PazerfausterServer server = new PazerfausterServer(port);
        Thread t = new Thread(server);
        t.start();
    }
}