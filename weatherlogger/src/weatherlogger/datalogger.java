package weatherlogger;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;    


public class datalogger extends JFrame {

	private JPanel contentPane;
	private JTextField date;
	private JTextField temp;
	private JTextField humidity;
	private JTextField windspeed;
	private JTextField albedo;
	private JLabel msg;
	private JComboBox tempunit;
	private JComboBox winddirection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					datalogger frame = new datalogger();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void init() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("New Data");
		title.setBounds(22, 16, 61, 16);
		contentPane.add(title);
		
		JLabel datelbl = new JLabel("Date: ");
		datelbl.setBounds(22, 64, 61, 16);
		contentPane.add(datelbl);
		
		JButton datetoday = new JButton("today");
		datetoday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
				LocalDateTime now = LocalDateTime.now();  
				date.setText(dtf.format(now));
			}
		});
		datetoday.setBounds(22, 92, 117, 29);
		contentPane.add(datetoday);
		
		date = new JTextField();
		date.setText("04/05/2021");
		date.setBounds(151, 92, 271, 26);
		contentPane.add(date);
		date.setColumns(10);
		
		JLabel templbl = new JLabel("Daily Temperature:");
		templbl.setBounds(22, 145, 127, 16);
		contentPane.add(templbl);
		
		temp = new JTextField();
		temp.setText("20");
		temp.setColumns(10);
		temp.setBounds(22, 173, 271, 26);
		contentPane.add(temp);
		
		tempunit = new JComboBox();
		tempunit.setModel(new DefaultComboBoxModel(new String[] {"Celsius (°C)", "Fahrenheit (°F)"}));
		tempunit.setBounds(305, 174, 70, 27);
		contentPane.add(tempunit);
		
		JLabel humiditylbl = new JLabel("Humidity: ");
		humiditylbl.setBounds(22, 225, 127, 16);
		contentPane.add(humiditylbl);
		
		humidity = new JTextField();
		humidity.setText("20");
		humidity.setColumns(10);
		humidity.setBounds(22, 264, 271, 26);
		contentPane.add(humidity);
		
		JLabel humidityunit = new JLabel("%");
		humidityunit.setBounds(314, 269, 61, 16);
		contentPane.add(humidityunit);
		
		JLabel windspeedlbl = new JLabel("Wind Speed:");
		windspeedlbl.setBounds(24, 320, 127, 16);
		contentPane.add(windspeedlbl);
		
		windspeed = new JTextField();
		windspeed.setText("20");
		windspeed.setColumns(10);
		windspeed.setBounds(22, 364, 271, 26);
		contentPane.add(windspeed);
		
		JLabel windspeedunit = new JLabel("km/h");
		windspeedunit.setBounds(314, 369, 61, 16);
		contentPane.add(windspeedunit);
		
		JLabel albedolbl = new JLabel("Albedo:");
		albedolbl.setBounds(22, 421, 127, 16);
		contentPane.add(albedolbl);
		
		albedo = new JTextField();
		albedo.setText("20");
		albedo.setColumns(10);
		albedo.setBounds(24, 464, 271, 26);
		contentPane.add(albedo);
		
		JLabel albedounit = new JLabel("footcandles");
		albedounit.setBounds(314, 469, 89, 16);
		contentPane.add(albedounit);
		
		JLabel winddirectionlbl = new JLabel("Wind Speed:");
		winddirectionlbl.setBounds(22, 511, 127, 16);
		contentPane.add(winddirectionlbl);
		
		winddirection = new JComboBox();
		winddirection.setBounds(22, 539, 271, 27);
		contentPane.add(winddirection);
		
		JButton addbutton = new JButton("Add");
		addbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataprocess();
				setVisible(false);
				dispose();
			}
		});
		addbutton.setBounds(305, 11, 117, 29);
		contentPane.add(addbutton);
		
		msg = new JLabel("");
		msg.setBounds(22, 44, 400, 16);
		contentPane.add(msg);
		
	}

	/**
	 * Create the frame.
	 */
	public datalogger() {
		this.init();

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e){
			//in case the database doesnt exist
			e.printStackTrace();
		}
	}

	public int monthdays(int m, int y) {
		if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
            return 31;
        }
        else if (m == 4 || m == 6 || m == 9 || m == 11) {
            return 30;
        }
        else if(m == 2) {
            if(y % 4 == 0 && y % 100 != 0 || y % 400 == 0) {
                return 29;
            } else {
                return 28;
            }
        }
        else {return 0;}
 	}
	
	public int yeardays(int y) {
		if(y % 4 == 0 && y % 100 != 0 || y % 400 == 0) {
            return 366;
        } else {
            return 365;
        }
	}
	
	public int dateprocess(String date) {
		int d = 0;
        int m = 0;
        int y = 0;
        int days = 0;
        try {
            d = Integer.parseInt(date.substring(0, 2));
            m = Integer.parseInt(date.substring(3, 5));
            y = Integer.parseInt(date.substring(6, 10));
        } catch(Exception e) {
            System.out.println("false format for dates");
			this.msg.setText("falase format for dates");
            e.printStackTrace();
        }
        if(y < 1800) {
            System.out.println("this system wouldn't be able to log weather data before 1800");
			this.msg.setText("this system wouldn't be able to log weather data before 1800");
            return 0;
            //msg delivers the same info
        }
        days += d;
        for(int i = 1; i < m; ++i) {
            days += monthdays(i, y);
            System.out.println(monthdays(i, y));
        }
        for(int i = 1800; i < y; ++i) {
            days += yeardays(i);
        }
        return days;
	}

	public void dataprocess() {
		int date = dateprocess(this.date.getText());
		if(tempunit.getSelectedItem() == "Fahrenheit (°F)") {
			
		}
	}

	public Connection buildConnection() {
		// SQLite connection string
		String url = "jdbc:sqlite:C://sqlite/db/test.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			this.msg.setText(e.getMessage());
		}
		return conn;
	}

	public void insertsql(int date, double temperature, double albedo, String winddirection, double windspeed, double humidity) {
        String sql = "INSERT INTO record(date,albedo,temperature,windspeed,winddirection,humidity) VALUES(?,?,?,?,?,?)";

        try (Connection conn = this.buildConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, date);
            pstmt.setDouble(2, albedo);
            pstmt.setDouble(3, temperature);
			pstmt.setDouble(4, windspeed);
			pstmt.setString(5, winddirection);
			pstmt.setDouble(6, humidity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
			this.msg.setText(e.getMessage());
        }
    }

}
