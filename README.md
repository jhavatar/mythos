[![Release](https://jitpack.io/v/jhavatar/mythos.svg)](https://jitpack.io/#jhavatar/mythos)

# ⚠️ DISCOURAGED to use this library with new projects

# mythos
A simple Model-View-Presenter library for Android apps.

## Setup

Core requirements for the basic implementation:
```groovy
repositories {
    maven { url "https://jitpack.io" }
}
dependencies {
    implementation "com.github.jhavatar.mythos:mvp:1.2.0"
}
```

For a [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) based implementation, also include
```groovy
    implementation "com.github.jhavatar.mythos:viewmodel:1.2.0"
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

## Change log
v1.2.0
- Switch from support lib to AndroidX

v1.1.6
- Removed deprecated `PresenterLoad` and `PresenterCacheLoaderCallback`
- Libary updates

v1.1.5
- Added helper method `getViewModelPresenterCache` on `PesenterCacheViewModel` to easily create and get PresenterCache associated with activity or fragment 
- Added method `firstLinkWithVu` on `Presenter`
- Library updates

v1.1.4
- Added `PresenterCacheViewModel` which uses Android architecture's `ViewModel`
- Added `PresenterCacheLazy`which lazy-loads the `Presenter`
- Removed `FragmentWrapper` since standard `Fragment` has been deprecated.
- Deprecated `PresenterLoad` and `PresenterCacheLoaderCallback`
- Libary updates







