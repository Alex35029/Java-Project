import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ChangeCalculator
{
    public int chgLeft;
    public int coinBucketValue;
    JLabel lblChangeDisp = new JLabel();
    int [] buckets = new int [] {50, 20, 10, 5};
    int [] coinsInBucket = new int [] {1, 2, 4, 10};
    //int [] coinsEntered = new int [] {0, 0, 0, 0};
    int [] changeGiven = new int [] {0, 0, 0, 0};
        
    public ChangeCalculator(int chgDue) //int paid, int cost, int chgDue)
    {
        //AmtPaid = paid;
        //DrinkCost = cost;
    }
    
    public ChangeCalculator(int[] coinsEntered, int chgReq, int[] changeGiven) //int paid, int cost, int chgDue)
    {
        chgLeft = chgReq;
        for (int i = 0; i < 4; i++) {
            coinsInBucket[i] += coinsEntered[i];
        }
        
        for (int i = 0; i < coinsEntered.length; i++) {        
            if  (coinsInBucket[i] > 0) {
                coinBucketValue = coinsInBucket[i] * buckets[i];
                if (coinBucketValue > chgLeft && chgLeft >= buckets[i]) {
                    while (chgLeft >= buckets[i]) {
                        chgLeft -= buckets[i];
                        changeGiven[i]++;
                        if (chgLeft <= 0) {
                           break;
                        }
                    }
                }                       
            }
        }
    }

    public static void main(String[] args) {
    }
}