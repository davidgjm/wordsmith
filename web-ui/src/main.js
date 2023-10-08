import './assets/main.css'


import { createApp } from 'vue'
import { createPinia } from 'pinia'
import 'material-symbols';

// import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/index.css'
import ElementPlus from 'element-plus'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(createPinia())
app.use(router)


app.use(ElementPlus, { size: 'small', zIndex: 3000 })

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}


app.mount('#app');
