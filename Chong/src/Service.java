
public class Service {
	private static DataOperator d=new DataOperator();
	public static void init() {
		d.loadDatabaseDriver();
		d.connect();
	}
	public int queryUser(String user) {
		return d.queryUser(user);
	}
	public static int queryMoney(String user) {
		if(d.queryUser(user)==1)
			return d.queryMoney(user);//返回当前金额
		else 
			return -2;//不存在用户返回-2
	}
	public static int addMoney(String user ,int money) {
		int money0=d.queryMoney(user);//获取此用户当前余额
		//System.out.print(money0);
		if(money0>=0) {
			d.addMoney(user, money+money0);//更新余额
			return 1;//更新成功返回1
		}
		else
			return money0;
	}
	public static int addUser(String user) {
		return d.addUser(user);
	}
	public static void quit() {
		// TODO Auto-generated method stub
		d.quit();
	}
	public static String[][] queryAll() {
		// TODO Auto-generated method stub
		return d.queryAll();
	}
	
}
