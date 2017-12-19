

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Car {

	String[ ] name = new String[50]; 
	int[ ] state = new int[50]; 
	String[ ] date=new String[50]; 
	int[] count=new int[50]; 
	Scanner in=new Scanner(System.in); 
	int xuhao; 
	int k=3; 
	boolean flag=true; 
	public Car(){

	}
	// 初始化程序 
	//数组初始化
	public void Car(){ 
		this.name[0]="宝马5i"; 
		this.state[0]=0;  
		this.date[0]="2017-03-01"; 

		this.name[1]="奔驰商务"; 
		this.state[1]=1;

		this.name[2]="大众Qolf"; 
		this.state[2]=1; 

		this.name[3]="奇瑞艾5"; 
		this.state[3]=0; 
		this.date[3]="2017-03-06"; 
	} 

	public void setName(String[] n){
		name=n;
	}
	public String[] getName(){
		return name;
	}
	//菜单
	void caidan(){ 

		System.out.println("欢迎使用汽车出租管理系统");
		System.out.println("----------------------------");
		System.out.println("1.新增CAR");
		System.out.println("2.查看CAR");
		System.out.println("3.删除CAR");
		System.out.println("4.借出CAR");
		System.out.println("5.归还CAR");
		System.out.println("6.退        出");
		System.out.println("----------------------------");
		System.out.println("请选择：");


		//选择
		xuhao=in.nextInt(); 
		switch(xuhao){ 
		case 1:
			this.zengjia();
			break; 
		case 2:
			this.chakan();
			break; 
		case 3:
			this.shanchu();
			break; 
		case 4:
			this.jiechu();
			break; 
		case 5:
			this.guihuan();
			break; 
		case 6:
			this.tuichu();
			break; 
		default:System.out.print("对不起，您的输入有误！请输入1—6:"); 
		}
	}
	//返回
	void fanhui(){
		System.out.print("按任意数字重新输入；按0返回："); 
		xuhao=in.nextInt(); 
		if(xuhao==0){ 
			System.out.println("-------------------------------------"); 
			this.caidan(); 
		} 
		else{
			this.zengjia();
		} 
	}
	//增加 
	void zengjia(){ 
		System.out.println("请输入新增：");
		String newCar=in.next(); 
		for(int i=0;i<k;i++){ 
			if(newCar.equals(this.name[i])){ 
				System.out.println("该CAR已存在！！"); 

			} 
		} 

		if(flag==true){ 
			this.name[k]=newCar; 
			this.state[k]=1; 
			System.out.println("新增CAR"+this.name[k]+"成功！"); 
			k++; 
			System.out.print("按任意数字继续添加；按0返回："); 
			xuhao=in.nextInt(); 
			if(xuhao==0){ 
				System.out.println("-------------------------------------"); 
				this.caidan(); 
			} 
			else{
				this.zengjia();
			} 
		} 

	} 
	//查看
	void chakan(){
		System.out.println("查看");
		System.out.println("序号\t名称\t\t状态\t借出时间\t");
		for(int i=0;i<this.name.length;i++){
			if(this.name[i]==null){
				break;
			}else if(this.state[i]==0){
				//代表已借出
				System.out.println(i+1+"\t"+this.name[i]+"\t"+"已借出"+"\t"+this.state+"天");
			}else if(this.state[i]==1){
				//没有借出

				System.out.println(i+1+"\t"+this.name[i]+"\t可借\t");
			}
		}
		System.out.println("**************************************");
		this.fanhui();


	}
	//删除
	void shanchu(){
		System.out.println("删除");

		System.out.println("请输入您要删除的CAR名称:");
		Scanner input = new Scanner(System.in);
		String name = input.next();

		//1.没有找到  ：  提示没有找到 请重新输入
		//2.找到 ：
		//1.已经借出去 ： 不能够删除
		//2.没有借出去 : 删除

		for(int i = 0;i<this.name.length;i++){
			//查找是否存在
			if(this.name[i] != null){

				if(this.name[i].equalsIgnoreCase(name)){ //已经找到

					//是否已经被借出
					if(this.state[i] == 0){ // 借出
						System.out.println(name+"已经被借出");
						break;
					}else if(this.state[i] == 1) {

						//没有借出需要删除
						//思路 ： 1.用后面一个替换前面的。
						//2.把最后面多余的地方赋值为null.


						for(int j = i ;j<this.name.length;j++){

							//做交换
							if(this.name[j +1] != null){
								this.name[j] = this.name[j+1];
								this.state[j] = this.state[j+1];

							}else{
								//清空最后一个值
								this.name[j] = null;
								this.date[j] = null;
								break;
							}

						}

						System.out.println("删除"+name+"成功");
						this.fanhui();
						break;
					}
				}

			}else {

				System.out.println("没有您要删除的CAR");
				System.out.println("***********************************************");

				this.fanhui();
				break;
			}    
		}


	}
	//借出
	void jiechu(){
		System.out.println("借出");


		System.out.println("请输入您要借的CAR名称:");
		Scanner input = new  Scanner(System.in);
		String name = input.next();

		//1.判断有没有
		//1.又没有被借出去。
		for(int i = 0;i<this.name.length;i++){

			if(this.name[i] != null){ // 有car

				if(this.name[i].equalsIgnoreCase(name) && this.state[i] == 1){ //有并且没有被借出去

					//1.修改状态
					this.state[i] = 0;
					System.out.println("请输入借车的时间(年-月-日):");
					Scanner inputDate = new Scanner(System.in);
					this.date[i] = inputDate.next();
					System.out.println("借车成功");
					this.count[i]++;
					this.fanhui();
					break;

				}else if(this.name[i].equalsIgnoreCase(name) && this.state[i] == 0){
					System.out.println("不好意思此借车已经被借出");
					this.fanhui();
					break;
				}

			}else { 

				System.out.println("没有您要找的CAR");
				this.fanhui();
				break;
			}
		}


	}
	//归还
	void guihuan(){
		System.out.println("归还");
		System.out.println("********欢迎进入还CAR页面******************");
        System.out.println("请输入还CAR的名称：");
        Scanner input = new Scanner(System.in);
        double  price = 0.0;
        String name = input.next();
        for(int i = 0;i<this.name.length;i++){

            if(this.name[i]!=null){ //有

                if(this.name[i].equalsIgnoreCase(name) && this.state[i] == 0){ //以借出就可以归还
                    this.state[i] = 1;
                    System.out.println("请输入归还车的时间(年-月-日):");
                    Scanner inputDate = new Scanner(System.in);
                    //获取时间差
                    long charge = charge1(this.date[i],inputDate.next());
                    price = charge * 1;
                    System.out.println(this.name[i]+"归还成功");
                    System.out.println("您应付"+price+"元");
                    //清空时间
                    this.date[i] = null;
                    break;

                }

            }else{
                System.out.println("没有找到匹配的信息");
                break;
            }
        }
        System.out.println("************************************************");
		this.fanhui();
	}
	public long charge1(String date1,String date2){
        
        //做时间差
        //将String转为Date做计算
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        long charge = 0;
        try{
            Date d1 = (Date) sd.parse(date1);  
            Date d2 = (Date) sd.parse(date2);
            //时间也是以毫秒为单位。
            charge = (d2.getTime() - d1.getTime()) / (1000*24*60*60);
        }catch(ParseException e){
            System.out.println(e);
        }
        
        return charge;
    }

	private long charge(String string, String next) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

    //排行榜
    public void list(){

        System.out.println("欢迎进入Dvd排行榜");
        //排序
        for(int i = 0 ; i <this.name.length-1;i++){

            if(this.name[i] != null){
                
                for(int j = 0; j<this.name.length-1-i;j++){

                    if(this.name[j] != null){

                        if(this.count[j] < this.count[j+1]){

                        int temp = this.count[j];
                        this.count[j] = this.count[j+1];
                        this.count[j+1]= temp;
                        
                        String name1 = this.name[j];
                        this.name[j] = this.name[j+1];
                        this.name[j+1] = name1;    

                        String date1= this.date[j];
                        this.date[j] = this.date[j+1];
                        this.date[j+1] = date1;    
                        
                        int state1= this.state[j];
                        this.state[j] = this.state[j+1];
                        this.state[j+1] = state1;

                        }
                    }else {
                        break;
                    }
                    
                    
                }

            }else{
                break;
            }
        }
        System.out.println("序号\t名称\t\t状态\t借出时间\t");
        for(int i=0;i<this.name.length ; i++){
            if(this.name[i] == null){
                
                break;
            }else if (this.state[i] == 0){
                //代表已经借出
                System.out.println(i+1+"\t"+"<<"+this.name[i]+">>\t已借出\t"+this.date[i]);
            }else if(this.state[i] == 1){

                //代没有借出
                System.out.println(i+1+"\t"+"<<"+this.name[i]+">>\t可借\t");
            }
        }

        System.out.println("**************************************");
            
    

}

	
	
	//退出
	void tuichu(){
		System.out.println("退出");

	}



	//测试类
	public static void main(String[] args) {

		Car c=new Car();
		c.Car();
		c.caidan();
	}

}
