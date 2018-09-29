[![Release](https://jitpack.io/v/jhavatar/mythos.svg)](https://jitpack.io/#jhavatar/mythos)

# mythos
A simple Model-View-Presenter library for Android apps.

## Setup

Core requirements for the basic implementation:
```groovy
repositories {
    maven { url "https://jitpack.io" }
}
dependencies {
    implementation "com.github.jhavatar.mythos:mvp:1.1.5"
}
```

For a [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) based implementation, also include
```groovy
    implementation "com.github.jhavatar.mythos:viewmodel:1.1.5"
```

## Introduction
Mythos is a tiny library that simplifies managing an Android-UI MVP pattern. Each MVP relationship requires:
* implementing interface Presenter
* implementing interface Vu (named to not confuse with all Android's "View" class)
* and calling a MVPDispatcher\<Presenter, Vu\> instance from the basic UI lifecycle callbacks.
 
Optional prefab implementations of MVPActivity, MVPFragment and MVPLayout are also available.

See the Kotlin or Java example in the project for a guide.

## Life cycles

### MVPDispatcher typical life cycle
1. restorePresenterState()
2. createVu()
3. linkPresenter()
4. unlinkPresenter()
5. destroyVu()
6. savePresenterState()
7. destroyPresenterIfRequired()

### Presenter callbacks
- onLink()
- onUnlink()
- onSaveState()
- onDestroy()

### Vu callbacks
- onCreate()
- onDestroy()



