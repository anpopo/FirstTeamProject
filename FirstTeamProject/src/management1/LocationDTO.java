package management1;

import java.io.Serializable;

public class LocationDTO implements Serializable {

	private static final long serialVersionUID = 1610915541194171747L;
	private int locseq;
	private String locname;	//서가명
	private String loc;	//서가번호
	private String bookid;	//도서ID
	private EachBookDTO eachdto;
	
	public LocationDTO() { }
	
	public LocationDTO(String locname, String loc, String bookid, EachBookDTO eachdto) {
		super();
		this.locname = locname;
		this.loc = loc;
		this.bookid = bookid;
		this.eachdto = eachdto;
	}

	public String getLocname() {
		return locname;
	}

	public void setLocname(String locname) {
		this.locname = locname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getBookid() {
		return bookid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
	}

	public EachBookDTO getEachdto() {
		return eachdto;
	}

	public void setEachdto(EachBookDTO eachdto) {
		this.eachdto = eachdto;
	}

	public int getLocseq() {
		return locseq;
	}

	public void setLocseq(int locseq) {
		this.locseq = locseq;
	}
}
