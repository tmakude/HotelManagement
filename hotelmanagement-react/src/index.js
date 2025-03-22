import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';



const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

if (typeof window !== 'undefined') {
    const resizeObserverErrorHandler = (e) => {
        if (e.message.includes('ResizeObserver loop limit exceeded')) {
            // Suppress ResizeObserver loop errors
            e.stopImmediatePropagation();
        }
    };
    window.addEventListener('error', resizeObserverErrorHandler);
}
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
