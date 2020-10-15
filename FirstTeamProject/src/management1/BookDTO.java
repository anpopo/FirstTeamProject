package management1;

import java.io.Serializable;

public class BookDTO implements Serializable {

	private static final long serialVersionUID = -6788995224883396771L;
	private int bookseq;
	private String isbn;
	private String category;
	private String bookName;
	private String Author;
	private String Publisher;
	private int price;
	private int status;
	private EachBookDTO eachBookDTO;

	public BookDTO() {
	}

	public BookDTO(String isbn, String category, String bookName, String Author, String publisher, int price) {
		this.isbn = isbn;
		this.category = category;
		this.bookName = bookName;
		this.Author = Author;
		this.Publisher = publisher;
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String bookAuthor) {
		this.Author = bookAuthor;
	}

	public String getPublisher() {
		return Publisher;
	}

	public void setPublisher(String publisher) {
		Publisher = publisher;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public EachBookDTO getEachBookDTO() {
		return eachBookDTO;
	}

	public void setEachBookDTO(EachBookDTO eachBookDTO) {
		this.eachBookDTO = eachBookDTO;
	}

	public int getBookseq() {
		return bookseq;
	}

	public void setBookseq(int bookseq) {
		this.bookseq = bookseq;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
