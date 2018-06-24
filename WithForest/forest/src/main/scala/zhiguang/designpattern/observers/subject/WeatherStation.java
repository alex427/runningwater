/**
 * 
 */
package zhiguang.designpattern.observers.subject;

import zhiguang.designpattern.observers.interfaces.Observer;
import zhiguang.designpattern.observers.interfaces.Subject;

import java.util.ArrayList;
import java.util.List;


/**
 * @author zhiguang
 *
 */
public class WeatherStation implements Subject {
	
	Integer temprature;
	Integer humidity;
	Integer pressure;
	List<Observer> list  = new ArrayList<>();

	@Override
	public void registObserver(Observer obs) {
		 list.add(obs);
	}

	@Override
	public void removeObserver(Observer obs) {
		 if(list.contains(obs)){
			 list.remove(obs);			 
		 }
		
	}

	@Override
	public void notifyObserver(Integer temprature, Integer humidity, Integer pressure) {
		 for(Observer obs : list){
			 obs.updateData(temprature, humidity, pressure);
		 }		
	}

}
