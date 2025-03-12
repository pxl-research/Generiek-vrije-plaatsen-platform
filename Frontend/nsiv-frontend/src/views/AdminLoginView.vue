<template>
  <div class="flex justify-between items-center mt-4 p-4 fixed top-0 left-0 right-0 z-10 bg-slate-200">
    <router-link to="/">
      <img src="/assets/nsiv_logo_v1.png" alt="Logo" class="h-18 w-auto cursor-pointer hover:opacity-80 transition" />
    </router-link>
  </div>



  <div class="min-h-screen flex items-center justify-center bg-slate-200">

    <div class="bg-white p-8 rounded-lg shadow-md w-100 mb-20 ">
      <h2 class="text-2xl font-bold mb-4 text-center">Welkom</h2>
      <p class="text-gray-600 text-center mb-4">Meld u aan om door te gaan</p>


      <form @submit.prevent="handleLogin">
        <div class="mb-4">
          <label class="block text-gray-700">Email</label>
          <input v-model="email" type="email" class="w-full p-2 border rounded" required />
        </div>

        <div class="mb-4">
          <label class="block text-gray-700">Wachtwoord</label>
          <input v-model="password" type="password" class="w-full p-2 border rounded" required />
        </div>
        <button type="submit" class="w-full bg-blue-500 text-white p-3 font-bold rounded hover:bg-blue-600">
          Inloggen
        </button>
      </form>
    </div>
  </div>

</template>

<script setup lang="ts">
import { ref } from 'vue';

const email = ref('');
const password = ref('');

const handleLogin = () => {
  console.log('Inloggen met:', email.value, password.value);
};
</script>
