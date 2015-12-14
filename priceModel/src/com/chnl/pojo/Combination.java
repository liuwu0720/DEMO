package com.chnl.pojo;

/**
 * Combination entity. @author MyEclipse Persistence Tools
 */

public class Combination implements Comparable< Combination > ,
        java.io.Serializable

{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private TruckInfo truckInfo; // 拖车
	private CarInfo carInfoByCarId1 = null;
	private LegInfo legInfo;
	private CarInfo carInfoByCarId2 = null;
	private CarInfo carInfoByCarId3 = null;
	private Integer totalcars;
	private Double price = 0.0;
	private Double prob1 = 0.0;
	private Double prob2 = 0.0;
	private Integer car1num = 0;
	private Integer car2num = 0;
	private Integer car3num = 0;
	private Double totalLength1; // 组合中放入商品车车后第一排总长度
	private Double totalLength2;// 组合中放入商品车车后第二排总长度
	private Double totalLength3;// 组合中放入商品车车后第三排总长度

	private Double totalWeight;
	// Constructors
	
	/** default constructor */
	public Combination()
	{}
	
	/** minimal constructor */
	public Combination( Integer id )
	{
		this.id = id;
	}
	
	public Integer getId()
	{
		return this.id;
	}
	
	public Double getTotalLength1()
	{
		return totalLength1;
	}
	
	public void setTotalLength1( Double totalLength1 )
	{
		this.totalLength1 = totalLength1;
	}
	
	public Double getTotalLength2()
	{
		return totalLength2;
	}
	
	public void setTotalLength2( Double totalLength2 )
	{
		this.totalLength2 = totalLength2;
	}
	
	public Double getTotalLength3()
	{
		return totalLength3;
	}
	
	public void setTotalLength3( Double totalLength3 )
	{
		this.totalLength3 = totalLength3;
	}
	

	/**
	 * @param id
	 * @param truckInfo
	 * @param carInfoByCarId1
	 * @param legInfo
	 * @param carInfoByCarId2
	 * @param carInfoByCarId3
	 * @param totalcars
	 * @param price
	 * @param prob1
	 * @param prob2
	 * @param car1num
	 * @param car2num
	 * @param car3num
	 * @param totalLength1
	 * @param totalLength2
	 * @param totalLength3
	 * @param totalWeight
	 */
	public Combination( Integer id , TruckInfo truckInfo ,
	        CarInfo carInfoByCarId1 , LegInfo legInfo ,
	        CarInfo carInfoByCarId2 , CarInfo carInfoByCarId3 ,
	        Integer totalcars , Double price , Double prob1 , Double prob2 ,
	        Integer car1num , Integer car2num , Integer car3num ,
	        Double totalLength1 , Double totalLength2 , Double totalLength3 ,
	        Double totalWeight )
	{
		super();
		this.id = id;
		this.truckInfo = truckInfo;
		this.carInfoByCarId1 = carInfoByCarId1;
		this.legInfo = legInfo;
		this.carInfoByCarId2 = carInfoByCarId2;
		this.carInfoByCarId3 = carInfoByCarId3;
		this.totalcars = totalcars;
		this.price = price;
		this.prob1 = prob1;
		this.prob2 = prob2;
		this.car1num = car1num;
		this.car2num = car2num;
		this.car3num = car3num;
		this.totalLength1 = totalLength1;
		this.totalLength2 = totalLength2;
		this.totalLength3 = totalLength3;
		this.totalWeight = totalWeight;
	}


	/**
	 * @param id
	 * @param truckInfo
	 * @param carInfoByCarId1
	 * @param legInfo
	 * @param carInfoByCarId2
	 * @param carInfoByCarId3
	 * @param totalcars
	 * @param price
	 * @param prob1
	 * @param prob2
	 * @param car1num
	 * @param car2num
	 * @param car3num
	 * @param totalLength1
	 * @param totalLength2
	 * @param totalLength3
	 * @param totalWeight
	 */
	public Combination( Integer id , TruckInfo truckInfo ,
	        CarInfo carInfoByCarId1 , LegInfo legInfo ,
	        CarInfo carInfoByCarId2 , CarInfo carInfoByCarId3 ,
	        Integer totalcars , Double price , Double prob1 , Double prob2 ,
	        Integer car1num , Integer car2num , Integer car3num ,
	        Double totalLength1 , Double totalLength2 , Double totalLength3 )
	{
		super();
		this.id = id;
		this.truckInfo = truckInfo;
		this.carInfoByCarId1 = carInfoByCarId1;
		this.legInfo = legInfo;
		this.carInfoByCarId2 = carInfoByCarId2;
		this.carInfoByCarId3 = carInfoByCarId3;
		this.totalcars = totalcars;
		this.price = price;
		this.prob1 = prob1;
		this.prob2 = prob2;
		this.car1num = car1num;
		this.car2num = car2num;
		this.car3num = car3num;
		this.totalLength1 = totalLength1;
		this.totalLength2 = totalLength2;
		this.totalLength3 = totalLength3;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}
	
	public TruckInfo getTruckInfo()
	{
		return this.truckInfo;
	}
	
	public void setTruckInfo( TruckInfo truckInfo )
	{
		this.truckInfo = truckInfo;
	}
	
	public CarInfo getCarInfoByCarId1()
	{
		return this.carInfoByCarId1;
	}
	
	public void setCarInfoByCarId1( CarInfo carInfoByCarId1 )
	{
		this.carInfoByCarId1 = carInfoByCarId1;
	}
	
	public LegInfo getLegInfo()
	{
		return this.legInfo;
	}
	
	public void setLegInfo( LegInfo legInfo )
	{
		this.legInfo = legInfo;
	}
	
	public CarInfo getCarInfoByCarId2()
	{
		return this.carInfoByCarId2;
	}
	
	public void setCarInfoByCarId2( CarInfo carInfoByCarId2 )
	{
		this.carInfoByCarId2 = carInfoByCarId2;
	}
	
	public CarInfo getCarInfoByCarId3()
	{
		return this.carInfoByCarId3;
	}
	
	public void setCarInfoByCarId3( CarInfo carInfoByCarId3 )
	{
		this.carInfoByCarId3 = carInfoByCarId3;
	}
	
	public Integer getTotalcars()
	{
		
		int totalNum = this.getCar1num() + this.getCar2num()
		        + this.getCar3num();
		return totalNum;
	}
	
	public void setTotalcars( Integer totalcars )
	{
		this.totalcars = totalcars;
	}
	
	public Double getPrice()
	{
		double totalPrice;
		if ( this.getCarInfoByCarId1().getIncomePrice() != null )
		{
			if ( this.getCarInfoByCarId2() == null )
			{
				totalPrice = this.getCar1num()
				        * this.getCarInfoByCarId1().getIncomePrice();
			}
			else if ( this.getCarInfoByCarId3() == null )
			{
				totalPrice = this.getCar1num()
				        * this.getCarInfoByCarId1().getIncomePrice()
				        + this.getCar2num()
				        * this.getCarInfoByCarId2().getIncomePrice();
			}
			else
			{
				totalPrice = this.getCar1num()
				        * this.getCarInfoByCarId1().getIncomePrice()
				        + this.getCar2num()
				        * this.getCarInfoByCarId2().getIncomePrice()
				        + this.getCar3num()
				        * this.getCarInfoByCarId3().getIncomePrice();
				
			}
			return Double.parseDouble( String.format( "%.4f" , totalPrice ) );
		}
		else
		{
			return this.price;
		}
		
	}
	
	public void setPrice( Double price )
	{
		this.price = price;
	}
	
	public Double getProb1()
	{
		
		int N = this.getTotalcars();
		
		int a = this.getCar1num();
		int b = this.getCar2num();
		int c = this.getCar3num();
		double py;
		double pz;
		if ( this.getCarInfoByCarId2() == null
		        || this.getCarInfoByCarId3() == null )
		{
			py = 1;
			pz = 1;
		}
		else
		{
			py = Math.pow( this.getCarInfoByCarId2().getRatio() ,
			        this.getCar2num() );
			pz = Math.pow( this.getCarInfoByCarId3().getRatio() ,
			        this.getCar3num() );
		}
		double px = Math.pow( this.getCarInfoByCarId1().getRatio() ,
		        this.getCar1num() );
		
		double NN = factorial( N );// 阶乘运算
		double aa = factorial( a );
		double bb = factorial( b );
		double cc = factorial( c );
		double p_total = px * py * pz;
		double a_b_c = aa * bb * cc;
		double prob1 = NN / a_b_c;
		double result = prob1 * p_total;
		return result;
	}
	
	/**
	 * @Description: TODO( 阶乘运算)
	 * @param n
	 * @return int 返回值描述
	 * @author liuwu
	 * @create_date 2014-8-19 下午5:04:25
	 */
	private double factorial( int n )
	{
		if ( n < 0 )
		{
			throw new IllegalArgumentException( "x must be>=0" );
		}
		if ( n <= 1 )
		{
			return 1;
		}
		else
		{
			return n * factorial( n - 1 );
		}
		
	}

	public void setProb1( Double prob1 )
	{
		this.prob1 = prob1;
	}
	
	public Double getProb2()
	{
		return this.prob2;
	}
	
	public void setProb2( Double prob2 )
	{
		this.prob2 = prob2;
	}
	
	public Integer getCar1num()
	{
		return this.car1num;
	}
	
	public void setCar1num( Integer car1num )
	{
		this.car1num = car1num;
	}
	
	public Integer getCar2num()
	{
		return this.car2num;
	}
	
	public void setCar2num( Integer car2num )
	{
		this.car2num = car2num;
	}
	
	public Integer getCar3num()
	{
		return this.car3num;
	}
	
	public void setCar3num( Integer car3num )
	{
		this.car3num = car3num;
	}
	
	public Double getTotalWeight()
	{
		double total = 0.0;

		if ( this.carInfoByCarId3 == null )
		{
			if ( this.carInfoByCarId2 == null )
			{
				total = this.carInfoByCarId1.getWeight() * this.car1num;
			}
			else
			{
				total = this.carInfoByCarId1.getWeight() * this.car1num
				        + this.carInfoByCarId2.getWeight() * this.car2num;
			}

		}
		else
		{
			total = this.carInfoByCarId1.getWeight() * this.car1num
			        + this.carInfoByCarId2.getWeight() * this.car2num
			        + this.carInfoByCarId3.getWeight() * this.car3num;
		}

		return total;
	}
	
	public void setTotalWeight( Double totalWeight )
	{
		this.totalWeight = totalWeight;
	}

	/**
	 * @Description: TODO(内部排序比较)
	 * @param o
	 * @return
	 * @author liuwu
	 * @create_date 2014-9-22 上午11:20:46
	 */
	public int compareTo( Combination o )
	{
		if ( null == o )
		{
			return 1;
		}
		else
		{
			return this.getTotalWeight().compareTo( o.getTotalWeight() );
		}
	}

	
	@Override
	public int hashCode()
	{
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public boolean equals( Object obj )
	{
		if ( obj == null )
		{
			return false;
			
		}
		else
		{
			// 去重
			if ( this.getClass() == obj.getClass() )
			{
				Combination combination = ( Combination ) obj;

				if ( this.getLegInfo().getId()
				        .equals( combination.getLegInfo().getId() )
				        && this.getCar1num().equals( combination.getCar1num() )
				        && this.getCar2num().equals( combination.getCar2num() )
				        && this.getCar3num().equals( combination.getCar3num() )
				        && this.getCarInfoByCarId1()
				                .getId()
				                .equals(
				                        combination.getCarInfoByCarId1()
				                                .getId() )
				        && this.getCarInfoByCarId2()
				                .getId()
				                .equals(
				                        combination.getCarInfoByCarId2()
				                                .getId() )
				        && this.getCarInfoByCarId3()
				                .getId()
				                .equals(
				                        combination.getCarInfoByCarId3()
				                                .getId() )
				        && this.getTruckInfo().getId()
				                .equals( combination.getTruckInfo().getId() ) )
				{
					return true;
					// 第一种情况：比如：10，1，0
				}
				else if ( this.getLegInfo().getId()
				        .equals( combination.getLegInfo().getId() )
				        && this.getCar1num().equals( combination.getCar1num() )
				        && this.getCar2num().equals( combination.getCar2num() )
				        && this.getCar3num().equals( 0 )
				        && combination.getCar3num().equals( 0 )
				        && this.getCarInfoByCarId1()
				                .getId()
				                .equals(
				                        combination.getCarInfoByCarId1()
				                                .getId() )
				        && this.getCarInfoByCarId2()
				                .getId()
				                .equals(
				                        combination.getCarInfoByCarId2()
				                                .getId() ) )
				{
					return true;
					// 第二种情况：0，10，1
				}
				else if ( this.getLegInfo().getId()
				        .equals( combination.getLegInfo().getId() )
				        && this.getCar2num().equals( combination.getCar2num() )
				        && this.getCar3num().equals( combination.getCar3num() )
				        && this.getCar1num().equals( 0 )
				        && combination.getCar1num().equals( 0 )
				        && this.getCarInfoByCarId2()
				                .getId()
				                .equals(
				                        combination.getCarInfoByCarId2()
				                                .getId() )
				        && this.getCarInfoByCarId3()
				                .getId()
				                .equals(
				                        combination.getCarInfoByCarId3()
				                                .getId() ) )
				{
					return true;
					
				}// 第三种情况：1，0，10
				else if ( this.getLegInfo().getId()
					        .equals( combination.getLegInfo().getId() )
					        && this.getCar1num().equals( combination.getCar1num() )
					        && this.getCar3num().equals( combination.getCar3num() )
					        && this.getCar2num().equals( 0 )
					        && combination.getCar2num().equals( 0 )
					        && this.getCarInfoByCarId1()
					                .getId()
					                .equals(
					                        combination.getCarInfoByCarId1()
					                                .getId() )
					        && this.getCarInfoByCarId3()
					                .getId()
					                .equals(
					                        combination.getCarInfoByCarId3()
					                                .getId() ) )
					{
						return true;
					
				}
				// 另外一种情况：车ID 1 2 3 （5，0，8） 与车ID1 3 4 （5，8，0）
				else if ( this.getLegInfo().getId()
				        .equals( combination.getLegInfo().getId() )
				        && this.getCar1num().equals( combination.getCar1num() )
				        && this.getCar3num().equals( combination.getCar2num() )
				        && this.getCar2num().equals( 0 )
				        && combination.getCar3num().equals( 0 )
				        && this.getCarInfoByCarId1()
				                .getId()

				                .equals(
				                        combination.getCarInfoByCarId1()
				                                .getId() )
				        && this.getCarInfoByCarId3()
				                .getId()
				                .equals(
				                        combination.getCarInfoByCarId2()
				                                .getId() )
				        || this.getLegInfo().getId()
				                .equals( combination.getLegInfo().getId() )
				        && this.getCar1num().equals( combination.getCar1num() )
				        && this.getCar2num().equals( combination.getCar3num() )
				        && this.getCar3num().equals( 0 )
				        && combination.getCar2num().equals( 0 )
				        && this.getCarInfoByCarId1()
				                .getId()
				                .equals(
				                        combination.getCarInfoByCarId1()
				                                .getId() )
				        && this.getCarInfoByCarId2()
				                .getId()
				                .equals(
				                        combination.getCarInfoByCarId3()
				                                .getId() ) )
				{
					return true;
				}
				
				// 第4种情况：11，0，0
				else if ( this.getLegInfo().getId()
				        .equals( combination.getLegInfo().getId() )
				        && this.getCar1num().equals( combination.getCar1num() )
				        && this.getCar2num().equals( 0 )
				        && combination.getCar2num().equals( 0 )
				        && this.getCar3num().equals( 0 )
				        && combination.getCar3num().equals( 0 )
				        && this.getCarInfoByCarId1()
				                .getId()
				                .equals(
				                        combination.getCarInfoByCarId1()
				                                .getId() ) )
				{
					return true;
					// 第5种情况：0，11，0
				}
				else if ( this.getLegInfo().getId()
				        .equals( combination.getLegInfo().getId() )
				        && this.getCar1num().equals( 0 )
				        && combination.getCar1num().equals( 0 )
				        && this.getCar2num().equals( combination.getCar2num() )
				        && this.getCar3num().equals( 0 )
				        && combination.getCar3num().equals( 0 )
				        && this.getCarInfoByCarId2()
				                .getId()
				                .equals(
				                        combination.getCarInfoByCarId2()
				                                .getId() ) )
				{
					return true;
				}
				// 第6种情况 0,0,11
				else if ( this.getLegInfo().getId()
				        .equals( combination.getLegInfo().getId() )
				        && this.getCar1num().equals( 0 )
				        && combination.getCar1num().equals( 0 )
				        && this.getCar3num().equals( combination.getCar3num() )
				        && this.getCar2num().equals( 0 )
				        && combination.getCar2num().equals( 0 )
				        && this.getCarInfoByCarId3()
				                .getId()
				                .equals(
				                        combination.getCarInfoByCarId3()
				                                .getId() ) )
				{
					return true;
				}
				else
				{
					return false;
				}
				
			}
			else
			{
				return false;
			}
			
		}

	}
	
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return super.toString();
	}
	


	

}