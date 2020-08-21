import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ReadExcel {

	public ReadExcel() {
	}
	public void start() {
		//�������� ������ �о����
		//1. excel������ FileInputStream�� ���� �����Ѵ�.
		try {
		File f = new File("d:/javaFolder/member.xls"); //������� ���� ���̽� â ����ְ� �� �� �ְ� ������ϴµ� ���⼱ �׳� ���Ǹ� ���ؼ� ��α׳ɾ�
		FileInputStream fis = new FileInputStream(f);
		
		//2.
		POIFSFileSystem system = new POIFSFileSystem(fis);
		
		//3. workbook ��ü ������
		HSSFWorkbook workbook= new HSSFWorkbook(system); //�� �ȿ� sheet�� �ִ�.
		
		//4. sheet ��ü ������
		HSSFSheet sheet= workbook.getSheet("ȸ�����"); //���� ������ ������ ���°� //workbook.getSheetAt(0);�� �ص� �ȴ�. ���ڴ� ��Ʈ���°����
		
		//5. �������
		System.out.println("��ȣ\t�̸�\t����ó"); 
		
		//��Ʈ�� ���ڵ� �� ���ϱ�
		int rowCount = sheet.getPhysicalNumberOfRows();
//		System.out.println("���� ��= "+rowCount); //���� ���� �ϳ� �� ���� ���� = ���� ���̶� //���� �� ����ϱ�
		//0���� �������� ������ �����⶧���� 0���� ��� ���ص� �ȴ�.
		//���Ǽ���ŭ �ݺ�
		for(int r=1; r<rowCount; r++) { //0���� ó�� ���ҰŴϱ� 1����
			//�� ���ϱ� .. ������ �ƴ϶� �� ��ü�� ���ؿ��°�. �׷��� �࿡ ��ĭ�� �ִ��� ���Ҽ��ִ�.
			HSSFRow row = sheet.getRow(r); //1�����.. �� �ȿ� ĭ�� ��ִ��� row�ȿ� ���
			//0�� 1�� 2��
			//���� �� ���ϱ�(��)
			int cellCount = row.getPhysicalNumberOfCells();
			for(int c=0; c<cellCount; c++) { // 0, 1, 2 �̷��� ����. c�� 0�϶��� 0�� c�� 1�϶��� 1��
				if(c==0) { //0������ ���ڰ� ���������ϱ� ���ڵ����͸� �о���� �޼ҵ�
					double num = row.getCell(c).getNumericCellValue(); //0���� �ִ� �����͸� �о double��
					System.out.print(num+"\t");
				}else { //0�� �ƴѰ��� ���ڸ� �о���� �޼ҵ�
					String data = row.getCell(c).getStringCellValue();
					System.out.print(data+"\t");
				}
			}
			System.out.println();
		}
		
		system.close();
		fis.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new ReadExcel().start();
	}

}
