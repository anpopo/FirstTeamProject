package management1;

import java.io.*;

public class LibrarySerializable {
	
	
	// 직렬화 하는 메소드 생성하기(메모리상에 올라온 객체를 하드디스크 파일에 저장시키기)
	public int objectToFileSave(Object obj, String saveFilename) {

		// === 객체 obj를 파일 saveFilename 으로 저장하기 ===//
		try {
			FileOutputStream fost = new FileOutputStream(saveFilename, false);
			// 출력 노드 스트림(빨대꽂기)
			// 파일이름(saveFilename)을 이용해서FileOutputStream 객체를 생성한다
			// 생성된 객체는 두번째 파라미터 boolean append를 통해 값에 따라서 true이면 기존 파일에 내용을 덧붙여 추가 할 것이고,
			// false이면 기존 내용은 삭제하고 새로운 내용으로 덮어씌운다.

			BufferedOutputStream bufOst = new BufferedOutputStream(fost, 1024);
			// 필터 스트림 (보조 스트림) - 노드 스트림의 인풋과 아웃풋의 속도 향상
			ObjectOutputStream objOst = new ObjectOutputStream(bufOst);
			// objOst은 객체 obj를 파일명 saveFilename 에 기록하는 스트림 객체 생성하기

			objOst.writeObject(obj);

			objOst.close();
			bufOst.close();
			fost.close();
			
			return 1;

		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			return 0;
		} catch (IOException e) {
//			e.printStackTrace();
			return 0;
		}

	}

	// 역직렬화하는 메소드 생성하기(하드디스크에 저장된 파일을 읽어다가 객체로 만들어 메모리에 올리게 하는 것)

	public Object getObjectFromFile(String fileName) {
		// === 파일명 fileName을 읽어서 객체 Object로 변환하기 === //

		try {
			FileInputStream finst = new FileInputStream(fileName);
			// 입력 노드 스트림(빨대 꽂기)

			BufferedInputStream bufInst = new BufferedInputStream(finst, 1024);
			// 필터스트림

			ObjectInputStream objInst = new ObjectInputStream(bufInst);
			// 파일명(fileName)을 읽어서 객체로 만들어 주는 스트림을 생성해주는 객체

			Object obj = objInst.readObject();
			// 파일명을 읽어서 객체로 만들어 주는 것이다
			
			objInst.close();
			bufInst.close();
			finst.close();
			
			
			return obj;
			
			
			
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
		} catch (IOException e) {
//			e.printStackTrace();
		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		}
		
		return null;
	}

}
