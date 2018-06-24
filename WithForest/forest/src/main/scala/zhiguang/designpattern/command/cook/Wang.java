package zhiguang.designpattern.command.cook;


import zhiguang.designpattern.command.cookable.ChieseCook;
import zhiguang.designpattern.command.utils.ThreadUtil;

public class Wang implements ChieseCook {

	@Override
	public void chaofan() {
		
		ThreadUtil.sleep(1000);
		System.out.println("中国炒饭");
	}
	
	
}
