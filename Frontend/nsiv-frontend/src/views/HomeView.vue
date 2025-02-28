<template>
  <HeaderComponent />
  <div class="relative min-h-screen flex flex-col px-6 pt-12 bg-gradient-to-tl from-slate-500 via-slate-200 to-slate-200">


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

      <img src="/assets/NSIV_header.png">

    </div>



    <div class="flex items-center justify-center pt-10">
      <ButtonComponent text="Zoek vrije plaatsen" class="w-full" />
    </div>
    <router-link to="/onboarding" class="text-right text-lg mt-4 text-white">
      onboarding →
    </router-link>
  </div>
</template>

<script setup lang="ts">
import HeaderComponent from '@/components/HeaderComponent.vue'
import ButtonComponent from '@/components/ButtonComponent.vue'
</script>
