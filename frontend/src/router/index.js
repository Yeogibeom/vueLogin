import { createRouter, createWebHistory } from 'vue-router';

import RegisterPage from '../components/RegisterPage.vue';
import HomePage from '../components/HomePage.vue';  // 홈 페이지 컴포넌트
import LoginPage from '../components/LoginPage.vue';
const routes = [
    {
        path: '/',
        name: 'Home',
        component: HomePage
    },
    {
        path: '/login',
        name: 'Login',
        component: LoginPage
    },
    {
        path: '/register',
        name: 'Register',
        component: RegisterPage
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

export default router;
