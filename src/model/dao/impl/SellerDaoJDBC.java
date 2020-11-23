package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection connection;

	public SellerDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) " + "VALUES " + "(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, obj.getNameString());
			statement.setString(2, obj.getEmailString());
			statement.setTimestamp(3, new java.sql.Timestamp(obj.getBirthDate().getTime()));
			statement.setDouble(4, obj.getBaseSalaryDouble());
			statement.setInt(5, obj.getDepartment().getIdInteger());

			int rowAffecteds = statement.executeUpdate();
			if (rowAffecteds > 0) {
				ResultSet rSet = statement.getGeneratedKeys();
				if (rSet.next()) {
					// existe
					int id = rSet.getInt(1);
					obj.setIdInteger(id);
					DB.closeConnetion();
				}
			} else {
				throw new DbException("Unexpected error no line affected!");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatements(statement);
		}

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(
					"UPDATE seller "
					+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? " 
					+ "WHERE Id = ?", 
					Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, obj.getNameString());
			statement.setString(2, obj.getEmailString());
			statement.setTimestamp(3, new java.sql.Timestamp(obj.getBirthDate().getTime()));
			statement.setDouble(4, obj.getBaseSalaryDouble());
			statement.setInt(5, obj.getDepartment().getIdInteger());
			statement.setInt(6, obj.getIdInteger());

			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatements(statement);
		}

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
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?");
			statement.setInt(1, idInteger);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				// exists
				Department department = instantiateDepartment(resultSet);

				Seller seller = instantiateSeller(resultSet, department);

				return seller;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatements(statement);
			DB.closeResultSet(resultSet);
		}

	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "ORDER BY Name;");

			resultSet = statement.executeQuery();
			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<Integer, Department>();

			while (resultSet.next()) {
				// exists department?
				Department dep = map.get(resultSet.getInt("DepartmentId"));
				if (dep == null) {
					dep = instantiateDepartment(resultSet);
					map.put(resultSet.getInt("DepartmentId"), dep);
				} // terminar outro dia
				Seller seller = instantiateSeller(resultSet, dep);
				list.add(seller);

			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatements(statement);
			DB.closeResultSet(resultSet);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE DepartmentId = ? " + "ORDER BY Name;");
			statement.setInt(1, department.getIdInteger());
			resultSet = statement.executeQuery();
			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<Integer, Department>();

			while (resultSet.next()) {
				// exists department?
				Department dep = map.get(resultSet.getInt("DepartmentId"));
				if (dep == null) {
					dep = instantiateDepartment(resultSet);
					map.put(resultSet.getInt("DepartmentId"), dep);
				} // terminar outro dia
				Seller seller = instantiateSeller(resultSet, dep);
				list.add(seller);

			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatements(statement);
			DB.closeResultSet(resultSet);
		}
	}

	// private methods
	private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
		Department department = new Department();
		department.setIdInteger(resultSet.getInt("DepartmentId"));
		department.setNameString(resultSet.getString("DepName"));
		return department;
	}

	private Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {
		Seller seller = new Seller();
		seller.setIdInteger(resultSet.getInt("Id"));
		seller.setNameString(resultSet.getString("Name"));
		seller.setEmailString(resultSet.getString("Email"));
		seller.setBaseSalaryDouble(resultSet.getDouble("BaseSalary"));
		seller.setBirthDate(resultSet.getDate("BirthDate"));
		seller.setDepartment(department);
		return seller;
	}

}
