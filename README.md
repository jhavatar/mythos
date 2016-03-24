# mythos
A Model-View-Presenter library for Android apps.

# Dependency
```groovy
dependencies {
	compile 'io.chthonic.mythos:mythos:0.8.0'
}
```

#Introduction
Mythos is a tiny library that simplifies managing an Android MVP pattern. Each MVP relationship requires:
* extending interface Presenter
* extending interface Vu (named to not confuse with all Andorid's "views") 
* and calling a MVPDispatcher\<Presenter, Vu\> instance from UI callbacks. 
 
Optional prefab implementations of MVPActivity, MVPFragment and MVPLayout are available.

