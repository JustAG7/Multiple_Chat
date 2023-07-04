package Login;

import ConnectDB.Connect;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Register extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JPasswordField passwordField_1;

	private Connect con = new Connect();

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public Register() {
		setBounds(100, 100, 430, 294);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Tài khoản");
			lblNewLabel.setBounds(12, 33, 70, 15);
			contentPanel.add(lblNewLabel);
		}
		
		textField = new JTextField();
		textField.setBounds(168, 33, 205, 19);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Mật khẩu");
		lblNewLabel_1.setBounds(12, 74, 70, 15);
		contentPanel.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(168, 74, 205, 19);
		contentPanel.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("Câu hỏi");
		lblNewLabel_2.setBounds(11, 150, 70, 15);
		contentPanel.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(167, 147, 205, 24);
		contentPanel.add(comboBox);
		comboBox.addItem("Bạn thân nhất của bạn là ai?");
		comboBox.addItem("Môn học nào bạn thích nhất?");
		comboBox.addItem("Bạn thích ăn gì nhất?");
		comboBox.addItem("Bạn sinh ra ở thành phố nào?");

		
		JLabel lblCuTrLi = new JLabel("Câu trả lời");
		lblCuTrLi.setBounds(11, 188, 98, 15);
		contentPanel.add(lblCuTrLi);
		
		textField_1 = new JTextField();
		textField_1.setBounds(167, 188, 205, 19);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Xác nhận mật khẩu");
		lblNewLabel_3.setBounds(12, 109, 135, 15);
		contentPanel.add(lblNewLabel_3);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(168, 107, 205, 19);
		contentPanel.add(passwordField_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Đăng ký");
				okButton.addActionListener(e -> {
					if(textField.getText().equals("") || passwordField.getText().equals("") || passwordField_1.getText().equals("") || textField_1.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
					}
					else {
						if(passwordField.getText().equals(passwordField_1.getText())) {
							boolean taken = false;
							taken = con.checkExistUserName(textField.getText());
							con.registerAccount(textField.getText(), passwordField.getText(), comboBox.getSelectedItem().toString(), textField_1.getText(),taken);

							System.out.println(taken);
							if(taken == true) {
								JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại");
							}
							else {
								JOptionPane.showMessageDialog(null, "Đăng ký thành công");
								dispose();
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Mật khẩu không trùng khớp");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Hủy");
				cancelButton.addActionListener(e -> {
					dispose();
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
