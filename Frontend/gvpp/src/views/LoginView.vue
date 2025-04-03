<template>
  <HeaderComponent/>
  <div class="flex items-center justify-center">
    <div class="w-80 max-w-md p-8 bg-gray-100 rounded-xl border-2 shadow-md rounded-lg mt-20">
      <h2 class="text-2xl font-semibold text-center mb-6">Login</h2>

      <form @submit.prevent="login">
        <!-- Username Input -->
        <div class="mb-4">
          <label for="username" class="block text-sm font-medium">Username</label>
          <input
            id="username"
            v-model="username"
            type="text"
            class="w-full p-2 border border-gray-300 rounded"
            required
          />
        </div>

        <!-- Password Input -->
        <div class="mb-6">
          <label for="password" class="block text-sm font-medium">Password</label>
          <input
            id="password"
            v-model="password"
            type="password"
            class="w-full p-2 border border-gray-300 rounded"
            required
          />
        </div>

        <!-- Login Button -->
        <button
          type="submit"
          class="w-full py-2 bg-blue-500 text-white rounded hover:bg-blue-600 transition"
        >
          Login
        </button>
      </form>

      <!-- Error Message -->
      <p v-if="loginError" class="text-red-500 text-sm mt-2">{{ loginError }}</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { useRouter } from "vue-router";
import HeaderComponent from "@/components/HeaderComponent.vue";

// State variables
const username = ref<string>("");
const password = ref<string>("");
const loginError = ref<string>("");

// Vue Router
const router = useRouter();

// Login function
const login = () => {
  const users = [
    { username: "admin", password: "admin123", role: "admin", route: "adminfilter" },
    { username: "user", password: "user123", role: "user", route: "/home" },
  ];

  const user = users.find(
    (u) => u.username === username.value && u.password === password.value
  );

  if (user) {
    localStorage.setItem("role", user.role);
    router.push(user.route);
  } else {
    loginError.value = "Invalid credentials. Try again.";
  }
};
</script>
