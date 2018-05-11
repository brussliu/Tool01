package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public JPanel1 jp1;
	public JPanel2 jp2;
	public JPanel3 jp3;
	public JPanel4 jp4;
	public JPanel5 jp5;

	public JButton jb1,jb2;

    public MainFrame(){

    	this.jp1 = new JPanel1(this);
    	this.jp2 = new JPanel2(this);
    	this.jp3 = new JPanel3(this);
    	this.jp4 = new JPanel4(this);
    	this.jp5 = new JPanel5(this);

    	this.add(this.jp1);
    	this.add(this.jp2);
    	this.add(this.jp3);
    	this.add(this.jp4);
    	this.add(this.jp5);

    	this.jb1 = new JButton("▲");
    	this.jb1.setFont(this.jb1.getFont().deriveFont(8.0f));
    	this.jb1.setMargin(new java.awt.Insets(0,0,0,0));
    	this.jb1.setBounds(2, 358, 15, 15);
    	this.jb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	this.jb1.setBackground(Color.BLUE);
    	this.jb1.setOpaque(false);
    	this.jb1.setBorderPainted(false);
    	this.jb1.setFocusPainted(false);

        this.jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	if("▼".equals(jb1.getText())){
            		jb1.setText("▲");
            		jb1.setBounds(2, 358, 15, 15);
            		jp1.open();
            		jp2.open();
            		jp4.open();
            		jp5.open();
            		open1();
            	}else if("▲".equals(jb1.getText())){
            		jb1.setText("▼");
            		jb1.setBounds(2, 95, 15, 15);
            		jp1.close();
            		jp2.close();
            		jp4.close();
            		jp5.close();
            		close1();
            	}
            }
        });

    	this.jb2 = new JButton("►");
    	this.jb2.setFont(this.jb1.getFont().deriveFont(15.0f));
    	this.jb2.setMargin(new java.awt.Insets(0,0,0,0));
    	this.jb2.setBounds(235, 170, 15, 15);
    	this.jb2.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	this.jb2.setBackground(Color.BLUE);
    	this.jb2.setOpaque(false);
    	this.jb2.setBorderPainted(false);
    	this.jb2.setFocusPainted(false);

        this.jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	if("►".equals(jb2.getText())){
            		jb2.setText("◄");

            		open2();
            	}else if("◄".equals(jb2.getText())){
            		jb2.setText("►");

            		close2();
            	}
            }
        });

    	this.add(this.jb1);
    	this.add(this.jb2);

    	this.setTitle("便利ツール");
    	Image img = Toolkit.getDefaultToolkit().getImage("./pic/dog.jpg");
    	this.setIconImage(img);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	this.setLayout(null);
        this.setSize(260, 400);
        this.setLocation(1000, 0);
        this.setVisible(true);
        this.setResizable(false);


    }

    protected void close1() {

    	double w = this.getSize().getWidth();
    	double h = this.getSize().getHeight();

    	double x = this.getLocation().getX();
    	double y = this.getLocation().getY();

    	this.setSize(160, 140);


    	this.setLocation((int)x + (int)w - 160, (int)y);
	}

	protected void open1() {

    	double w = this.getSize().getWidth();
    	double h = this.getSize().getHeight();

    	double x = this.getLocation().getX();
    	double y = this.getLocation().getY();

    	if("►".equals(this.jb2.getText())){
    		this.setSize(260, 400);
    		this.setLocation((int)x + (int)w - 260, (int)y);
    	}else{
    		this.setSize(760, 400);
    		this.setLocation((int)x + (int)w - 760, (int)y);
    	}



	}

    protected void close2() {

    	double w = this.getSize().getWidth();
    	double h = this.getSize().getHeight();

    	double x = this.getLocation().getX();
    	double y = this.getLocation().getY();

    	this.setSize(260, 400);

    	this.setLocation((int)x + (int)w - 260, (int)y);
	}

	protected void open2() {

    	double w = this.getSize().getWidth();
    	double h = this.getSize().getHeight();

    	double x = this.getLocation().getX();
    	double y = this.getLocation().getY();

		this.setSize(760, 400);

    	this.setLocation((int)x + (int)w - 760, (int)y);
	}

}
