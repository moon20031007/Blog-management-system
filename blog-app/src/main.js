import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue'
import router from './router'
import MyNavigation from './components/Navigation.vue'
import MyArticleList from './components/Articlelist.vue'
import MyAsideList from './components/Asidelist.vue'
import LmessageList from './views/LmessageList.vue';
import LmessageEditor from './views/LmessageEditor.vue';

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.component('my-nav', MyNavigation);
Vue.component('my-art-list', MyArticleList);
Vue.component('my-asi-list', MyAsideList)
Vue.component('my-lme-list',LmessageList)
Vue.component('my-lme-editor',LmessageEditor)



new Vue({
    router,
    render: h => h(App)
}).$mount('#app')