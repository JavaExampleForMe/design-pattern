package postman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

public class PostmanRout extends JPanel {

    private int secondsCounter;

    public PostmanRout() {
        setOpaque(false);
        setLayout(null);
        secondsCounter = 0;
    }

    public Point sendPostman(Point sourcePoint, Point destinationPoint, JLabel clockLabel) {
        JLabel postmanLabel = new JLabel();
        postmanLabel.setLocation(sourcePoint);
        add(postmanLabel);

        ImageIcon postmanIcon = new ImageIcon(getClass().getResource("/images/postman.png"));
        postmanLabel.setIcon(postmanIcon);
        postmanLabel.setSize(postmanLabel.getPreferredSize());
        final int postmanIconHeight = postmanIcon.getIconHeight();
        Timer timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point point = postmanLabel.getLocation();
                if (point.x < destinationPoint.x) {
                    point.x += 1;
                }else {
                    if (point.x > destinationPoint.x) {
                        point.x -= 1;
                    }
                }
                if (point.y < (destinationPoint.y - postmanIconHeight)) {
                    point.y += 1;
                }else {
                    if (point.y > (destinationPoint.y - postmanIconHeight)) {
                        point.y -= 1;
                    }
                }
                postmanLabel.setLocation(point);
              //  clockLabel.setText(String.valueOf(secondsCounter++));
                repaint();
            }

        });
        timer.start();
        setVisible(true);
        loopWhileDidNotReachToDestination(destinationPoint, postmanLabel, postmanIconHeight);
        timer.stop();
        Point lastPostmanPoint = postmanLabel.getLocation();
        leavePackage(destinationPoint, postmanLabel);
        return lastPostmanPoint;
    }

    private void loopWhileDidNotReachToDestination(Point destinationPoint, JLabel postmanLabel, int postmanIconHeight) {
        while (postmanLabel.getLocation().x != destinationPoint.x || (postmanLabel.getLocation().y != destinationPoint.y - postmanIconHeight)) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void leavePackage(Point destinationPoint, JLabel postmanLabel) {
        ImageIcon packageIcon = new ImageIcon(getClass().getResource("/images/smallPackage.png"));
        postmanLabel.setIcon(packageIcon);
        postmanLabel.setLocation(new Point(destinationPoint.x, destinationPoint.y - packageIcon.getIconHeight() - 28) );
        repaint();
    }

}

