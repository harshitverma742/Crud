package com.ntt.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.ntt.model.Employee;

public class EmployeeDAO {
	
	private String jdbcURL="jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername="root";
	private String jdbcPassword="root";
	
	private static final String INSERT_USERS_SQL="INSERT INTO employee123" +" 	(employeename,address,dateofjoining,experience,dateofbirth) VALUES"
	+"(?,?,?,?,?);";
	
	private static final String SELECT_USER_BY_ID="select id,employeename,address,dateofjoining,experience,dateofbirth from employee123 where id=?";
	private static final String SELECT_ALL_USERS="select * from employee123";
	private static final String UPDATE_USERS_SQL="update employee123 set employeename=?,address=?,dateofjoining=?,experience=?,dateofbirth=? where id=?;";
	private static final String DELETE_USERS_SQL="delete from employee123 where id=?";
	
	protected Connection getConnection(){
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
		}catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	//create user
	
	public void insertEmployee(Employee employee)throws SQLException{
	try(Connection connection=getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(INSERT_USERS_SQL)){
		preparedStatement.setString(1,employee.getEmployeename());
		preparedStatement.setString(2,employee.getAddress());
		preparedStatement.setString(3,employee.getDateofjoining());
		preparedStatement.setInt(4,employee.getExperience());
		preparedStatement.setString(5,employee.getDateofbirth());
		System.out.println(preparedStatement);
		preparedStatement.executeUpdate();
	
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	public boolean updateEmployee(Employee employee) throws SQLException{
		boolean rowUpdated;
		try(Connection connection=getConnection();
				PreparedStatement statement=connection.prepareStatement(UPDATE_USERS_SQL);){
			statement.setString(1,employee.getEmployeename());
			statement.setString(2,employee.getAddress());
			statement.setString(3,employee.getDateofjoining());
			statement.setInt(4,employee.getExperience());
			statement.setString(5,employee.getDateofbirth());
			statement.setInt(6,employee.getId());
			
			rowUpdated=statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	

	public Employee selectEmployee(int id) {
	Employee employee=null;
	
	try(Connection connection =getConnection();
			PreparedStatement preparedStatement= connection.prepareStatement(SELECT_USER_BY_ID);){
		preparedStatement.setInt(1,id);
		System.out.println(preparedStatement);
		
		ResultSet rs=preparedStatement.executeQuery();
		while(rs.next()) {
			String employeename=rs.getString("employeename");
			String address=rs.getString("address");
			
			String dateofjoining=rs.getString("dateofjoining");
			int experience=rs.getInt("experience");
			String dateofbirth=rs.getString("dateofbirth");
			employee=new Employee(id,employeename,address,dateofjoining,experience,dateofbirth);
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return employee;
}
	public List<Employee> selectAllEmployees() {
		List<Employee> employees=new ArrayList<>();
		
		try(Connection connection =getConnection();
				PreparedStatement preparedStatement= connection.prepareStatement(SELECT_ALL_USERS);){
			
			System.out.println(preparedStatement);
			
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				
				String employeename=rs.getString("employeename");
				String address=rs.getString("address");
				String dateofjoining=rs.getString("dateofjoining");
				int experience=rs.getInt("experience");
				String dateofbirth=rs.getString("dateofbirth");
				employees.add(new Employee(id,employeename,address,dateofjoining,experience,dateofbirth));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}
	
	public boolean deleteEmployee(int id) throws SQLException{
		boolean rowDeleted;
		try(Connection connection =getConnection();
				PreparedStatement statement=connection.prepareStatement(DELETE_USERS_SQL);){
			statement.setInt(1, id);
			rowDeleted=statement.executeUpdate()> 0;
		}
		return rowDeleted;
		
	}
}
