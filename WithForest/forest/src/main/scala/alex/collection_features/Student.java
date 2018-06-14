package alex.collection_features;

public class Student implements Comparable<Student>{
	private String name;
	private int age;
	private int score;
	public Student() {

	}
	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public Student(String name, int age, int score) {
		super();
		this.name = name;
		this.age = age;
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", score=" + score
				+ "]";
	}
	@Override
	public int compareTo(Student o) {
		int num = this.score-o.score;		
		return num==0?1:num;
	}
	
}
