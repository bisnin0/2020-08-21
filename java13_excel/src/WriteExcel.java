import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/* 
 * 
 * ������ ���� �� �غ���� *
 POI�ٿ�ε� 
 jakarta.apache.org���� poi-bin-4.1.2-20200217.zip �ٿ�ε�
 
poi-4.1.2.jar, commons-math3-3.6.1.jar BuildPath �����Ѵ�.

 */


/////////////�ڹٷ� ��������
public class WriteExcel {

	public WriteExcel() {
	}
	
	public void start() {
		//������ ����
		//1. workbook ��ü ����
		HSSFWorkbook workbook = new HSSFWorkbook(); //Ŭ���� �̸� HSSFWorkbook(); �̰� import org.apache.poi.hssf.usermodel.HSSFWorkbook; �̷��� import�ؾ��Ѵ�.
		
		//2. sheet ��ü ����
		HSSFSheet sheet1= workbook.createSheet("ȸ�����"); //��Ʈ����.. org.apache.poi.hssf.usermodel.HSSFSheet;
		HSSFSheet sheet2 = workbook.createSheet();
		
		//3. row��ü
		HSSFRow row1 = sheet1.createRow(0); //org.apache.poi.hssf.usermodel.HSSFRow;
		
		HSSFCell cell = row1.createCell(0); //ù��°ĭ �������.. org.apache.poi.hssf.usermodel.HSSFCell;
		
		cell.setCellValue("��ȣ");
		//////////������ ������ ���� �ִ°�.
		//////////�Ʒ��� ���ٷ� �����°�
		row1.createCell(1).setCellValue("�̸�"); //row1.���� ĭ�� �����ϴµ� �ε����� 1���ض�.
		row1.createCell(2).setCellValue("����ó");
		
		String data[][] = {{"1","ȫ�浿","010-1234-5678"}, //�迭�� ���� �����Ͱ� �־�� �ϱ� ������ ���ڸ� ���ڷ� �ٲ�
							{"2","������","010-1111-1111"},
							{"3","�̼���","010-3333-3333"}};

		// 				1~3		3 //���� 1���� �����̳� 0���� ���ۿ� ū �ǹ̴� ����. 0�� 1�� �̷������� �����ϱ����ؼ� �׳� ����
		for(int i=1; i<=data.length; i++) { //��
			HSSFRow row = sheet1.createRow(i); //11111111111  1���� ����
			
			
			
			//			0~2
			for (int j=0; j<data[i-1].length; j++) {//��.. ĭ �� i�� 1 2 3�� �Ǵµ� �׳� i���� �迭�� 3�� ã�°Ŷ� ������
				HSSFCell cell2 = row.createCell(j); // 11111 1���� ����
				if(j==0) { //��ȣ�϶��� ����ó�� �� �迭���� index0 �� ���ڸ� ���ڷ� �ٲ��ű⶧���� 0���� ���ڷ� �ٲ���Ѵ�.
					cell2.setCellValue(Integer.parseInt(data[i-1][j])); //i�� 1���� �ߴµ� �迭�� 0���� �����̴ϱ� -1�ϴ°�
				}else {//�ƴҶ��� ����ó��
					cell2.setCellValue(data[i-1][j]);
				}
			}  
		}
		
		/////////////////////////////
		//// 5. ���Ϸ� ���� .. ���Ϸ� ���°� Filewritestream�� Fileoutputstream�ε� ���⼭ FileOutputStream�� ����Ѵ�.
		try {
		File f = new File("D://javaFolder/member.xls");
		FileOutputStream fos = new FileOutputStream(f);
		
		workbook.write(fos);
		workbook.close();
		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		System.out.println("������ ���Ⱑ �Ϸ�Ǿ����ϴ�.");
		
		
		
		
		
		
	}
	public static void main(String[] args) {
		new WriteExcel().start();
	}

}
