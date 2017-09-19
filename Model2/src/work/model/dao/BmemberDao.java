package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import work.model.dto.Bmember;

public class BmemberDao {
	private FactoryDao factory = FactoryDao.getinstance();
	private static BmemberDao instance = new BmemberDao();
	
	private BmemberDao() { }
	
	public static BmemberDao getInstance() {
		return instance;
	}
	
	/**
	 * <pre>
	 * ���̵� �ߺ� Ȯ��
	 * </pre>
	 * @param id
	 * @return ������ true, ������ false
	 */
	public boolean isM_id(String id) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT M_ID FROM BMEMBER WHERE M_ID=?";
		try {
			con = factory.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close(rs, pst, con);
		}
		return false;
	}
	
	/**
	 * <pre>
	 * �г��� �ߺ� Ȯ��
	 * </pre>
	 * @param nick
	 * @return ������ true, ������ false
	 */
	public boolean isM_nick(String nick) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT M_NICK FROM BMEMBER WHERE M_NICK=?";
		try {
			con = factory.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, nick);
			rs = pst.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close(rs, pst, con);
		}
		return true;
	}

	/**
	 * <pre>
	 * ȸ�����̺� ���
	 * </pre>
	 * @param DTO
	 * @return ����1, ���� 0
	 */
	public int insert(Bmember DTO) {
		Connection con = null;
		PreparedStatement pst = null;
		String sql = "INSERT INTO BMEMBER VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = factory.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, DTO.getmId());
			pst.setString(2, DTO.getmPw());
			pst.setString(3, DTO.getmName());
			pst.setString(4, DTO.getmNick());
			pst.setString(5, DTO.getmBirth());
			//pst.setString(5, "to_date('"+DTO.getmBirth()+"','yyyy/mm/dd')");
			pst.setString(6, DTO.getmPhone());
			pst.setString(7, DTO.getmMail());
			pst.setString(8, DTO.getmGender());
			pst.setString(9, DTO.getmAddress());
			pst.setInt(10, DTO.getmQuest());
			pst.setString(11, DTO.getmAnswer());
			return pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		} finally {
			factory.close(pst, con);
		}
		return 0;
	}
	
	/**
	 * <pre>
	 * ȸ�� ����
	 * </pre>
	 * @param m_id
	 * @return ���� 1, ���� 0
	 */
	public int delete(String m_id) {
		Connection con = null;
		PreparedStatement pst = null;
		String sql = "DELETE FROM BMEMBER WHERE m_id=?";
		try {
			con = factory.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, m_id);
			return pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close(pst, con);
		}
		return 0;
	}
	
	/**
	 * <pre>
	 * �α��� ��
	 * </pre>
	 * @param id
	 * @return ����: ���̵�, ��й�ȣ, �г���  ����: null
	 */
	public HashMap selectlogin(String id) {
		HashMap hm = new HashMap<>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT m_id, m_pw, m_nick FROM BMEMBER WHERE m_id=?";
		try {
			con = factory.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				String sid = rs.getString("m_id");
				String spw = rs.getString("m_pw");
				String snick = rs.getString("m_nick");
				hm.put(sid, new Bmember(sid, spw, snick));
			}
			return hm;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			factory.close(rs, pst, con);
		}
		return hm;
	}
	
	/**
	 * <pre>
	 * ���̵� ã��
	 * </pre>
	 * @param name
	 * @return �ش� �̸��� ���� ��� ȸ�� ����
	 */
	public ArrayList selectId(String name) {
		ArrayList al = new ArrayList<>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT m_id, m_name, to_char(m_birth,'yyyyMMdd')\"m_birth\", m_answer FROM BMEMBER WHERE m_name=?";
		try {
			con = factory.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			while (rs.next()) {
				String sid = rs.getString("m_id");
				String sname = rs.getString("m_name");
				String sbirth = rs.getString("m_birth");
				String sanswer = rs.getString("m_answer");
				al.add(new Bmember(sid, sname, sbirth, sanswer));
			}
			return al;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			factory.close(rs, pst, con);
		}
		return al;
	}
	
	/**
	 * <pre>
	 * ��й�ȣ ã��
	 * </pre>
	 * @param id
	 * @return �ش� ���̵� ���� ȸ������
	 */
	public ArrayList selectPw(String id) {
		ArrayList al = new ArrayList<>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT m_id, m_pw, m_name, to_char(m_birth,'yyyyMMdd')\"m_birth\", m_answer FROM BMEMBER WHERE m_id=?";
		try {
			con = factory.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				String sid = rs.getString("m_id");
				String spw = rs.getString("m_pw");
				String sname = rs.getString("m_name");
				String sbirth = rs.getString("m_birth");
				String sanswer = rs.getString("m_answer");
				al.add(new Bmember(sid, spw, sname, sbirth, sanswer));
			}
			return al;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			factory.close(rs, pst, con);
		}
		return al;
	}
	
	/**
	 * <pre>
	 * 개인정보 조회
	 * </pre>
	 * @param id
	 * @param nick
	 * @return 회원 정보 조회 결과
	 */
	public Bmember selectinfor(String id) {
		ArrayList al = new ArrayList<>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT m_id, m_pw, m_name, m_name, m_nick, to_char(m_birth,'yyyy/MM/dd')\"m_birth\","
				+ "m_phone, m_mail, m_gender, m_address, m_quest, m_answer FROM BMEMBER WHERE m_id=?";
		try {
			con = factory.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				String sid = rs.getString("m_id");
				String spw = rs.getString("m_pw");
				String sname = rs.getString("m_name");
				String snick = rs.getString("m_nick");
				String sbirth = rs.getString("m_birth");
				String sphone = rs.getString("m_phone");
				String smail = rs.getString("m_mail");
				String sgender = rs.getString("m_gender");
				String saddress = rs.getString("m_address");				
				int squestion = Integer.parseInt(rs.getString("m_quest"));				
				String sanswer = rs.getString("m_answer");
				return new Bmember(sid, spw, sname, snick, sbirth, sphone, smail, sgender, saddress, squestion, sanswer);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			factory.close(rs, pst, con);
		}
		return null;
	}
	
}
