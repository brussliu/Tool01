package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.apache.commons.io.FileUtils;

public class JPanel4 extends JPanel{

	private static final long serialVersionUID = 1L;

	public JButton jb1;

	public JComboBox<String> jcb1;

	public List<String> tList = new ArrayList<String>();

    public JPanel4(MainFrame mf){

    	this.setBorder(BorderFactory.createTitledBorder("常用ツール"));

    	this.jb1 = new JButton("起動");
    	this.jb1.setMargin(new java.awt.Insets(0,0,0,0));

        this.jb1.setBounds(170, 20, 40, 30);

        this.add(this.jb1);

        try {
			tList = FileUtils.readLines(new File("D:\\toolList.txt"), "shift-jis");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        this.jcb1 = new JComboBox<String>();

        for(String tool : tList){

        	String itemName = tool.split("=")[0];

        	this.jcb1.addItem(itemName);

        }
        this.jcb1.setBounds(15, 20, 150, 30);
        this.add(this.jcb1);

        this.setBounds(8, 215, 220, 65);

    	this.setLayout(null);

        this.jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	String toolsName = (String) jcb1.getSelectedItem();

            	for(String tool : tList){

            		String itemName = tool.split("=")[0];
            		String itemValue = tool.split("=")[1];

            		if(itemName.equals(toolsName)){

            			Runtime rn = Runtime.getRuntime();

    					String command = itemValue;

    	                try {
    						rn.exec(command);
    					} catch (IOException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
            		}

            	}

            }
        });

    }




}
