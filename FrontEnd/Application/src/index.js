import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import './i18n/i18n';
// import { I18nextProvider } from 'react-transition-group';
// import { initReactI18next } from "react-i18next";

ReactDOM.render(
  <React.StrictMode>
    {/* <I18nextProvider i18n={i18n}> */}
      <App />
    {/* </I18nextProvider> */}
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
