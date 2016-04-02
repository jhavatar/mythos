# mythos
A Model-View-Presenter library for Android apps.

## Dependency
```groovy
dependencies {
	compile 'io.chthonic.mythos:mythos:0.8.4'
}
```

## Introduction
Mythos is a tiny library that simplifies managing an Android-UI MVP pattern. Each MVP relationship requires:
* implementing interface Presenter
* implementing interface Vu (named to not confuse with all Andorid's "views") 
* and calling a MVPDispatcher\<Presenter, Vu\> instance from UI callbacks. 
 
Optional prefab implementations of MVPActivity, MVPFragment and MVPLayout are available.

## Life cycles

### MVPDispatcher life cycle
1. attachPresenter()
2. attachVu()
3. onStartUI()
4. onResumeUI()
5. onPauseUI() -- possibly goto step 4
6. onStopUI() -- possibly goto step 3
7. destroyVu()  -- possibly goto step 2
8. destroyPresenter()

### Presenter life cycle
1. initialize()
2. attachVu()
3. onStartVu()
4. onResumeVu()
5. onPauseVu() -- possibly goto step 4
6. onStopVu() -- possibly goto step 3
5. detachVu() -- possibly goto step 2
6. onDestroy()



