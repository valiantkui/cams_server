package kui.cams.dao;

import org.springframework.stereotype.Repository;

import kui.cams.entity.Resource;

@Repository("resourceDao")
public interface ResourceDao {

	public Resource findResourceByMd5(String md5);
	public void addResource(Resource resource);
	public void updateCount(int r_no);
}
