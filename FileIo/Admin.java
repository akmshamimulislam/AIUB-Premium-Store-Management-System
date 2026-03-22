package FileIo;
import java.io.*;
import Class.Fruits;
import Class.Vegetable;
import Class.Meat;

public class Admin 
{
    private final String username = "aiub@edu";
    private final String password = "pass1234";
    private boolean loggedIn = false;

    protected double total = 0.0;

    private static final String INVENTORY_FILE = "Report\\inventory.txt";
    private static final String SALES_FILE = "Report\\sales.txt";
    private static final String TOTAL_SALES_FILE = "Report\\totalSales.txt";

    public boolean isLoggedIn() 
	{ 
	return loggedIn; 
	}

    public boolean login(String user, String pass) 
	{
        if (user != null && pass != null && user.equals(username) && pass.equals(password)) 
		{
            loggedIn = true;
            loadTotalSales();
            return true;
        }
        return false;
    }

    public void logout() 
	{ 
	loggedIn = false; 
	}

    public void saveInventory(Fruits f, Vegetable v, Meat m) 
	{
        try (PrintWriter pw = new PrintWriter(new FileWriter(INVENTORY_FILE))) 
		{

            pw.println(
                f.getStock(Fruits.APPLE) + "," + f.getPrice(Fruits.APPLE) + "," +
                f.getStock(Fruits.BANANA) + "," + f.getPrice(Fruits.BANANA) + "," +
                f.getStock(Fruits.ORANGE) + "," + f.getPrice(Fruits.ORANGE) + "," +
                f.getStock(Fruits.MANGO) + "," + f.getPrice(Fruits.MANGO)
            );

            pw.println(
                v.getStock(Vegetable.POTATO) + "," + v.getPrice(Vegetable.POTATO) + "," +
                v.getStock(Vegetable.CARROT) + "," + v.getPrice(Vegetable.CARROT) + "," +
                v.getStock(Vegetable.TOMATO) + "," + v.getPrice(Vegetable.TOMATO) + "," +
                v.getStock(Vegetable.CUCUMBER) + "," + v.getPrice(Vegetable.CUCUMBER)
            );

            pw.println(
                m.getStock(Meat.BEEF) + "," + m.getPrice(Meat.BEEF) + "," +
                m.getStock(Meat.CHICKEN) + "," + m.getPrice(Meat.CHICKEN) + "," +
                m.getStock(Meat.MUTTON) + "," + m.getPrice(Meat.MUTTON)
            );

        } 
		catch (IOException e) 
		{
            System.err.println("Error saving inventory: " + e.getMessage());
        }
    }

    public void loadInventory(Fruits f, Vegetable v, Meat m) 
	{
        File file = new File(INVENTORY_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) 
		{

            String fruitsLine = br.readLine();
            String vegLine = br.readLine();
            String meatLine = br.readLine();

            if (fruitsLine != null) 
			{
                String[] x = fruitsLine.split(",");
                if (x.length >= 8) 
				{
                    f.setStock(Fruits.APPLE, Integer.parseInt(x[0]));   
                    f.setStock(Fruits.BANANA, Integer.parseInt(x[2]));  
                    f.setStock(Fruits.ORANGE, Integer.parseInt(x[4]));  
                    f.setStock(Fruits.MANGO, Integer.parseInt(x[6]));   
					
					f.setPrice(Fruits.APPLE, Double.parseDouble(x[1]));
					f.setPrice(Fruits.BANANA, Double.parseDouble(x[3]));
					f.setPrice(Fruits.ORANGE, Double.parseDouble(x[5]));
					f.setPrice(Fruits.MANGO, Double.parseDouble(x[7]));
                }
            }

            if (vegLine != null) 
			{
                String[] x = vegLine.split(",");
                if (x.length >= 8) 
				{
                    v.setStock(Vegetable.POTATO, Integer.parseInt(x[0]));    
                    v.setStock(Vegetable.CARROT, Integer.parseInt(x[2]));    
                    v.setStock(Vegetable.TOMATO, Integer.parseInt(x[4]));    
                    v.setStock(Vegetable.CUCUMBER, Integer.parseInt(x[6]));  
					
					v.setPrice(Vegetable.POTATO, Double.parseDouble(x[1]));
					v.setPrice(Vegetable.CARROT, Double.parseDouble(x[3]));
					v.setPrice(Vegetable.TOMATO, Double.parseDouble(x[5]));
					v.setPrice(Vegetable.CUCUMBER, Double.parseDouble(x[7]));
                }
            }

            if (meatLine != null) {
                String[] x = meatLine.split(",");
                if (x.length >= 6) 
				{
                    m.setStock(Meat.BEEF, Integer.parseInt(x[0]));    
                    m.setStock(Meat.CHICKEN, Integer.parseInt(x[2]));  
                    m.setStock(Meat.MUTTON, Integer.parseInt(x[4]));   
					 
					m.setPrice(Meat.BEEF, Double.parseDouble(x[1]));
					m.setPrice(Meat.CHICKEN, Double.parseDouble(x[3]));
					m.setPrice(Meat.MUTTON, Double.parseDouble(x[5]));
                }
				
            }

        } catch (Exception e) 
		{
            System.err.println("Error loading inventory: " + e.getMessage());
        }
    }

    public void saveSales(double saleAmount) 
	{
        total += saleAmount;
        saveTotalSales();
    }

    public void generateReceipt(String details, double saleTotal, String paymentMethod)
	{
        try (FileWriter fw = new FileWriter(SALES_FILE, true))
		{
            fw.write("****************************************\n");
            fw.write("           AIUB PREMIUM STORE           \n");
            fw.write("****************************************\n");
            fw.write("----------------------------------------\n");
            fw.write(details == null ? "" : details);
            if (details != null && !details.endsWith("\n")) fw.write("\n");
            fw.write("----------------------------------------\n");
            fw.write("GRAND TOTAL: ৳" + String.format("%.2f", saleTotal) + "\n");
            fw.write("PAYMENT METHOD: " + (paymentMethod == null ? "" : paymentMethod.toUpperCase()) + "\n");
            fw.write("****************************************\n\n");
        } 
		catch (IOException e) 
		{
            System.out.println("Sales Log Error: " + e.getMessage());
        }
    }

    public String getSalesHistoryData() 
	{
        if (!loggedIn) 
		{
			return "Access Denied. Please login.";
		}

        loadTotalSales();

        StringBuilder sb = new StringBuilder();
        sb.append("========== AIUB STORE SALES HISTORY ==========\n\n");

        File file = new File(SALES_FILE);
        if (!file.exists()) 
		{
            sb.append("No transactions found in record.\n");
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) 
			{
                String line;
                while ((line = br.readLine()) != null) sb.append(line).append("\n");
            } 
			catch (IOException e) 
			{
                sb.append("No transactions found in record.\n");
            }
        }

        sb.append("\n----------------------------------------------\n");
        sb.append("TOTAL ACCUMULATED SALES: ৳").append(String.format("%.2f", total));
        return sb.toString();
    }

    public void loadTotalSales() 
	{
        File f = new File(TOTAL_SALES_FILE);
        if (!f.exists()) 
		{ 
	    total = 0.0; 
		return; 
		}

        try (BufferedReader br = new BufferedReader(new FileReader(TOTAL_SALES_FILE))) 
		{
            String line = br.readLine();
            total = (line == null || line.trim().isEmpty()) ? 0.0 : Double.parseDouble(line.trim());
        } 
		catch (Exception e) 
		{
            total = 0.0;
        }
    }

    private void saveTotalSales() 
	{
        try (FileWriter fw = new FileWriter(TOTAL_SALES_FILE)) 
		{
            fw.write(Double.toString(total));
        } 
		catch (IOException e) 
		{
            System.err.println("Error saving total sales: " + e.getMessage());
        }
    }

    public void updateFruitStock(Fruits f, int[] c, double[] p) 
	{
        if (!loggedIn)
	    { 
		System.err.println("Access Denied: Admin login required"); 
		return; 
		}
        f.setStock(Fruits.APPLE, c[0]);  f.setStock(Fruits.BANANA, c[1]);
        f.setStock(Fruits.ORANGE, c[2]); f.setStock(Fruits.MANGO, c[3]);

        f.setPrice(Fruits.APPLE, p[0]);  f.setPrice(Fruits.BANANA, p[1]);
        f.setPrice(Fruits.ORANGE, p[2]); f.setPrice(Fruits.MANGO, p[3]);
    }

    public void updateVegetableStock(Vegetable v, int[] c, double[] p) 
	{
        if (!loggedIn) 
		{ 
	     System.err.println("Access Denied: Admin login required"); 
		 return; 
		 }
        v.setStock(Vegetable.POTATO, c[0]);   v.setStock(Vegetable.CARROT, c[1]);
        v.setStock(Vegetable.TOMATO, c[2]);   v.setStock(Vegetable.CUCUMBER, c[3]);

        v.setPrice(Vegetable.POTATO, p[0]);   v.setPrice(Vegetable.CARROT, p[1]);
        v.setPrice(Vegetable.TOMATO, p[2]);   v.setPrice(Vegetable.CUCUMBER, p[3]);
    }

    public void updateMeatStock(Meat m, int[] c, double[] p) 
	{
        if (!loggedIn) 
		{ 
	     System.err.println("Access Denied: Admin login required"); 
		 return; 
		 }
        m.setStock(Meat.BEEF, c[0]);     m.setStock(Meat.CHICKEN, c[1]); m.setStock(Meat.MUTTON, c[2]);
        m.setPrice(Meat.BEEF, p[0]);     m.setPrice(Meat.CHICKEN, p[1]); m.setPrice(Meat.MUTTON, p[2]);
    }

    public double getTotalSales() 
	{ 
	return total; 
	}
}
