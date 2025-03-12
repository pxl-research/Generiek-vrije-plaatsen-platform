<script setup lang="ts">
import ButtonComponent from '@/components/ButtonComponent.vue'
</script>

<template>
  <div class="min-h-screen flex flex-col px-6 pt-12 bg-gradient-to-tl from-slate-500 via-slate-200 to-slate-200">
    <header class="flex justify-between items-center p-4 fixed top-0 left-0 right-0 z-10 bg-slate-200">
      <div class="flex items-center mt-4">
        <img src="/assets/nsiv_logo_v1.png" alt="Logo" class="h-18 w-auto" />
      </div>
    </header>

    <div class="mx-auto text-center mt-24 space-y-2 ">

      <h1 class="text-4xl mb-2 font-bold text-left mt-2">Welcome,</h1>
      <p class="text-lg/7 text-gray-700 text-left">
        to <b>'Naar School in Vlaanderen'</b>, your go-to platform for finding available school places
        in participating municipalities across Flanders.
      </p>

      <p class="text-lg/7 text-gray-700 text-left">
        <b>Easily search</b> for openings by <b>selecting your preferred city or town</b>, academic year,
        education level (pre-primary, primary or secondary) and <b>type of education</b> (mainstream, special or reception education).
        <b>Start your search</b> today to find the <b>perfect school</b> for your child.
      </p>

      <router-link to="/admin-selection">
        <img src="/assets/NSIV_header.png" alt="Admin of School" class="cursor-pointer hover:opacity-80 transition duration-300">
      </router-link>


    </div>



    <div class="flex items-center justify-center pt-10">
      <ButtonComponent text="Zoek vrije plaatsen" class="w-full" />
    </div>
    <router-link to="/onboarding" class="text-right text-lg mb-4 mt-4 text-white">
      onboarding →
    </router-link>
  </div>
</template>


