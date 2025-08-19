import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/")
public class ControllingServlet extends HttpServlet{
	private static final long serialVersionUID=1l;
	private BookDao bookdao;
	public void init() {
		String url=System.getenv("JDBC_URL");
		String username=System.getenv("JDBC_USERNAME");
		String password=System.getenv("JDBC_PASSWORD");
		bookdao=new BookDao(url,username,password);
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		 String action=request.getServletPath();
		 try {
		 switch(action) {
		 	case "/insert":
		 		insertTheBook(request,response);
		 		break;
		 	case "/update":
		 		updateTheBook(request,response);
		 		break;
		 	default:
		 		listBooks(request,response);
		 		break;
		 }
		 }catch(Exception e) {
			 throw new ServletException(e);
		 }
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String action = request.getServletPath();
		try {
		switch(action) {
			case "/add":
				showAddBookForm(request,response);
				break;
		 	case "/edit":
		 		showEditBookForm(request,response);
		 		break;
		 	case "/delete":
		 		deleteTheBook(request,response);
		 		break;
			default:
				listBooks(request,response);
				break;
		}
		}catch(Exception e) {
			throw new ServletException(e);
		}
	}
	protected void showAddBookForm(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		RequestDispatcher rd=request.getRequestDispatcher("bookForm.jsp");
		rd.forward(request, response);
	}
	protected void showEditBookForm(HttpServletRequest request,HttpServletResponse response) throws SQLException,ServletException,IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		Book existingbook=bookdao.getBook(id);
		request.setAttribute("book", existingbook);
//		System.out.println(existingbook.getTitle());
		RequestDispatcher rd=request.getRequestDispatcher("bookForm.jsp");
		rd.forward(request, response);
	}
	protected void listBooks(HttpServletRequest request,HttpServletResponse response) throws SQLException,ServletException,IOException{
		List<Book> books=bookdao.listAllBooks();
		request.setAttribute("listBooks", books);
		RequestDispatcher rd=request.getRequestDispatcher("books.jsp");
//		System.out.println(books.get(0).getId());
		rd.forward(request, response);
	}
	protected void updateTheBook(HttpServletRequest request,HttpServletResponse response) throws SQLException,ServletException,IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		String title=request.getParameter("title");
		String author=request.getParameter("author");
		float price=Float.parseFloat(request.getParameter("price"));
		bookdao.updateBook(new Book(id,title,author,price));
//		System.out.println(author+" "+ans);
		RequestDispatcher rd=request.getRequestDispatcher("books.jsp");
		rd.forward(request, response);
	}
	protected void insertTheBook(HttpServletRequest request,HttpServletResponse response) throws SQLException,ServletException,IOException{
		String title=request.getParameter("title");
		String author=request.getParameter("author");
		Float price=Float.parseFloat(request.getParameter("price"));
		bookdao.insertBook(new Book(title,author,price));
		RequestDispatcher rd=request.getRequestDispatcher("books.jsp");
		rd.forward(request,response);
	}
	protected void deleteTheBook(HttpServletRequest request,HttpServletResponse response) throws SQLException,ServletException,IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		bookdao.deleteBook(id);
		RequestDispatcher rd=request.getRequestDispatcher("books.jsp");
		rd.forward(request,response);
	}
}
