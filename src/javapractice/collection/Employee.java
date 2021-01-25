package javapractice.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Employee implements Comparable<Employee>{

    private int empId; //primitive
    private String empName;
    private String companyName;

    public Employee(int empId, String empName, String companyName) {
        this.empId = empId;
        this.empName = empName;
        this.companyName = companyName;
    }

    @Override
    public int compareTo(Employee object){
        return this.getEmpId() - object.getEmpId();
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object object){
        if(object == this){
            return true;
        }
        /*
        * Don't use getClass() to compare object equality because Hibernate uses proxy and
         * this check will always fail. Instead use instanceof operator, it respect proxy because
          * they have IS-A relationship with actual object.
        * */
        if(object == null || object.getClass() != this.getClass()){
            return false;
        }
        /* it will fail for subclass (loss equality check), so you can use if class i immutable
        if(!(object instanceof Employee)){
            return false;
        }*/

        Employee employee = (Employee)object;
        return ((employee.getEmpId() == this.getEmpId()) &&
                (employee.getCompanyName() == this.getCompanyName() || (employee.getCompanyName() != null && employee.getCompanyName().equals(this.getCompanyName()))));
    }

    @Override
    public int hashCode(){
        final int primeNo = 31;
        int result = 1;
        result = primeNo * result + this.getEmpId();
        result = primeNo*result+(this.getCompanyName() == null ? 0:this.getCompanyName().hashCode());
        return result;
    }

    @Override
    public String toString(){
        return this.getEmpId()+ " "+this.getEmpName()+" "+this.getCompanyName();
    }

    public static void main(String[] args) {
        Employee e = new Employee(2,"raj","peopleStrong");
        Employee e2 = new Employee(1,"deshraj","peopleStrong");
        if(e.equals(e2)){
            System.out.println("equal"+" its hashCode : "+(e.hashCode() == e2.hashCode()));
        }else{
            System.out.println("not equal");
        }
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(e);
        employeeList.add(e2);
        System.out.println("before sorting  :"+employeeList.toString());
        Collections.sort(employeeList,(em1,em2)->em1.getEmpName().compareTo(em2.getEmpName()));
        System.out.println("after  sorting  :"+employeeList.toString());
    }
}
