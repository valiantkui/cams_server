package kui.cams.dao;

import org.springframework.stereotype.Repository;

import kui.cams.entity.R_c_s;

@Repository("rcsDao")
public interface R_c_sDao {

	public void addR_c_s(R_c_s rcs);
}
