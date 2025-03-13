import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/HomeView.vue'),
      meta: {
        
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
      path: '/list',
      name: 'list',
      component: () => import('../views/ListView.vue'),
      meta: {
        
      },
    },
    {
      path: '/map',
      name: 'map',
      component: () => import('../views/MapView.vue'),
      meta: {
        showInNav: "Zoek op de kaart"
      },
    },
    {
      path: '/privacy',
      name: 'privacy',
      component: () => import('../views/PrivacyView.vue'),
      meta: {
        showInNav: "Privacy en beleid"
      },
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutUsView.vue'),
      meta: {
        showInNav: "Over ons"
      },
    },
    {
      path: '/onboarding',
      name: 'onboarding',
      component: () => import('../views/OnboardingView.vue'),
      meta: {
        
      },
    },
    {
      path: '/admin',
      name: 'Admin login',
      component: () => import('../views/AdminLoginView.vue'),

    },
    {
      path: '/admin-selection',
      name: 'Admin selection',
      component: () => import('../views/AdminSelectionView.vue'),

    },

  ],
})

export default router
