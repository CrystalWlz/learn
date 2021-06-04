package emp;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.*;
public abstract class Employee implements Comparable<Employee>{
	public Employee(){
		firstName="Tom";
		lastName="Tom";
		socialSecurityNumber="000000";
	}//构造函数
	
	String firstName;
	String lastName;
	String socialSecurityNumber;
	//参数
	
	public abstract double earning();//抽象方法
	
	public String getfirstName() {
		return firstName;
	}
	public String getlastName() {
		return lastName;
	}
	public String getsocialSecurityNumber() {
		return socialSecurityNumber;
	}
	public void setfirstName(String s) {
		firstName=s;
	}
	public void setlastName(String s) {
		lastName=s;
	}//getter和setter
	public String toString() {
		return "firstName:"+firstName+"; lastName:"+lastName+"; socialSecurityNumber:"+socialSecurityNumber;
	}//toString
	public int compareTo(Employee o) {
			if(earning()<o.earning()) {
				return -1;
			}
			if(earning()>o.earning()){
				return 1;
			}
			if(earning()==o.earning()) {
				return 0;
			}
			return 0;
	}
}
class SalaridEmployee extends Employee{
	public SalaridEmployee(){}//构造函数
	double weeklySalary;//周薪
	public double earning(){
		return weeklySalary*4;
	}//重载
	public double getweeklySalary() {
		return weeklySalary;
	}
	public void setweeklySalary(double s) {
		weeklySalary=s;
	}//getter和setter
	//toString
}


class HourlyEmployee extends Employee{
	public HourlyEmployee(){}//构造函数
	double wage;//时薪
	double hours;//月工时
	public double earning() {
		return wage*hours;
	}//重载
	public double getwage() {
		return wage;
	}
	public void setwage(double s) {
		wage=s;
	}
	public double gethours() {
		return hours;
	}
	public void sethours(double s) {
		hours=s;
	}//getter和setter
	
	//toString
}

class CommisionEmployee extends Employee{
	public CommisionEmployee(){}
	double grossSales;//销售额
	double commissionRate;//提成比率
	public double earning() {
		return grossSales*commissionRate;
	}//重载
	public double getgrossSales() {
		return grossSales;
	}
	public void setgrossSales(double s) {
		grossSales=s;
	}
	public double getcommissionRate() {
		return commissionRate;
	}
	public void setcommissionRate(double s) {
		commissionRate=s;
	}//getter和setter
	//toString
}

class basePlusCommisionEmployee extends CommisionEmployee{
	public basePlusCommisionEmployee(){}
	double baseSalary;//月基本工资
	public double earning() {
		return grossSales*commissionRate+baseSalary;
	}
	public double getbaseSalary() {
		return baseSalary;
	}
	public void setbaseSalary(double s) {
		baseSalary=s;
	}//getter和setter
	//toString
}


