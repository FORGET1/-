package com.pingdu.service.safePromiseService;

import java.util.List;

import com.pingdu.entity.safePromise.SafePromise;

public interface SafePromiseService {
	public Integer calSum();
	public List<SafePromise>getSafePromiseList(int page,int rows);
	public SafePromise getSafePromiseInfo(int entCode, int safePromiseCode);
	public boolean del(int entCode, int safePromiseCode);
	public Integer sumOfSearch(String type, String keyWord);
	public List<SafePromise> search(String searchType, String keyWord,int page,int rows);
	public int userCodeChangeEntCode(String userCode);
	public boolean GenerateImage(String imgStr, String Path, String imageName);
	public boolean modifySafePromise(SafePromise safe);
}
