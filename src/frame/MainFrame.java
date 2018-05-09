package frame;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	public JPanel1 jp1;
	public JPanel2 jp2;
	public JPanel3 jp3;
	public JPanel4 jp4;


    public MainFrame(){

    	this.jp1 = new JPanel1(this);
    	this.jp2 = new JPanel2(this);
    	this.jp3 = new JPanel3(this);
    	this.jp4 = new JPanel4(this);

    	this.add(this.jp1);
    	this.add(this.jp2);
    	this.add(this.jp3);
    	this.add(this.jp4);

    	this.setTitle("ï÷óòÉcÅ[Éã");
    	Image img = Toolkit.getDefaultToolkit().getImage("./pic/dog.jpg");
    	this.setIconImage(img);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	this.setLayout(null);
        this.setSize(245, 310);
        this.setLocation(1000, 0);
        this.setVisible(true);
        this.setResizable(false);


    }


}
