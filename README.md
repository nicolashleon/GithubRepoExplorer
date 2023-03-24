# GithubRepoExplorer

Github Repo Explorer

This app shows GitHub repositories. The App contains a list screen and detail screen where the information about the repos is displayed.

The app to caches the data in the database for the repository and its owner information to be able to working offline.
The cached data gets updated once the connection is available (can be refreshed by executing a pull to refresh**).
Images for the owner’s avatars are cached locally but not in the database using a third party lib.

Both screens can handle empty, loading and error states to provide a good UI/UX.
Repository List Screen

The list screen shows (paginated) list of repos with the following data per item:

    Name
    Owner’s avatar image
    Visibility
    And if the repo is private or public

Repository Detail Screen

The detail screen shows:

    Name
    Full name
    Description
    Owner’s avatar image
    Visibility
    If the repo is private or public
    And a CTA button which opens the repository website in an external browser

You can find a GIF showing the demo of the app here https://github.com/nicolashleon/GithubRepoExplorer/blob/feature/add-repository-list-and-details/app/src/main/res/values/Github%20Repo%20Explorer.gif

Configuration

To run the application make sure that you configure your system’s gradle properties file (add one if you don’t have it)
where you set the github personal access token following the pattern. There is no need to add any type of string markings such us " or '. Just copy and paste the plain access token that github provides.
githubApiKey=this_is_my_key
The application will ensure this key is used to authenticate the requests to avoid an HTTP 401 or HTTP 403 when asking for the data.

This is the prefered approach to avoid leaking credentials into the Github.

You can find further information in https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token
Architecture and Design

This app follows the MVVM approach
Compatibility, Frameworks and Libs

    Android minSdk 23 and targetSdk 33
    UI built using Jetpack Compose and Material Design 3
    Network: Retrofit, Okhttp, Gson for serialization and deserialization
    Database: Room
    Pagination: Jetpack Paging Lib v.3
    Live Data and Coroutines for threading and background execution for DB and network calls

Further improvements

    Track the network state to automatically refresh the data without direct user involvement once the connection is available after internet connectivity issues
    Keep state of the list when navigating across screens to provide a better UX.
