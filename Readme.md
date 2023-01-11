[![github action](https://github.com/0x384c0/Experiments-Android/actions/workflows/tests.yml/badge.svg)](https://github.com/0x384c0/Experiments-Android/actions/workflows/tests.yml)
### Description
A sample Android app that shows recent posts from internet forum.

<img src="/media/screenshot.jpg" height="300">

### Project Structure
Project structure was generated using [ChatGPT](https://chat.openai.com/chat)
```
app - android app, that hosts all features
features - all app features
  feature_name - example of single feature
    data - module, that retrieves and stores data from the app's data sources
      entity - models, used in data layer
      mapper - maps data between domain and data
      repository - wrapper for API or database
      service - API implementation
    domain - module, that contains the core business logic of the app
      model - models, used in domain layer
      repository - wrapper for accessing data
      usecase - business logic omplementation
    presentation - module, that contains the code that adapts the domain layer to the Android framework
      components - App views
      data - models, used in ui layer
      mapper - maps data between domain and presentation
      navigation - controls transitions between screens
```
### Modules
App has multiple [features](/features)
Each feature split in to 3 modules
1. [presentation](/features/reddit_posts/presentation/) - contains Android Presentation Layer
1. [domain](/features/reddit_posts/domain/) - contains Domain layer with business logic
1. [data](/features/reddit_posts/data/) - contains Data layer with REST API requests

### Communication between layers
1. [UI](/features/reddit_posts/presentation/src/main/java/com/example/presentation/components/screens/HomeScreen.kt) calls functions from [ViewModel](/features/reddit_posts/presentation/src/main/java/com/example/presentation/components/screens/HomeViewModel.kt).
1. ViewModel executes Use cases from [Interactor](/features/reddit_posts/domain/src/main/java/com/example/domain/usecase/RedditPostsInteractorImpl.kt).
1. Use case obtains data from [Repository](/features/reddit_posts/data/src/main/java/com/example/data/repository/RedditRepositoryImpl.kt)
1. Repository returns data from a [Api](/features/reddit_posts/data/src/main/java/com/example/data/service/RedditApiService.kt).
1. Information flows back to the UI to be displayed.
### Test coverage
1. [data](/features/reddit_posts/data/src/test/java/com/example/data/mapper/)
1. [domain](/features/reddit_posts/domain/src/test/java/com/example/domain/)
1. [presentation](/features/reddit_posts/presentation/src/test/java/com/example/presentation/mapper/)
1. [ui](/features/reddit_posts/presentation/src/androidTest/java/com/example/presentation/components/screens/)
### Used Tecnologies
1. [Jetpack Compose](https://developer.android.com/jetpack/compose)
1. [Coroutines](https://kotlinlang.org/docs/coroutines-overview.htm)
1. [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
1. [Retrofit](https://square.github.io/retrofit/)
1. [Gradle Kotlin DSL ](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
