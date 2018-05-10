package opt;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;

import common.Constent;
import sun.awt.shell.ShellFolder;

public class IconExport {

	static public Icon exportIcon(String fileName,int size){

		if(fileName != null && !"".equals(fileName)){

			File file = new File(fileName);

			if(size == 1){

				Icon ic = FileSystemView.getFileSystemView().getSystemIcon(file);

				return formatIcon(ic,16,16);

			}else if(size == 2){

	            try {

	                ShellFolder sf = ShellFolder.getShellFolder( file );

	                ImageIcon ic = new ImageIcon( sf.getIcon( true ) );

	                return ic;

	            } catch ( FileNotFoundException e ) {

	                e.printStackTrace();
	            }
			}

		}

		return null;
	}


	public static Icon getExistIcon(String itemName,int size) {

		int width = 16;
		int height = 16;

		if(size == 2){
			width = 32;
			height = 32;
		}

		File f = new File(Constent.ICON_PATH);

		File[] iconList = f.listFiles();

		for(File icon : iconList){

			if(itemName.equals(icon.getName().split("\\.")[0])){

				ImageIcon ic = formatIcon(icon.getPath(),width,height);

				return ic;
			}

		}

		return null;
	}

	private static ImageIcon formatIcon(Icon icon,int width,int height) {

		ImageIcon ic = (ImageIcon) icon;

		Image im = ic.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);

		ic = new ImageIcon(im);

		return ic;
	}


	private static ImageIcon formatIcon(String fileName,int width,int height) {

		ImageIcon ic = new ImageIcon(fileName);

		Image im = ic.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);

		ic = new ImageIcon(im);

		return ic;
	}

	public static Icon getDefaultToolIcon() {

		ImageIcon ic = formatIcon(Constent.DEFAULT_ICON,16,16);

		return ic;
	}


}
