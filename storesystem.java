package big_homework;

import java.util.ArrayList;
import java.util.Iterator;

public class storesystem{
	public static void mian(String[] args)
	{

		ProductSpecification p1 = new ProductSpecification("00001","UML与模式应用",18,"A");
		ProductSpecification p2 = new ProductSpecification("00001","Java与模式",34,"C");
		ProductSpecification p3 = new ProductSpecification("00003","HeadFirst设计模式",58,"C");
		ProductSpecification p4 = new ProductSpecification("00004","爱丽丝历险记",30,"B");
		ProductSpecification p5 = new ProductSpecification("00005","煲汤大全",20,"D");
		PricingStrategyFactory factor = PricingStrategyFactory.getInstance();
		SaleLineItem item1 = new SaleLineItem(2,p1,factor.getPricingStrategy(p1.getType()));
		SaleLineItem item2 = new SaleLineItem(2,p2,factor.getPricingStrategy(p2.getType()));
		SaleLineItem item3 = new SaleLineItem(1,p3,factor.getPricingStrategy(p3.getType()));
		SaleLineItem item4 = new SaleLineItem(3,p4,factor.getPricingStrategy(p4.getType()));
		SaleLineItem item5 = new SaleLineItem(1,p5,factor.getPricingStrategy(p5.getType()));
		Sale s = new Sale();
		s.addItem(item1);
		s.addItem(item2);
		s.addItem(item3);
		s.addItem(item4);
		s.addItem(item5);
		System.out.println("The total price is:"+String.format("%.2f",s.getTotal()));
	}
}

//书的描述信息，包括书的isbn号、书名、单价和书的类型
class ProductSpecification
{
	String ISBN;
	String Name;
	double Price;
	String Type;
	
	public ProductSpecification(String ISBN, String Name, double Price ,String Type) 
	{
		this.ISBN = ISBN;
		this.Name = Name;
		this.Price =Price;
		this.Type = Type;
	}
	public String getISBN() 
	{
		return ISBN;
	}
	public String getName()
	{
		return Name;
	}
	public double getPrice() 
	{
		return Price;
	}
	public String getType() 
	{
		return Type;
	}
}

//根据具体的优惠策略计算折扣后1个销售项目（SaleLineItem）的总价
class PricingStrategyFactory
{
	 private static PricingStrategyFactory instance = new PricingStrategyFactory();
	 private PricingStrategyFactory() {};
	 public static PricingStrategyFactory getInstance() 
	 {
			return instance;
	 }
	 
	 public IPricingStrategy getPricingStrategy(String BookType) 
	 {
		if(BookType.equals("A")) 
		{
			return new FlatRateStrategy(1);//教材类图书，每本1元折扣
		}
		else if(BookType.equals("B")) 
		{
			return new PercentageStrategy(7);//连环画类图书，每本7%折扣
		}
		else if(BookType.equals("C")) 
		{
			return new PercentageStrategy(3);//非教材类的计算机图书，每本3%折扣
		}
		else 
		{
			return new NoDiscountStrategy();//其它，没有折扣
		}
	}
}

interface IPricingStrategy
{
	public double getSubTotal(SaleLineItem sli);//得到单本图书优惠后的价格
}

class SaleLineItem 
{
	private int copies;
	private ProductSpecification prodSpec;
	private IPricingStrategy strategy;
	
	public SaleLineItem(int copies, ProductSpecification prodSpec, IPricingStrategy strategy)
	{
		this.copies = copies;
		this.prodSpec = prodSpec;
		this.strategy = strategy;
	}
	public double getSubTotal() 
	{
		return copies*(strategy.getSubTotal(this));
    } //得到此种图书，优惠后的价格
	public ProductSpecification getPro()
	{
		return prodSpec;
	}
}

class FlatRateStrategy implements IPricingStrategy
{
	private double discountPerBook;
	public FlatRateStrategy(double a){
		discountPerBook = a;
	}  //构造函数
	
	public double getSubTotal(SaleLineItem sli) {
		return sli.getPro().getPrice()-discountPerBook; //原先的价格-优惠的价格
	}
}

class PercentageStrategy implements IPricingStrategy
{
	private int discountPercentage;
	public PercentageStrategy(int a) 
	{
		discountPercentage = a;
	}
	
	public double getSubTotal(SaleLineItem sli) 
	{
		double discount = discountPercentage/100.0;
		return sli.getPro().getPrice()*(1-discount);
		//原先的价格*（1-折扣力度）
	}
}

class NoDiscountStrategy implements IPricingStrategy
{
	 public NoDiscountStrategy() {} //构造函数
	 public double getSubTotal(SaleLineItem sli) 
	 {
		 return sli.getPro().getPrice();//直接返回价格
	 }
}

class Sale
{
	private ArrayList<SaleLineItem> list = new ArrayList();
	public Sale() {}
	
	public void addItem(SaleLineItem it) 
	{		
	   list.add(it);
	}
	public double getTotal() 
	{
	    double total =0;
	    for(Iterator<SaleLineItem> iter = list.iterator();iter.hasNext();) 
	    {
	    	total = total + iter.next().getSubTotal();
	    }
	    	return total;
	} 
}