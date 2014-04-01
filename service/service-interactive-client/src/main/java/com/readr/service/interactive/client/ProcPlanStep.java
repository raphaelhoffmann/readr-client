package com.readr.service.interactive.client;

public class ProcPlanStep {

	private String processor;
	
	public ProcPlanStep() {}
	
	public ProcPlanStep(String processor) {
		this.processor = processor;
	}
	
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	
	public String getProcessor() {
		return processor;
	}
}
