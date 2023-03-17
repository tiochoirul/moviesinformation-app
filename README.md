# MoviesInformationApp

[![Arifaizin](https://circleci.com/gh/arifaizin/MySimpleCleanArchitecture.svg?style=svg)](https://circleci.com/gh/arifaizin/MySimpleCleanArchitecture)

This is the final submission to graduate from the Become an Android Developer Expert class organized by IDCampXdicoding. This is an Android application written in Kotlin that allows users to browse and search for information about movies. The app uses the TheMovieDb API to retrieve data about movies such as title, year, plot, and poster image. Users can save their favorite movies to a local database and access them later.

## Getting Started
Instructions on how to get a copy of the project up and running on your local machine.

### Prerequisites
- Android Studio
- JDK 19

### Instaling
Instructions on how to install and run the project on a local machine.

1. Clone the repository. 
    ```bash
    git clone https://github.com/tiochoirul/MoviesInformationApp.git
    ```
2. Open the project in Android Studio.
3. Build and run the project on an emulator or physical device.

## Features
- Browse movies using the TheMovieDb API
- View movie details such as title, year, plot, and poster image
- Save favorite movies to a local database
- View a list of saved movies
- Delete saved movies from the list

## Libraries
- Android X
- Coroutine
- Retrofit
- Glide
- Room

## Architecture
The application is built using the following architecture:

- Clean Architecture: The application is divided into three layers: presentation, domain, and data. Each layer has its own set of responsibilities and is isolated from the other layers.
- MVVM: The presentation layer uses the MVVM (Model-View-ViewModel) architecture pattern. The view is responsible for displaying data, the view model is responsible for providing data to the view, and the model is responsible for providing data to the view model.
- Reactive Programming: The application uses Coroutine Flow for reactive programming. Coroutine Flow is used to handle asynchronous operations and data streams.
- Modularization: The application is divided into modules: app, core, data, and domain. Each module has its own set of responsibilities and can be developed and tested independently.

## Contributing
If you would like to contribute to the project, please follow these steps:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature/new-feature`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add some feature`).
5. Push to the branch (`git push origin feature/new-feature`).
6. Create a new pull request.

## Usage
1. Launch the app
2. Browse or search for a movie in the list
3. Tap on a movie to view its details
4. To save a movie to the local database, tap on the "Love" button at the bottom of the screen
5. To view a list of saved movies, tap on the "Saved Movies" button in the navigation drawer
6. To delete a saved movie from the list, swipe left on the movie item and tap on the "Delete" button

## License
This project is licensed under the <a href="https://opensource.org/licenses/MIT">MIT License</a>.

## Contact Information
If you have any questions or feedback about Dicoding Story App, please contact us at mtio871@gmail.com.

