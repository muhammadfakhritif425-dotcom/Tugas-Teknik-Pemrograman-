import id.ac.polban.employee.model.*;
import id.ac.polban.employee.service.*;

public class EmployeeMain {

    public static void main(String[] args) {

        Department dept = new Department("IT");
         EmploymentType type = new EmploymentType("Full-Time");

        Employee emp1 = new Employee(1, "Fakhri", dept, type, 5000000);
        Employee emp2 = new Employee(2, "Budi", dept, type, 4000000);

        EmployeeService service = new EmployeeService();
        service.addEmployee(emp1);
        service.addEmployee(emp2);
        service.raiseSalary(1, 10);
        


        System.out.println("Nama Employee 1: " + emp1.getName());
        System.out.println("Department: " + emp1.getDepartment().getName());
        System.out.println("Gaji Setelah Naik: " + service.getEmployee(1).getSalary());
        System.out.println("Total Employee: " + Employee.getTotalEmployee());

    }
}
