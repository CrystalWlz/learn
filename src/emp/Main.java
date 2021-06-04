package emp;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		int n=0;
		int m=0;
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		Employee[] ems=new Employee[100];
		for(int i=0;i<n;i++) {
			int t=0;
			t=sc.nextInt();
			if(t==0) {
				SalaridEmployee temp=new SalaridEmployee();
				temp.firstName=sc.next();
				temp.lastName=sc.next();
				temp.socialSecurityNumber=sc.next();
				temp.weeklySalary=sc.nextDouble();
				ems[i]=temp;
				continue;
			}
			if(t==1) {
				HourlyEmployee temp=new HourlyEmployee();
				temp.firstName=sc.next();
				temp.lastName=sc.next();
				temp.socialSecurityNumber=sc.next();
				temp.wage=sc.nextDouble();
				temp.hours=sc.nextDouble();
				ems[i]=temp;
				continue;
			}
			if(t==2) {
				CommisionEmployee temp=new CommisionEmployee();
				temp.firstName=sc.next();
				temp.lastName=sc.next();
				temp.socialSecurityNumber=sc.next();
				temp.grossSales=sc.nextDouble();
				temp.commissionRate=sc.nextDouble();
				ems[i]=temp;
				continue;
			}
			if(t==3) {
				basePlusCommisionEmployee temp=new basePlusCommisionEmployee();
				temp.firstName=sc.next();
				temp.lastName=sc.next();
				temp.socialSecurityNumber=sc.next();
				temp.grossSales=sc.nextDouble();
				temp.commissionRate=sc.nextDouble();
				temp.baseSalary=sc.nextDouble();
				ems[i]=temp;
				continue;
				}
		}
		Arrays.sort(ems,(first,second)->(int)(first.earning()-second.earning()));
			//排序
		m=sc.nextInt();
		for(int i=0;i<m;i++) {
			int t=0;
			t=sc.nextInt();
			if(t==0) {
				String tempName=sc.next();
				for(int j=0;j<n;j++) {
					if(ems[j].firstName.equals(tempName)) {
						System.out.println(ems[j].toString()+"; earning:"+String.format("%.2f",ems[j].earning()));
					}
				}
			}
			if(t==1) {
				String tempNumber=sc.next();
				for(int j=0;j<n;j++) {
					if(ems[j].socialSecurityNumber.equals(tempNumber)) {
						System.out.println(ems[j].toString()+"; earning:"+String.format("%.2f",ems[j].earning()));
					}
				}
			}
		}
	}
}