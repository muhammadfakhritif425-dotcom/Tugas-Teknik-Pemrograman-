public class Commission extends Hourly
{
    private double totalSales;
    private double commissionRate;

    public Commission(String name, String address, String phone,
                      String ssn, double rate, double commissionRate)
    {
        super(name, address, phone, ssn, rate);
        this.commissionRate = commissionRate;
        totalSales = 0;
    }

    public void addSales(double sales)
    {
        totalSales += sales;
    }

    public double pay()
    {
        double payment = super.pay() + (totalSales * commissionRate);
        totalSales = 0;
        return payment;
    }

    public String toString()
    {
        String result = super.toString();
        result += "\nTotal Sales: " + totalSales;
        return result;
    }
}