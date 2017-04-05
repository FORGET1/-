package com.pingdu.dao.licenseDao;

import static com.pingdu.manager.ThreadLocalManager.em;

import java.io.File;
import java.util.List;

import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.config.ResultType;
import org.springframework.stereotype.Repository;

import com.pingdu.entity.license.License;
import com.pingdu.utility.PublicVariable;

@Repository
public class LicenseDao {
	public void insert() {
		em().persist(this);
	}

	public List<License> getLicenseList(int page, int rows) {
		int head = (page - 1) * rows;
		String jpql = "select l from License l where 1=1 ";
		TypedQuery<License> query = em().createQuery(jpql, License.class);
//		query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
		query.setFirstResult(head);
		query.setMaxResults(rows);
		List<License> list = query.getResultList();
		return list;
	}

	public License getLicenseInfo(int entCode, int licenseCode){
		String jpql = "select l from License l where l.licenseCode=:licenseCode AND　l.entCode=:entCode";
		TypedQuery<License> query = em().createQuery(jpql, License.class);
		query.setParameter("licenseCode", licenseCode);
		query.setParameter("entCode", entCode);		
		List<License> licenses = query.getResultList();
		if(licenses.size() == 1){
			return licenses.get(0);
		}else{
			System.out.println("证照数量有多个非法或者证照为空");
			System.out.println("证照数量有多个非法或者证照为空");
			System.out.println("证照数量有多个非法或者证照为空");
			System.out.println("证照数量有多个非法或者证照为空");
			System.out.println("证照数量有多个非法或者证照为空");
			System.out.println("证照数量有多个非法或者证照为空");
			System.out.println("证照数量有多个非法或者证照为空");
		}
		License license = new License();
		return license;
	}
//////待完善 
	public List<License> searchLicenses(String searchType, Object keyWord, int page, int rows) {
		String jpql = new String();
		int head = (page - 1) * rows;
		if (searchType.equals("1")) {
			jpql = "select lic from License lic where lic.entCode =:keyWord order by lic.licenseCode";
		}
		if (searchType.equals("证照名称")) {
			jpql = "select lic from License lic where lic.licTypeCode =:keyWord order by lic.licenseCode";
		}
		if (searchType.equals("上传日期")) {
			jpql = "select lic from License lic where lic.uploadDate =:keyWord order by lic.licenseCode";
		}
		if (searchType.equals("有效日期")) {
			jpql = "select lic from License lic where lic.expireDate =:keyWord order by lic.licenseCode";
		}
		if (searchType.equals("是否有效")) {
			jpql = "select lic from License lic where lic.isExpire =:keyWord order by lic.licenseCode";
		}
		TypedQuery<License> query = em().createQuery(jpql, License.class);
		//query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
		query.setParameter("keyWord", keyWord);
		query.setFirstResult(head);
		query.setMaxResults(rows);
		List<License> list = query.getResultList();
		return list;
	}

	public Integer sumOfSearch(String searchType, Object keyWord) {
		String jpql = new String();
		//改。。。。。
//		if (searchType.equals("企业名称")) {
		if (searchType.equals("证照名称")) {
			jpql = "select lic from License lic where lic.licTypeCode =:keyWord order by lic.licenseCode";
		}
		if (searchType.equals("上传日期")) {
			jpql = "select lic from License lic where lic.uploadDate =:keyWord order by lic.licenseCode";
		}
		if (searchType.equals("有效日期")) {
			jpql = "select lic from License lic where lic.expireDate =:keyWord order by lic.licenseCode";
		}
		if (searchType.equals("是否有效")) {
			jpql = "select lic from License lic where lic.isExpire =:keyWord order by lic.licenseCode";
		}
		TypedQuery<License> query = em().createQuery(jpql, License.class);
		//query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
		query.setParameter("keyWord",keyWord);
		int sum = ((query.getResultList().size()) - 1) / PublicVariable.rows + 1;
		return sum;
	}

	public boolean delete(int companyCode,int licenseCode) {
		License license = findByCompanyCodeAndLicenseCode(companyCode,licenseCode);
		if (license != null) {
			em().remove(license);
			//删除文件夹下所有证照图片
			String path = license.getImagePath();
			if(!(path==null)){
				File file = new File(path);
				if(file.isDirectory())
				{
					File[] subFiles = file.listFiles();
					for(int i=0;i<subFiles.length;i++)
					{
						subFiles[i].delete();
					}
				}
				file.delete();
			}
			return true;
		}
		return false;
	}

	private License findByCompanyCodeAndLicenseCode(int companyCode,int licenseCode) {
		String jpql = "select l from License l where l.entCode = :entCode AND l.licenseCode = :licenseCode";
		TypedQuery<License> query = em().createQuery(jpql, License.class);
	//	query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
		query.setParameter("entCode",companyCode);
		query.setParameter("licenseCode", licenseCode);
		List<License> licenses = query.getResultList();
		if(licenses.size()==1)
			return licenses.get(0);
		else
			return null;
	}

	public String getLicenseImgSrc(int companyCode, int licenseCode) {
		License l = findByCompanyCodeAndLicenseCode(companyCode,licenseCode);
		if (l != null) {
			return l.getImagePath();
		}
		return null;
	}

	public boolean mod(License license) {
		try {
			em().merge(license);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public Integer sum(int rows) {
		String jpql = "select lic from License lic where 1=1";
		TypedQuery<License> query = em().createQuery(jpql, License.class);
		//query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
		int SUM = ((query.getResultList().size()) - 1) / rows + 1;
		return SUM;
	}

	public List<License> getAllLicenseList() {
		String jpql = "select lic from License lic where 1=1";
		TypedQuery<License> query = em().createQuery(jpql, License.class);

		return query.getResultList();
	}



}
