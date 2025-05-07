<script setup lang="ts">
import { RouterLink, RouterView, useRouter } from 'vue-router'
import { ref, computed, onMounted, onUnmounted } from "vue";

const router = useRouter();
const isOpen = ref(false);

const navLinks = computed(() =>
    router.options.routes.filter(route => route.meta?.showInNav)
)

const handleClickOutside = (event: Event) => {
  const nav = document.getElementById("navigation-menu");
  if (nav && !nav.contains(event.target as Node)) {
    isOpen.value = false;
  }
};

onMounted(() => {
  document.addEventListener("click", handleClickOutside);
});

onUnmounted(() => {
  document.removeEventListener("click", handleClickOutside);
});
</script>

<template>
    <body>
        <header>
            <div class="container mx-auto flex justify-between items-center">
                <!-- Later misschien dynamisch
                <h1 class="text-xl font-bold">
                   {{ page-title }}
                </h1>
                -->
                <button @click.stop="isOpen = !isOpen"
                    class="focus:outline-none fixed top-6 right-10 z-30 h-6 w-6 "
                >
                    <svg class="w-6 h-6" fill="none" stroke="currentColor" stroke-width="2"
                        viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"
                    >
                        <path v-if="!isOpen"
                            stroke-linecap="round" stroke-linejoin="round"
                            d="M4 6h16M4 12h16M4 18h16"
                        >
                        </path>
                        <path v-else
                            stroke-linecap="round" stroke-linejoin="round"
                            d="M6 6l12 12M6 18L18 6">
                        >
                        </path>
                    </svg>
                </button>
                <nav id="navigation-menu"
                    :class="isOpen ? 'block' : 'hidden'"
                    class="absolute px-10 py-8 top-0 left-0 w-full h-full bg-gray-100 z-20"
                    @click.stop
                >
                    <div
                        class="flex flex-col gap-6 justify-start items-center h-full"
                    >
                        <RouterLink
                            :to="{name: 'home'}"
                            @click="isOpen = false"
                            class="w-full mb-5"
                        >



                        </RouterLink>
                        <RouterLink
                            v-for="route in navLinks"
                            :key="route.path"
                            :to="route.path"
                            @click="isOpen = false"
                            class="font-bold text-xl"
                        >
                            {{ route.meta?.showInNav }}
                        </RouterLink>
                    </div>

                </nav>
            </div>
        </header>
        <RouterView/>
    </body>

</template>


