package com.pingdu.dao.fileDao;


import static com.pingdu.manager.ThreadLocalManager.em;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.config.ResultType;
import org.springframework.stereotype.Repository;

import com.pingdu.entity.file.FileSRInfo;
import com.pingdu.entity.file.Filedownload;
import com.pingdu.entity.file.Files;
import com.pingdu.entity.notice.Notice;
import com.pingdu.entity.user.User;
import com.pingdu.utility.PublicVariable;
import com.pingdu.view.ArchiveItemList;
import com.pingdu.view.FileAllPageList;
import com.pingdu.view.FileUserCodePage;

@Repository
public class FileDao {

	public String FileList(int page) {
		// 查看文件列表
		String jpql = "SELECT f.fileName,f.fileCode,fs.senderCode,fs.uploadDate,"
				+ " fs.senderName,fs.downloadPersonNum FROM Files f "
				+ " INNER JOIN FileSRInfo fs ON fs.fileCode=f.fileCode";
		return jpql;

	}
	public String FileUserCode(int userCode){
		//查看制定的文件列表
		String jpql = "SELECT t.fileCode,t.downloadDate,t.isDownload,"
	             + " t1.fileName FROM Filedownload t"
				 + " LEFT JOIN Files t1 ON t.fileCode = t1.fileCode"
	             + " WHERE t.userCode = '" + userCode + "' and t.isDownload=0" ;
		return jpql;
	}

	public String FileSearchLlist(String searchType, String searchKey) {
		// 查询特定的文件列表
		String jpql = "SELECT f.fileName,f.fileCode,fs.senderCode,"
				+ " fs.senderName,fs.downloadPersonNum FROM Files f "
				+ " INNER JOIN FileSRInfo fs ON fs.fileCode=f.fileCode";
		if ("1".equals(searchType)) {
			jpql = jpql + " where fs.senderName like '"+"%"+searchKey+"%"+"'";
		} else if ("2".equals(searchType)) {
			jpql = jpql + " where  f.fileName like '"+"%"+searchKey+"%"+"'";
		}
		return jpql;

	}
	
	
	
	
	public List<FileAllPageList> getFileList(int page) {
		try {
			//查看文件列表
				String jpql = FileList(page);
				TypedQuery<FileAllPageList> query = em().createQuery(jpql, FileAllPageList.class);
				query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
				int head = (page - 1) * 15;
				query.setFirstResult(head);
				query.setMaxResults(15);
				List<FileAllPageList> fileAllList = query.getResultList(); 
				System.out.println(" 查看文件列表sql语句成功执行");
				return fileAllList;
		} catch (Exception e) {
				System.out.println(" 查看文件列表sql语句异常"+e.getMessage());
				return null;
		}
	}
	
	public List<FileAllPageList> getArchiveSpecList(String searchType,String keyword,int page) {
		try {
			//根据条件查询文件列表
				String jpql = FileSearchLlist(searchType, keyword);
				TypedQuery<FileAllPageList> query = em().createQuery(jpql, FileAllPageList.class);
				//.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
				int head = (page - 1) * 15;
				query.setFirstResult(head);
				query.setMaxResults(15);
				List<FileAllPageList> fileSearchList = query.getResultList(); 
				System.out.println(" 查看特定条件文件列表sql语句成功执行");
				return fileSearchList;
		} catch (Exception e) {
	// TODO: handle exception
	System.out.println(" 查看特定条件文件列表sql语句异常"+e.getMessage());
	return null;
		}
	}
	
	

	// 文件下载
	public Files getOneDevInfo(String fileName) {
		if (!"0".equals(fileName)) {
			String jpql = "select dev from File dev where dev.fileName=:fileName";
			TypedQuery<Files> query = em().createQuery(jpql, Files.class);
			query.setParameter("fileName", fileName);
			Files file = query.getSingleResult();

			return file;
		}

		Files file = new Files();
		file.setFileName("/");

		return file;
	}
/********************************************************************************************/
	//以下方法不是被serviceImpl直接调用
	public int fileDownCode(String fileName) {
		try {
			// 文件下载处理流程 根据文件名获取文件编号
			String jpql = "SELECT file.fileCode FROM File file WHERE file.fileName=:fileName";
			TypedQuery query = em().createQuery(jpql, Files.class);
			query.setParameter("fileName", fileName);
			int fileCode = (int) query.getSingleResult();
			String jpql1 = "SELECT fileDown.fileDowncode FROM Filedownload fileDown WHERE fileDown.fileCode=:fileCode ";
			TypedQuery query1 = em().createQuery(jpql1, Filedownload.class);
			query.setParameter("fileCode", fileCode);
			int fileDownCode = (int) query.getSingleResult();
			return fileDownCode;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("根据文件名--》file中的fileCode---》filesrinfo中的Code异常"+e.getMessage());
			return 1;
		}
	
	}

	public int fileSRCode(String fileName) {
		// 文件下载处理流程 根据文件名获取文件编号x
		String jpql = "SELECT file.fileCode FROM File file WHERE file.fileName=:fileName";
		TypedQuery query = em().createQuery(jpql, Files.class);
		query.setParameter("fileName", fileName);
		int fileCode = (int) query.getSingleResult();
		String jpql1 = "SELECT filesr.fileSRInfocode FROM FileSRInfo filesr WHERE fileDown.fileCode=:fileCode ";
		TypedQuery query1 = em().createQuery(jpql1, FileSRInfo.class);
		query.setParameter("fileCode", fileCode);
		int fileSRInfocode = (int) query.getSingleResult();
		return fileSRInfocode;
	}
	/********************************************************************************************/
	public void insertfileDown(String fileName,int UserCode) {
		// 文件下载处理流程 填写文件下载信息表
		try {
			int fileDownCode = fileDownCode(fileName);
			Filedownload filedownload = em().find(Filedownload.class,
					fileDownCode);
			filedownload.setFileDowncode(fileDownCode);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			filedownload.setUserCode(UserCode);
			filedownload.setDownloadDate(sdf.format(d));
			filedownload.setIsDownload(1);
			em().merge(filedownload);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("文件下载处理流程 填写文件下载信息表" + e.getMessage());
		}

	}

	public void insertFilesrinfo(String fileName) {
		// 文件下载处理流程 填写文件收发信息表
		try {
			int filesrinfocode = fileSRCode(fileName);
			FileSRInfo fileinfo = em().find(FileSRInfo.class, filesrinfocode);

			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			fileinfo.setFilesrinfocode(filesrinfocode);
			fileinfo.setUploadDate(sdf.format(d));
			fileinfo.setDownloadPersonNum(fileinfo.getDownloadPersonNum() + 1);
			em().merge(fileinfo);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("文件下载处理流程 填写文件收发信息表" + e.getMessage());
		}

	}
	//添加文件
	public void insert(Files files,int UserCode,String senderType,int receiverCode,String recvType){
		//文件管理表中插入并且将文件收发表中的信息也插入
		try {
			em().persist(Files.class);
			String jpql = "SELECT f FROM Files f WHERE 1=1 order by f.fileCode DESC";
			TypedQuery<Files> query = em().createQuery(jpql, Files.class);
			int fileCode = query.getResultList().get(0).getFileCode();

			String jpql1 = "SELECT user FROM User user WHERE user.userCode='"
					+ UserCode + "'";
			TypedQuery<User> query1 = em().createQuery(jpql1, User.class);
			List<User> users = query1.getResultList();
			int roleCode =users.get(0).getRole().getRoleCode();
			String userName = users.get(0).getName();

			int senderCode = 0;
			if (roleCode >= 4) {
				senderCode = users.get(0).getEnterprise().getEntCode();
			}
			if (roleCode < 4) {
				senderCode = users.get(0).getDept().getDeptCode();;
			}
			FileSRInfo filesrinfo=new FileSRInfo();
			filesrinfo.setFileCode(fileCode);
			filesrinfo.setSenderName(userName);
			filesrinfo.setSenderType(senderType);
			filesrinfo.setSenderCode(senderCode);
			filesrinfo.setReceiverCode(receiverCode);
			filesrinfo.setReceiverType(recvType);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			filesrinfo.setUploadDate(sdf.format(d));
			em().persist(filesrinfo);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("上传文件做两个表插入操作时异常"+e.getMessage());
		}
	
		
	}
	
	/**
	 * 计算总页数
	 * 
	 * @param entCode
	 * @param page
	 * @param status
	 * @return
	 */
	public int calPage(int page) {
		try {
			String jpql = FileList(page);
			TypedQuery query = em().createQuery(jpql, FileAllPageList.class);
			// query.setParameter("entCode", entCode);
			 //Query query=em().createQuery(jpql);
			
			int sum = (query.getResultList().size() - 1) / PublicVariable.rows + 1;
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("计算总页数异常"+e.getMessage());
			return 1;
		}
	
	}
	
	public int calPageSearch(String searchType,String keyword) {
		try {
			String jpql =FileSearchLlist(searchType, keyword);
			TypedQuery<FileAllPageList> query = em().createQuery(jpql, FileAllPageList.class);
			int sum = (query.getResultList().size() - 1) / PublicVariable.rows + 1;
			System.out.println("计算最后一页成功");
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("计算最后一页异常"+e.getMessage());
			return 1;
		}
	
	}

	
	public int appGetFileListPage(int userCode) {
		//app接口接收通知列表页数
		try {
			String jpql = FileUserCode(userCode);
			TypedQuery<FileUserCodePage> query = em().createQuery(jpql, FileUserCodePage.class);
		
			int sum = (query.getResultList().size() - 1) / PublicVariable.rows + 1;
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("计算最后一页异常"+e.getMessage());
			return 1;
		}
	
	}
	/**
	 * 获取全部文件列表
	 * @param userCode
	 * @param page
	 */

	public List<FileUserCodePage> appGetFileList(int userCode, int page) {
		try {
			// 查看发送的通知列表
			String jpql = FileUserCode(userCode);
			TypedQuery<FileUserCodePage> query = em().createQuery(jpql,
					FileUserCodePage.class);
			query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
			int head = (page - 1) * 15;
			query.setFirstResult(head);
			query.setMaxResults(15);
			List<FileUserCodePage> fileUserCodePage = query.getResultList();
			System.out.println(" 查看发送通知sql语句成功执行");
			return fileUserCodePage;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("查看发送通知sql语句异常" + e.getMessage());
			return null;
		}

	}

	// app文件下载
		public Files getAppDownloadFile(int fileCode) {
			if (!"".equals(fileCode)) {
				String jpql = "select dev from Files dev where dev.fileCode=:fileCode";
				TypedQuery<Files> query = em().createQuery(jpql, Files.class);
				query.setParameter("fileCode", fileCode);
				Files file = query.getSingleResult();

				return file;
			}
			Files file = new Files();
			file.setFileName("/");
			return file;
		}
		public int appfileSRInfocode(int fileCode,int userCode) {
			// 文件下载处理流程 根据文件编号获取文件上传编号
			String jpql1 = "SELECT t FROM User t where t.userCode=:userCode";
			TypedQuery<User> query1 = em().createQuery(jpql1, User.class);
			query1.setParameter("userCode", userCode);
			int recvCode;
			int fileSRInfocode;
			if(null != this.getSingleResultOrNull(query1)&&!"".equals(this.getSingleResultOrNull(query1))&&query1.getSingleResult().getRole().getRoleCode()>=4){
				recvCode = query1.getSingleResult().getDept().getDeptCode();
				String jpql = "SELECT f FROM FileSRInfo f WHERE f.fileCode=:fileCode and f.receiverCode=:recvCode";
				TypedQuery<FileSRInfo> query = em().createQuery(jpql, FileSRInfo.class);
				query.setParameter("fileCode", fileCode);
				query.setParameter("recvCode", recvCode);
				
				if(null != this.getSingleResultOrNull(query)&&!"".equals(this.getSingleResultOrNull(query))){
					fileSRInfocode= query.getSingleResult().getFilesrinfocode();
				}else{
					fileSRInfocode=0;
				}
			}else{
				fileSRInfocode = 0;
			}
		
			
			
			return fileSRInfocode;
		}
		public int appfileDownCode(int fileCode,int userCode) {
			// 文件下载处理流程 根据文件名获取文件下载编号
			String jpql = "SELECT fileDown FROM Filedownload fileDown WHERE fileDown.fileCode=:fileCode  and fileDown.userCode=:userCode";
			TypedQuery<Filedownload> query = em().createQuery(jpql, Filedownload.class);
			query.setParameter("fileCode", fileCode);
			query.setParameter("userCode", userCode);
			int fileDownCode;
			if(null != this.getSingleResultOrNull(query)&&!"".equals(this.getSingleResultOrNull(query))){
				fileDownCode  = query.getSingleResult().getFileDowncode();
				}
			else{
				fileDownCode=0;
			}
			
			return fileDownCode;
		}
		public void appinsertfileDown(int fileCode,int userCode) {
			// app文件下载处理流程 填写文件下载信息表 改
			try {
				int fileDownCode = appfileDownCode(fileCode,userCode);
				if(fileDownCode!=0){
					Filedownload filedownload = em().find(Filedownload.class,
							fileDownCode);
					filedownload.setFileDowncode(fileDownCode);
					Date d = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					filedownload.setDownloadDate(sdf.format(d));
//					filedownload.setIsDownload(1);
					filedownload.setUserCode(userCode);
					em().merge(filedownload);	
				}else{
					Filedownload filedownload=new Filedownload();
					filedownload.setUserCode(userCode);
					Date d = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					filedownload.setDownloadDate(sdf.format(d));
	     			filedownload.setIsDownload(0);
					filedownload.setFileCode(fileCode);
					em().persist(filedownload);
				}

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("文件下载处理流程 填写文件下载信息表" + e.getMessage());
				}

		}
		public void appinsertFilesrinfo(int fileCode,int userCode) {
			// app文件下载处理流程 填写文件收发信息表
			try {
				int filesrinfocode = appfileSRInfocode(fileCode,userCode);
				if(filesrinfocode!=0){
					FileSRInfo fileinfo = em().find(FileSRInfo.class,
							filesrinfocode);
					Date d = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					fileinfo.setDownloadPersonNum(fileinfo.getDownloadPersonNum() + 1);
					em().merge(fileinfo);	
				}
				else{
					String jpql = "SELECT user FROM User user WHERE user.userCode=:userCode";
					TypedQuery<User> query = em().createQuery(jpql, User.class);
					query.setParameter("userCode", userCode);
					int roleCount = query.getSingleResult().getRole().getRoleCode();
					int recvCode;
					if(roleCount>3){
						recvCode= query.getSingleResult().getDept().getDeptCode();
					}
					else{
						recvCode = query.getSingleResult().getEnterprise().getEntCode();
					}
					FileSRInfo fileinfo = new FileSRInfo();

					Date d = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					fileinfo.setReceiverCode(recvCode);
					fileinfo.setReceiverType("需要在改");
					fileinfo.setDownloadPersonNum(1);
					fileinfo.setFileCode(fileCode);
					em().persist(fileinfo);
				}
			
				
			
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("文件下载处理流程 填写文件收发信息表" + e.getMessage());
			}

		}
		
		public void appSendDownloadFlag(int fileCode,int userCode,int flag) {
			// app 标置文件是否下载成功 改
			try {
				int fileDownCode = appfileDownCode(fileCode,userCode);
				Filedownload filedownload = em().find(Filedownload.class,
						fileDownCode);
					filedownload.setIsDownload(1);
					em().merge(filedownload);
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("文件下载失败" + e.getMessage());
			}

		}
		public void delete(int fileCode,int userCode) {
			// app 删除文件
//			String jpql = "select dev from Filedownload dev where dev.fileCode='"+fileCode+"'  and dev.userCode='"+userCode+"'";
			try {
			int fileDownCode = appfileDownCode(fileCode,userCode);
			Filedownload filedownload = em().find(Filedownload.class,
					fileDownCode);
			filedownload.setIsDownload(0);
			em().merge(filedownload);	
			}catch(Exception e) {
				// TODO: handle exception
				System.out.println("文件下载失败" + e.getMessage());
			}
//			
//			TypedQuery<Files> query = em().createQuery(jpql, Files.class);
//			 List<Files> list = query.getResultList();
//				for(int i=0;i<list.size();i++){
//					em().remove(list.get(i));
//				}
		}
		 public static <T> T getSingleResultOrNull(TypedQuery<T> query) {
			    query.setMaxResults(1);
			    List<T> list = query.getResultList();
			    if (list.isEmpty()) {
			        return null;
			    }
			    return list.get(0);
			}

	/**************************************************************/
	public void deleteFiles(int fileCode) {
		String jpql = "delete from Files dev where dev.fileCode=:fileCode ";
		TypedQuery<Files> query = em().createQuery(jpql, Files.class);
		query.setParameter("fileCode", fileCode);
		query.executeUpdate();
	}

	public void deleteFilesrinfo(int fileCode) {
		String jpql = "delete from FileSRInfo dev where dev.fileCode=:fileCode ";
		TypedQuery<FileSRInfo> query = em().createQuery(jpql, FileSRInfo.class);
		query.setParameter("fileCode", fileCode);
		query.executeUpdate();
	}
	
	public void deletefileDowwnload(int fileCode) {
		String jpql = "delete from Filedownload dev where dev.fileCode=:fileCode ";
		TypedQuery<Filedownload> query = em().createQuery(jpql, Filedownload.class);
		query.setParameter("fileCode", fileCode);
		query.executeUpdate();
	}
	
	
}
