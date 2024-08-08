import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import TagView from '../views/TagView.vue'
import LogView from '../views/LogView.vue'
import LmessageView from '../views/LmessageView.vue'
import WriteView from '../views/WriteView.vue'
import LoginView from '../views/LoginView.vue'
import SignupView from '../views/SignupView.vue'
import TagSearchPage from '../views/TagSearchPage.vue'
import PersonalCenter from '../views/PersonalCenter.vue'
Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'home',
        component: HomeView
    },
    {
        path: '/tag',
        name: 'tag',
        component: TagView
    },
    {
        path: '/log',
        name: 'log',
        component: LogView
    },
    {
        path: '/lmessage',
        name: 'lmessage',
        component: LmessageView
    },
    {
        path: '/write',
        name: 'write',
        component: WriteView
    },
    {
        path: '/login',
        name: 'login',
        component: LoginView
    },
    {
        path: '/signup',
        name: 'signup',
        component: SignupView
    },
    {
        path: '/tag/:id',
        name: 'Tag_id',
        component: TagSearchPage
    },
    {
        path: '/account/:id',
        name: 'PersonalCenter',
        component: PersonalCenter
    }
]

const router = new VueRouter({
    mode: 'history',
    routes
})

export default router
