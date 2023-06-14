package Login;

import ConnectDB.Connect;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ForgetPassword extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	JComboBox comboBox;
	private Connect con = new Connect();

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public ForgetPassword() {
		setBounds(100, 100, 377, 258);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Tài khoản");
			lblNewLabel.setBounds(12, 42, 70, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(128, 42, 205, 19);
			contentPanel.add(textField);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Câu hỏi");
			lblNewLabel_2.setBounds(12, 85, 70, 15);
			contentPanel.add(lblNewLabel_2);
		}
		{
			comboBox = new JComboBox();
			comboBox.setBounds(128, 84, 205, 24);
			contentPanel.add(comboBox);
			comboBox.addItem("Bạn thân nhất của bạn là ai?");
			comboBox.addItem("Môn học nào bạn thích nhất?");
			comboBox.addItem("Bạn thích ăn gì nhất?");
			comboBox.addItem("Bạn sinh ra ở thành phố nào?");
		}
		{
			JLabel lblCuTrLi = new JLabel("Câu trả lời");
			lblCuTrLi.setBounds(12, 132, 98, 15);
			contentPanel.add(lblCuTrLi);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(128, 134, 205, 19);
			contentPanel.add(textField_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Xác nhận");
				okButton.addActionListener(e -> {
					boolean check = con.checkUser(textField.getText(),  comboBox.getSelectedItem().toString(), textField_1.getText());
					if(check){
						NewPassword newPassword = new NewPassword(textField.getText());
						newPassword.setVisible(true);
						this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Hủy");
				cancelButton.addActionListener(e -> {
					this.dispose();
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
