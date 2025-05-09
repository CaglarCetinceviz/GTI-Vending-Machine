/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vendingMachine;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static vendingMachine.MainGUI.*;
import static vendingMachine.Stock.*;
import static vendingMachine.Float.*;

/**
 *
 * @author cagla
 */
public class Methods {

    // Thanks message after succesfull buying
    public static void ThanksMessage() {
        JOptionPane.showMessageDialog(null, "Thank you for shopping");
    }

    // Message for add extra money
    public static void AddMoney() {
        JOptionPane.showMessageDialog(null, "Not enough money. Add coins");
    }

    // Message for no stock
    public static void NoProduct() {
        JOptionPane.showMessageDialog(null, "Not enough product");
    }

    // Message Dispense the change
    public static void TakeChange() {
        JOptionPane.showMessageDialog(null, "Please, Take your change");
    }

    // Sends Restocking Message to suplier
    public static void RestockingMessage() {
        JOptionPane.showMessageDialog(null, "Restocking Message Sent");
    }

    // Message for card payment
    public static void CartPayment() {
        JOptionPane.showMessageDialog(null, "Please make payment by card in 5 seconds");
    }

    // To enable the product buttons after succesfull purchase
    public static void EnableBTN(JButton button, boolean choice) {
        button.setEnabled(choice);
    }
    
    // Disable/Enable the Dispensed Product Label
    public static void TriggerDispensedLabel(JLabel product, boolean value) {
        product.setVisible(value);
    }
    

    //Coin stock balance
    public static void StockBalance(double currentMoney) {
        while ((currentMoney >= 2) && (twoEuroStock > 0)) {
            twoEuroStock--;
            currentMoney -= 2;
        }

        while ((currentMoney >= 1) && (oneEuroStock > 0)) {
            oneEuroStock--;
            currentMoney -= 1;
        }

        while ((currentMoney >= 0.5) && (fiftyCentStock > 0)) {
            fiftyCentStock--;
            currentMoney -= 0.5;
        }

        while ((currentMoney >= 0.19) && (twentyCentStock > 0)) {
            twentyCentStock--;
            currentMoney -= 0.2;
        }
    }

    // Disable/Enable the Buttons after selection
    public static void TriggerAllButtons(JButton coke, JButton fanta, JButton mineral, JButton water, JButton beer, JButton milk, boolean value) {
        coke.setEnabled(value);
        fanta.setEnabled(value);
        mineral.setEnabled(value);
        water.setEnabled(value);
        beer.setEnabled(value);
        milk.setEnabled(value);
    }

    // Disable/Enable the Coin Buttons During Card payment
    public static void TriggerAllCoinButtons(JButton one, JButton two, JButton fiftycent, JButton twentycent, boolean value) {
        one.setEnabled(value);
        two.setEnabled(value);
        fiftycent.setEnabled(value);
        twentycent.setEnabled(value);
    }

    // Disables all Products Label for Dispensed Products when the window opened
    public static void DisableAllLabels(JLabel cola, JLabel fanta, JLabel soda, JLabel water, JLabel beer, JLabel milk) {
        cola.setVisible(false);
        fanta.setVisible(false);
        soda.setVisible(false);
        water.setVisible(false);
        beer.setVisible(false);
        milk.setVisible(false);
    }

    // Disable/Enable Single Button
    public static void TriggerSingleButton(JButton name, boolean value) {
        name.setEnabled(value);
    }
    
    // Current money increment
    public static double addCash(double value) {
        return currentMoney += value;
    }
    
    // If money is bigger then 0 refunds money during cancel
    public static void CancelReturn(double money) {
        if (money > 0) {
            TakeChange();
        }
    }

    // refill new products with current one
    public static void StockRefill() {
        cokeStock += cokeOrder;
        fantaStock += fantaOrder;
        sodaStock += mineralOrder;
        waterStock += waterOrder;
        beerStock += beerOrder;
        milkStock += milkOrder;
    }
    
    // refill new floats with current one
    public static void FloatRefill(){
    twoEuroStock += twoEuro;
    oneEuroStock += oneEuro;
    fiftyCentStock += fiftyCent;
    twentyCentStock += twentyCent;
    }

    // Clears order for stock window
    public static void CleanOrder() {
        cokeOrder = 0;
        fantaOrder = 0;
        mineralOrder = 0;
        waterOrder = 0;
        beerOrder = 0;
        milkOrder = 0;
    }
    // Clears order for float window
    public static void CleanFloat() {
    twoEuro = 0;
    oneEuro = 0;
    fiftyCent = 0;
    twentyCent = 0;
    }

}
