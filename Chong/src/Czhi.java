import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;
public class Czhi extends JFrame{
	static Border bo1= BorderFactory.createBevelBorder(1);//边框
	JTextField jtf2;
	JPanel jp1=new JPanel(null);
	JScrollPane jsp=null;
	public Czhi() {
		
		
		JTextField jtf1=new JTextField();//用户名录入
		jtf2=new JTextField();//输入金额
		
		JLabel jl1=new JLabel("请输入账户名");
		JLabel jl2=new JLabel("请选择或输入充值的金额（整数金额）");
		
		JButton jb1=new JButton("充值");
		JButton jb2=new JButton("查询");
		JButton jb3=new JButton("确认充值");
		JButton jb4=new JButton("确认查询");
		
		JButton jb11=new JButton("50");
		jb11.addActionListener(new SetNumber());
		JButton jb12=new JButton("100");
		jb12.addActionListener(new SetNumber());
		JButton jb13=new JButton("300");
		jb13.addActionListener(new SetNumber());
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jb11.setVisible(true);
				jb12.setVisible(true);
				jb13.setVisible(true);
				jl2.setText("请选择或输入充值的金额（整数金额）");
				Czhi.this.setSize(290, 360);
				jb1.setVisible(false);
				jb4.setVisible(false);
				jtf2.setEditable(true);
				jtf2.setText("");
				Czhi.this.getRootPane().setDefaultButton(jb3);

			}
		});
		jb2.addActionListener(new ActionListener() {//点击查询按钮后变化布局
			public void actionPerformed(ActionEvent e) {
				jb11.setVisible(false);
				jb12.setVisible(false);
				jb13.setVisible(false);
				jl2.setText("当前账户的余额为：");
				Czhi.this.setSize(290, 280);
				jb1.setVisible(true);
				jb4.setVisible(true);
				jtf2.setEditable(false);
				jtf2.setText("");
				Czhi.this.getRootPane().setDefaultButton(jb4);

			}
		});
		jb3.addActionListener(new ActionListener() {//点击确认充值按钮
			public void actionPerformed(ActionEvent e) {				
				String user=jtf1.getText().trim();
				int money = 0;
				//System.out.print(user);
				if(user.length()!=0) {
					if(user.equals("1024")) {
						JOptionPane.showMessageDialog(Czhi.this, "管理员用户无法充值");
						return;
					}
					try {
						money=Integer.valueOf(jtf2.getText());
					}
					catch(NumberFormatException e1) {
						JOptionPane.showMessageDialog(Czhi.this, "请输入正确的充值金额");
						return;
					}
					int i=Service.addMoney(user, money);
					if(i==1)
						JOptionPane.showMessageDialog(Czhi.this, "充值成功");
					else if(i==-2) {
						int r=JOptionPane.showConfirmDialog(Czhi.this,"是否以此用户名创建新用户？","该用户不存在！",JOptionPane.OK_OPTION);
						//System.out.print(r);+
						if(r==0) {
							int j=Service.addUser(user);
							if(j==1) {
								JOptionPane.showMessageDialog(Czhi.this, "添加成功");
								jtf2.setText("");
							}
						}
					}
					else if(i==-1)
						JOptionPane.showMessageDialog(Czhi.this, "充值失败");
					}
				else
					JOptionPane.showMessageDialog(Czhi.this, "请先输入用户名");
			}
		});
		jb4.addActionListener(new ActionListener() {//点击确认查询按钮
			public void actionPerformed(ActionEvent e) {
				String user=jtf1.getText().trim();
				int money = 0;
				if(user.length()!=0) {
					if(user.equals("1024")) {
						queryAll();
						jtf2.setText("");
						return;
					}
					money=Service.queryMoney(user);
					if(money>=0) {
						JOptionPane.showMessageDialog(Czhi.this, "查询成功");
						jtf2.setText(String.valueOf(money));
					}
					else if(money==-2) {
						int r=JOptionPane.showConfirmDialog(Czhi.this,"是否以此用户名创建新用户？","该用户不存在！",JOptionPane.OK_OPTION);
						//System.out.print(r);+
						if(r==0) {
							int j=Service.addUser(user);
							if(j==1) {
								JOptionPane.showMessageDialog(Czhi.this, "添加成功");
								jtf2.setText("");
								if(Czhi.this.jsp!=null)
									Czhi.this.queryAll();
							}
						}
					}
					else if(money==-1)
						JOptionPane.showMessageDialog(Czhi.this, "查询失败");
					}
				else
					JOptionPane.showMessageDialog(Czhi.this, "请先输入用户名");
				
			}
		});

		
		
		jl1.setBounds(18,10,250,30);
		jtf1.setBounds(18,50,250,30);
		jl2.setBounds(18,90,250,30);
		jtf2.setBounds(18,130,250,30);
		jb11.setBounds(18,180,60,30);
		jb12.setBounds(113,180,60,30);
		jb13.setBounds(208,180,60,30);
		jb3.setBounds(18, 250, 120, 30);
		jb2.setBounds(150, 250, 120, 30);
		jb4.setBounds(18, 180, 120, 30);
		jb1.setBounds(150, 180, 120, 30);
		jb1.setVisible(false);
		jb4.setVisible(false);

		
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jtf1);
		jp1.add(jtf2);
		jp1.add(jb11);
		jp1.add(jb12);
		jp1.add(jb13);
		jp1.add(jb3);
		jp1.add(jb2);
		jp1.add(jb1);
		jp1.add(jb4);
		
		
		this.getRootPane().setDefaultButton(jb3);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {//窗口关闭事件
				Service.quit();
				System.exit(0);
			}
		});
		
		
		this.add(jp1);
		
		
		
		this.setTitle("充值及查询");
		this.setSize(290, 360);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);//窗口居中
		//this.centered(this);
		
	}
	protected void queryAll() {
		if(this.jsp!=null)
			this.jp1.remove(jsp);
		this.setSize(590, 275);
		String[] columnNames = { "ID", "userName","userMoney" }; // 定义表格列名数组
		// 定义表格数据数组
		String[][] tableValues = Service.queryAll();
		// 创建指定列名和数据的表格
		JTable table = new JTable(tableValues, columnNames);
		// 创建显示表格的滚动面板
		jsp = new JScrollPane(table);
		table.setEnabled(false);
		jsp.setBounds(285,2,300,242);
		this.jp1.add(jsp);

	}
	public static void main(String args[]) {
		Czhi c=new Czhi();
		Service.init();
	}
	
	class SetNumber implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton jb=(JButton)e.getSource();
			String num=jb.getText();
			jtf2.setText(num);
		}

	}
	/*
	public static void centered(JFrame frame){
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		frame.setLocation((width-frame.getSize().width)/2, (height-frame.getSize().height)/2);
	}*/
	
}
