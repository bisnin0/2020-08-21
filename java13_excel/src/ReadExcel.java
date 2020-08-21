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
		//엑셀에서 데이터 읽어오기
		//1. excel파일을 FileInputStream을 먼저 생성한다.
		try {
		File f = new File("d:/javaFolder/member.xls"); //실제라면 파일 초이스 창 띄워주고 고를 수 있게 해줘야하는데 여기선 그냥 편의를 위해서 경로그냥씀
		FileInputStream fis = new FileInputStream(f);
		
		//2.
		POIFSFileSystem system = new POIFSFileSystem(fis);
		
		//3. workbook 객체 얻어오기
		HSSFWorkbook workbook= new HSSFWorkbook(system); //이 안에 sheet가 있다.
		
		//4. sheet 객체 얻어오기
		HSSFSheet sheet= workbook.getSheet("회원목록"); //종이 한장을 가지고 나온것 //workbook.getSheetAt(0);로 해도 된다. 숫자는 시트몇번째인지
		
		//5. 제목출력
		System.out.println("번호\t이름\t연락처"); 
		
		//시트의 레코드 수 구하기
		int rowCount = sheet.getPhysicalNumberOfRows();
//		System.out.println("행의 수= "+rowCount); //행의 수가 하나 더 많은 이유 = 제목도 행이라서 //행의 수 출력하기
		//0행은 제목으로 위에서 찍어놨기때문에 0행은 출력 안해도 된다.
		//행의수만큼 반복
		for(int r=1; r<rowCount; r++) { //0행은 처리 안할거니까 1부터
			//행 구하기 .. 갯수가 아니라 행 전체를 구해오는것. 그래야 행에 몇칸이 있는지 구할수있다.
			HSSFRow row = sheet.getRow(r); //1행부터.. 행 안에 칸이 몇개있는지 row안에 담김
			//0열 1열 2열
			//셀의 수 구하기(열)
			int cellCount = row.getPhysicalNumberOfCells();
			for(int c=0; c<cellCount; c++) { // 0, 1, 2 이렇게 돈다. c가 0일때는 0열 c가 1일때는 1열
				if(c==0) { //0열에는 숫자가 찍혀있으니까 숫자데이터를 읽어오는 메소드
					double num = row.getCell(c).getNumericCellValue(); //0열에 있는 데이터를 읽어서 double로
					System.out.print(num+"\t");
				}else { //0이 아닌경우는 문자를 읽어오는 메소드
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
