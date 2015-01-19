android-utils
=============

A collection of utility classes for the Android Platform.

How to use
=============
```xml
<dependency>
  <groupId>com.nityankhanna</groupId>
  <artifactId>androidutils</artifactId>
  <version>1.1</version>
</dependency>
```


Examples
=============

Threading
=============

    ThreadPool threadPool = ThreadPool.getInstance();

		threadPool.queueWorkerItem(new Runnable() {

			@Override
			public void run() {
				Log.d("DEBUG", "I am running in a background thread");
			}
    });
    
    
Http Services
=============
    
    HttpRequestMessage requestMessage = new HttpRequestMessage("http://example.com", RequestType.GET);
    
    requestMessage.addHeader(new HttpHeader("Content-Type", "application/json;charset=UTF-8");
	
	JSONObject body = new JSONObject();
	
	body.put("key", "value");
	body.put("key2", 1);
	
    requestMessage.setJsonBody(body);
    
    HttpClientService client = new HttpClientService(requestMessage, this);
    
    client.executeRequestAsync();


System Services
=============

    ServiceManager serviceManager = ServiceManager.getInstance();
    
    if (serviceManager.isBluetoothAvailable()) {
	    Log.d("DEBUG", "Bluetooth is available");
    }
    
    if (serviceManager.isNetworkAvailable()) {
	    Log.d("DEBUG", "Network is available");
    }
    
String Utility Services
=============
    
    StringUtils.toByteArray("this is a string");
    StringUtils.toInt("1234");
    StringUtils.isNullOrEmpty("Pretend Im NULL");
    
Encryption Services
=============
    
    EncryptionManager encryptionManager = EncryptionManager.getInstance();
    
    SecretKeySpec password = encryptionManager.createPassword("guest");
    
    byte[] encryptedData = encryptionManager.encryptData(password, StringUtils.toByteArray("this is the data to encrypt");
    
    byte[] decryptedData = encryptionManager.decryptData(password, encryptedData);
    
    
Shared Preferences
=============

    SharedPreferencesService sharedPreferences = new SharedPreferencesService(getApplicationContext());
    
    sharedPreferences.setStringForKey("I'm the key", "I'm the value");
    
    String data = sharedPreferences.getStringForKey("I'm the key");
    
Secure Shared Preferences
=============

    SharedPreferencesService sharedPreferences = new SharedPreferencesService(getApplicationContext());
    
    EncryptionManager encryptionManager = EncryptionManager.getInstance();
    
    SecretKeySpec password = encryptionManager.createPassword("guest");
    
    sharedPreferences.setPassword(password);
    
    sharedPreferences.setSecureStringForKey("I'm the key", "I'm the value");



