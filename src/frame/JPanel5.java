package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import opt.OptListener;

public class JPanel5 extends JPanel{

	private static final long serialVersionUID = 1L;

	public JButton jb1,jb2;

	public JRadioButton jrb1,jrb2,jrb3;

	public JLabel jl1,jl2,jl3,jl4,jl5;

    public JPanel5(MainFrame mf){

    	this.setBorder(BorderFactory.createTitledBorder("ホットキー設定"));

    	this.jb1 = new JButton("起動");
    	this.jb1.setMargin(new java.awt.Insets(0,0,0,0));
        this.jb1.setBounds(175, 20, 35, 60);
        this.jb1.setVisible(true);
        this.add(this.jb1);

    	this.jb2 = new JButton("停止");
    	this.jb2.setMargin(new java.awt.Insets(0,0,0,0));
        this.jb2.setBounds(175, 20, 35, 60);
        this.jb2.setVisible(false);
        this.add(this.jb2);

        this.jl1 = new JLabel("Ctrl+C⇒");
        this.jl1.setBounds(15, 20, 60, 30);
        this.add(this.jl1);

        this.jrb1 = new JRadioButton();
        this.jrb1.setSelected(true);
        this.jrb1.setBounds(70, 20, 20, 30);
        this.add(this.jrb1);

        this.jl3 = new JLabel("Alt");
        this.jl3.setBounds(90, 20, 60, 30);
        this.add(this.jl3);

        this.jrb2 = new JRadioButton();
        this.jrb2.setSelected(false);
        this.jrb2.setBounds(110, 20, 20, 30);
        this.add(this.jrb2);

        this.jl4 = new JLabel("マウス");
        this.jl4.setBounds(130, 20, 60, 30);
        this.add(this.jl4);

        this.jl2 = new JLabel("Ctrl+V⇒");
        this.jl2.setBounds(15, 45, 60, 30);
        this.add(this.jl2);

        this.jrb3 = new JRadioButton();
        this.jrb3.setSelected(true);
        this.jrb3.setBounds(70, 45, 20, 30);
        this.add(this.jrb3);

        this.jl5 = new JLabel("無変換");
        this.jl5.setBounds(90, 45, 60, 30);
        this.add(this.jl5);

    	OptListener ol = new OptListener();


        this.setBounds(18, 285, 220, 85);

    	this.setLayout(null);

        this.jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	if(!jrb1.isSelected() && !jrb2.isSelected() && !jrb3.isSelected()){

            		return;

            	}
            	ol.copy1 = jrb1.isSelected();
            	ol.copy2 = jrb2.isSelected();
            	ol.paste1 = jrb3.isSelected();

            	jb1.setVisible(false);
            	jb2.setVisible(true);

            	jrb1.setEnabled(false);
            	jrb2.setEnabled(false);
            	jrb3.setEnabled(false);

            	ol.startHotKey();


            }
        });
        this.jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	jb1.setVisible(true);
            	jb2.setVisible(false);

            	jrb1.setEnabled(true);
            	jrb2.setEnabled(true);
            	jrb3.setEnabled(true);

            	ol.stopHotKey();
            }
        });

        this.jrb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//            	if(!jrb1.isSelected()){
//
//            		jrb1.setSelected(true);;
//            	}

            	if(jrb1.isSelected()){

            		jrb1.setSelected(true);
            		jrb2.setSelected(false);
            	}

            }
        });

        this.jrb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//            	if(!jrb2.isSelected()){
//
//            		jrb2.setSelected(true);;
//            	}
            	if(jrb2.isSelected()){

            		jrb1.setSelected(false);
            		jrb2.setSelected(true);
            	}
            }
        });

        this.jrb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//            	if(!jrb3.isSelected()){
//
//            		jrb3.setSelected(true);;
//            	}

            }
        });
    }


    public void close(){
    	this.setBounds(18, 285 - 205, 220 - 100, 85 - 65);
    }

    public void open(){
    	this.setBounds(18, 285, 220, 85);
    }
}

