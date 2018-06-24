package zhiguang.tools.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job{
	
	public HelloJob() {
		
    }

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		doTest();		
	}
	
	public void doTest(){
		System.out.println("hello job .............."+ new Date());
	}

}
