package GUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.File;

import Class.Fruits;
import Class.Vegetable;
import Class.Meat;
import Class.Checkout;
import FileIo.Admin;

public class AIUBStoreGUI extends JFrame 
{

    private final Color AQUAMARINE = new Color(0x60, 0xF2, 0xD2);
    private final Color TURQUOISE  = new Color(0x40, 0xE0, 0xD0);
    private final Color TEAL       = new Color(0x00, 0x80, 0x80);
    private final Color CARIBBEAN  = new Color(0x08, 0x5A, 0x57);
    private final Color DARK_BG    = new Color(0x10, 0x2A, 0x23);

    private Fruits fruits = new Fruits(50, 50, 50, 50, 2.0, 0.5, 3.0, 1.2);
    private Vegetable vegetables = new Vegetable(100, 60, 80, 40, 1.0, 0.8, 1.5, 1.1);
    private Meat meat = new Meat(20, 30, 15, 15.0, 10.0, 25.0);

    private Admin admin = new Admin();
    private Checkout checkoutCalc = new Checkout(fruits, vegetables, meat);

    private JTextField[] fruitStock = new JTextField[4], fruitPrice = new JTextField[4];
    private JTextField[] vegStock   = new JTextField[4], vegPrice   = new JTextField[4];
    private JTextField[] meatStock  = new JTextField[3], meatPrice  = new JTextField[3];

    private JTextField[] purchaseInputs = new JTextField[11];
    private JRadioButton rbCash, rbCard;
    private JLabel lblTotal;
    private JButton btnUpdate, btnViewSales, btnLogout;

    private JTextField tfUser;
    private JPasswordField tfPass;
    private JButton btnLogin;

    private boolean isAdmin = false;

    public AIUBStoreGUI() 
	{
        setTitle("AIUB Premium Store Management");
        setSize(1450, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        admin.loadTotalSales();
        admin.loadInventory(fruits, vegetables, meat);

        add(createSidebar(), BorderLayout.WEST);
        setupLiveTotalUpdate();

        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(Color.WHITE);

        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));
        header.setBackground(TURQUOISE);
        JLabel headLbl = new JLabel("AIUB Premium Store Management");
        headLbl.setFont(new Font("Segoe UI", Font.BOLD, 26));
        header.add(headLbl);
        mainContent.add(header, BorderLayout.NORTH);

        JPanel gridArea = new JPanel();
gridArea.setLayout(new BoxLayout(gridArea, BoxLayout.Y_AXIS));

gridArea.add(createCategory("FRUITS",
        new String[]{"Apple", "Banana", "Orange", "Mango"},
        new String[]{"Apple.jpg", "Banana.jpg", "Orange.jpg", "Mango.jpg"},
        fruitStock, fruitPrice));

gridArea.add(createCategory("VEGETABLES",
        new String[]{"Potato", "Carrot", "Tomato", "Cucumber"},
        new String[]{"Potatoes.jpg", "Carrots.jpg", "tomato.jpg", "cucumber.jpg"},
        vegStock, vegPrice));

gridArea.add(createCategory("MEAT",
        new String[]{"Beef", "Chicken", "Mutton"},
        new String[]{"beef.jpg", "chicken.jpg", "mutton.jpg"},
        meatStock, meatPrice));


        JScrollPane sp = new JScrollPane(gridArea);
        sp.getVerticalScrollBar().setUnitIncrement(18); 
        mainContent.add(sp, BorderLayout.CENTER);

        add(mainContent, BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);

        refreshUI();
        setAdminMode(false);
        setVisible(true);
    }

    private ImageIcon loadIconFromImagesFolder(String fileName) 
	{
        File f = new File("images", fileName);
        System.out.println("Trying image: " + f.getAbsolutePath());
        if (!f.exists()) 
		{
			return null;
		}
        return new ImageIcon(f.getAbsolutePath());
    }

    private void setupLiveTotalUpdate() 
	{
        for (JTextField tf : purchaseInputs) 
		{
            if (tf == null) continue;
            tf.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() 
			{
                public void insertUpdate(javax.swing.event.DocumentEvent e) 
				{ 
				updateLiveTotal(); 
				}
                public void removeUpdate(javax.swing.event.DocumentEvent e) 
				{ 
				updateLiveTotal(); 
				}
                public void changedUpdate(javax.swing.event.DocumentEvent e) 
				{ 
				updateLiveTotal(); 
				}
            });
        }
        updateLiveTotal();
    }

    private void updateLiveTotal() 
	{
        try 
		{
            double totalPreview = checkoutCalc.grandTotal(
                    vSafe(0), vSafe(1), vSafe(2), vSafe(3),
                    vSafe(4), vSafe(5), vSafe(6), vSafe(7),
                    vSafe(8), vSafe(9), vSafe(10)
            );
            lblTotal.setText("Total: ৳" + String.format("%.2f", totalPreview));
        } 
		catch (Exception ex) 
		{
            lblTotal.setText("Total: ৳0.00");
        }
    }

    private int vSafe(int idx) 
	{
        String s = purchaseInputs[idx].getText().trim();
        if (s.isEmpty()) return 0;
        return Integer.parseInt(s);
    }

    private JPanel createSidebar() 
	{
        JPanel side = new JPanel();
        side.setPreferredSize(new Dimension(320, 0));
        side.setBackground(DARK_BG);
        side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));
        side.setBorder(new EmptyBorder(30, 25, 30, 25));

        JLabel title = new JLabel("CUSTOMER CART");
        title.setForeground(AQUAMARINE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        side.add(title);
        side.add(Box.createVerticalStrut(20));

        String[] itemNames = {"Apple","Banana","Orange","Mango","Potato","Carrot","Tomato","Cucumber","Beef","Chicken","Mutton"};
        for (int i = 0; i < itemNames.length; i++) {
            JPanel row = new JPanel(new BorderLayout());
            row.setOpaque(false);
            row.setMaximumSize(new Dimension(300, 30));
            JLabel l = new JLabel(itemNames[i]);
            l.setForeground(Color.WHITE);
            purchaseInputs[i] = new JTextField("0", 5);
            row.add(l, BorderLayout.WEST);
            row.add(purchaseInputs[i], BorderLayout.EAST);
            side.add(row);
            side.add(Box.createVerticalStrut(10));
        }

        side.add(Box.createVerticalStrut(20));
        JLabel payLabel = new JLabel("PAYMENT METHOD");
        payLabel.setForeground(AQUAMARINE);
        payLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        side.add(payLabel);

        rbCash = new JRadioButton("Cash");
        rbCard = new JRadioButton("Card");
        rbCash.setForeground(Color.WHITE);
        rbCard.setForeground(Color.WHITE);
        rbCash.setOpaque(false);
        rbCard.setOpaque(false);
        rbCash.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(rbCash);
        group.add(rbCard);

        JPanel pPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pPanel.setOpaque(false);
        pPanel.add(rbCash);
        pPanel.add(rbCard);
        side.add(pPanel);

        side.add(Box.createVerticalGlue());
        lblTotal = new JLabel("Total: ৳0.00");
        lblTotal.setForeground(AQUAMARINE);
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 28));
        side.add(lblTotal);
        side.add(Box.createVerticalStrut(15));

        JButton btnSell = styleBtn("CONFIRM SALE", AQUAMARINE, DARK_BG);
        btnSell.addActionListener(e -> handleSale());
        side.add(btnSell);

        return side;
    }

    private void handleSale() 
	{
        try 
		{
            String[] names = {"Apple","Banana","Orange","Mango","Potato","Carrot","Tomato","Cucumber","Beef","Chicken","Mutton"};
            StringBuilder details = new StringBuilder();

            for (int i = 0; i < names.length; i++) 
			{
                int qty = v(i);
                if (qty > 0) details.append(String.format("%-15s x%d\n", names[i], qty));
            }

            double sale = checkoutCalc.processSale(
                    v(0), v(1), v(2), v(3),
                    v(4), v(5), v(6), v(7),
                    v(8), v(9), v(10)
            );

            if (sale == -1) 
			{
                JOptionPane.showMessageDialog(this, "Not enough stock OR invalid input!");
                return;
            }

            if (sale > 0) 
			{
                String method = rbCash.isSelected() ? "Cash" : "Card";

                admin.saveSales(sale);
                admin.generateReceipt(details.toString(), sale, method);
                admin.saveInventory(fruits, vegetables, meat);

                refreshUI();
                JOptionPane.showMessageDialog(this, "Sale Confirmed! Saved in sales.txt");

                resetCart();
            } 
			else 
			{
                JOptionPane.showMessageDialog(this, "Select items first!");
            }
        } 
		catch (Exception e) 
		{
            JOptionPane.showMessageDialog(this, "Invalid Quantity Input!");
        }
    }

    private void resetCart() 
	{
        for (JTextField field : purchaseInputs) field.setText("0");
        lblTotal.setText("Total: ৳0.00");
        rbCash.setSelected(true);
    }

    private void refreshUI() 
	{
        fruitStock[0].setText("" + fruits.getStock(Fruits.APPLE));   
        fruitStock[1].setText("" + fruits.getStock(Fruits.BANANA));  
        fruitStock[2].setText("" + fruits.getStock(Fruits.ORANGE));  
        fruitStock[3].setText("" + fruits.getStock(Fruits.MANGO));   
		
		fruitPrice[0].setText("" + fruits.getPrice(Fruits.APPLE));
		fruitPrice[1].setText("" + fruits.getPrice(Fruits.BANANA));
		fruitPrice[2].setText("" + fruits.getPrice(Fruits.ORANGE));
		fruitPrice[3].setText("" + fruits.getPrice(Fruits.MANGO));

        vegStock[0].setText("" + vegetables.getStock(Vegetable.POTATO));    
        vegStock[1].setText("" + vegetables.getStock(Vegetable.CARROT));    
        vegStock[2].setText("" + vegetables.getStock(Vegetable.TOMATO));    
        vegStock[3].setText("" + vegetables.getStock(Vegetable.CUCUMBER));  
		
		vegPrice[0].setText("" + vegetables.getPrice(Vegetable.POTATO));
		vegPrice[1].setText("" + vegetables.getPrice(Vegetable.CARROT));
		vegPrice[2].setText("" + vegetables.getPrice(Vegetable.TOMATO));
		vegPrice[3].setText("" + vegetables.getPrice(Vegetable.CUCUMBER));

        meatStock[0].setText("" + meat.getStock(Meat.BEEF));     
        meatStock[1].setText("" + meat.getStock(Meat.CHICKEN));  
        meatStock[2].setText("" + meat.getStock(Meat.MUTTON));   
		
		meatPrice[0].setText("" + meat.getPrice(Meat.BEEF));
		meatPrice[1].setText("" + meat.getPrice(Meat.CHICKEN));
		meatPrice[2].setText("" + meat.getPrice(Meat.MUTTON));
		
    }

    private JPanel createCategory(String catName, String[] items, String[] imgs, JTextField[] stocks, JTextField[] prices) 
	{

        JPanel catPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 25));
        catPanel.setBackground(Color.WHITE);
        catPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(TEAL, 2),
                catName, TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 20), TEAL));

        for (int i = 0; i < items.length; i++) 
		{
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setPreferredSize(new Dimension(230, 270));
            card.setBackground(new Color(245, 255, 252));
            card.setBorder(new CompoundBorder(new LineBorder(AQUAMARINE, 1), new EmptyBorder(10, 10, 10, 10)));

            JLabel imgLabel = new JLabel();
            imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            try 
			{
                ImageIcon icon = loadIconFromImagesFolder(imgs[i]);
                if (icon != null && icon.getIconWidth() > 0) 
				{
                    imgLabel.setIcon(new ImageIcon(icon.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH)));
                }
				else 
				{
                    imgLabel.setText("No Image");
                }
            } 
			catch (Exception e) 
			{
                imgLabel.setText("No Image");
            }

            JLabel title = new JLabel(items[i], SwingConstants.CENTER);
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            title.setFont(new Font("Segoe UI", Font.BOLD, 16));

            stocks[i] = new JTextField();
            stocks[i].setEditable(false);
            prices[i] = new JTextField();
            prices[i].setEditable(false);

            card.add(imgLabel); card.add(Box.createVerticalStrut(10));
            card.add(title);    card.add(Box.createVerticalStrut(10));
            card.add(createAlignedRow("Stock(Per Kg):", stocks[i]));
            card.add(createAlignedRow("Price(In BDT):", prices[i]));
            catPanel.add(card);
        }

        return catPanel;
    }

    private JPanel createAlignedRow(String labelText, JTextField field) 
	{
        JPanel p = new JPanel(new BorderLayout(10, 0));
        p.setOpaque(false);
        p.add(new JLabel(labelText), BorderLayout.WEST);
        p.add(field, BorderLayout.CENTER);
        return p;
    }

    private JPanel createFooter() 
	{
        JPanel foot = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        foot.setBackground(CARIBBEAN);

        tfUser = new JTextField(10);
        tfPass = new JPasswordField(10);

        btnLogin = styleBtn("ADMIN LOGIN", TEAL, Color.WHITE);
        btnUpdate = styleBtn("UPDATE SYSTEM", Color.WHITE, DARK_BG);
        btnViewSales = styleBtn("VIEW SALES", AQUAMARINE, DARK_BG);
        btnLogout = styleBtn("LOGOUT", new Color(0xD94C4C), Color.WHITE);

        btnUpdate.setEnabled(false);
        btnViewSales.setEnabled(false);
        btnLogout.setVisible(false);

        btnLogin.addActionListener(e -> doLogin());
        btnLogout.addActionListener(e -> doLogout());
        btnUpdate.addActionListener(e -> applyAdminChanges());

        btnViewSales.addActionListener(e -> 
		{
            JTextArea textArea = new JTextArea(admin.getSalesHistoryData());
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(650, 450));
            JOptionPane.showMessageDialog(this, scrollPane, "All Sales Transactions", JOptionPane.INFORMATION_MESSAGE);
        });

        JLabel uL = new JLabel("User: "); uL.setForeground(Color.WHITE);
        JLabel pL = new JLabel("Pass: "); pL.setForeground(Color.WHITE);

        foot.add(uL); foot.add(tfUser);
        foot.add(pL); foot.add(tfPass);
        foot.add(btnLogin);
        foot.add(btnLogout);
        foot.add(btnUpdate);
        foot.add(btnViewSales);

        return foot;
    }

    private void doLogin() 
	{
        if (admin.login(tfUser.getText(), new String(tfPass.getPassword()))) 
		{
            isAdmin = true;
            setAdminMode(true);
            btnUpdate.setEnabled(true);
            btnViewSales.setEnabled(true);
            btnLogout.setVisible(true);

            tfUser.setEnabled(false);
            tfPass.setEnabled(false);
            btnLogin.setEnabled(false);

            JOptionPane.showMessageDialog(this, "Admin Authenticated. Sales History Unlocked.");
        } 
		else 
		{
            JOptionPane.showMessageDialog(this, "Wrong username/password!");
        }
    }

    private void doLogout() 
	{
        isAdmin = false;
        setAdminMode(false);

        btnUpdate.setEnabled(false);
        btnViewSales.setEnabled(false);
        btnLogout.setVisible(false);

        tfUser.setEnabled(true);
        tfPass.setEnabled(true);
        btnLogin.setEnabled(true);

        tfUser.setText("");
        tfPass.setText("");

        JOptionPane.showMessageDialog(this, "Logged out successfully.");
    }

    private void applyAdminChanges() 
	{
        try 
		{
            admin.updateFruitStock(fruits,
                    new int[]{i(fruitStock[0]), i(fruitStock[1]), i(fruitStock[2]), i(fruitStock[3])},
                    new double[]{d(fruitPrice[0]), d(fruitPrice[1]), d(fruitPrice[2]), d(fruitPrice[3])});

            admin.updateVegetableStock(vegetables,
                    new int[]{i(vegStock[0]), i(vegStock[1]), i(vegStock[2]), i(vegStock[3])},
                    new double[]{d(vegPrice[0]), d(vegPrice[1]), d(vegPrice[2]), d(vegPrice[3])});

            admin.updateMeatStock(meat,
                    new int[]{i(meatStock[0]), i(meatStock[1]), i(meatStock[2])},
                    new double[]{d(meatPrice[0]), d(meatPrice[1]), d(meatPrice[2])});

            admin.saveInventory(fruits, vegetables, meat);

            refreshUI();
            JOptionPane.showMessageDialog(this, "System Updated Successfully!");
        } 
		catch (Exception e) 
		{
            JOptionPane.showMessageDialog(this, "Error: Check numeric formats.");
        }
    }

    private int v(int idx) 
	{ 
	return vSafe(idx); 
	}
    private int i(JTextField f) 
	{ 
	return Integer.parseInt(f.getText().trim()); 
	}
    private double d(JTextField f) 
	{ 
	return Double.parseDouble(f.getText().trim()); 
	}

    private void setAdminMode(boolean b) 
	{
        JTextField[][] all = {fruitStock, fruitPrice, vegStock, vegPrice, meatStock, meatPrice};
        for (JTextField[] arr : all) 
		{
            for (JTextField f : arr) 
			{
                if (f != null) f.setEditable(b);
            }
        }
    }

    private JButton styleBtn(String t, Color bg, Color fg) 
	{
        JButton b = new JButton(t);
        b.setBackground(bg);
        b.setForeground(fg);
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setPreferredSize(new Dimension(180, 35));
        return b;
    }
}
