package emp;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String str = in.next();
		int n;//代表输⼊的整数
		String firstname, lastname;//名和姓
		factory test = new factory();
		String number; //学号

		Double weekSalary, HourSalary, grossSales, commissionRate, baseSalary;
		Double Hours;
		Employee emp,up_emp;
		for(;;)
		{
		if(str.equals("exit"))
			break;
		else
		{
			if(str.equals("add"))
			{
				n=in.nextInt();
				firstname=in.next();
				lastname=in.next();
				number=in.next();
				if(n==0)
				{
					weekSalary=in.nextDouble();
					SalaridEmployee temp=new SalaridEmployee();
					temp.setfirstName(firstname);
					temp.setlastName(lastname);
					temp.setsocialSecurityNumber(number);
					temp.setweeklySalary(weekSalary);
					emp=temp;
				}
				else if(n==1)
				{
					HourSalary=in.nextDouble();
					Hours=in.nextDouble();
					HourlyEmployee temp=new HourlyEmployee();
					temp.setfirstName(firstname);
					temp.setlastName(lastname);
					temp.setsocialSecurityNumber(number);
					temp.setwage(HourSalary);
					temp.sethours(Hours);
					emp=temp;
				}
				else if(n==2)
				{
					grossSales=in.nextDouble();
					commissionRate=in.nextDouble();
					CommisionEmployee temp=new CommisionEmployee();
					temp.setfirstName(firstname);
					temp.setlastName(lastname);
					temp.setsocialSecurityNumber(number);
					temp.setgrossSales(grossSales);
					temp.setcommissionRate(commissionRate);
					emp=temp;
				}
				else 
				{
					grossSales=in.nextDouble();
					commissionRate=in.nextDouble();
					baseSalary=in.nextDouble();
					basePlusCommisionEmployee temp=new basePlusCommisionEmployee();
					temp.setfirstName(firstname);
					temp.setlastName(lastname);
					temp.setsocialSecurityNumber(number);
					temp.setgrossSales(grossSales);
					temp.setcommissionRate(commissionRate);
					temp.setbaseSalary(baseSalary);
					emp=temp;
				}
				try {
					up_emp=test.addEmployee(emp);
					System.out.println(up_emp);	
				}catch (EmployeeException e){
					e.printshit();
				}	
			}
			else if(str.equals("update"))
			{
				n=in.nextInt();
				firstname=in.next();
				lastname=in.next();
				number=in.next();
				if(n==0)
				{
					weekSalary=in.nextDouble();
					SalaridEmployee temp=new SalaridEmployee();
					temp.setfirstName(firstname);
					temp.setlastName(lastname);
					temp.setsocialSecurityNumber(number);
					temp.setweeklySalary(weekSalary);
					emp=temp;
				}
				else if(n==1)
				{
					HourSalary=in.nextDouble();
					Hours=in.nextDouble();
					HourlyEmployee temp=new HourlyEmployee();
					temp.setfirstName(firstname);
					temp.setlastName(lastname);
					temp.setsocialSecurityNumber(number);
					temp.setwage(HourSalary);
					temp.sethours(Hours);
					emp=temp;
				}
				else if(n==2)
				{
					grossSales=in.nextDouble();
					commissionRate=in.nextDouble();
					CommisionEmployee temp=new CommisionEmployee();
					temp.setfirstName(firstname);
					temp.setlastName(lastname);
					temp.setsocialSecurityNumber(number);
					temp.setgrossSales(grossSales);
					temp.setcommissionRate(commissionRate);
					emp=temp;
				}
				else 
				{
					grossSales=in.nextDouble();
					commissionRate=in.nextDouble();
					baseSalary=in.nextDouble();
					basePlusCommisionEmployee temp=new basePlusCommisionEmployee();
					temp.setfirstName(firstname);
					temp.setlastName(lastname);
					temp.setsocialSecurityNumber(number);
					temp.setgrossSales(grossSales);
					temp.setcommissionRate(commissionRate);
					temp.setbaseSalary(baseSalary);
					emp=temp;
				}
				try {
					up_emp=test.updateEmployee(number,emp);
					System.out.println(up_emp);	
				}catch (EmployeeException e){
					e.printshit();
				}	
			}
			else if(str.equals("delete"))
			{
				number=in.next();
				try {
					up_emp=test.deleteEmployee(number);
					System.out.println(up_emp);	
				}catch (EmployeeException e){
					e.printshit();
				}	
			}
			else if(str.equals("get"))
			{
				number=in.next();
				try {
					up_emp=test.getEmployee(number);
					System.out.println(up_emp);	
				}catch (EmployeeException e){
					e.printshit();
				}	
				}
			else
			{
				test.printEmployees();
			}
			str=in.next();
		}
	   }
	}
}