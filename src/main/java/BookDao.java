import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class BookDao {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	public BookDao(String jdbcURL,String jdbcUsername,String jdbcPassword) {
		this.jdbcURL=jdbcURL;
		this.jdbcUsername=jdbcUsername;
		this.jdbcPassword=jdbcPassword;
	}
	protected void connect() throws SQLException{
		if(jdbcConnection==null || jdbcConnection.isClosed() ) {
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			}catch(ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection=DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
		}
	}
	protected void disconnect() throws SQLException{
		if(jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	public boolean insertBook(Book book) throws SQLException{
		connect();
		PreparedStatement state=jdbcConnection.prepareStatement("Insert into book(title,author,price) values(?,?,?)");
		state.setString(1, book.getTitle());
		state.setString(2, book.getAuthor());
		state.setFloat(3, book.getPrice());
		boolean update=state.executeUpdate() > 0;
		state.close();
		disconnect();
		return update;
	}
	public List<Book> listAllBooks() throws SQLException{
		connect();
		List<Book> listBooks=new ArrayList<>();
		Statement state=jdbcConnection.createStatement();
		ResultSet res=state.executeQuery("Select * from book");
		while(res.next()) {
			int id=res.getInt(1);
			String title=res.getString(2);
			String author=res.getString(3);
			float price=res.getFloat(4);
			Book book=new Book(id,title,author,price);
			listBooks.add(book);
		}
		state.close();
		res.close();
		disconnect();
		return listBooks;
	}
	public Book getBook(int id) throws SQLException{
		Book book=null;
		connect();
		PreparedStatement state=jdbcConnection.prepareStatement("Select * from book where id=?");
		state.setInt(1, id);
		ResultSet res=state.executeQuery();
		if(res.next()) {
//			System.out.println("yes");
			String title=res.getString(2);
			String author=res.getString(3);
			float price=res.getFloat(4);
			book=new Book(id,title,author,price);
		}
//		System.out.println("no");
		state.close();
		res.close();
		disconnect();
		return book;
	}
	public boolean updateBook(Book book) throws SQLException{
		connect();
		PreparedStatement state=jdbcConnection.prepareStatement("Update book set title=?,author=?,price=? where id=?");
		state.setString(1, book.getTitle());
		state.setString(2, book.getAuthor());
		state.setFloat(3, book.getPrice());
		state.setInt(4, book.getId());
//		System.out.println("update "+book.getAuthor()+" "+book.getId());
		boolean update=state.executeUpdate() > 0;
		state.close();
		disconnect();
		return update;
	}
	public boolean deleteBook(int id) throws SQLException{
		connect();
		PreparedStatement state=jdbcConnection.prepareStatement("Delete from book where id=?");
		state.setInt(1, id);
		boolean update=state.executeUpdate() > 0;
		state.close();
		disconnect();
		return update;
	}
}
