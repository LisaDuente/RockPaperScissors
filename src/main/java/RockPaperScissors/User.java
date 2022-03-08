package RockPaperScissors;

import java.util.Scanner;

public class User {
    private String name;
    private int win;
    private int loose;

    public User(String name, int win, int loose){
        this.name = name;
        this.win = win;
        this.loose = loose;
    }

    public User(){
        this.win = 0;
        this.loose = 0;
    }

    public void chooseName(){
        System.out.println("Choose your name: ");
        Scanner scanner = new Scanner(System.in);
        this.name = scanner.next();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String showStats(){
        return "Wins: " + this.win + " | Lost: " + this.loose;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getWin() {
        return win;
    }

    public void setLoose(int loose) {
        this.loose = loose;
    }

    public int getLoose() {
        return loose;
    }
}

