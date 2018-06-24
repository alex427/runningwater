package zhiguang.datastructure.elevator;

public class Elevator {

	public static void main(String[] args) {
		Elevator elevator = new Elevator();
		elevator.dowork();
	}

	private void dowork() {
		int[] arr = new int[] { 1, 20, 15, 11, 21, 23, 24, 2, 4, 5, 6 };
		boolean flag = false;
		int max = 0;
		int min = 0;
		if (!flag) {
			for (int i = 0; i < arr.length; i++) {
				if (i == 0) {
					max = arr[i];
					System.out.println(arr[i]);
				} else {
					if (arr[i] > arr[i - 1]) {
						if (arr[i] > max) {
							max = arr[i];
						}
						if (arr[i] >= max) {
							System.out.println(arr[i]);
						}

					}
				}
			}
			flag = true;
		}
		if (flag) {
			for (int i = arr.length - 1; i > 0; i--) {
				if (i == arr.length - 1) {
					min = arr[i];
					System.out.println(arr[i]);
				} else {
					if (arr[i] < arr[i - 1]) {
						if (arr[i] < min) {
							min = arr[i];
						}
						if (arr[i] <= min) {
							System.out.println(arr[i]);
						}

					}
				}
			}
			flag = false;
		}

	}

}
