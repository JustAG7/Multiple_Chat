package Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import ConnectDB.*;

public class SignIn extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;
	private Register register;
	private Connect con = new Connect();

	/**
	 * Launch the application.
	 */
	public static void main
	(String[] args) {
		try {
			SignIn dialog = new SignIn();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SignIn() {
		setBounds(100, 100, 384, 236);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Tài khoản");
			lblNewLabel.setBounds(57, 39, 70, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Mật khẩu");
			lblNewLabel_1.setBounds(57, 86, 70, 15);
			contentPanel.add(lblNewLabel_1);
		}
		
		textField = new JTextField();
		textField.setBounds(145, 37, 195, 19);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(145, 84, 195, 19);
		contentPanel.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("Quên mật khẩu");
		lblNewLabel_2.setBounds(196, 131, 145, 15);
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(lblNewLabel_2.getFont().deriveFont(Font.BOLD, 16));
		lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	ForgetPassword forgetPassword = new ForgetPassword();
                forgetPassword.setVisible(true);
            }
        });
		contentPanel.add(lblNewLabel_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Đăng nhập");
				okButton.addActionListener(e -> {
					String username = textField.getText();
					String password = String.valueOf(passwordField.getPassword());
					if (con.checkLogin(username, password)) {
						dispose();

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			JButton btnngK = new JButton("Đăng ký");
			btnngK.addActionListener(e -> {
				register = new Register();
				register.setVisible(true);
			});
			buttonPane.add(btnngK);
			{
				JButton cancelButton = new JButton("Thoát");
				cancelButton.addActionListener(e -> {
					System.exit(0);
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
