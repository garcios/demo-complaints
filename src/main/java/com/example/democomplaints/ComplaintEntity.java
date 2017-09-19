package com.example.democomplaints;
 

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ComplaintEntity {

	@Id
	private String complaintId;
	private String company;
	private String description;
	
	public ComplaintEntity(){};
	
	public ComplaintEntity(String complaintId, String company, String description) {
		this.complaintId = complaintId;
		this.company = company;
		this.description = description;
	}

	public String getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
