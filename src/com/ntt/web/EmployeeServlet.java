package com.ntt.web;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ntt.dao.EmployeeDAO;
import com.ntt.model.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDAO employeeDAO;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
    	this.employeeDAO=new EmployeeDAO();
        
    }
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getServletPath();
		
		switch(action) {
		case "/new":
			showNewForm(request,response);
			break;
			
		case "/insert":
			
			try {
				insertUser(request,response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			break;
		case "/delete":
			try {
				deleteUser(request,response);
			} catch ( IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/edit":
			
			try {
				showEditForm(request,response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/update":
			try {
				updateUser(request,response);
			} catch (ServletException | IOException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			break;
			default:
			try {
				listEmployee(request,response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
		}
		
	}
	
	private void listEmployee(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException, SQLException{
		List<Employee> listEmployee=employeeDAO.selectAllEmployees();
		request.setAttribute("listEmployee",listEmployee);
		RequestDispatcher dispatcher=request.getRequestDispatcher("employee-list.jsp");
		dispatcher.forward(request,response);
	}
	
	private void deleteUser(HttpServletRequest request,HttpServletResponse response)throws IOException, SQLException{
		int id=Integer.parseInt(request.getParameter("id"));
		employeeDAO.deleteEmployee(id);
		response.sendRedirect("list");
	}
	private void updateUser(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException, SQLException{
		int id=Integer.parseInt(request.getParameter("id"));
		String employeename=request.getParameter("employeename");
		String address=request.getParameter("address");
		String dateofjoining=request.getParameter("dateofjoining");
		int experience=Integer.parseInt(request.getParameter("experience"));
		String dateofbirth=request.getParameter("dateofbirth");
		
		Employee employee=new Employee(id,employeename,address,dateofjoining,experience,dateofbirth); 
		employeeDAO.updateEmployee(employee);
		response.sendRedirect("list");
		
	}
	
	private void showEditForm(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException, SQLException{
		int id=Integer.parseInt(request.getParameter("id"));
		Employee existingEmployee=employeeDAO.selectEmployee(id);
		RequestDispatcher dispatcher=request.getRequestDispatcher("employee-form.jsp");
		request.setAttribute("employee",existingEmployee);
		dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		RequestDispatcher dispatcher=request.getRequestDispatcher("employee-form.jsp");
		dispatcher.forward(request,response);
		
	}
	
	private void insertUser(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException, SQLException{
		String employeename=request.getParameter("employeename");
		String address=request.getParameter("address");
		String dateofjoining=request.getParameter("dateofjoining");
		int experience=Integer.parseInt(request.getParameter("experience"));
		String dateofbirth=request.getParameter("dateofbirth");
		
		Employee newEmployee=new Employee(employeename,address,dateofjoining,experience,dateofbirth);
		employeeDAO.insertEmployee(newEmployee);
		response.sendRedirect("list");
	}

	
}
