package com.daimler.spi.spibatch.data;

public class BatchJobStatus {
	
	  private String jobName;
	    private String status;
	    
	    
		public BatchJobStatus(String jobName, String status) {
			super();
			this.jobName = jobName;
			this.status = status;
		}
		public String getJobName() {
			return jobName;
		}
		public void setJobName(String jobName) {
			this.jobName = jobName;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}

}
