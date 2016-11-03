public class Main {
    public static void main(String[] args) {

        if(args.length != 3) {
            System.out.println("Usage: <ip> <port> <name>");
        }

        try {
            new Client(
                args[0],
                Integer.parseInt(args[1]),
                args[2]
            );
        }
        catch (Exception e) {
            System.out.println("Error in Client");
        }
    }
}