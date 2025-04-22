# City Weather Search Application

This project is a full-stack application that allows users to search for cities starting with a specific letter. The application consists of a Kotlin backend and a React TypeScript frontend.

## Project Structure

```
weather-city-search/
├── backend/               # Kotlin Spring Boot backend
│   ├── src/               # Source code
│   └── build.gradle.kts   # Gradle build configuration
└── frontend/              # React TypeScript frontend
    ├── public/            # Static files
    ├── src/               # Source code
    └── package.json       # NPM configuration
```

## Requirements

- Java 17 or higher
- Node.js 14 or higher
- npm or yarn

## Running the Backend

1. Navigate to the backend directory:
   ```
   cd weather-city-search/backend
   ```

2. Build the project:
   ```
   ./gradlew build
   ```

3. Run the application:
   ```
   ./gradlew bootRun
   ```

   The backend will start on http://localhost:8082

## Running the Frontend

1. Navigate to the frontend directory:
   ```
   cd weather-city-search/frontend
   ```

2. Install dependencies:
   ```
   npm install
   ```
   or if you're using yarn:
   ```
   yarn install
   ```

3. Start the development server:
   ```
   npm start
   ```
   or with yarn:
   ```
   yarn start
   ```

   The frontend will start on http://localhost:3000

## API Endpoints

- `GET /api/cities/count?letter={letter}` - Returns the count of cities that start with the specified letter.

## How to Use

1. Start both the backend and frontend applications as described above.
2. Open your browser and navigate to http://localhost:3000
3. Enter a letter in the input field and click the "Search" button.
4. The application will display the number of cities starting with that letter.

## Technologies Used

### Backend
- Kotlin
- Spring Boot
- Spring WebFlux
- Gradle

### Frontend
- React
- TypeScript
- Axios for API requests
- CSS for styling

## Visuals

<img width="1003" alt="image" src="https://github.com/user-attachments/assets/ef56bbf7-6659-4d46-8938-fc239d8e40ae" />

<img width="1001" alt="image" src="https://github.com/user-attachments/assets/1d70771f-684c-4e87-8486-81735a4f0590" />
