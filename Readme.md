Project structure was generated using [ChatGPT](https://chat.openai.com/chat)

Prompt 
```
write a project stricture with only folders of android app, written using clean architeure approaches from uncle bob
```
Answer
```
my-app/
  app/
    src/
      androidTest/
        java/
          com.example.myapp/
      main/
        java/
          com.example.myapp/
        res/
          drawable/
          layout/
          values/
          AndroidManifest.xml
      test/
        java/
          com.example.myapp/
    build.gradle
  domain/
    src/
      main/
        java/
          com.example.myapp.domain/
            model/
            repository/
            usecase/
        test/
          java/
            com.example.myapp.domain/
              model/
              repository/
              usecase/
    build.gradle
  presentation/
    src/
      main/
        java/
          com.example.myapp.presentation/
            model/
            presenter/
            view/
        res/
          layout/
          values/
        test/
          java/
            com.example.myapp.presentation/
              model/
              presenter/
              view/
    build.gradle
  data/
    src/
      main/
        java/
          com.example.myapp.data/
            entity/
            mapper/
            repository/
            service/
        test/
          java/
            com.example.myapp.data/
              entity/
              mapper/
              repository/
              service/
    build.gradle
  build.gradle
  settings.gradle
```

This project structure separates the different layers of the app into different modules: app, domain, presentation, and data.

The app module contains the Android-specific code, such as the MainActivity and the app's resources, such as layouts, strings, and styles.

The domain module contains the core business logic of the app, such as the User model and the GetUsers use case. This module is independent of the Android framework and can be tested without an Android device or emulator.

The presentation module contains the code that adapts the domain layer to the Android framework, such as the UserView and the UserPresenter. This module depends on the domain module but is independent of the data layer.

The data module contains the code that retrieves and stores data from the app's data sources, such as a remote API or a local database. This module depends on the domain module and provides an implementation of the repositories defined in the domain layer.

Each module has a separate build.gradle file that defines the dependencies for that module. The root build.gradle file defines the overall settings for the project, such as the Android plugin and the target SDK version. The settings.gradle file defines the modules that belong to the project.