import java.io.*;
import java.util.*;

public class StoreManagement {
    private ArrayList<Staff> staffs;
    private ArrayList<String> workingTime;
    private ArrayList<Invoice> invoices;
    private ArrayList<InvoiceDetails> invoiceDetails;
    private ArrayList<Drink> drinks;

    public StoreManagement(String staffPath, String workingTimePath, String invoicesPath, String detailsPath, String drinksPath) {
        this.staffs = loadStaffs(staffPath);
        this.workingTime = loadFile(workingTimePath);
        this.invoices = loadInvoices(invoicesPath);
        this.invoiceDetails = loadInvoicesDetails(detailsPath);
        this.drinks = loadDrinks(drinksPath);
    }

    public ArrayList<Staff> getStaffs() {
        return this.staffs;
    }

    public void setStaffs(ArrayList<Staff> staffs){
        this.staffs = staffs;
    }
    
    public ArrayList<Drink> loadDrinks(String filePath) {
        ArrayList<Drink> drinksResult = new ArrayList<Drink>();
        ArrayList<String> drinks = loadFile(filePath);

        for (String drink : drinks) {
            String[] information = drink.split(",");
            drinksResult.add(new Drink(information[0], Integer.parseInt(information[1])));
        }

        return drinksResult;
    }

    public ArrayList<Invoice> loadInvoices(String filePath) {
        ArrayList<Invoice> invoiceResult = new ArrayList<Invoice>();
        ArrayList<String> invoices = loadFile(filePath);

        for (String invoice : invoices) {
            String[] information = invoice.split(",");
            invoiceResult.add(new Invoice(information[0], information[1], information[2]));
        }

        return invoiceResult;
    }

    public ArrayList<InvoiceDetails> loadInvoicesDetails(String filePath) {
        ArrayList<InvoiceDetails> invoiceResult = new ArrayList<InvoiceDetails>();
        ArrayList<String> invoices = loadFile(filePath);

        for (String invoice : invoices) {
            String[] information = invoice.split(",");
            invoiceResult.add(new InvoiceDetails(information[0], information[1], Integer.parseInt(information[2])));
        }

        return invoiceResult;
    }

    // requirement 1
    public ArrayList<Staff> loadStaffs(String filePath) {
        //code here and modify the return value
        ArrayList<Staff> staffsResult  = new ArrayList<Staff>();
        ArrayList<String> staffs = loadFile(filePath);

        for (String staff  : staffs) {
            String [] information = staff.split(",");
            switch (information.length) {
                case 3: 
                    staffsResult.add(new SeaSonalStaff(information[0], information[1], Integer.parseInt(information[2])));
                    break;

                case 4:
                    staffsResult.add(new FullTimeStaff(information[0], information[1], Integer.parseInt(information[2]), Double.parseDouble(information[3])));
                    break;

                case 5:
                    staffsResult.add(new Manager(information[0], information[1], Integer.parseInt(information[2]), Double.parseDouble(information[3]), Integer.parseInt(information[4])));
                    break;

                default: 
                    System.out.println("Error loadStaff: wrong format staff information !");
                    break;
            }
        }
        return staffsResult;
    }

    // requirement 2
    public ArrayList<SeaSonalStaff> getTopFiveSeasonalStaffsHighSalary() {
        //code here and modify the return value
        ArrayList<SeaSonalStaff> listSeaSonal = new ArrayList<SeaSonalStaff>();
        for (Staff staff : staffs) {
            if (staff instanceof SeaSonalStaff) {
                listSeaSonal.add((SeaSonalStaff)staff);
            }
        }
        // Collections.sort(listSeaSonal, new Comparator<SeaSonalStaff>() {
        //     @Override
        //     public int compare(SeaSonalStaff s1, SeaSonalStaff s2) {
                // if (s1.paySalary() < s2.paySalary()) {
                //     return 1;
                // } else if (s1.paySalary() > s2.paySalary()) {
                //     return -1;
                // } else {
                //     return 0;
                // }
        //     }
        // });
        ArrayList<SeaSonalStaff> topFive = new ArrayList<SeaSonalStaff>();
        for (int i = 0; i < 5; i++) {
            topFive.add(listSeaSonal.get(i));
        }
        return topFive;
    }

    // requirement 3
    public ArrayList<FullTimeStaff> getFullTimeStaffsHaveSalaryGreaterThan(int lowerBound) {
        //code here and modify the return value
        ArrayList<FullTimeStaff> result = new ArrayList<FullTimeStaff>();
        for (Staff staff : staffs) {
            if (staff instanceof FullTimeStaff) {
                
            }
        }
        return result;
    }

    // requirement 4
    public double totalInQuarter(int quarter) {
        double total = 0;
        // code here




        return total;
    }

    // requirement 5
    public Staff getStaffHighestBillInMonth(int month) {
        Staff maxStaff = null;
        //code here
        return maxStaff;
    }

    // load file as list
    public static ArrayList<String> loadFile(String filePath) {
        String data = "";
        ArrayList<String> list = new ArrayList<String>();

        try {
            FileReader reader = new FileReader(filePath);
            BufferedReader fReader = new BufferedReader(reader);

            while ((data = fReader.readLine()) != null) {
                list.add(data);
            }

            fReader.close();
            reader.close();

        } catch (Exception e) {
            System.out.println("Cannot load file");
        }
        return list;
    }

    public void displayStaffs() {
        for (Staff staff : this.staffs) {
            System.out.println(staff);
        }
    }

    public <E> boolean writeFile(String path, ArrayList<E> list) {
        try {
            FileWriter writer = new FileWriter(path);
            for (E tmp : list) {
                writer.write(tmp.toString());
                writer.write("\r\n");
            }

            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("Error.");
            return false;
        }

        return true;
    }

    public <E> boolean writeFile(String path, E object) {
        try {
            FileWriter writer = new FileWriter(path);

            writer.write(object.toString());
            writer.close();

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("Error.");
            return false;
        }

        return true;
    }
}