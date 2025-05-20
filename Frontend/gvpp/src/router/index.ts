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
      path: '/map',
      name: 'map',
      component: () => import ('../views/MapView.vue'),
      meta: {
        showInNav: "Search by location"
      },
    },
    {
      path: '/filter',
      name: 'zoek',
      component: () => import('../views/FilterView.vue'),
      meta: {
        showInNav: "Zoek vrije plaatsen"
      },
    },
    {
      path: '/privacy',
      name: 'privacy',
      component: () => import('../views/PrivacyPolicy.vue'),
      meta: {
        showInNav: "Privacy Policy"
      },
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue'),
    },
    {
      path: '/adminfilter',
      name: 'adminfilter',
      component: () => import('../views/AdminFilterView.vue'),
    },





  ],
})

export default router
