<template>
  <div class="flex justify-between items-center mt-4 p-4 fixed top-0 left-0 right-0 z-10 bg-slate-200">
    <router-link to="/">
      <img src="/assets/nsiv_logo_v1.png" alt="Logo" class="h-18 w-auto cursor-pointer hover:opacity-80 transition" />
    </router-link>
  </div>

  <div class="min-h-screen flex flex-col items-center justify-center bg-slate-200">
    <h2 class="text-2xl font-bold mb-6">Maak een keuze</h2>

    <div class="space-y-4 w-80">
      <router-link to="/" class="block text-center bg-blue-500 text-white py-3 rounded-lg hover:bg-blue-600 transition">
        Zoek scholen
      </router-link>

      <router-link to="/admin" class="block text-center bg-gray-700 text-white py-3 rounded-lg hover:bg-gray-800 transition">
        Log in als admin
      </router-link>
    </div>
  </div>
</template>
