/**
 * 
 */
package zhiguang.designpattern.observers.observer;


import zhiguang.designpattern.observers.interfaces.Observer;

/**
 * @author zhiguang
 *
 */
public class CompanyA implements Observer {
	
	public CompanyA() {
	
	}

	@Override
	public void updateData(Integer temprature, Integer humidity, Integer pressure) {
		 System.out.println("CompanyA气象数据如下: "+temprature +"----"+humidity+"---"+pressure);		
	}

}
