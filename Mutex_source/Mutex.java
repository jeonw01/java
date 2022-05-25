package com.ssp.test;

import java.util.concurrent.locks.ReentrantLock;

class MyThread extends Thread {
	String threadName;
	static ReentrantLock lock = new ReentrantLock();

	public MyThread(String threadName) {
		this.threadName = threadName;
	}

	public void run() {
		try {
			Mutex.printNumber(this.threadName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class Mutex {

	public static void main(String[] args) throws InterruptedException {
		MyThread mt1 = new MyThread("Threadl");
		MyThread mt2 = new MyThread("Thread2");

		mt1.start();
		mt2.start();
		printNumber("Main");
		mt1.join();
		mt2.join();
	}

	synchronized static void printNumber(String name) throws InterruptedException {
		System.out.println("[" + name + "]");
		for (int i = 0; i < 30; i++) {
			System.out.print((i + 1) + " "); 
			// Thread.sleep(1);
		}
		System.out.println();
	}
}
