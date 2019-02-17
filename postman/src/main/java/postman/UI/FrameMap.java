package postman.UI;

import postman.Address;
import postman.City;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FrameMap {
    private final HashMap<Address, JButton> existingAddresses = new HashMap<>();
    private int numberStreets;
    private int numHousesInStreet;
    private final PostmanRout postmanRout = new PostmanRout();
    private final JButton packageButton = new JButton();
    private final Color backgroundColor = new Color(242, 243, 244);
    private final JLabel clockLabel = new JLabel();

    public FrameMap(int numberStreets, int numHousesInStreet) {
        this.numberStreets = numberStreets;
        this.numHousesInStreet = numHousesInStreet;
    }

    public PostmanRout getPostmanRout() {
        return postmanRout;
    }

    public JButton getPackageButton() {
        return packageButton;
    }

    public JLabel getClockLabel() {
        return clockLabel;
    }

    public HashMap<Address, JButton> drawMap(){
        Frame frame = getFrame();
        JPanel mapPanel = getMapPanel(numberStreets, numHousesInStreet);
        frame.add(mapPanel);
        frame.setVisible(true);

        Iterator it = existingAddresses.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            JButton button = (JButton) pair.getValue();
            button.setToolTipText(button.getLocationOnScreen().toString());
            System.out.println(((JButton) pair.getValue()).getLocationOnScreen() +" "+((Address) pair.getKey()).toString());
        }
        return existingAddresses;
    }

    private Frame getFrame() {
        Frame frame = new JFrame("The Virtual World");
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        ((JFrame) frame).setGlassPane(postmanRout);
        ((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private JPanel getPackagePanel() {
        JPanel packagePanel = new JPanel();
        packagePanel.setLayout(new BorderLayout());
        packageButton.setBackground(backgroundColor);
        packageButton.setBorderPainted(false);
        packageButton.setIcon(new ImageIcon(getClass().getResource("/images/package.png")));
        packageButton.setText("Next Address");
        packagePanel.add(packageButton,BorderLayout.CENTER);
        clockLabel.setBackground(backgroundColor);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        packagePanel.add(clockLabel, BorderLayout.SOUTH);
        return packagePanel;
    }

    private JPanel getMapPanel(int numberStreets, int numHousesInStreet) {
        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new BorderLayout());
        for (City currCity : City.values()) {
            mapPanel.add(getCityPanel(currCity, numberStreets, numHousesInStreet), currCity.getBorderLayout());
        }
        JPanel packagePanel = getPackagePanel();
        mapPanel.add(packagePanel, BorderLayout.CENTER);
        return mapPanel;
    }

    private JPanel getCityPanel(City city, int numberStreets, int numHousesInStreet) {
        JPanel cityPanel = new JPanel();
        cityPanel.setBackground(backgroundColor);
        cityPanel.setLayout(new BoxLayout(cityPanel, BoxLayout.Y_AXIS));
        JLabel cityLabel = new JLabel("");
        cityLabel.setIcon(new ImageIcon(getClass().getResource("/images/"+city.getName()+".png")));
        cityPanel.add(cityLabel);

        for (int i = 1; i <= numberStreets; i++) {
            JPanel streetPanel1 = getStreetPanel(city, "street" + i, numHousesInStreet);
            cityPanel.add(streetPanel1);
        }

        return cityPanel;
    }

    private JPanel getStreetPanel(City city, String streetName, int numHousesInStreet) {
        JPanel streetPanel = new JPanel();
        streetPanel.setBackground(backgroundColor);
        JLabel streetLabel = new JLabel(streetName);
        streetPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        streetPanel.add(streetLabel);
        streetPanel.setLayout(new BoxLayout(streetPanel, BoxLayout.X_AXIS));
        streetPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        for (int i = 1; i <= numHousesInStreet; i++) {
            JButton label = new JButton(String.valueOf(i));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            streetPanel.add(label);
            existingAddresses.putIfAbsent(new Address(city, streetName, i), label);
        }
        return streetPanel;
    }
}
