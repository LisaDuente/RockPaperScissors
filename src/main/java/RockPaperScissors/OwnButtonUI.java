package RockPaperScissors;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OwnButtonUI extends BasicButtonUI implements MouseListener {
    BufferedImage buttonImg;
    BufferedImage buttonIdle;
    BufferedImage buttonPressed;
    BufferedImage buttonMouseEntered;
    File sourceButton;
    File sourceButtonPressed;
    File sourceButtonHover;


    public OwnButtonUI(String pathButtonIdle, String pathButtonPressed, String pathButtonHover){
        sourceButton = new File(pathButtonIdle);
        sourceButtonPressed = new File(pathButtonPressed);
        sourceButtonHover = new File(pathButtonHover);
        try {
            buttonImg = ImageIO.read(sourceButton);
            buttonIdle = ImageIO.read(sourceButton);
            buttonPressed = ImageIO.read(sourceButtonPressed);
            buttonMouseEntered = ImageIO.read(sourceButtonHover);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        Dimension d = b.getSize();

        super.paint(g, b);

        g.drawImage(buttonImg, 0, 0, b);
        g.setFont(b.getFont());
        FontMetrics fm = g.getFontMetrics();

        String caption = b.getText();
        //finds the middle of the button
        int x = (d.width - fm.stringWidth(caption)) / 2;
        int y = (d.height + fm.getAscent()) / 2;
        g.drawString(caption, x, y);
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        c.addMouseListener(this);
    }

    public void uninstallUI(JComponent c) {
        super.uninstallUI(c);
        c.removeMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JComponent c = (JComponent) e.getComponent();
        buttonImg = buttonMouseEntered;
        c.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JComponent c = (JComponent) e.getComponent();
        buttonImg = buttonPressed;
        c.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JComponent c = (JComponent) e.getComponent();
        buttonImg = buttonIdle;
        c.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JComponent c = (JComponent) e.getComponent();
        buttonImg = buttonMouseEntered;
        c.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JComponent c = (JComponent) e.getComponent();
        buttonImg = buttonIdle;
        c.repaint();
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(buttonImg.getHeight(), buttonImg.getWidth());
    }



    public void setButtonImg(BufferedImage buttonImg) {
        this.buttonImg = buttonImg;
    }

    public void setButtonPressed(BufferedImage buttonPressed) {
        this.buttonPressed = buttonPressed;
    }
}