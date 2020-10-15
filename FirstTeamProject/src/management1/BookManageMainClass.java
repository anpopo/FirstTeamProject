package management1;

import java.util.Scanner;

public class BookManageMainClass {
	public static void main(String[] args) {
		InterBookManage manage = new BookManage();

		/*BookDTO bookDto = null;*/

		Scanner sc = new Scanner(System.in);

		String menu = "1.사서 전용메뉴    2.일반회원 전용메뉴    3.프로그램 종료\n" + "=> 메뉴번호선택 : ";
		String title = "    ====>>>> 도서대여 프로그램 <<<<====    ";
		String menuNum = "";

		do {

			System.out.println(title); // 메인 메뉴 보여주기
			System.out.print(menu); // 메인 메뉴 보여주기
			menuNum = sc.nextLine(); // 메인 메뉴번호 선택하기
			System.out.println();

			switch (menuNum) {
			case "1":  // 사서 전용 메뉴
				manage.showManagerMenu(sc/*, bookDto*/);
				break;

			case "2": // 일반 회원 전용 메뉴
				manage.showUserMenu(sc/*, bookDto*/);
				break;

			case "3": // 프로그램 종료
				break;
			
			default:
				System.out.println(">>> 메뉴에 없는 번호 입니다. 다시 선택하세요!!\n");
				break;
			}// end of switch--------------------------------------------

		} while (!"3".equals(menuNum)); // end of while----------------------------------

		System.out.println(">>> 프로그램을 종료합니다. <<<\n");
		sc.close();

	}
}
