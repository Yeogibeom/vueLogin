import { createRouter, createWebHistory } from 'vue-router';

import RegisterPage from '../components/RegisterPage.vue';
import HomePage from '../components/HomePage.vue';
import LoginPage from '../components/LoginPage.vue';
import ListPage from '../components/ListPage.vue';  // 경로 확인
import EditPage from "@/components/EditPage.vue";
import DetailPage from "@/components/DetailPage.vue";
import UpdateItemPage from "@/components//UpdataItemPage.vue"
import PostWrite from "@/components/PostWrite.vue";
import PostList from "@/components/PostList.vue";
import DetailPost from "@/components/DetailPost.vue";

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
    },
    {
        path: '/list',
        name:'List',
        component: ListPage
    },
    {
        path: '/edit',
        name:'edit',
        component: EditPage
    },
    {
        path: '/detail/:id',
        name:'Detail',
        component: DetailPage
    },
    {
        path:'/UpdateItem/:id',
        name:'UpdateItem',
        component: UpdateItemPage
    },
    {
        path:'/PostWrite',
        name:'PostWrite',
        component: PostWrite
    },
    {
        path:'/PostList',
        name:'PostList',
        component: PostList
    },
    {
        path: '/post/:id',
        name:'DetailPost',
        component: DetailPost
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

export default router;
