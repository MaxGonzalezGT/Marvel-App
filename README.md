# Marvel App

The app displays a list of Marvel characters and enables the user to see detailed information about each character individually.


## Features

- Displays a scrollable list of Marvel characters from API.
- Marvel list is searchable.
- Shows detailed information about each character individually.
- Works offline with local database.
- Internet verification. 
- Light/dark mode.


## Architecture applied

- Clean Architecture
## Tools 

- Material Components
- Navigation Component
- Room components
- DataStore
- Recyclerview
- Retrofit
- Dagger - Hilt
- Coroutines
- Lifecycle
- Image library Coil
- Gson
- Jsoup
- Unit Test Components

## Installation

- Clone project 
- Open project in Android Studio
- Get API Key in https://developer.marvel.com/docs
- Go back to project to util/Constants
- Change BASE_URL, API_KEY and PRIVATE_KEY.

```bash
  // Marvel API
        const val BASE_URL = "BASE_URL"
        const val API_KEY = "API_KEY"
        const val PRIVATE_KEY = "PRIVATE_KEY
```
- Run project

## Screenshots

- List of Marvel Characters
![Screenshot_20220519_130157](https://user-images.githubusercontent.com/105871309/169362318-be1773cc-5ebf-4dc9-8cd1-70be4f7e269d.png)
- Search List of Marvel Characters
![Screenshot_20220519_132707](https://user-images.githubusercontent.com/105871309/169362325-9b9aac2b-43dd-4b8a-b05f-b9dc1cfe4f5f.png)
- Detail view of Marvel Character
![Screenshot_20220519_132636](https://user-images.githubusercontent.com/105871309/169362324-bd404d63-7881-4ce7-a8fe-86237bbcd05c.png)
- Dark mode
![Screenshot_20220519_125959](https://user-images.githubusercontent.com/105871309/169362305-f27696d9-dc10-4819-a574-fbdea7627c0c.png)
![Screenshot_20220519_130044](https://user-images.githubusercontent.com/105871309/169362312-62669fba-51a7-45f2-9772-91a30ca59284.png)
![Screenshot_20220519_130017](https://user-images.githubusercontent.com/105871309/169362308-993a10d5-5d66-43ef-803d-4a105dcd6546.png)
- If app is installed without internet it display an internet message
![Screenshot_20220519_133051](https://user-images.githubusercontent.com/105871309/169362327-2eebb873-4df5-4e24-be80-555d4b9b8af3.png)

## Demo

https://user-images.githubusercontent.com/105871309/169860528-33382af2-fa5d-4436-a076-ee529cf670b2.mp4
