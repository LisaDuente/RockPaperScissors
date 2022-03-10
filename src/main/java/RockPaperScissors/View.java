package RockPaperScissors;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

public class View implements PropertyChangeListener {

    Controller control;
    //frame and panel
    JFrame frame;
    JPanelBackground panelMenu;
    JPanelBackground panelGame;
    JPanelBackground panelLoad;
    JPanelBackground panelSave;
    //buttons
    JButton menuLoad;
    JButton menuPlay;
    JButton menuClose;
    JButton rock;
    JButton paper;
    JButton scissors;
    JButton load;
    JButton backGame;
    JButton back1;
    JButton back2;
    JButton newPlayer;
    JButton save;
    //lists
    JList<String> list;
    DefaultListModel<String> model;
    JScrollPane scroll;
    //labels
    JLabel head;
    JLabel score;
    JLabel loadStatus;
    JLabel computer;
    JLabel you;
    JLabel userInput;
    JLabel computerInput;
    JLabel computerWin;
    JLabel userWin;
    JLabel userName;
    JLabel playerName;
    //image Icons
    ImageIcon scr;
    ImageIcon scrM;
    ImageIcon rck;
    ImageIcon rckM;
    ImageIcon ppr;
    ImageIcon pprM;
    ImageIcon nope;
    //buffered Images
    BufferedImage bg;
    BufferedImage fbg;
    //text fields
    TextField userNameInput;
    TextField nameInput;
    // files
    File background;
    File fightBG;
    //fonts
    Font font;
    Font buttonFont;
    /* TO DO
        - erase all methods and classes that are no longer used
        - look into CSS to see if the frame could look better
        - save this as an executable
     */


    public View(String text, Controller control){
        //sets the look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.control = control;
        frame = new JFrame(text);

    //initiate background panels
        background = new File("src/main/resources/RPS.bmp");
        fightBG = new File("src/main/resources/FigthingBG.png");
        try {
            this.bg = ImageIO.read(background);
            this.fbg = ImageIO.read(fightBG);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panelMenu = new JPanelBackground(bg);
        BackgroundRun run = new BackgroundRun(frame,panelMenu);

        panelGame = new JPanelBackground(fbg);
        BackgroundRun run2 = new BackgroundRun(frame,panelGame);

        panelSave = new JPanelBackground(bg);
        BackgroundRun run3 = new BackgroundRun(frame,panelSave);

        panelLoad = new JPanelBackground(bg);
        BackgroundRun run4 = new BackgroundRun(frame,panelLoad);

        SwingUtilities.invokeLater(run);
        SwingUtilities.invokeLater(run2);
        SwingUtilities.invokeLater(run3);
        SwingUtilities.invokeLater(run4);

        //create font
        String fName = "src/main/resources/8-bit Arcade Out.ttf";
        try {
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, new File(fName)).deriveFont(30f);
            buttonFont = Font.createFont(Font.TRUETYPE_FONT, new File(fName)).deriveFont(20f);
            ge.registerFont(font);
            ge.registerFont(buttonFont);

        } catch (IOException|FontFormatException e) {
            e.printStackTrace();
        }

        //initiate buttons
        menuClose = new JButton("Close");
        menuLoad = new JButton("Load");
        menuPlay = new JButton("Play");
        backGame = new JButton("Back");
        backGame.setUI(new OwnButtonUI("src/main/resources/customButtonFight.png",
                "src/main/resources/customButtonFightPressed.png",
                "src/main/resources/customButtonFightHover.png"));
        backGame.setBorder(null);
        backGame.setFont(buttonFont);
        back1 = new JButton("Back");
        back2 = new JButton("Back");
        load = new JButton("Load");
        rock = new JButton("Rock");
        rock.setUI(new OwnButtonUI("src/main/resources/customButtonFight.png",
                    "src/main/resources/customButtonFightPressed.png",
                    "src/main/resources/customButtonFightHover.png"));
        rock.setBorder(null);
        rock.setFont(buttonFont);
        paper = new JButton("Paper");
        paper.setUI(new OwnButtonUI("src/main/resources/customButtonFight.png",
                "src/main/resources/customButtonFightPressed.png",
                "src/main/resources/customButtonFightHover.png"));
        paper.setBorder(null);
        paper.setFont(buttonFont);
        scissors = new JButton("Scissors");
        scissors.setUI(new OwnButtonUI("src/main/resources/customButtonFight.png",
                "src/main/resources/customButtonFightPressed.png",
                "src/main/resources/customButtonFightHover.png"));
        scissors.setFont(buttonFont);
        newPlayer = new JButton("New Player");
        save = new JButton("Save");

    //initiate images
        nope = new ImageIcon();
        pprM = new ImageIcon("src/main/resources/paperMirror.gif");
        ppr = new ImageIcon("src/main/resources/paper.gif");
        scr = new ImageIcon("src/main/resources/scissor.gif");
        scrM = new ImageIcon("src/main/resources/scissorM.gif");
        rck = new ImageIcon("src/main/resources/rock.gif");
        rckM = new ImageIcon("src/main/resources/rockM.gif");

    //initiate labels
        head = new JLabel("Welcome to Rock Paper Scissors");
        score = new JLabel("Start");
        loadStatus = new JLabel("No Player");
        you = new JLabel("No Player");
        computerInput = new JLabel(nope);
        userInput = new JLabel(nope);
        computerWin = new JLabel("wins " + 0);
        userWin = new JLabel(("wins " + 0));
        userName = new JLabel("Insert your nickname");
        playerName = new JLabel("Insert your own name");
        computer = new JLabel("Computer");

    //initiate lists
        model = new DefaultListModel<>();
        fillList();
        list = new JList<>(model);
        scroll = new JScrollPane(list);

    //initiate text fields
        userNameInput = new TextField();
        nameInput = new TextField();

    //define menu panel
        //panelMenu.setVisible(false);        //take this away after the background test
        panelMenu.setBounds(0,0,500,400);
        panelMenu.setBackground(Color.LIGHT_GRAY);

        //labels
        head.setBounds(30,25,500,50);
        head.setFont(font);

        //buttons
        menuLoad.setBounds(25,300, 75,25);
        menuPlay.setBounds(125,300,75,25);
        newPlayer.setBounds(225,300,100,25);
        menuClose.setBounds(350, 300, 75, 25);

        //action listener
        menuLoad.addActionListener((e) -> { changeToLoad(); fillList();});
        menuPlay.addActionListener(e -> changeToGame());
        newPlayer.addActionListener(e -> changeToSave());
        menuClose.addActionListener(e -> endGame() );

        //enabling
        menuPlay.setEnabled(false);

    //define load panel
        panelLoad.setBounds(0,0,500,400);
        panelLoad.setBackground(Color.LIGHT_GRAY);
        panelLoad.setVisible(false);

        //labels
        loadStatus.setBounds(50,10,325,25);
        loadStatus.setFont(font);

        //define scroll panel
        scroll.createHorizontalScrollBar();
        scroll.setBounds(50,50,375,250);

        //buttons
        load.setBounds(100,325,75,25);
        back1.setBounds (300, 325, 75,25);

        //action listener
        back1.addActionListener(e -> getBack());
        //loads a user and enables the play button
        load.addActionListener( (e) -> {control.loadUser(list.getSelectedValue()); menuPlay.setEnabled(true);});

    //define game panel
        panelGame.setBounds(0,0,500,400);
        panelGame.setBackground(Color.LIGHT_GRAY);
        panelGame.setVisible(false);

        //buttons
        rock.setBounds(20,300, 100,25);
        paper.setBounds(130,300,100,25);
        scissors.setBounds(240,300,100,25);
        backGame.setBounds (350, 300, 100,25);

        //labels
        score.setBounds(210,10,200,25);
        score.setFont(font);
        you.setBounds(40, 40, 300, 25);
        you.setFont(font);
        computer.setBounds(325, 40, 200, 25);
        computer.setFont(font);
        userWin.setBounds(40,230,200,25);
        userWin.setFont(font);
        computerWin.setBounds(325,230,250,25);
        computerWin.setFont(font);

        //labels displaying pictures
        computerInput.setBounds(350, 100, 100,100);
        userInput.setBounds(50, 100, 100,100);

        //resets the game for a new player
        backGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getBack();
                control.resetGame();
                userInput.setIcon(nope);
                computerInput.setIcon(nope);
                score.setText("Start");
            }
        });
        //sets the picture for the inputs and gets the wins from controller
        rock.addActionListener((e) -> {control.playGame("rock");
                                        computerWin.setText("wins "+ control.getWinsComputer());
                                        userWin.setText("wins " + control.getWinsUser());});
        scissors.addActionListener((e) -> {control.playGame("scissors");
                                            computerWin.setText("wins "+ control.getWinsComputer());
                                            userWin.setText("wins " + control.getWinsUser());});
        paper.addActionListener((e) -> {control.playGame("paper");
                                        computerWin.setText("wins "+ control.getWinsComputer());
                                        userWin.setText("wins " + control.getWinsUser());});

    //define panelSave
        panelSave.setBounds(0,0,500,400);
        panelSave.setBackground(Color.LIGHT_GRAY);
        panelSave.setVisible(false);

        //buttons
        save.setBounds(75, 300, 100, 25);
        back2.setBounds(275, 300, 100, 25);

        //labels
        playerName.setBounds(125, 50,200,25);
        userName.setBounds(125, 170, 200, 25);

        //text fields
        userNameInput.setBounds(125, 200, 200, 25);
        nameInput.setBounds(125, 80, 200, 25);

        //action listener
        back2.addActionListener(e -> getBack());
        //saves the user in DB and clears all text fields
        save.addActionListener((e) -> {control.savePlayer(userNameInput.getText(), nameInput.getText());
                                        userNameInput.setText("");
                                        nameInput.setText("");});


    //add to panels
        panelGame.add(rock);
        panelGame.add(paper);
        panelGame.add(scissors);
        panelGame.add(backGame);
        panelGame.add(score);
        panelGame.add(you);
        panelGame.add(computer);
        panelGame.add(computerInput);
        panelGame.add(userInput);
        panelGame.add(userWin);
        panelGame.add(computerWin);

        panelLoad.add(load);
        panelLoad.add(back1);
        panelLoad.add(scroll);
        panelLoad.add(loadStatus);

        panelMenu.add(menuLoad);
        panelMenu.add(menuPlay);
        panelMenu.add(menuClose);
        panelMenu.add(newPlayer);
        panelMenu.add(head);

        panelSave.add(userNameInput);
        panelSave.add(save);
        panelSave.add(back2);
        panelSave.add(userName);
        panelSave.add(playerName);
        panelSave.add(nameInput);


    //add to frame
        frame.add(panelMenu);
        frame.add(panelLoad);
        frame.add(panelGame);
        frame.add(panelSave);

        frame.setSize(500,438);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void changeToLoad(){
        panelMenu.setVisible(false);
        panelLoad.setVisible(true);
    }

    public void changeToGame(){
        panelMenu.setVisible(false);
        panelGame.setVisible(true);
    }

    public void getBack(){
        panelMenu.setVisible(true);
        panelLoad.setVisible(false);
        panelGame.setVisible(false);
        panelSave.setVisible(false);
    }

    public void changeToSave(){
        panelSave.setVisible(true);
        panelMenu.setVisible(false);
        panelLoad.setVisible(false);
        panelGame.setVisible(false);
    }

    public void endGame(){
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        System.exit(0);
    }

    //make this in control?
    public void fillList(){
        model.clear();
        control.updateHashmap();
        int i = 0;
        for(String key : control.getMap().keySet()){
            model.add(i,key);
            i++;
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()){
            case "input":
                //set right picture in playPanel
                if(evt.getNewValue().equals("scissors")){
                    userInput.setIcon(scr);
                }else if(evt.getNewValue().equals("rock")){
                    userInput.setIcon(rck);
                }else if(evt.getNewValue().equals("paper")){
                    userInput.setIcon(ppr);
                }else{
                    userInput.setIcon(nope);
                }
                break;
            case "message":
                score.setText(evt.getNewValue().toString());
                break;
            case "inputComputer":
                //set right picture in playPanel
                if(evt.getNewValue().equals("scissors")){
                    computerInput.setIcon(scrM);
                }else if(evt.getNewValue().equals("rock")){
                    computerInput.setIcon(rckM);
                }else if(evt.getNewValue().equals("paper")){
                    computerInput.setIcon(pprM);
                }else{
                    computerInput.setIcon(nope);
                }
                break;
            case "currentUserName":
                //set the current userName in loadPanel and gamePanel
                loadStatus.setText(evt.getNewValue().toString());
                you.setText(evt.getNewValue().toString());
                break;
        }


    }
}
