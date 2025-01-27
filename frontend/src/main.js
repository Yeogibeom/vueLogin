import { createApp } from 'vue';
import App from './App.vue';
import router from './router';

import 'bootstrap/dist/css/bootstrap.min.css'
import Store from "./store";
// import store from './store';
createApp(App)
    .use(router).use(Store)
    .mount('#app');