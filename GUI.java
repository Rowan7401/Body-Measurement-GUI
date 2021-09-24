import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    int maintenenceCal = 2000;
    int height = 69;
    int weight = 150;
    int BMI = 0;
    int workoutsCount = 0;

    private JLabel calLabel;
    private JLabel heightLabel;
    private JLabel weightLabel;
    private JLabel BMILabel;
    private JLabel  BMIIndicator;
    private JLabel workoutsLabel;

    private JFrame frame;
    private JPanel panel;
    private JTextField workouts;

    private JButton heightButtonUp;
    private JButton heightButtonDown;
    private JButton weightButtonUp;
    private JButton weightButtonDown; 

    public GUI() {
        frame = new JFrame();

        heightButtonUp = new JButton("Height+");
        heightButtonDown = new JButton("Height-");
        weightButtonUp = new JButton("Weight+");
        weightButtonDown = new JButton("Weight-");

        heightButtonUp.addActionListener(this);
        heightButtonDown.addActionListener(this);
        weightButtonUp.addActionListener(this);
        weightButtonDown.addActionListener(this);

        workouts  = new JTextField("(Type number of workouts here)" , 10);
        workouts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String workoutCount = workouts.getText();
                int workoutsInt = Integer.parseInt(workoutCount);

                if (workoutsInt > 0 && workoutsInt <= 4) {
                    maintenenceCal = weight * 15;
                }
                else if (workoutsInt > 4) {
                    maintenenceCal = weight * 18;
                }
                else {
                    maintenenceCal = weight * 13;
                }

                workoutsLabel.setText("Workouts per week: " + workoutCount);
                calLabel.setText("Maintenence Calories: " + maintenenceCal);
            }
        });
        

        heightLabel = new JLabel("Height: 5'9\"");
        weightLabel = new JLabel("Weight:  150");
        workoutsLabel = new JLabel("How many workouts per week do you do? ");
        BMILabel = new JLabel("BMI: 0");
        BMIIndicator = new JLabel("Your BMI is ");
        calLabel = new JLabel("Maintenence Calories: ");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.setForeground(Color.yellow);
        panel.setBackground(Color.green);
        panel.add(heightButtonUp);
        panel.add(heightButtonDown);
        panel.add(weightButtonUp);
        panel.add(weightButtonDown);
        panel.add(heightLabel);
        panel.add(weightLabel);
        panel.add(workoutsLabel);
        panel.add(workouts);
        panel.add(BMILabel);
        panel.add(BMIIndicator);
        panel.add(calLabel);
        

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Gainz Tracker");
        frame.pack();
        frame.setVisible(true);
        
        
    }
    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == heightButtonUp) {
            height++;
        } 
        else if (source == heightButtonDown) {
            height--;
        }
        else if (source == weightButtonUp) {
            weight++;
        }
        else if (source == weightButtonDown) {
            weight--;
        }

        int inchCount = height % 12;
        int feetCount = height / 12;

        double roughBMI = ((weight / Math.pow(height , 2)) * 703);
        double roundedBMI = (int) (roughBMI * 100);
        double BMI = roundedBMI/100;
        String BMIText = "";

        if (BMI > 25) {
            BMIText += "above the reccomended value.";
        }
        else if (BMI < 19) {
            BMIText += "below the reccomended value.";
        }
        else {
            BMIText += "a healthy value!";
        }
        
        heightLabel.setText("Height: " + feetCount + "'" + inchCount + "\"");
        weightLabel.setText("Weight: " + weight + " lbs");
        BMILabel.setText("BMI: " + BMI);
        BMIIndicator.setText("Your BMI is " + BMIText);
        
    }
    
}