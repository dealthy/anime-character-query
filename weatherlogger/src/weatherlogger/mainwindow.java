package weatherlogger;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/*
import org.jfree.chart.ChartPanel;

import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
*/

public class mainwindow extends JFrame {

	private JPanel contentPane;
	private JTextField start;
	private JTextField end;
	private JLabel msg;
	private JComboBox category;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainwindow frame = new mainwindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
	
	public int dataprocess(String date) {
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
	
	public DefaultCategoryDataset graphdata() {
		int start = dataprocess(this.start.getText());
		int endraw = dataprocess(this.end.getText());
		String xaxis = this.category.getSelectedItem().toString();
		System.out.println("plotting graph");
		this.msg.setText("plotting graph");
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		dataset.addValue( 15 , "schools" , "1970" );
		dataset.addValue( 30 , "schools" , "1980" );
		dataset.addValue( 60 , "schools" ,  "1990" );
		dataset.addValue( 120 , "schools" , "2000" );
		dataset.addValue( 240 , "schools" , "2010" );
		dataset.addValue( 300 , "schools" , "2014" );
		//dataset.addValue( 300 , "assholes" , "2014" );
		return dataset;
	}
	
	public void init() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel startdatelabel = new JLabel("from: ");
		startdatelabel.setBounds(772, 58, 61, 16);
		contentPane.add(startdatelabel);
		
		JLabel enddatelabel = new JLabel("to:");
		enddatelabel.setBounds(772, 125, 61, 16);
		contentPane.add(enddatelabel);
		
		JButton logger = new JButton("log data");
		logger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				datalogger logwindow = new datalogger();
				System.out.println("opening window");
				logwindow.setVisible(true);
			}
		});
		logger.setBounds(764, 279, 130, 29);
		contentPane.add(logger);
		
		start = new JTextField();
		start.setText("04/05/2021");
		start.setBounds(772, 86, 122, 26);
		contentPane.add(start);
		start.setColumns(10);
		
		end = new JTextField();
		end.setText("04/05/2021");
		end.setColumns(10);
		end.setBounds(772, 154, 122, 26);
		contentPane.add(end);
		
		msg = new JLabel("");
		msg.setVerticalAlignment(SwingConstants.TOP);
		msg.setHorizontalAlignment(SwingConstants.LEFT);
		msg.setBounds(26, 534, 734, 32);
		contentPane.add(msg);
		
		JButton graph = new JButton("graph data");
		graph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graphdata();
			}
		});
		graph.setBounds(764, 250, 130, 29);
		contentPane.add(graph);
		
		JLabel categorylbl = new JLabel("category: ");
		categorylbl.setBounds(772, 188, 74, 16);
		contentPane.add(categorylbl);
		
		category = new JComboBox();
		category.setModel(new DefaultComboBoxModel(new String[] {"temperature", "humidity", "albedo", "wind speed and direction"}));
		category.setBounds(772, 216, 122, 27);
		contentPane.add(category);


		JFreeChart lineChart = ChartFactory.createLineChart(
			"Temperature vs Time",
			"Time","Temperature",
			graphdata(),
			PlotOrientation.VERTICAL,
			true,true,false);

		
		ChartPanel graphpanel = new ChartPanel( lineChart );
		graphpanel.setBounds(26, 25, 734, 497);
		contentPane.add(graphpanel);
		graphpanel.setVisible(true);
		
		
	}

	/**
	 * Create the frame.
	 */
	public mainwindow() {
		this.init();

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e){
			//in case the database doesnt exist
			e.printStackTrace();
		}
	}
}
