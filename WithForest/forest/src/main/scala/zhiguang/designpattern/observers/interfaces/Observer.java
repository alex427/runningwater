/**
 * 
 */
package zhiguang.designpattern.observers.interfaces;

/**
 * @author zhiguang
 *
 */
public interface Observer {
	//获取数据,也即是接收通知
	void updateData(Integer temprature, Integer humidity, Integer pressure);
}
