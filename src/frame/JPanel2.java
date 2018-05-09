package frame;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.commons.io.FileUtils;

public class JPanel2 extends JPanel{

	private static final long serialVersionUID = 1L;

	public JButton jb1,jb2;

	public static ArrayList<String> fileList = new ArrayList<String>();

    public JPanel2(MainFrame mf){

    	this.setBorder(BorderFactory.createTitledBorder("ファイル保存"));

    	this.jb1 = new JButton("►");
    	this.jb1.setFont(this.jb1.getFont().deriveFont(10.0f));
    	this.jb1.setMargin(new java.awt.Insets(0,0,0,0));

        this.jb1.setBounds(190, 20, 15, 68);
        this.jb1.setVisible(true);

        this.jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	int x = (int) mf.getLocation().getX();
            	int y = (int) mf.getLocation().getY();

            	mf.setLocation(x - 500, y);
            	mf.setSize(mf.getWidth() + 500, mf.getHeight());

            	jb2.setVisible(true);
            	jb1.setVisible(false);

            }
        });

    	this.jb2 = new JButton("◄");
    	this.jb2.setFont(this.jb1.getFont().deriveFont(10.0f));
    	this.jb2.setMargin(new java.awt.Insets(0,0,0,0));

        this.jb2.setBounds(190, 20, 15, 68);
        this.jb2.setVisible(false);

        this.jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	int x = (int) mf.getLocation().getX();
            	int y = (int) mf.getLocation().getY();

            	mf.setLocation(x + 500, y);
            	mf.setSize(mf.getWidth() - 500, mf.getHeight());

            	jb2.setVisible(false);
            	jb1.setVisible(true);

            }
        });

        this.setBounds(8, 110, 220, 100);

        this.add(this.jb1);
        this.add(this.jb2);
    	this.setLayout(null);

    	readFileList();

    	drag(mf);

    }

    private void readFileList() {

    	try {
			File f = new File("D:\\fileList.txt");
			fileList = (ArrayList<String>) FileUtils.readLines(f,"shift-jis");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void drag(MainFrame mf){

        new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, new DropTargetAdapter()
        {
            @Override
            public void drop(DropTargetDropEvent dtde){

                try{

                    if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)){

                        dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);

                        List<File> list =  (List<File>) (dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor));

                        File f = new File("D:\\fileList.txt");

                        for(File file : list){

                        	String fileInfo = file.getName() + "=" + file.toString();

                        	if(!fileList.contains(fileInfo)){

	                        	ArrayList<String> a = new ArrayList<String>();
	                        	a.add(fileInfo);
	                            FileUtils.writeLines(f, a, true);

	                            fileList.add(fileInfo);
                        	}
                        }

                        dtde.dropComplete(true);

                        mf.jp3.loadfileList();

                        mf.jp3.mainPanel.updateUI();




                    }else{
                        dtde.rejectDrop();
                    }
                }catch (Exception e){

                    e.printStackTrace();
                }
            }

        });
    }


}
