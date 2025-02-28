<script setup lang="ts">
import { RouterLink, RouterView, useRouter } from 'vue-router'
import { ref, computed } from "vue";

const router = useRouter();
const isOpen = ref(false);

const navLinks = computed(() =>
    router.options.routes.filter(route => route.meta?.showInNav === true)
)
console.log(navLinks.value);
</script>

<template>
    <body>
        <header class="mx-5 my-4">
            <div class="container mx-auto flex justify-between items-center">
                <!-- Later misschien dynamisch
                <h1 class="text-xl font-bold">
                   {{ page-title }}
                </h1>
                -->
                <button @click="isOpen = !isOpen" class="focus:outline-none">
                    <svg class="w-6 h-6" fill="none" stroke="currentColor" stroke-width="2"
                    viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                    <path stroke-linecap="round" stroke-linejoin="round"
                        d="M4 6h16M4 12h16M4 18h16"></path>
                    </svg>
                </button>
                <nav :class="isOpen ? 'block' : 'hidden'" 
                class="absolute p-4 top-16 left-0 w-full bg-gray-100"
                >   
                    <RouterLink 
                        v-for="route in navLinks" 
                        :key="route.path" 
                        :to="route.path"
                        class="block px-4 py-2"
                    >
                        {{ route.name }}
                    </RouterLink>
                    
                    <a href="#" class="block px-4 py-2">About</a>
                    <a href="#" class="block px-4 py-2">Contact</a>
                </nav>
            </div>    
            <div>

            </div>
        </header>
        <RouterView />
    </body>

</template>


