// *******************************************************
// Salesperson.java
//
// Represents a sales person who has a first name, last
// name, and total number of sales.
// *******************************************************
public class Salesperson implements Comparable
{
    private String firstName, lastName;
    private int totalSales;

    public Salesperson (String first, String last, int sales)
    {
        firstName = first;
        lastName = last;
        totalSales = sales;
    }

    public String toString()
    {
        return lastName + ", " + firstName + ": \t" + totalSales;
    }

    public boolean equals (Object other)
    {
        return (lastName.equals(((Salesperson)other).getLastName()) &&
                firstName.equals(((Salesperson)other).getFirstName()));
    }

    public int compareTo(Object other)
    {
        int result;
        Salesperson otherPerson = (Salesperson) other;
        result = this.totalSales - otherPerson.getSales(); // bandingkan berdasarkan total sales
        if (result == 0) // jika sales sama, gunakan nama sebagai tiebreaker
            result = (this.lastName + this.firstName).compareTo(otherPerson.getLastName() + otherPerson.getFirstName());
        return result;
    }

    public String getFirstName() { return firstName; }
    public String getLastName()  { return lastName; }
    public int getSales()        { return totalSales; }
}