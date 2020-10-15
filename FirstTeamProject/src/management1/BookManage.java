package management1;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BookManage implements InterBookManage {

	LibrarySerializable serial = new LibrarySerializable();
	private final String BOOK_LIST_FILE_NAME = "C:\\NCS\\iotestdata\\project\\librarymanage/booklist.dat";
	private final String USER_LIST_FILE_NAME = "C:\\NCS\\iotestdata\\project\\librarymanage/userlist.dat";
	private final String MANAGER_LIST_FILE_NAME = "C:\\NCS\\iotestdata\\project\\librarymanage/managerlist.dat";
	private final String EACH_BOOK_FILE_NAME = "C:\\NCS\\iotestdata\\project\\librarymanage/eachbooklist.dat";
	private final String RENT_LIST_FILE_NAME = "C:\\NCS\\iotestdata\\project\\librarymanage/rentlist.dat";
	private final String BOOKLOCATION_FILE_NAME = "C:\\NCS\\iotestdata\\project\\librarymanage/loclist.dat";
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void showManagerMenu(Scanner sc/* , BookDTO bookDto */) { // 사서 메뉴
		String managerCheck = null; // 로그인 했을때 나오는 정보를 바꿔주기 위해 로그인 유무를 체크하는 용도로 사용하는 스트링 변수

		String menu = "1.사서 가입    2.로그인    3.로그아웃    4.도서 정보 등록    5.개별 도서 등록 \n"
				+ "6.도서 대여 해주기    7.대여중인 도서 조회    8.도서 반납 해주기    9.전체 유저 확인\n"
				+ "10.개별 도서 삭제    11.도서 위치 등록       12.나가기\n" + "=> 메뉴번호선택 : ";
		String menuNum = "";

		do {
			if (managerCheck == null) {
				String titleNo = "\n>>>> 사서 전용 메뉴 <<<<";
				System.out.println(titleNo);
				System.out.print(menu); // 사서 메인 메뉴 보여주기
				menuNum = sc.nextLine(); // 사서 메인 메뉴번호 선택하기
				System.out.println();
			} else {
				String titleYes = "\n>>>> 사서 전용 메뉴 [" + managerCheck + "님 로그인중...]<<<<";
				System.out.println(titleYes);
				System.out.print(menu); // 사서 메인 메뉴 보여주기
				menuNum = sc.nextLine(); // 사서 메인 메뉴번호 선택하기
				System.out.println();
			}

			switch (menuNum) {
			case "1": // 사서 가입
				if (managerCheck == null) { // 아무도 로그인 되어있지 않고
					managerSignUp(sc);
				} else { // 누군가가 로그인이 되어 있을때
					System.out.printf(">>>이미 %s님이 로그인 중이십니다!!!<<<\n\n로그아웃 하시고 가입을 해주세요.\n", managerCheck);
				}

				break;
			case "2": // 로그인
				if (managerCheck == null) { // 아무도 로그인 되어있지 않고 로그인을 시도할때
					managerCheck = managerLogin(sc);
				} else { // 누군가가 로그인이 되어 있을때
					System.out.printf(">>>이미 %s님이 로그인 중이십니다!!!<<<\n", managerCheck);
				}

				break;
			case "3": // 로그아웃
				if (managerCheck == null) { // 아무도 로그인을 하지 않았을때
					System.out.println(">>>로그인을 먼저 해주셔야 로그아웃을 할 수 있습니다!!!!<<<\n");
				} else { // 누군가가 로그인이 되어 있을때
					System.out.println(">>> " + managerCheck + "님 로그아웃 되셨습니다!\n");
					managerCheck = null;
				}
				break;
			case "4": // 도서 정보 등록
				if (managerCheck == null) { // 아무도 로그인 되어있지 않고 로그인을 시도할때
					System.out.printf(">>>사서 로그인을 먼저 진행해 주세요!!<<<\n\n");
				} else { // 누군가가 로그인이 되어 있을때
					registerBook(sc);
				}
				break;
			case "5": // 개별 도서 등록
				if (managerCheck == null) { // 아무도 로그인 되어있지 않고 로그인을 시도할때
					System.out.printf(">>>사서 로그인을 먼저 진행해 주세요!!<<<\n\n");
				} else { // 누군가가 로그인이 되어 있을때
					addEachBook(sc);
				}
				break;
			case "6": // 도서 대여 해주기
				if (managerCheck == null) { // 아무도 로그인 되어있지 않고 로그인을 시도할때
					System.out.printf(">>>사서 로그인을 먼저 진행해 주세요!!<<<\n\n");
				} else { // 누군가가 로그인이 되어 있을때
					rentBook(sc);
				}

				break;
			case "7": // 대여중인 도서 조회
				if (managerCheck == null) { // 아무도 로그인 되어있지 않고 로그인을 시도할때
					System.out.printf(">>>사서 로그인을 먼저 진행해 주세요!!<<<\n\n");
				} else { // 누군가가 로그인이 되어 있을때
					showRentBook();
				}

				break;
			case "8": // 도서 반납 해주기
				if (managerCheck == null) { // 아무도 로그인 되어있지 않고 로그인을 시도할때
					System.out.printf(">>>사서 로그인을 먼저 진행해 주세요!!<<<\n\n");
				} else { // 누군가가 로그인이 되어 있을때
					returnBook(sc);
				}
				break;
			case "9": // 계정 관리 메뉴
				if (managerCheck == null) { // 아무도 로그인 되어있지 않고 로그인을 시도할때
					System.out.printf(">>>사서 로그인을 먼저 진행해 주세요!!<<<\n\n");
				} else { // 누군가가 로그인이 되어 있을때
					userCheck();
				}
				break;
			case "10": // 개별 도서 삭제
				if (managerCheck == null) { // 아무도 로그인 되어있지 않고 로그인을 시도할때
					System.out.printf(">>>사서 로그인을 먼저 진행해 주세요!!<<<\n\n");
				} else { // 누군가가 로그인이 되어 있을때
					deleteEachBook(sc);
				}
				break;
			case "11":
				if (managerCheck == null) {
					System.out.println(">>>사서 로그인을 먼저 진행해 주세요!!<<<\\n\\n");
				} else {
					addloc(sc);
				}
				break;
			case "12": // 나가기
				System.out.print("로그인 상태일 경우 자동으로 회원 로그아웃 됩니다.[Y/N]\n => ");
				String yesno = sc.nextLine().trim();
				if ("y".equalsIgnoreCase(yesno)) {
					System.out.println("로그아웃 되셨습니다.\n");
					return;
				} else if ("n".equalsIgnoreCase(yesno)) {
					System.out.println("계속해서 서비스를 이용하실 수 있습니다.\n");
					continue;
				} else {
					System.out.println("계속해서 서비스를 이용합니다.\n");
					continue;
				}
			default:
				System.out.println(">>> 메뉴에 없는 번호 입니다. 다시 선택하세요!!\n");
				break;
			}// end of switch------------------------------

		} while (true); // end of do-while-------------------------------

//		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	@Override
	public void managerSignUp(Scanner sc) {
		String managerPasswordTitle = "암호";

		String managerId;
		do {
			System.out.print("▶ 사서 ID : ");
			managerId = sc.nextLine().trim();

			if (managerId.trim().isEmpty() || managerId == null) {
				System.out.println("~~~ 아이디를 입력하세요!!\n");
			} else {

				if (!checkManagerId(managerId)) {
					System.out.println("~~~ " + managerId + " 는 이미 존재하므로 다른  ID를 입력하세요!!\n");
				} else {
					break;
				}
			}

		} while (true); // end of do-while---------------------------------------

		String managerPassword = isEmptyCheck(managerPasswordTitle, sc);

		ManagerDTO managerdto = new ManagerDTO(managerId, managerPassword); // manager 객체 생성

		File file = new File(MANAGER_LIST_FILE_NAME); // 첫등록인지 아닌지를 확인하기 위해 파일 객체 생성

		List<ManagerDTO> managerList = null;

		int n = 0;

		if (!file.exists()) {
			managerList = new ArrayList<ManagerDTO>();
			managerList.add(managerdto);
		} else {
			managerList = (ArrayList<ManagerDTO>) serial.getObjectFromFile(MANAGER_LIST_FILE_NAME);
			managerList.add(managerdto);
		}
		n = serial.objectToFileSave(managerList, MANAGER_LIST_FILE_NAME);

		if (n == 1) {
			System.out.println(">>> 사서 등록 성공 <<<\n");
		} else {
			System.out.println(">>> 사서 등록 개실패!!ㅋㅋㅋㅋㅋ <<<\n");
		}

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	@Override
	public String managerLogin(Scanner sc) {
		Object obj = serial.getObjectFromFile(MANAGER_LIST_FILE_NAME);
		String getIdTitle = "사서 아이디";
		String getPasswordTitle = "암호";

		String getId = isEmptyCheck(getIdTitle, sc);
		String getPassword = isEmptyCheck(getPasswordTitle, sc);

		boolean flag = true;

		List<ManagerDTO> managerList = null;
		if (obj != null) {
			managerList = (List<ManagerDTO>) obj;

			for (ManagerDTO manager : managerList) {
				if (manager.getManagerId().equals(getId) && manager.getManagerPassword().equals(getPassword)) {
					System.out.println(">>>축하합니다! 로그인에 성공하셨어요~~!!!<<<\n");
					return manager.getManagerId();
				} else if (!(manager.getManagerId().equals(getId))
						|| !(manager.getManagerPassword().equals(getPassword))) {
					flag = false;
				}
			}

			if (!flag) {
				System.out.println(">>>로그인에 실패했으니까 아이디랑 비밀번호를 확인하면 좋겠어요!<<<\n");
			}
		} else {
			System.out.println(">>> 회원가입을 부탁드립니다. 등록되어 있지 않은 아이디와 비밀번호 입니다. <<<");
		}
		return null;

	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkManagerId(String managerId) {
		boolean isUse = true;
		Object managerListObj = serial.getObjectFromFile(MANAGER_LIST_FILE_NAME);

		if (managerListObj != null) {

			List<ManagerDTO> managerList = (List<ManagerDTO>) managerListObj;
			for (ManagerDTO manager : managerList) {
				if (manager.getManagerId().equals(managerId)) {
					isUse = false;
					break;
				} // end of if -------------------------------------
			} // end of for ------------------------------------
//			isUse = false;
		} // end of if --------------------------------------

		return isUse;

	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	@Override
	public void registerBook(Scanner sc) {

		String getISBNTitle = "국제 표준 도서 번호 (ISBN)";
		String getCategoryTitle = "도서 분류 카테고리";
		String getBookNameTitle = "도서명";
		String getAutorTitle = "작가명";
		String getPublisherTitle = "출판사";
		String getPriceTitle = "가격";

		System.out.println("====> 도서 정보 등록하기 <====");
		String getISBN;
		do {
			getISBN = isEmptyCheck(getISBNTitle, sc);
			if (!checkIsbn(getISBN))
				System.out.printf(">>> %s 는 이미 존재하므로 다른 %s를 입력하시기 바랍니다!!!<<<\n", getISBN, getISBNTitle);
			else
				break;
		} while (true);

		String getCategory = isEmptyCheck(getCategoryTitle, sc);
		String getBookName = isEmptyCheck(getBookNameTitle, sc);
		String getAutor = isEmptyCheck(getAutorTitle, sc);
		String getPublisher = isEmptyCheck(getPublisherTitle, sc);
		int getPrice = isNumCheck(getPriceTitle, sc);

		BookDTO bookDto = new BookDTO(getISBN, getCategory, getBookName, getAutor, getPublisher, getPrice);
		File file = new File(BOOK_LIST_FILE_NAME);
		Map<String, BookDTO> bookMap = null;

		if (!file.exists()) {
			bookMap = new HashMap<String, BookDTO>();
			bookMap.put(getISBN, bookDto);
		} else {
			bookMap = (HashMap<String, BookDTO>) serial.getObjectFromFile(BOOK_LIST_FILE_NAME);
			bookMap.put(getISBN, bookDto);
		}

		int n = serial.objectToFileSave(bookMap, BOOK_LIST_FILE_NAME);

		if (n == 1) {
			System.out.println(">>> 도서 정보 등록 성공!! <<<\n");
		} else {
			System.out.println(">>> 도서 정보 등록 개실패!!ㅋㅋ <<<\n");
		}

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	@Override
	public boolean checkIsbn(String isbn) {
		Map<String, BookDTO> bookMap = (HashMap<String, BookDTO>) serial.getObjectFromFile(BOOK_LIST_FILE_NAME);

		boolean flag = true;
		if (!(bookMap == null)) {

			Set<String> keys = bookMap.keySet();

			for (String k : keys) {
				if (k.equals(isbn)) {
					flag = false;
				}
			}
		}
		return flag;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public boolean checkEachBook(String eachBookId) {
		List<EachBookDTO> eachBookList = (List<EachBookDTO>) serial.getObjectFromFile(EACH_BOOK_FILE_NAME);

		boolean flag = true;
		if (!(eachBookList == null)) {

			for (EachBookDTO book : eachBookList) {
				if (book.getBookId().equals(eachBookId)) {
					flag = false;
				}
			}
		}
		return flag;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	@Override
	public void addEachBook(Scanner sc) {
		System.out.println("== 개별도서 등록하기 ==");
		String getISBNTitle = "국제 표준 도서 번호 (ISBN)";
		String getISBN = isEmptyCheck(getISBNTitle, sc); // 111-22-33333-44-5 111-22-33333-44-6 111-22-33333-44-7
		String getEachBookId;
		int n;
		List<EachBookDTO> eachBookList = null;

		if (checkIsbn(getISBN)) {
			System.out.print(">>> 등록된 ISBN 이 아닙니다! 도서등록 실패!! <<<\n");
			return;
		} else {
			do {
				String getEachBookIdTitle = "도서아이디";
				getEachBookId = isEmptyCheck(getEachBookIdTitle, sc); // 111-22-33333-44-5-001 111-22-33333-44-6-001
																		// 111-22-33333-44-7-001
																		// 111-22-33333-44-5-002 111-22-33333-44-6-002
																		// 111-22-33333-44-7-002
																		// 111-22-33333-44-5-003

				if (checkEachBook(getEachBookId)) {
					Map<String, BookDTO> bookMap = (Map<String, BookDTO>) serial.getObjectFromFile(BOOK_LIST_FILE_NAME);
					EachBookDTO eachBook = new EachBookDTO(getISBN, getEachBookId, bookMap.get(getISBN));

					File file = new File(EACH_BOOK_FILE_NAME);

					if (!file.exists()) {
						eachBookList = new ArrayList<EachBookDTO>();
						eachBookList.add(eachBook);
					} else {
						eachBookList = (ArrayList<EachBookDTO>) serial.getObjectFromFile(EACH_BOOK_FILE_NAME);
						eachBookList.add(eachBook);
					}
					n = serial.objectToFileSave(eachBookList, EACH_BOOK_FILE_NAME);

					break;

				} else {
					System.out.printf(">>> %s 는 이미 존재하므로 다른 %s를 입력하시기 바랍니다!!!<<<\n\n", getEachBookId,
							getEachBookIdTitle);
					continue;
				}

			} while (true);
		}

		if (n == 1) {
			System.out.println(">>>>> 도서 등록에 성공 하셨습니다!! <<<<<\n");
		} else {
			System.out.println(">>>>> 도서 등록에 실패 하셨습니다!! <<<<<\n");
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	@Override
	public void rentBook(Scanner sc) {
		String userIdTitle = "회원 ID";
		String countBookTitle = "총대여권수";
		String bookIdTitle = "도서 ID";
		String bookId;
		String userId;

		List<UserDTO> userList = (ArrayList<UserDTO>) serial.getObjectFromFile(USER_LIST_FILE_NAME);
		List<EachBookDTO> eachBookList = (ArrayList<EachBookDTO>) serial.getObjectFromFile(EACH_BOOK_FILE_NAME);
		File file = new File(RENT_LIST_FILE_NAME);

		List<RentalTaskDTO> rentalList = null;

		UserDTO rentUser = null;
		EachBookDTO rentBook = null;
		RentalTaskDTO rentalDto = null;
		boolean flag;
		int n = 0;

		System.out.println(">>> 도서 대여 하기 <<<");

		if (userList != null && eachBookList != null) {
			do {
				flag = false;
				userId = isEmptyCheck(userIdTitle, sc);

				for (UserDTO user : userList) {

					if (user.getUserId().equals(userId)) {

						rentUser = user;
						flag = true;
						break;
					}
				}

				if (flag == false)
					System.out.println(">>> 등록된 회원 ID가 아닙니다! <<<\n");
				else
					break;
			} while (true);

			int countBook = isNumCheck(countBookTitle, sc);

			int temp = 0;
//			boolean flag;

			do { // start fo do - while
					// -------------------------------------------------------------
				flag = false;
				bookId = isEmptyCheck(bookIdTitle, sc);
				for (EachBookDTO book : eachBookList) {
					if (book.getBookId().equals(bookId)) {
						rentBook = book;
						flag = true;
					}
				}

				if (flag == false)
					System.out.println(">>> 등록된 도서 ID가 아닙니다! 다시 입력해 주세요!! <<<\n");
				else {
					rentalDto = new RentalTaskDTO(userId, bookId, rentUser, rentBook); // rentalDto객체 하나 만들어 주고
//					SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
//					Calendar cal = Calendar.getInstance();
//					Date date = new Date();
////					String today = simple.format(date); // 오늘 날짜 구해주고
////					try {
////						date = simple.parse(today); // 문자열 타입을 데이트 타입으로 바꾸어 준다
////					} catch (ParseException e) {
////						e.printStackTrace();
////					}
//					cal.setTime(date);
//					cal.setTime(date);
//					cal.add(Calendar.DATE, 3); // 3일 더한 날짜를 구해주고
//					String returnDate = simple.format(cal.getTime());

					if (!file.exists()) {
						rentalList = new ArrayList<RentalTaskDTO>();
						rentalDto.setRentalDate();
						rentalDto.setReturnDate();
						rentalDto.getEachBookDto().setRent(true);
						rentalList.add(rentalDto);
					} else {
						rentalList = (ArrayList<RentalTaskDTO>) serial.getObjectFromFile(RENT_LIST_FILE_NAME);
						boolean flag2 = false; // 이미 등록이 되어 있는 책일 경우 확인할 수 있도록 사용하는 플래그
						for (RentalTaskDTO rent : rentalList) { // 이미 렌탈 리스트에 있는 책인지 아닌지 확인한다.
							if (rent.getBookId().equals(bookId)) {

								flag2 = true;
								break;
							}
						}

						if (flag2 == true) {
							System.out.println(">>> 이미등록되어 있는 책입니다. <<<\n");
							continue;
						}

						rentalDto.setRentalDate();
						rentalDto.setReturnDate();
						rentalDto.getEachBookDto().setRent(true);
						rentalList.add(rentalDto);
					}

					temp++;
					n = serial.objectToFileSave(userList, USER_LIST_FILE_NAME);
					n = serial.objectToFileSave(eachBookList, EACH_BOOK_FILE_NAME);
					n = serial.objectToFileSave(rentalList, RENT_LIST_FILE_NAME);
				}
			} while (temp < countBook); // end of do - while--------------------------------------------

			if (n == 1) {
				System.out.println(">>> 대여 등록 성공!!! <<<\n");
				System.out.println(">>> 대여도서 비치중에서 대여중으로 변경함!! <<<\n");
			} else {
				System.out.println(">>> 대여 등록 실패!!! <<<\n");
			}

		} else {
			System.out.println("유저 혹은 개별 책을 등록하셨는지 먼저 확인해 주세요\n");
		}

	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	@Override
	public void showRentBook() {
		List<RentalTaskDTO> rentList = (ArrayList<RentalTaskDTO>) serial.getObjectFromFile(RENT_LIST_FILE_NAME);

		System.out.println(
				"=================================================================================================================================================");
		System.out.println(
				"도서ID                    ISBN                     도서명          작가명         출판사         회원ID            대여일자                    반납예정일");
		System.out.println(
				"=================================================================================================================================================");
		if (rentList == null) {
			System.out.println("현재 대여중인 도서가 없습니다!!");
		} else {
			for (RentalTaskDTO rental : rentList) {
				System.out.print(rental);
			}
		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	@Override
	public void returnBook(Scanner sc) {
		String numberOfReturnbookTitle = "총 반납 권수";
		String returnBookIdTitle = "반납도서 ID";
		List<RentalTaskDTO> rentList = (ArrayList<RentalTaskDTO>) serial.getObjectFromFile(RENT_LIST_FILE_NAME);
		List<EachBookDTO> eachBookList = (ArrayList<EachBookDTO>) serial.getObjectFromFile(EACH_BOOK_FILE_NAME);

		List<String> tempIdList = new ArrayList<String>();
		System.out.println(">>> 도서 반납 하기 <<<");
		int numberOfRepeat = isNumCheck(numberOfReturnbookTitle, sc); // 총 반납을 할 책의 수를 입력받는다.
		int cnt = 0;
		int i = 0;
		String returnBookId = null;
		int total = 0;
		int[] index = new int[numberOfRepeat]; // 인트 리스트에 리스트의 리스트의 인덱스 값을 받기 위해 인트 배열을 초기화해준다.
		int tempIndex = 0;

		do { // start of do while
				// ------------------------------------------------------------
			boolean flag = false; // 값이 리스트 안에 체크하기 위해 만든 불린값 플래그
			returnBookId = isEmptyCheck(returnBookIdTitle, sc); // 반납할 도서의 아이디를 입력을 받고 빈칸 체크까지 한다

			if (rentList != null) {
				for (RentalTaskDTO rent : rentList) { // 렌트리스트에 있는 렌탈 디티오 내부에서 입력한 책의 이름과 같은 책의 이름을 찾아 있다면 플래그 값을 트루값으로
														// 바꾼다.
					if (rent.getBookId().equals(returnBookId)) {
						flag = true;
						break; // 책의 아이디는 고유의 값이기 때문에 중복 될 수 없다. 그렇기 때문에 하나의 책만 찾으면 반복문을 바로 해제시켜 준다.
					}
				} // end of for -----------------------------------------------------------------
			} else {
				System.out.println("대여중인 책이 한권도 없습니다~!\n");
				return;
			}

			if (flag == false) { // 리스트 내부에 검색한 책의 아이디와 같은 아이디가 없다면

				System.out.println(">>> 대출 목록에 존재하지 않는 도서 ID입니다. 다시 입력하세요!! <<<\n");
				continue;

			}

			else { // 빌려간 책의 리스트 내부에 검색한 책의 아이디와 같은 책의 이름이 있을 경우
				if (tempIdList == null || tempIdList.isEmpty()) {

					for (i = 0; i < rentList.size(); i++) { // 리스트 전체를 돌면서 각각의 객체를 확인하기 위해 반복문을 사용한다.
						if (rentList.get(i).getEachBookDto().getBookId().equals(returnBookId)) { // 만약 렌트 리스트에 입력받은 책의
																									// 아이디와 같은 아이디가 있는
																									// 경우
							rentList.get(i).setArrears(); // 연체료를 설정해준다.
							total += rentList.get(i).getArrears(); // 연체료를 토탈 연체료에 더해준다.
							System.out.printf("도서별 연체료 : %d원\n", rentList.get(i).getArrears()); // 해당 도서의 연체료를 보여준다.
							rentList.get(i).getEachBookDto().setRent(false); // 각각의 책의 디티오에 접근해 대여중의 상태를 비치중으로 바꾸어 준다.
							index[tempIndex] = i;
							tempIdList.add(returnBookId);
							tempIndex++;
							break;
						}
					}

					for (EachBookDTO bookss : eachBookList) {
						if (bookss.getBookId().equals(returnBookId)) {
							bookss.setRent(false);
							break;
						}
					}

				} else {
					flag = false;

					for (String Id : tempIdList) {
						if (returnBookId.equals(Id)) {
							flag = true;
							break;
						}
					}

					if (flag == true) {
						System.out.println("이미 입력을 하셨습니다. 다시 새로 입력해 주세요!");
						continue;
					} else {
						for (i = 0; i < rentList.size(); i++) { // 리스트 전체를 돌면서 각각의 객체를 확인하기 위해 반복문을 사용한다.
							if (rentList.get(i).getBookId().equals(returnBookId)) { // 만약 렌트 리스트에 입력받은 책의 아이디와 같은 아이디가
																					// 있는 경우
								rentList.get(i).setArrears(); // 연체료를 설정해준다.
								total += rentList.get(i).getArrears(); // 연체료를 토탈 연체료에 더해준다.
								System.out.printf("도서별 연체료 : %d원\n", rentList.get(i).getArrears()); // 해당 도서의 연체료를 보여준다.
								rentList.get(i).getEachBookDto().setRent(false); // 각각의 책의 디티오에 접근해 대여중의 상태를 비치중으로 바꾸어
																					// 준다.
								index[tempIndex] = i;
								tempIndex++;
								tempIdList.add(returnBookId);
								break;
							}
						}

						for (EachBookDTO bookss : eachBookList) {
							if (bookss.getBookId().equals(returnBookId)) {
								bookss.setRent(false);
								break;
							}
						}
					}
				}

				cnt += 1;
			}

		} while (cnt < numberOfRepeat); // end of do-while
										// ---------------------------------------------------------------

		int num = 0;
		for (int k = 0; k < index.length; k++) {
			for (int r = k + 1; r < index.length; r++) {
				if (index[r] > index[k]) {
					num = index[k];
					index[k] = index[r];
					index[r] = num;
				}
			}
		}

		for (int w : index) {
			rentList.remove(w);
		}

		int n = serial.objectToFileSave(eachBookList, EACH_BOOK_FILE_NAME);
		n = serial.objectToFileSave(rentList, RENT_LIST_FILE_NAME);

		if (n == 1) {
			System.out.println(">>> 도서 반납 성공!! <<<");
			System.out.println(">>> 대여 도서 대여중에서 비치중으로 변경함 <<<\n");
			System.out.printf("▶ 연체료 총계 : %d원\n", total);
		} else {
			System.out.println(">>> 도서 반납 개실패!!!ㅋㅋㅋㅋ <<<\n");
		}

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void showUserMenu(Scanner sc/* , BookDTO bookDto */) { // 유저 메뉴 보여주기
		String userCheck = null;

		String menu = "1.일반 회원 가입    2.로그인    3.로그아웃    4.도서 검색하기    5.도서위치 검색\n6.나의 대여 현황 보기    7.랜덤 책추천    8.유저 탈퇴    "
				+ "9.나가기\n" + "=> 메뉴번호선택 : ";
		String menuNum = "";

		do {

			if (userCheck == null) {
				String title = ">>>> 일반회원 전용 메뉴 <<<<";
				System.out.println(title);
				System.out.print(menu); // 유저 메인 메뉴 보여주기
				menuNum = sc.nextLine(); // 사서 메인 메뉴번호 선택하기
				System.out.println();
			} else {
				String titleYes = "\n>>>> 일반회원 전용 메뉴 [" + userCheck + "님 로그인중...]<<<<";
				System.out.println(titleYes);
				System.out.print(menu); // 사서 메인 메뉴 보여주기
				menuNum = sc.nextLine(); // 사서 메인 메뉴번호 선택하기
				System.out.println();
			}

			switch (menuNum) {
			case "1": // 일반 회원 가입

				if (userCheck == null) { // 아무도 로그인 되어있지 않고
					userSignUp(sc);
				} else { // 누군가가 로그인이 되어 있을때
					System.out.printf(">>>이미 %s님이 로그인 중이십니다!!!<<<\n\n로그아웃 하시고 가입을 해주세요.\n", userCheck);
				}

				break;
			case "2": // 로그인

				if (userCheck == null) { // 아무도 로그인 되어있지 않고
					userCheck = userLogin(sc);
				} else { // 누군가가 로그인이 되어 있을때
					System.out.printf(">>>이미 %s님이 로그인 중이십니다!!!<<<\n", userCheck);
				}
				break;
			case "3": // 로그아웃

				if (userCheck == null) { // 아무도 로그인을 하지 않았을때
					System.out.println(">>>로그인을 먼저 해주셔야 로그아웃을 할 수 있습니다!!!!<<<\n");

				} else { // 누군가가 로그인이 되어 있을때
					System.out.println(">>> " + userCheck + "님 로그아웃 되셨습니다!\n");
					userCheck = null;
				}
				break;
			case "4": // 도서 검색 하기
				if (userCheck == null) { // 아무도 로그인을 하지 않았을때
					System.out.println(">>>로그인을 먼저 해주셔야  이용할 수 있습니다!!!!<<<\n");

				} else { // 누군가가 로그인이 되어 있을때
					searchBook(sc);
				}
				break;
			case "5":
				if (userCheck == null) { // 아무도 로그인을 하지 않았을때
					System.out.println(">>>로그인을 먼저 해주셔야  이용할 수 있습니다!!!!<<<\n");

				} else { // 누군가가 로그인이 되어 있을때
					searchLoc(sc);
				}
				break;
			case "6": // 나의 대여 현황 보기
				if (userCheck == null) { // 아무도 로그인을 하지 않았을때
					System.out.println(">>>로그인을 먼저 해주셔야  이용할 수 있습니다!!!!<<<\n");

				} else { // 누군가가 로그인이 되어 있을때
					showMyState(userCheck);
				}
				break;
			case "7": // 이달의 도서 순위
				if (userCheck == null) { // 아무도 로그인을 하지 않았을때
					System.out.println(">>>로그인을 먼저 해주셔야  이용할 수 있습니다!!!!<<<\n");

				} else { // 누군가가 로그인이 되어 있을때
					bookOfThisMonth();
				}
				break;
			case "8": // 유저 탈퇴하기
				if (userCheck == null) { // 아무도 로그인을 하지 않았을때
					System.out.println(">>>로그인을 먼저 해주셔야  이용할 수 있습니다!!!!<<<\n");

				} else { // 누군가가 로그인이 되어 있을때
					userCheck = deleteUserId(sc, userCheck);
				}
				break;
			case "9": // 나가기

				System.out.print("로그인 상태일 경우 자동으로 회원 로그아웃 됩니다.[Y/N]\n => ");
				String yesno = sc.nextLine().trim();
				if ("y".equalsIgnoreCase(yesno)) {
					System.out.println("로그아웃 되셨습니다.\n");
					return;
				} else if ("n".equalsIgnoreCase(yesno)) {
					System.out.println("계속해서 서비스를 이용하실 수 있습니다.\n");
					continue;
				} else {
					System.out.println("계속해서 서비스를 이용합니다.\n");
					continue;
				}

			default:
				System.out.println(">>> 메뉴에 없는 번호 입니다. 다시 선택하세요!!\n");
				break;
			}// end of switch------------------------------------------------
		} while (true); // end of do-while-----------------------------------------------

//		}

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings({ "unchecked" })
	@Override
	public void searchBook(Scanner sc) {
		System.out.println(">>> 도서 검색 하기 <<<");
		System.out.println("[주의사항] 검색어를 입력하지 않고 엔터를 하면 검색 대상에서 제외됩니다.");

		List<EachBookDTO> answer = new ArrayList<>();
		List<EachBookDTO> tempList = new ArrayList<>();
		List<EachBookDTO> emptyList = new ArrayList<>();
		List<EachBookDTO> eachBookList = (ArrayList<EachBookDTO>) serial.getObjectFromFile(EACH_BOOK_FILE_NAME);

		if (eachBookList != null) {
			System.out.printf("▶ %s : ", "도서 분류 카테고리(Programming, DataBase)");
			String getCategory = sc.nextLine();
			if (!getCategory.trim().isEmpty()) {
				for (EachBookDTO book1 : eachBookList) {
					if (book1.getBookdto().getCategory().equalsIgnoreCase(getCategory)) {
//						System.out.println("123123");
						tempList.add(book1);
					}
				}
				answer = tempList;
				tempList = emptyList;
			} else {

				answer = eachBookList;
			}

			System.out.printf("▶ %s : ", "도서명");
			String getBookName = sc.nextLine();

			if (!getBookName.trim().isEmpty()) {
				for (EachBookDTO book1 : answer) {
					if (book1.getBookdto().getBookName().equalsIgnoreCase(getBookName)) {
						tempList.add(book1);
					}
				}
				answer = tempList;
				tempList = emptyList;
			}

			System.out.printf("▶ %s : ", "작가명");
			String getAutor = sc.nextLine();

			if (!getAutor.trim().isEmpty()) {
				for (EachBookDTO book1 : answer) {
					if (book1.getBookdto().getAuthor().equalsIgnoreCase(getAutor)) {
						tempList.add(book1);
					}
				}
				answer = tempList;
				tempList = emptyList;
			}

			System.out.printf("▶ %s : ", "출판사명");
			String getPublisher = sc.nextLine();

			if (!getPublisher.trim().isEmpty()) {
				for (EachBookDTO book1 : answer) {
					if (book1.getBookdto().getPublisher().equalsIgnoreCase(getPublisher)) {
						tempList.add(book1);
					}
				}
				answer = tempList;
				tempList = emptyList;
			}

		}
		System.out.println(
				"================================================================================================================================");
		System.out.println(
				"ISBN                 도서아이디                                      도서명       작가명        출판사        가격       대여상태");
		System.out.println(
				"================================================================================================================================");
		if (answer.isEmpty()) {
			System.out.println("검색에 일치하는 도서가 없습니다~~~~~~~~~~~~~~~~~~~~~~~~~");
		} else {
			for (EachBookDTO book : answer) {

				System.out.print(book);

			}
		}

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	@Override
	public void showMyState(String userName) {
		List<RentalTaskDTO> rentalList = (ArrayList<RentalTaskDTO>) serial.getObjectFromFile(RENT_LIST_FILE_NAME);

		System.out.println(
				"=================================================================================================================================================");
		System.out.println(
				"도서ID                    ISBN                 도서명        작가명       출판사      회원ID     대여일자          반납예정일");
		System.out.println(
				"=================================================================================================================================================");
		boolean flag = false;

		if (rentalList != null) {

			for (RentalTaskDTO rental : rentalList) {
				if (rental.getUserDto().getUserName().equals(userName)) {
					System.out.print(String.format("%s    %s    %s    %s    %s    %s    %s    %s\n", rental.getBookId(),
							rental.getEachBookDto().getIsbn(), rental.getEachBookDto().getBookdto().getBookName(),
							rental.getEachBookDto().getBookdto().getAuthor(),
							rental.getEachBookDto().getBookdto().getPublisher(), rental.getUserDto().getUserId(),
							rental.getRentalDate(), rental.getReturnDate()));
					flag = true;
				}
			}

		} else {
			System.out.println(">>>> 대여해가신 도서가 없습니데이~~ <<<<");
			return;

		}

		if (!flag) {
			System.out.println(">>>> 대여해가신 도서가 없습니데이~~ <<<<");
			return;
		}

	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkUserId(String userId) {
		boolean isUse = true;
		Object userListObj = serial.getObjectFromFile(USER_LIST_FILE_NAME);
		List<UserDTO> userList;

		if (userListObj != null) {
			userList = (List<UserDTO>) userListObj;
			for (UserDTO user : userList) {
				if (user.getUserId().equals(userId)) {
					isUse = false;
					break;
				} // end of if -------------------------------------
			} // end of for ------------------------------------
		} // end of if --------------------------------------
		return isUse;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	@Override
	public void userSignUp(Scanner sc) {
		String userPasswordTitle = "암호";
		String userNameTitle = "성명";
		String UserPhoneNumTitle = "연락처";

		String userId;
		do {
			System.out.print("▶ 회원 ID : ");
			userId = sc.nextLine().trim();

			if (userId.trim().isEmpty() || userId == null) {
				System.out.println("~~~ 아이디를 입력하세요!!\n");
			} else {

				if (!checkUserId(userId)) {
					System.out.println("~~~ " + userId + " 는 이미 존재하므로 다른  ID를 입력하세요!!\n");
				} else {
					break;
				}
			}

		} while (true); // end of do-while---------------------------------------

		String userPassword = isEmptyCheck(userPasswordTitle, sc);
		String userName = isEmptyCheck(userNameTitle, sc);
		String userPhoneNum = isEmptyCheck(UserPhoneNumTitle, sc);

		UserDTO userdto = new UserDTO(userId, userPassword, userName, userPhoneNum); // user 객체 생성

		File file = new File(USER_LIST_FILE_NAME); // 첫등록인지 아닌지를 확인하기 위해 파일 객체 생성

		List<UserDTO> userList = null;

		int n = 0;

		if (!file.exists()) {
			userList = new ArrayList<UserDTO>();
			userList.add(userdto);
		} else {
			userList = (ArrayList<UserDTO>) serial.getObjectFromFile(USER_LIST_FILE_NAME);
			userList.add(userdto);
		}
		n = serial.objectToFileSave(userList, USER_LIST_FILE_NAME);

		if (n == 1) {
			System.out.println(">>> 회원 등록 성공 <<<\n");
		} else {
			System.out.println(">>> 회원 등록 개실패!!ㅋㅋㅋㅋㅋ <<<\n");
		}

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public String isEmptyCheck(String title, Scanner sc) {
		String tempStr;
		do {
			System.out.printf("▶ %s : ", title);
			tempStr = sc.nextLine().trim();
			if (tempStr.isEmpty() || tempStr == null) {
				System.out.printf(">>> 빈칸이 있습니다! %s 을(를) 입력 해주세요. <<<\n\n", title);
			} else {
				break;
			}
		} while (true);
		return tempStr;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int isNumCheck(String title, Scanner sc) {
		String tempStr;
		int n = 0;
		do {
			System.out.printf("▶ %s : ", title);
			tempStr = sc.nextLine().trim();
			if (tempStr.isEmpty() || tempStr == null) {
				System.out.printf(">>> 빈칸이 있습니다! %s 을(를) 입력 해주세요. <<<\n\n", title);
			} else {
				if ("0".equals(tempStr)) {
					System.out.println(">>> 0 보다 큰 수를 입력해주셔야 합니다! <<<\n");
					continue;
				}
				try {
					n = Integer.parseInt(tempStr);
					break;
				} catch (Exception e) {
					System.out.printf(">>> 숫자로 입력해 주셔야 합니다!! %s 을(를) 숫자로 입력 해주세요. <<<\n\n", title);
				}
			}
		} while (true);
		return n;

	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	@Override
	public String userLogin(Scanner sc) {
		Object obj = serial.getObjectFromFile(USER_LIST_FILE_NAME);
		String getIdTitle = "회원 아이디";
		String getPasswordTitle = "암호";

		String getId = isEmptyCheck(getIdTitle, sc);
		String getPassword = isEmptyCheck(getPasswordTitle, sc);

		boolean flag = true;

		List<UserDTO> userList = null;
		if (obj != null) {
			userList = (List<UserDTO>) obj;

			for (UserDTO user : userList) {
				if (user.getUserId().equals(getId) && user.getUserPassword().equals(getPassword)) {
					System.out.println(">>>축하합니다! 로그인에 성공하셨어요~~!!!<<<\n");
					return user.getUserName();
				} else if (!(user.getUserId().equals(getId)) || !(user.getUserPassword().equals(getPassword))) {
					flag = false;
				}
			}

			if (!flag) {
				System.out.println(">>>로그인에 실패했으니까 아이디랑 비밀번호를 확인하면 좋겠어요!<<<\n");
			}
		}
		return null;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	@Override
	public String deleteUserId(Scanner sc, String userCheck) {
		List<RentalTaskDTO> rentalList = (ArrayList<RentalTaskDTO>) serial.getObjectFromFile(RENT_LIST_FILE_NAME);
		List<UserDTO> userList = (ArrayList<UserDTO>) serial.getObjectFromFile(USER_LIST_FILE_NAME);
		boolean flag = false;
		// 회원 삭제 창인것 알려주기
		System.out.println(">>>> 회원 탈퇴 <<<<");
		System.out.println("한번더 본인 확인을 위해 본인의 아이디를 입력해 주세요!\n");

		// 유저 아이디와 비밀번호 확인
		String getIdTitle = "회원 아이디";
		String getId = isEmptyCheck(getIdTitle, sc);

		for (UserDTO user : userList) {
			if (user.getUserId().equals(getId)) {
				System.out.println(">>> 회원 확인에 성공하셨습니다.! <<<\n");
				flag = true;
				break;
			}
		}

		if (!flag) {
			System.out.println("회원 확인에 실패하셨습니다! 메뉴로 돌아갑니다.");
			return userCheck;
		}

		// 현재 대여중인 책이 있는지 확인
		System.out.println("회원 탈퇴 전 현재 대여중인 책이 있는지 확인합니다.\n");
		int cnt = 0;
		for (RentalTaskDTO rent : rentalList) {
			if (rent.getUserDto().getUserId().equals(getId)) {
				cnt += 1;
			}
		}

		if (cnt == 0) {
			System.out.println("현재 대여중인 책이 없습니다. 회원 탈퇴 진행 가능합니다.");
		} else {
			System.out.println(">>>회원 탈퇴가 불가능 하십니다<<<");
			System.out.printf("현재 %s님은 %d권의 책이 미반납 상태에 있습니다. 반납을 진행해 주신후 회원 탈퇴를 해주세요.\n", userCheck, cnt);
			System.out.println("대여 상태를 확인하시려면 회원 전용 메뉴의 나의 대여 현황 보기를 확인해 주세요!!!!!!");
			return userCheck;
		}
		// 위의 조건들을 만족하면 회원을 탈퇴하시겠습니까 물어봄

		System.out.print("회원탈퇴를 원하시면 \"DELETE\"를 정확하게 입력해 주시기 바랍니다. \n => ");
		String delete = sc.nextLine().trim();
		int i = 0;
		int n = 0;
		if ("DELETE".equals(delete)) {
			for (i = 0; i < userList.size(); i++) {
				if (userList.get(i).getUserId().equals(getId)) {
					userList.remove(i);
					break;
				}
			}
			n = serial.objectToFileSave(rentalList, RENT_LIST_FILE_NAME);
			n = serial.objectToFileSave(userList, USER_LIST_FILE_NAME);
			if (n == 1) {

				System.out.println("회원 탈퇴가 완료됐습니다.\n!!!!!!자동으로 로그아웃 처리 됩니다!!!!!!!!\n");
				return null;
			} else {
				System.out.println("시스템의 문제로 인해 회원 탈퇴를 실패했습니다.. \n");
				return userCheck;
			}
		} else {
			System.out.println("계속해서 서비스를 이용합니다.\n");
			return userCheck;
		}

		// delete를 정확하게 입력하면 유저 삭제 성공

		//

	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	@Override
	public void userCheck() {
		List<RentalTaskDTO> rentalList = (ArrayList<RentalTaskDTO>) serial.getObjectFromFile(RENT_LIST_FILE_NAME);
		List<UserDTO> userList = (ArrayList<UserDTO>) serial.getObjectFromFile(USER_LIST_FILE_NAME);
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		String currentDay = simple.format(new Date());
		Date currentDate = null;
		Date returnDate = null;

		System.out.println(">>>> 전체 유저 확인!! <<<<");

		System.out.println(
				"================================================================================================================================");
		System.out.println("유저아이디             유저이름         유저전화번호       대여도서수    연체된책수    총연체료");
		System.out.println(
				"================================================================================================================================");

		if (userList != null) {

			for (UserDTO user : userList) {
				int bookCount = 0;
				int bookCountArrears = 0;
				int bookTotalArrears = 0;

				try {
					for (RentalTaskDTO rent : rentalList) {
						if (rent.getUserId().equals(user.getUserId())) {
							bookCount += 1;
							try {
								currentDate = simple.parse(currentDay);
								returnDate = simple.parse(rent.getReturnDate());
								long diff = currentDate.getTime() - returnDate.getTime();

								long timeDiff = diff / (24 * 60 * 60 * 1000);

								if (timeDiff >= 1) {
									bookCountArrears += 1;
									bookTotalArrears += ((int) timeDiff) * 100;
								}
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
					}
				} catch (Exception e) {

				}
				System.out.printf("%s        %s        %s    %d권    %d권    %d원\n", user.getUserId(), user.getUserName(),
						user.getUserPhoneNUm(), bookCount, bookCountArrears, bookTotalArrears);

			}

		} else {
			System.out.println("유저 정보가 없습니다~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	@Override
	public void deleteEachBook(Scanner sc) {
		List<RentalTaskDTO> rentalList = (ArrayList<RentalTaskDTO>) serial.getObjectFromFile(RENT_LIST_FILE_NAME);
		List<EachBookDTO> eachBookList = (ArrayList<EachBookDTO>) serial.getObjectFromFile(EACH_BOOK_FILE_NAME);
		String getBookIdTitle = "개별 도서 ID";

		System.out.println(">>>> 등록된 개별 도서 삭제 <<<<\n");
		String getBookId = isEmptyCheck(getBookIdTitle, sc);
		boolean flag = false;

		if (eachBookList != null) {
			int index = 0;
			for (EachBookDTO book : eachBookList) {
				if (book.getBookId().equals(getBookId)) {
					if (book.isRent() == false) {
						System.out.println(">>>>>> " + book.getBookdto().getBookName() + " 을(를) 삭제합니다 (도서 ID : "
								+ getBookId + ")\n");
						flag = true;
						break;
					} else {
						System.out.println(">>>> 현재 대여중인 도서 입니다!!!! 반납을 받고서 삭제를 진행해 주세요.\n");
						return;
					}
				}

				index += 1;
			}

			if (flag == false) {
				System.out.println(">>>> 해당 도서는 개별 도서 목록에 없습니다! 다시 확인하고 진행해 주세요\n");
				return;
			}

			eachBookList.remove(index);

			int n = serial.objectToFileSave(eachBookList, EACH_BOOK_FILE_NAME);
			n = serial.objectToFileSave(rentalList, RENT_LIST_FILE_NAME);

			if (n == 1) {
				System.out.println(">>>> 도서 삭제 완료!! <<<<\n");
			} else {
				System.out.println(">>>> 도서 삭제 개실패!!ㅋㅋㅋㅋㅋㅋㅋㅋ <<<<\n");

			}
		} else {
			System.out.println(">>>> 등록된 도서가 없습니다. 개별 도서등록을 먼저 진행해 주세요!\n");
			return;
		}
	}

	// 도서 위치 등록
	public void addloc(Scanner sc) {
		int n = 0;

		LocationDTO ldto;
		boolean flag = false;
		String bid = "";
		
		// 파일에서 EachBookDTO 객체를 불러온다.
		List<EachBookDTO> eachBookList = (ArrayList<EachBookDTO>) serial.getObjectFromFile(EACH_BOOK_FILE_NAME);
		EachBookDTO eachdto = new EachBookDTO();

		System.out.println(" === 도서 위치 등록 === ");

		// 도서 ID를 입력받는다.
		bid = isEmptyCheck("도서ID", sc);
		
		// 불러온 EachBookDTO 객체에서 입력한 도서ID와 일치하는 객체를 위에 생성한 eachdto에 저장해준다.
		for (int i = 0; i < eachBookList.size(); i++) {
			if (eachBookList.get(i).getBookId().equals(bid)) {
				eachdto = eachBookList.get(i);
				flag = true;
			}
		}
		// 입력한 도서id가 locationDTO에 들어있는지 확인
		boolean b = checklocid(bid);
		
		// 등록되어 있지 않다면 등록해준다.
		if (b) {
			// 도서 ID가 EachbookDTO에 등록되어 있는 유효한 ID일때
			if (flag) {
				// 도서위치의 서가명과 서가번호를 입력해준다.
				String locname = isEmptyCheck("서가명(ex. 컴퓨터/자연/공학)", sc);
				String loc = isEmptyCheck("서가번호(ex. A001)", sc);

				// 각각 입력받은 변수들을 LocationDTO 객체에 넣어준다.
				ldto = new LocationDTO(locname, loc, bid, eachdto);

				File file = new File(BOOKLOCATION_FILE_NAME);
				List<LocationDTO> locList = null;

				// 파일이 존재하지 않을때
				if (!file.exists()) {
					locList = new ArrayList<>();
					locList.add(ldto);
				} 
				// 파일이 존재할때
				else {
					locList = (ArrayList<LocationDTO>) serial.getObjectFromFile(BOOKLOCATION_FILE_NAME);
					locList.add(ldto);
				}
				
				n = serial.objectToFileSave(locList, BOOKLOCATION_FILE_NAME);

				if (n == 1) {
					System.out.println(">>> 도서위치 등록 성공 <<<\n");
				} else {
					System.out.println(">>> 도서위치 등록 실패<<<\n");
				}
			} 
			// 도서ID가 유효하지 않은 ID일때
			else {
				System.out.println("~~~ 존재하지 않는 도서ID입니다!!! ~~~");
			}
		} 
		// 등록되어 있다면 이미 등록되었다는 안내를 출력해준다.
		else {
			System.out.println("~~~ 해당 도서ID의 도서위치는 이미 등록되어 있습니다! ~~~");
		}
	}

	// 해당 도서 ID가 이미 LocationDTO에 등록되어 있는지 여부를 확인하기 위한 메서드
	public boolean checklocid(String id) {
		boolean flag = true;
		File file = new File(BOOKLOCATION_FILE_NAME);
		if (!file.exists()) {
			flag = true;
		} else {
			List<LocationDTO> locBookList = (ArrayList<LocationDTO>) serial.getObjectFromFile(BOOKLOCATION_FILE_NAME);
			for (int i = 0; i < locBookList.size(); i++) {
				if (locBookList.get(i).getBookid().equals(id)) {
					flag = false;
				}
			}
		}

		return flag;
	}

	//도서 위치 검색 메서드 
	public void searchLoc(Scanner sc) {

		System.out.println(" >>> 도서위치 검색 <<< ");
		// 도서 위치 검색을 위해 도서명을 입력받는다.
		String bname = isEmptyCheck("도서명", sc);
		ArrayList<LocationDTO> idtemp = new ArrayList<>();
		ArrayList<EachBookDTO> temp = new ArrayList<>();

		ArrayList<LocationDTO> locList = (ArrayList<LocationDTO>) serial.getObjectFromFile(BOOKLOCATION_FILE_NAME);
		ArrayList<EachBookDTO> eachList = (ArrayList<EachBookDTO>) serial.getObjectFromFile(EACH_BOOK_FILE_NAME);

		// 대여중과 비치중을 출력하기 위해 EachDTO에서 도서명이 같은 객체의 대여 상태를 temp리스트에 넣어준다.
		for (int i = 0; i < eachList.size(); i++) {
			if (eachList.get(i).getBookdto().getBookName().equals(bname)) {
				temp.add(eachList.get(i));
			}
		}

		// 입력받은 도서명과 EachBookDTO에서 비교해서 같은 도서명을 가진 객체를 idtemp리스트에 넣어준다.
		for (int i = 0; i < locList.size(); i++) {
			if (locList.get(i).getEachdto().getBookdto().getBookName().equals(bname)) {
				idtemp.add(locList.get(i));
			}
		}

		// 출력문
		System.out.println();
		System.out.println("=====================대여 가능한 '" + bname + "' 도서 위치 ====================");
		for (int i = 0; i < idtemp.size(); i++) {
			boolean state = temp.get(i).isRent();
			String str = "";
			if (state) {
				str = "대여중";
			} else {
				str = "비치중";
			}
			System.out.println("도서ID: " + idtemp.get(i).getBookid() + "   대여현황: " + str);
			System.out.println("서가명: " + idtemp.get(i).getLocname());
			System.out.println("서가번호: " + idtemp.get(i).getLoc());
			System.out.println();

		}
	}

	@SuppressWarnings("unchecked")
	   public void bookOfThisMonth() { // 이달의 도서
	      List<EachBookDTO> eachBookList = (ArrayList<EachBookDTO>) serial.getObjectFromFile(EACH_BOOK_FILE_NAME);
	      int[] countList = new int[eachBookList.size()];
	      int[] indexList = new int[3];
	      int numberCnt = 1;

	      System.out.println(">>>>> 이달의 도서!!!!!! <<<<<<");
	      if (eachBookList != null) {
	         for (int i = 0; i < countList.length; i++) {
	            countList[i] = eachBookList.get(i).getRankCount(); // 인트 배열 내부에 각각의 인덱스 번호마다 각각의 카운트 숫자를 받아오기

	         }
	      }
	      // 각각의 카운트 숫자를 통해서 가장 많이 빌린 책의 아이디를 받아오기
	      // 카운트 리스트에 있는 숫자들중에서 큰 숫자의 인덱스 별로 새로운 인트 리스트의 배열로 받아온다!??

	      int max = countList[0];
	      int maxIndex = 0;
	      for (int j = 0; j < indexList.length; j++) {
	         for (int k = 0; k < countList.length; k++) {
	            if (countList[k] >= max) {
	               max = countList[k];
	               maxIndex = k;

	            }
	            
	         }
	         if (max == 0) {
	            
	         }
	         
	         countList[maxIndex] = 0;
	         indexList[j] = maxIndex;
	         max = 0;
	         maxIndex = 0;
	      }
	      // 인덱스 리스트에 있는 첫번째 숫자의 번호부터 이치북 리스트의 해당 인덱스 번호의 책을 출력해주면 됩니다!
	      boolean flag;
	      int[] numCheck = new int[3];
	      int w = 0;
	      
	      for (int y : indexList) {
	         if (y >= max) {
	            max = y;
	         }
	      }
	      
//	      if (max == 0) {
//	         System.out.println(">>> 대여기록이 없기 때문에 순위가 없습니다!\n");
	         System.out.println(">>> 추천 리스트를 보여드리겠습니다!!!!!!\n");

	         do {

	            flag = true;;
	            
	            int temp = (int) ((Math.random() * eachBookList.size()) + 1);
	            
	            for (int t = 0; t < numCheck.length; t++) {
	               if (numCheck[t] == temp) {
	                  flag = false;
	               }
	            }
	            numCheck[w] = temp;
	            
	            if (flag == false) {
	               continue;
	            }
	            
	            System.out.println(numberCnt + ". 책이름 : " + eachBookList.get(numCheck[w] - 1).getBookdto().getBookName() + "\n책 ID : " + eachBookList.get(numCheck[w] - 1).getBookId() + "\n");
	            numberCnt += 1;

	            w += 1;
	         } while (w < numCheck.length);
//	      } else {
//	         for (int num : indexList) {
//	            System.out.println(numberCnt + ". 책이름 : " + eachBookList.get(num).getBookdto().getBookName() + "\n     책 ID : " + eachBookList.get(num).getBookId()
//	                  + "\n");
//	            numberCnt++;
//	         }
//	      }

	   }
	
}
