# Movies

Movies is a sample app the displays a list of movies from themoviedb API.

<p align="center">
<img src="https://github.com/minafkamel/movies/blob/master/List.png" alt="musically flow" width="400" height="850">
<img src="https://github.com/minafkamel/movies/blob/master/Search.png" alt="musically flow" width="400" height="850">
<img src="https://github.com/minafkamel/movies/blob/master/Details.png" alt="musically flow" width="400" height="850">
</p>

### Features:
- A list of movies with infinite scrolling. Each scroll loads 20 movies. A movie item shows a title and a date.
- Search: When an a movie title is written in the search bar, the movies are filtered according to the value entered.
- Details: When an item is pressed, the details are shown. These are: photo, name, date and overview

### App flow
- When the app is opened, the API is called with `page` value = 0.
- The API response is cached in `ApiCache` and the `_moviesFlow` emits the movies which are then displayed.
- When a movie item is clicked, the `id` is passed to the details screen then the movie is retrieved from `ApiCache` and the details are shown. This ensure a unidirectional flow from data to UI without while optimising API calls.
- When the user types in the search box, the cached movies in `ApiCache` are filtered by title and then `_moviesFlow` emits.

### Security
For security reason, the `API_KEY` is added to the Build Config. To run the app, `API_KEY=xxxx` must be set in the `local.properties` file.

### Tests
As a sample, two tests were added for `LocalDataSource` for key features like retreving the movies and filtering.

### Tech stack
- Jetpack Compose
- Coroutines
- Retrofit
- Hilt
- Coil


