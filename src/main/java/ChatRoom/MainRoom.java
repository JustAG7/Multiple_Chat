package ChatRoom;

import java.awt.*;

import ConnectDB.*;
import Login.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class MainRoom extends JFrame {
	String userName;
	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
	private JPanel contentPane;
	private Server server = new Server();
	Connect con = new Connect();
	ArrayList<Integer> list = con.getAllPorts();
	private JTextField textField;
	int currentPort;
	TextArea textArea;
	public void setSocket(Socket socket) throws Exception{
		this.socket = socket;
		this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	}
	public void sendMessage(){
		try{
			writer.write(userName);
			writer.newLine();
			writer.flush();

			while(socket.isConnected()){
				String message = textField.getText();
				writer.write(userName + ": " + message);
				writer.newLine();
				writer.flush();
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	public void listenForMessage(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				String messageFromServer;
				while(socket.isConnected()){
					try{
						messageFromServer = reader.readLine();
						textArea.append(messageFromServer + "\n");
					} catch (Exception e){
						e.printStackTrace();
					}
				}
			}
		});
	}
	public MainRoom(String username) {
		this.userName = username;
		this.listenForMessage();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200,700);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Hệ thống");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Đăng xuất");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
				SignIn signIn = new SignIn();
				signIn.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Thông tin");
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenu mnNewMenu_1 = new JMenu("ChatRoom");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Thêm phòng chat mới");
		mnNewMenu_1.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Tìm phòng chat");
		mnNewMenu_1.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Xóa phòng chat");
		mnNewMenu_1.add(mntmNewMenuItem_4);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 198, 618);
		contentPane.add(scrollPane);

		JPanel panel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		scrollPane.setViewportView(panel);
		panel.setLayout(boxLayout);

		int spacing = 10;

		for (int port : list) {
			JLabel lblNewLabel = new JLabel(String.valueOf(port));
			lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 25));
			lblNewLabel.setPreferredSize(new Dimension(195, 42));
			lblNewLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
				}

				@Override
				public void mousePressed(MouseEvent e) {
					currentPort = Integer.parseInt(lblNewLabel.getText());

				}
			});

			panel.add(lblNewLabel);
			panel.add(Box.createRigidArea(new Dimension(50, 42)));
		}





		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(238, 12, 876, 618);
		contentPane.add(scrollPane_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		scrollPane_1.setViewportView(panel_1);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 516, 873, 2);
		panel_1.add(separator);

		textField = new JTextField();
		textField.setBounds(0, 516, 755, 99);
		panel_1.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Gửi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				sendMessage();
			}
		});
		btnNewButton.setBounds(744, 516, 129, 99);
		panel_1.add(btnNewButton);

		textArea = new TextArea();
		textArea.setBounds(0, 0, 873, 510);
		panel_1.add(textArea);
	}
}