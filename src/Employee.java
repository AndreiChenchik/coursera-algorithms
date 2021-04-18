/* *****************************************************************************
 *  Name:              Andrei Chenchik
 *  Coursera User ID:  c27c0b79c8a674f5a98145672b4581b2
 *  Last modified:     March 19, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class Employee implements Comparable<Employee> {
    private int salary;

    public Employee(int salary) {
        this.salary = salary;
    }

    public static void main(String[] args) {
        Employee employeeOne = new Employee(100);
        Employee employeeTwo = new Employee(150);
        StdOut.println(employeeOne.compareTo(employeeTwo));
    }

    public int compareTo(Employee that) {
        return Integer.compare(this.salary, that.salary);
    }

    public int getSalary() {
        return this.salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
