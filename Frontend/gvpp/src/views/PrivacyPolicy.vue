<template>

  <div class="min-h-screen flex flex-col bg-slate-200">
    <header class="flex justify-between items-center p-4 fixed top-0 left-0 right-0 z-10 bg-slate-200">
      <div class="flex items-center mt-2">
        <RouterLink to="/">
          <img src="/assets/placeholder-logo.png" alt="Logo" class="h-18 w-auto cursor-pointer" />
        </RouterLink>
      </div>
    </header>

  <main class="mx-5 my-20 flex flex-col gap-6">



      <section class="flex flex-col gap-5 text-lg mt-10">
          <div>
            <h2 class="text-4xl font-bold mt-5 mb-5">Privacy Policy</h2>

              <p>
                Our platform is designed to provide users with reliable and accessible information in a user-friendly way. We aim to simplify processes and improve access to relevant services while ensuring a secure and transparent experience.
              </p>
          </div>
          <div>
              <h3 class="font-black">Data Collection</h3>
              <p>
                We collect personal details such as name and email when users sign up or contact us. Additionally, we gather usage data to understand how visitors interact with the platform, helping us enhance functionality and overall user experience.


              </p>
          </div>
          <div>
              <h3 class="font-black">Data Usage</h3>
              <p>
                Collected data is used to communicate updates, improve services, and ensure security. We do not share personal information with third parties unless legally required.
              </p>
          </div>
          <div>
              <h3 class="font-black">Your Rights</h3>
              <p>
                  You have the right to request access to your personal information, correct inaccuracies, or request deletion. For inquiries, please reach out to us.
              </p>
          </div>
      </section>

      <h2 class="text-lg font-bold">Terms of Use</h2>
      <section class="flex flex-col gap-3 text-m">
          <div>
              <h3 class="font-black">Introduction</h3>
              <p>
                By using this platform, users agree to the outlined terms and conditions. The platform should be used responsibly and in compliance with applicable laws.
              </p>
          </div>
          <div>
              <h3 class="font-black">Website Access</h3>
              <p>
                We strive to maintain continuous availability but may occasionally limit access for maintenance, updates, or improvements.
              </p>
          </div>
          <div>
              <h3 class="font-black">Intellectual Property</h3>
              <p>
                All content, including text and graphics, is owned by the platform or its licensors. Unauthorized use or distribution is strictly prohibited.
              </p>
          </div>
      </section>

      <h2 class="text-lg font-bold">Cookie Policy</h2>
      <section class="flex flex-col gap-3 text-m">
          <div>
              <h3 class="font-black">Introduction</h3>
              <p>
                  This policy explains what cookies are and how we use them to enhance your experience.
              </p>
          </div>
          <div>
              <h3 class="font-black">Managing Cookies</h3>
              <p>
                  You can adjust your browser settings to manage or disable cookies, but this may impact website functionality.
              </p>
          </div>
          <div>
              <h3 class="font-black">Policy Updates</h3>
              <p>
                  Changes to this policy will be posted on our website to keep you informed.
              </p>
          </div>
      </section>
  </main>
</div>
</template>

<script setup lang="ts">
</script>
