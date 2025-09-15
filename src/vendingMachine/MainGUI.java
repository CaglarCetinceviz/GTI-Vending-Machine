/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vendingMachine;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;
import static vendingMachine.Methods.*;

/**
 *
 * @author cagla
 */
public class MainGUI extends javax.swing.JFrame {

    // Products stocks
    public static int cokeStock = 5;
    public static int fantaStock = 6;
    public static int sodaStock = 7;
    public static int waterStock = 4;
    public static int beerStock = 8;
    public static int milkStock = 4;
    // index 0 is coke, 1 is fanta, 2 is soda, 3 is water, 4 is beer, 5 is milk


    // Coin Stocks Count, They not double because they are count not value
    public static int twoEuroStock = 5;
    public static int oneEuroStock = 5;
    public static int fiftyCentStock = 10;
    public static int twentyCentStock = 15;

    // Current Money
    public static double currentMoney = 0.0;

    public static double debt = 0;

    // Sets the Text for stocking
    public void RefillSetText() {
        colaStockTF.setText(cokeStock + "");
        fantaStockTF.setText(fantaStock + "");
        sodaStockTF.setText(sodaStock + "");
        waterStockTF.setText(waterStock + "");
        beerStockTF.setText(beerStock + "");
        milkStockTF.setText(milkStock + "");
    }

    // Sets the money stocks
    public void MoneyStockSetText() {
        amountOfTwoEuroTF.setText(twoEuroStock + "");
        amountOfOneEuroTF.setText(oneEuroStock + "");
        amountOfFiftyCentTF.setText(fiftyCentStock + "");
        amountOfTwentyCentTF.setText(twentyCentStock + "");
    }

    // Sets the stocks and 
    public void WindowOpened() {
        RefillSetText();
        MoneyStockSetText();
        CurrentMoneyAfterAdding();
        DisableAllLabels(colaLabel, fantaLabel, sodaLabel, waterLabel, beerLabel, milkLabel);
        EnableBTN(cardBTN, false);
    }

    // Current Money text format
    public void CurrentMoneyAfterAdding() {
        currentAmountTF.setText(String.format("%.2f", currentMoney));
    }

    public void CurrentAmountClear() {
        currentAmountTF.setText("");
    }

    public void DispenseAmountClear() {
        dispenseAmountBTN.setText("");
    }

    public void SetText(JTextField name, int value) {
        name.setText(value + "");
    }

    public void SetToZero() {
        currentMoney = 0.0;
        debt = 0.0;
    }

    //Purchacing Button event on Action
    /**
     *
     * @param stock
     * @param textField
     * @param productLabel
     * @return
     */
    public int ProductBuying(int stock, JTextField textField, JLabel productLabel) {

        //Timer class
        Timer timer = new Timer(5000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
               // Checks that Card Payment is equal the debt
                if (currentMoney == debt) {
                    TriggerDispensedLabel(productLabel, true);
                    CurrentAmountClear();
                    TriggerAllButtons(colaBTN, fantaBTN, sodaBTN, waterBTN, beerBTN, milkBTN, true);
                    TriggerAllCoinButtons(twoEuroBTN, oneEuroBTN, fiftyCentBTN, twentyCentBTN, true);
                    ThanksMessage();
                    // problem is a problem
//                            stock--;
//                            SetText(textField, stock);

                } // if card payment is not succesfull
                else {
                    twoEuroBTN.requestFocus();
                    cardBTN.setBackground(new Color(187, 187, 187));
                    TriggerSingleButton(cardBTN, false);
                    SetToZero();
                    CurrentAmountClear();
                    DispenseAmountClear();
                    AddMoney();
                    TriggerAllCoinButtons(twoEuroBTN, oneEuroBTN, fiftyCentBTN, twentyCentBTN, true);
                    TriggerAllButtons(colaBTN, fantaBTN, sodaBTN, waterBTN, beerBTN, milkBTN, true);
                }
                TriggerDispensedLabel(productLabel, false);
                TriggerSingleButton(cardBTN, false);
                SetToZero();

                }
        });

        // Checks the stock is available
        if (!(stock <= 0)) {
            // Checks inserted money is enough
            if (!(currentMoney < debt)) {
                currentMoney -= debt;
                stock--;
                SetText(textField, stock);
                CurrentAmountClear();
                TriggerAllButtons(colaBTN, fantaBTN, sodaBTN, waterBTN, beerBTN, milkBTN, false);
                TriggerDispensedLabel(productLabel, true);
                debt = 0.0;
                // if current money is more than 0 sets enable the withdraw button else thanksMessage
                if (currentMoney > 0) {
                    EnableBTN(dispenseAmountBTN, true);
                    dispenseAmountBTN.setText(String.format("%.2f", currentMoney) + " € Withdraw Exchange");
        } else {
                    ThanksMessage();
                    CurrentAmountClear();
                    TriggerAllButtons(colaBTN, fantaBTN, sodaBTN, waterBTN, beerBTN, milkBTN, true);
                    TriggerDispensedLabel(productLabel, false);
                }
            } // Here card payment 
            else {
                CartPayment();
                TriggerAllCoinButtons(twoEuroBTN, oneEuroBTN, fiftyCentBTN, twentyCentBTN, false);
                TriggerAllButtons(colaBTN, fantaBTN, sodaBTN, waterBTN, beerBTN, milkBTN, false);
                TriggerSingleButton(cardBTN, true);
                cardBTN.setBackground(Color.red);
                timer.start();
                timer.setRepeats(false);                
            }
        } else {
            NoProduct();
            RestockingMessage();
        }
        return stock;
    }

    public void Increment(int i) {
        i++;
    }

    /**
     * Creates new form Main_GUI
     */
    public MainGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        productsPanel = new javax.swing.JPanel();
        colaBTN = new javax.swing.JButton();
        fantaBTN = new javax.swing.JButton();
        sodaBTN = new javax.swing.JButton();
        waterBTN = new javax.swing.JButton();
        beerBTN = new javax.swing.JButton();
        milkBTN = new javax.swing.JButton();
        colaStockPanel = new javax.swing.JPanel();
        colaStockTF = new javax.swing.JTextField();
        beerStockPanel = new javax.swing.JPanel();
        beerStockTF = new javax.swing.JTextField();
        fantaStockPanel = new javax.swing.JPanel();
        fantaStockTF = new javax.swing.JTextField();
        waterStockPanel = new javax.swing.JPanel();
        waterStockTF = new javax.swing.JTextField();
        milkStockPanel = new javax.swing.JPanel();
        milkStockTF = new javax.swing.JTextField();
        sodaStockPanel = new javax.swing.JPanel();
        sodaStockTF = new javax.swing.JTextField();
        paymentPanel = new javax.swing.JPanel();
        fiftyCentBTN = new javax.swing.JButton();
        clearBTN = new javax.swing.JButton();
        cardBTN = new javax.swing.JButton();
        oneEuroBTN = new javax.swing.JButton();
        twoEuroBTN = new javax.swing.JButton();
        twentyCentBTN = new javax.swing.JButton();
        currentAmountPanel = new javax.swing.JPanel();
        currentAmountTF = new javax.swing.JTextField();
        dispenseAmountPanel = new javax.swing.JPanel();
        dispenseAmountBTN = new javax.swing.JButton();
        PriceListPanel = new javax.swing.JPanel();
        colaPriceLabel = new javax.swing.JLabel();
        fantaPriceLabel = new javax.swing.JLabel();
        mineralWaterPriceLabel = new javax.swing.JLabel();
        waterPriceLabel = new javax.swing.JLabel();
        beerPriceLabel = new javax.swing.JLabel();
        milkPriceLabel = new javax.swing.JLabel();
        amountOfMoneyPanel = new javax.swing.JPanel();
        amountOfTwoEuroTF = new javax.swing.JTextField();
        amountOfOneEuroTF = new javax.swing.JTextField();
        amountOfFiftyCentTF = new javax.swing.JTextField();
        amountOfTwentyCentTF = new javax.swing.JTextField();
        twoEuroLabel = new javax.swing.JLabel();
        oneEuroLabel = new javax.swing.JLabel();
        fiftyCentLabel = new javax.swing.JLabel();
        twentyCentLabel = new javax.swing.JLabel();
        refillFloatBTN = new javax.swing.JButton();
        dispenseProducPicture = new javax.swing.JPanel();
        colaLabel = new javax.swing.JLabel();
        fantaLabel = new javax.swing.JLabel();
        sodaLabel = new javax.swing.JLabel();
        waterLabel = new javax.swing.JLabel();
        beerLabel = new javax.swing.JLabel();
        milkLabel = new javax.swing.JLabel();
        stockControl = new javax.swing.JPanel();
        refillBTN = new javax.swing.JButton();
        stockBTN = new javax.swing.JButton();
        floatBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vending Machine");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        productsPanel.setBackground(new java.awt.Color(0, 0, 0));
        productsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select one", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        colaBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vendingMachine/Images/cola.png"))); // NOI18N
        colaBTN.setToolTipText("Cola");
        colaBTN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        colaBTN.setPreferredSize(new java.awt.Dimension(55, 75));
        colaBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colaBTNActionPerformed(evt);
            }
        });

        fantaBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vendingMachine/Images/fanta.png"))); // NOI18N
        fantaBTN.setToolTipText("Fanta");
        fantaBTN.setPreferredSize(new java.awt.Dimension(55, 75));
        fantaBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fantaBTNActionPerformed(evt);
            }
        });

        sodaBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vendingMachine/Images/soda.png"))); // NOI18N
        sodaBTN.setToolTipText("Mineral Water");
        sodaBTN.setPreferredSize(new java.awt.Dimension(55, 75));
        sodaBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sodaBTNActionPerformed(evt);
            }
        });

        waterBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vendingMachine/Images/water.png"))); // NOI18N
        waterBTN.setToolTipText("Water");
        waterBTN.setPreferredSize(new java.awt.Dimension(55, 75));
        waterBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                waterBTNActionPerformed(evt);
            }
        });

        beerBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vendingMachine/Images/beer-can.png"))); // NOI18N
        beerBTN.setToolTipText("Beer");
        beerBTN.setPreferredSize(new java.awt.Dimension(55, 75));
        beerBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beerBTNActionPerformed(evt);
            }
        });

        milkBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vendingMachine/Images/milk.png"))); // NOI18N
        milkBTN.setToolTipText("Milk");
        milkBTN.setPreferredSize(new java.awt.Dimension(55, 75));
        milkBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                milkBTNActionPerformed(evt);
            }
        });

        colaStockPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Stock"));
        colaStockPanel.setPreferredSize(new java.awt.Dimension(86, 50));

        colaStockTF.setEditable(false);
        colaStockTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        colaStockTF.setToolTipText("Cola stock");
        colaStockTF.setPreferredSize(new java.awt.Dimension(64, 30));
        colaStockTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colaStockTFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout colaStockPanelLayout = new javax.swing.GroupLayout(colaStockPanel);
        colaStockPanel.setLayout(colaStockPanelLayout);
        colaStockPanelLayout.setHorizontalGroup(
            colaStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colaStockPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(colaStockTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        colaStockPanelLayout.setVerticalGroup(
            colaStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(colaStockTF, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        beerStockPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Stock"));
        beerStockPanel.setPreferredSize(new java.awt.Dimension(84, 50));

        beerStockTF.setEditable(false);
        beerStockTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        beerStockTF.setToolTipText("Beer Stock");
        beerStockTF.setPreferredSize(new java.awt.Dimension(64, 27));

        javax.swing.GroupLayout beerStockPanelLayout = new javax.swing.GroupLayout(beerStockPanel);
        beerStockPanel.setLayout(beerStockPanelLayout);
        beerStockPanelLayout.setHorizontalGroup(
            beerStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beerStockPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(beerStockTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        beerStockPanelLayout.setVerticalGroup(
            beerStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(beerStockTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        fantaStockPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Stock"));
        fantaStockPanel.setPreferredSize(new java.awt.Dimension(84, 50));

        fantaStockTF.setEditable(false);
        fantaStockTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fantaStockTF.setToolTipText("Fanta stock");
        fantaStockTF.setPreferredSize(new java.awt.Dimension(64, 27));

        javax.swing.GroupLayout fantaStockPanelLayout = new javax.swing.GroupLayout(fantaStockPanel);
        fantaStockPanel.setLayout(fantaStockPanelLayout);
        fantaStockPanelLayout.setHorizontalGroup(
            fantaStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fantaStockPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fantaStockTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        fantaStockPanelLayout.setVerticalGroup(
            fantaStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fantaStockPanelLayout.createSequentialGroup()
                .addComponent(fantaStockTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        waterStockPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Stock"));
        waterStockPanel.setPreferredSize(new java.awt.Dimension(84, 50));

        waterStockTF.setEditable(false);
        waterStockTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        waterStockTF.setToolTipText("Water stock");
        waterStockTF.setPreferredSize(new java.awt.Dimension(64, 27));

        javax.swing.GroupLayout waterStockPanelLayout = new javax.swing.GroupLayout(waterStockPanel);
        waterStockPanel.setLayout(waterStockPanelLayout);
        waterStockPanelLayout.setHorizontalGroup(
            waterStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(waterStockPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(waterStockTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );
        waterStockPanelLayout.setVerticalGroup(
            waterStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(waterStockPanelLayout.createSequentialGroup()
                .addComponent(waterStockTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        milkStockPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Stock"));
        milkStockPanel.setPreferredSize(new java.awt.Dimension(84, 50));

        milkStockTF.setEditable(false);
        milkStockTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        milkStockTF.setToolTipText("Milk stock");
        milkStockTF.setPreferredSize(new java.awt.Dimension(64, 27));

        javax.swing.GroupLayout milkStockPanelLayout = new javax.swing.GroupLayout(milkStockPanel);
        milkStockPanel.setLayout(milkStockPanelLayout);
        milkStockPanelLayout.setHorizontalGroup(
            milkStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, milkStockPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(milkStockTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        milkStockPanelLayout.setVerticalGroup(
            milkStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(milkStockPanelLayout.createSequentialGroup()
                .addComponent(milkStockTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sodaStockPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Stock"));
        sodaStockPanel.setPreferredSize(new java.awt.Dimension(84, 50));

        sodaStockTF.setEditable(false);
        sodaStockTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        sodaStockTF.setToolTipText("Soda stock");
        sodaStockTF.setPreferredSize(new java.awt.Dimension(64, 27));

        javax.swing.GroupLayout sodaStockPanelLayout = new javax.swing.GroupLayout(sodaStockPanel);
        sodaStockPanel.setLayout(sodaStockPanelLayout);
        sodaStockPanelLayout.setHorizontalGroup(
            sodaStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sodaStockPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sodaStockTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );
        sodaStockPanelLayout.setVerticalGroup(
            sodaStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sodaStockTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout productsPanelLayout = new javax.swing.GroupLayout(productsPanel);
        productsPanel.setLayout(productsPanelLayout);
        productsPanelLayout.setHorizontalGroup(
            productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(colaStockPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fantaStockPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                    .addComponent(fantaBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(colaBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(waterStockPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sodaBTN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sodaStockPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(waterBTN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(beerBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(milkBTN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(beerStockPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                    .addComponent(milkStockPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        productsPanelLayout.setVerticalGroup(
            productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(colaBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sodaBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(beerBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(beerStockPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(sodaStockPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(colaStockPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fantaBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(waterBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(milkBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fantaStockPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(waterStockPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(milkStockPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        paymentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Add money"));

        fiftyCentBTN.setText("50c");
        fiftyCentBTN.setToolTipText("Add 50c");
        fiftyCentBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fiftyCentBTNActionPerformed(evt);
            }
        });

        clearBTN.setMnemonic('c');
        clearBTN.setText("Clear");
        clearBTN.setToolTipText("Clear");
        clearBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBTNActionPerformed(evt);
            }
        });

        cardBTN.setText("Card");
        cardBTN.setToolTipText("Card payment");
        cardBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardBTNActionPerformed(evt);
            }
        });

        oneEuroBTN.setText("1 €");
        oneEuroBTN.setToolTipText("Add 1 €");
        oneEuroBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneEuroBTNActionPerformed(evt);
            }
        });

        twoEuroBTN.setText("2 €");
        twoEuroBTN.setToolTipText("Add 2 €");
        twoEuroBTN.setPreferredSize(new java.awt.Dimension(40, 55));
        twoEuroBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoEuroBTNActionPerformed(evt);
            }
        });

        twentyCentBTN.setText("20c");
        twentyCentBTN.setToolTipText("Add 20c");
        twentyCentBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twentyCentBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paymentPanelLayout = new javax.swing.GroupLayout(paymentPanel);
        paymentPanel.setLayout(paymentPanelLayout);
        paymentPanelLayout.setHorizontalGroup(
            paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(twentyCentBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                    .addComponent(twoEuroBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paymentPanelLayout.createSequentialGroup()
                        .addComponent(oneEuroBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fiftyCentBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paymentPanelLayout.createSequentialGroup()
                        .addComponent(cardBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clearBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        paymentPanelLayout.setVerticalGroup(
            paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fiftyCentBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oneEuroBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(twoEuroBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(twentyCentBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        currentAmountPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Current amount"));

        currentAmountTF.setEditable(false);
        currentAmountTF.setToolTipText("Current amount");
        currentAmountTF.setPreferredSize(new java.awt.Dimension(70, 22));

        javax.swing.GroupLayout currentAmountPanelLayout = new javax.swing.GroupLayout(currentAmountPanel);
        currentAmountPanel.setLayout(currentAmountPanelLayout);
        currentAmountPanelLayout.setHorizontalGroup(
            currentAmountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentAmountPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currentAmountTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        currentAmountPanelLayout.setVerticalGroup(
            currentAmountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, currentAmountPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currentAmountTF, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        dispenseAmountPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Dispense amount"));

        dispenseAmountBTN.setToolTipText("Exchange");
        dispenseAmountBTN.setEnabled(false);
        dispenseAmountBTN.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        dispenseAmountBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispenseAmountBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dispenseAmountPanelLayout = new javax.swing.GroupLayout(dispenseAmountPanel);
        dispenseAmountPanel.setLayout(dispenseAmountPanelLayout);
        dispenseAmountPanelLayout.setHorizontalGroup(
            dispenseAmountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dispenseAmountPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dispenseAmountBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        dispenseAmountPanelLayout.setVerticalGroup(
            dispenseAmountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dispenseAmountPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dispenseAmountBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PriceListPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Price List"));

        colaPriceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        colaPriceLabel.setLabelFor(colaBTN);
        colaPriceLabel.setText("Cola 2 €");
        colaPriceLabel.setToolTipText("Cola price");
        colaPriceLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        fantaPriceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fantaPriceLabel.setText("Fanta 2 €");
        fantaPriceLabel.setToolTipText("Fanta price");

        mineralWaterPriceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mineralWaterPriceLabel.setText("Soda Water 1 €");
        mineralWaterPriceLabel.setToolTipText("Soda Water price");

        waterPriceLabel.setText("Water 1 €");
        waterPriceLabel.setToolTipText("Water price");

        beerPriceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        beerPriceLabel.setText("Beer 3 €");
        beerPriceLabel.setToolTipText("Beer price");

        milkPriceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        milkPriceLabel.setText("Milk 1 €");
        milkPriceLabel.setToolTipText("Milk price");

        javax.swing.GroupLayout PriceListPanelLayout = new javax.swing.GroupLayout(PriceListPanel);
        PriceListPanel.setLayout(PriceListPanelLayout);
        PriceListPanelLayout.setHorizontalGroup(
            PriceListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PriceListPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(colaPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(fantaPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mineralWaterPriceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(36, 36, 36)
                .addComponent(waterPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(beerPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(milkPriceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29))
        );
        PriceListPanelLayout.setVerticalGroup(
            PriceListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PriceListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PriceListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(milkPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(beerPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(waterPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mineralWaterPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fantaPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(colaPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        PriceListPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {beerPriceLabel, colaPriceLabel, fantaPriceLabel, milkPriceLabel, mineralWaterPriceLabel, waterPriceLabel});

        amountOfMoneyPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Amount of Money Machine Holds (in piece)"));

        amountOfTwoEuroTF.setEditable(false);
        amountOfTwoEuroTF.setToolTipText("Two euro stock");

        amountOfOneEuroTF.setEditable(false);
        amountOfOneEuroTF.setToolTipText("One euro stock");
        amountOfOneEuroTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountOfOneEuroTFActionPerformed(evt);
            }
        });

        amountOfFiftyCentTF.setEditable(false);
        amountOfFiftyCentTF.setToolTipText("Fifty cent stock");

        amountOfTwentyCentTF.setEditable(false);
        amountOfTwentyCentTF.setToolTipText("Twenty cent stock");

        twoEuroLabel.setText("2 €");

        oneEuroLabel.setText("1 €");

        fiftyCentLabel.setText("50c");

        twentyCentLabel.setText("20c");

        refillFloatBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vendingMachine/Images/refresh.png"))); // NOI18N
        refillFloatBTN.setToolTipText("Refill the Float");
        refillFloatBTN.setEnabled(false);
        refillFloatBTN.setPreferredSize(new java.awt.Dimension(33, 33));
        refillFloatBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refillFloatBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout amountOfMoneyPanelLayout = new javax.swing.GroupLayout(amountOfMoneyPanel);
        amountOfMoneyPanel.setLayout(amountOfMoneyPanelLayout);
        amountOfMoneyPanelLayout.setHorizontalGroup(
            amountOfMoneyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, amountOfMoneyPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(twoEuroLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(amountOfTwoEuroTF, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(oneEuroLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(amountOfOneEuroTF, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(fiftyCentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(amountOfFiftyCentTF, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(twentyCentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(amountOfTwentyCentTF, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(refillFloatBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        amountOfMoneyPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {amountOfFiftyCentTF, amountOfOneEuroTF, amountOfTwentyCentTF, amountOfTwoEuroTF});

        amountOfMoneyPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {fiftyCentLabel, oneEuroLabel, twentyCentLabel, twoEuroLabel});

        amountOfMoneyPanelLayout.setVerticalGroup(
            amountOfMoneyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(amountOfMoneyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(amountOfMoneyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(refillFloatBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(amountOfMoneyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(amountOfFiftyCentTF)
                        .addComponent(fiftyCentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(amountOfMoneyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(amountOfTwentyCentTF)
                        .addComponent(twentyCentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(amountOfMoneyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(amountOfOneEuroTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(oneEuroLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(amountOfMoneyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(amountOfTwoEuroTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(twoEuroLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        amountOfMoneyPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {fiftyCentLabel, oneEuroLabel, twentyCentLabel, twoEuroLabel});

        amountOfMoneyPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {amountOfFiftyCentTF, amountOfOneEuroTF, amountOfTwentyCentTF, amountOfTwoEuroTF});

        dispenseProducPicture.setBackground(new java.awt.Color(0, 0, 0));
        dispenseProducPicture.setBorder(javax.swing.BorderFactory.createTitledBorder("Dispensed Product"));
        dispenseProducPicture.setToolTipText("Take Your Product");
        dispenseProducPicture.setLayout(new javax.swing.OverlayLayout(dispenseProducPicture));

        colaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        colaLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vendingMachine/Images/cola-256.png"))); // NOI18N
        dispenseProducPicture.add(colaLabel);

        fantaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fantaLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vendingMachine/Images/fanta-256.png"))); // NOI18N
        dispenseProducPicture.add(fantaLabel);

        sodaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sodaLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vendingMachine/Images/soda-256.png"))); // NOI18N
        dispenseProducPicture.add(sodaLabel);

        waterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        waterLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vendingMachine/Images/water-256.png"))); // NOI18N
        dispenseProducPicture.add(waterLabel);

        beerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        beerLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vendingMachine/Images/beer-256.png"))); // NOI18N
        dispenseProducPicture.add(beerLabel);

        milkLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        milkLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vendingMachine/Images/milk-256.png"))); // NOI18N
        dispenseProducPicture.add(milkLabel);

        stockControl.setBorder(javax.swing.BorderFactory.createTitledBorder("Stock Control"));
        stockControl.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        refillBTN.setMnemonic('r');
        refillBTN.setText("Refill");
        refillBTN.setToolTipText("Refill the products");
        refillBTN.setEnabled(false);
        refillBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refillBTNActionPerformed(evt);
            }
        });

        stockBTN.setMnemonic('s');
        stockBTN.setText("Stock");
        stockBTN.setToolTipText("Order new products");
        stockBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockBTNActionPerformed(evt);
            }
        });

        floatBTN.setMnemonic('f');
        floatBTN.setText("Float");
        floatBTN.setToolTipText("Order additional floats");
        floatBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                floatBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout stockControlLayout = new javax.swing.GroupLayout(stockControl);
        stockControl.setLayout(stockControlLayout);
        stockControlLayout.setHorizontalGroup(
            stockControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(stockControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stockBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(refillBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(floatBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        stockControlLayout.setVerticalGroup(
            stockControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stockControlLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(stockBTN)
                .addGap(18, 18, 18)
                .addComponent(refillBTN)
                .addGap(18, 18, 18)
                .addComponent(floatBTN)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(productsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dispenseProducPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(paymentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(currentAmountPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dispenseAmountPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(stockControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(PriceListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(amountOfMoneyPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(productsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dispenseProducPicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(currentAmountPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dispenseAmountPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(paymentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stockControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43)
                .addComponent(PriceListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(amountOfMoneyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void beerBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beerBTNActionPerformed
        debt += 3;
            beerStock = ProductBuying(beerStock, beerStockTF, beerLabel);
    }//GEN-LAST:event_beerBTNActionPerformed

    private void fantaBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fantaBTNActionPerformed
        debt += 2;
            fantaStock = ProductBuying(fantaStock, fantaStockTF, fantaLabel);
    }//GEN-LAST:event_fantaBTNActionPerformed

    private void colaStockTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colaStockTFActionPerformed

    }//GEN-LAST:event_colaStockTFActionPerformed

    private void milkBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_milkBTNActionPerformed
        debt += 1;
        if (!(milkStock <= 0)) {
            // Checks inserted money is enough
            if (!(currentMoney < debt)) {
                currentMoney -= debt;
                milkStock--;
                SetText(milkStockTF, milkStock);
                CurrentAmountClear();
                TriggerAllButtons(colaBTN, fantaBTN, sodaBTN, waterBTN, beerBTN, milkBTN, false);
                TriggerDispensedLabel(milkLabel, true);
                debt = 0.0;
                // if current money is more than 0 sets enable the withdraw button else thanksMessage
                if (currentMoney > 0) {
                    EnableBTN(dispenseAmountBTN, true);
                    dispenseAmountBTN.setText(String.format("%.2f", currentMoney) + " € Withdraw Exchange");
                } else {
                    ThanksMessage();
                    CurrentAmountClear();
                    TriggerAllButtons(colaBTN, fantaBTN, sodaBTN, waterBTN, beerBTN, milkBTN, true);
                    TriggerDispensedLabel(milkLabel, false);
                }
            } // Here card payment 
            else {
                CartPayment();
                TriggerAllCoinButtons(twoEuroBTN, oneEuroBTN, fiftyCentBTN, twentyCentBTN, false);
                TriggerAllButtons(colaBTN, fantaBTN, sodaBTN, waterBTN, beerBTN, milkBTN, false);
                TriggerSingleButton(cardBTN, true);
                cardBTN.setBackground(Color.red);
                Timer timer = new Timer(5000, new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        // Checks that Card Payment is equal the debt
                        if (currentMoney == debt) {
                            TriggerDispensedLabel(milkLabel, true);
                            CurrentAmountClear();
                            TriggerAllButtons(colaBTN, fantaBTN, sodaBTN, waterBTN, beerBTN, milkBTN, true);
                            TriggerAllCoinButtons(twoEuroBTN, oneEuroBTN, fiftyCentBTN, twentyCentBTN, true);
                            ThanksMessage();
                            milkStock--;
                            SetText(milkStockTF, milkStock);
                        } // if card payment is not succesfull
                        else {
                            twoEuroBTN.requestFocus();
                            cardBTN.setBackground(new Color(187, 187, 187));
                            TriggerSingleButton(cardBTN, false);
                            SetToZero();
                            CurrentAmountClear();
                            DispenseAmountClear();
                            AddMoney();
                            TriggerAllCoinButtons(twoEuroBTN, oneEuroBTN, fiftyCentBTN, twentyCentBTN, true);
                            TriggerAllButtons(colaBTN, fantaBTN, sodaBTN, waterBTN, beerBTN, milkBTN, true);
                        }
                        TriggerDispensedLabel(milkLabel, false);
                        TriggerSingleButton(cardBTN, false);
                        SetToZero();
                    }
                });
                timer.start();
                timer.setRepeats(false);
            }
        } else {
            NoProduct();
            RestockingMessage();
        }
    }//GEN-LAST:event_milkBTNActionPerformed

    private void clearBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBTNActionPerformed
        StockBalance(currentMoney);
        MoneyStockSetText();
        CancelReturn(currentMoney);
        SetToZero();
        CurrentAmountClear();
        DispenseAmountClear();
    }//GEN-LAST:event_clearBTNActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        WindowOpened();
    }//GEN-LAST:event_formWindowOpened

    private void twoEuroBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoEuroBTNActionPerformed
        addCash(2);
        twoEuroStock++;
        SetText(amountOfTwoEuroTF, twoEuroStock);
        CurrentMoneyAfterAdding();
    }//GEN-LAST:event_twoEuroBTNActionPerformed

    private void oneEuroBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneEuroBTNActionPerformed
        addCash(1);
        oneEuroStock++;
        SetText(amountOfOneEuroTF, oneEuroStock);
        CurrentMoneyAfterAdding();
    }//GEN-LAST:event_oneEuroBTNActionPerformed

    private void fiftyCentBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiftyCentBTNActionPerformed
        addCash(0.5);
        fiftyCentStock++;
        SetText(amountOfFiftyCentTF, fiftyCentStock);
        CurrentMoneyAfterAdding();
    }//GEN-LAST:event_fiftyCentBTNActionPerformed

    private void twentyCentBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twentyCentBTNActionPerformed
        addCash(0.2);;
        twentyCentStock++;
        SetText(amountOfTwentyCentTF, twentyCentStock);
        CurrentMoneyAfterAdding();
    }//GEN-LAST:event_twentyCentBTNActionPerformed

    private void colaBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colaBTNActionPerformed
        debt += 2;
            cokeStock = ProductBuying(cokeStock, colaStockTF, colaLabel);
    }//GEN-LAST:event_colaBTNActionPerformed

    private void dispenseAmountBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dispenseAmountBTNActionPerformed
        // Here button adjust money to 0 after we withdraw all dispence amount and Decrement iteration on the given coin type   
        StockBalance(currentMoney);
        MoneyStockSetText();
        SetToZero();
        CurrentAmountClear();
        DispenseAmountClear();
        TriggerSingleButton(dispenseAmountBTN, false);
        DisableAllLabels(colaLabel, fantaLabel, sodaLabel, waterLabel, beerLabel, milkLabel);
        TriggerAllButtons(colaBTN, fantaBTN, sodaBTN, waterBTN, beerBTN, milkBTN, true);
        ThanksMessage();
    }//GEN-LAST:event_dispenseAmountBTNActionPerformed

    private void waterBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_waterBTNActionPerformed
        debt += 1;
            waterStock = ProductBuying(waterStock, waterStockTF, waterLabel);
    }//GEN-LAST:event_waterBTNActionPerformed

    private void amountOfOneEuroTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountOfOneEuroTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountOfOneEuroTFActionPerformed

    private void refillFloatBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refillFloatBTNActionPerformed
        // Refill the Float
        FloatRefill();
        MoneyStockSetText();
        TriggerSingleButton(refillFloatBTN, false);
    }//GEN-LAST:event_refillFloatBTNActionPerformed

    private void refillBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refillBTNActionPerformed
        StockRefill();
        RefillSetText();
        TriggerSingleButton(refillBTN, false);
    }//GEN-LAST:event_refillBTNActionPerformed

    private void stockBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockBTNActionPerformed
        TriggerSingleButton(refillBTN, true);
        Stock s = new Stock();
        s.setVisible(true);
    }//GEN-LAST:event_stockBTNActionPerformed

    private void cardBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardBTNActionPerformed
        currentMoney = debt;
        CurrentMoneyAfterAdding();
    }//GEN-LAST:event_cardBTNActionPerformed

    private void sodaBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sodaBTNActionPerformed
        debt += 1;
            sodaStock = ProductBuying(sodaStock, sodaStockTF, sodaLabel);
    }//GEN-LAST:event_sodaBTNActionPerformed

    private void floatBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_floatBTNActionPerformed
        TriggerSingleButton(refillFloatBTN, true);
        Float f = new Float();
        f.setVisible(true);
    }//GEN-LAST:event_floatBTNActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PriceListPanel;
    private javax.swing.JTextField amountOfFiftyCentTF;
    private javax.swing.JPanel amountOfMoneyPanel;
    private javax.swing.JTextField amountOfOneEuroTF;
    private javax.swing.JTextField amountOfTwentyCentTF;
    private javax.swing.JTextField amountOfTwoEuroTF;
    private javax.swing.JButton beerBTN;
    private javax.swing.JLabel beerLabel;
    private javax.swing.JLabel beerPriceLabel;
    private javax.swing.JPanel beerStockPanel;
    private javax.swing.JTextField beerStockTF;
    private javax.swing.JButton cardBTN;
    private javax.swing.JButton clearBTN;
    private javax.swing.JButton colaBTN;
    private javax.swing.JLabel colaLabel;
    private javax.swing.JLabel colaPriceLabel;
    private javax.swing.JPanel colaStockPanel;
    private javax.swing.JTextField colaStockTF;
    private javax.swing.JPanel currentAmountPanel;
    private javax.swing.JTextField currentAmountTF;
    private javax.swing.JButton dispenseAmountBTN;
    private javax.swing.JPanel dispenseAmountPanel;
    private javax.swing.JPanel dispenseProducPicture;
    private javax.swing.JButton fantaBTN;
    private javax.swing.JLabel fantaLabel;
    private javax.swing.JLabel fantaPriceLabel;
    private javax.swing.JPanel fantaStockPanel;
    private javax.swing.JTextField fantaStockTF;
    private javax.swing.JButton fiftyCentBTN;
    private javax.swing.JLabel fiftyCentLabel;
    private javax.swing.JButton floatBTN;
    private javax.swing.JButton milkBTN;
    private javax.swing.JLabel milkLabel;
    private javax.swing.JLabel milkPriceLabel;
    private javax.swing.JPanel milkStockPanel;
    private javax.swing.JTextField milkStockTF;
    private javax.swing.JLabel mineralWaterPriceLabel;
    private javax.swing.JButton oneEuroBTN;
    private javax.swing.JLabel oneEuroLabel;
    private javax.swing.JPanel paymentPanel;
    private javax.swing.JPanel productsPanel;
    private javax.swing.JButton refillBTN;
    private javax.swing.JButton refillFloatBTN;
    private javax.swing.JButton sodaBTN;
    private javax.swing.JLabel sodaLabel;
    private javax.swing.JPanel sodaStockPanel;
    private javax.swing.JTextField sodaStockTF;
    private javax.swing.JButton stockBTN;
    private javax.swing.JPanel stockControl;
    private javax.swing.JButton twentyCentBTN;
    private javax.swing.JLabel twentyCentLabel;
    private javax.swing.JButton twoEuroBTN;
    private javax.swing.JLabel twoEuroLabel;
    private javax.swing.JButton waterBTN;
    private javax.swing.JLabel waterLabel;
    private javax.swing.JLabel waterPriceLabel;
    private javax.swing.JPanel waterStockPanel;
    private javax.swing.JTextField waterStockTF;
    // End of variables declaration//GEN-END:variables

}
