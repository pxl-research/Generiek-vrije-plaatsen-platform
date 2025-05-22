<template>

  <div class="min-h-screen flex flex-col bg-slate-200">
    <HeaderComponent/>

  <main class="mx-15 my-5 flex flex-col gap-6">



      <section class="flex flex-col gap-5 text-lg mt-10">
          <div>
            <h2 class="text-4xl font-bold mt-5 mb-5">Privacy Policy</h2>

              <p>
                Our platform is designed to provide users with reliable and accessible information in a user-friendly way.
                We aim to simplify processes and improve access to relevant services while ensuring a secure and transparent experience.
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
            <h3 class="font-black">Location Data</h3>
            <p>
              With your consent, we may collect and process location data to show available places near you.
            </p>
        </div>
          <div class="mb-10">
              <h3 class="font-black">Your Rights</h3>
              <p>
                  You have the right to request access to your personal information, correct inaccuracies, or request deletion. For inquiries, please reach out to us.
              </p>
          </div>
      </section>

      <h2 class="text-xl font-black">Terms of Use</h2>
      <section class="flex flex-col gap-3 text-m">
          <div>
              <h3 class="font-black">Acceptance of Terms</h3>
              <p>
                By accessing or using [Generiek Vrije Plaatsen Platform] ("the GVPP"), you agree to be bound by these Terms of Use and our Privacy Policy. If you do not agree, please do not use the Platform.
              </p>
          </div>
          <div>
              <h3 class="font-black">Use of the Platform</h3>

               <ul>
                <li>You agree to use the Platform only for lawful purposes and in a way that does not infringe the rights of others or restrict their use and enjoyment of the Platform.</li>
                <li>You agree not to misuse the Platform by interfering with its normal operation or accessing it using a method other than the interface and instructions provided.</li>
                <li>You are responsible for maintaining the confidentiality of your account information and for all activities under your account.</li>
               </ul>

          </div>
          <div>
              <h3 class="font-black">Booking and Availability</h3>
              <ul>
                <li>GVPP provides information about available places for booking with various partner organizations.</li>
                <li>Availability and booking confirmation depend on the partner organizations and are subject to their terms and conditions.</li>
                <li>We do not guarantee the accuracy or completeness of availability information and are not responsible for booking errors or cancellations.</li>
              </ul>
          </div>

          <div>
            <h3 class="font-black">Intellectual Property</h3>
            <ul>
              <li>All content on GVPP, including text, graphics, logos, and software, is owned or licensed by [GVPP] and protected by intellectual property laws.</li>
              <li>Availability and booking confirmation depend on the partner organizations and are subject to their terms and conditions.</li>
              <li>We do not guarantee the accuracy or completeness of availability information and are not responsible for booking errors or cancellations.</li>
            </ul>
        </div>

        <div>
          <h3 class="font-black">Contact Information</h3>
          <p>If you have questions about these Terms, please contact us at:
            📧 [support@[platform].com]
            🏢 [Company Name], [Address]

            </p>
      </div>
      </section>

      <h2 class="text-xl font-black">Cookie Policy</h2>
      <section class="flex flex-col gap-3 text-m">
          <div>
              <h3 class="font-black">What are cookies?</h3>
              <p>
                Cookies are small text files that are stored on your device (computer, smartphone, or tablet) when you visit a website. They help the website remember your preferences and improve your experience.
              </p>
          </div>
          <div>
              <h3 class="font-black">How we use cookies</h3>
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
import HeaderComponent from '@/components/HeaderComponent.vue';
</script>
