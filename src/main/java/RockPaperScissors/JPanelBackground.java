package RockPaperScissors;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class JPanelBackground extends JPanel {

    private Image background;

    public JPanelBackground(Image image){
        this.background = image;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background,0,0,this);
    }

}
