
public class Book {
	protected int id;
	protected String title;
	protected String author;
	protected float price;
	public Book(int id) {
		this.id=id;
	}
	public Book(int id,String title,String author,float price) {
		this(title,author,price);
		this.id=id;
	}
	public Book(String title,String author,float price) {
		this.title=title;
		this.author=author;
		this.price=price;
	}
	public void setId(int id) {
		this.id=id;
	}
	public int getId() {
		return id;
	}
	public void setTitle(String title) {
		this.title=title;
	}
	public String getTitle() {
		return title;
	}
	public void setAuthor(String author) {
		this.author=author;
	}
	public String getAuthor() {
		return author;
	}
	public void setPrice(float price) {
		this.price=price;
	}
	public float getPrice() {
		return price;
	}
}
