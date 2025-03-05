<template>
<!-- Map view - mobile op Figma-->
    <iframe 
            class="absolute inset-0 w-full h-full"
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d80478.5673049272!2d5.231144139688511!3d50.9245452049915!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47c12183ded75db7%3A0xf7cb7b027e7e2181!2sHasselt!5e0!3m2!1sen!2sbe!4v1741168303625!5m2!1sen!2sbe"
            loading="lazy"
    ></iframe>    
    <main class="absolute flex flex-col pt-30 top-0 left-0 w-full h-full p-5 border-1"><!--margin naar app.vue zetten-->
        <nav class="flex items-center">
            <input type="text" 
                class="w-full p-1 bg-white rounded-lg"
            >
            <button
                class="h-8 w-8 flex justify-center items-center ml-3 bg-red-200 text-red-900 rounded-lg border-2 border-white"
            >
                <svg class="w-6 h-6" fill="none" stroke="currentColor" stroke-width="2"
                    viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"
                >
                    <path
                        stroke-linecap="round" stroke-linejoin="round"
                        d="M6 6l12 12M6 18L18 6">
                    >
                    </path>
                </svg>
            </button>
        </nav>
        <section
            class="w-full p-4 bg-white flex flex-col gap-2 justify-center items-center mt-auto"
        >
            <p class="text-gray-500 flex justify-start w-full">
                Your Location
            </p>
            <div class="flex justify-between w-full">
                <div>
                    <h1 class="text-xl font-black">
                        3500 Hasselt
                    </h1>
                    <p class="text-sm font-semibold text-gray-500">
                        + deelgemeenten
                    </p>
                </div>
                <button class="mt-2 px-10 py-2 bg-blue-700 text-white rounded">Use</button>
            </div>
            <hr class="border-t-1 border-gray-500 w-full my-4">

            <p class="w-full text-sm text-gray-500">
                Nearby locations
            </p>
            <div class="flex w-full gap-3 items-center">
                <div class="rounded-full p-6 bg-gray-500"></div>
                <div class="w-full h-full">
                    <h2>
                        Kermt
                    </h2>
                    <p class="text-sm text-gray-500">
                        3500, Hasselt (Limburg)
                    </p>
                </div>
            </div>
        </section>
    </main>
</template>

<script setup lang="ts">

</script>