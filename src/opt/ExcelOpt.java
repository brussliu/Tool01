package opt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import frame.JPanel1;

public class ExcelOpt {

	public static String recordAttendance(String day,String time1,String time2) {

		int n = 9;

		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		if("本日".equals(day)){

		}else if("昨日".equals(day)){
			calendar.add(Calendar.DATE, -1);
			date = calendar.getTime();
		}else if("明日".equals(day)){
			calendar.add(Calendar.DATE, 1);
			date = calendar.getTime();
		}else if("先週金曜".equals(day)){
			int w = calendar.get(Calendar.DAY_OF_WEEK) + 1;
			calendar.add(Calendar.DATE, - w );
			date = calendar.getTime();
		}


		try {
			SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yy");
			SimpleDateFormat dateFormatter2 = new SimpleDateFormat("MM");
			SimpleDateFormat dateFormatter3 = new SimpleDateFormat("dd");
			//SimpleDateFormat dateFormatter4 = new SimpleDateFormat("HH");

			String y = dateFormatter1.format(date);
			String m = dateFormatter2.format(date);
			String d = dateFormatter3.format(date);

			String sheetName = y + "年" + m + "月";

			int row = Integer.parseInt(d) + 7;

			InputStream is = new FileInputStream("D:\\個人利用（劉季）\\個人勤務\\出勤報告(最新).xlsx");
			Workbook book = WorkbookFactory.create(is);

			Sheet sheet = book.getSheet(sheetName);

			//出勤時間記入
			sheet.getRow(row).getCell(2).setCellValue(time1);

			//退勤時間記入
			sheet.getRow(row).getCell(4).setCellValue(time2);

			//昼休み時間記入
			if( time1.compareTo("12:30") > 0){
				n = 0;
			}else{
				n = 1;
			}
			sheet.getRow(row).getCell(5).setCellValue(n);

			//関数の再計算
			//book.getCreationHelper().createFormulaEvaluator().evaluateAll();
			//book.getCreationHelper().createFormulaEvaluator().evaluateFormulaCellEnum(sheet.getRow(row).getCell(7));
			sheet.setForceFormulaRecalculation(true);

			//ファイル保存
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
