import java.sql.*;
public class DataOperator {
	Connection con;
	private PreparedStatement pstmt;
	private String sql;
	public void loadDatabaseDriver() {//��������
		try {
			Class.forName("com.hxtt.sql.access.AccessDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("�������ݿ�����ʧ��");
			System.out.println(e);
		}
	}
	public void connect() {//��������
		String connectString="jdbc:Access://Access/yeb.accdb";
		try {
			con=DriverManager.getConnection(connectString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("���ݿ����ӳ���");
			System.out.println(e);
		}
	}
	public  int queryUser(String user) {//�������޵�ǰ�û�
		sql="SELECT * FROM users WHERE userName = ?";
		ResultSet rs;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user);
			rs=pstmt.executeQuery();
			if(rs.next())
				return 1;//��ѯ�ɹ�����1
			else 
				return -2;//��ѯʧ�ܷ���-2
		} catch (SQLException e) {
			System.out.print(e);
			return -1;//��ѯ���󷵻�-1
		}
	}
	public  int queryMoney(String user) {//��ѯ���û����˻����
		sql="SELECT userMoney FROM users WHERE userName = ?";
		ResultSet rs;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user);
			rs=pstmt.executeQuery();
			if(rs.next())
				return rs.getInt(1);//���ص�ǰ���
			else
				return -2;
		} catch (SQLException e) {
			System.out.print(e);
			return -1;//��ѯ���󷵻�-1
		}
	}
	public  int addMoney(String user ,int money) {
		sql="UPDATE users SET userMoney = ? WHERE userName = ?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setString(2, user);
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			System.out.print(e);
			return -1;
		}
	}
	public  int addUser(String user) {
		sql="INSERT INTO users (userName) VALUES (?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			System.out.print(e);
			return -1;
		}
	}
	public void quit() {
		// TODO Auto-generated method stub
		try {
			if(con!=null) 
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.print(e);
		}
	}
	public String[][] queryAll() {//�������޵�ǰ�û�
		sql="SELECT * FROM users ";
		ResultSet rs;
		String[][]allUsers = null;
		int u=0;
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				u++;
			}
		} catch (SQLException e) {
			System.out.print(e);
		}
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			allUsers=new String[u][3];
			for(int j=0;rs.next();j++) {
				allUsers[j][0]=rs.getString("ID");
				allUsers[j][1]=rs.getString("userName");
				allUsers[j][2]=rs.getString("userMoney");
			}
		} catch (SQLException e) {
			System.out.print(e);
		}
		return allUsers;
	}
}
