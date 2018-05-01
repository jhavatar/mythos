[![Release](https://jitpack.io/v/jhavatar/mythos.svg)](https://jitpack.io/#jhavatar/mythos)

# mythos
A simple Model-View-Presenter library for Android apps.

## Setup
```groovy
repositories {
    maven { url "https://jitpack.io" }
}
dependencies {
    implementation "com.github.jhavatar:mythos:1.0.5"
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



