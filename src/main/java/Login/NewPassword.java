package Login;

import ConnectDB.Connect;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NewPassword extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private Connect con = new Connect();

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public NewPassword(String username) {
		setBounds(100, 100, 403, 192);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel_1 = new JLabel("Mật khẩu mới");
			lblNewLabel_1.setBounds(12, 37, 123, 15);
			contentPanel.add(lblNewLabel_1);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(168, 37, 205, 19);
			contentPanel.add(passwordField);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Xác nhận mật khẩu");
			lblNewLabel_3.setBounds(12, 78, 135, 15);
			contentPanel.add(lblNewLabel_3);
		}
		{
			passwordField_1 = new JPasswordField();
			passwordField_1.setBounds(168, 76, 205, 19);
			contentPanel.add(passwordField_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Đổi mật khẩu");
				okButton.addActionListener(e -> {
					String password = new String(passwordField.getPassword());
					String password1 = new String(passwordField_1.getPassword());
					if(password.equals(password1)){
						con.changePassword(username, password);
						JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
						dispose();

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Hủy");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
