import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/* 
 * 
 * 엑셀로 쓰기 전 준비사항 *
 POI다운로드 
 jakarta.apache.org에서 poi-bin-4.1.2-20200217.zip 다운로드
 
poi-4.1.2.jar, commons-math3-3.6.1.jar BuildPath 설정한다.

 */


/////////////자바로 엑셀쓰기
public class WriteExcel {

	public WriteExcel() {
	}
	
	public void start() {
		//엑셀로 쓰기
		//1. workbook 객체 생성
		HSSFWorkbook workbook = new HSSFWorkbook(); //클래스 이름 HSSFWorkbook(); 이걸 import org.apache.poi.hssf.usermodel.HSSFWorkbook; 이렇게 import해야한다.
		
		//2. sheet 객체 생성
		HSSFSheet sheet1= workbook.createSheet("회원목록"); //시트생성.. org.apache.poi.hssf.usermodel.HSSFSheet;
		HSSFSheet sheet2 = workbook.createSheet();
		
		//3. row객체
		HSSFRow row1 = sheet1.createRow(0); //org.apache.poi.hssf.usermodel.HSSFRow;
		
		HSSFCell cell = row1.createCell(0); //첫번째칸 만들어짐.. org.apache.poi.hssf.usermodel.HSSFCell;
		
		cell.setCellValue("번호");
		//////////위에는 변수를 만들어서 넣는것.
		//////////아래는 한줄로 끝내는것
		row1.createCell(1).setCellValue("이름"); //row1.에서 칸을 생성하는데 인덱스를 1로해라.
		row1.createCell(2).setCellValue("연락처");
		
		String data[][] = {{"1","홍길동","010-1234-5678"}, //배열은 같은 데이터가 있어야 하기 때문에 숫자를 문자로 바꿈
							{"2","강감찬","010-1111-1111"},
							{"3","이순신","010-3333-3333"}};

		// 				1~3		3 //딱히 1부터 시작이나 0부터 시작에 큰 의미는 없다. 0열 1행 이런식으로 구분하기위해서 그냥 쓴것
		for(int i=1; i<=data.length; i++) { //행
			HSSFRow row = sheet1.createRow(i); //11111111111  1행을 만듬
			
			
			
			//			0~2
			for (int j=0; j<data[i-1].length; j++) {//열.. 칸 수 i가 1 2 3이 되는데 그냥 i쓰면 배열의 3을 찾는거라 에러남
				HSSFCell cell2 = row.createCell(j); // 11111 1열을 만듬
				if(j==0) { //번호일때는 숫자처리 위 배열에서 index0 은 숫자를 문자로 바꿔논거기때문에 0열은 숫자로 바꿔야한다.
					cell2.setCellValue(Integer.parseInt(data[i-1][j])); //i를 1부터 했는데 배열은 0부터 시작이니까 -1하는것
				}else {//아닐때는 문자처리
					cell2.setCellValue(data[i-1][j]);
				}
			}  
		}
		
		/////////////////////////////
		//// 5. 파일로 쓰기 .. 파일로 쓰는건 Filewritestream과 Fileoutputstream인데 여기서 FileOutputStream을 써야한다.
		try {
		File f = new File("D://javaFolder/member.xls");
		FileOutputStream fos = new FileOutputStream(f);
		
		workbook.write(fos);
		workbook.close();
		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		System.out.println("엑셀로 쓰기가 완료되었습니다.");
		
		
		
		
		
		
	}
	public static void main(String[] args) {
		new WriteExcel().start();
	}

}
