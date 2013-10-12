android-utils
=============

A collection of utility classes for the Android Platform.

How to use
=============

Method One
=============

Download the JAR file, and add it as a library to your project.


Method Two
=============

1) Download and extract the project.

2) Copy the project into a folder inside your project

3) Import the project following the Android Studio import wizard.


Examples
=============

Threading

    ThreadPool threadPool = ThreadPool.getInstance();

		threadPool.queueWorkerItem(new Runnable() {

			@Override
			public void run() {
				Log.d("DEBUG", "I am running in a background thread");
			}
    });
    
    
Http Services

    HttpClientService clientService = new HttpClientService("http://example.com", RequestType.GET, this);
    clientService.executeRequestAsync();





