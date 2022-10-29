# NetworkRequest 
[![](https://jitpack.io/v/somnathnath8482/NetworkRequest.svg)](https://jitpack.io/#somnathnath8482/NetworkRequest)

<p align="center">An Android library that returns real paths from Uri's</p>

<p align="center"><img src="https://www.globalsign.com/application/files/3916/0397/8810/iStock-833750208.png"></p>

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
    
