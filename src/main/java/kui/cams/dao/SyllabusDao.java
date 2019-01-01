package kui.cams.dao;

import org.springframework.stereotype.Repository;

import kui.cams.entity.Syllabus;

@Repository("syllabusDao")
public interface SyllabusDao {

		public Syllabus findSyllabusByTermC_no(Syllabus syllabus);
		public Syllabus findSyllabusByS_no(int s_no);

		public int addSyllabus(Syllabus syllabus);
		
		public int updateSyllabus(Syllabus syllabus);
		
		public int deleteSyllabusByS_no(int s_no);
		
}
