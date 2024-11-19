# Mobile Wallet Android Application

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://shields.io)  
[![License](https://img.shields.io/badge/license-MIT-blue)](LICENSE)

## About the Project

This project is an interview solution for **Compulynx**, focusing on building a mobile wallet application using modern Android development practices. The app provides essential mobile wallet features, including user authentication, balance inquiry, money transfer, and transaction history.

---

## Features

### 1. **Login Page**
- Allows login using `Customer ID` and `PIN`.
- On successful login:
  - Fetches customer details (Name, ID, Account, and Email) via the API.
  - Stores the details locally using Room Database.
- Displays a **Toast message** if login fails.
- Redirects to the **Home Page** upon successful login.

### 2. **Home Page**
- Displays a personalized welcome message with the customer's name.
- Features navigation buttons:
  - **Check Balance**: Fetch and display the account balance.
  - **Send Money**: Navigate to a form to send money.
  - **View Profile**: Display stored customer details.
  - **View Statement**: Fetch and display the last 100 transactions.
  - **View Last Transactions**: Show locally stored transactions.
  - **Logout**: Logs out and redirects to the Login Page.

### 3. **Check Balance**
- Fetches the current account balance via the API.
- Displays the balance in a user-friendly format.

### 4. **Send Money**
- Provides a form with fields for:
  - `Account To`: Target account for the transfer.
  - `Amount`: Amount to be sent.
- Saves transaction details locally before sending the request to the API.
- Displays:
  - **Success Alert**: On a successful transaction, redirects to the Home Page.
  - **Failure Toast**: On failure, redirects back to the Home Page.

### 5. **View Profile**
- Displays customer details (Name, ID, Account, and Email) stored in the Room Database.
- Includes a **Home Button** to navigate back to the Home Page.

### 6. **View Statement**
- Fetches the last 100 transactions from the API.
- Displays transactions in a list, with a total amount at the bottom.

### 7. **View Last Transactions**
- Displays locally stored transactions saved in the Room Database.
- Shows the `Account To` and `Amount` fields for each transaction.

---

## Prerequisites

- Android Studio Bumblebee or newer.
- Kotlin 1.5+ (required for Jetpack Compose).
- A device or emulator running Android 8.0 (API level 26) or higher.

---

## Technologies Used

### Android
- **Programming Language**: Kotlin
- **UI Framework**: Jetpack Compose for a modern declarative UI.
- **Local Storage**: Room Database for saving user and transaction data locally.
- **Networking**: Retrofit with OkHttp for API calls.
- **Dependency Injection**: Hilt for streamlined dependency management.
- **JSON Parsing**: Kotlin Serialization for efficient JSON handling.
- **Image Loading**: Coil for asynchronous image rendering.
- **Animation**: Lottie for interactive animations.
- **Navigation**: Android Navigation Component for intuitive screen transitions.

---

## Setup and Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/morarafrank/CompulynxInterview.git
   cd CompulynxInterview
   ```
2. Open the project in **Android Studio**.
3. Sync the project with Gradle files.
4. Set up an emulator or connect a physical device.
5. Run the app using the **Run** button in Android Studio.

---

## Project Structure

```
com.morarafrank.compulynxinterview
├── data                # Data-related components
│   ├── local           # Room Database entities and DAOs
│   ├── remote          # Retrofit services and models
│   │   ├── model       # API data models
│   │   └── CompulynxService # Retrofit interface for API calls
│   └── repo            # Repository for data handling
├── di                  # Dependency Injection modules
│   ├── AppModule.kt    # Provides app-level dependencies
│   ├── NetworkModule.kt # Provides networking-related dependencies
│   └── SharedPrefModule.kt # Provides shared preferences dependencies
├── domain              # Core domain models and business logic
├── ui                  # User interface components
│   ├── composables     # Reusable Jetpack Compose UI elements
│   ├── navigation      # Navigation graph and routes
│   ├── screens         # Individual screens (e.g., Login, Home, Profile)
│   ├── theme           # App theming and styling
│   └── viewmodel       # ViewModel classes for UI state management
├── utils               # Utility classes and extensions
├── MainActivity.kt     # Main entry point of the app
└── CompulynxInterviewApp.kt # Application class for initialization
```

---

## Future Improvements
- Enhanced UI/UX design using Material 3 theming.
- Support for additional languages and currencies.
- Offline mode with synchronization for unsent transactions.

--- 

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```
Contributions are welcome.
