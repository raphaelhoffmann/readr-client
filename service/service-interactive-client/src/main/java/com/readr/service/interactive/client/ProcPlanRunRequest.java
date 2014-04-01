package com.readr.service.interactive.client;

public class ProcPlanRunRequest {
	private ProcPlan plan;
	private String text;
	private int id;
	
	public ProcPlanRunRequest() {};
	
	public void setPlan(ProcPlan plan) {
		this.plan = plan;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public ProcPlan getPlan() {
		return plan;
	}
	
	public String getText() {
		return text;
	}
	
	public int getId() {
		return id;
	}
}
