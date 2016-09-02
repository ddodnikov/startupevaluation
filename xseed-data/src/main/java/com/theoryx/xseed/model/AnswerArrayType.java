package com.theoryx.xseed.model;

import java.io.Serializable;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

public class AnswerArrayType implements UserType {

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.ARRAY };
	}

	@Override
	public Class<AnswerOption[]> returnedClass() {
		return AnswerOption[].class;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == null) {
			return y == null;
		} else {
			return x.equals(y);
		}
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		if (x != null)
			return x.hashCode();
		else
			return 0;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		Connection connection = session.connection();
		Array array = rs.getArray(names[0]);
		if (array == null) {
			return null;
		} else {
			Integer[] ids = (Integer[]) array.getArray();
			AnswerOption[] answers = new AnswerOption[ids.length];
			for (int i = 0; i < ids.length; i++) {
				answers[i] = select(ids[i], connection);
			}
			return answers;
		}
	}

	private AnswerOption select(Integer id, Connection connection) {
		AnswerOption answer = new AnswerOption();
		String sql = "SELECT id, identifier, text, group_id FROM answer_options WHERE id=?";
		try (PreparedStatement pr = connection.prepareStatement(sql)) {
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			while (rs.next()) {
				int selectedId = rs.getInt("id");
				String selectedIdentifier = rs.getString("identifier");
				String selectedText = rs.getString("text");
				int group_id = rs.getInt("group_id");
				AnswerGroup answerGroup = new AnswerGroup();
				String sql1 = "SELECT id, identifier, name, type FROM answer_groups WHERE id=?";
				try (PreparedStatement pr1 = connection.prepareStatement(sql1)) {
					pr1.setInt(1, group_id);
					ResultSet rs1 = pr1.executeQuery();
					while (rs1.next()) {
						answerGroup.setId(rs1.getInt("id"));
						answerGroup.setIdentifier(rs1.getString("identifier"));
						answerGroup.setName(rs1.getString("name"));
						QuestionAnswerType qat = QuestionAnswerType.valueOf(rs1.getString("type"));
						answerGroup.setType(qat);
						break;
					}
				}
				answer.setId(selectedId);
				answer.setIdentifier(selectedIdentifier);
				answer.setText(selectedText);
				answer.setGroup(answerGroup);
				break;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return answer;
	}

	private AnswerOption selectByIdentifier(AnswerOption answer, Connection connection) {
		String sql = "SELECT id, identifier, text, group_id FROM answer_options WHERE identifier=?";
		try (PreparedStatement pr = connection.prepareStatement(sql)) {
			pr.setString(1, answer.getIdentifier());
			ResultSet rs = pr.executeQuery();
			while (rs.next()) {
				int selectedId = rs.getInt("id");
				String selectedIdentifier = rs.getString("identifier");
				String selectedText = rs.getString("text");
				int group_id = rs.getInt("group_id");
				AnswerGroup answerGroup = new AnswerGroup();
				String sql1 = "SELECT id, identifier, name, type FROM answer_groups WHERE id=?";
				try (PreparedStatement pr1 = connection.prepareStatement(sql1)) {
					pr1.setInt(1, group_id);
					ResultSet rs1 = pr1.executeQuery();
					while (rs1.next()) {
						answerGroup.setId(rs1.getInt("id"));
						answerGroup.setIdentifier(rs1.getString("identifier"));
						answerGroup.setName(rs1.getString("name"));
						QuestionAnswerType qat = QuestionAnswerType.valueOf(rs1.getString("type"));
						answerGroup.setType(qat);
						break;
					}
				}
				answer.setId(selectedId);
				answer.setIdentifier(selectedIdentifier);
				answer.setText(selectedText);
				answer.setGroup(answerGroup);
				break;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return answer;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		Connection connection = st.getConnection();
		AnswerOption[] castObject = (AnswerOption[]) value;
		Integer[] ids;
		if (castObject == null) {
			ids = new Integer[0];
		} else {
			ids = new Integer[castObject.length];
			for (int i = 0; i < castObject.length; i++) {
				if (castObject[i].getId() != null) {
					ids[i] = castObject[i].getId();
				} else {
					AnswerOption option = selectByIdentifier(castObject[i], connection);
					if (option == null) {
						option = insert(castObject[i], connection);
					}
					ids[i] = option.getId();
				}
			}
		}
		Array array = connection.createArrayOf("integer", ids);
		st.setArray(index, array);
	}

	private AnswerOption insert(AnswerOption option, Connection connection) {
		String sql = "IMSERT INTO answer_options(identifier, text, group_id) VALUES(?,?,?)";
		try (PreparedStatement pr = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pr.setString(1, option.getIdentifier());
			pr.setString(2, option.getText());
			pr.setInt(3, option.getGroup().getId());
			int affectedRows = pr.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Creating AnswerOption failed, no rows affected.");
			}
			try (ResultSet generatedKeys = pr.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					option.setId((int) (generatedKeys.getLong(1)));
				} else {
					throw new SQLException("Creating AnswerOption failed, no ID obtained.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return option;
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (AnswerOption[]) this.deepCopy(value);
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return this.deepCopy(cached);
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return deepCopy(original);
	}

}
