package management1;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RentalTaskDTO implements Serializable {

	private static final long serialVersionUID = 5510118491723194854L;
	private int rentaltastseq;
	private String userId;
	private String bookId;
	private String rentalDate;
	private String returnDate;
	private int arrears;
	private EachBookDTO eachBookDto;
	private UserDTO userDto;

	public RentalTaskDTO() {
	}

	public RentalTaskDTO(String userId, String bookId, UserDTO userDto, EachBookDTO eachBookDto) {
		this.userId = userId;
		this.bookId = bookId;
		this.userDto = userDto;
		this.eachBookDto = eachBookDto;

	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate() {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_YEAR, -2);
		
		rentalDate = simple.format(cal.getTime());
		
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate() {
		try {
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
			
			Date returnDate = simple.parse(rentalDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(returnDate);
			cal.add(Calendar.DAY_OF_YEAR, 3);
			
			this.returnDate = simple.format(cal.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getArrears() {
		return arrears;
	}

	public void setArrears() {
		
		try {
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
			String currentDay = simple.format(new Date());
			
			Date currentDate = simple.parse(currentDay);
			Date returnDate;
			returnDate = simple.parse(this.returnDate);
			long diff = currentDate.getTime() - returnDate.getTime();
			
			long timeDiff = diff/(24*60*60*1000);
			
			if (timeDiff >= 1) {
				arrears = ((int)timeDiff) * 100;
			} else {
				arrears = 0;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
//		try {
//			 SimpleDateFormat sdfmt = new SimpleDateFormat("yyyy-MM-dd"); 
//			 String currentDay = sdfmt.format(new Date());
//			
//			 Date currentDate = sdfmt.parse(currentDay);
//			 Date scheduledReturnDate = sdfmt.parse(returnDate);
//			
//			 long diff = currentDate.getTime() - scheduledReturnDate.getTime();
//			 // Date 객체의 getTime()은 1970-01-01 00:00:00 으로 부터 경과한 밀리초임.
//			 // currentDate.getTime()은 1970-01-01 00:00:00 으로 부터 현재일 까지 경과한 밀리초임. 
//			 // scheduledReturnDate.getTime()은 1970-01-01 00:00:00 으로 부터 반납예정일 까지 경과한 밀리초임.
//	         
//			 // 시간차이를 시간,분,초, 1000을 곱한값으로 나누면 하루단위가 나온다.
//	         long diffDays = diff/(24*60*60*1000);
//			 
//	         if(diffDays >= 1) {
//	        	 arrears = ((int)diffDays) * 100;
//	         } else {
//	        	 arrears = 0;
//	         }
//			
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		
	}

	public EachBookDTO getEachBookDto() {
		return eachBookDto;
	}

	public void setEachBookDto(EachBookDTO eachBookDto) {
		this.eachBookDto = eachBookDto;
	}

	public UserDTO getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}
	
	@Override
	public String toString() {
		
		return String.format("%s    %s        %s        %s        %s        %s      %s     %s      %s     %s\n",
				bookId, eachBookDto.getIsbn(), eachBookDto.getBookdto().getBookName(),eachBookDto.getBookdto().getAuthor(), eachBookDto.getBookdto().getPublisher(), userDto.getUserId(), userDto.getUserName(), userDto.getUserPhoneNUm(), rentalDate, returnDate);
	}

	public int getRentaltastseq() {
		return rentaltastseq;
	}

	public void setRentaltastseq(int rentaltastseq) {
		this.rentaltastseq = rentaltastseq;
	}

}
