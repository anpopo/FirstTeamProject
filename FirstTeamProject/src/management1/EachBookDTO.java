package management1;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EachBookDTO implements Serializable{
   
   private static final long serialVersionUID = -8339372111875000067L;
   private int eachbookseq;
   private String isbn;
   private String bookId;
   private boolean isRent;  // false 일때는 책이 비치상태, true 일때는 책이 대여 상태
   private BookDTO   bookdto;
   private int rankCount;
   
   
   public EachBookDTO() {}

   public EachBookDTO(String isbn, String bookId, BookDTO bookdto) {
      this.isbn = isbn;
      this.bookId = bookId;
      this.bookdto = bookdto;
      
   }

   public String getIsbn() {
      return isbn;
   }

   public void setIsbn(String isbn) {
      this.isbn = isbn;
   }

   public String getBookId() {
      return bookId;
   }

   public void setBookId(String bookId) {
      this.bookId = bookId;
   }

   public boolean isRent() {
      return isRent;
   }

   public void setRent(boolean isRent) {
      this.isRent = isRent;
   }

   public BookDTO getBookdto() {
      return bookdto;
   }

   public void setBookdto(BookDTO bookdto) {
      this.bookdto = bookdto;
   }
   
   
   public int getRankCount() {
      return rankCount;
   }

   public void setRankCount() {
      SimpleDateFormat simple = new SimpleDateFormat("dd");
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.DATE, 0);
      
      String todayDate = simple.format(cal.getTime());
      
      if (todayDate.equals("01")) {
         rankCount = 0;
         rankCount += 1;
      } else {
         rankCount += 1;
      }
      
   }

   String rentCondition(boolean isRent) {
      String isCheckRent = "";
      if (isRent == true) {
         isCheckRent = "대여중";
      } else if (isRent == false){
         isCheckRent = "비치중";
      }
      return isCheckRent;
      
   }
   
   @Override
   public String toString() {
      
      return String.format("%s    %s        %s        %s        %s        %,d원        %s\n", isbn, bookId, bookdto.getBookName(), bookdto.getAuthor(), bookdto.getPublisher(), bookdto.getPrice(), rentCondition(isRent));
   }

public int getEachbookseq() {
	return eachbookseq;
}

public void setEachbookseq(int eachbookseq) {
	this.eachbookseq = eachbookseq;
}
   
   
}