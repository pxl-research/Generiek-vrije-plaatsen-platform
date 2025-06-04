<script setup lang="ts">
import HeaderComponent from '@/components/HeaderComponent.vue';
</script>

<template>
  <div class="min-h-screen flex flex-col bg-slate-200">

        <HeaderComponent/>

  <main class="mx-auto max-w-6xl px-6  space-y-16">

  <section class="flex flex-col lg:flex-row items-center gap-12">
    <div class="lg:w-1/2">
      <h2 class="text-4xl font-bold mt-10">Onze Missie</h2>
      <p class="mt-4 text-xl text-gray-700">
        Onze missie is om het <span class="font-semibold">vinden van beschikbare plaatsen</span> gemakkelijker te maken via een <span class="font-semibold">gebruiksvriendelijk en informatief platform.</span>
      </p>

      <p class="mt-4 text-xl text-gray-700">We willen individuen en families helpen de beste opties <span class="font-semibold">voor zorginstellingen</span> te vinden. Door uitgebreide en betrouwbare informatie te bieden, <span class="font-semibold">streven we ernaar het beslissingsproces te vereenvoudigen</span>
        en transparantie te waarborgen voor alle betrokkenen.
      </p>
    </div>
    <div class="lg:w-1/2 mt-10">
      <img src="/assets/placeholder.jpg" alt="Missie afbeelding" class="rounded-lg shadow-md">
    </div>
  </section>

  <section class="flex flex-col lg:flex-row-reverse items-center gap-12">
    <div class="lg:w-1/2">
      <h2 class="text-4xl font-bold">Onze Visie</h2>
      <p class="mt-4 text-xl text-gray-700">
        Wij zien een toekomst voor ons waarin iedereen gemakkelijk toegang heeft tot de informatie die nodig is om <span class="font-semibold">goed geïnformeerde keuzes</span> te maken over zorginstellingen.
        We geloven in gelijke kansen voor iedereen, ongeacht achtergrond of specifieke behoeften, en zetten ons in voor inclusiviteit en toegankelijkheid binnen deze sectoren.
      </p>
      <p class="mt-4 text-xl text-gray-700">
        Door een <span class="font-semibold">centraal platform</span> aan te bieden dat individuen, zorgverleners en gemeenschappen verbindt, hopen we bij te dragen aan een sterker, beter geïnformeerd en betrokken ondersteuningsnetwerk.
      </p>
    </div>
    <div class="lg:w-1/2 mt-10">
      <img src="/assets/placeholder.jpg" alt="Visie afbeelding" class="rounded-lg shadow-md">
    </div>
  </section>

  <section class="flex flex-col items-center gap-6 ">
    <h2 class="text-4xl mb-10 mt-20 font-bold">Mede mogelijk gemaakt door</h2>
    <img src="/assets/logo-codeforbelgium.png" alt="Missie afbeelding" class="rounded-lg  w-full max-w-md">
    <p class="mt-10 text-xl text-gray-700 max-w-2xl">
      CodeForBelgium is een gepassioneerde gemeenschap van vrijwilligers die zich toeleggen op het creëren van impactvolle projecten. Door samenwerking, expertise en toewijding aan kwaliteit te combineren, leveren we oplossingen die goed onderhoudbaar en toch kostenefficiënt zijn. Of je nu een idee tot leven wilt brengen of betekenisvolle projecten wilt steunen, CodeForBelgium helpt je dit waar te maken.<br><br> Samen coderen we voor een betere toekomst!
    </p>
  </section>

  <section>
    <h2 class="text-4xl font-bold text-center  mb-10">Onze Waarden</h2>
    <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8">
      <div class="p-6 border rounded-lg shadow-sm bg-slate-300">
        <h3 class="font-bold text-xl">Transparantie</h3>
        <p class="text-gray-600 mt-2">Wij bieden duidelijke en betrouwbare informatie over beschikbare zorgopties.</p>
      </div>
      <div class="p-6 border rounded-lg shadow-sm bg-slate-300">
        <h3 class="font-bold text-xl">Toegankelijkheid</h3>
        <p class="text-gray-600 mt-2">Ons platform is gebruiksvriendelijk en inclusief voor iedereen.</p>
      </div>
      <div class="p-6 border rounded-lg shadow-sm bg-slate-300">
        <h3 class="font-bold text-xl">Betrouwbaarheid</h3>
        <p class="text-gray-600 mt-2">We werken met betrouwbare bronnen en houden onze informatie actueel.</p>
      </div>
      <div class="p-6 border rounded-lg shadow-sm bg-slate-300">
        <h3 class="font-bold text-xl">Gemeenschap</h3>
        <p class="text-gray-600 mt-2">Wij ondersteunen een actief netwerk van individuen, families en zorgaanbieders.</p>
      </div>
      <div class="p-6 border rounded-lg shadow-sm bg-slate-300">
        <h3 class="font-bold text-xl">Ondersteuning</h3>
        <p class="text-gray-600 mt-2">We begeleiden gebruikers bij elke stap van het beslissingsproces.</p>
      </div>
    </div>
  </section>

  <section class="mb-10">
    <h2 class="text-4xl font-bold text-center mb-8">Ons Team</h2>
    <div class="grid gap-8 sm:grid-cols-2">
      <div class="border border-gray-300  shadow-sm rounded-lg p-6 flex flex-col items-center text-center">
        <img class="w-24 h-24 rounded-full bg-gray-600 mb-4">
        <p class="text-lg font-bold">Mathias Beutling</p>
        <p class="text-sm text-gray-600">
          SCRUM MASTER
        </p>
      </div>
      <div class="border border-gray-300 shadow-sm rounded-lg p-6 flex flex-col items-center text-center">
        <img class="w-24 h-24 rounded-full bg-gray-600 mb-4">
        <p class="text-lg font-bold">Simon Iven</p>
        <p class="text-sm text-gray-600">
          FULLSTACK DEVELOPER
        </p>
      </div>
      <div class="border border-gray-300 shadow-sm rounded-lg p-6 flex flex-col items-center text-center">
        <img class="w-24 h-24 rounded-full bg-gray-600 mb-4">
        <p class="text-lg font-bold">Carolina Augustyniak</p>
        <p class="text-sm text-gray-600">
          FRONT-END DEVELOPER
        </p>
      </div>
    </div>
  </section>

</main>

  </div>
</template>


