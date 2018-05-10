package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import org.apache.commons.io.FileUtils;

import opt.IconExport;

public class JPanel3 extends JPanel{

	private static final long serialVersionUID = 1L;

	public JButton jb1;

	public JScrollPane jsp;

	public JPanel mainPanel;

    public JPanel3(MainFrame mf){

    	this.setBorder(BorderFactory.createTitledBorder("常用ファイル"));

    	this.jb1 = new JButton("ｘｘ");
        this.jb1.setBounds(465, 15, 15, 190);
        //this.add(this.jb1);

    	loadfileList();


        this.setBounds(240, 5, 488, 275);



        //this.setVisible(false);
    	this.setLayout(null);

        this.jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	mf.jp2.jb1.setVisible(true);

            	int x = (int) mf.getLocation().getX();
            	int y = (int) mf.getLocation().getY();

            	mf.setLocation(x + 500, y);
            	mf.setSize(mf.getWidth() - 500, mf.getHeight());

            }
        });

    }

	public void loadfileList() {

    	this.mainPanel = new JPanel();
        SpringLayout layout = new SpringLayout();
        this.mainPanel.setLayout(layout);

        if(this.getComponentCount() > 0){
        	this.remove(this.jsp);
        }


    	this.jsp = new JScrollPane(mainPanel);

    	JPanel p1 = new JPanel();
    	JPanel p2 = new JPanel();

    	for(int i = 0;i < JPanel2.fileList.size();i ++){

    		File f = new File(JPanel2.fileList.get(i).split("=")[1]);

    		String fn = JPanel2.fileList.get(i).split("=")[0];
    		String fp = JPanel2.fileList.get(i).split("=")[1];

    		String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(f.lastModified());

    		p2 = p1;

    		p1 = makeP(fn,fp,time);

    		if(i > 0){
    			layout.putConstraint(SpringLayout.NORTH, p1, 1, SpringLayout.SOUTH,p2);//前のタスクの1px下に配置
    		}

    		this.mainPanel.setPreferredSize(new Dimension(453,41*(i+1)));//Task数に合わせてmainPanelのサイズを変更する
    		this.mainPanel.add(p1);

    	}
    	this.jsp.setBorder(null);
        this.jsp.setBounds(8, 15, 473, 250);

        this.add(this.jsp);
	}

	private JPanel makeP(String fn, String fp, String time) {

		JPanel p = new JPanel();
	    p.setPreferredSize(new Dimension(453,40));

	    SpringLayout layout = new SpringLayout();
	    p.setLayout(layout);

	    Icon icon = IconExport.exportIcon(fp,2);
	    JLabel ck = new JLabel(icon,JLabel.CENTER);

	    JLabel fileNameLabel = new JLabel(fn);
	    fileNameLabel.setForeground(Color.BLUE);
	    fileNameLabel.setFont(fileNameLabel.getFont().deriveFont(20.0f));

	    layout.putConstraint(SpringLayout.NORTH, ck, 5, SpringLayout.NORTH, p);
	    layout.putConstraint(SpringLayout.WEST, ck, 5, SpringLayout.WEST, p);
	    layout.putConstraint(SpringLayout.NORTH, fileNameLabel, -5, SpringLayout.NORTH, ck);
	    layout.putConstraint(SpringLayout.WEST, fileNameLabel, 10, SpringLayout.EAST, ck);

	    p.add(ck);
	    p.add(fileNameLabel);

	    JLabel dateLabel = new JLabel(time);
	    dateLabel.setFont(dateLabel.getFont().deriveFont(10.0f));
	    layout.putConstraint(SpringLayout.NORTH, dateLabel, 20, SpringLayout.NORTH, ck);
	    layout.putConstraint(SpringLayout.WEST, dateLabel, 10, SpringLayout.EAST, ck);
	    p.add(dateLabel);

	    JLabel pathLabel = new JLabel(fp);
	    pathLabel.setForeground(Color.gray);

	    pathLabel.setFont(pathLabel.getFont().deriveFont(10.0f));
	    layout.putConstraint(SpringLayout.NORTH, pathLabel, 20, SpringLayout.NORTH, ck);
	    layout.putConstraint(SpringLayout.WEST, pathLabel, 120, SpringLayout.EAST, ck);
	    p.add(pathLabel);

	    JButton button1 = new JButton("開く");
	    button1.setPreferredSize(new Dimension(40, 20));
	    button1.setFont(button1.getFont().deriveFont(10.0f));
	    button1.setMargin(new java.awt.Insets(0,0,0,0));
	    layout.putConstraint(SpringLayout.NORTH, button1, 0, SpringLayout.NORTH, ck);
	    layout.putConstraint(SpringLayout.EAST, button1, -90, SpringLayout.EAST, p);
	    p.add(button1);

	    button1.addActionListener(
	    	new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {

					Runtime rn = Runtime.getRuntime();

					String command = "explorer.exe " + fp;

	                try {
						rn.exec(command);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
	    	}
	    );

	    JButton button2 = new JButton("削除");
	    button2.setPreferredSize(new Dimension(40, 20));
	    button2.setFont(button1.getFont().deriveFont(10.0f));
	    button2.setMargin(new java.awt.Insets(0,0,0,0));
	    layout.putConstraint(SpringLayout.NORTH, button2, 0, SpringLayout.NORTH, ck);
	    layout.putConstraint(SpringLayout.EAST, button2, -45, SpringLayout.EAST, p);
	    p.add(button2);

	    button2.addActionListener(
		    	new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {

						try {

							JPanel2.fileList.remove(fn + "=" + fp);

							File f = new File("D:\\fileList.txt");
							FileUtils.writeLines(f, JPanel2.fileList, false);

	                        loadfileList();

	                        jsp.invalidate();
	                        jsp.validate();

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}


					}
		    	}
		    );

	    JButton button3 = new JButton("命名");
	    button3.setPreferredSize(new Dimension(40, 20));
	    button3.setFont(button1.getFont().deriveFont(10.0f));
	    button3.setMargin(new java.awt.Insets(0,0,0,0));
	    layout.putConstraint(SpringLayout.NORTH, button3, 0, SpringLayout.NORTH, ck);
	    layout.putConstraint(SpringLayout.EAST, button3, 0, SpringLayout.EAST, p);
	    p.add(button3);

	    button3.addActionListener(
		    	new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {

						String input = JOptionPane.showInputDialog(null,"新しい名称を入力してください：\n","新しい名称",JOptionPane.PLAIN_MESSAGE);

						if(input != null && !"".equals(input) ){
							try {
								int index = JPanel2.fileList.indexOf(fn + "=" + fp);

								JPanel2.fileList.remove(fn + "=" + fp);

								JPanel2.fileList.add(index, input + "=" + fp);
								//JPanel2.fileList.add(input + "=" + fp);

								File f = new File("D:\\fileList.txt");
								FileUtils.writeLines(f, JPanel2.fileList, false);

		                        loadfileList();

		                        jsp.invalidate();
		                        jsp.validate();

							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}
		    	}
		    );

		return p;
	}




}
