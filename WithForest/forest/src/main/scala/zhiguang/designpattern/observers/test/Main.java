/**
 * 
 */
package zhiguang.designpattern.observers.test;


import zhiguang.designpattern.observers.observer.CompanyA;
import zhiguang.designpattern.observers.observer.CompanyB;
import zhiguang.designpattern.observers.subject.WeatherStation;

/**
 * @author zhiguang
 *
 */
public class Main {
 
	public static void main(String[] args) {
		CompanyA companyA = new CompanyA();
		CompanyB companyB = new CompanyB();
		WeatherStation station = new WeatherStation();
		station.registObserver(companyA);
		station.registObserver(companyB);
		station.notifyObserver(30, 22, 33);
		
	}

}
