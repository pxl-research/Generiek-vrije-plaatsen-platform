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
      path: '/escaperooms/filter',
      name: 'zoek_escaperoom',
      component: () => import('../views/SectorFilterViews/EscapeRoomFilterView.vue'),
      meta: {
        showInNav: "Zoek vrije plaatsen"
      },
    },
    {
      path: '/nurseries/filter',
      name: 'zoek_nursery',
      component: () => import('../views/SectorFilterViews/NurseryFilterView.vue'),
      meta: {
        showInNav: "Zoek vrije plaatsen"
      },
    },
    {
      path: '/schools/filter',
      name: 'zoek_school',
      component: () => import('../views/SectorFilterViews/SchoolFilterView.vue'),
      meta: {
        showInNav: "Zoek vrije plaatsen"
      },
    },
    {
      path: '/filterdashboard',
      name: 'filterdashboard',
      component: () => import('../views/Admin/FilterDashboardView.vue'),
      meta: {
        showInNav: "Filter dashboard"
      }
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
      path: '/escaperooms/freespotsdashboard',
      name: 'escaperooms_freespotsdashboard',
      component: () => import('../views/Admin/EscapeRoomFreeSpots.vue'),
    },
    {
      path: '/nurseries/freespotsdashboard',
      name: 'nurseries_freespotsdashboard',
      component: () => import('../views/Admin/EscapeRoomFreeSpots.vue'),
    },
    {
      path: '/schools/freespotsdashboard',
      name: 'schools_freespotsdashboard',
      component: () => import('../views/Admin/SchoolFreeSpots.vue'),
    }





  ],
})

export default router
