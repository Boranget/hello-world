import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;
public class Czhi extends JFrame{
	static Border bo1= BorderFactory.createBevelBorder(1);//�߿�
	JTextField jtf2;
	JPanel jp1=new JPanel(null);
	JScrollPane jsp=null;
	public Czhi() {
		
		
		JTextField jtf1=new JTextField();//�û���¼��
		jtf2=new JTextField();//������
		
		JLabel jl1=new JLabel("�������˻���");
		JLabel jl2=new JLabel("��ѡ��������ֵ�Ľ�������");
		
		JButton jb1=new JButton("��ֵ");
		JButton jb2=new JButton("��ѯ");
		JButton jb3=new JButton("ȷ�ϳ�ֵ");
		JButton jb4=new JButton("ȷ�ϲ�ѯ");
		
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
				jl2.setText("��ѡ��������ֵ�Ľ�������");
				Czhi.this.setSize(290, 360);
				jb1.setVisible(false);
				jb4.setVisible(false);
				jtf2.setEditable(true);
				jtf2.setText("");
				Czhi.this.getRootPane().setDefaultButton(jb3);

			}
		});
		jb2.addActionListener(new ActionListener() {//�����ѯ��ť��仯����
			public void actionPerformed(ActionEvent e) {
				jb11.setVisible(false);
				jb12.setVisible(false);
				jb13.setVisible(false);
				jl2.setText("��ǰ�˻������Ϊ��");
				Czhi.this.setSize(290, 280);
				jb1.setVisible(true);
				jb4.setVisible(true);
				jtf2.setEditable(false);
				jtf2.setText("");
				Czhi.this.getRootPane().setDefaultButton(jb4);

			}
		});
		jb3.addActionListener(new ActionListener() {//���ȷ�ϳ�ֵ��ť
			public void actionPerformed(ActionEvent e) {				
				String user=jtf1.getText().trim();
				int money = 0;
				//System.out.print(user);
				if(user.length()!=0) {
					if(user.equals("1024")) {
						JOptionPane.showMessageDialog(Czhi.this, "����Ա�û��޷���ֵ");
						return;
					}
					try {
						money=Integer.valueOf(jtf2.getText());
					}
					catch(NumberFormatException e1) {
						JOptionPane.showMessageDialog(Czhi.this, "��������ȷ�ĳ�ֵ���");
						return;
					}
					int i=Service.addMoney(user, money);
					if(i==1)
						JOptionPane.showMessageDialog(Czhi.this, "��ֵ�ɹ�");
					else if(i==-2) {
						int r=JOptionPane.showConfirmDialog(Czhi.this,"�Ƿ��Դ��û����������û���","���û������ڣ�",JOptionPane.OK_OPTION);
						//System.out.print(r);+
						if(r==0) {
							int j=Service.addUser(user);
							if(j==1) {
								JOptionPane.showMessageDialog(Czhi.this, "��ӳɹ�");
								jtf2.setText("");
							}
						}
					}
					else if(i==-1)
						JOptionPane.showMessageDialog(Czhi.this, "��ֵʧ��");
					}
				else
					JOptionPane.showMessageDialog(Czhi.this, "���������û���");
			}
		});
		jb4.addActionListener(new ActionListener() {//���ȷ�ϲ�ѯ��ť
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
						JOptionPane.showMessageDialog(Czhi.this, "��ѯ�ɹ�");
						jtf2.setText(String.valueOf(money));
					}
					else if(money==-2) {
						int r=JOptionPane.showConfirmDialog(Czhi.this,"�Ƿ��Դ��û����������û���","���û������ڣ�",JOptionPane.OK_OPTION);
						//System.out.print(r);+
						if(r==0) {
							int j=Service.addUser(user);
							if(j==1) {
								JOptionPane.showMessageDialog(Czhi.this, "��ӳɹ�");
								jtf2.setText("");
								if(Czhi.this.jsp!=null)
									Czhi.this.queryAll();
							}
						}
					}
					else if(money==-1)
						JOptionPane.showMessageDialog(Czhi.this, "��ѯʧ��");
					}
				else
					JOptionPane.showMessageDialog(Czhi.this, "���������û���");
				
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
			public void windowClosing(WindowEvent e) {//���ڹر��¼�
				Service.quit();
				System.exit(0);
			}
		});
		
		
		this.add(jp1);
		
		
		
		this.setTitle("��ֵ����ѯ");
		this.setSize(290, 360);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);//���ھ���
		//this.centered(this);
		
	}
	protected void queryAll() {
		if(this.jsp!=null)
			this.jp1.remove(jsp);
		this.setSize(590, 275);
		String[] columnNames = { "ID", "userName","userMoney" }; // ��������������
		// ��������������
		String[][] tableValues = Service.queryAll();
		// ����ָ�����������ݵı��
		JTable table = new JTable(tableValues, columnNames);
		// ������ʾ���Ĺ������
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
