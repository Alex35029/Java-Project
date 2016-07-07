import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

class VendingMachine extends JFrame
                                  implements ActionListener
{
    JButton btnCoffee = new JButton("Coffee: 50p");
    JButton btnTea = new JButton("Tea: 40p");  
    JButton btnLatte = new JButton("Latte: 70p");
    JButton btnCappuccino = new JButton("Cappuccino: 70p");  
    JButton btnCoffeeMilk = new JButton("Coffee with milk: 50p");    
    JButton btnTeaMilk = new JButton("Tea with milk: 40p");    
    JButton btnDispense = new JButton("Dispense");
    JButton btnReset = new JButton("Reset");
    JButton btn5p = new JButton("5p");
    JButton btn10p = new JButton("10p"); 
    JButton btn20p = new JButton("20p");   
    JButton btn50p = new JButton("50p"); 
    JButton btnHelp = new JButton("Help");
    JLabel lblDrinkSelected = new JLabel("                                             Drink Selected:");
    JLabel lblDrink = new JLabel();
    JLabel lblAmountPaid = new JLabel("                                                Amount Paid:");
    JLabel lblCoinInput = new JLabel();
    JLabel lblChangeGiven = new JLabel("                                              Change Given:");
    JLabel lblChangeDisp = new JLabel(); 
    JLabel lblHelp = new JLabel("Select drink > click coins > 'Dispense' Drink and change");
    JLabel lbl5p = new JLabel("                                                                   5p:");
    JLabel lbl5p_change = new JLabel();
    JLabel lbl10p = new JLabel("                                                                 10p:");
    JLabel lbl10p_change = new JLabel();
    JLabel lbl20p = new JLabel("                                                                 20p:");
    JLabel lbl20p_change = new JLabel();
    JLabel lbl50p = new JLabel("                                                                 50p:");
    JLabel lbl50p_change = new JLabel();
    
    int CoinSelected;
    int coinEnteredValue;
    int chgReq;
    int chgDue;
    int CoinValue;
    int chgLeft;
    boolean DrinkChosen = false;
    double drinkSelected;
    boolean CheckSelected = false;
    int [] coinsEntered = new int [] {0, 0, 0, 0};
    int [] changeGiven = new int [] {0, 0, 0, 0};   
    int [] coinsInBucket = new int [] {0, 0, 0, 0};
    
    int StepCounter = 0;
    
    double coffee;
    //double 
    
    public static int drinkCost;
    public static int AmtPaid;
    
    public static void main(String[] args)
    {
        new VendingMachine();
    }

    public VendingMachine() 
    {
        setLayout(new BorderLayout());
        setSize(600, 350);
        setTitle("Vending Machine simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel top = new JPanel();
        top.add(btn5p);
        btn5p.addActionListener(this);
        top.add(btn10p);
        btn10p.addActionListener(this);
        top.add(btn20p);
        btn20p.addActionListener(this);
        top.add(btn50p);
        btn50p.addActionListener(this);
        add("North",top);   
        
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(0, 2));
        center.add(btnCoffee);
        btnCoffee.addActionListener(this);    
        center.add(btnTea);
        btnTea.addActionListener(this);
        center.add(btnCappuccino);
        btnCappuccino.addActionListener(this);
        center.add(btnLatte);
        btnLatte.addActionListener(this);
        center.add(btnCoffeeMilk);
        btnCoffeeMilk.addActionListener(this);
        center.add(btnTeaMilk);
        btnTeaMilk.addActionListener(this);
        center.add(lblDrinkSelected);
        center.add(lblDrink);

        center.add(lblAmountPaid);
        center.add(lblCoinInput);
        center.add(lblChangeGiven);
        center.add(lblChangeDisp); 
        center.add(lbl5p);
        center.add(lbl5p_change);
        center.add(lbl10p);
        center.add(lbl10p_change);
        center.add(lbl20p);
        center.add(lbl20p_change);
        center.add(lbl50p);
        center.add(lbl50p_change);
        add("Center",center);   
        
        JPanel bottom = new JPanel();
        bottom.add(btnDispense);
        btnDispense.addActionListener(this);
        bottom.add(btnReset);
        btnReset.addActionListener(this);
        bottom.add(lblHelp);
        add("South",bottom);
        btnDispense.setEnabled(false);
              
        setResizable(false);
        setVisible(true);      
    }
       
    public void actionPerformed(ActionEvent e) {            
        if (e.getSource () == btnReset) {
            btnDispense.setEnabled(false);
            btnCoffee.setEnabled(true);
            btnTea.setEnabled(true);
            btnLatte.setEnabled(true);
            btnCappuccino.setEnabled(true);
            btnCoffeeMilk.setEnabled(true);
            btnTeaMilk.setEnabled(true);
            btn5p.setEnabled(false);
            btn10p.setEnabled(false);
            btn20p.setEnabled(false);
            btn50p.setEnabled(false);
            drinkCost = 0;
            CoinValue = 0;
            AmtPaid = 0;
            lblDrink.setText(" ");
            lblCoinInput.setText(" ");
            Arrays.fill(changeGiven, 0);
            lblChangeDisp.setText("");  
            lbl5p_change.setText("");
            lbl10p_change.setText("");
            lbl20p_change.setText("");
            lbl50p_change.setText("");
        }
            
        if (e.getSource () == btn10p) {
            CoinValue = 10;
            coinsEntered[2]++;            
            CheckSelected();
        }
        
        if (e.getSource() == btn5p) {
            CoinValue = 5;      
            coinsEntered[3]++;            
            CheckSelected();
        }
           
        if (e.getSource() == btn20p) {
            CoinValue = 20;  
            coinsEntered[1]++;
            CheckSelected();
        }
            
        if (e.getSource() == btn50p) {
            CoinValue = 50;      
            coinsEntered[0]++;
            CheckSelected();
        }            
        
        if (e.getSource() == btnCoffee) {
            DrinkSelected();
            drinkCost = 50;
            lblDrink.setText("Coffee");
            double coffee;
        }
        
        if (e.getSource() == btnTea) {
            DrinkSelected();
            drinkCost = 40;
            lblDrink.setText("Tea");
            double tea;
        }
        
        if (e.getSource() == btnCappuccino) {
            DrinkSelected();
            drinkCost = 70;
            lblDrink.setText("Cappuccino");
            double cappuccino;
        }
        
        if (e.getSource() == btnLatte) {
            DrinkSelected();
            drinkCost = 70;
            lblDrink.setText("Latte");
            double latte;
        }
        
        if (e.getSource() == btnCoffeeMilk) {
            DrinkSelected();
            drinkCost = 50;
            lblDrink.setText("Coffee with milk");
            double coffeeMilk;
        }
        
        if (e.getSource() == btnTeaMilk) {
            DrinkSelected();
            drinkCost = 40;
            lblDrink.setText("Tea with milk");
            double teaMilk;
        }
        
        if (e.getSource () == btnDispense) {
            String[] coinMsg = {"50p", "20p", "10p", "5p"};
            String msg = "";
            for (int i = 0; i < changeGiven.length; i++) {
                msg += "" + changeGiven[i] + " x " + coinMsg[i] + " ";
            }
            
            JOptionPane.showMessageDialog(null, "Here is your drink and change: " + msg + "- Click reset");
            btnReset.setEnabled(true);
            btnDispense.setEnabled(false);
        }
    }
    
    private void DrinkSelected ()  {
        DrinkChosen = true;
        btnDispense.setEnabled(false);
        btnCoffee.setEnabled(false);
        btnTea.setEnabled(false);
        btnLatte.setEnabled(false);
        btnCappuccino.setEnabled(false);
        btnCoffeeMilk.setEnabled(false);
        btnTeaMilk.setEnabled(false); 
        btn5p.setEnabled(true);
        btn10p.setEnabled(true);
        btn20p.setEnabled(true);
        btn50p.setEnabled(true);
    }
       
    private void CheckSelected () {
        if (DrinkChosen == false) {
            JOptionPane.showMessageDialog(null, "Error msg: Please select drink first, " + "change: " + CoinValue);
            btnDispense.setEnabled(false);
            btnReset.setEnabled(false);
        }
        else {  
            AmtPaid += CoinValue;            
            lblCoinInput.setText("" + AmtPaid);            
        }
            
        if(AmtPaid >= drinkCost) {    
            chgReq = AmtPaid - drinkCost;           
            ChangeCalculator chgDue = new ChangeCalculator(coinsEntered, chgReq, changeGiven);
            lblChangeDisp.setText("" + chgReq);             
            lbl5p_change.setText("" + changeGiven[3]);
            lbl10p_change.setText("" + changeGiven[2]);
            lbl20p_change.setText("" + changeGiven[1]);
            lbl50p_change.setText("" + changeGiven[0]);              
            btnDispense.setEnabled(true);
            if (AmtPaid > drinkCost) {
                btn50p.setEnabled(false);
                btn20p.setEnabled(false);
                btn10p.setEnabled(false);
                btn5p.setEnabled(false);
                btnReset.setEnabled(false);
            }
        }
    }
}             