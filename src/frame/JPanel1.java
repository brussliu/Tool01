package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import opt.ExcelOpt;

public class JPanel1 extends JPanel{

	private static final long serialVersionUID = 1L;

	public JButton jb1, jb2;
    public JComboBox<String> jcb1,jcb2,jcb3;

    public static String file = "D:\\個人利用（劉季）\\個人勤務\\出勤報告(最新).xlsx";

    public JPanel1(MainFrame mf){

    	this.setBorder(BorderFactory.createTitledBorder("勤務記録"));

        this.jb1 = new JButton("記載");
        this.jb2 = new JButton("勤怠表確認");

        this.jcb1 = new JComboBox<String>();
        this.jcb1.addItem("09:00"); this.jcb1.setSelectedItem("09:00");
        this.jcb1.addItem("09:30");
        this.jcb1.addItem("10:00");
        this.jcb1.addItem("10:30");
        this.jcb1.addItem("11:00");
        this.jcb1.addItem("11:30");
        this.jcb1.addItem("12:00");
        this.jcb1.addItem("12:30");
        this.jcb1.addItem("13:30");
        this.jcb1.addItem("14:00");

        this.jcb2 = new JComboBox<String>();
        this.jcb2.addItem("17:30");
        this.jcb2.addItem("18:00");
        this.jcb2.addItem("18:30");
        this.jcb2.addItem("19:00");
        this.jcb2.addItem("19:30");
        this.jcb2.addItem("20:00");
        this.jcb2.addItem("20:30");
        this.jcb2.addItem("21:00");

        this.jcb3 = new JComboBox<String>();
        this.jcb3.addItem("先週金曜");
        this.jcb3.addItem("昨日");
        this.jcb3.addItem("本日");this.jcb3.setSelectedItem("本日");
        this.jcb3.addItem("明日");


        this.setBounds(18, 5, 220, 100);

        this.jcb1.setBounds(15, 20, 60, 30);
        this.jcb2.setBounds(80, 20, 60, 30);
        this.jcb3.setBounds(145, 20, 60, 30);

        this.jb1.setBounds(15, 55, 85, 30);
        this.jb2.setBounds(105, 55, 100, 30);

        this.jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	String time1 = jcb1.getSelectedItem().toString();
            	String time2 = jcb2.getSelectedItem().toString();
            	String day = jcb3.getSelectedItem().toString();

				String n = ExcelOpt.recordAttendance(day, time1, time2);

				if("9".equals(n)){
					JOptionPane.showMessageDialog(null, "出退勤時間記載時に、異常が発生しました。","異常発生",JOptionPane.ERROR_MESSAGE);
				}else{
					//下記の出退勤時間を記載完了しました。
					//出勤時間：09:00　退勤時間：17:30　昼休み時間：1
					JLabel jl = new JLabel("<html>下記の出退勤時間を記載完了しました。<br>出勤時間：" + time1 +"　退勤時間：" + time2 + "　昼休み時間：" + n + "</html>");
					JOptionPane.showMessageDialog(null, jl,"記載完了",JOptionPane.INFORMATION_MESSAGE);
				}

            }
        });

        this.jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	Runtime rn = Runtime.getRuntime();
                String command = "explorer.exe " + file;
                try {
					rn.exec(command);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

            }
        });

    	this.add(this.jcb1);
    	this.add(this.jcb2);
    	this.add(this.jcb3);

    	this.add(this.jb1);
    	this.add(this.jb2);

    	this.setLayout(null);

    }

    public void close(){
    	this.setBounds(18, 5, 220 - 100, 100 - 80);
    }
    public void open(){
    	this.setBounds(18, 5, 220, 100);
    }
}
