# Social Learning Application

A mobile learning app built with **Android Studio (Java)**, **Firebase**, and **Navigation Component**.  
The app allows users to register/login, take quizzes, track tasks, view profiles, and securely log out.  

---

## Features

- **User Authentication**
  - Firebase Email/Password login & registration
  - Secure logout functionality

- **Quiz System**
  - Multiple choice quizzes with predefined questions
  - Final result screen showing score
  - Restart quiz option using Navigation Component

- **Profile Section**
  - Display user **name** and **email**
  - Show **quiz attempts count** (from Firebase)
  - Show **tasks count** (from Firebase)
  - Logout button that signs out & closes the app

- **Tasks**
  - Task management backed by Firebase Database
  - Task count shown in profile

---

## Tech Stack

- **Language:** Java  
- **UI:** XML Layouts, Material Design  
- **Architecture:** MVVM (Repositories for Quiz & Task data)  
- **Navigation:** AndroidX Navigation Component  
- **Database & Auth:** Firebase Authentication & Realtime Database  

---

## Project Structure

```
com.rzjaffery.sociallearningapplication
│
├── data
│ └── repository
│ ├── QuizRepository.java # Handles Firebase quiz attempts
│ └── TaskRepository.java # Handles Firebase tasks
│
├── ui
│ ├── main
│ │ └── ProfileFragment.java # Profile screen with logout
│ │
│ └── quiz
│ ├── QuizFragment.java # Quiz questions screen
│ └── ResultFragment.java # Final results screen with restart option
│
├── res
│ ├── layout
│ │ ├── fragment_profile.xml
│ │ ├── fragment_quiz.xml
│ │ └── fragment_result.xml
│ └── navigation
│ └── nav_graph.xml # App navigation graph
```


---

##  Firebase Setup

1. Create a new project in [Firebase Console](https://console.firebase.google.com/).  
2. Enable **Email/Password Authentication** in Firebase Authentication.  
3. Create a **Realtime Database** and set rules:
   ```json
   {
     "rules": {
       ".read": "auth != null",
       ".write": "auth != null"
     }
   }
4.Download the google-services.json file and place it in:
```
app/google-services.json
```
5.Add Firebase dependencies in app/build.gradle:
```
implementation ("com.google.firebase:firebase-auth:22.1.2")
implementation ("com.google.firebase:firebase-database:20.3.0")
implementation ("androidx.navigation:navigation-fragment:2.7.7")
implementation ("androidx.navigation:navigation-ui:2.7.7")
```
## Screenshots
### Login Screen 

<img width="440" height="920" alt="Screenshot_20250902_002101" src="https://github.com/user-attachments/assets/c8505ab8-c47d-43c7-89cb-b0b34aa6250f" />

### Quiz Screen 

<img width="440" height="920" alt="Screenshot_20250902_002530" src="https://github.com/user-attachments/assets/71ec0cf3-5fb1-4ce9-95f5-8419751c27b8" />

### Result Screen 

<img width="440" height="920" alt="Screenshot_20250902_002547" src="https://github.com/user-attachments/assets/20b7e712-45ae-496e-ad71-0e539207a8d7" />

### Profile Screen 

<img width="440" height="920" alt="Screenshot_20250902_002744" src="https://github.com/user-attachments/assets/4f124d60-bdbc-4f65-8b50-4ba0b51f00b4" />

## Future Improvements

- Add Leaderboard for top quiz scores
- Implement Task CRUD (Create, Read, Update, Delete)
- Dark mode support
- Push notifications for new quizzes
