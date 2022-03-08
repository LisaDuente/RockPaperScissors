package RockPaperScissors;

public class Main {
    public static void main (String[] args) {
        //small tests
        //DBHandler db = new DBHandler();
        //HashMap<String, String> map = db.getData();
        //System.out.println(map.keySet() + " "+ map.values());
        //db.insertUser("NeonPinky","Lisa");
        //GameSaveDB saver = new GameSaveDB();


        //Playing the game
        Model model = new Model();
        Controller control = new Controller(model);
        View view = new View("Rock Paper Scissors", control);
        model.addPcl(view);
        //control.gameLoop();

    }

}