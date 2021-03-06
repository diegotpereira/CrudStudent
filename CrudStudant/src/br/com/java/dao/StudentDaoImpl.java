package br.com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.java.model.Student;

public class StudentDaoImpl extends DB implements StudentDao {


	@Override
	public void saveStudent(Student stud) throws Exception {

		Connection conn = getDBConnection();

		String sql = "INSERT INTO student (name, rollNo, phoneNo, gender, email, address, collegeName) VALUES (?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement stat = conn.prepareStatement(sql);
		stat.setString(1, stud.getName());
		stat.setString(2, stud.getRollNo());
		stat.setString(3, stud.getPhoneNo());
		stat.setString(4, stud.getGender());
		stat.setString(5, stud.getEmail());
		stat.setString(6, stud.getAddress());
		stat.setString(7, stud.getCollegeName());

		stat.executeUpdate(); // execute query

		conn.close();

	}

	@Override
	public List<Student> getAllStudents() throws Exception {

		List<Student> studList = new ArrayList<Student>();

		Connection conn = getDBConnection();
		Statement stat = conn.createStatement();

		ResultSet rs = stat.executeQuery("SELECT * FROM student");
		while (rs.next()) {
			// `name`, `roll_no`, `phone_no`, `gender`, `email`, `address`,
			// `college_name`

			Student stud = new Student();
			stud.setId(rs.getLong("id"));
			stud.setName(rs.getString("name"));
			stud.setRollNo(rs.getString("rollNo"));
			stud.setPhoneNo(rs.getString("phoneNo"));
			stud.setGender(rs.getString("gender"));
			stud.setEmail(rs.getString("email"));
			stud.setAddress(rs.getString("address"));
			stud.setCollegeName(rs.getString("collegeName"));

			studList.add(stud);
		}

		conn.close();
		return studList;
	}

	@Override
	public void delete(Long id) throws Exception {
		Connection conn = getDBConnection();
		Statement stat = conn.createStatement();
		stat.executeUpdate("DELETE FROM student where id = " + id);
		conn.close();
	}

	@Override
	public void updateStudent(Student stud) throws Exception {
		Connection conn = getDBConnection();

		String sql = "UPDATE student SET name = ? , rollNo = ? , phoneNo = ? , gender = ? , email = ?, address = ? , collegeName = ? WHERE id = ?";

		PreparedStatement stat = conn.prepareStatement(sql);
		stat.setString(1, stud.getName());
		stat.setString(2, stud.getRollNo());
		stat.setString(3, stud.getPhoneNo());
		stat.setString(4, stud.getGender());
		stat.setString(5, stud.getEmail());
		stat.setString(6, stud.getAddress());
		stat.setString(7, stud.getCollegeName());
		stat.setLong(8, stud.getId());

		stat.executeUpdate(); // execute query

		conn.close();
	}

}
