package emp;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
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
			Image image=new ImageIcon("IMG_4840.jpeg").getImage();
			g.drawImage(image, 3, 4, this);		
		}
} 
class EmployeeGUI  implements ActionListener{
		JFrame frame;
		JButton EmployeeInfoInput;
		
public void actionPerformed(ActionEvent event) {
	frame.repaint();
}
		
		public void go() {
			frame=new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			EmployeeInfoInput=new JButton("EmployeeInfoInput");
			
			EmployeeInfoInput.addActionListener(this);
			MyDrawPanel drawPanel = new MyDrawPanel();
			
			frame.getContentPane().add(BorderLayout.SOUTH,EmployeeInfoInput);
			frame.getContentPane().add(BorderLayout.CENTER,drawPanel);
			
			frame.setSize(500,500);
			frame.setVisible(true);
			
		}
}
class Enter{    
		JLabel label = new JLabel();
		JFrame window1;
	//我提前声明了一些按钮、标签以及输入框，让他们能被不同的内部类调用和修改
		factory f = new factory();
		public JButton btn1 = new JButton("EmployeeInfoInput");//一级菜单
		public JButton btn2 = new JButton("Search");//一级菜单
		public JButton btn3 = new JButton("退出系统");//一级菜单
		public JButton btn4 = new JButton("CommisionEmployee类员工");//二级菜单
		public JButton btn5 = new JButton("BasePlusCommisionEmployee类员工");//二级菜单
		public JButton btn6 = new JButton("返回");//二级菜单
		public JButton btn7 = new JButton("完成");//三级菜单CommisionEmployee类员工
		public JButton btn8 = new JButton("返回");//二级菜单
		public JButton btn9 = new JButton("完成");//三级菜单BasePlusCommisionEmployee类员工
		public JButton btn10 = new JButton("AverageEarningSearch");//二级菜单
		public JButton btn11 = new JButton("返回");//二级菜单
		public JButton btn12 = new JButton("ShowInfo");//二级菜单
		public JButton btn13 = new JButton("返回");//三级菜单ShowInfo
	
		JTextField text2 = new JTextField(25);//三级菜单CommisionEmployee类员工
		JTextField text1 = new JTextField(25);//三级菜单BasePlusCommisionEmployee类员工
		String ave2;
		
	//编写Enter()的构造方法	
		public Enter(){
			//先来实现主界面的设计
			window1 = new JFrame("员工信息的录入和查询");
			window1.add(btn1);
			window1.add(btn2);
			window1.add(btn3);
			window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口的同时结束整个程序
			window1.setSize(480, 600);
			window1.setLocation(720, 240);
			window1.setLayout(new FlowLayout(FlowLayout.CENTER,200,100));
			window1.setVisible(true);
			
		//然后对主界面的三个按钮进行监听
			btn1.addActionListener(new btn1Listener());
			btn2.addActionListener(new btn2Listener());
			btn3.addActionListener(new btn3Listener());
		}
		class btn1Listener implements ActionListener{
			public void actionPerformed(ActionEvent event) {
				new CommisionEmployeePage();
			}
		}
		class btn2Listener implements ActionListener{
			public void actionPerformed(ActionEvent event) {
				new AverageEarningSearch();
			}
		}
		class btn3Listener implements ActionListener{
			public void actionPerformed(ActionEvent event) {
				window1.dispose();
			}
		}
		public class CommisionEmployeePage{
			public CommisionEmployeePage()
			{
			//其实创建不同的页面形式的大同小异，可以再声明一个内部类专门处理页面的创建
			//这样的代码思路更清晰，看起来也更好看
			//但我已经写完了...所以我懒得改了，直接Ctrl c + Ctrl v
				JFrame window2 = new JFrame("CommisionEmployee");
				window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window2.setSize(480, 600);
				window2.setLocation(720, 240);
				window2.setLayout(new FlowLayout(FlowLayout.CENTER,200,100));
				window2.setVisible(true);
				window2.add(btn4);
				window2.add(btn5);
				window2.add(btn6);
				btn4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new InputCommisionEmployee();
					}
				});
				btn5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new InputBasePlusCommisionEmployee();
					}
				});
				btn6.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						window2.dispose();
					}
				});
			}
		}
		public class InputCommisionEmployee{
			public InputCommisionEmployee()
			{
				JFrame window3 = new JFrame("InputCommisionEmployee");
				window3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window3.setSize(480, 600);
				window3.setLocation(720, 240);
				window3.setLayout(new FlowLayout(FlowLayout.CENTER,200,100));
				window3.setVisible(true);
				window3.add(new JLabel("请输入员工信息形式如下："));
				window3.add(new JLabel("gzo zihan 2018211031 7000 0.1"));
				window3.add(text2);
				window3.add(btn7);
				btn7.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String line = text2.getText();
						String[] str = line.split(" ");
						double a = Double.parseDouble(str[3]);
						double b = Double.parseDouble(str[4]);
						String c = Double.toString(a*b);
						f.addEmployee(str[2],c);
						window3.dispose();
					}
				});
			}
		}
	public class Search{
		public Search(){
			JFrame window4 = new JFrame("CommisionEmployee");
			window4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window4.setSize(480, 600);
			window4.setLocation(720, 240);
			window4.setVisible(true);
			window4.add(new JLabel("全部员工的平均工资为:"));
			ave2 = Double.toString(f.Average());
			label.setText(ave2);
			//这里的label标签要声明在Enter类的全局变量中，不然是不会动态显示的
			//我最一开始不知道，浪费了好多时间QwQ
			window4.add(label);
			window4.add(btn8);
			window4.setLayout(new FlowLayout(FlowLayout.CENTER,200,100));
			btn8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					window4.dispose();
				}
			});
		}
	}
	public class AverageEarningSearch{
		public AverageEarningSearch(){
			JFrame window6 = new JFrame("AverageEarningSearch");
			window6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window6.setSize(480, 600);
			window6.setLocation(720, 240);
			window6.setLayout(new FlowLayout(FlowLayout.CENTER,200,100));
			window6.setVisible(true);
			window6.add(btn10);
			window6.add(btn12);
			window6.add(btn11);
			btn10.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Search();
				}
			});
			btn11.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					window6.dispose();
				}
			});
			//这里我加了一点小功能，显示已经输入的员工的编号和工资
			//其实我是想加名字的，但map只储存了编号和工资，就懒得再去处理名字了
			//我觉得可以在Enter的构造方法前声明两个数组来分别储存firstName和lastName
			//然后在显示的时候加上去
			btn12.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ShowInfo();
				}
			});
			
		}
		public class ShowInfo{
			public ShowInfo(){
				JFrame window7 = new JFrame("ShowInfo");
				window7.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window7.setSize(480, 600);
				window7.setLocation(720, 240);
				window7.setLayout(new FlowLayout(FlowLayout.CENTER,100,50));
				window7.setVisible(true);
				String[] Info = {"socialNumber","Earning"};
				String[] data1 =  f.Number();
				String[] data2 = f.Money();
				int num = f.one();
				String[][] base;
				base = new String[10][10];
				for(int j = 0;j < num;j++)
				{
					base[j][0] = data1[j];
					base[j][1] = data2[j];
				}
				//这里用了JTable的构造方法之一，比另一个简便
				//base数组储存了表格信息，Info数组储存了表格名称
				JTable table = new JTable(base,Info);//这里用了JTable的构造方法之一，比另一个简便
				//一定要加这个JScrollPane(添加滚动条)，不然表格名称无法显示
				//我也不知道为什么非要加....反正加了就能显示
				JScrollPane ScrollPane  = new JScrollPane(table);
				window7.add(ScrollPane );
				window7.add(btn13);
				btn13.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						window7.dispose();
					}
				});
			}
		}
	}

}