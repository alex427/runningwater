package zhiguang.tools.quartz;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class MyScheduler {
	
	public static void main(String[] args) throws SchedulerException {
		MyScheduler myScheduler = new MyScheduler();
		myScheduler.doConfig();
		
	}
	
	public void doConfig() throws SchedulerException{
		Date runtime = new Date();
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sc = sf.getScheduler();
		
		
        // 定义job
		/*JobDetail jobDetail = new JobDetail();
		jobDetail.setName("job1");
		jobDetail.setGroup("group1");
		
		Trigger trigger = new SimpleTrigger();
		trigger.setName("trigger1");
		trigger.setGroup("group1");
		trigger.setStartTime(runtime);*/
		
        // 将job注册到调度器
        //sc.scheduleJob(jobDetail, trigger);
		sc.start();
	}
	

}
