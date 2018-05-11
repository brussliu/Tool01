package frame;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import org.apache.commons.io.FileUtils;

import opt.IconExport;
import opt.WindowsShortcut;

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

        loadToolList();

        this.setBounds(18, 215, 220, 65);

    	this.setLayout(null);

        this.jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	Object[] selectItem = (Object[]) jcb1.getSelectedItem();

            	String toolsName = selectItem[0].toString().substring(3);

            	for(String tool : tList){

            		String itemName = tool.split("=")[0];
            		String itemValue = tool.split("=")[1];

            		if(itemName.equals(toolsName)){

            			Runtime rn = Runtime.getRuntime();

            			File f = new File(itemValue);
            			String command = "";
            			if(f.exists()){
            				command = "explorer.exe " + itemValue;
            			}else{
            				command = itemValue;
            			}

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

	public void loadToolList() {

		try {
			tList = FileUtils.readLines(new File("D:\\toolList.txt"), "shift-jis");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        if(this.getComponentCount() > 1){
        	this.remove(this.jcb1);
        }

        Object elements[][] = new Object[tList.size()][2];

        for(int i = 0;i < tList.size();i ++){

        	String tool = tList.get(i);

        	String no = String.format("%02d", i+1);
        	String itemName = tool.split("=")[0];
        	String fileName = tool.split("=")[1];

        	String txt = no + "." + itemName;

        	Icon ic = IconExport.getExistIcon(itemName,1);

        	if(ic == null){
	        	String exeFileName = getExefileName(fileName);
	        	ic = IconExport.exportIcon(exeFileName,1);
        	}
        	if(ic == null){
        		ic = IconExport.getDefaultToolIcon();
        	}

        	elements[i][0] = txt;
			elements[i][1] = ic;
        	//this.jcb1.addItem(no + "." + itemName);

        }


        ComboBoxRenderer renderer = new ComboBoxRenderer();
        renderer.setPreferredSize(new Dimension(100, 30));

        this.jcb1 = new JComboBox(elements);
        this.jcb1.setRenderer(renderer);

        this.jcb1.setBounds(15, 20, 150, 30);
        this.add(this.jcb1);


	}

	private String getExefileName(String fileName) {

		String returnValue = null;

		if(fileName.endsWith(".exe")){
			returnValue = fileName;
		}else if(fileName.endsWith(".lnk")){

			File lnk = new File(fileName);

			try {
				WindowsShortcut ws = new WindowsShortcut(lnk);
				returnValue = ws.getRealFilename();

			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



		}else if(fileName.indexOf(".exe -") > 0){

			int p = fileName.indexOf(".exe -");

			returnValue = fileName.substring(0, p+4);
		}



		return returnValue;
	}



	private class ComboBoxRenderer extends JLabel implements ListCellRenderer<Object> {

		private static final long serialVersionUID = 1L;

		@Override
	    public Component getListCellRendererComponent(JList<?> list, Object value,
	            int index, boolean isSelected, boolean cellHasFocus) {

			ImageIcon image = null;

			String text = "";

			if (value instanceof Object[]) {
				Object[] values = (Object[]) value;
				text = (String) values[0];
				image = (ImageIcon)values[1];

			}

			if (image != null) {
				this.setIcon(image);
			}else{
				this.setIcon(new ImageIcon(this.getClass().getResource("dog.jpg")));
			}

			this.setText(text);
			return this;

		}
	}

    public void close(){

    	this.setBounds(18, 215 - 160, 220 - 100, 65 - 45);
    }

    public void open(){

    	this.setBounds(18, 215, 220, 65);
    }
}

