package member.dao;

import java.util.List;

import member.dto.MemberDTO;

public interface MemberDAO {
	public int insertMember(MemberDTO dto);
	public List<MemberDTO> listMember();
	public List<MemberDTO> findMember(String search, String searchString);
	public int deleteMember(int no);
	public MemberDTO getMember(int no);
	public int updateMember(MemberDTO dto);
	public String searchMember(String name, String ssn1, String ssn2, String id);
	public boolean checkMember(String name, String ssn1, String ssn2);
}
