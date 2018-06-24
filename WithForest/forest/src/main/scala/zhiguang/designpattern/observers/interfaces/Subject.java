/**
 * 
 */
package zhiguang.designpattern.observers.interfaces;

/**
 * @author zhiguang
 *
 */
public interface Subject {
	//注册订阅者
	void registObserver(Observer obs);
	//移除订阅者
	void removeObserver(Observer obs);
	//通知订阅者,把数据传给他,参数就是双方遵守的数据协议
	void notifyObserver(Integer temprature, Integer humidity, Integer pressure);
}
