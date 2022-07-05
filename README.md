# Giphy in a Jiffy

Sample app for interview purposes.

## Setup
Procure a Giphy API key from https://developers.giphy.com/, your local supermarket, or a shady character in an alley, then add a line to the local.properties file, like:

`giphyApiKey=Ex4mpl3`

## Considerations
This version makes use of the Giphy SDK to display the list of GIFs and individual GIFs on the detail screen. If desired, I could remove uses of the SDK, perhaps instead using tools like:
- Retrofit
- The Jetpack Paging library
- Kotlin Flow
- The repository pattern
- Glide
But that would take more time. And as Groucho Marx famously said, "time flies like an arrow. Fruit flies like a banana."

There are no automated tests, due to the simplicity of the functionality and to time constraints.

Lower priority enhancements could include (if I stopped using the SDK) debouncing search-as-you-type or caching results from previous searches.

## Features
~~Stealing~~ Taking inspiration from the Giphy Android SDK example project helped to significantly simplify the work. So, apart from searching as you type, hiding the keyboard on enter or clicking the search button, and resetting to trending GIFs when you clear the search, what does this simple app do?

- Media object is passed between Fragments using the Navigation graph
- Updates action bar title based on GIF title through use of a ViewModel and LiveData
- Giphy API key is read from local.properties file, so if the code is shared with the world, the API key is not
- R8 obfuscation is enabled, to make it harder to read the API key from the signed APK (note: multidex needed to be enabled for this to work, since the minimum SDK is kept at 19)
