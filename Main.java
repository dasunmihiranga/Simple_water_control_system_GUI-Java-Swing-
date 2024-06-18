import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

class WaterLevelObserver extends JPanel{
	public void update(int waterLevel){
		////Overridden by subClass-> Alarm,Display,SMSsender,Splitter
	}
}
class Alarm extends WaterLevelObserver{
	private JLabel lblAlarm;

    Alarm() {
        lblAlarm = new JLabel("  ");
        lblAlarm.setFont(new Font("", Font.BOLD, 25));
        add(lblAlarm);
    }
	public void update(int waterLevel){
		lblAlarm.setText(waterLevel >= 50 ? "ON" : "OFF");
	}
}
class Display extends WaterLevelObserver{
	private JLabel lblDisplay;

    Display(){
        lblDisplay = new JLabel("  ");
        lblDisplay.setFont(new Font("", Font.BOLD, 25));
        add(lblDisplay);
    }
	public void update(int waterLevel){
		lblDisplay.setText(String.valueOf(waterLevel));
	}
}
class SMSsender extends WaterLevelObserver{
	private JLabel lblSms;

    SMSsender(){
        lblSms = new JLabel("  ");
        lblSms.setFont(new Font("", Font.BOLD, 25));
        add(lblSms);
    }
	public void update(int waterLevel){
		lblSms.setText(waterLevel>=75?"Sended":"Not Sended");
	}
}
class Splitter extends WaterLevelObserver{
	 private JLabel lblSplitter;

    Splitter() {
        lblSplitter = new JLabel("  ");
        lblSplitter.setFont(new Font("", Font.BOLD, 25));
        add(lblSplitter);
    }
	public void update(int waterLevel){
		lblSplitter.setText(waterLevel >= 75 ? "ON" : "OFF");
	}
}
class WaterTank extends JFrame{
	private JSlider waterLevelSlider;
	private WaterLevelObservable waterLevelObservable;
	
	WaterTank(WaterLevelObservable waterLevelObservable){
		this.waterLevelObservable=waterLevelObservable;
		setSize(300,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		waterLevelSlider=new JSlider(JSlider.VERTICAL,0,100,50);
		waterLevelSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent evt){
				waterLevelObservable.setWaterLevel(waterLevelSlider.getValue());
			}
		});
		add(waterLevelSlider);
		

	}
	
}
 
class WaterLevelObservable {
	private ArrayList<WaterLevelObserver> observerList=new ArrayList<>();
	
	private int waterLevel;
	
	public void addWaterLevelObserver(WaterLevelObserver ob){
		observerList.add(ob);
		
	}
	public void setWaterLevel(int waterLevel){
		if(this.waterLevel!=waterLevel){
			this.waterLevel=waterLevel;
			notifyDevices();
		}
	}
	public void notifyDevices(){
		for(WaterLevelObserver ob : observerList){
			ob.update(waterLevel);
		}
		
		
	}

}
class Main extends JFrame{
	private WaterLevelObservable waterLevelObservable;
	private JTabbedPane tabbedPane;
	private JSlider waterLevelSlider;
	private JLabel lbltitle;
	Main(){
		waterLevelObservable=new WaterLevelObservable();
		setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        lbltitle = new JLabel("Water Level Controlling System");
        lbltitle.setFont(new Font("", Font.BOLD, 30));
        lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbltitle.setVerticalAlignment(SwingConstants.CENTER);
        lbltitle.setOpaque(true);
        lbltitle.setBackground(new Color(122, 79, 79));
        lbltitle.setForeground(Color.white);
        lbltitle.setBounds(0,0,600,50);
        
        Alarm alarm=new Alarm();
        Display display=new Display();
        SMSsender smssender=new SMSsender();
        Splitter splitter=new Splitter();
        waterLevelObservable.addWaterLevelObserver(alarm);
		waterLevelObservable.addWaterLevelObserver(display);
		waterLevelObservable.addWaterLevelObserver(smssender);
		waterLevelObservable.addWaterLevelObserver(splitter);
		
		
		JLabel lblalarm=new JLabel("Alarm Status   	:");
		lblalarm.setFont(new Font("", Font.BOLD, 25));
		lblalarm.setBounds(50,60,200,30);
		alarm.setBounds(260,60,50,40);
		
		JLabel lbldisplay=new JLabel("Water Level    	:");
		lbldisplay.setFont(new Font("", Font.BOLD, 25));
		lbldisplay.setBounds(50,140,200,30);
		display.setBounds(260,140,50,40);
		
		
		JLabel lblsms=new JLabel("SMS Status     	:");
		lblsms.setFont(new Font("", Font.BOLD, 25));
		lblsms.setBounds(50,210,200,30);
		smssender.setBounds(260,210,150,40);
		
		JLabel lblsplitter=new JLabel("Splitter Status :");
		lblsplitter.setFont(new Font("", Font.BOLD, 25));
		lblsplitter.setBounds(50,280,200,30);
		splitter.setBounds(260,280,50,40);
		
		
		add(lbltitle);
		add(lblalarm);
		add(alarm);
		add(lbldisplay);
		add(display);
		add(lblsms);
		add(smssender);
		add(lblsplitter);
		add(splitter);
		waterLevelSlider=new JSlider(JSlider.VERTICAL,0,100,50);
		waterLevelSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent evt){
				waterLevelObservable.setWaterLevel(waterLevelSlider.getValue());
			}
		});
		JLabel lblslider=new JLabel("Water Controller");
		lblslider.setFont(new Font("", Font.BOLD, 20));
		lblslider.setBounds(420,60,200,30);
		waterLevelSlider.setBounds(450,100,100,200);
		add(lblslider);
		add(waterLevelSlider);
	}
	public static void main(String args[]){
		
		
		
		new Main().setVisible(true);
		
		
	}
}
