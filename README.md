# ThreadReady

[![](https://jitpack.io/v/qq987212100/EasyThread.svg)](https://jitpack.io/#qq987212100/EasyThread)

以创建线程为例：


	支持设置ID、设置Runnable、设置监听（监听自身待执行内容是否执行完毕,未放入线程池或放入常规线程池时支持，监听线程池会覆盖掉线程自身监听）

	BaseThread baseThread = new BaseThread();
	baseThread.setid(i);
	baseThread.setRunnable(new Runnable() {
		@Override
		public void run() {
		   //TODO
		}
	});

	baseThread.setOnThreadStateChangeListener(new OnThreadStateChangeListener() {

                @Override
                public void onThreadStart(int id) {
                    
                }

                @Override
                public void onThreadEnd(int id) {

                }

                @Override
                public void onThreadError(int id, Exception e) {

                }
            });

	

	//监听线程池（只支持可监听线程的执行完毕监听，但依然可放入常规线程）
	BaseThreadPoolExecutor executor = BaseExecutorService.newFixedThreadPool(8);
	
	//或者常规创建模式
	BaseThreadPoolExecutor executor = new BaseThreadPoolExecutor(nThreads, nThreads,0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

	executor.setOnThreadPoolStateChangeListener(new OnThreadPoolStateChangeListener() {
            @Override
            public void onThreadStart(int id) {

            }

            @Override
            public void onThreadEnd(int id) {
                System.out.println(id + "----线程已执行完毕");
            }

            @Override
            public void onThreadError(int id, Exception e) {
                System.out.println(id + "---" + e.getMessage());
            }

            @Override
            public void onThreadPoolEnd(ExecutorService executorService) {
                System.out.println("----线程池已整体执行完毕");
            }
        });

	//添加入线程池 
	executor.execute(baseThread);

	这个引用要加上
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	然后再加上这个
	
	dependencies {
	        implementation 'com.github.qq987212100:EasyThread:1.0.1'
	}
	
	

主要的目的就是方便监听线程和线程池是否执行完毕，还需要什么优化和什么功能正在思考，写着玩玩的，各位大哥大姐请高抬贵手！

