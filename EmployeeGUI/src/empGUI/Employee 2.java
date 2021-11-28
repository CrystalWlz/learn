package empGUI;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
abstract class Employee implements Comparable<Employee>{
	public Employee(){
		firstName="Tom";
		lastName="Tom";
		socialSecurityNumber="000000";
	}//构造函数
	
	private String firstName;
	private String lastName;
	private String socialSecurityNumber;
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
		this.firstName=s;
	}
	public void setlastName(String s) {
		this.lastName=s;
	}
	public void setsocialSecurityNumber(String s) {
		this.socialSecurityNumber=s;
	}
	//getter和setter
	public String toString() {
		return "firstName:"+getfirstName()+"; lastName:"+getlastName()+"; socialSecurityNumber:"+getsocialSecurityNumber()+"; earning:"+String.format("%.2f", earning());
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
	private double weeklySalary;//周薪
	public double earning(){
		return getweeklySalary()*4;
	}//重载
	public double getweeklySalary() {
		return weeklySalary;
	}
	public void setweeklySalary(double s) {
		this.weeklySalary=s;
	}//getter和setter
	//toString
}


class HourlyEmployee extends Employee{
	public HourlyEmployee(){}//构造函数
	private double wage;//时薪
	private double hours;//月工时
	public double earning() {
		return getwage()*gethours();
	}//重载
	public double getwage() {
		return wage;
	}
	public void setwage(double s) {
		this.wage=s;
	}
	public double gethours() {
		return hours;
	}
	public void sethours(double s) {
		this.hours=s;
	}//getter和setter
	
}

class CommisionEmployee extends Employee{
	public CommisionEmployee(){}
	private double grossSales;//销售额
	private double commissionRate;//提成比率
	public double earning() {
		return getgrossSales()*getcommissionRate();
	}//重载
	public double getgrossSales() {
		return grossSales;
	}
	public void setgrossSales(double s) {
		this.grossSales=s;
	}
	public double getcommissionRate() {
		return commissionRate;
	}
	public void setcommissionRate(double s) {
		this.commissionRate=s;
	}//getter和setter
	//toString
}

class basePlusCommisionEmployee extends CommisionEmployee{
	public basePlusCommisionEmployee(){}
	private double baseSalary;//月基本工资
	public double earning() {
		return getgrossSales()*getcommissionRate()+getbaseSalary();
	}
	public double getbaseSalary() {
		return baseSalary;
	}
	public void setbaseSalary(double s) {
		this.baseSalary=s;
	}//getter和setter
	//toString
}
class EmployeeException extends Exception{
	public String code;
	public String message;
	public EmployeeException(String c,String m){
		this.code=c;
		this.message=m;
	}
	public void printshit() {
		System.out.println(code);
		System.out.println(message);
	}
}
class factory{
	private TreeMap<String,Employee> employees=new TreeMap<>();
	Employee getEmployee(String empSecNum) throws EmployeeException{
		if(employees.containsKey(empSecNum)) {
			return employees.get(empSecNum);
		}else {
			throw new EmployeeException("1004","employee not found.");
		}
	}
	Employee deleteEmployee(String empSecNum)throws EmployeeException {
		if(employees.containsKey(empSecNum)) {
			Employee e=employees.get(empSecNum);
			employees.remove(empSecNum);
			return e;
		}else {
			throw new EmployeeException("1002","employee not found.");
		}
	}
	Employee addEmployee(Employee emp) throws EmployeeException{
		if(!employees.containsKey(emp.getsocialSecurityNumber())) {
			employees.put(emp.getsocialSecurityNumber(), emp);
			return emp;
		}else {
			throw new EmployeeException("1001","employee exists.");
		}
	}
	Employee updateEmployee(String empSecNum,Employee emp)throws EmployeeException {
		if(employees.containsKey(empSecNum)) {
			employees.replace(empSecNum, emp);
			return emp;
		}else {
			throw new EmployeeException("1003","employee not found.");
		}
	}
	void printEmployees() {
		Collection<Employee> values=employees.values();
		for(Employee e : values) {
			System.out.println(e.toString());
		}
	}
}
class MyDrawPanel extends JPanel{
	public void paintComponent(Graphics g) {
			Image image=new ImageIcon("").getImage();
			g.drawImage(image, 0, 0, this);		
		}
}
class EmployeeGUI  implements ActionListener{
		JButton EmployeeInfoInput;
		
public void actionPerformed(ActionEvent event) {
	
}
		
		public void go() {
			JFrame frame=new JFrame();
			EmployeeInfoInput=new JButton("EmployeeInfoInput");
			
			EmployeeInfoInput.addActionListener(this);
			
			frame.getContentPane().add(BorderLayout.CENTER,EmployeeInfoInput);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(300,300);
			frame.setVisible(true);
			
		}
}