package management1;

import java.util.Scanner;

public interface InterBookManage {
	void showManagerMenu(Scanner sc/*, BookDTO bookDto*/);  // 사서 전용 메뉴  (0)
	
	void managerSignUp(Scanner sc);  // 사서 가입 기능 (0)
	
	String managerLogin(Scanner sc);  // 사서 로그인 (0)
	
	boolean checkManagerId(String managerId);  // 사서 아이디 중복확인 (0)
	
	void registerBook(Scanner sc);  // 도서 정보 등록 (0)
	
	boolean checkIsbn(String isbn);  // isbn 중복검사 (0)
	
	void addEachBook(Scanner sc);  // 개별 도서 등록 (0)
	
	void rentBook(Scanner sc);  // 도서 대여 (0)
	
	void showRentBook();  // 대여중인 도서 조회(0)
	
	void returnBook(Scanner sc);  // 도서 반납
	
	void addloc(Scanner sc);	// 도서 위치 등록
	
	////////////////////////////////////////////////////////
	
	void showUserMenu(Scanner sc/*, BookDTO bookDto*/);  // 일반 회원 전용 메뉴
	
	boolean checkUserId(String userId);  // 유저 아이디 중복확인
	
	void userSignUp(Scanner sc);  // 회원가입
	
	void searchBook(Scanner sc);  // 도서 검색
	
	void searchLoc(Scanner sc);   // 도서 위치 검색
	
	void showMyState(String userName);  // 나의 대여 현황 보기
	
	String userLogin(Scanner sc);  // 유저 로그인
	
	////////////////////////////////////////////////////////////////////////////
	
	void userCheck();// 사서 메뉴 - 전체 유저 정보 확인
	
	void deleteEachBook(Scanner sc);// 사서 메뉴 - 개별도서 삭제하기
	
	String deleteUserId(Scanner sc, String userCheck);// 유저 메뉴 - 유저 탈퇴 하기
	
	
	// 사서 메뉴 - 
}
