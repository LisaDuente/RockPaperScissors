package RockPaperScissors;
import java.util.TreeMap;

public class Controller {
    private Model model;

    public Controller(Model model){
        this.model = model;

    }

    public void playGame(String input){
        if(input != null) {
            if (this.model.getUser() != null) {
                switch (input) {
                    case "scissors":
                        this.model.setInputUser("scissors");
                        this.model.getGame().setInput("scissors");
                        break;
                    case "rock":
                        this.model.setInputUser("rock");
                        this.model.getGame().setInput("rock");
                        break;
                    case "paper":
                        this.model.setInputUser("paper");
                        this.model.getGame().setInput("paper");
                        break;
                    default:
                        this.model.setError("Controller::setInput:error");
                }
                this.model.getGame().setComputer();
                this.model.setInputComputer(this.model.getGame().getComputer());
                this.model.getGame().compareChoice();
                this.model.getGame().checkWin();

                if (!this.model.getGame().isRunning() && this.model.getGame().getWinUser() == 3) {
                    this.model.setMessage("You won");
                    this.model.getUser().setWin(this.model.getUser().getWin() + 1);
                    this.model.getSaver().saveAUser(this.model.getUser(), this.model.getCurrentUserName());
                    this.model.getGame().resetGame();
                } else if (!this.model.getGame().isRunning() && this.model.getGame().getWinComputer() == 3) {
                    this.model.setMessage("You lost");
                    this.model.getUser().setLoose(this.model.getUser().getLoose() + 1);
                    this.model.getSaver().saveAUser(this.model.getUser(), this.model.getCurrentUserName());
                    this.model.getGame().resetGame();
                }
            } else {
                this.model.setMessage("Please choose a user first!");
            }
        }
    }

    public void loadUser(String s){
        if(s != null) {
            if(this.model.getSaver().getMap().containsKey(s)) {
                this.model.setUser(this.model.getSaver().buildAUser(s));
                this.model.setCurrentUserName(s);
            }else{
                this.model.setError("There is no user "+s);
            }
        }else{
            this.model.setError("Please choose a user first!");
        }
    }

    public void savePlayer(String playerUserName, String playerName){
        if(!this.model.getSaver().getMap().containsKey(playerUserName)){
            if(playerUserName.length()>0 && playerName.length()>0) {
                if(playerUserName.length()<=100 && playerName.length()<=50){
                    this.model.getSaver().newUser(playerUserName, playerName);
                }else{
                    this.model.setError("User name to long!");
                }
            }else{
                this.model.setError("User name too short!");
            }
        }else{
            this.model.setError("User already exists!");
        }

    }

    public void updateHashmap(){
        this.model.getSaver().refreshMap();
    }

    public void resetGame(){
        this.model.resetGameStatus();
    }

    public TreeMap<String, String> getMap(){
        return this.model.getSaver().getMap();
    }

    public int getWinsUser(){
        return this.model.getGame().getWinUser();
    }

    public int getWinsComputer(){
        return this.model.getGame().getWinComputer();
    }

}
