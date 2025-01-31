import { createApp } from 'vue';
import App from './App.vue';
import router from './router';

import 'bootstrap/dist/css/bootstrap.min.css'

import store from "./store";
import axios from "axios";


axios.interceptors.request.use(
    (config) => {
        const token = store.getters.getJwt; // Vuex에서 JWT 가져오기
        if (token) {
            config.headers.Authorization = `Bearer ${token}`; // Authorization 헤더에 JWT 추가
        }
        return config;
    },
    (error) => Promise.reject(error)
);
// 앱 로드 시 localStorage에서 JWT 복원
const savedJwt = localStorage.getItem('jwt');
if (savedJwt) {
    store.dispatch('setJwt', savedJwt);
}

// import store from './store';
createApp(App)
    .use(router).use(store)
    .mount('#app');