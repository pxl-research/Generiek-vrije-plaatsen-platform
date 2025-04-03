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
          <h2 class="text-4xl font-bold mt-20">Our Mission</h2>
          <p class="mt-4 text-xl text-gray-700">
            Our mission is to make <span class="font-semibold">finding available spaces</span> easier through a <span class="font-semibold">user-friendly and informative platform.</span>
          </p>

          <p class="mt-4 text-xl text-gray-700">We aim to help individuals and families find the best options <span class="font-semibold">for care facilities.</span>By providing comprehensive and reliable information,  <span class="font-semibold">we strive to simplify the decision-making process</span>
            and ensure transparency for all involved.
          </p>
        </div>
        <div class="lg:w-1/2">
          <img src="/assets/placeholder.jpg" alt="Missie afbeelding" class="rounded-lg shadow-md">
        </div>
      </section>

      <section class="flex flex-col lg:flex-row-reverse items-center gap-12">
        <div class="lg:w-1/2">
          <h2 class="text-4xl font-bold">Our Vision</h2>
          <p class="mt-4 text-xl text-gray-700">
            We envision a future where everyone has easy access to the information they need to make <span class="font-semibold">well-informed choices</span> regarding care facilities.
            We believe in equal opportunities for all, regardless of their background or specific needs, and we are committed to promoting inclusivity and accessibility within these sectors.
          </p>
          <p class="mt-4 text-xl text-gray-700">
            By offering a <span class="font-semibold">central platform</span>  that connects individuals, care providers, and communities, we hope to contribute to a stronger, more informed, and engaged support network.
          </p>
        </div>
        <div class="lg:w-1/2">
          <img src="/assets/placeholder.jpg" alt="Visie afbeelding" class="rounded-lg shadow-md">
        </div>
      </section>

      <section class="flex flex-col items-center gap-6 ">
        <h2 class="text-4xl mb-10 mt-20 font-bold">Powered By</h2>
        <img src="/assets/logo-codeforbelgium.png" alt="Mission image" class="rounded-lg  w-full max-w-md">
        <p class="mt-6 text-xl text-gray-700 max-w-2xl">
          CodeForBelgium is a passionate community of volunteers dedicated to creating impactful projects. By combining teamwork, expertise, and a commitment to quality, we deliver solutions that are highly maintainable yet cost-effective. Whether you’re looking to bring an idea to life or support meaningful projects, CodeForBelgium is here to make it happen. Together, we code for a better tomorrow!
        </p>

      </section>






      <section>
        <h2 class="text-4xl font-bold text-center mb-8">Our Values</h2>
        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8">
          <div class="p-6 border rounded-lg shadow-sm bg-slate-300">
            <h3 class="font-bold text-xl">Transparency</h3>
            <p class="text-gray-600 mt-2">We provide clear and reliable information about available care options.</p>
          </div>
          <div class="p-6 border rounded-lg shadow-sm bg-slate-300">
            <h3 class="font-bold text-xl">Accessibility</h3>
            <p class="text-gray-600 mt-2">Our platform is user-friendly and inclusive for everyone.</p>
          </div>
          <div class="p-6 border rounded-lg shadow-sm bg-slate-300">
            <h3 class="font-bold text-xl">Reliability</h3>
            <p class="text-gray-600 mt-2">We work with trusted sources and keep our information up to date.</p>
          </div>
          <div class="p-6 border rounded-lg shadow-sm bg-slate-300">
            <h3 class="font-bold text-xl">Community</h3>
            <p class="text-gray-600 mt-2">We support an active network of individuals, families, and care providers.</p>
          </div>
          <div class="p-6 border rounded-lg shadow-sm bg-slate-300">
            <h3 class="font-bold text-xl">Support</h3>
            <p class="text-gray-600 mt-2">We guide users through every step of the decision-making process.</p>
          </div>
        </div>
      </section>


      <section>
        <h2 class="text-4xl font-bold text-center mb-8">Our Team</h2>
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


