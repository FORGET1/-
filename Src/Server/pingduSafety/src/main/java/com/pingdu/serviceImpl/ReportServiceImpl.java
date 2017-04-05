package com.pingdu.serviceImpl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.pingdu.dao.ReportDao;
import com.pingdu.service.ReportService;
import com.pingdu.view.Report;

@Service
public class ReportServiceImpl implements ReportService{

	@Override
	public List<Report> getReport() {
		
		return ReportDao.getReport();
	}
	
      
    
}
