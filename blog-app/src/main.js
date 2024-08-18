import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue'
import router from './router'
import http from './http';

import MyNavigation from './components/Navigation.vue'
import MyArticleList from './components/Articlelist.vue'
import MyAsideList from './components/Asidelist.vue'
import MyLmessageList from './components/LmessageList.vue'
import MyLmessageEditor from './components/LmessageEditor.vue'
import MyLogList from './views/LogView.vue'
import MyArticleWriteEditor from './components/ArticleWriteEditor.vue'


Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.component('my-nav', MyNavigation);
Vue.component('my-art-list', MyArticleList);
Vue.component('my-asi-list', MyAsideList)
Vue.component('my-lme-list',MyLmessageList)
Vue.component('my-lme-editor',MyLmessageEditor)
Vue.component('my-log-list',MyLogList)
Vue.component('my-art-wrt-editor',MyArticleWriteEditor)

Vue.prototype.$formatTime = function (time) {
    const date = new Date(time);
    const options = { year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric' };
    return date.toLocaleString(undefined, options);
};

Vue.prototype.$http = http;

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')