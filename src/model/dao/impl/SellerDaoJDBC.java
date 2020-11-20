package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	private Connection connection;
	public SellerDaoJDBC(Connection connection){
		this.connection = connection;
	}
		
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer idInteger) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"WHERE seller.Id = ?");
			statement.setInt(1, idInteger);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				// exists
				Department department = new Department();
				department.setIdInteger(resultSet.getInt("DepartmentId"));
				department.setNameString(resultSet.getString("DepName"));
				
				Seller seller = new Seller();
				seller.setIdInteger(resultSet.getInt("Id"));
				seller.setNameString(resultSet.getString("Name"));
				seller.setEmailString(resultSet.getString("Email"));
				seller.setBaseSalaryDouble(resultSet.getDouble("BaseSalary"));
				seller.setBirthDate(resultSet.getDate("BirthDate"));
				seller.setDepartment(department);
				return seller;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatements(statement);
			DB.closeResultSet(resultSet);
		}
		
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
