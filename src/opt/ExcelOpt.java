package opt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import frame.JPanel1;

public class ExcelOpt {

	public static String recordAttendance(Date date,String time1,String time2) {

		int n = 9;

		try {
			SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yy");
			SimpleDateFormat dateFormatter2 = new SimpleDateFormat("MM");
			SimpleDateFormat dateFormatter3 = new SimpleDateFormat("dd");
			//SimpleDateFormat dateFormatter4 = new SimpleDateFormat("HH");

			String year = dateFormatter1.format(date);
			String month = dateFormatter2.format(date);
			String day = dateFormatter3.format(date);

			String sheetName = year + "�N" + month + "��";

			int row = Integer.parseInt(day) + 7;

			InputStream is = new FileInputStream("D:\\�l���p�i���G�j\\�l�Ζ�\\�o�Ε�(�ŐV).xlsx");
			Workbook book = WorkbookFactory.create(is);

			Sheet sheet = book.getSheet(sheetName);

			//�o�Ύ��ԋL��
			sheet.getRow(row).getCell(2).setCellValue(time1);

			//�ދΎ��ԋL��
			sheet.getRow(row).getCell(4).setCellValue(time2);

			//���x�ݎ��ԋL��
			if( time1.compareTo("12:30") > 0){
				n = 0;
			}else{
				n = 1;
			}
			sheet.getRow(row).getCell(5).setCellValue(n);

			//�֐��̍Čv�Z
			//book.getCreationHelper().createFormulaEvaluator().evaluateAll();
			//book.getCreationHelper().createFormulaEvaluator().evaluateFormulaCellEnum(sheet.getRow(row).getCell(7));
			sheet.setForceFormulaRecalculation(true);

			//�t�@�C���ۑ�
			FileOutputStream out = new FileOutputStream(JPanel1.file);
			book.write(out);

			is.close();
			out.close();
			book.close();

		} catch (NumberFormatException | EncryptedDocumentException | InvalidFormatException | IOException e) {

			e.printStackTrace();
			return Integer.toString(n);
		}

	    return Integer.toString(n);
	}

}
