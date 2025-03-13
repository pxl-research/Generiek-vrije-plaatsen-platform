<script setup lang="ts">
import { ref } from 'vue';

const progress = ref(33.33);
const number = ref([ "1", "2", "3" ]);

const title = ref([ "Zoek naar resultaten", "Zoek naar scholen", "Zoek naar opleidingen" ]);

const text = ref([
  "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
  "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
  "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
]);

const increaseProgress = () => {
  if (progress.value < 100) {
    progress.value += 33.33;
  }
  currentNumber.value = (currentNumber.value + 1) % number.value.length;
  currentTitle.value = (currentTitle.value + 1) % title.value.length;
  currentText.value = (currentText.value + 1) % text.value.length;
};

const decreaseProgress = () => {
  if (progress.value > 33.33) {
    progress.value -= 33.33;
  }
  currentNumber.value = (currentNumber.value - 1) % number.value.length;
  currentTitle.value = (currentTitle.value - 1) % title.value.length;
  currentText.value = (currentText.value - 1) % text.value.length;
};

const currentNumber = ref(0);
const currentTitle = ref(0);
const currentText = ref(0);
</script>

<template>
  <div class="bg-slate-200 w-screen min-h-screen flex-row items-center px-6 pt-20">
    <div class="w-full max-w-xs mx-auto bg-gray-300 rounded-lg overflow-hidden">
      <div class="h-3 bg-blue-900 transition-all duration-300" :style="{ width: `${progress}%` }"></div>
    </div>

    <div class="image w-full mx-auto max-w-xs mt-10">
      <img src="/public/assets/NSIV_header.png">
    </div>

    <div class="number bg-black rounded-4xl ml-5 w-9 h-9 flex items-center justify-center mt-6">
      <p class="text-lg font-semibold text-white">{{ number[currentNumber] }}</p>
    </div>

    <p class="ml-5 mt-3 text-lg font-bold">{{ title[currentTitle] }}</p>

    <p class="ml-5 mt-3">{{ text[currentText] }}</p>

    <div class="bottom flex justify-between items-center pb-5   mt-15 ml-5 w-77">
      <p class="underline text-xl underline-offset-4"><RouterLink to="/">Skip</RouterLink></p>
      <div class="buttons w-50 flex justify-between items-center">

        <button
          @click="decreaseProgress"
          :disabled="currentNumber <= 0"
          class="pb-1 font-bold text-4xl bg-blue-900 h-16 w-16 text-white flex-row justify-items-center content-center rounded-4xl hover:bg-blue-600 transition disabled:opacity-50">
          ←
        </button>


        <button
          @click="increaseProgress"
          :disabled="currentNumber >= 2"
          class="pb-1 font-bold text-4xl bg-blue-900 h-16 w-16 text-white flex-row justify-items-center content-center rounded-4xl hover:bg-blue-600 transition disabled:opacity-50">
          →
        </button>
      </div>
    </div>
  </div>
</template>
