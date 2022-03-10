package RockPaperScissors;
import java.util.TreeMap;

public class Controller {
    private Model model;

    public Controller(Model model){
        this.model = model;

    }

    public void playGame(String input){
        if(this.model.getUser() != null){
            switch(input){
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
                    System.out.println("Controller::setInput:error");
            }
            this.model.getGame().setComputer();
            this.model.setInputComputer(this.model.getGame().getComputer());
            this.model.getGame().compareChoice();
            this.model.getGame().checkWin();

            if(!this.model.getGame().isRunning() && this.model.getGame().getWinUser() == 3){
                this.model.setMessage("You won");
                this.model.getUser().setWin(this.model.getUser().getWin()+1);
                this.model.getSaver().saveAUser(this.model.getUser(), this.model.getCurrentUserName());
                this.model.getGame().resetGame();
            }else if( !this.model.getGame().isRunning() && this.model.getGame().getWinComputer() == 3){
                this.model.setMessage("You lost");
                this.model.getUser().setLoose(this.model.getUser().getLoose()+1);
                this.model.getSaver().saveAUser(this.model.getUser(), this.model.getCurrentUserName());
                this.model.getGame().resetGame();
            }
        }else{
            this.model.setMessage("Please choose a user first!");
        }
    }

    public void loadUser(String s){
        if(s != null) {
            this.model.setUser(this.model.getSaver().buildAUser(s));
            this.model.setCurrentUserName(s);
        }
    }

    public void savePlayer(String playerUserName, String playerName){
        if(playerUserName.length()>0 && playerName.length()>0) {
            this.model.getSaver().newUser(playerUserName, playerName);
        }
    }

    public void updateHashmap(){
        this.model.getSaver().refreshMap();
    }

    public void resetGame(){
        this.model.getGame().resetGame();
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
