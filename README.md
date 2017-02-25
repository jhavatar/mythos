# mythos
A simple Model-View-Presenter library for Android apps.

## Dependency
```groovy
dependencies {
    compile 'io.chthonic.mythos:mythos:0.9.1'
}
```

## Introduction
Mythos is a tiny library that simplifies managing an Android-UI MVP pattern. Each MVP relationship requires:
* implementing interface Presenter
* implementing interface Vu (named to not confuse with all Android's "View" class)
* and calling a MVPDispatcher\<Presenter, Vu\> instance from basic UI callbacks.
 
Optional prefab implementations of MVPActivity, MVPFragment and MVPLayout are available.

## Life cycles

### MVPDispatcher life cycle
1. restorePresenterState()
2. createVu()
3. linkPresenter()
4. unlinkPresenter()
5. destroyVu()
6. savePresenterState()

### Presenter callbacks
- onLink()
- onUnlink()
- onDestroy()

### Vu callbacks
- onCreate()
- onDestroy()



