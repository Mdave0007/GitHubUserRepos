# GitHub User Repository Explorer

## Overview

This Android application allows users to search for a GitHub user by their username, display the user's profile information, and list the public repositories owned by the user. The app also allows navigation to a detailed view of each repository, showing additional information such as the number of forks. If the total forks exceed 5000, a special badge is displayed.

<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/96644410-2efd-4d65-b968-991a8cc2a409" width="500"/></td>
    <td><img src="https://github.com/user-attachments/assets/1039fbca-5535-46e6-b6b3-3e578b4101d6" width="500"/></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/08eff3b7-7d7a-47ef-b859-9c9bf94226ff" width="500"/></td>
    <td><img src="https://github.com/user-attachments/assets/0f8baa38-9a2a-4a01-a7a8-4709c1d88c40" width="500"/></td>
  </tr>
</table>



## Features

- Search GitHub users by username.
- Display user profile information (name and avatar).
- Display a list of the user's public repositories, including the name and description.
- Navigate to a detailed screen of each repository to see more details.
- Display the total number of forks with a special badge if it exceeds 5000.

## Architecture

The app is built using the Model-View-ViewModel (MVVM) architecture pattern, which helps in separating concerns, improving testability, and making the app more modular.

### Layers

- **Model**: Contains the data classes that represent the GitHub User and Repository.
- **ViewModel**: Manages UI-related data and business logic. It interacts with the repository to fetch data and exposes it to the UI using `StateFlow`.
- **Repository**: Acts as a single source of truth for fetching data. It handles network operations and abstracts the data source from the ViewModel.
- **View**: Built using Jetpack Compose, it displays the UI components and observes data changes from the ViewModel.

## Libraries Used

- **Jetpack Compose**: For building the UI declaratively.
- **Retrofit**: For making network requests to the GitHub API.
- **Gson**: For JSON serialization and deserialization.
- **Kotlin Coroutines**: For managing asynchronous operations.
- **StateFlow**: For observing data changes in a lifecycle-aware manner.
- **Jetpack Navigation**: For handling navigation between screens.
- **Mockito**: For mocking dependencies in unit tests.
- **JUnit**: For running unit tests.

## Installation

### Prerequisites

- Android Studio Arctic Fox or later.
- Gradle 7.0 or later.
- Minimum SDK version 21.

### Steps to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/github-user-repository-explorer.git
2. **Open the project in Android Studio.**
3. **Sync the project** to download all dependencies.
4. **Run the app** on an emulator or a physical device.

## API Details

The app uses the following GitHub API endpoints:

### Get User Information

- **URL**: `https://api.github.com/users/{userId}`
- **Response**: Contains user details like name and avatar URL.

### Get User Repositories

- **URL**: `https://api.github.com/users/{userId}/repos`
- **Response**: Contains a list of repositories, including the name, description, number of stars, and forks.

## Testing

The application includes both unit tests and UI tests:

### Unit Tests

- **ViewModel Tests**: Tests the ViewModel logic to ensure data is fetched correctly and error handling works.
- **Repository Tests**: Tests the repository's network calls and data handling logic.

### UI Tests

- **Main Screen Tests**: Verifies the correct display of user information and repositories list.
- **Navigation Tests**: Ensures that navigation between the main screen and the detail screen works as expected.

### To run the tests:

1. Open the `Test` panel in Android Studio.
2. Right-click on `All Tests` and select `Run`.

## Known Issues and Limitations

- The app currently does not handle pagination for repositories. Only the first set of repositories is loaded.
- Error messages are displayed as plain text and can be improved with better UI/UX design.
- The app does not include offline caching. Data will be lost when the app is closed.

## Future Enhancements

- **Pagination**: Implement pagination to handle users with a large number of repositories.
- **Offline Caching**: Use Room or a similar library to cache data for offline use.
- **Error Handling**: Improve error handling by showing more user-friendly messages and retry options.
- **Testing**: Increase test coverage, including edge cases and more UI interactions.

## Conclusion

This application demonstrates the use of modern Android development practices, including Jetpack Compose and MVVM architecture. The project is modular, testable, and adheres to best practices for Android development.
