import { createRouter, createWebHistory } from 'vue-router'

const routes = [
    {
        path: '/',
        name: 'home',
        component: () => import('../views/Main.vue'),
    },
    {
        path: '/login',
        component: () => import('../views/Login.vue'),
    },
    {
        path: '/sign-up',
        component: () => import('../views/SignUp.vue')
    },
    {
        path: '/oauth/redirect',
        component: () => import('../components/OAuthRedirect.vue')
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router