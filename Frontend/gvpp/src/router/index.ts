import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/HomeView.vue'),
      meta: {
        showInNav: "Home"
      },
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutUsView.vue'),
      meta: {
        showInNav: "About us"
      },
    },
    {
      path: '/search',
      name: 'search',
      component: () => import ('../views/SearchByName.vue'),
      meta: {
        showInNav: "Search by name"
      },
    },
    {
      path: '/map',
      name: 'map',
      component: () => import ('../views/MapView.vue'),
      meta: {
        showInNav: "Search by location"
      },
    },





  ],
})

export default router
