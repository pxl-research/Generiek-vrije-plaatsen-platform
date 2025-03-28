<script setup lang="ts"></script>

<template>
  <div class="min-h-screen flex flex-col bg-slate-200">
    <header class="flex justify-between items-center p-4 fixed top-0 left-0 right-0 z-10 bg-slate-200">
      <div class="flex items-center mt-4">
        <RouterLink to="/">
          <img src="/assets/placeholder-logo.png" alt="Logo" class="h-18 w-auto cursor-pointer" />
        </RouterLink>
      </div>
    </header>
    <main class="mx-auto max-w-6xl px-6 py-12 space-y-16">



      <section class="flex flex-col lg:flex-row items-center gap-12">
        <div class="lg:w-1/2">
          <h2 class="text-4xl font-bold mt-20">Onze Missie</h2>
          <p class="mt-4 text-xl text-gray-700">
            Onze missie is om ouders en scholen in Vlaanderen te <span class="font-semibold">verbinden</span> door middel van een <span class="font-semibold">gebruiksvriendelijk en informatief platform.</span>
          </p>

          <p class="mt-4 text-xl text-gray-700">Wij streven ernaar om ouders te helpen bij het vinden van de <span class="font-semibold">beste onderwijsopties</span> voor hun kinderen, door uitgebreide en betrouwbare informatie te bieden over <span class="font-semibold">scholen, studierichtingen en ondersteuningsmogelijkheden.</span>
            Ons doel is om het proces van schoolkeuze en inschrijving transparanter, eenvoudiger en stressvrij te maken voor alle betrokkenen.
          </p>
        </div>
        <div class="lg:w-1/2">
          <img src="/assets/placeholder-logo.png" alt="Missie afbeelding" class="rounded-lg shadow-md">
        </div>
      </section>

      <section class="flex flex-col lg:flex-row-reverse items-center gap-12">
        <div class="lg:w-1/2">
          <h2 class="text-4xl font-bold">Onze Visie</h2>
          <p class="mt-4 text-xl text-gray-700">
            Onze visie is een onderwijsomgeving waarin elke ouder gemakkelijk toegang heeft tot de informatie die nodig is om weloverwogen beslissingen te nemen over de <span class="font-semibold">schoolkeuze</span> van hun kinderen.
             Wij geloven in gelijke kansen voor alle kinderen,<span class="font-semibold"> ongeacht hun achtergrond of speciale behoeften</span>, en streven ernaar om inclusiviteit en diversiteit binnen het onderwijs te bevorderen.
          </p>
          <p class="mt-4 text-xl text-gray-700">
            Door een <span class="font-semibold">centraal platform</span> te bieden dat ouders, scholen en gemeenschappen samenbrengt, willen wij bijdragen aan een sterkere, beter geïnformeerde en meer betrokken <span class="font-semibold">onderwijsgemeenschap</span> in Vlaanderen.
          </p>
        </div>
        <div class="lg:w-1/2">
          <img src="/assets/placeholder-logo.png" alt="Visie afbeelding" class="rounded-lg shadow-md">
        </div>
      </section>


      <section>
        <h2 class="text-4xl font-bold text-center mb-8">Onze Waarden</h2>
        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8">
          <div class="p-6 border rounded-lg shadow-sm bg-slate-300">
            <h3 class="font-bold text-xl">Transparantie</h3>
            <p class="text-gray-600 mt-2">Wij bieden duidelijke en betrouwbare informatie over onderwijsopties.</p>
          </div>
          <div class="p-6 border rounded-lg shadow-sm bg-slate-300">
            <h3 class="font-bold text-xl">Toegankelijkheid</h3>
            <p class="text-gray-600 mt-2">Ons platform is gebruiksvriendelijk en inclusief.</p>
          </div>
          <div class="p-6 border rounded-lg shadow-sm bg-slate-300">
            <h3 class="font-bold text-xl">Betrouwbaarheid</h3>
            <p class="text-gray-600 mt-2">We werken met betrouwbare bronnen en up-to-date informatie.</p>
          </div>
          <div class="p-6 border rounded-lg shadow-sm bg-slate-300">
            <h3 class="font-bold text-xl">Gemeenschap</h3>
            <p class="text-gray-600 mt-2">We ondersteunen een actieve gemeenschap van ouders en scholen.</p>
          </div>
          <div class="p-6 border rounded-lg shadow-sm bg-slate-300">
            <h3 class="font-bold text-xl">Ondersteuning</h3>
            <p class="text-gray-600 mt-2">We begeleiden ouders in elke stap van het proces.</p>
          </div>
        </div>
      </section>


      <section>
        <h2 class="text-4xl font-bold text-center mb-8">Ons Team</h2>
        <div class="grid gap-8 sm:grid-cols-2">
          <div class="border border-gray-300  shadow-sm rounded-lg p-6 flex flex-col items-center text-center">
            <img class="w-24 h-24 rounded-full bg-gray-600 mb-4">
            <p class="text-lg font-bold">Emma Janssen</p>
            <p class="text-sm text-gray-600">
              Projectleider met een achtergrond in onderwijskunde en 10 jaar ervaring.
            </p>
          </div>
          <div class="border border-gray-300 shadow-sm rounded-lg p-6 flex flex-col items-center text-center">
            <img class="w-24 h-24 rounded-full bg-gray-600 mb-4">
            <p class="text-lg font-bold">Lucas De Vries</p>
            <p class="text-sm text-gray-600">
              Technisch directeur met ervaring in softwareontwikkeling en IT-beheer.
            </p>
          </div>
        </div>
      </section>

     
      <section class="text-center">
        <h2 class="text-4xl font-bold">Volg ons</h2>
        <ul class="flex justify-center gap-6 mt-4">
          <li><p class="text-lg">ⓕ Facebook</p></li>
          <li><p class="text-lg">𝕏 Twitter</p></li>
          <li><p class="text-lg">🅾 Instagram</p></li>
        </ul>
      </section>
    </main>
  </div>
</template>


