# MovieExercise - an app for tracking your movies

## This app aims to
    * Provide a way to discover new movies that you might like
    * Provide a way to search for movies
    * Login and sync the movies you select as watched with your TMDb account
    * View the details for any movie you choose
    
## Images


![ezgif-1-6ff3ff874f](https://user-images.githubusercontent.com/5503414/41813696-6e3223f6-7743-11e8-8db2-e49ad6f5d30e.gif)
![ezgif-1-13a7a0aba6](https://user-images.githubusercontent.com/5503414/41813669-3054b99a-7743-11e8-8e05-1cb221e78354.gif)
![screenshot_20180623-232034](https://user-images.githubusercontent.com/5503414/41813671-38f8d392-7743-11e8-8592-17ae0c962cd9.png)
![screenshot_20180623-232101](https://user-images.githubusercontent.com/5503414/41813672-39719e4e-7743-11e8-9fac-01985c64eebe.png)
![screenshot_20180623-232114](https://user-images.githubusercontent.com/5503414/41813674-3ac8a8aa-7743-11e8-93f3-93e96154bd88.png)


## Architecture 

The project is built with the MVP (Model - View - Presenter) pattern in mind. The Views are considered to be the Fragments/Activities while the Models are the data classes provided by the TMDb API backend. The Views handle the interactions with the UI elements while the Presenters handle all the logic/transformation required after fetching the data from the Models. 

### Dependencies

```implementation 'com.android.support:support-annotations:27.1.1'
implementation 'com.android.support:appcompat-v7:26.1.0'
implementation 'com.birbit:android-priority-jobqueue:2.0.1'
implementation 'com.android.support.constraint:constraint-layout:1.1.2'
implementation 'com.android.support:recyclerview-v7:26.1.0'
implementation 'com.android.support:cardview-v7:26.1.0'
implementation 'com.android.support:design:26.1.0'
implementation 'com.squareup.retrofit2:retrofit:2.4.0'
implementation 'com.squareup.retrofit2:converter-gson:2.4.0'
implementation 'com.squareup.picasso:picasso:2.71828'
implementation 'com.mcxiaoke.photoview:library:1.2.3'
implementation 'com.pnikosis:materialish-progress:1.5'
implementation 'de.hdodenhof:circleimageview:2.1.0'
```
Retrofit2 was used to communicate and authenticate with the TMDb API.  The Priority Job Queue was used execute all the time consuming tasks asynchronously in the background away from the UI thread. 
The Picasso library was used to fetch and cache the images pulled from the api. The rest of the dependencies are used to provide the material design UI design elements.  

### Building

Import the project in Android Studio, let Gradle take care of the dependencies and then run the app on your emulator or your phone. 

### Notes
    * In order to fetch an avatar, the user must have specified a Gravatar for his email address.
    * All movie gridviews allow for endless scrolling except for the search view which loads the first 20 results.
    * A restricted api was used via reflection to disable the bottom navbar item shifting. 
