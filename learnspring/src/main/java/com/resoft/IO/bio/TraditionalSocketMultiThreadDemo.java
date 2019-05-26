package com.resoft.IO.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 传统BIO 多线程伪异步IO
 */
public class TraditionalSocketMultiThreadDemo {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		ExecutorService threadPool = Executors.newCachedThreadPool();// 线程池，缓存，无限大  m:m = 请求：线程  n:m  n=>m=1
//		ExecutorService threadPool = Executors.newFixedThreadPool(100); // 请求：线程 = n:m (n>m)
		new ThreadPoolExecutor(100, 1000, 10000, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread thread = new Thread();
				thread.setName("threadPoll");
				return thread;
			}
		}, new ThreadPoolExecutor.AbortPolicy());

		ServerSocket serverSocket = new ServerSocket(7777);
		System.out.println("服务端启动...");
		while (true) {
			// 获取socket套接字
			// accept()阻塞点
			Socket socket = serverSocket.accept();
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("有新客户端连接上来了...");
						// 获取客户端输入流
						InputStream is = socket.getInputStream();
						byte[] b = new byte[1024];
						while (true) {
							// 循环读取数据
							// read() 阻塞点
							int data = is.read(b);
							if (data != -1) {
								String info = new String(b, 0, data, "GBK");
								System.out.println(info);
							} else {
								break;
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}
