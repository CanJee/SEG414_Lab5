import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class UserInterface {

	private JFrame frame;
	private JTextField forwardCmValue;
	private JTextField backwardCmValue;
	private JTextField clockwiseDegValue;
	private JTextField cClockwiseDegValue;
	
	static CommandLineShell shell;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frame.setVisible(true);
					shell = new CommandLineShell("Welcome to Lab 5 Command Line Tool");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 510, 349);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Robot Functions");
		lblNewLabel.setBounds(32, 0, 434, 22);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Move Forward (cm)");
		lblNewLabel_1.setBounds(20, 46, 101, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton forwardBtn = new JButton("GO");
		forwardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String forwardCm = forwardCmValue.getText();
				try {
					shell.writeValue("foward "  + Integer.parseInt(forwardCm));
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		forwardBtn.setBounds(366, 42, 58, 23);
		frame.getContentPane().add(forwardBtn);
		
		forwardCmValue = new JTextField();
		forwardCmValue.setBounds(196, 43, 86, 20);
		frame.getContentPane().add(forwardCmValue);
		forwardCmValue.setColumns(10);
		
		JLabel lblMoveBackwardscm = new JLabel("Move Backwards (cm)");
		lblMoveBackwardscm.setBounds(20, 77, 112, 14);
		frame.getContentPane().add(lblMoveBackwardscm);
		
		backwardCmValue = new JTextField();
		backwardCmValue.setColumns(10);
		backwardCmValue.setBounds(196, 74, 86, 20);
		frame.getContentPane().add(backwardCmValue);
		
		JButton backwardBtn = new JButton("GO");
		backwardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String backwardCm = backwardCmValue.getText();
				try {
					shell.writeValue("backward "  + Integer.parseInt(backwardCm));
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		backwardBtn.setBounds(366, 73, 58, 23);
		frame.getContentPane().add(backwardBtn);
		
		JLabel lblRotateClockwisecm = new JLabel("Rotate Clockwise (deg)");
		lblRotateClockwisecm.setBounds(20, 107, 112, 14);
		frame.getContentPane().add(lblRotateClockwisecm);
		
		JLabel lblRotateCounterclockwisedeg = new JLabel("Rotate Counter-Clockwise (deg)");
		lblRotateCounterclockwisedeg.setBounds(20, 140, 154, 14);
		frame.getContentPane().add(lblRotateCounterclockwisedeg);
		
		clockwiseDegValue = new JTextField();
		clockwiseDegValue.setColumns(10);
		clockwiseDegValue.setBounds(196, 105, 86, 20);
		frame.getContentPane().add(clockwiseDegValue);
		
		cClockwiseDegValue = new JTextField();
		cClockwiseDegValue.setColumns(10);
		cClockwiseDegValue.setBounds(196, 137, 86, 20);
		frame.getContentPane().add(cClockwiseDegValue);
		
		JButton clockwiseBtn = new JButton("GO");
		clockwiseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String clockwiseDeg = clockwiseDegValue.getText();
				try {
					shell.writeValue("clockwise "  + Integer.parseInt(clockwiseDeg));
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		clockwiseBtn.setBounds(366, 103, 58, 23);
		frame.getContentPane().add(clockwiseBtn);
		
		JButton cClockwiseBtn = new JButton("GO");
		cClockwiseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cClockwiseDeg = cClockwiseDegValue.getText();
				try {
					shell.writeValue("counter_clockwise "  + Integer.parseInt(cClockwiseDeg));
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		cClockwiseBtn.setBounds(366, 136, 58, 23);
		frame.getContentPane().add(cClockwiseBtn);
		
		JButton sonarBtn = new JButton("Display Sonar Distance");
		sonarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					shell.writeValue("distance");
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		sonarBtn.setBounds(167, 183, 154, 23);
		frame.getContentPane().add(sonarBtn);
		
		JButton temperatureBtn = new JButton("Display Temperature");
		temperatureBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					shell.writeValue("temperature");
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		temperatureBtn.setBounds(167, 217, 154, 23);
		frame.getContentPane().add(temperatureBtn);
		
		JButton exitBtn = new JButton("EXIT");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					shell.quit();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		exitBtn.setBounds(167, 251, 154, 23);
		frame.getContentPane().add(exitBtn);
	}
}
