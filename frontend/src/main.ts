import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from "./router";
import { createPinia } from 'pinia'
import piniaPersistedstate from 'pinia-plugin-persistedstate';

const pinia = createPinia()
pinia.use(piniaPersistedstate)

createApp(App)
    .use(router)
    .use(pinia)
    .mount('#app')
