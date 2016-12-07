public class GameState {

    private static GameState state = new GameState();
    private static boolean isRunning = false;

    public static GameState getState() {
        return state;
    }

    public static void startUDPServer() {
        if(isRunning) {
            System.out.println("The game has already started");
            return;
        }

        new Thread(new Runnable() {
            public void run() {
                while(true) {
                    System.out.println("AAAAAAAHH");
                }
            }
        }){}.start();
    }

    public void broadcastUDP() {

    }

    private GameState() {}
}