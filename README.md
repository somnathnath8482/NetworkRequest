# NetworkRequest 
[![](https://jitpack.io/v/somnathnath8482/NetworkRequest.svg)](https://jitpack.io/#somnathnath8482/NetworkRequest)

<p align="center">An Android library that returns real paths from Uri's</p>

<p align="center"><img width= "200" src="https://www.globalsign.com/application/files/3916/0397/8810/iStock-833750208.png"></p>

</br>
Add NetworkRequest to your project:
---

Add the following in your root build.gradle at the end of repositories:

```java
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
    
Then, add the dependency, in your app level build.gradle:

```java
android{
   packagingOptions {
        resources.excludes.add("META-INF/*")
    }
}
dependencies {
        implementation 'com.github.somnathnath8482:NetworkRequest:v1'
}
```

Now Create Instance in Your Class 

```

private Main main;

 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
       
        main = new Main(this, this, this);

    }
```
And Implement these Methosd 
```
 @Override
    public void OnEror(String url, String code, String message) {

        //todo

    }

    @Override
    public void OnSucces(String url, String code, String res) {
      
        binding.textView.setText(res);
        //todo

    }
```
Now all is set 

1. To Create A Get Request 
```
 HashMap<String, String> map = new HashMap<>();
 main.CALLGetRequest("https://jsonplaceholder.typicode.com/comments?postId=1", Conntants.AUTHHEADER,map);
 map may be null.
 auth may be "".
```
    
2. To Create A Post  Request Json
```
 JsonObject object;
 .......
main.CallPostRequestJSon("https://artixdevl.000webhostapp.com/api/test.php",object);
  auth may be "".
```

3.To Create A Post Request FormData

```
     HashMap<String, Object> map = new HashMap<>();
            map.put("email", "email@gmail.com");
            map.put("password", "123e456");
            map.put("new_password", "123123123");
            map.put("password_confirmation", "123123123");

           main.CallPostRequestFormdata("https://artixdevl.000webhostapp.com/api/test.php",Conntants.AUTHHEADER,map);
           
```

4. To Create a Multipart Request with single/multiple File 
```
  HashMap<String, Object> map = new HashMap<>();
            map.put("Name", "Somnath");
            map.put("Namcvxve", "Somnath");
            map.put("Naxme", "Somsnath");
            map.put("Namcsce", "Somnath");
            map.put("Nadme", "Somnath");
            List<File> f = new ArrayList<>();
            f.add(new File(path));
            main.setShow_Progress(true);
            main.CAllMultipartRequest("https://artixdevl.000webhostapp.com/api/test.php",
                    Conntants.AUTHHEADER,
                    map
                    ,
                    f,
                    "img");
        }
        
        
        

```
If Multiple File
```
HashMap<String, Object> map = new HashMap<>();
            map.put("Name", "Somnath");
            map.put("Namcvxve", "Somnath");
            map.put("Naxme", "Somsnath");
            map.put("Namcsce", "Somnath");
            map.put("Nadme", "Somnath");
            List<File> f = new ArrayList<>();
           for (int i=0;i<paths.size();i++){
               f.add(new File(paths.get(i)));
           }
            main.setShow_Progress(true);
            main.CAllMultipartRequest("https://artixdevl.000webhostapp.com/api/test.php",
                    Conntants.AUTHHEADER,
                    map
                    ,
                    f,
                    "img[]");

```
<h2 align="center"><b>** note while multiple file the key must be with [] signe , if single file then key may be any string;</b></h2>


Please do test if any issue happen plese mail report issue or mail to jarves8484@gmail.com
Thank You Injot it 



