// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import './assets/css/defualt.css'
import vuePicturePreview from 'vue-picture-preview'
import 'vue-ydui/dist/ydui.rem.css';
import YDUI from 'vue-ydui';


Vue.config.productionTip = false;
Vue.use(vuePicturePreview)
Vue.use(YDUI)


router.afterEach((to,from,next) => {
  window.scrollTo(0,0);
});
router.beforeEach((to, from, next) => {
  /* 路由发生变化修改页面title */
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next()})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
