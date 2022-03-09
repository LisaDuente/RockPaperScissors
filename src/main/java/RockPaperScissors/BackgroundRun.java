package RockPaperScissors;

import javax.swing.*;
import java.awt.*;

public class BackgroundRun implements Runnable{

    JFrame frame;
    JPanelBackground panel;

    public BackgroundRun(JFrame frame, JPanelBackground panel){
        this.frame = frame;
        this.panel = panel;
    }

    @Override
    public void run() {
        panel.setLayout(null);
        panel.setBounds(0,0,500,700);
    }

    public JPanelBackground getPanel(){
        return this.panel;
    }
}
