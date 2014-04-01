package com.readr.service.interactive.client;

import java.util.List;

public class ProcPlan {
	
	private List<ProcPlanStep> steps;
	
	public void setSteps(List<ProcPlanStep> steps) {
		this.steps = steps;
	}
	
	public List<ProcPlanStep> getSteps() {
		return steps;
	}
}
