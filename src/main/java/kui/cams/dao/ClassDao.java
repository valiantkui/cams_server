package kui.cams.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kui.cams.entity.Class;
import kui.cams.entity.Student;

@Repository("classDao")
public interface ClassDao {

	public void classRegister(Class c);
	public Class findClassByC_no(String c_no);
	
	
}
