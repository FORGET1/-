//package com.pingdu.autoBatch;
//
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.scheduling.annotation.Scheduled;    
//import org.springframework.stereotype.Component;
//
//import com.pingdu.autoBatch.SafetyPromiseCheckService;
//
//@Component("PingDuTask") 
//public class TaskManager {  
//
//	@Autowired
//	private LicenseCheckService licenseCheckService;
//	@Autowired
//	private SafetyPromiseCheckService safetyPromiseCheckService;
//	@Autowired
//	private ArchiveCheckService archiveCheckService;
//	
//    public void licenseCheck() {  
//    	licenseCheckService.checkLicense();
//    }
//    public void archiveCheck() {  
//    	archiveCheckService.checkArchive();
//    }
//    public void SafetyPromiseDaoCheck() {  
//    	safetyPromiseCheckService.checkSafetyPromise();
//    }
//}