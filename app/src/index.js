import React from 'react';
import ReactDOM from 'react-dom/client';
import 'bootstrap/dist/css/bootstrap.min.css';
import './css/header.css'
import './css/index.css';
import './css/loginPage.css';
import './css/footer.css';
import './css/singleCoursePage.css';
import './css/CoursePage.css';
import './css/searchComponent.css';
import './css/ProfilePage.css';
import './css/homePage.css';
import './css/mentorPage.css';
import './css/menteePage.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import store, { Persistor } from './app/store'
import { Provider } from 'react-redux';
import { GoogleOAuthProvider } from '@react-oauth/google';
import { PersistGate } from 'redux-persist/integration/react';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <GoogleOAuthProvider clientId='310536116903-4oolc727rmg62b4qsf58p8a3i76o4pfq.apps.googleusercontent.com'>
    <Provider store={store}>
      <PersistGate loading={null} persistor={Persistor}>
        <App />
      </PersistGate>
    </Provider>
  </GoogleOAuthProvider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
