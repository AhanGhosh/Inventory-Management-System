package mainPackage;

import java.sql.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InterfaceDesign {

	private JFrame frame;
	private JTextField txtPname;
	private JTextField txtQuantity;
	private JTextField txtPrice;
	private JTextField txtSearchId;
	private JTextField txtSellId;
	private JTextField txtSellQuantity;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceDesign window = new InterfaceDesign();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfaceDesign() {
		initialize();
		buildConnection();
		loadTable();
	}
	
	Connection con;
	PreparedStatement prestm;
	ResultSet rst;
	
	public void buildConnection() {
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/swingprojectdatabase", "root", "ahan1234");
			System.out.println("Done with stable connection");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadTable() {
		
		try {
			prestm=con.prepareStatement("select * from inventorytable");
			
			rst=prestm.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rst));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 584);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inventory Management System");
		lblNewLabel.setBounds(104, 11, 435, 37);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(22, 62, 233, 187);
		panel.setBorder(new TitledBorder(null, "Add Items", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Product Name ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 24, 84, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Quantity");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(10, 59, 63, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price per Item");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(10, 91, 84, 14);
		panel.add(lblNewLabel_1_1_1);
		
		txtPname = new JTextField();
		txtPname.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtPname.setBounds(97, 22, 126, 20);
		panel.add(txtPname);
		txtPname.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtQuantity.setBounds(97, 57, 126, 20);
		panel.add(txtQuantity);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtPrice.setBounds(97, 89, 126, 20);
		panel.add(txtPrice);
		
		JButton btnAddItems = new JButton("Add Items");
		btnAddItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name,quantity,price;
				name=txtPname.getText();
				quantity=txtQuantity.getText();
				price=txtPrice.getText();
				
				try {
					prestm=con.prepareStatement("insert into inventorytable(ProductName,Quantity,PricePerItem)values(?,?,?)");
					
					prestm.setString(1,name);
					prestm.setString(2,quantity);
					prestm.setString(3,price);
					
					prestm.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Item Added Successfully");
					
					loadTable();
					
					txtPname.setText("");
					txtQuantity.setText("");
					txtPrice.setText("");
					
					txtPname.requestFocus();
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnAddItems.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddItems.setBounds(10, 133, 98, 32);
		panel.add(btnAddItems);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtPname.setText("");
				txtQuantity.setText("");
				txtPrice.setText("");
				
				txtPname.requestFocus();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnClear.setBounds(125, 133, 98, 32);
		panel.add(btnClear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search Item", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(22, 271, 233, 137);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Enter Product ID");
		lblNewLabel_1_1_1_1.setBounds(10, 27, 101, 16);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_1.add(lblNewLabel_1_1_1_1);
		
		txtSearchId = new JTextField();
		txtSearchId.setColumns(10);
		txtSearchId.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtSearchId.setBounds(107, 26, 116, 20);
		panel_1.add(txtSearchId);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String searchId=txtSearchId.getText();
				
				try {
					prestm=con.prepareStatement("select * from inventorytable where ID=?");
					
					prestm.setString(1, searchId);
					
					rst=prestm.executeQuery();
					
					if(rst.next()) {
						txtPname.setText(rst.getString(2));
						txtQuantity.setText(rst.getString(3));
						txtPrice.setText(rst.getString(4));
					}else {
						txtPname.setText("");
						txtQuantity.setText("");
						txtPrice.setText("");
						
						txtPname.requestFocus();
						
					}
					
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSearch.setBounds(51, 68, 91, 32);
		panel_1.add(btnSearch);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Modify Record", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(22, 419, 233, 92);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name,quantity,price,id;
				
				name=txtPname.getText();
				quantity=txtQuantity.getText();
				price=txtPrice.getText();
				id=txtSearchId.getText();
				
				try {
					prestm = con.prepareStatement("UPDATE inventorytable SET ProductName=?, Quantity=?, PricePerItem=? WHERE ID=?");

					
					prestm.setString(1,name);
					prestm.setString(2,quantity);
					prestm.setString(3,price);
					prestm.setString(4,id);
					
					prestm.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Item Updated Successfully");
					
					loadTable();
					
					txtPname.setText("");
					txtQuantity.setText("");
					txtPrice.setText("");
					
					txtPname.requestFocus();
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdate.setBounds(10, 32, 91, 32);
		panel_2.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id=txtSearchId.getText();
				
				try {
					prestm = con.prepareStatement("delete from inventorytable WHERE ID=?");
					prestm.setString(1,id);
					
					prestm.executeUpdate();
					JOptionPane.showMessageDialog(null, "Item Deleted Successfully");
					
                    loadTable();
					
					txtPname.setText("");
					txtQuantity.setText("");
					txtPrice.setText("");
					
					txtPname.requestFocus();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDelete.setBounds(115, 32, 91, 32);
		panel_2.add(btnDelete);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExit.setBounds(549, 41, 65, 32);
		frame.getContentPane().add(btnExit);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Sell Items", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(265, 371, 349, 137);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Enter Product ID");
		lblNewLabel_1_1_1_1_1.setBounds(29, 21, 93, 16);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_4.add(lblNewLabel_1_1_1_1_1);
		
		txtSellId = new JTextField();
		txtSellId.setBounds(132, 21, 132, 16);
		txtSellId.setColumns(10);
		txtSellId.setBorder(new LineBorder(new Color(171, 173, 179)));
		panel_4.add(txtSellId);
		
		txtSellQuantity = new JTextField();
		txtSellQuantity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String sellID=txtSellId.getText();
				String sellQuantity=txtSellQuantity.getText();
				
				
				try {
					prestm=con.prepareStatement("select * from inventorytable where ID=?");
					
					prestm.setString(1, sellID);
					
					rst=prestm.executeQuery();
					
					
					
					if(rst.next()) {
						if(Integer.parseInt(sellQuantity)<=Integer.parseInt(rst.getString(3))) {
							txtPname.setText(rst.getString(2));
							txtQuantity.setText(rst.getString(3));
							txtPrice.setText(rst.getString(4));
					    }else {
					    	txtPname.setText("");
							txtQuantity.setText("");
							txtPrice.setText("");
							
							txtPname.requestFocus();
					    }
						
					}else {
						txtPname.setText("");
						txtQuantity.setText("");
						txtPrice.setText("");
						
						txtPname.requestFocus();
						
					}
					
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		txtSellQuantity.setColumns(10);
		txtSellQuantity.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtSellQuantity.setBounds(132, 48, 132, 16);
		panel_4.add(txtSellQuantity);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Quantity");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1_1_1.setBounds(29, 47, 93, 16);
		panel_4.add(lblNewLabel_1_1_1_1_1_1);
		
		JButton btnSellItem = new JButton("Sell Item");
		btnSellItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
                String name,quantity,price,id;
				
				name=txtPname.getText();
				quantity=txtQuantity.getText();
				price=txtPrice.getText();
				id=txtSellId.getText();
				String sellQuantity=txtSellQuantity.getText();
				
				Integer diffQuantity=Integer.parseInt(quantity)-Integer.parseInt(sellQuantity);
				
				
				
				try {
					prestm = con.prepareStatement("UPDATE inventorytable SET Quantity=? WHERE ID=?");

					
	
					prestm.setString(1,diffQuantity.toString());
					prestm.setString(2,id);
					
					prestm.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Item Sold Successfully");
					
					loadTable();
					
					txtPname.setText("");
					txtQuantity.setText("");
					txtPrice.setText("");
					
					txtPname.requestFocus();
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
				
			}
		});
		btnSellItem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSellItem.setBounds(101, 85, 91, 32);
		panel_4.add(btnSellItem);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(267, 72, 347, 288);
		frame.getContentPane().add(panel_3);
		panel_3.setBorder(new TitledBorder(null, "Inventory", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 327, 254);
		panel_3.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
