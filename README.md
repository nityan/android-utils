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
    clientService.addHeader(new HttpHeader("Content-Type", "application/json;charset=UTF-8"));
    clientService.executeRequestAsync();


System Services

    ServiceManager serviceManager = ServiceManager.getInstance(getApplicationContext());
    
    if (serviceManager.isBluetoothAvailable()) {
	    Log.d("DEBUG", "Bluetooth is available");
    }
    
    if (serviceManager.isNetworkAvailable()) {
	    Log.d("DEBUG", "Network is available");
    }
    
Shared Preferences

    SharedPreferences sharedPreferences = SharedPreferences.getInstance(getApplicationContext());
    
    sharedPreferences.setStringForKey("I'm the key", "I'm the value");
    
    String data = sharedPreferences.getStringForKey("I'm the key");

